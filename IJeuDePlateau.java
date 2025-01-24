package plateau;

import plateau.Plateau;

public interface IJeuDePlateau {
    boolean coupValide(Plateau plateau, int x, int y);
    boolean coupGagnant(Plateau plateau, int x, int y);
    int taillePlateauMin();

}



