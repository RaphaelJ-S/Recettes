package recettes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ecriveur {

    Path filePath;

    public Ecriveur(String path) {
        this.filePath = Path.of(path, "").toAbsolutePath();
    }

    public void ecrire(String contenu) {
        try {
            byte[] strToBtes = contenu.getBytes();
            Files.write(filePath, strToBtes);
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
