package org.lsh.pokemonbook;

import android.view.View;

public interface onPokemonItemClickListener {
    public void onItemClick(PokemonAdapter.ViewHolder holder, View view, int position);
}
