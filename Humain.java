package joueur;

import partie.IJoueur;
import plateau.Plateau;
import java.util.Scanner;

public class Humain implements IJoueur {
    private String nom;
    private String couleur;

    public Humain(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    @Override
    public void play(Plateau plateau) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(nom + " (" + couleur + "), entrez votre coup (ex: A1) :");
                String input = scanner.nextLine().trim();
                int x = Integer.parseInt(input.substring(1)) - 1;
                int y = input.charAt(0) - 'A';
                plateau.put(couleur, x, y);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
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




