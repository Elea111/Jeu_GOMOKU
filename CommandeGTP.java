package mode;

import plateau.Plateau;

import java.util.Map;
import java.util.Random;

public class CommandeGTP {

    private static final Map<String, String> pions = Map.of(
            "white", "O",
            "black", "X"
    );

    public static void boardsize(Plateau plateau, String[] commande) {
        try {
            int taille = Integer.parseInt(commande[1]);

            if (!plateau.boardSize(taille)) {
                System.out.println("? board size outside engine's limits");
            } else {
                System.out.println("=");
            }
        } catch (Exception e) {
            System.out.println("? board size outside engine's limits");
        }
    }

    public static void clear_board(Plateau plateau){
        plateau.clearBoard();
        System.out.println("=");
    }

    public static void play(Plateau plateau, String[] commande) {
        if (commande.length < 3) {
            throw new IllegalArgumentException("? invalid vertex");
        }

        String[] position = commande[2].split("");
        int x = Integer.parseInt(position[1]) - 1;
        int y = position[0].charAt(0) - 'A';

        if (!plateau.put(pions.get(commande[1]), x, y)) {
            throw new IllegalArgumentException("? illegal move");
        }

        System.out.println("=");
    }
    public static void genmove(Plateau plateau, String[] commande) {
        if (commande.length < 2) {
            throw new IllegalArgumentException("? invalid vertex");
        }

        Random random = new Random();
        int colonne, ligne;

        do {
            colonne = random.nextInt(plateau.getTaillePlateau());
            ligne = random.nextInt(plateau.getTaillePlateau());
        } while (!plateau.getJeu().coupValide(plateau, ligne, colonne));

        plateau.put(pions.get(commande[1]), ligne, colonne);
        System.out.println("= " + (char) ('A' + colonne) + (ligne+1));
    }

    public static void showboard(Plateau plateau) {
        System.out.println("=\n" + plateau.toString());
    }

    public static boolean quit() {
        System.out.println("=");
        return false;
    }
}




