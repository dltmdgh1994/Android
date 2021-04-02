package org.lsh.pokemonbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonInfoAdapter extends RecyclerView.Adapter<PokemonInfoAdapter.ViewHolder>
        implements onPokemonInfoItemClickListener {

    ArrayList<Pokemon> items;

    onPokemonInfoItemClickListener listener;

    public PokemonInfoAdapter(ArrayList<Pokemon> items) {
        this.items = items;
    }

    @Override
    public void onItemClick(PokemonInfoAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public void setOnPokemonInfoItemClickListener(onPokemonInfoItemClickListener listener){
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
        Pokemon item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView, final onPokemonInfoItemClickListener listener) {
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

    public Pokemon getItem(int position){
        return items.get(position);
    }
}