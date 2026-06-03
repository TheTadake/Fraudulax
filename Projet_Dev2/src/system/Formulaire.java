package system;

import system.fraude.Fraude;
import system.personne.Etudiant;
import system.epreuve.Epreuve;

import java.util.*;

public class Formulaire {
    private Fraude[] fraudes;
    private Etudiant[] etudiants;
    private Epreuve[] epreuves;

    public Formulaire(Fraude[] fraudes, Etudiant[] etudiants, Epreuve[] epreuves) {
        if (fraudes.length != etudiants.length || fraudes.length != epreuves.length) {
            throw new IllegalArgumentException("Les tableaux fraudes, etudiants et epreuves doivent avoir la même taille.");
        }
        this.fraudes = fraudes;
        this.etudiants = etudiants;
        this.epreuves = epreuves;
    }

    public Fraude[] getFraudes() {
        return fraudes;
    }

    public void setFraudes(Fraude[] fraudes) {
        this.fraudes = fraudes;
    }

    public Etudiant[] getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiant[] etudiants) {
        this.etudiants = etudiants;
    }

    public Epreuve[] getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(Epreuve[] epreuves) {
        this.epreuves = epreuves;
    }

    public void rechercheFiltre() {

    }


    public String statisques() {
        return "";
    }

    /**
     * La fonction graphique() construit un graphe non-orienté de plagiat.
     * Deux étudiants sont reliés s'ils ont fraudé lors de la même épreuve :
     * même codeECUE + même datePassage
     */
    public String graphique() {
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
        sb.append("=== Graphe de plagiat ===\n\n");

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