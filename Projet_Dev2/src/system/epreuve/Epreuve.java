package system.epreuve;

import system.personne.Professeur;
import system.personne.Surveillant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Epreuve {
    private String codeECUE;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Modalite modalite;
    private int duree; // en minutes
    private Professeur professeur;
    private Surveillant[] surveillants;

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
        return "Epreuve : " +
                "codeECUE='" + codeECUE + '\n' +
                "datePassage=" + datePassage + '\n' +
                "heurePassage=" + heurePassage +'\n' +
                "modalite=" + modalite +'\n' +
                "duree=" + duree +'\n' +
                "professeur=" + professeur.getNom() + " " + professeur.getPrenom() +'\n' +

                '}';
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
