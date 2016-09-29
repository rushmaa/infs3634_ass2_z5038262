package infs3634.pokedex.dao;

import android.content.res.Resources;

import com.csvreader.CsvReader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import infs3634.pokedex.dto.Pokemon;

/**
 * Created by leeli on 12/09/2016.
 */
public class PokemonDAO {

    private static List<Pokemon> pokemons;

    public static List<Pokemon> getPokemons() {
        return pokemons;
    }

    public static List<Pokemon> filterPokemons(String query) {
        List<Pokemon> resultList = new ArrayList<Pokemon>();
        query = query.toLowerCase();
        for (Pokemon p : getPokemons()) {
            if (p.getIdentifier().toLowerCase().indexOf(query) != -1 ||
                    p.getIdStr().indexOf(query) != -1) {
                resultList.add(p);
            }

        }
        return resultList;
    }


    public static Pokemon getPokemonById(int id) {
        return pokemons.get(id - 1);
    }


    // prepare data
    public static void loadPokemonData(Resources resources) {
        // only run this once
        if (pokemons != null) {
            return;
        }
        pokemons = new ArrayList<Pokemon>();
        try {
            InputStream iS = resources.getAssets().open("data/pokemon.csv");

            CsvReader csvReader = new CsvReader(iS, Charset.defaultCharset());

            csvReader.readHeaders();

            while (csvReader.readRecord()) {
                Pokemon p = new Pokemon();
                p.setId(Integer.parseInt(csvReader.get("id")));
                p.setIdentifier(csvReader.get("identifier"));
                p.setSpeciesId(Integer.parseInt(csvReader.get("species_id")));
                p.setHeight(Integer.parseInt(csvReader.get("height")));
                p.setWeight(Integer.parseInt(csvReader.get("weight")));
                p.setBaseExperience(Integer.parseInt(csvReader.get("base_experience")));
                p.setOrder(Integer.parseInt(csvReader.get("order")));
                p.setIsDefault(Integer.parseInt(csvReader.get("is_default")));
                pokemons.add(p);
            }

            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
