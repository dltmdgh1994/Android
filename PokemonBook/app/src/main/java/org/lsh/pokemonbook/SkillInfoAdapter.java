package org.lsh.pokemonbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SkillInfoAdapter extends RecyclerView.Adapter<SkillInfoAdapter.ViewHolder>
        implements onSkillInfoItemClickListener {

    ArrayList<Skill> items;

    onSkillInfoItemClickListener listener;

    public SkillInfoAdapter(ArrayList<Skill> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.skill_in_recycler, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public void setonSkillInfoItemClickListener(onSkillInfoItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView skill_name;

        public ViewHolder(@NonNull View itemView, final onSkillInfoItemClickListener listener) {
            super(itemView);

            skill_name = itemView.findViewById(R.id.skill_in_recycler_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(Skill item) {
            skill_name.setText(item.getSkill_name());
        }
    }

    public Skill getItem(int position) {
        return items.get(position);
    }
}