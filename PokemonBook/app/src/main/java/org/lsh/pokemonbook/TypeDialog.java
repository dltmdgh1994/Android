package org.lsh.pokemonbook;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TypeDialog extends Dialog {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView titleText;
    ImageView imageView;

    Button btn;
    public TypeDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.type_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        btn = findViewById(R.id.typeDialogBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();   //다이얼로그를 닫는 메소드입니다.
            }
        });

        titleText = findViewById(R.id.typeDialogTitle);
        textView1 = findViewById(R.id.typeDialogText1);
        textView2 = findViewById(R.id.typeDialogText2);
        textView3 = findViewById(R.id.typeDialogText3);
        textView4 = findViewById(R.id.typeDialogText4);
        imageView = findViewById(R.id.typeDialogImg);
    }

    public void setTextView(Spanned s1, Spanned s2, Spanned s3, Spanned s4, String title){
        titleText.setText(title);
        textView1.setText(s1);
        textView2.setText(s2);
        textView3.setText(s3);
        textView4.setText(s4);
        setImg(title);
    }

    private void setImg(String s){
        if(s.equals("노말")){
            imageView.setImageResource(R.drawable.normal);
        }else if(s.equals("격투")){
            imageView.setImageResource(R.drawable.fighting);
        }else if(s.equals("비행")){
            imageView.setImageResource(R.drawable.flying);
        }else if(s.equals("독")){
            imageView.setImageResource(R.drawable.poison);
        }else if(s.equals("땅")){
            imageView.setImageResource(R.drawable.ground);
        }else if(s.equals("바위")){
            imageView.setImageResource(R.drawable.rock);
        }else if(s.equals("벌레")){
            imageView.setImageResource(R.drawable.bug);
        }else if(s.equals("고스트")){
            imageView.setImageResource(R.drawable.ghost);
        }else if(s.equals("강철")){
            imageView.setImageResource(R.drawable.iron);
        }else if(s.equals("불꽃")){
            imageView.setImageResource(R.drawable.fire);
        }else if(s.equals("물")){
            imageView.setImageResource(R.drawable.water);
        }else if(s.equals("풀")){
            imageView.setImageResource(R.drawable.plant);
        }else if(s.equals("전기")){
            imageView.setImageResource(R.drawable.electric);
        }else if(s.equals("에스퍼")){
            imageView.setImageResource(R.drawable.esper);
        }else if(s.equals("얼음")){
            imageView.setImageResource(R.drawable.ice);
        }else if(s.equals("드래곤")){
            imageView.setImageResource(R.drawable.dragon);
        }else if(s.equals("악")){
            imageView.setImageResource(R.drawable.dark);
        }else if(s.equals("페어리")){
            imageView.setImageResource(R.drawable.fairy);
        }
    }
}
