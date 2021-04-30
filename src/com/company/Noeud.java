package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Noeud {
    private Grille grille;
    private Noeud pere;
    private  int g;

    public Noeud(Grille grille,Noeud p, int g){
        this.grille=grille;
        this.pere=p;
        this.g=g;
    }

    public Grille getGrille() {
        return grille;
    }

    public Noeud getPere() {
        return pere;
    }
    public int h1(){
        int h1=0;
        int taille=this.grille.getTaille();
        for(int i=0;i< taille;i++){
            for(int j=0;j< taille;j++){
                if((this.grille.getValeur(i,j)!=taille*i+j+1)){
                    if(this.grille.getValeur(i,j)!=0) {
                        h1+=1;
                    }
                    else{
                        if(i!=this.grille.getLigne0() && j!=this.grille.getColonne0()){
                            h1+=1;
                        }
                    }

                }

            }
        }
        return h1;
    }
    public int g(){
        return this.g;
    }
    public int f(){
        return this.g()+this.h1();
    }
    public boolean estUnEtatFinal(){
        boolean estFinal= this.h1() == 0;
        return  estFinal;
    }

    public void permuteCases(Grille maGrille,int mvtLigne,int mvtColonne){
        //Grille maGrille=new Grille(this.grille.copier());
        int ligne0 = grille.getLigne0();
        int colonne0 = grille.getColonne0();
        int temp;
        temp=maGrille.getValeur(ligne0,colonne0);
        maGrille.getGrille()[ligne0][colonne0]= maGrille.getValeur(ligne0+mvtLigne,colonne0+mvtColonne);
        maGrille.getGrille()[ligne0+mvtLigne][colonne0+mvtColonne]=temp;
        //return maGrille;
    }

    public ArrayList<Grille> sucesseurs(){
        ArrayList<Grille> s= new ArrayList<>();

        int ligne0 = grille.getLigne0();
        int colonne0 =grille.getColonne0();
        int posFin= grille.getTaille()-1;

        //aller à gauche
        if(colonne0-1>=0) {
            Grille aGauche = new Grille(this.grille.copier());
            permuteCases(aGauche,0, -1);
            s.add(aGauche);
        }
        //aller à droite
        if(colonne0+1<=posFin){
            Grille aDroite = new Grille(this.grille.copier());
            permuteCases( aDroite,0,1);
            s.add(aDroite);

        }
        // aller en haut
        if(ligne0-1>=0){
            Grille enHaut = new Grille(this.grille.copier());
            permuteCases(enHaut,-1,0);
            s.add(enHaut);

        }
        //aller en bas
        if(ligne0+1<=posFin){
            Grille enBas = new Grille(this.grille.copier());
            permuteCases( enBas,1,0);
            s.add(enBas);

        }
        return s;
    }
}
