package partie;

import joueur.BotMinimax;
import joueur.Humain;
import plateau.Plateau;
import jeu.Gomoku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHumainVsBotMinimax {

    @Test
    public void testPartieBotMinimax() {
        Plateau plateau = new Plateau(new Gomoku(), 9);
        IJoueur humain = new Humain("Humain", "X");
        IJoueur botMinimax = new BotMinimax("Bot Minimax", "O", 5);

        IJoueur joueurActuel = humain;

        boolean partieTerminee = false;
        int coupsJoues = 0;

        System.out.println(plateau);

        while (!partieTerminee && coupsJoues < plateau.getTaillePlateau() * plateau.getTaillePlateau()) {
            if (joueurActuel instanceof Humain) {
                int x = coupsJoues / plateau.getTaillePlateau();
                int y = coupsJoues % plateau.getTaillePlateau();
                if (plateau.getPiece(x, y) == null) {
                    plateau.put(humain.getCouleur(), x, y);
                    System.out.println("Humain joue en : " + (char) ('A' + y) + (x + 1));
                }
            } else {
                botMinimax.play(plateau);
                System.out.println("Bot Minimax a joué.");
            }

            System.out.println(plateau);

            for (int i = 0; i < plateau.getTaillePlateau(); i++) {
                for (int j = 0; j < plateau.getTaillePlateau(); j++) {
                    if (plateau.getPiece(i, j) != null && plateau.getJeu().coupGagnant(plateau, i, j)) {
                        partieTerminee = true;
                        System.out.println("Victoire de : " + joueurActuel.getNom());
                        break;
                    }
                }
            }

            joueurActuel = (joueurActuel == humain) ? botMinimax : humain;
            coupsJoues++;
        }

        assertTrue(partieTerminee || coupsJoues == plateau.getTaillePlateau() * plateau.getTaillePlateau(),
                "La partie aurait dû se terminer correctement.");
    }
}

