package system;

import system.fraude.Fraude;
import system.personne.Etudiant;
import system.epreuve.Epreuve;

public class Formulaire {
    private Fraude[] fraudes;
    private Etudiant[] etudiants;
    private Epreuve[] epreuves;

    public Formulaire(Fraude[] fraudes, Etudiant[] etudiants, Epreuve[] epreuves) {
        this.fraudes = fraudes;
        this.etudiants = etudiants;
        this.epreuves = epreuves;
    }

    public Fraude[] getFraudes() { return fraudes;}
    public void setFraudes(Fraude[] fraudes) { this.fraudes = fraudes;}

    public Etudiant[] getEtudiants(){ return etudiants;}
    public void setEtudiants(Etudiant[] etudiants){ this.etudiants = etudiants;}

    public Epreuve[] getEpreuves() { return epreuves;}
    public void setEpreuves(Epreuve[] epreuves) { this.epreuves = epreuves;}

    public void rechercheFiltre(){

    }

    public String statistiques( Formulaire[] formulaire){
        int nbFormulaire = formulaire.length;
        int nbEtudiantUnique = 0;
        int nbFraudes = 0;
        int moyFraudeByFormulaire = 0;
        double ecartType = 0;
        int nbEtu = 0;
        for(int i = 0; i < nbFormulaire; i++){
            nbEtu += formulaire[i].getEtudiants().length;
        }
        Etudiant[] listeEtuUnique =new Etudiant[nbEtu] ;

        for (int i = 0; i < nbFormulaire; i++){
            // Calcul du nombre total de fraudes
            nbFraudes += formulaire[i].getFraudes().length;


            // Ajout des étudiants uniques à la liste
            for (int k = 0; k < formulaire[i].getEtudiants().length; k++){
                boolean isUnique = true;
                for (int j = 0; j < nbEtudiantUnique; j++){
                    if (formulaire[i].getEtudiants()[k].equals(listeEtuUnique[j])){
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique){
                    listeEtuUnique[nbEtudiantUnique] = formulaire[i].getEtudiants()[k];
                    nbEtudiantUnique++;
                }

            }
        }
        // Calcul de la moyenne de fraudes par formulaire
        moyFraudeByFormulaire = nbFraudes / nbFormulaire;

        // Calcul de l'écart type
        int[] fraudesParFormulaire = new int[nbFormulaire];
        for (int i = 0; i < nbFormulaire; i++){
            fraudesParFormulaire[i] = formulaire[i].getFraudes().length;
        }

        double sommeCarres = 0;
        for (int i = 0; i < nbFormulaire; i++){
            sommeCarres += Math.pow(fraudesParFormulaire[i] - moyFraudeByFormulaire, 2);
        }
        ecartType = Math.sqrt(sommeCarres / nbFormulaire);

       return "Comptage des formulaires : " + nbFormulaire + "\n" +
              "Nombre d'étudiants uniques : " + nbEtudiantUnique + "\n" +
              "Nombre total de fraudes : " + nbFraudes + "\n" +
              "Moyenne de fraudes par formulaire : " + moyFraudeByFormulaire + "\n" +
              "Écart type du nombre de fraudes par formulaire : " + ecartType;
    }


    
    public String graphique(){
        return "";
    }


}
