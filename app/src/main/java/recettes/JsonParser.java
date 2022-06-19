package recettes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser implements Parser {

    @Override
    public Map<String, List<Recette>> parse(Lecteur lecteur) {
        String raw = lecteur.lire();
        JSONParser parser = new JSONParser();
        Map<String, List<Recette>> recettes = new HashMap<>();
        try {
            JSONObject base = (JSONObject) parser.parse(raw);
            JSONObject semaine = (JSONObject) base.get("semaine");
            recettes.put("dejeuner", parseElement(semaine, "dejeuner"));
            recettes.put("principal", parseElement(semaine, "principal"));
            recettes.put("collation", parseElement(semaine, "collation"));
            recettes.put("finDeSemaine", parseElement(base, "finDeSemaine"));

        } catch (ParseException pe) {
            System.out.println("Erreur dans le parsing des recettes");
        }

        return recettes;
    }

    private List<Recette> parseElement(JSONObject base, String key) {
        JSONArray recettes = (JSONArray) base.get(key);
        List<Recette> recetteParsed = new ArrayList<>();
        if (recettes != null) {
            Stream<JSONObject> str = (Stream<JSONObject>) recettes.stream();
            str.forEach((recette) -> {
                Map<String, Ingredient> nvIngredients = new HashMap<>();
                String nom = recette.get("nom").toString();
                String url = recette.get("url").toString();
                ArrayList<String> ingredients = new ArrayList<String>((JSONArray) recette.get("ingredients"));

                ingredients.forEach(ingredient -> {
                    Ingredient nv = new Ingredient(ingredient);
                    nvIngredients.compute(nv.nom,
                            (String cle, Ingredient value) -> value == null ? nv : value.ajouter(nv));
                });
                recetteParsed.add(new Recette(nom, url, nvIngredients));
            });
        }

        return recetteParsed;
    }

}
