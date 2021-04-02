package org.lsh.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements TimerStarter {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    AutoCompleteTextView hrText;
    AutoCompleteTextView minText;
    AutoCompleteTextView secText;
    EditText editText;

    RecyclerView recyclerView;
    TimerAdapter timerAdapter;

    String h,m,s,timer,time,labeling;

    @Override
    protected void onStop() {
        super.onStop();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //길이 제한할 때는 그냥 edittext maxLength를 지정해주자
        TextWatcher textWatcher = new TextWatcher() {
            //start 지점에서 시작되는 count 갯수만큼의 글자들이 after 길이만큼의 글자로 대치되려고 할 때 호출된다
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            //start 지점에서 시작되는 before 갯수만큼의 글자들이 count 갯수만큼의 글자들로 대치되었을 때 호출된다.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            //EditText의 텍스트가 변경되면 호출된다.
            @Override
            public void afterTextChanged(Editable s) {
                //61 이상이면 한자리로 61->06
                if(minText.isFocused() && minText.getText().toString().getBytes().length > 0){
                    int i = Integer.parseInt(s.toString());
                    if(i>60) {
                        String s1 = Integer.toString(i).substring(0,1);
                        minText.setText("0" + s1);
                        minText.setSelection(minText.length());
                    }
                }else if(secText.isFocused() && secText.getText().toString().getBytes().length > 0){
                    int i = Integer.parseInt(s.toString());
                    if(i>60){
                        String s1 = Integer.toString(i).substring(0,1);
                        secText.setText("0"+s1);
                        secText.setSelection(secText.length());
                    }
                }

                if(editText.isFocused() && editText.getText().toString().getBytes().length == 12){
                    Toast.makeText(getApplicationContext(),"12글자 이상 입력하실 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        hrText = findViewById(R.id.hrtext);

        minText = findViewById(R.id.mintext);
        minText.addTextChangedListener(textWatcher);

        secText = findViewById(R.id.sectext);
        secText.addTextChangedListener(textWatcher);

        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(textWatcher);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerAdapter.getItemCount() < 5){
                    labeling = editText.getText().toString();
                    h = hrText.getText().toString();
                    m = minText.getText().toString();
                    s = secText.getText().toString();
                    timer = h + m + s;
                    time = h + ":" + m + ":" + s;

                    timerAdapter.addItem(new Timer(labeling, time));
                    timerAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(getApplicationContext(),
                            "5개 이상의 Timer를 설정할 수 없습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });

        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        timerAdapter = new TimerAdapter(this);
        timerAdapter.setHasStableIds(true);
        recyclerView.setAdapter(timerAdapter);

        timerAdapter.setOnItemClickListener(new OnTimerItemClickListener() {
            @Override
            public void onItemClick(TimerAdapter.ViewHolder holder, View view, final int position) {
                show(position);
            }
        });
    }

    //서비스는 activity와 달리 여러 번 호출되어도 인스턴스가 하나다.
    //따라서 같은 기능을 하는 서비스를 여러 개 동작시키기 위해서 여러 개의 서비스를 만들었다.
    @Override
    public void startTimer(ServiceConnection connection) {
        Intent intent = new Intent(MainActivity.this,TimerService.class);
        intent.putExtra("timer",timer);
        intent.putExtra("labeling",labeling);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    public void stopTimer(ServiceConnection serviceConnection) {
        unbindService(serviceConnection);
    }

    @Override
    public void startTimer2(ServiceConnection serviceConnection) {
        Intent intent = new Intent(MainActivity.this,TimerService2.class);
        intent.putExtra("timer",timer);
        intent.putExtra("labeling",labeling);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    public void stopTimer2(ServiceConnection serviceConnection) {
        unbindService(serviceConnection);
    }

    @Override
    public void startTimer3(ServiceConnection serviceConnection) {
        Intent intent = new Intent(MainActivity.this,TimerService3.class);
        intent.putExtra("timer",timer);
        intent.putExtra("labeling",labeling);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    public void stopTimer3(ServiceConnection serviceConnection) {
        unbindService(serviceConnection);
    }

    @Override
    public void startTimer4(ServiceConnection serviceConnection) {
        Intent intent = new Intent(MainActivity.this,TimerService4.class);
        intent.putExtra("timer",timer);
        intent.putExtra("labeling",labeling);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    public void stopTimer4(ServiceConnection serviceConnection) {
        unbindService(serviceConnection);
    }

    @Override
    public void startTimer5(ServiceConnection serviceConnection) {
        Intent intent = new Intent(MainActivity.this,TimerService5.class);
        intent.putExtra("timer",timer);
        intent.putExtra("labeling",labeling);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    public void stopTimer5(ServiceConnection serviceConnection) {
        unbindService(serviceConnection);
    }

    private void show(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제");
        builder.setMessage("해당 항목을 삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timerAdapter.removeItem(position);
                timerAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
