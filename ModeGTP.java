package mode;

import partie.IMode;
import plateau.Plateau;
import java.util.Scanner;
import static mode.CommandeGTP.*;

public class ModeGTP implements IMode {

    private final Plateau plateau;

    public ModeGTP(Plateau plateau) {
        this.plateau = plateau;
    }

    public void lancer() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String[] commande = scanner.nextLine().trim().split(" ");
                if (commande[0].equalsIgnoreCase("switch")) {
                    System.out.println("= switching to interactive mode");
                    break;
                } else {
                    traiterCommande(commande);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void traiterCommande(String[] commande) {
        switch (commande[0].toLowerCase()) {
            case "boardsize":
                boardsize(plateau, commande);
                break;
            case "clear_board":
                clear_board(plateau);
                break;
            case "play":
                play(plateau, commande);
                break;
            case "genmove":
                genmove(plateau, commande);
                break;
            case "showboard":
                showboard(plateau);
                break;
            case "quit":
                if (quit()) {
                    System.exit(0);
                }
                break;
            default:
                System.out.println("? unknown command");
        }
    }
}





