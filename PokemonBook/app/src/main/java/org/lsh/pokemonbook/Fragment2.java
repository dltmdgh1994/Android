package org.lsh.pokemonbook;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Fragment2 extends Fragment implements TextWatcher {
    RecyclerView recycler;
    SkillAdapter skillAdapter;
    EditText editText;

    String[] typeItems = {"전체","노말","격투","비행","독","땅","바위","벌레","고스트","강철",
            "불꽃","물","풀","전기","에스퍼","얼음","드래곤","악","페어리"};
    String[] sortItems = {"전체","특수 공격","통상 공격"};


    public Fragment2(SkillAdapter skillAdapter){
        this.skillAdapter = skillAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, container, false);
        recycler = v.findViewById(R.id.skillRecycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(skillAdapter);

        editText = v.findViewById(R.id.skillEditText);
        editText.addTextChangedListener(this);

        Spinner sortSpinner = v.findViewById(R.id.sortSpinner);
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, sortItems
        );
        sortAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sortSpinner.setAdapter(sortAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skillAdapter.showSort(position);
                skillAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Spinner typeSpinner = v.findViewById(R.id.typeSpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, typeItems
        );
        typeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skillAdapter.showType(position);
                skillAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        return  v;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        skillAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) { }
}
