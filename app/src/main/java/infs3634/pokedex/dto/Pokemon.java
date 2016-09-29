package infs3634.pokedex.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import infs3634.pokedex.apiClient.PokemonRestClient;

public class Pokemon {
    private int id;
    private String identifier;
    private int speciesId;
    private int height;
    private int weight;
    private int baseExperience;
    private int order;
    private int isDefault;
    private JSONObject jsonObject;
    private List<String> abilities;
    private List<String> moves;
    private String back_female;
    private String back_shiny_female;
    private String back_default;
    private String front_female;
    private String front_shiny_female;
    private String back_shiny;
    private String front_default;
    private String front_shiny;

    public Pokemon() {
        this.abilities = new ArrayList<String>();
        this.moves = new ArrayList<String>();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getAbilitiesStr() {
        String _str = this.abilities.toString();
        _str = _str.substring(0, _str.length() - 1).substring(1);
        return _str;
    }

    public String getMovesStr() {
        String _str = this.moves.toString();
        _str = _str.substring(0, _str.length() - 1).substring(1);
        return _str;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;

        try {
            // parse abilities
            JSONArray abilities = jsonObject.getJSONArray("abilities");
            for (int i = 0; i < abilities.length(); i++) {
                JSONObject obj = abilities.getJSONObject(i);
                this.abilities.add(obj.getJSONObject("ability").getString("name"));
            }

            // parse moves
            JSONArray moves = jsonObject.getJSONArray("moves");
            for (int i = 0; i < moves.length(); i++) {
                JSONObject obj = moves.getJSONObject(i);
                this.moves.add(obj.getJSONObject("move").getString("name"));
            }


            JSONObject sprites = jsonObject.getJSONObject("sprites");
            this.back_female = sprites.getString("back_female");
            this.back_shiny_female = sprites.getString("back_shiny_female");
            this.back_default = sprites.getString("back_default");
            this.front_female = sprites.getString("front_female");
            this.front_shiny_female = sprites.getString("front_shiny_female");
            this.back_shiny = sprites.getString("back_shiny");
            this.front_default = sprites.getString("front_default");
            this.front_shiny = sprites.getString("front_shiny");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getBack_female() {
        return back_female;
    }

    public void setBack_female(String back_female) {
        this.back_female = back_female;
    }

    public String getBack_shiny_female() {
        return back_shiny_female;
    }

    public void setBack_shiny_female(String back_shiny_female) {
        this.back_shiny_female = back_shiny_female;
    }

    public String getBack_default() {
        return back_default;
    }

    public void setBack_default(String back_default) {
        this.back_default = back_default;
    }

    public String getFront_female() {
        return front_female;
    }

    public void setFront_female(String front_female) {
        this.front_female = front_female;
    }

    public String getFront_shiny_female() {
        return front_shiny_female;
    }

    public void setFront_shiny_female(String front_shiny_female) {
        this.front_shiny_female = front_shiny_female;
    }

    public String getBack_shiny() {
        return back_shiny;
    }

    public void setBack_shiny(String back_shiny) {
        this.back_shiny = back_shiny;
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public String getFront_shiny() {
        return front_shiny;
    }

    public void setFront_shiny(String front_shiny) {
        this.front_shiny = front_shiny;
    }

    public String getURL() {
        return PokemonRestClient.getAbsUrl(String.format("pokemon/%d", this.id));
    }

    public String getIdStr() {
        return String.format("#%03d", this.id);
    }

    public String getPortraitFileName() {
        return String.format("pokemon/%d.png", this.id);
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
