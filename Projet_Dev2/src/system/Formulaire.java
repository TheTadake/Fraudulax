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
    public String statisques( Formulaire[] formulaire){
        int nbFormulaire = formulaire.length;
        int nbEtudiantUnique = 0;
        int nbFraudes = 0;
        int moyFraudeByFormulaire = 0;
        int ecartType = 0;

        for (int i = 0; i < nbFormulaire; i++){
            // Calcul du nombre total de fraudes
            nbFraudes += formulaire[i].getFraudes().length;

           
        }
        // Calcul de la moyenne de fraudes par formulaire
        moyFraudeByFormulaire = nbFraudes / nbFormulaire;
       return "";
    }
    public String graphique(){
        return "";
    }


}
