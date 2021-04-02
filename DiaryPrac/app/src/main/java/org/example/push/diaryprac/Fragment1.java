package org.example.push.diaryprac;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import lib.kingja.switchbutton.SwitchMultiButton;

public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        //참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위해 instanceof 연산자를 사용
        //MainActivity의 context가 OnTabItemSelectedListener을 상속하고 있기 때문
        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1,container,false);
        initUI(rootView);
        loadNoteListData();
        return rootView;
    }

    private void initUI(ViewGroup rootView){
        Button todayWriteBtn = rootView.findViewById(R.id.todayWriteBtn);
        todayWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchMultiButton = rootView.findViewById(R.id.switchBtn);
        switchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(),tabText,Toast.LENGTH_LONG).show();

                noteAdapter.switchLayout(position);
                noteAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        noteAdapter = new NoteAdapter();
        noteAdapter.addItem(new Note(0, "0", "강남구 삼성동", "", "","오늘 너무 행복해!", "0", "capture1.jpg", "2월 10일"));
        noteAdapter.addItem(new Note(1, "1", "강남구 삼성동", "", "","친구와 재미있게 놀았어", "0", "capture1.jpg", "2월 11일"));
        noteAdapter.addItem(new Note(2, "0", "강남구 역삼동", "", "","집에 왔는데 너무 피곤해 ㅠㅠ", "0", null, "2월 13일"));

        recyclerView.setAdapter(noteAdapter);

        //아이템을 클릭했을 때
        noteAdapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = noteAdapter.getItem(position);
                Toast.makeText(getContext(),"아이템 선택됨 : " + item.getContents(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public int loadNoteListData(){
        AppConstants.println("LoadNoteListData called");

        String sql = "select _id, WEATHER, ADDRESS, LOCATION_X, LOCATION_Y, CONTENTS, MOOD, PICTURE, CREATE_DATE, MODIFY_DATE from " + NoteDatabase.TABLE_NOTE + " order by CREATE_DATE desc";

        int recordCnt = -1;
        NoteDatabase database = NoteDatabase.getInstance(context);
        if(database != null){
            database.open();
            Cursor outCursor = database.rawQuery(sql);

            recordCnt = outCursor.getCount();
            AppConstants.println("record Cnt : " + recordCnt + "\n");

            ArrayList<Note> items = new ArrayList<>();

            for(int i = 0; i < recordCnt; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String weather = outCursor.getString(1);
                String address = outCursor.getString(2);
                String locationX = outCursor.getString(3);
                String locationY = outCursor.getString(4);
                String contents = outCursor.getString(5);
                String mood = outCursor.getString(6);
                String picture = outCursor.getString(7);
                String dateStr = outCursor.getString(8);
                String createDateStr = null;

                if(dateStr != null && dateStr.length() > 10){
                    try {
                        Date inDate = AppConstants.dateFormat4.parse(dateStr);
                        createDateStr = AppConstants.dateFormat3.format(inDate);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    createDateStr = "";
                }

                AppConstants.println("#" + i + " -> " + _id + ", " + weather + ", " +
                        address + ", " + locationX + ", " + locationY + ", " + contents + ", " +
                        mood + ", " + picture + ", " + createDateStr);

                items.add(new Note(_id, weather, address, locationX, locationY, contents, mood, picture, createDateStr));
            }

            outCursor.close();
            database.close();

            noteAdapter.setItems(items);
            noteAdapter.notifyDataSetChanged();
        }
        return recordCnt;
    }
}
