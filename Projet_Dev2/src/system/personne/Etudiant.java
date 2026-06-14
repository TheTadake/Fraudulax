package system.personne;

/**
 * @brief Classe représentant un étudiant héritant de la class Personne.
 */
public class Etudiant extends Personne{
    private Cursus cursus;

    /**
     * @brief Constructeur de la classe Etudiant
     * @param num
     * @param nom
     * @param prenom
     * @param cursus
     */
    public Etudiant(String num, String nom, String prenom, Cursus cursus) {
        super(num, nom, prenom);
        this.cursus = cursus;
    }

    public Cursus getCursus() {return cursus;}
    public void setCursus(Cursus cursus) {this.cursus = cursus;}


}
