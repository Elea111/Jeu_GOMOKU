package joueur;

import partie.IJoueur;
import plateau.Plateau;

public class BotMinimax implements IJoueur {
    private String nom;
    private String couleur;
    private int profondeur;

    public BotMinimax(String nom, String couleur, int profondeur) {
        this.nom = nom;
        this.couleur = couleur;
        this.profondeur = profondeur;
    }

    @Override
    public void play(Plateau plateau) {
        int[] bestMove = trouverMeilleurCoup(plateau, couleur, profondeur);
        if (bestMove[0] != -1 && bestMove[1] != -1) {
            plateau.put(couleur, bestMove[0], bestMove[1]);
            System.out.println(nom + " joue en : " + (char) ('A' + bestMove[1]) + (bestMove[0] + 1));
        }
    }

    private int[] trouverMeilleurCoup(Plateau plateau, String bot, int profondeur) {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        String adversaire = bot.equals("X") ? "O" : "X";

        for (int i = 0; i < plateau.getTaillePlateau(); i++) {
            for (int j = 0; j < plateau.getTaillePlateau(); j++) {
                if (plateau.getPiece(i, j) == null) {
                    plateau.put(bot, i, j);

                    if (plateau.getJeu().coupGagnant(plateau, i, j)) {
                        plateau.removePiece(i, j);
                        return new int[]{i, j};
                    }

                    plateau.removePiece(i, j);

                    plateau.put(adversaire, i, j);

                    if (plateau.getJeu().coupGagnant(plateau, i, j)) {
                        plateau.removePiece(i, j);
                        return new int[]{i, j};
                    }

                    plateau.removePiece(i, j);

                    plateau.put(bot, i, j);
                    int moveValue = minimax(plateau, profondeur - 1, false, bot, adversaire);
                    plateau.removePiece(i, j);

                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(Plateau plateau, int profondeur, boolean isMax, String bot, String adversaire) {
        if (profondeur == 0) {
            return evaluerPlateau(plateau, bot, adversaire);
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < plateau.getTaillePlateau(); i++) {
                for (int j = 0; j < plateau.getTaillePlateau(); j++) {
                    if (plateau.getPiece(i, j) == null) {
                        plateau.put(bot, i, j);
                        best = Math.max(best, minimax(plateau, profondeur - 1, false, bot, adversaire));
                        plateau.removePiece(i, j);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < plateau.getTaillePlateau(); i++) {
                for (int j = 0; j < plateau.getTaillePlateau(); j++) {
                    if (plateau.getPiece(i, j) == null) {
                        plateau.put(adversaire, i, j);
                        best = Math.min(best, minimax(plateau, profondeur - 1, true, bot, adversaire));
                        plateau.removePiece(i, j);
                    }
                }
            }
            return best;
        }
    }

    private int evaluerPlateau(Plateau plateau, String bot, String adversaire) {
        int score = 0;

        for (int i = 0; i < plateau.getTaillePlateau(); i++) {
            for (int j = 0; j < plateau.getTaillePlateau(); j++) {
                String piece = plateau.getPiece(i, j);
                if (piece != null) {
                    if (piece.equals(bot)) {
                        score += compterAlignement(plateau, i, j, bot);
                    } else if (piece.equals(adversaire)) {
                        score -= compterAlignement(plateau, i, j, adversaire);
                    }
                }
            }
        }
        return score;
    }

    private int compterAlignement(Plateau plateau, int x, int y, String piece) {
        int score = 0;

        int[][] directions = {
                {1, 0}, {0, 1}, {1, 1}, {1, -1}
        };

        for (int[] direction : directions) {
            int count = 1;

            int nx = x + direction[0];
            int ny = y + direction[1];
            while (nx >= 0 && ny >= 0 && nx < plateau.getTaillePlateau() && ny < plateau.getTaillePlateau() &&
                    piece.equals(plateau.getPiece(nx, ny))) {
                count++;
                nx += direction[0];
                ny += direction[1];
            }

            nx = x - direction[0];
            ny = y - direction[1];
            while (nx >= 0 && ny >= 0 && nx < plateau.getTaillePlateau() && ny < plateau.getTaillePlateau() &&
                    piece.equals(plateau.getPiece(nx, ny))) {
                count++;
                nx -= direction[0];
                ny -= direction[1];
            }

            if (count >= 5) {
                return Integer.MAX_VALUE;
            }

            score += count * count;
        }

        return score;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }
}


