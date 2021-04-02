package org.lsh.timer;


import android.content.ServiceConnection;

public interface TimerStarter {
    void startTimer(ServiceConnection serviceConnection);
    void stopTimer(ServiceConnection serviceConnection);
    void startTimer2(ServiceConnection serviceConnection);
    void stopTimer2(ServiceConnection serviceConnection);
    void startTimer3(ServiceConnection serviceConnection);
    void stopTimer3(ServiceConnection serviceConnection);
    void startTimer4(ServiceConnection serviceConnection);
    void stopTimer4(ServiceConnection serviceConnection);
    void startTimer5(ServiceConnection serviceConnection);
    void stopTimer5(ServiceConnection serviceConnection);
}
