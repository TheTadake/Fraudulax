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

    public String getNum() {return num;}
    public   void setNum(String num) {this.num = num;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
}

