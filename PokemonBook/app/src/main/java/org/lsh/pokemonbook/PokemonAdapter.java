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

    //cp ???????????? ??????
    public void sortCpAsc(){
        cp = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getCp() - p2.getCp();
            }
        });
    }
    //cp ???????????? ??????
    public void sortCpDec(){
        cp = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getCp() - p1.getCp();
            }
        });
    }
    //Num ???????????? ??????
    public void sortNumAsc(){
        num = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getNum() - p2.getNum();
            }
        });
    }
    //Num ???????????? ??????
    public void sortNumDec(){
        num = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getNum() - p1.getNum();
            }
        });
    }
    //Att ???????????? ??????
    public void sortAttAsc(){
        att = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getAtt() - p2.getAtt();
            }
        });
    }
    //Att ???????????? ??????
    public void sortAttDec(){
        att = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getAtt() - p1.getAtt();
            }
        });
    }
    //Dep ???????????? ??????
    public void sortDepAsc(){
        dep = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getDep() - p2.getDep();
            }
        });
    }
    //Dep ???????????? ??????
    public void sortDepDec(){
        dep = 0;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p2.getDep() - p1.getDep();
            }
        });
    }
    //Hel ???????????? ??????
    public void sortHelAsc(){
        hel = 1;
        Collections.sort(filteredItems, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getHel() - p2.getHel();
            }
        });
    }
    //Hel ???????????? ??????
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
            sort("??????");
        }else if(pos == 2){
            sort("??????");
        }else if(pos == 3){
            sort("??????");
        }else if(pos == 4){
            sort("???");
        }else if(pos == 5){
            sort("???");
        }else if(pos == 6){
            sort("??????");
        }else if(pos == 7){
            sort("??????");
        }else if(pos == 8){
            sort("?????????");
        }else if(pos == 9){
            sort("??????");
        }else if(pos == 10){
            sort("??????");
        }else if(pos == 11){
            sort("???");
        }else if(pos == 12){
            sort("???");
        }else if(pos == 13){
            sort("??????");
        }else if(pos == 14){
            sort("?????????");
        }else if(pos == 15){
            sort("??????");
        }else if(pos == 16){
            sort("?????????");
        }else if(pos == 17){
            sort("???");
        }else if(pos == 18){
            sort("?????????");
        }
    }

    //????????? ????????? ArrayList??? ????????? ?????????
    private void sort(String s){
        filteredItems.clear();

        if(s.equals("a")) { //????????? ????????? ???
            if(!charString.isEmpty()){ //?????? ?????? ??? ?????? ???
                for(Pokemon pokemon : origin) {
                    if(pokemon.getName().toLowerCase().contains(charString.toLowerCase())){
                        filteredItems.add(pokemon);
                    }
                }
            }else{ //?????? ?????? ????????? ???
                //?????? ????????? ?????? ?????? ?????? ????????? ?????? ????????? ???????????? ????????? ??????.
                //addAll??? ?????? ?????? ????????? ??????.
                filteredItems.addAll(origin);
            }
        }
        else{ //????????? ????????? ?????? ???
            ArrayList<Pokemon> filteringList = new ArrayList<>();
            for(Pokemon pokemon : origin) { //???????????? ?????????.
                String[] type = pokemon.getTypes();
                for(int i = 0; i < type.length; i++){
                    if(type[i].equals(s)){
                        filteringList.add(pokemon);
                        break;
                    }
                }
            }
            if(charString.isEmpty()){ //?????? ?????? ????????? ???
                filteredItems.addAll(filteringList);
            }else{ //?????? ?????? ??? ?????? ???
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

    //???????????? ??? ????????? ?????????????????? ?????????
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
