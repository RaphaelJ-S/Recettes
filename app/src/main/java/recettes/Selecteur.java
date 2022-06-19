package recettes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Selecteur {

    Map<String, List<Recette>> recettes;

    public Selecteur(Map<String, List<Recette>> recettes) {
        this.recettes = recettes;
    }

    public Map<String, Optional<Recette>> faireChoix() {
        Map<String, Optional<Recette>> choix = new HashMap<>();
        choix.put("dejeuner", genererIndexChoixUnique(this.recettes.get("dejeuner")));
        choix.put("diner", genererIndexChoixUnique(this.recettes.get("principal")));
        choix.put("souper", genererIndexChoixUnique(this.recettes.get("principal")));
        choix.put("collation", genererIndexChoixUnique(this.recettes.get("collation")));
        choix.put("finDeSemaine", genererIndexChoixUnique(this.recettes.get("finDeSemaine")));

        return choix;

    }

    private Optional<Recette> genererIndexChoixUnique(List<Recette> possibilites) {
        Random rand = new Random();
        Optional<Recette> choix = Optional.empty();
        if (possibilites.size() > 0) {
            int index = rand.nextInt(possibilites.size());
            Recette choisie = possibilites.get(index);
            choix = Optional.of(choisie);
        }

        return choix;
    }

}
