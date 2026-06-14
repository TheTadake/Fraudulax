package system.epreuve;

import system.personne.Professeur;
import system.personne.Surveillant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @brief Classe représentant une épreuve d'examen.
 * @details Cette classe contient les informations relatives à une épreuve, telles que le code de l'ECUE,
 * la date et l'heure de passage, la modalité, la durée, le professeur responsable et les surveillants assignés.
 */
public class Epreuve {
    private String codeECUE;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Modalite modalite;
    private int duree; // en minutes
    private Professeur professeur;
    private Surveillant[] surveillants;

    /**
     * @brief Constructeur de la classe Epreuve.
     * @param codeECUE Le code de l'ECUE associé à l'épreuve.
     * @param datePassage La date de passage de l'épreuve.
     * @param heurePassage L'heure de passage de l'épreuve.
     * @param modalite La modalité de l'épreuve (écrit, oral, etc.).
     * @param duree La durée de l'épreuve en minutes.
     * @param professeur Le professeur responsable de l'épreuve.
     * @param surveillants Les surveillants assignés à l'épreuve.
     */
    public Epreuve(String codeECUE, LocalDate datePassage, LocalTime heurePassage, Modalite modalite, int duree, Professeur professeur, Surveillant[] surveillants) {
        this.codeECUE = codeECUE;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.modalite = modalite;
        this.duree = duree;
        this.professeur = professeur;
        this.surveillants = surveillants;
    }
    public String getCodeECUE() { return  codeECUE; }
    public void  setCodeECUE(String codeECUE) { this.codeECUE = codeECUE; }

    public LocalDate getDatePassage() { return datePassage; }
    public void setDatePassage(LocalDate datePassage) { this.datePassage = datePassage; }

    public LocalTime getHeurePassage() { return heurePassage; }
    public void setHeurePassage(LocalTime heurePassage) { this.heurePassage = heurePassage; }

    public Modalite getModalite() {return modalite;}
    public void setModalite(Modalite modalite) {this.modalite = modalite;}


    public int getDuree() {return duree;}
    public void setDuree(int duree) {this.duree = duree;}

    public Professeur getProfesseur() {return professeur;}
    public void setProfesseur(Professeur professeur) {this.professeur = professeur;}

    public Surveillant[] getSurveillants() {return surveillants;}
    public void setSurveillants(Surveillant[] surveillants) {this.surveillants = surveillants;}

    /**
     * @brief Méthode permettant d'ajouter un surveillant à l'épreuve, sans avoir à connaître les anciens.
     * @param newSurveillant Le surveillant à ajouter à l'épreuve.
     */
    public void addSurveillant (Surveillant newSurveillant) {
        if (surveillants == null) {
            surveillants = new Surveillant[]{newSurveillant};
        } else {
            Surveillant[] updatedSurveillants = new Surveillant[surveillants.length + 1];
            for (int i = 0; i < surveillants.length; i++) {
                updatedSurveillants[i] = surveillants[i];
            }
            updatedSurveillants[surveillants.length] = newSurveillant;
            surveillants = updatedSurveillants;
        }
    }

    @Override
    public String toString() {
        String surveillantList = "";
        if (surveillants != null) {
            for (int i = 0; i < surveillants.length; i++) {
                surveillantList += surveillants[i].getNom() + " " + surveillants[i].getPrenom() + "\n";

            }
        }else{
            surveillantList = "Aucun surveillant assigné.\n";
        }
        return "Epreuve : " +
                "codeECUE : " + codeECUE + '\n' +
                "datePassage : " + datePassage + '\n' +
                "heurePassage : " + heurePassage +'\n' +
                "modalite : " + modalite +'\n' +
                "duree : " + duree +'\n' +
                "professeur : " + professeur.getNom() + " " + professeur.getPrenom() +'\n' +
                "surveillants : " +surveillantList;


    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Epreuve epreuve = (Epreuve) o;
        return duree == epreuve.duree && Objects.equals(codeECUE, epreuve.codeECUE) && Objects.equals(datePassage, epreuve.datePassage) && Objects.equals(heurePassage, epreuve.heurePassage) && modalite == epreuve.modalite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeECUE, datePassage, heurePassage, modalite, duree);
    }
}
