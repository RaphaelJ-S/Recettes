package recettes;

import java.util.List;
import java.util.Optional;

import javax.naming.directory.InvalidAttributesException;

public interface Parser {

    public List<Optional<List<Recette>>> parse(Lecteur lecteur);

}
