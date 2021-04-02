package org.lsh.timer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class TimerService extends Service {
    private static final String TAG = "Service";
    private String timer;
    private String labeling;
    private boolean running;

    NotificationManager manager;

    private static String CHANNEL_ID2 = "channel2";
    private static String CHANNEL_NAME2 = "Channel2";

    public TimerService() {
    }

    IMyTimerService.Stub binder = new IMyTimerService.Stub() {
        @Override
        public String getTimer() throws RemoteException {
            return timer;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
         running = true;
        Log.d(TAG,"create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
         running = false;
        Log.d(TAG,"destroy");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        timer = intent.getStringExtra("timer");
        labeling = intent.getStringExtra("labeling");
        Log.d(TAG,timer);
        countDown(timer);
        return binder;
    }

    public void countDown(final String time) {

        long conversionTime = 0;

        // 1000 단위가 1초
        // 60000 단위가 1분
        // 60000 * 3600 = 1시간

        String getHour = time.substring(0, 2);
        String getMin = time.substring(2, 4);
        String getSecond = time.substring(4, 6);

        // "00"이 아니고, 첫번째 자리가 0 이면 제거
        if (getHour.substring(0, 1) == "0") {
            getHour = getHour.substring(1, 2);
        }

        if (getMin.substring(0, 1) == "0") {
            getMin = getMin.substring(1, 2);
        }

        if (getSecond.substring(0, 1) == "0") {
            getSecond = getSecond.substring(1, 2);
        }

        // 변환시간
        conversionTime = Long.valueOf(getHour) * 1000 * 3600 + Long.valueOf(getMin) * 60 * 1000 + Long.valueOf(getSecond) * 1000;

        // 첫번쨰 인자 : 원하는 시간 (예를들어 30초면 30 x 1000(주기))
        // 두번쨰 인자 : 주기( 1000 = 1초)
        new CountDownTimer(conversionTime, 1000) {

            // 특정 시간마다 뷰 변경
            public void onTick(long millisUntilFinished) {

                // 시간단위
                String hour = String.valueOf(millisUntilFinished / (60 * 60 * 1000));

                // 분단위
                long getMin = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000)) ;
                String min = String.valueOf(getMin / (60 * 1000)); // 몫

                // 초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000); // 나머지

                // 시간이 한자리면 0을 붙인다
                if (hour.length() == 1) {
                    hour = "0" + hour;
                }

                // 분이 한자리면 0을 붙인다
                if (min.length() == 1) {
                    min = "0" + min;
                }

                // 초가 한자리면 0을 붙인다
                if (second.length() == 1) {
                    second = "0" + second;
                }

                timer = hour + ":" + min + ":" + second;
                Log.d("service",timer);

                // ==은 주소도 비교해서 true가 안된다.
                if(timer.equals("00:10:00")){
                    showNoti2(labeling + " : 10분 남았습니다!");
                    vibrate();
                }

                if (!running){
                    cancel();
                }
            }

            // 제한시간 종료시
            public void onFinish() {

                // 변경 후
                timer = "끝!";
                showNoti2(labeling +" : 끝!");
                vibrate();

                // TODO : 타이머가 모두 종료될때 어떤 이벤트를 진행할지

            }
        }.start();
    }

    //눌러도 반응, setContentIntent를 이용해 메인 액티비티를 띄우도록 했기 때문
    private void showNoti2(String text){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel(CHANNEL_ID2) == null) {
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
            builder = new NotificationCompat.Builder(this, CHANNEL_ID2);
        }
        else {
            builder = new NotificationCompat.Builder(this,CHANNEL_ID2);
        }

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),101,intent,
                PendingIntent.FLAG_CANCEL_CURRENT);


        //알림을 클릭했을 때, 알림창이 사라지도록하는 메서드
        builder.setAutoCancel(true);
        //알림을 클릭했을 때, intent를 전달해서 MainActivity를 띄우도록 함
        builder.setContentIntent(pendingIntent);
        //스타일
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle(text);

        builder.setStyle(style);
        builder.setSmallIcon(android.R.drawable.ic_menu_view);

        Notification noti = builder.build();

        manager.notify(1, noti);
    }

    private void vibrate(){
        //진동을 위해 vibrator 객체 참조
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if(Build.VERSION.SDK_INT >= 26){
            //지속시간, 음량
            vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
        }else {
            vibrator.vibrate(1000);
        }
    }
}
