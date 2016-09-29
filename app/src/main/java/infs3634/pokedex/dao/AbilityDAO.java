package infs3634.pokedex.dao;

import android.content.res.Resources;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import infs3634.pokedex.dto.Ability;

public class AbilityDAO {

    private static List<Ability> abilities;

    public static List<Ability> getAbilities() {
        return abilities;
    }

    public static List<Ability> filterAbilities(String query) {
        List<Ability> resultList = new ArrayList<Ability>();
        query = query.toLowerCase();
        for (Ability p : getAbilities()) {
            if (p.getIdentifier().toLowerCase().indexOf(query) != -1 ||
                    p.getIdStr().indexOf(query) != -1) {
                resultList.add(p);
            }

        }
        return resultList;
    }


    public static Ability getAbilityById(int id) {
        return abilities.get(id - 1);
    }


    // prepare data
    public static void loadAbilityData(Resources resources) {

        // only run this once
        if (abilities != null) {
            return;
        }
        abilities = new ArrayList<Ability>();

        try {
            InputStream iS = resources.getAssets().open("data/abilities.csv");

            CsvReader csvReader = new CsvReader(iS, Charset.defaultCharset());

            csvReader.readHeaders();

            while (csvReader.readRecord()) {
                Ability p = new Ability();
                p.setId(Integer.parseInt(csvReader.get("id")));
                p.setIdentifier(csvReader.get("identifier"));
                p.setEffect(csvReader.get("effect"));
                abilities.add(p);
            }

            csvReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
