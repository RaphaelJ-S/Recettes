package recettes;

import java.util.List;

public class Recette {

    private String nom;
    private String url;
    private List<String> ingredients;

    public Recette(String nom, String url, List<String> ingredients) {
        this.nom = nom;
        this.url = url;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuffer pres = new StringBuffer();
        pres.append("\n\n" + nom + " -> " + url);
        ingredients.forEach((ingredient) -> pres.append("\n" + ingredient));

        return pres.toString();
    }

}
