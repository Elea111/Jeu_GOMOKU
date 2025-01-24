package partie;

import plateau.Plateau;

public interface IJoueur {
    void play(Plateau plateau);
    String getNom();
    String getCouleur();
}



