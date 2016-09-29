package infs3634.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import infs3634.pokedex.dao.AbilityDAO;
import infs3634.pokedex.dao.PokemonDAO;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Frame layout: Which is going to be used as parent layout for child activity layout.
     * This layout is protected so that child activity can access this
     */
    protected FrameLayout frameLayout;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // prepare the pokemon data
        PokemonDAO.loadPokemonData(getResources());
        AbilityDAO.loadAbilityData(getResources());

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_menu, null);
        frameLayout = (FrameLayout) mDrawerLayout.findViewById(R.id.content_frame);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(mDrawerLayout);
        setUpNavDrawer();
    }

    protected void setUpNavDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(getTitle());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pokemon_list) {
            Intent intent = new Intent(this, PokemonListActivity.class);
            startActivity(intent);
        } else if (id == R.id.poke_rap) {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
        } else if (id == R.id.tips) {
            Intent intent = new Intent(this, TipsActivity.class);
            startActivity(intent);
        } else if (id == R.id.abilities) {
            Intent intent = new Intent(this, AbilitiesActivity.class);
            startActivity(intent);
        } else if (id == R.id.terms_and_conditions) {
            Intent intent = new Intent(this, TermConditionActivity.class);
            startActivity(intent);
        } else if (id == R.id.pokeRadar) {
            Intent intent = new Intent(this, PokeradarActivity.class);
            startActivity(intent);
        } else if (id == R.id.map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
