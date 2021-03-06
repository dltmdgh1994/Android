package org.lsh.pokemonbook;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.bson.Document;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private String TAG = "Main";

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    ViewPager pager;

    FragmentManager FM;
    PokemonAdapter pokemonAdapter;
    SkillAdapter skillAdapter;

    MongoClient mongoClient;
    ArrayList<Pokemon> pokemonList;
    ArrayList<Skill> skillList;

    customAnimDialog customAnimDialog;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"destroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        if (id == R.id.btab1) {
                            pager.setCurrentItem(0);
                        } else if (id == R.id.btab2) {
                            pager.setCurrentItem(1);
                        } else if (id == R.id.btab3) {
                            pager.setCurrentItem(2);
                        }
                        return true;
                    }
                }
        );

        //??????????????? ????????? ??????
        pokemonAdapter = new PokemonAdapter();
        pokemonAdapter.setOnPokemonItemClickListener(new onPokemonItemClickListener() {
            @Override
            public void onItemClick(PokemonAdapter.ViewHolder holder, View view, int position) {
                Pokemon item = pokemonAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, Pokemon_Info.class);
                intent.putExtra("img",item.getImg());
                intent.putExtra("name",item.getName());
                intent.putExtra("type",item.getTypes());
                intent.putExtra("candy",item.getCandy());
                intent.putExtra("cp",item.getCp());
                intent.putExtra("att",item.getAtt());
                intent.putExtra("dep",item.getDep());
                intent.putExtra("hel",item.getHel());
                intent.putExtra("pro",item.getPro());

                //?????? ????????? intent??? ??????
                ArrayList<Skill> skills = new ArrayList<>();
                for(String tech : item.getTechs()){
                    for(Skill skill : skillList){
                        if(skill.getSkill_name().equals(tech)){
                            skills.add(skill);
                        }
                    }
                }
                intent.putExtra("skills",skills);

                startActivity(intent);
            }
        });
        skillAdapter = new SkillAdapter();
        skillAdapter.setOnSkillItemClickListener(new onSkillItemClickListener() {
            @Override
            public void onItemClick(SkillAdapter.ViewHolder holder, View view, int position) {
                Skill item = skillAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, Skill_Info.class);
                String name = item.getSkill_name();
                intent.putExtra("name",name);
                intent.putExtra("dps",item.getSkill_dps());
                intent.putExtra("type",item.getSkill_type());
                intent.putExtra("sort",item.getSkill_sort());
                intent.putExtra("power",item.getSkill_power());
                intent.putExtra("eps",item.getSkill_eps());
                intent.putExtra("time",item.getSkill_time());

                ArrayList<Pokemon> pokemons = new ArrayList<>();
                for(Pokemon pokemon : pokemonList){
                    for(String tech : pokemon.getTechs()){
                        if(tech.equals(name)){
                            //intent??? ????????? ??? 100kb??? ????????? ?????? ?????????, ????????? ????????? ???????????? ??????
                            //pokemon.setImg(Bitmap.createScaledBitmap(pokemon.getImg(),50,50,true));
                            pokemons.add(pokemon);
                        }
                    }
                }
                intent.putExtra("pokemons",pokemons);

                startActivity(intent);
            }
        });

        pokemonList = new ArrayList<>();
        skillList = new ArrayList<>();

        //mongodb ?????? ??? ????????? ????????? ??????
        findPokemon.start();

        //mongodb ?????? ??? ?????? ????????? ??????
        findSkill.start();

        customAnimDialog = new customAnimDialog(MainActivity.this);
        customAnimDialog.show();

        fragment1 = new Fragment1(pokemonAdapter);
        fragment2 = new Fragment2(skillAdapter);
        fragment3 = new Fragment3();
        FM = getSupportFragmentManager();

        //????????? ??????
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);
        MyPagerAdapter adapter = new MyPagerAdapter(FM);
        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    final Handler phandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            pokemonAdapter.notifyDataSetChanged();
            customAnimDialog.dismiss();
        }
    };

    final Handler shandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            skillAdapter.notifyDataSetChanged();
        }
    };

    Thread findSkill = new Thread(){
        @Override
        public void run() {
            //???????????? ?????? ????????? ????????? ???(?????? ????????? ??????)?????? ???????????? ??????.
            mongoClient = new MongoClient(new ServerAddress("ip",0));
            MongoDatabase db = mongoClient.getDatabase("customDB");
            MongoCollection collection = db.getCollection("skill");
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    JSONObject jsonObject = new JSONObject(cursor.next().toJson());
                    String name = jsonObject.getString("name");
                    String dps = jsonObject.getString("dps");
                    String type = jsonObject.getString("typ");
                    String sort = jsonObject.getString("sort");
                    String power = jsonObject.getString("power");
                    String eps = jsonObject.getString("eps");
                    String time = jsonObject.getString("time");

                    Skill skill = new Skill(name,dps,type,sort,power,eps,time);

                    skillAdapter.addItem(skill);
                    skillList.add(skill);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                cursor.close();
                new Thread(){
                    @Override
                    public void run() {
                        //UI??? ???????????? ????????? ??????????????? ???????????? ??? ??? ???????????? ???????????? ????????????.
                        //????????? ?????? ?????? ???????????? ??????
                        Message msg = shandler.obtainMessage();
                        shandler.sendMessage(msg);
                    }
                }.start();
            }
        }
    };

    Thread findPokemon = new Thread(){
        @Override
        public void run() {
            //???????????? ?????? ????????? ????????? ???(?????? ????????? ??????)?????? ???????????? ??????.
            mongoClient = new MongoClient(new ServerAddress("ip",0));
            MongoDatabase db = mongoClient.getDatabase("customDB");
            MongoCollection collection = db.getCollection("monster");
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    JSONObject jsonObject = new JSONObject(cursor.next().toJson());
                    int num = jsonObject.getInt("num");
                    String name = jsonObject.getString("name");
                    String type = jsonObject.getString("typ");
                    String[] types = type.split(",");
                    int candy = Integer.parseInt(jsonObject.getString("candy"));
                    int cp = Integer.parseInt(jsonObject.getString("cp"));
                    int att = Integer.parseInt(jsonObject.getString("att"));
                    int dep = Integer.parseInt(jsonObject.getString("dep"));
                    int hel = Integer.parseInt(jsonObject.getString("hel"));
                    String pro = jsonObject.getString("pro");
                    String tech = jsonObject.getString("techs");
                    String key = jsonObject.getString("key");
                    String[] techs = tech.split(",");
                    int img = getResources().getIdentifier("org.lsh.pokemonbook:drawable/p"+key,null,null);

                    //????????? url??? ???????????? ???????????? ???????????? ???????????? ??????????????? ???????????? intent ?????? ???
                    //?????? ????????? ???????????? ?????? ????????? ????????? R.drawable??? ???????????? ??? ????????? Resource???
                    //id?????? ????????? ?????? ??????????????? ??????.
                    //??? ????????? ???????????? ????????? ?????? ????????????.

                    //String img = jsonObject.getString("img");
                    //http??? ?????? ????????? network_security_config.xml??? ??????????????????.
                    //URL url = new URL(img);
                    //InputStream is = url.openStream();
                    //Bitmap bm = BitmapFactory.decodeStream(is);

                    Pokemon pokemon = new Pokemon(num,name,img,types,candy,cp,att,dep,hel,pro,techs);

                    pokemonAdapter.addItem(pokemon);
                    pokemonList.add(pokemon);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                cursor.close();
                new Thread(){
                    @Override
                    public void run() {
                        //UI??? ???????????? ????????? ??????????????? ???????????? ??? ??? ???????????? ???????????? ????????????.
                        //????????? ?????? ?????? ???????????? ??????
                        Message msg = phandler.obtainMessage();
                        phandler.sendMessage(msg);
                    }
                }.start();
            }
        }
    };
}


