package partie;

import mode.ModeGTP;
import mode.ModeInteractif;
import partie.IMode;
import plateau.Plateau;
import partie.IJoueur;

public class GestionModes {
    private Plateau plateau;
    private IJoueur joueur1;
    private IJoueur joueur2;
    private IMode modeActuel;

    public GestionModes(Plateau plateau, IJoueur joueur1, IJoueur joueur2) {
        this.plateau = plateau;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.modeActuel = new ModeInteractif(plateau, joueur1, joueur2); // Mode par défaut
    }

    public void lancer() {
        while (true) {
            if (modeActuel instanceof ModeInteractif) {
                System.out.println("Vous êtes en mode interactif.");
            } else if (modeActuel instanceof ModeGTP) {
                System.out.println("Vous êtes en mode GTP.");
            }
            modeActuel.lancer();
            switchMode();
        }
    }

    private void switchMode() {
        if (modeActuel instanceof ModeInteractif) {
            modeActuel = new ModeGTP(plateau);
        } else {
            modeActuel = new ModeInteractif(plateau, joueur1, joueur2);
        }
    }
}


