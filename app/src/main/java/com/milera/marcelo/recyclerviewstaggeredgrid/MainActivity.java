package com.milera.marcelo.recyclerviewstaggeredgrid;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.milera.marcelo.recyclerviewstaggeredgrid.data.PokemonData;
import com.milera.marcelo.recyclerviewstaggeredgrid.model.Pokemon;
import com.milera.marcelo.recyclerviewstaggeredgrid.ui.adapter.RecyclerViewAdapter;
import com.milera.marcelo.recyclerviewstaggeredgrid.ui.adapter.OnItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        pokemonList = new PokemonData().generate();

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, pokemonList);
        rcAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(rcAdapter);
    }

    private void gotoDetailsAnimation(Pokemon pokemon,ImageView imageView) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("POKEMON", pokemon);
        intent.putExtra("IMAGE_TRANSITION", ViewCompat.getTransitionName(imageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle());
        }
    }

    @Override
    public void onClikListener(int position, View container, ImageView imageView) {
        if(pokemonList!=null){
            Pokemon pokemon= pokemonList.get(position);
            gotoDetailsAnimation(pokemon,imageView);
        }
    }
}
