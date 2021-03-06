package org.lsh.pokemonbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Skill_Info extends AppCompatActivity {
    private String TAG = "Skill_Info";
    ArrayList<Pokemon> pokemons;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_info);

        ImageGetter imageGetter = new ImageGetter(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String dps = intent.getStringExtra("dps");
        String type = intent.getStringExtra("type");
        String sort = intent.getStringExtra("sort");
        String power = intent.getStringExtra("power");
        String eps = intent.getStringExtra("eps");
        String time = intent.getStringExtra("time");
        pokemons = (ArrayList<Pokemon>) intent.getSerializableExtra("pokemons");

        TextView Name = findViewById(R.id.SkillInfoName);
        Name.setText(name);
        TextView Dps = findViewById(R.id.SkillInfoDps);
        Dps.setText(dps);

        TextView TypeText = findViewById(R.id.SkillInfoType);
        String s = changeString(type);
        String types = "<img src=\""+s+"\">"+type;
        Spanned htmlText = Html.fromHtml(types , imageGetter, null);
        TypeText.setText(htmlText);

        TextView Sort = findViewById(R.id.SkillInfoSort);
        Sort.setText(sort);
        TextView Power = findViewById(R.id.SkillInfoPower);
        Power.setText(power);
        TextView Eps = findViewById(R.id.SkillInfoEps);
        Eps.setText(eps);
        TextView Time = findViewById(R.id.SkillInfoTime);
        Time.setText(time);

        PokemonInfoAdapter pokemonInfoAdapter = new PokemonInfoAdapter(pokemons);
        recycler = findViewById(R.id.skillInfoRecycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(pokemonInfoAdapter);

        Button btn= findViewById(R.id.skillInfoBtn);
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
        if(type.equals("??????")){
            return "fire";
        }else if(type.equals("??????")){
            return "bug";
        }else if(type.equals("??????")){
            return "normal";
        }else if(type.equals("??????")){
            return "fighting";
        }else if(type.equals("??????")){
            return "flying";
        }else if(type.equals("???")){
            return "poison";
        }else if(type.equals("???")){
            return "ground";
        }else if(type.equals("??????")){
            return "rock";
        }else if(type.equals("?????????")){
            return "ghost";
        }else if(type.equals("??????")){
            return "iron";
        }else if(type.equals("???")){
            return "water";
        }else if(type.equals("???")){
            return "plant";
        }else if(type.equals("??????")){
            return "electric";
        }else if(type.equals("?????????")){
            return "esper";
        }else if(type.equals("??????")){
            return "ice";
        }else if(type.equals("?????????")){
            return "dragon";
        }else if(type.equals("???")){
            return "dark";
        }else if(type.equals("?????????")){
            return "fairy";
        }
        return null;
    }

}
