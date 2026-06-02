package system.personne;

public abstract class Personne {
    private  String num;
    private  String nom;
    private String prenom;

    protected Personne(String num, String nom, String prenom) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
    }

    protected String getNum() {return num;}
    protected   void setNum(String num) {this.num = num;}

    protected String getNom() {return nom;}
    protected void setNom(String nom) {this.nom = nom;}

    protected String getPrenom() {return prenom;}
    protected void setPrenom(String prenom) {this.prenom = prenom;}
}

