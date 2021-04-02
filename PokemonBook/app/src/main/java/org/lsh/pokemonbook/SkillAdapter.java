package org.lsh.pokemonbook;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder>
                            implements onSkillItemClickListener, Filterable {

    ArrayList<Skill> filteredItems = new ArrayList<>();
    ArrayList<Skill> origin = new ArrayList<>();
    ArrayList<Skill> normal = new ArrayList<>();
    ArrayList<Skill> special = new ArrayList<>();

    int type = 0; int sort = 0;
    String charString = "";

    onSkillItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.skill_layout,parent,false);

        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill item = filteredItems.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public void setOnSkillItemClickListener(onSkillItemClickListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView skill_name;
        TextView skill_type;
        TextView skill_sort;
        TextView skill_power;

        public ViewHolder(@NonNull View itemView,final onSkillItemClickListener listener) {
            super(itemView);

            skill_name = itemView.findViewById(R.id.skill_in_recycler_name);
            skill_type = itemView.findViewById(R.id.skill_layout_type);
            skill_sort = itemView.findViewById(R.id.skill_layout_sort);
            skill_power = itemView.findViewById(R.id.skill_layout_power);

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
        public void setItem(Skill item){
            skill_name.setText(item.getSkill_name());
            skill_type.setText(item.getSkill_type());
            skill_sort.setText(item.getSkill_sort());
            skill_power.setText(item.getSkill_power());
        }
    }

    public void addItem(Skill item){
        filteredItems.add(item);
        origin.add(item);
        if(item.getSkill_sort().equals("특수 공격")){
            special.add(item);
        }else{
            normal.add(item);
        }
    }
    public Skill getItem(int position){
        return filteredItems.get(position);
    }

    private String setTypeString(){
        if(type == 0){
            return "a";
        }else if(type == 1){
            return "노말";
        }else if(type == 2){
            return "격투";
        }else if(type == 3){
            return "비행";
        }else if(type == 4){
            return "독";
        }else if(type == 5){
            return "땅";
        }else if(type == 6){
            return "바위";
        }else if(type == 7){
            return "벌레";
        }else if(type == 8){
            return "고스트";
        }else if(type == 9){
            return "강철";
        }else if(type == 10){
            return "불꽃";
        }else if(type == 11){
            return "물";
        }else if(type == 12){
            return "풀";
        }else if(type == 13){
            return "전기";
        }else if(type == 14){
            return "에스퍼";
        }else if(type == 15){
            return "얼음";
        }else if(type == 16){
            return "드래곤";
        }else if(type == 17){
            return "악";
        }else if(type == 18){
            return "페어리";
        }
        return "";
    }

    public void showType(int pos){
        setType(pos);
        sort();
    }

    public void showSort(int pos){
        setSort(pos);
        sort();
    }

    private void setType(int i){
        type = i;
    }

    private void setSort(int i){
        sort = i;
    }

    //sort -> type -> text
    private void sort(){
        filteredItems.clear();

        if(sort == 0){ //분류가 전체일 때
            if(type == 0){ //타입이 전체일 때
                if(!charString.isEmpty()){ //검색 칸이 차 있을 때
                    for(Skill skill : origin) {
                        if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                            filteredItems.add(skill);
                        }
                    }
                }else{ //검색 칸이 비었을 때
                    //그냥 대입을 하게 되면 얉은 복사를 하기 때문에 원본에도 영향을 준다.
                    //addAll을 통해 깊은 복사를 한다.
                    filteredItems.addAll(origin);
                }
            }else{ //타입이 전체가 아닐 때
                ArrayList<Skill> filteringList = new ArrayList<>();
                String s = setTypeString();
                for(Skill skill : origin) { //타입별로 채운다.
                    if(skill.getSkill_type().equals(s)){
                        filteringList.add(skill);
                    }
                }
                if(charString.isEmpty()){ //검색 칸이 비었을 때
                    filteredItems.addAll(filteringList);
                }else{ //검색 칸이 차 있을 때
                    for(Skill skill : filteringList) {
                        if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                            filteredItems.add(skill);
                        }
                    }
                }
                filteringList = null;
            }
        }else{ //분류가 전체가 아닐 때
            if(sort == 1){ //특수 공격
                if(type == 0){ //타입이 전체일 때
                    if(!charString.isEmpty()){ //검색 칸이 차 있을 때
                        for(Skill skill : special) {
                            if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                                filteredItems.add(skill);
                            }
                        }
                    }else{ //검색 칸이 비었을 때
                        //그냥 대입을 하게 되면 얉은 복사를 하기 때문에 원본에도 영향을 준다.
                        //addAll을 통해 깊은 복사를 한다.
                        filteredItems.addAll(special);
                    }
                }else{ //타입이 전체가 아닐 때
                    ArrayList<Skill> filteringList = new ArrayList<>();
                    String s = setTypeString();
                    for(Skill skill : special) { //타입별로 채운다.
                        if(skill.getSkill_type().equals(s)){
                            filteringList.add(skill);
                        }
                    }
                    if(charString.isEmpty()){ //검색 칸이 비었을 때
                        filteredItems.addAll(filteringList);
                    }else{ //검색 칸이 차 있을 때
                        for(Skill skill : filteringList) {
                            if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                                filteredItems.add(skill);
                            }
                        }
                    }
                    filteringList = null;
                }
            }else if(sort == 2){ //통상 공격
                if(type == 0){ //타입이 전체일 때
                    if(!charString.isEmpty()){ //검색 칸이 차 있을 때
                        for(Skill skill : normal) {
                            if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                                filteredItems.add(skill);
                            }
                        }
                    }else{ //검색 칸이 비었을 때
                        //그냥 대입을 하게 되면 얉은 복사를 하기 때문에 원본에도 영향을 준다.
                        //addAll을 통해 깊은 복사를 한다.
                        filteredItems.addAll(normal);
                    }
                }else{ //타입이 전체가 아닐 때
                    ArrayList<Skill> filteringList = new ArrayList<>();
                    String s = setTypeString();
                    for(Skill skill : normal) { //타입별로 채운다.
                        if(skill.getSkill_type().equals(s)){
                            filteringList.add(skill);
                        }
                    }
                    if(charString.isEmpty()){ //검색 칸이 비었을 때
                        filteredItems.addAll(filteringList);
                    }else{ //검색 칸이 차 있을 때
                        for(Skill skill : filteringList) {
                            if(skill.getSkill_name().toLowerCase().contains(charString.toLowerCase())){
                                filteredItems.add(skill);
                            }
                        }
                    }
                    filteringList = null;
                }
            }
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                charString = constraint.toString();
                sort();

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (ArrayList<Skill>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
