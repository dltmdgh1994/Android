package org.lsh.timer;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.ViewHolder>
                            implements OnTimerItemClickListener{
    ArrayList<Timer> items = new ArrayList<>();
    OnTimerItemClickListener listener;

    private IMyTimerService binder;
    private IMyTimerService2 binder2;
    private IMyTimerService3 binder3;
    private IMyTimerService4 binder4;
    private IMyTimerService5 binder5;

    //각 쓰레드 내에서 쓰레드가 실행중인지 아닌지를 나타내는 변수
    boolean running = false;
    boolean running2 = false;
    boolean running3 = false;
    boolean running4 = false;
    boolean running5 = false;

    //서비스와 연결 여부를 나타내는 변수
    boolean r1 = false;
    boolean r2 = false;
    boolean r3 = false;
    boolean r4 = false;
    boolean r5 = false;

    ComponentName componentName;
    ComponentName componentName2;
    ComponentName componentName3;
    ComponentName componentName4;
    ComponentName componentName5;

    private ServiceConnection connection = new ServiceConnection() {
        @Override // 서비스와 연결되었을 때 호출되는 메서드
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IMyTimerService.Stub.asInterface(service);
            componentName = name;
        }

        @Override // 서비스와 연결이 끊기거나 종료되었을 때
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
            componentName = null;
        }
    };

    private ServiceConnection connection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder2 = IMyTimerService2.Stub.asInterface(service);
            componentName2 = name;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder2 = null;
            componentName2 = null;
        }
    };

    private ServiceConnection connection3 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder3 = IMyTimerService3.Stub.asInterface(service);
            componentName3 = name;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder3 = null;
            componentName3 = null;
        }
    };

    private ServiceConnection connection4 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder4 = IMyTimerService4.Stub.asInterface(service);
            componentName4 = name;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder4 = null;
            componentName4 = null;
        }
    };

    private ServiceConnection connection5 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder5 = IMyTimerService5.Stub.asInterface(service);
            componentName5 = name;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder5 = null;
            componentName5 = null;
        }
    };

    private TimerStarter starter;
    public TimerAdapter(TimerStarter timerStarter){
        starter = timerStarter;
    }


    public void setOnItemClickListener(OnTimerItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    //getItemCount -> onCreateViewHolder -> onBindViewHolder 순으로 호출(처음 생성할 때)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("vh","create");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.timerlayout, parent, false);
        ViewHolder holder = new ViewHolder(itemView,starter,this);

        return holder;
    }

    //삭제하더라도 다시 접근할 때는 create를 건너뛰고 바로 bind로 접근한다.
    //리싸이클러뷰에서 안보이는 아이템뷰를 다시 등록하기도 한다.
    //처음 서비스가 등록될 때는 servicePos가 -1로 초기화 된다.
    //이후 아이템이 삭제될 때(진짜 서비스와 연결이 끊길 떄)까지 servicePos는 고유 값을 가진다.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d("vh","bind");
        final Timer item = items.get(position);
        int servicePos = item.getServicePos();

        if(!r1 && servicePos == -1){
            starter.startTimer(connection);
            holder.setItem(item);
            item.setServicePos(0);
            running = true; r1 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s0 = binder.getTimer();
                                    item.setTimer(s0);
                                    holder.timerText.setText(s0);
                                    if(s0 == "끝!"){ running = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        } else if(!r2 && servicePos == -1){
            starter.startTimer2(connection2);
            holder.setItem(item);
            item.setServicePos(1);
            running2 = true; r2 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s1 = binder2.getTimer();
                                    item.setTimer(s1);
                                    holder.timerText.setText(s1);
                                    if(s1 == "끝!"){ running2 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running2){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        } else if(!r3 && servicePos == -1){
            starter.startTimer3(connection3);
            holder.setItem(item);
            item.setServicePos(2);
            running3 = true; r3 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s2 = binder3.getTimer();
                                    item.setTimer(s2);
                                    holder.timerText.setText(s2);
                                    if(s2 == "끝!"){ running3 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running3){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        } else if(!r4 && servicePos == -1){
            starter.startTimer4(connection4);
            holder.setItem(item);
            item.setServicePos(3);
            running4 = true; r4 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s3 = binder4.getTimer();
                                    item.setTimer(s3);
                                    holder.timerText.setText(s3);
                                    if(s3 == "끝!"){ running4 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running4){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        } else if(!r5 && servicePos == -1){
            starter.startTimer5(connection5);
            holder.setItem(item);
            item.setServicePos(4);
            running5 = true; r5 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s4 = binder5.getTimer();
                                    item.setTimer(s4);
                                    holder.timerText.setText(s4);
                                    if(s4 == "끝!"){ running5 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running5){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        } else if(r1 && servicePos == 0){
            running = false;
            holder.setItem(item);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            running = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s0 = binder.getTimer();
                                    item.setTimer(s0);
                                    holder.timerText.setText(s0);
                                    if(s0 == "끝!"){ running = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        }else if(r2 && servicePos == 1){
            running2 = false;
            item.setServicePos(1);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            running2 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s1 = binder2.getTimer();
                                    item.setTimer(s1);
                                    holder.timerText.setText(s1);
                                    if(s1 == "끝!"){ running2 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running2){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        }else if(r3 && servicePos == 2){
            running3 = false;
            holder.setItem(item);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            running3 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s2 = binder3.getTimer();
                                    item.setTimer(s2);
                                    holder.timerText.setText(s2);
                                    if(s2 == "끝!"){ running3 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running3){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        }else if(r4 && servicePos == 3){
            running4 = false;
            holder.setItem(item);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            running4 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s3 = binder4.getTimer();
                                    item.setTimer(s3);
                                    holder.timerText.setText(s3);
                                    if(s3 == "끝!"){ running4 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running4){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        }else if(r5 && servicePos == 4){
            running5 = false;
            holder.setItem(item);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            running5 = true;

            new Thread(new Runnable() {
                private Handler handler = new Handler();

                @Override
                public void run() {
                    while (true){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s4 = binder5.getTimer();
                                    item.setTimer(s4);
                                    holder.timerText.setText(s4);
                                    if(s4 == "끝!"){ running5 = false; }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!running5){
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //뷰홀더는 뷰 전체를 담는게 아니라 하나의 뷰를 담는다. 즉, 리싸이클러뷰는 여러 개의 뷰홀더를 생성한다.
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView labelingText;
        TextView timerText;

        private TimerStarter starter;

        public ViewHolder(@NonNull View itemView, TimerStarter timerStarter, final OnTimerItemClickListener listener) {
            super(itemView);
            Log.d("vh","first");

            labelingText = itemView.findViewById(R.id.labelingText);
            timerText = itemView.findViewById(R.id.timerText);

            starter = timerStarter;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,v,position);
                    }
                }
            });
        }

        public void setItem(Timer item){
            labelingText.setText(item.getLabeling());
            timerText.setText(item.getTimer());
        }
    }

    public void addItem(Timer item){ items.add(item);}

    //아이템을 삭제할 때 서비스를 종료해주고, running과 r를 false로 초기화한다.
    public void removeItem(int position){
        int servicePos = items.get(position).getServicePos();
        Log.d("remove",""+servicePos);
        items.remove(position);
        notifyItemRemoved(position);

        if(r1 && servicePos == 0){
            starter.stopTimer(connection);
            //서비스가 종료되면 connection도 끊어줘야 한다.
            connection.onServiceDisconnected(componentName);
            if(running == true){
                running = false;
            }
            r1 = false;
        }else if(r2 && servicePos == 1){
            starter.stopTimer2(connection2);
            connection2.onServiceDisconnected(componentName2);
            if(running2 == true){
                running2 = false;
            }
            r2 = false;
        }else if(r3 && servicePos == 2){
            starter.stopTimer3(connection3);
            connection3.onServiceDisconnected(componentName3);
            if(running3 == true){
                running3 = false;
            }
            r3 = false;
        }else if(r4 && servicePos == 3){
            starter.stopTimer4(connection4);
            connection4.onServiceDisconnected(componentName4);
            if(running4 == true){
                running4 = false;
            }
            r4 = false;
        }else if(r5 && servicePos == 4){
            starter.stopTimer5(connection5);
            connection5.onServiceDisconnected(componentName5);
            if(running5 == true){
                running5 = false;
            }
            r5 = false;
        }
    }
}
