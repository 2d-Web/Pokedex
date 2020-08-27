package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.activities.VisActivity;
import com.example.pokedex.modelos.Pokemon;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<com.example.pokedex.ListaPokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> dataset;
    private Context context;
    private Pokemon p;

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }


    @NonNull
    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListaPokemonAdapter.ViewHolder holder, int position) {

        p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + p.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImagenView);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView fotoImagenView;
        private TextView nombreTextView;
        private CardView tarjetas;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImagenView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            tarjetas = (CardView) itemView.findViewById(R.id.tarjetas);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tarjetas:
                    Intent i = new Intent(view.getContext(), VisActivity.class);
                    view.getContext().startActivity(i);

                    Snackbar.make(view, "Hola Pokemon", Snackbar.LENGTH_LONG).show();
            break;

            }

        }
    }
}
