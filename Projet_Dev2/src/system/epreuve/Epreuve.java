package system.epreuve;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Epreuve {
    private String codeECUE;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Modalite modalite;
    private int duree; // en minutes

    public Epreuve(String codeECUE, LocalDate datePassage, LocalTime heurePassage, Modalite modalite, int duree) {
        this.codeECUE = codeECUE;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.modalite = modalite;
        this.duree = duree;
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

    @Override
    public String toString() {
        return "Epreuve{" +
                "codeECUE='" + codeECUE + '\'' +
                ", datePassage=" + datePassage +
                ", heurePassage=" + heurePassage +
                ", modalite=" + modalite +
                ", duree=" + duree +
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
