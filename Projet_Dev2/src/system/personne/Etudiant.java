package system.personne;

public class Etudiant extends Personne{
    private Cursus cursus;

    public Etudiant(String num, String nom, String prenom, Cursus cursus) {
        super(num, nom, prenom);
        this.cursus = cursus;
    }

    public Cursus getCursus() {return cursus;}
    public void setCursus(Cursus cursus) {this.cursus = cursus;}
}
