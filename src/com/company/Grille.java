package com.company;

import java.util.Arrays;

public class Grille {
    private int[][] grille;
    private int taille;
    private int ligne0;
    private int colonne0;
    public Grille(int[][] g){
        this.grille=g;
        this.taille=g.length;
    }

    public int[][] getGrille() {
        return grille;
    }

    public int getTaille() {
        return taille;
    }

    public int getLigne0() {
        for(int i=0;i<getTaille();i++){
            for(int j=0;j<getTaille();j++){

                if (grille[i][j] == 0) {
                    this.ligne0 = i;
                    break;
                }
            }
        }
        return this.ligne0;
    }

        public int getColonne0() {
            for(int i=0;i<getTaille();i++){
                for(int j=0;j<getTaille();j++){
                    if(grille[i][j]==0) {
                        this.colonne0=j;
                        break;
                    }
                }
            }
            return this.colonne0;
        }
        public int getValeur(int i,int j){
            int val=0;
                for(int k=0;k<this.taille;k++)
                {
                    for(int l=0;l<this.taille;l++){
                        if (k == i && l == j) {
                            val = this.grille[i][j];
                            break;
                        }
                    }
                }
            return val;
        }
    public int[][] copier() {
        int[][] grilleCopie = new int[this.taille][this.taille];
        for(int i =0;i<this.taille ;i++){
            for(int j=0;j<this.taille;j++){
                grilleCopie[i][j]=this.grille[i][j];
            }
        }
        return grilleCopie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grille grille1 = (Grille) o;
        return taille == grille1.taille && ligne0 == grille1.ligne0 && colonne0 == grille1.colonne0 && Arrays.deepEquals(grille, grille1.grille);
    }

    @Override
    public String toString() {
        StringBuilder grille= new StringBuilder();
        String affiche="=";

       for(int i=0; i<this.taille;i++){
           if(i%this.taille==0){
               grille.append(affiche.repeat(6 * this.taille + 2)).append("\n");
           }
           for(int j=0;j<this.taille;j++){
               if(j%this.taille==0){
                   grille.append("|| ");
               }
               grille = new StringBuilder(String.valueOf(this.grille[i][j]).length() == 2 ? grille + String.valueOf(this.grille[i][j]) + " || " : grille + String.valueOf(this.grille[i][j]) + "  || ");
           }
           grille.append("\n");

       }
        grille.append(affiche.repeat(6 * this.taille + 2)).append("\n");

        return grille.toString();
    }
}
