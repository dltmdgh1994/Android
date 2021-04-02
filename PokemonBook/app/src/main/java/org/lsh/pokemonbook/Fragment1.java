package org.lsh.pokemonbook;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Fragment1 extends Fragment implements TextWatcher {
    RecyclerView recycler;
    PokemonAdapter pokemonAdapter;
    EditText editText;

    ArrayList<Pokemon> pokemonList;
    ArrayList<Skill> skillList;

    String[] items = {"전체","노말","격투","비행","독","땅","바위","벌레","고스트","강철",
                        "불꽃","물","풀","전기","에스퍼","얼음","드래곤","악","페어리"};
    Button numBtn;
    Button cpBtn;
    Button attBtn;
    Button depBtn;
    Button helBtn;

    int sortNum = 1; int sortCp = 0; int sortAtt = 0; int sortDep = 0; int sortHel = 0;

    public Fragment1(PokemonAdapter pokemonAdapter) {
        this.pokemonAdapter = pokemonAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container, false);

        recycler = v.findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(pokemonAdapter);

        editText = v.findViewById(R.id.pokemonEditText);
        editText.addTextChangedListener(this);

        Spinner spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, items
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortNum = 1; sortCp = 0; sortAtt = 0; sortDep = 0; sortHel = 0;
                changeBtn(sortNum,numBtn);
                changeBtn(sortCp,cpBtn);
                changeBtn(sortAtt,attBtn);
                changeBtn(sortDep,depBtn);
                changeBtn(sortHel,helBtn);
                pokemonAdapter.show(position);
                pokemonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        numBtn = v.findViewById(R.id.numBtn);
        numBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortCp != 0){
                    sortCp = 0;
                    changeBtn(sortCp,cpBtn);
                }
                if(sortAtt != 0){
                    sortAtt = 0;
                    changeBtn(sortAtt,attBtn);
                }
                if(sortDep != 0){
                    sortDep = 0;
                    changeBtn(sortDep,depBtn);
                }
                if(sortHel != 0){
                    sortHel = 0;
                    changeBtn(sortHel,helBtn);
                }
                if(sortNum == 1){
                    sortNum = -1;
                    pokemonAdapter.sortNumDec();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortNum, numBtn);
                }else{
                    sortNum = 1;
                    pokemonAdapter.sortNumAsc();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortNum, numBtn);
                }
            }
        });

        cpBtn = v.findViewById(R.id.cpBtn);
        cpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortNum != 0){
                    sortNum = 0;
                    changeBtn(sortNum,numBtn);
                }
                if(sortAtt != 0){
                    sortAtt = 0;
                    changeBtn(sortAtt,attBtn);
                }
                if(sortDep != 0){
                    sortDep = 0;
                    changeBtn(sortDep,depBtn);
                }
                if(sortHel != 0){
                    sortHel = 0;
                    changeBtn(sortHel,helBtn);
                }
                if(sortCp == 1){
                    sortCp = -1;
                    pokemonAdapter.sortCpDec();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortCp, cpBtn);
                }else{
                    sortCp = 1;
                    pokemonAdapter.sortCpAsc();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortCp, cpBtn);
                }
            }
        });

        attBtn = v.findViewById(R.id.attBtn);
        attBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortNum != 0){
                    sortNum = 0;
                    changeBtn(sortNum,numBtn);
                }
                if(sortCp != 0){
                    sortCp = 0;
                    changeBtn(sortCp,cpBtn);
                }
                if(sortDep != 0){
                    sortDep = 0;
                    changeBtn(sortDep,depBtn);
                }
                if(sortHel != 0){
                    sortHel = 0;
                    changeBtn(sortHel,helBtn);
                }
                if(sortAtt == 1){
                    sortAtt = -1;
                    pokemonAdapter.sortAttDec();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortAtt, attBtn);
                }else{
                    sortAtt = 1;
                    pokemonAdapter.sortAttAsc();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortAtt, attBtn);
                }
            }
        });

        depBtn = v.findViewById(R.id.depBtn);
        depBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortNum != 0){
                    sortNum = 0;
                    changeBtn(sortNum,numBtn);
                }
                if(sortCp != 0){
                    sortCp = 0;
                    changeBtn(sortCp,cpBtn);
                }
                if(sortAtt != 0){
                    sortAtt = 0;
                    changeBtn(sortAtt,attBtn);
                }
                if(sortHel != 0){
                    sortHel = 0;
                    changeBtn(sortHel,helBtn);
                }
                if(sortDep == 1){
                    sortDep = -1;
                    pokemonAdapter.sortDepDec();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortDep, depBtn);
                }else{
                    sortDep = 1;
                    pokemonAdapter.sortDepAsc();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortDep, depBtn);
                }
            }
        });

        helBtn = v.findViewById(R.id.helBtn);
        helBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortNum != 0){
                    sortNum = 0;
                    changeBtn(sortNum,numBtn);
                }
                if(sortCp != 0){
                    sortCp = 0;
                    changeBtn(sortCp,cpBtn);
                }
                if(sortAtt != 0){
                    sortAtt = 0;
                    changeBtn(sortAtt,attBtn);
                }
                if(sortDep != 0){
                    sortDep = 0;
                    changeBtn(sortDep,depBtn);
                }
                if(sortHel == 1){
                    sortHel = -1;
                    pokemonAdapter.sortHelDec();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortHel, helBtn);
                }else{
                    sortHel = 1;
                    pokemonAdapter.sortHelAsc();
                    pokemonAdapter.notifyDataSetChanged();
                    changeBtn(sortHel, helBtn);
                }
            }
        });

        return  v;
    }

    private void changeBtn(int cp, Button btn){
        if(cp == -1){
            btn.setText("내림차순");
            btn.setBackgroundResource(R.color.blue);
        }else if(cp == 1){
            btn.setText("오름차순");
            btn.setBackgroundResource(R.color.colorAccent);
        }else{
            btn.setText("OFF");
            btn.setBackgroundResource(R.color.gray);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        pokemonAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) { }
}
