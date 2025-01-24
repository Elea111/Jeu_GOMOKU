package jeu;

import plateau.IJeuDePlateau;
import plateau.Plateau;

public class Gomoku implements IJeuDePlateau {


    @Override
    public boolean coupValide(Plateau plateau, int x, int y) {
        return plateau.getPiece(x,y) == null;
    }
    @Override
    public int taillePlateauMin(){ return 5;}

    @Override
    public boolean coupGagnant(Plateau plateau, int x, int y) {
        if (x < 0 || y < 0 || x >= plateau.getTaillePlateau() || y >= plateau.getTaillePlateau()) {
            return false; // si les coordonnées sont invalide
        }

        String piece = plateau.getPiece(x, y);

        if (piece == null) {
            return false;
        }

        return verifieAlignement(plateau, x, y, piece);
    }


    private boolean verifieAlignement(Plateau plateau, int x, int y, String piece) {

        int[][] directions = {
                {1, 0},  // Horizontal
                {0, 1},  // Vertical
                {1, 1},  // Diagonale haut
                {1, -1}  // Diagonale bas
        };

        for (int[] direction : directions) {
            int count = 1;


            count += compterAlignement(plateau, x, y, direction[0], direction[1], piece);


            count += compterAlignement(plateau, x, y, -direction[0], -direction[1], piece);


            if (count >= 5) {
                return true;
            }
        }

        return false;
    }

    private int compterAlignement(Plateau plateau, int x, int y, int dx, int dy, String piece) {
        int count = 0;
        int nx = x + dx;
        int ny = y + dy;

        while (nx >= 0 && ny >= 0 && nx < plateau.getTaillePlateau() && ny < plateau.getTaillePlateau() &&
                piece.equals(plateau.getPiece(nx, ny))) {
            count++;
            nx += dx;
            ny += dy;
        }

        return count;
    }



}


