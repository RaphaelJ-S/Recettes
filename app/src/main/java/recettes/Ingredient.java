package recettes;

public class Ingredient {

    String nom;
    String volume; // unité très variable
    int poid;// en gramme

    public Ingredient(String ligne) {
        String[] volume = ligne.split(" \\(");
        String[] poid = volume[1].split("\\) ");
        this.nom = poid[1];
        this.volume = volume[0];
        this.poid = Integer.parseInt(poid[0].split(" g")[0]);

    }

    public Ingredient(String nom, String volume, int poid) {
        this.nom = nom;
        this.volume = volume;
        this.poid = poid;
    }

    public Ingredient ajouter(Ingredient aAjouter) {
        return new Ingredient(this.nom, this.volume + " et " + aAjouter.volume, this.poid + aAjouter.poid);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        return true;
    }

}
