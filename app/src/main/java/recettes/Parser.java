package recettes;

import java.util.List;
import java.util.Map;

public interface Parser {

    public Map<String, List<Recette>> parse(Lecteur lecteur);

}
