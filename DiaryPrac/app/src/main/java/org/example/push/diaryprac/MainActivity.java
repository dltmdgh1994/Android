package org.example.push.diaryprac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.example.push.diaryprac.data.WeatherItem;
import org.example.push.diaryprac.data.WeatherResult;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements OnTabItemSelectedListener,OnRequestListener,
        AutoPermissionsListener,MyApplication.OnResponseListener {
    private static final String TAG = "MainActivity";

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    BottomNavigationView bottomNavigationView;

    Location currentLocation;
    GPSListener gpsListener;

    int locationCnt = 0;
    String currentWeather;
    String currentAddress;
    String currentDateString;
    Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab1:
                                Toast.makeText(getApplicationContext(), "??? ?????? ???", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                                return true;
                            case R.id.tab2:
                                Toast.makeText(getApplicationContext(), "??? ?????? ???", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                                return true;
                            case R.id.tab3:
                                Toast.makeText(getApplicationContext(), "??? ?????? ???", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

        AutoPermissions.Companion.loadAllPermissions(this, 101);

        setPicturePath();
    }

    public void setPicturePath() {
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        AppConstants.FOLDER_PHOTO = sdcardPath + File.separator + "photo";
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigationView.setSelectedItemId(R.id.tab3);
        }
    }

    @Override
    public void onRequest(String command) {
        if (command != null) {
            if (command.equals("getCurrentLocation")) {
                getCurrentLocation();
            }
        }
    }

    public void getCurrentLocation() {
        currentDate = new Date();
        currentDateString = AppConstants.dateFormat3.format(currentDate);
        if (fragment2 != null) {
            fragment2.setDateString(currentDateString);
        }

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (currentLocation != null) {
                double latitude = currentLocation.getLatitude();
                double longitude = currentLocation.getLongitude();
                String msg = "Last Location -> Latitude : " + latitude + "\nLongitude : " + longitude;
                println(msg);


                getCurrentWeather();
                getCurrentAddress();
            }

            gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
            println("Current location requested.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            manager.removeUpdates(gpsListener);

            println("Current location requested.");

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;

            locationCnt++;

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Current Location -> Latitude : " + latitude + "\nLongitude : " + longitude;
            println(msg);

            getCurrentWeather();
            getCurrentAddress();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    private void println(String data) {
        Log.d(TAG, data);
    }

    public void getCurrentAddress(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(),1);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(addresses != null && addresses.size() > 0){
            Address address = addresses.get(0);
            currentAddress = address.getLocality() + " " + address.getSubLocality();
            String adminArea = address.getAdminArea();
            String country = address.getCountryName();
            println("Address : " + country + " " + adminArea + " " + currentAddress);

            if(fragment2 != null){
                fragment2.setAddress(currentAddress);
            }
        }
    }

    public void getCurrentWeather(){
        //?????? ?????? ??????
        Map<String,Double> gridMap = GridUtil.getGrid(currentLocation.getLatitude(),
                                                        currentLocation.getLongitude());
        double gridX = gridMap.get("x");
        double gridY = gridMap.get("y");
        println("x -> " +gridX+", y -> "+gridY);
        //????????? ?????? ????????? ????????? ??????
        sendLocalWeatherReq(gridX,gridY);
    }

    //????????? ?????? ????????? ????????? ??????
    public void sendLocalWeatherReq(double gridX, double gridY){
        String url = "http://www.kma.go.kr/wid/queryDFS.jsp";
        url += "?gridx=" + Math.round(gridX);
        url += "&gridy=" + Math.round(gridY);

        Map<String,String > params = new HashMap<>();

        MyApplication.send(AppConstants.REQ_WEATHER_BY_GRID, Request.Method.GET,url,params,this);
    }

    //????????? ?????? ????????? ?????? ????????? ????????? ??? ???????????? ??????
    //xml ?????? ???????????? ?????? ????????? ??????
    @Override
    public void processResponse(int requestCode, int responseCode, String response) {
        if(responseCode == 200){
            if(requestCode == AppConstants.REQ_WEATHER_BY_GRID){
                XmlParserCreator parserCreator = new XmlParserCreator() {
                    @Override
                    public XmlPullParser createParser() {
                        try{
                            return XmlPullParserFactory.newInstance().newPullParser();
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    }
                };

                GsonXml gsonXml = new GsonXmlBuilder()
                        .setXmlParserCreator(parserCreator)
                        .setSameNameLists(true)
                        .create();

                WeatherResult weatherResult = gsonXml.fromXml(response,WeatherResult.class);

                try {
                    Date tmDate = AppConstants.dateFormat.parse(weatherResult.header.tm);
                    String tmDateText = AppConstants.dateFormat2.format(tmDate);
                    println("?????? ?????? : " + tmDateText);

                    for(int i = 0; i < weatherResult.body.datas.size(); i++){
                        WeatherItem item = weatherResult.body.datas.get(i);
                        println("#"+i+" ??????: " + item.hour + "???, " + item.day + "??????");
                        println("  ??????: " + item.wfKor);
                        println("  ??????: " + item.temp + " C");
                        println("  ????????????: " + item.pop + "%");

                        println("debug 1 : " + (int)Math.round(item.ws * 10));
                        float ws = Float.valueOf(String.valueOf((int)Math.round(item.ws * 10)))/ 10.0f;
                        println("  ??????: " + ws + "m/s");
                    }

                    WeatherItem item = weatherResult.body.datas.get(0);
                    currentWeather = item.wfKor;
                    if(fragment2 != null){
                        fragment2.setWeather(item.wfKor);
                    }

                    if(locationCnt > 0 ){
                        stopLocationService();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                println("Unknown request code : " + requestCode);
            }
        }
        else{
            println("Failure response code : " + responseCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }
}
