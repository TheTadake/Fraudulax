package system.personne;

import java.util.Objects;

public abstract class Personne {
    private  String num;
    private  String nom;
    private String prenom;

    protected Personne(String num, String nom, String prenom) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNum() {return num;}
    public   void setNum(String num) {this.num = num;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(num, personne.num) && Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, nom, prenom);
    }

    
}

