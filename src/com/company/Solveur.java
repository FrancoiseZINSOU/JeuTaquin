package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Solveur {
    private ArrayList<Noeud> liste_noeuds_ouverts;
    private ArrayList<Noeud> liste_noeuds_fermes;

    public Solveur(){
        this.liste_noeuds_ouverts= new ArrayList<>();
        this.liste_noeuds_fermes=new ArrayList<Noeud>();

    }

    public Grille chargerFichier(String nomFichier) throws IOException {
        int[][] array = new int[0][];
        int compteurLigne = 0, taille = 0, compteurColonne = 0;
        for (String ligne : Files.readAllLines(Paths.get(nomFichier))) {
            for (String chaine : ligne.split(" +")) {
                if (compteurLigne == 0) {
                    taille = Integer.parseInt(chaine);
                    array = new int[taille][taille];
                } else {
                    array[compteurLigne - 1][compteurColonne - taille * (compteurLigne - 1)] = Integer.parseInt(chaine);
                    compteurColonne++;
                }
            }
            compteurLigne++;
        }
        return new Grille(array);
    }

    public Noeud algoAstar(Grille initial) {
        Noeud noeud_init = new Noeud(initial, null, 0);

        int minVal = Integer.MAX_VALUE, indiceMin = 0;
        liste_noeuds_ouverts.add(noeud_init);
        int nIdentique;

        if (noeud_init.estUnEtatFinal()) {
            return noeud_init;
        } else {
            while (!liste_noeuds_ouverts.isEmpty()) {

                for (int i = 0; i < liste_noeuds_ouverts.size(); i++) {
                    if (liste_noeuds_ouverts.get(i).f() < minVal) {
                        minVal = liste_noeuds_ouverts.get(i).f();
                        indiceMin = i;
                    }
                }

                Noeud noeudCourant = liste_noeuds_ouverts.get(indiceMin);
                liste_noeuds_fermes.add(noeudCourant);
                liste_noeuds_ouverts.remove(indiceMin);
                ArrayList<Grille> s;
                s = noeudCourant.sucesseurs();


                ArrayList<Noeud> noeudS = new ArrayList<>();

                for (Grille grille : s) {
                    noeudS.add(new Noeud(grille, noeudCourant, noeudCourant.g() + 1));
                }
                for (Noeud noeud : noeudS) {

                    if (noeud.estUnEtatFinal()) {
                        return noeud;
                    } else {
                        if (!estDansListeFermee(noeud)) {

                            nIdentique = estDansListeOuverte(noeud);
                            if (nIdentique == -1) {
                                liste_noeuds_ouverts.add(noeud);
                            } else {
                                Noeud n = liste_noeuds_ouverts.get(nIdentique);
                                if (noeud.f() < n.f()) {
                                    liste_noeuds_ouverts.set(nIdentique, noeud);
                                }
                            }
                        }
                    }

                }
            }

            return null;
        }
    }


    public boolean estDansListeFermee(Noeud n){
        boolean etat=false;
        for (Noeud liste_noeuds_ferme : liste_noeuds_fermes) {
            if (Objects.equals(n.getGrille(), liste_noeuds_ferme.getGrille())) {

                etat = true;
                break;
            }
        }
        return  etat;
    }
    public int estDansListeOuverte(Noeud n){
        int etat=-1;
        for(int i=0;i<liste_noeuds_ouverts.size();i++){
            if(Objects.equals(n.getGrille(),liste_noeuds_ouverts.get(i).getGrille())){
                etat=i;

                break;
            }


        }
        return etat;
    }

    public ArrayList<Grille> cheminComplet(Noeud noeudFinal){
        ArrayList<Grille> cheminComplet = new ArrayList<Grille>();
        Noeud courant=noeudFinal;
        while (courant!=null){
            cheminComplet.add(courant.getGrille());
            courant=courant.getPere();
        }
        System.out.println(cheminComplet.size()+" mouvements");
        return cheminComplet;
    }
}
