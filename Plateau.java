package plateau;

public class Plateau {
    private String[][] plateau;
    private final IJeuDePlateau jeu;
    private static final int TAILLE_LIMITE = 26;

    public Plateau(IJeuDePlateau jeu, int taille) {
        this.jeu = jeu;
        boardSize(taille);
    }

    public boolean boardSize(int taille) {
        if (taille > TAILLE_LIMITE || taille < jeu.taillePlateauMin()) {
            return false;
        }
        this.plateau = new String[taille][taille];
        return true;
    }

    public void clearBoard() {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = null;
            }
        }
    }

    public boolean put(String piece, int x, int y) {
        if (x < 0 || y < 0 || x >= plateau.length || y >= plateau.length) {
            return false;
        }
        if (plateau[x][y] != null) {
            return false;
        }
        plateau[x][y] = piece;
        return true;
    }

    public void removePiece(int x, int y) {
        plateau[x][y] = null;
    }

    public String getPiece(int x, int y) {
        return plateau[x][y];
    }

    public int getTaillePlateau() {
        return plateau.length;
    }

    public IJeuDePlateau getJeu() {
        return jeu;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for (int i = 0; i < plateau.length; i++) {
            builder.append(" ").append((char) ('A' + i));
        }
        builder.append("\n");

        for (int i = plateau.length - 1; i >= 0; i--) {
            builder.append(i + 1).append(" ");
            for (int j = 0; j < plateau[i].length; j++) {
                builder.append(plateau[i][j] == null ? ". " : plateau[i][j] + " ");
            }
            builder.append(i + 1).append("\n");
        }

        builder.append(" ");
        for (int i = 0; i < plateau.length; i++) {
            builder.append(" ").append((char) ('A' + i));
        }
        return builder.toString();
    }
}