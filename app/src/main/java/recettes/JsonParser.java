package recettes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser implements Parser {

    @Override
    public List<Optional<List<Recette>>> parse(Lecteur lecteur) {
        String raw = lecteur.lire();
        JSONParser parser = new JSONParser();
        List<Optional<List<Recette>>> recettes = new ArrayList<>();
        try {
            JSONObject base = (JSONObject) parser.parse(raw);
            JSONObject semaine = (JSONObject) base.get("semaine");

            recettes.add(parseElement(semaine, "dejeuner"));
            recettes.add(parseElement(semaine, "principal"));
            recettes.add(parseElement(semaine, "collation"));
            recettes.add(parseElement(base, "finDeSemaine"));

        } catch (ParseException pe) {
            System.out.println("Erreur dans le parsing des recettes");
        }

        return recettes;
    }

    private Optional<List<Recette>> parseElement(JSONObject base, String key) {
        JSONArray recettes = (JSONArray) base.get(key);
        Optional<List<Recette>> resultats;
        List<Recette> recetteParsed = new ArrayList<>();
        if (recettes != null) {
            Stream<JSONObject> str = (Stream<JSONObject>) recettes.stream();
            str.forEach((recette) -> {
                String nom = recette.get("nom").toString();
                String url = recette.get("url").toString();
                JSONArray ingredients = (JSONArray) recette.get("ingredients");
                recetteParsed.add(new Recette(nom, url, new ArrayList<String>(ingredients)));
            });
            resultats = Optional.of(recetteParsed);
        } else {
            resultats = Optional.empty();
        }

        return resultats;
    }

}
