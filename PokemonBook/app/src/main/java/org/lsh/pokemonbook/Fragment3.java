package org.lsh.pokemonbook;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {
    TypeDialog typeDialog;
    Spanned htmlText1;
    Spanned htmlText2;
    Spanned htmlText3;
    Spanned htmlText4;
    String  title;
    ImageGetter imageGetter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3, container, false);

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        typeDialog = new TypeDialog(getContext());
        WindowManager.LayoutParams wm = typeDialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(typeDialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        wm.width = width;
        wm.height = height;

        imageGetter = new ImageGetter(getContext());

        Button normalBtn = v.findViewById(R.id.normalBtn);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(0);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button fightingBtn = v.findViewById(R.id.fightingBtn);
        fightingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(1);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button flyingBtn = v.findViewById(R.id.flyingBtn);
        flyingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(2);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button poisonBtn = v.findViewById(R.id.poisonBtn);
        poisonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(3);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button groundBtn = v.findViewById(R.id.groundBtn);
        groundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(4);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button rockBtn = v.findViewById(R.id.rockBtn);
        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(5);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button bugBtn = v.findViewById(R.id.bugBtn);
        bugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(6);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button ghostBtn = v.findViewById(R.id.ghostBtn);
        ghostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(7);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button ironBtn = v.findViewById(R.id.ironBtn);
        ironBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(8);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button fireBtn = v.findViewById(R.id.fireBtn);
        fireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(9);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button waterBtn = v.findViewById(R.id.waterBtn);
        waterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(10);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button plantBtn = v.findViewById(R.id.plantBtn);
        plantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(11);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button elecBtn = v.findViewById(R.id.elecBtn);
        elecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(12);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button esperBtn = v.findViewById(R.id.esperBtn);
        esperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(13);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button iceBtn = v.findViewById(R.id.iceBtn);
        iceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(14);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button dragonBtn = v.findViewById(R.id.dragonBtn);
        dragonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(15);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button darkBtn = v.findViewById(R.id.darkBtn);
        darkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(16);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        Button fairyBtn = v.findViewById(R.id.fairyBtn);
        fairyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType(17);
                typeDialog.setTextView(htmlText1, htmlText2, htmlText3, htmlText4, title);
                typeDialog.show();
            }
        });

        return v;
    }
    private void showType(int num){
        String s;
        if(num == 0){
            s = "노말";
            title = s;
            s = "없음";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"ghost\">고스트 <img src=\"iron\">강철";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ghost\">고스트";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 1){
            s = "격투";
            title = s;
            s = "<img src=\"normal\">노말 <img src=\"rock\">바위 <img src=\"iron\">강철 " +
                    "<img src=\"ice\">얼음 <img src=\"dark\">악";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"poison\">독 <img src=\"bug\">벌레 " +
                    "<img src=\"ghost\">고스트 <img src=\"esper\">에스퍼 <img src=\"fairy\">페어리";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"esper\">에스퍼 <img src=\"fairy\">페어리";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"bug\">벌레 <img src=\"dark\">악";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 2){
            s = "비행";
            title = s;
            s = "<img src=\"fighting\">격투 <img src=\"bug\">벌레 <img src=\"plant\">풀";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"electric\">전기 <img src=\"iron\">강철";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"electric\">전기 <img src=\"ice\">얼음";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"bug\">벌레 <img src=\"plant\">풀 " +
                    "<img src=\"ground\">땅";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 3){
            s = "독";
            title = s;
            s = "<img src=\"poison\">독 <img src=\"ground\">땅 <img src=\"rock\">바위 " +
                    "<img src=\"ghost\">고스트 <img src=\"iron\">강철";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"plant\">풀 <img src=\"fairy\">페어리";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"poison\">독 <img src=\"plant\">풀 " +
                    "<img src=\"fairy\">페어리";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ground\">땅 <img src=\"esper\">에스퍼";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 4){
            s = "땅";
            title = s;
            s = "<img src=\"poison\">독 <img src=\"rock\">바위 <img src=\"iron\">강철 " +
                    "<img src=\"fire\">불꽃 <img src=\"electric\">전기";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"bug\">벌레 <img src=\"plant\">풀";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"water\">물 <img src=\"plant\">풀 <img src=\"ice\">얼음";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"poison\">독 <img src=\"rock\">바위 <img src=\"electric\">전기";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 5){
            s = "바위";
            title = s;
            s = "<img src=\"flying\">비행 <img src=\"bug\">벌레 <img src=\"fire\">불꽃 " +
                    "<img src=\"ice\">얼음";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"ground\">땅 <img src=\"iron\">강철";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"ground\">땅 <img src=\"iron\">강철 " +
                    "<img src=\"water\">물 <img src=\"plant\">풀";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"normal\">노말 <img src=\"flying\">비행 <img src=\"poison\">독 " +
                    "<img src=\"fire\">불꽃";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 6){
            s = "벌레";
            title = s;
            s = "<img src=\"plant\">풀 <img src=\"esper\">에스퍼 <img src=\"dark\">악";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"flying\">비행 <img src=\"poison\">독 " +
                    "<img src=\"ghost\">고스트 <img src=\"iron\">강철 <img src=\"fire\">불꽃 " +
                    "<img src=\"fairy\">페어리";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"rock\">바위 <img src=\"fire\">불꽃";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"ground\">땅 <img src=\"plant\">풀";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 7){
            s = "고스트";
            title = s;
            s = "<img src=\"ghost\">고스트 <img src=\"esper\">에스퍼";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"normal\">노말 <img src=\"dark\">악";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ghost\">고스트 <img src=\"dark\">악";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"normal\">노말 <img src=\"fighting\">격투 <img src=\"poison\">독 " +
                    "<img src=\"bug\">벌레";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 8){
            s = "강철";
            title = s;
            s = "<img src=\"rock\">바위 <img src=\"ice\">얼음";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"iron\">강철 <img src=\"fire\">불꽃 <img src=\"water\">물 " +
                    "<img src=\"electric\">전기";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"ground\">땅 <img src=\"fire\">불꽃";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"normal\">노말 <img src=\"flying\">비행 <img src=\"poison\">독 " +
                    "<img src=\"rock\">바위 <img src=\"bug\">벌레 <img src=\"iron\">강철 " +
                    "<img src=\"plant\">풀 <img src=\"esper\">에스퍼 <img src=\"ice\">얼음 " +
                    "<img src=\"dragon\">드래곤 <img src=\"fairy\">페어리";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 9){
            s = "불꽃";
            title = s;
            s = "<img src=\"bug\">벌레 <img src=\"iron\">강철 <img src=\"plant\">풀" +
                    "<img src=\"ice\">얼음";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"fire\">불꽃 <img src=\"water\">물 " +
                    "<img src=\"dragon\">드래곤";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ground\">땅 <img src=\"rock\">바위 <img src=\"water\">물";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"bug\">벌레 <img src=\"iron\">강철 <img src=\"fire\">불꽃" +
                    "<img src=\"plant\">풀 <img src=\"ice\">얼음 <img src=\"fairy\">페어리";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 10){
            s = "물";
            title = s;
            s = "<img src=\"ground\">땅 <img src=\"rock\">바위 <img src=\"fire\">불꽃";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"water\">물 <img src=\"plant\">풀 <img src=\"dragon\">드래곤";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"plant\">풀 <img src=\"electric\">전기";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"iron\">강철 <img src=\"fire\">불꽃 <img src=\"water\">물" +
                    "<img src=\"ice\">얼음";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 11){
            s = "풀";
            title = s;
            s = "<img src=\"ground\">땅 <img src=\"rock\">바위 <img src=\"water\">물";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"poison\">독 <img src=\"bug\">벌레 " +
                    "<img src=\"iron\">강철 <img src=\"fire\">불꽃 <img src=\"plant\">풀 " +
                    "<img src=\"dragon\">드래곤";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"poison\">독 <img src=\"bug\">벌레 " +
                    "<img src=\"fire\">불꽃 <img src=\"ice\">얼음";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ground\">땅 <img src=\"water\">물 <img src=\"plant\">풀 " +
                    "<img src=\"electric\">전기";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 12){
            s = "전기";
            title = s;
            s = "<img src=\"flying\">비행 <img src=\"water\">물";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"rock\">바위 <img src=\"plant\">풀 <img src=\"electric\">전기 " +
                    "<img src=\"dragon\">드래곤";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ground\">땅";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"flying\">비행 <img src=\"iron\">강철 <img src=\"electric\">전기";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 13){
            s = "에스퍼";
            title = s;
            s = "<img src=\"fighting\">격투 <img src=\"poison\">독";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"iron\">강철 <img src=\"esper\">에스퍼 <img src=\"dark\">악";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"bug\">벌레 <img src=\"ghost\">고스트 <img src=\"dark\">악";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"esper\">에스퍼";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 14){
            s = "얼음";
            title = s;
            s = "<img src=\"flying\">비행 <img src=\"ground\">땅 <img src=\"plant\">풀 " +
                    "<img src=\"dragon\">드래곤";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"iron\">강철 <img src=\"fire\">불꽃 <img src=\"water\">물 " +
                    "<img src=\"ice\">얼음";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"rock\">바위 <img src=\"iron\">강철 " +
                    "<img src=\"fire\">불꽃";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ice\">얼음";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 15){
            s = "드래곤";
            title = s;
            s = "<img src=\"dragon\">드래곤";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"iron\">강철 <img src=\"fairy\">페어리";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ice\">얼음 <img src=\"dragon\">드래곤 <img src=\"fairy\">페어리";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fire\">불꽃 <img src=\"water\">물 <img src=\"plant\">풀 " +
                    "<img src=\"electric\">전기";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 16){
            s = "악";
            title = s;
            s = "<img src=\"ghost\">고스트 <img src=\"esper\">에스퍼";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"dark\">악 <img src=\"fairy\">페어리";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"bug\">벌레 <img src=\"fairy\">페어리";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"ghost\">고스트 <img src=\"esper\">에스퍼 <img src=\"dark\">악";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        } else if(num == 17){
            s = "페어리";
            title = s;
            s = "<img src=\"fighting\">격투 <img src=\"dragon\">드래곤 <img src=\"dark\">악";
            htmlText1 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"poison\">독 <img src=\"iron\">강철 <img src=\"fire\">불꽃";
            htmlText2 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"poison\">독";
            htmlText3 = Html.fromHtml(s , imageGetter, null);
            s = "<img src=\"fighting\">격투 <img src=\"bug\">벌레 <img src=\"dragon\">드래곤 " +
                    "<img src=\"dark\">악";
            htmlText4 = Html.fromHtml(s , imageGetter, null);
        }
    }
}
