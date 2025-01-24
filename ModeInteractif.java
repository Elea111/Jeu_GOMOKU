package mode;

import partie.IMode;
import plateau.Plateau;
import partie.IJoueur;

import java.util.Scanner;

public class ModeInteractif implements IMode {
    private final Plateau plateau;
    private final IJoueur joueur1;
    private final IJoueur joueur2;

    public ModeInteractif(Plateau plateau, IJoueur joueur1, IJoueur joueur2) {
        this.plateau = plateau;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    @Override
    public void lancer() {
        Scanner scanner = new Scanner(System.in);
        IJoueur joueurActuel = joueur1;

        System.out.println("Vous êtes en mode interactif. À tout moment, entrez 'switch' pour changer de mode.");

        while (true) {
            try {
                System.out.println(plateau);
                System.out.println(joueurActuel.getNom() + " (" + joueurActuel.getCouleur() + "), entrez votre coup (ex: A1) :");

                if (!(joueurActuel instanceof joueur.Humain)) {
                    joueurActuel.play(plateau);
                    if (checkCoupGagnant(joueurActuel)) {
                        System.out.println("Victoire de " + joueurActuel.getNom() + " !");
                        break;
                    }
                    joueurActuel = switchJoueur(joueurActuel);
                    continue;
                }

                String commande = scanner.nextLine().trim();

                if (commande.equalsIgnoreCase("switch")) {
                    System.out.println("Switching to GTP mode...");
                    break;
                }

                int x = Integer.parseInt(commande.substring(1)) - 1;
                int y = commande.charAt(0) - 'A';

                if (x < 0 || y < 0 || x >= plateau.getTaillePlateau() || y >= plateau.getTaillePlateau()) {
                    throw new IllegalArgumentException("Coordonnées hors limites !");
                }

                plateau.put(joueurActuel.getCouleur(), x, y);

                if (checkCoupGagnant(joueurActuel)) {
                    System.out.println("Victoire de " + joueurActuel.getNom() + " !");
                    break;
                }

                joueurActuel = switchJoueur(joueurActuel);

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private IJoueur switchJoueur(IJoueur joueurActuel) {
        return (joueurActuel == joueur1) ? joueur2 : joueur1;
    }

    private boolean checkCoupGagnant(IJoueur joueur) {
        for (int x = 0; x < plateau.getTaillePlateau(); x++) {
            for (int y = 0; y < plateau.getTaillePlateau(); y++) {
                if (plateau.getPiece(x, y) != null && plateau.getPiece(x, y).equals(joueur.getCouleur())) {
                    if (plateau.getJeu().coupGagnant(plateau, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


