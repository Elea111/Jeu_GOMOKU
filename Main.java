package partie;

import jeu.Gomoku;
import plateau.Plateau;
import joueur.BotNaif;
import joueur.BotMinimax;
import joueur.Humain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int taille;
        while (true) {
            System.out.println("Choisissez la taille du plateau (entre 5 et 26) :");
            try {
                taille = Integer.parseInt(scanner.nextLine().trim());

                if (taille >= 5 && taille <= 26) {
                    break;
                } else {
                    System.out.println("La taille doit être entre 5 et 26.");
                }
            } catch (NumberFormatException e) {
                System.out.println("commande inconnue");
            }
        }
        Plateau plateau = new Plateau(new Gomoku(), taille);

        String joueurNoir = demanderJoueur(scanner, "noir");
        String joueurBlanc = demanderJoueur(scanner, "blanc");

        IJoueur joueur1 = creerJoueur("Noir", joueurNoir, "X");
        IJoueur joueur2 = creerJoueur("Blanc", joueurBlanc, "O");

        GestionModes gestionModes = new GestionModes(plateau, joueur1, joueur2);
        gestionModes.lancer();
    }


    private static String demanderJoueur(Scanner scanner, String couleur) {
        String type;
        while (true) {
            System.out.println("Choisissez le joueur " + couleur + " (human, botNaif, botMinimax) :");
            type = scanner.nextLine().trim().toLowerCase();
            if (type.equals("human") || type.equals("botnaif") || type.equals("botminimax")) {
                break;
            } else {
                System.out.println("Veuillez entrer 'human', 'botNaif' ou 'botMinimax'");
            }
        }
        return type;
    }

    private static IJoueur creerJoueur(String role, String type, String couleur) {
        switch (type.toLowerCase()) {
            case "human":
                return new Humain("Joueur " + role, couleur);
            case "botnaif":
                return new BotNaif("Bot " + role, couleur);
            case "botminimax":
                System.out.println("Entrez la profondeur pour le Minimax: ");
                int profondeur = Integer.parseInt(new Scanner(System.in).nextLine().trim());
                return new BotMinimax("Bot Minimax " + role, couleur, profondeur);
                default:
                throw new IllegalArgumentException("Type de joueur inconnu : " + type);
        }
    }
}

