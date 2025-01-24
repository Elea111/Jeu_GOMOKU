package joueur;

import partie.IJoueur;
import plateau.Plateau;

import java.util.Random;

public class BotNaif implements IJoueur {
    private final String nom;
    private final String couleur;

    public BotNaif(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    @Override
    public void play(Plateau plateau) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(plateau.getTaillePlateau());
            int y = random.nextInt(plateau.getTaillePlateau());

            if (x >= 0 && y >= 0 && x < plateau.getTaillePlateau() && y < plateau.getTaillePlateau() &&
                    plateau.getPiece(x, y) == null) {
                plateau.put(couleur, x, y);
                break;
            }
        }
    }


    @Override
    public String getNom() {
        return "Bot Naïf : " + nom;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }
}


