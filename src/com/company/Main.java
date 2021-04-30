package com.company;



import java.io.IOException;

import java.util.ArrayList;



public class Main {

    public static void main(String[] args) throws IOException {
        Solveur s=new Solveur();
        Grille grille= s.chargerFichier("puzzles/puzzle04.txt");

        if(grille!=null) {
            System.out.println("GRILLE INITIALE\n" + grille);

            Noeud noeudFinal = s.algoAstar(grille);

            if (noeudFinal != null) {
                System.out.println("CHEMIN COMPLET");
                ArrayList<Grille> cheminComplet = s.cheminComplet(noeudFinal);
                for (int i = cheminComplet.size() - 1; i >= 0; i--) {
                    System.out.println(cheminComplet.get(i));
                }
            }
            else
                System.out.println("Pas de solution");
        }
    }
}
