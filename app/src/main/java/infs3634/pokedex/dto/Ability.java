package infs3634.pokedex.dto;

public class Ability {
    private int id;
    private String identifier;
    private String effect;

    public int getId() {
        return id;
    }

    public String getIdStr() {
        return String.format("#%03d", this.id);
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String toString(){
        return this.effect;
    }
}
