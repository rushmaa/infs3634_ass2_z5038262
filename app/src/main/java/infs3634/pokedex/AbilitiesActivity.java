package infs3634.pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import infs3634.pokedex.dao.AbilityDAO;
import infs3634.pokedex.dao.AbilityDAO;
import infs3634.pokedex.dto.Ability;
import infs3634.pokedex.dto.Ability;

public class AbilitiesActivity extends MenuActivity {

    private RecyclerView recyclerView;
    private AbilityRVAdapter pAdptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities);

        this.recyclerView = (RecyclerView) findViewById(R.id.abilities_list);
        this.pAdptor = new AbilityRVAdapter();
        this.recyclerView.setAdapter(this.pAdptor);
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
                pAdptor.filterAbility(newText);
                return true;
            }
        });
        return true;
    }


    public class AbilityRVAdapter
            extends RecyclerView.Adapter<AbilityRVAdapter.ViewHolder> {

        private List<Ability> abilities;

        public AbilityRVAdapter() {
            this.abilities = AbilityDAO.getAbilities();
        }

        public void filterAbility(String query) {
            if (query != null && !query.trim().isEmpty()) {
                this.abilities = AbilityDAO.filterAbilities(query);
            } else {
                this.abilities = AbilityDAO.getAbilities();
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ability_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.setAbility(abilities.get(position));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, AbilityDetailActivity.class);
//                    intent.putExtra(AbilityDetailFragment.ARG_ITEM_ID, holder.ability.getId());
//
//                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return abilities.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mAbilityNameView;
            public final TextView mAbilityEffectView;

            public Ability ability;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mAbilityNameView = (TextView) view.findViewById(R.id.ability_name);
                mAbilityEffectView = (TextView) view.findViewById(R.id.ability_effect);
            }

            public void setAbility(Ability ability) {
                this.ability = ability;
                this.mAbilityNameView.setText(ability.getIdStr() + " - " + ability.getIdentifier());
                this.mAbilityEffectView.setText(ability.getEffect());
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mAbilityNameView.getText() + "'";
            }
        }
    }

}
