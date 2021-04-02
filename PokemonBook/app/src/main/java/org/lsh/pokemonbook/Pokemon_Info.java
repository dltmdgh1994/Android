package org.lsh.pokemonbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pokemon_Info extends AppCompatActivity {
    private String TAG = "Poke_Info";
    ImageView imageView;
    ArrayList<Skill> skills;
    RecyclerView recycler1;
    RecyclerView recycler2;

    double normal = 1;
    double poison = 1;
    double bug = 1;
    double fire = 1;
    double elec = 1;
    double dragon = 1;
    double fight = 1;
    double ground = 1;
    double ghost = 1;
    double water = 1;
    double esper = 1;
    double dark = 1;
    double fly = 1;
    double rock = 1;
    double iron = 1;
    double plant = 1;
    double ice = 1;
    double fairy = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);

        ImageGetter imageGetter = new ImageGetter(this);

        Intent intent = getIntent();
        int img = intent.getIntExtra("img",0);
        String name = intent.getStringExtra("name");
        String[] type = intent.getStringArrayExtra("type");
        int candy = intent.getIntExtra("candy",0);
        int cp = intent.getIntExtra("cp",0);
        int att = intent.getIntExtra("att",0);
        int dep = intent.getIntExtra("dep",0);
        int hel = intent.getIntExtra("hel",0);
        String pro = intent.getStringExtra("pro");
        skills = (ArrayList<Skill>) intent.getSerializableExtra("skills");

        imageView = findViewById(R.id.pokemonInfoImg);
        imageView.setImageResource(img);

        TextView Name = findViewById(R.id.pokemonInfoName2);
        Name.setText(name);
        TextView Type = findViewById(R.id.pokemonInfoType2);
        String types = "";
        for(int i = 0; i < type.length; i++){
            String s = changeString(type[i]);
            types += "<img src=\""+s+"\">"+type[i]+" ";
        }
        Spanned htmlText = Html.fromHtml(types , imageGetter, null);
        Type.setText(htmlText);
        TextView Candy = findViewById(R.id.pokemonInfoCandy2);
        Candy.setText(Integer.toString(candy));
        TextView Cp = findViewById(R.id.pokemonInfoCp2);
        Cp.setText(Integer.toString(cp));
        TextView Att = findViewById(R.id.pokemonInfoAtt2);
        Att.setText(Integer.toString(att));
        TextView Dep = findViewById(R.id.pokemonInfoDep2);
        Dep.setText(Integer.toString(dep));
        TextView Hel = findViewById(R.id.pokemonInfoHel2);
        Hel.setText(Integer.toString(hel));
        TextView Pro = findViewById(R.id.pokemonInfoPro2);
        Pro.setText(pro);


        ArrayList<Skill> normalSkill = new ArrayList<>();
        ArrayList<Skill> specialSkill = new ArrayList<>();
        for(Skill skill: skills){
            if(skill.getSkill_sort().equals("통상 공격")){
                normalSkill.add(skill);
            }else{
                specialSkill.add(skill);
            }
        }

        SkillInfoAdapter skillInfoAdapter1 = new SkillInfoAdapter(normalSkill);
        SkillInfoAdapter skillInfoAdapter2 = new SkillInfoAdapter(specialSkill);

        recycler1 = findViewById(R.id.pokemonInfoRecycler1);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 1);
        recycler1.setLayoutManager(layoutManager1);
        recycler1.setAdapter(skillInfoAdapter1);

        recycler2 = findViewById(R.id.pokemonInfoRecycler2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 1);
        recycler2.setLayoutManager(layoutManager2);
        recycler2.setAdapter(skillInfoAdapter2);

        //타입 상성표 계산
        calcType(type);
        TextView normal2 = findViewById(R.id.normalTextView2);
        normal2.setText(calcDecimal(normal));
        TextView poison2 = findViewById(R.id.PoisonTextView2);
        poison2.setText(calcDecimal(poison));
        TextView bug2 = findViewById(R.id.bugTextView2);
        bug2.setText(calcDecimal(bug));
        TextView fire2 = findViewById(R.id.fireTextView2);
        fire2.setText(calcDecimal(fire));
        TextView elec2 = findViewById(R.id.elecTextView2);
        elec2.setText(calcDecimal(elec));
        TextView dragon2 = findViewById(R.id.dragonTextView2);
        dragon2.setText(calcDecimal(dragon));
        TextView fight2 = findViewById(R.id.fightTextView2);
        fight2.setText(calcDecimal(fight));
        TextView ground2 = findViewById(R.id.groundTextView2);
        ground2.setText(calcDecimal(ground));
        TextView ghost2 = findViewById(R.id.ghostTextView2);
        ghost2.setText(calcDecimal(ghost));
        TextView water2 = findViewById(R.id.waterTextView2);
        water2.setText(calcDecimal(water));
        TextView esper2 = findViewById(R.id.esperTextView2);
        esper2.setText(calcDecimal(esper));
        TextView dark2 = findViewById(R.id.darkTextView2);
        dark2.setText(calcDecimal(dark));
        TextView fly2 = findViewById(R.id.flyTextView2);
        fly2.setText(calcDecimal(fly));
        TextView rock2 = findViewById(R.id.rockTextView2);
        rock2.setText(calcDecimal(rock));
        TextView iron2 = findViewById(R.id.ironTextView2);
        iron2.setText(calcDecimal(iron));
        TextView plant2 = findViewById(R.id.plantTextView2);
        plant2.setText(calcDecimal(plant));
        TextView ice2 = findViewById(R.id.iceTextView2);
        ice2.setText(calcDecimal(ice));
        TextView fairy2 = findViewById(R.id.fairyTextView2);
        fairy2.setText(calcDecimal(fairy));

        Button btn = findViewById(R.id.pokemonInfoBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

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

    public String changeString(String type){
        if(type.equals("불꽃")){
            return "fire";
        }else if(type.equals("벌레")){
            return "bug";
        }else if(type.equals("노말")){
            return "normal";
        }else if(type.equals("격투")){
            return "fighting";
        }else if(type.equals("비행")){
            return "flying";
        }else if(type.equals("독")){
            return "poison";
        }else if(type.equals("땅")){
            return "ground";
        }else if(type.equals("바위")){
            return "rock";
        }else if(type.equals("고스트")){
            return "ghost";
        }else if(type.equals("강철")){
            return "iron";
        }else if(type.equals("물")){
            return "water";
        }else if(type.equals("풀")){
            return "plant";
        }else if(type.equals("전기")){
            return "electric";
        }else if(type.equals("에스퍼")){
            return "esper";
        }else if(type.equals("얼음")){
            return "ice";
        }else if(type.equals("드래곤")){
            return "dragon";
        }else if(type.equals("악")){
            return "dark";
        }else if(type.equals("페어리")){
            return "fairy";
        }
        return null;
    }

    private void calcType(String[] type){
        double strong = 1.6;
        double weak = 0.625;
        for(int i = 0; i < type.length; i++){
            if(type[i].equals("불꽃")){
                ground *= strong; rock *= strong; bug *= weak;
                iron *= weak; fire *= weak; water *= strong;
                plant *= weak; ice *= weak; fairy *= weak;
            }else if(type[i].equals("벌레")){
                fight *= weak; fly *= strong; ground *= weak;
                rock *= strong; fire *= strong; plant *= weak;
            }else if(type[i].equals("노말")){
                fight *= strong; ghost *= weak;
            }else if(type[i].equals("격투")){
                fly *= strong; rock *= weak; bug *= weak;
                esper *= strong; dark *= weak; fairy *= strong;
            }else if(type[i].equals("비행")){
                fight *= weak; ground *= weak; rock *= strong;
                bug *= weak; plant *= weak; elec *= strong;
                ice *= strong;
            }else if(type[i].equals("독")){
                fight *= weak; poison *= weak; ground *= strong;
                bug *= weak; plant *= weak; esper *= strong;
                fairy *= weak;
            }else if(type[i].equals("땅")){
                poison *= weak; rock *= weak; water *= strong;
                plant *= strong; elec *= weak; ice *= strong;
            }else if(type[i].equals("바위")){
                normal *= weak; fight *= strong; fly *= weak;
                poison *= weak; ground *= strong; iron *= strong;
                fire *= weak; water *= strong; plant *= strong;
            }else if(type[i].equals("고스트")){
                normal *= weak; fight *= weak; poison *= weak;
                bug *= weak; ghost *= strong; dark *= strong;
            }else if(type[i].equals("강철")){
                normal *= weak; fight *= strong; fly *= weak;
                poison *= weak; ground *= strong; rock *= weak;
                bug *= weak; iron *= weak; fire *= strong;
                plant *= weak; esper *= weak; ice *= weak;
                dragon *= weak; fairy *= weak;
            }else if(type[i].equals("물")){
                iron *= weak; fire *= weak; water *= weak;
                plant *= strong; elec *= strong; ice *= weak;
            }else if(type[i].equals("풀")){
                fly *= strong; poison *= strong; ground *= weak;
                bug *= strong; fire *= strong; water *= weak;
                plant *= weak; elec *= weak; ice *= strong;
            }else if(type[i].equals("전기")){
                fly *= weak; ground *= strong; iron *= weak;
                elec *= weak;
            }else if(type[i].equals("에스퍼")){
                fight *= weak; bug *= strong; ghost *= strong;
                esper *= weak; dark *= strong;
            }else if(type[i].equals("얼음")){
                fight *= strong; rock *= strong; iron *= strong;
                fire *= strong; ice *= weak;
            }else if(type[i].equals("드래곤")){
                fire *= weak; water *= weak; plant *= weak;
                elec *= weak; ice *= strong; dragon *= strong;
                fairy *= strong;
            }else if(type[i].equals("악")){
                fight *= strong; bug *= strong; ghost *= weak;
                esper *= weak; dark *= weak; fairy *= strong;
            }else if(type[i].equals("페어리")){
                fight *= weak; poison *= strong; bug *= weak;
                dragon *= weak; dark *= weak;
            }
        }
    }

    private String calcDecimal(Double type){
        if(type == 1){
            return "-";
        }else{
            return String.format("%.3f", type);
        }
    }
}
