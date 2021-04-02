package org.lsh.pokemonbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>
                            implements onPokemonItemClickListener, Filterable {

    ArrayList<Pokemon> filteredItems = new ArrayList<>();
    ArrayList<Pokemon> origin = new ArrayList<>();

    onPokemonItemClickListener listener;

    String charString = ""; int type = -1;
    int num = -1; int cp = -1; int att = -1; int dep = -1; int hel = -1;

    @Override
    public void onItemClick(PokemonAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public void setOnPokemonItemClickListener(onPokemonItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pokemonlayout,parent,false);

        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon item = filteredItems.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView, final onPokemonItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
        public void setItem(Pokemon item){
            imageView.setImageResource(item.getImg());
            textView.setText(item.getName());
        }
    }

    public void addItem(Pokemon item){
        filteredItems.add(item);
        origin.add(item);
    }

    public Pokemon getItem(int position){
        return filteredItems.get(position);
    }

    //cp 오름차순 정렬
    public void sortCpAsc(){
        cp = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getCp() - p2.getCp();
            }
        });
    }
    //cp 내림차순 정렬
    public void sortCpDec(){
        cp = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getCp() - p1.getCp();
            }
        });
    }
    //Num 오름차순 정렬
    public void sortNumAsc(){
        num = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getNum() - p2.getNum();
            }
        });
    }
    //Num 내림차순 정렬
    public void sortNumDec(){
        num = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getNum() - p1.getNum();
            }
        });
    }
    //Att 오름차순 정렬
    public void sortAttAsc(){
        att = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getAtt() - p2.getAtt();
            }
        });
    }
    //Att 내림차순 정렬
    public void sortAttDec(){
        att = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getAtt() - p1.getAtt();
            }
        });
    }
    //Dep 오름차순 정렬
    public void sortDepAsc(){
        dep = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getDep() - p2.getDep();
            }
        });
    }
    //Dep 내림차순 정렬
    public void sortDepDec(){
        dep = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getDep() - p1.getDep();
            }
        });
    }
    //Hel 오름차순 정렬
    public void sortHelAsc(){
        hel = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getHel() - p2.getHel();
            }
        });
    }
    //Hel 내림차순 정렬
    public void sortHelDec(){
        hel = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getHel() - p1.getHel();
            }
        });
    }

    public void show(int pos){
        type = pos;
        if(pos == 0){
            sort("a");
        }else if(pos == 1){
            sort("노말");
        }else if(pos == 2){
            sort("격투");
        }else if(pos == 3){
            sort("비행");
        }else if(pos == 4){
            sort("독");
        }else if(pos == 5){
            sort("땅");
        }else if(pos == 6){
            sort("바위");
        }else if(pos == 7){
            sort("벌레");
        }else if(pos == 8){
            sort("고스트");
        }else if(pos == 9){
            sort("강철");
        }else if(pos == 10){
            sort("불꽃");
        }else if(pos == 11){
            sort("물");
        }else if(pos == 12){
            sort("풀");
        }else if(pos == 13){
            sort("전기");
        }else if(pos == 14){
            sort("에스퍼");
        }else if(pos == 15){
            sort("얼음");
        }else if(pos == 16){
            sort("드래곤");
        }else if(pos == 17){
            sort("악");
        }else if(pos == 18){
            sort("페어리");
        }
    }

    //입력한 타입을 ArrayList에 채우는 메소드
    private void sort(String s){
        filteredItems.clear();

        if(s.equals("a")) { //타입이 전체일 때
            if(!charString.isEmpty()){ //검색 칸이 차 있을 때
                for(Pokemon pokemon : origin) {
                    if(pokemon.getName().toLowerCase().contains(charString.toLowerCase())){
                        filteredItems.add(pokemon);
                    }
                }
            }else{ //검색 칸이 비었을 때
                //그냥 대입을 하게 되면 얉은 복사를 하기 때문에 원본에도 영향을 준다.
                //addAll을 통해 깊은 복사를 한다.
                filteredItems.addAll(origin);
            }
        }
        else{ //타입이 전체가 아닐 때
            ArrayList<Pokemon> filteringList = new ArrayList<>();
            for(Pokemon pokemon : origin) { //타입별로 채운다.
                String[] type = pokemon.getTypes();
                for(int i = 0; i < type.length; i++){
                    if(type[i].equals(s)){
                        filteringList.add(pokemon);
                        break;
                    }
                }
            }
            if(charString.isEmpty()){ //검색 칸이 비었을 때
                filteredItems.addAll(filteringList);
            }else{ //검색 칸이 차 있을 때
                for(Pokemon pokemon : filteringList) {
                    if(pokemon.getName().toLowerCase().contains(charString.toLowerCase())){
                        filteredItems.add(pokemon);
                    }
                }
            }
            filteringList = null;
        }

        if(num == 0){
            sortNumDec();
        }else if(num == 1){
            sortNumAsc();
        }

        if(cp == 0){
            sortCpDec();
        }else if(cp == 1){
            sortCpAsc();
        }

        if(att == 0){
            sortAttDec();
        }else if(att == 1){
            sortAttAsc();
        }

        if(dep == 0){
            sortDepDec();
        }else if(dep == 1){
            sortDepAsc();
        }

        if(hel == 0){
            sortHelDec();
        }else if(hel == 1){
            sortHelAsc();
        }
    }

    //검색했을 때 뷰들을 필터링해주는 메소드
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                charString = constraint.toString();
                show(type);

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (ArrayList<Pokemon>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
