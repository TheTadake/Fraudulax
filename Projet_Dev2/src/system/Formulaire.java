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

    /**
     * Filtre les étudiants selon nom, prénom et/ou numéro.
     * Un critère null ou vide est ignoré.
     */
    public String rechercheFiltre(String nom, String prenom, String numero) {
        StringBuilder sb = new StringBuilder();
        boolean etudiantTrouve = false;

        sb.append(String.format("%-15s %-15s %-10s %-10s%n", "Nom", "Prénom", "Numéro", "Cursus"));
        sb.append("-".repeat(50)).append("\n");

        for (Etudiant e : this.etudiants) {
            boolean matchNom = (nom == null || nom.isBlank()) || e.getNom().equalsIgnoreCase(nom.trim());
            boolean matchPrenom = (prenom == null || prenom.isBlank()) || e.getPrenom().equalsIgnoreCase(prenom.trim());
            boolean matchNum = (numero == null || numero.isBlank()) || e.getNum().equalsIgnoreCase(numero.trim());

            if (matchNom && matchPrenom && matchNum) {
                sb.append(String.format("%-15s %-15s %-10s %-10s%n", e.getNom(), e.getPrenom(), e.getNum(), e.getCursus()));
                etudiantTrouve = true;
            }
        }

        if (!etudiantTrouve) return "Aucun étudiant trouvé pour les critères donnés.\n";
        return sb.toString();
    }

    public String statisques(){
       return "";
    }
    public String graphique(){
        return "";
    }


}
