package org.lsh.timer;

public class Timer {
    String labeling;
    String timer;
    int servicePos;

    public Timer(String labeling, String timer) {
        this.labeling = labeling;
        this.timer = timer;
        this.servicePos = -1;
    }

    public String getLabeling() {
        return labeling;
    }

    public void setLabeling(String labeling) {
        this.labeling = labeling;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public int getServicePos() { return servicePos; }

    public void setServicePos(int servicePos) { this.servicePos = servicePos; }
}
