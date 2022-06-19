package recettes;

import java.util.HashMap;
import java.util.Map;

public class Recette {

    private String nom;
    private String url;
    private Map<String, Ingredient> ingredients;

    public Recette(String nom, String url, Map<String, Ingredient> ingredients) {
        this.nom = nom;
        this.url = url;
        this.ingredients = ingredients;
    }

    public Map<String, Ingredient> combinerIngredients(Map<String, Ingredient> aCombiner) {
        Map<String, Ingredient> combinaison = new HashMap<>();

        combinaison.putAll(aCombiner);
        this.ingredients.forEach((key, value) -> {
            combinaison.compute(key, (String cle, Ingredient valeur) -> valeur == null ? value : valeur.ajouter(value));
        });

        return combinaison;
    }

    @Override
    public String toString() {
        StringBuffer pres = new StringBuffer();
        pres.append(nom + " -> " + url);

        return pres.toString();
    }

}
