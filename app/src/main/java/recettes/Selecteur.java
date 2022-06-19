package recettes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Selecteur {

    private List<Recette> dejeuner;
    private List<Recette> principal;
    private List<Recette> collation;
    private List<Recette> finDeSemaine;

    public Selecteur(List<Optional<List<Recette>>> recettes) {
        this.dejeuner = recettes.get(0).orElseGet(() -> new ArrayList<Recette>());
        this.principal = recettes.get(1).orElseGet(() -> new ArrayList<Recette>());
        this.collation = recettes.get(2).orElseGet(() -> new ArrayList<Recette>());
        this.finDeSemaine = recettes.get(3).orElseGet(() -> new ArrayList<Recette>());
    }

    public Optional<List<Recette>> faireChoix() {
        Optional<List<Recette>> choix = Optional.empty();
        if (dejeuner.size() != 0 && principal.size() != 0) {
            List<Recette> recettesChoisies = new ArrayList<>();
            List<Optional<Integer>> index = genererIndexChoixUnique();
            Integer indexDejeuner = index.get(0).get();
            Integer indexDiner = index.get(1).get();
            Integer indexSouper = index.get(2).get();
            recettesChoisies.add(dejeuner.get(indexDejeuner));
            recettesChoisies.add(principal.get(indexDiner));
            recettesChoisies.add(principal.get(indexSouper));
            recettesChoisies.add(
                    index.get(3).isPresent() ? collation.get(index.get(3).get()) : null);
            recettesChoisies.add(
                    index.get(4).isPresent() ? finDeSemaine.get(index.get(4).get()) : null);
            choix = Optional.of(recettesChoisies);

        }

        return choix;

    }

    private List<Optional<Integer>> genererIndexChoixUnique() {
        Random rand = new Random();
        Optional<Integer> indexDejeuner = Optional.of(rand.nextInt(dejeuner.size()));
        Optional<Integer> indexDiner = Optional.of(rand.nextInt(principal.size()));
        Optional<Integer> indexSouper = Optional.of(rand.nextInt(principal.size()));
        Optional<Integer> indexCollation = collation.size() == 0 ? Optional.empty()
                : Optional.of(rand.nextInt(collation.size()));
        Optional<Integer> indexFinDeSemaine = finDeSemaine.size() == 0 ? Optional.empty()
                : Optional.of(rand.nextInt(finDeSemaine.size()));
        return new ArrayList<>(
                Arrays.asList(indexDejeuner, indexDiner, indexSouper, indexCollation, indexFinDeSemaine));
    }

    @Override
    public String toString() {
        return "Recettes de la semaine : \n\nDejeuner: " + dejeuner.toString() + "\n\nPrincipal : "
                + principal.toString() + "\n\nCollation : " + collation.toString() + "\n\nRecettes de la fds : "
                + finDeSemaine.toString();
    }
}
