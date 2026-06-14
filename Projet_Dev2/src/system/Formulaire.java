package system;

import system.fraude.Fraude;
import system.personne.Etudiant;
import system.epreuve.Epreuve;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * @brief class représentant un formulaire de fraude contenant une liste d'épreuves, d'étudiants et de fraudes ainsi qu'un horodatage .
 *
 */
public class Formulaire {
    private Fraude[] fraudes;
    private Etudiant[] etudiants;
    private Epreuve[] epreuves;
    private LocalDate creationDate;
    private LocalDate modificationDate = null;
    private LocalTime creationTime;
    private LocalTime modificationTime= null;

    /**
     * @brief Constructeur de la classe Formulaire
     * @param fraudes
     * @param etudiants
     * @param epreuves
     */
    public Formulaire(Fraude[] fraudes, Etudiant[] etudiants, Epreuve[] epreuves) {
        if (fraudes == null || etudiants == null || epreuves == null) {
            throw new IllegalArgumentException("Les tableaux fraudes, etudiants et epreuves ne peuvent pas être null.");
        }
        this.fraudes = fraudes;
        this.etudiants = etudiants;
        this.epreuves = epreuves;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now().withNano(0);
    }

    public Fraude[] getFraudes() {
        return fraudes;
    }

    public void setFraudes(Fraude[] fraudes) {
        this.fraudes = fraudes;
        setModificationDate(LocalDate.now());
        setModificationTime(LocalTime.now());
    }

    public Etudiant[] getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiant[] etudiants) {
        this.etudiants = etudiants;
        setModificationDate(LocalDate.now());
        setModificationTime(LocalTime.now());

    }
  
    public Epreuve[] getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(Epreuve[] epreuves) {
        this.epreuves = epreuves;
        setModificationDate(LocalDate.now());
        setModificationTime(LocalTime.now());
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;


    }
    public LocalTime getCreationTime() {
        return creationTime;

    }
    public void setCreationTime(LocalTime creationTime) {
        this.creationTime = creationTime.withNano(0);

    }
    public LocalDate getModificationDate() {
        return modificationDate;
    }
    public  void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }
    public LocalTime getModificationTime() {
        return modificationTime;
    }
    public void setModificationTime(LocalTime modificationTime) {
        this.modificationTime = modificationTime.withNano(0);
    }


    /**
     * @briefFiltre les étudiants selon nom, prénom et/ou numéro.
     * Un critère null ou vide est ignoré.
     * @param nom Le nom de l'étudiant à rechercher (peut être null ou vide)
     * @param prenom Le prénom de l'étudiant à rechercher (peut être null ou vide)
     * @param numero Le numéro de l'étudiant à rechercher (peut être null ou vide)
     * @return Une chaîne de caractères représentant les étudiants filtrés, ou un message indiquant qu'aucun étudiant n'a été trouvé.
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

    /**
     * @brief Cette méthode calcule et retourne des statistiques sur un tableau de formulaires.
     * @param formulaire Liste de formulaire
     * @return Une chaîne de caractères contenant les statistiques calculées, incluant le nombre de formulaires, le nombre d'étudiants uniques, le nombre total de fraudes, la moyenne de fraudes par formulaire et l'écart type du nombre de fraudes par formulaire.
     */
    public String statisques( Formulaire[] formulaire){
        int nbFormulaire = formulaire.length;
        int nbEtudiantUnique = 0;
        int nbFraudes = 0;
        int moyFraudeByFormulaire = 0;
        double ecartType = 0;
        int nbEtu = 0;
        for(int i = 0; i < nbFormulaire; i++){
            nbEtu += formulaire[i].getEtudiants().length;
        }
        Etudiant[] listeEtuUnique =new Etudiant[nbEtu] ;

        for (int i = 0; i < nbFormulaire; i++){
            // Calcul du nombre total de fraudes
            nbFraudes += formulaire[i].getFraudes().length;


            // Ajout des étudiants uniques à la liste
            for (int k = 0; k < formulaire[i].getEtudiants().length; k++){
                boolean isUnique = true;
                for (int j = 0; j < nbEtudiantUnique; j++){
                    if (formulaire[i].getEtudiants()[k].equals(listeEtuUnique[j])){
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique){
                    listeEtuUnique[nbEtudiantUnique] = formulaire[i].getEtudiants()[k];
                    nbEtudiantUnique++;
                }

            }
        }
        // Calcul de la moyenne de fraudes par formulaire
        moyFraudeByFormulaire = nbFraudes / nbFormulaire;

        // Calcul de l'écart type
        int[] fraudesParFormulaire = new int[nbFormulaire];
        for (int i = 0; i < nbFormulaire; i++){
            fraudesParFormulaire[i] = formulaire[i].getFraudes().length;
        }

        double sommeCarres = 0;
        for (int i = 0; i < nbFormulaire; i++){
            sommeCarres += Math.pow(fraudesParFormulaire[i] - moyFraudeByFormulaire, 2);
        }
        ecartType = Math.sqrt(sommeCarres / nbFormulaire);

       return "Comptage des formulaires : " + nbFormulaire + "\n" +
              "Nombre d'étudiants uniques : " + nbEtudiantUnique + "\n" +
              "Nombre total de fraudes : " + nbFraudes + "\n" +
              "Moyenne de fraudes par formulaire : " + moyFraudeByFormulaire + "\n" +
              "Écart type du nombre de fraudes par formulaire : " + ecartType;
    }


    /**
     * @biref La fonction graphique() construit un graphe non-orienté de plagiat. Deux étudiants sont reliés s'ils ont fraudé lors de la même épreuve :
     * même codeECUE + même datePassage
     * @return Une chaîne de caractères représentant le graphe de plagiat, avec chaque étudiant et ses voisins (étudiants liés par plagiat) affichés de manière hiérarchique. Si aucune relation de plagiat n'est détectée, un message approprié est retourné.
     *
     */
    public String graphe() {
        int n = this.etudiants.length; // Avec n le nombre d'entrées dans le formulaire
        Map<Etudiant, Set<Etudiant>> relation = new LinkedHashMap<>(); // Conserver l'ordre d'insertion à l'affichage

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean memeEpreuve = this.epreuves[i].getCodeECUE().equals(this.epreuves[j].getCodeECUE())
                        && this.epreuves[i].getDatePassage().equals(this.epreuves[j].getDatePassage());

                if (memeEpreuve) {
                    Etudiant a = this.etudiants[i];
                    Etudiant b = this.etudiants[j];
                    if (!relation.containsKey(a)) relation.put(a, new LinkedHashSet<>());
                    relation.get(a).add(b);
                    if (!relation.containsKey(b)) relation.put(b, new LinkedHashSet<>());
                    relation.get(b).add(a);
                }
            }
        }

        if (relation.isEmpty()) return "Aucune relation de plagiat détectée.\n";

        StringBuilder sb = new StringBuilder();
        sb.append("Graphe de plagiat\n\n");

        for (Map.Entry<Etudiant, Set<Etudiant>> entry : relation.entrySet()) {
            Etudiant e = entry.getKey(); // Affichage du noeud principal
            sb.append(e.getNom()).append(" ").append(e.getPrenom()).append(" (").append(e.getNum()).append(")\n");
            // Affichage des voisins
            for (Etudiant voisin : entry.getValue()) {
                sb.append("  |____ ")
                        .append(voisin.getNom()).append(" ").append(voisin.getPrenom()).append(" (").append(voisin.getNum()).append(")\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}