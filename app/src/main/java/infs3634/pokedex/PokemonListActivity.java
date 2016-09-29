package infs3634.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import infs3634.pokedex.dao.PokemonDAO;
import infs3634.pokedex.dto.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * An activity representing a list of Pokemons. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PokemonDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PokemonListActivity extends MenuActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private PokemonRVAdapter pAdptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_menu, frameLayout);
        setContentView(R.layout.activity_pokemon_list);

        setupRecyclerView();

        // The detail container view will be present only in the
        // large-screen layouts (res/values-w900dp).
        // If this view is present, then the
        // activity should be in two-pane mode.
        mTwoPane = findViewById(R.id.pokemon_detail_container) != null;

        int top = getIntent().getIntExtra("top", 1);
        ((RecyclerView) recyclerView).scrollToPosition(top - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pAdptor.filterPokemon(newText);
                return true;
            }
        });
        return true;
    }


    private void setupRecyclerView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.pokemon_list);
        assert recyclerView != null;
        this.pAdptor = new PokemonRVAdapter();
        this.recyclerView.setAdapter(this.pAdptor);
    }

    public class PokemonRVAdapter
            extends RecyclerView.Adapter<PokemonRVAdapter.ViewHolder> {

        private List<Pokemon> pokemons;

        public PokemonRVAdapter() {
            this.pokemons = PokemonDAO.getPokemons();
        }

        public void filterPokemon(String query) {
            if (query != null && !query.trim().isEmpty()) {
                this.pokemons = PokemonDAO.filterPokemons(query);
            } else {
                this.pokemons = PokemonDAO.getPokemons();
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pokemon_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.setPokemon(pokemons.get(position));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(PokemonDetailFragment.ARG_ITEM_ID, holder.pokemon.getId());
                        PokemonDetailFragment fragment = new PokemonDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.pokemon_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PokemonDetailActivity.class);
                        intent.putExtra(PokemonDetailFragment.ARG_ITEM_ID, holder.pokemon.getId());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return pokemons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final ImageView mImage;

            public Pokemon pokemon;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.list_item_id);
                mContentView = (TextView) view.findViewById(R.id.list_item_content);
                mImage = (ImageView) view.findViewById(R.id.list_item_image);
            }

            public void setPokemon(Pokemon pokemon) {
                this.pokemon = pokemon;
                this.mIdView.setText(pokemon.getIdStr());
                this.mContentView.setText(pokemon.getIdentifier());

                try {
                    // get input stream
                    InputStream ims = getAssets().open(pokemon.getPortraitFileName());
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    mImage.setImageDrawable(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
