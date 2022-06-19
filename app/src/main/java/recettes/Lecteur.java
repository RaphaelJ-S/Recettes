package recettes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lecteur {

    private Path filePath;

    public Lecteur(String filePath) {
        this.filePath = Path.of(filePath, "").toAbsolutePath();
    }

    public String lire() {
        StringBuffer resultat = new StringBuffer();

        try {
            Files.lines(this.filePath).forEach(
                    (line) -> resultat.append(line));
        } catch (IOException ioe) {
            System.out.println("Erreur dans la lecture de " + this.filePath);
            ioe.printStackTrace();
        }

        return resultat.toString();
    }
}
