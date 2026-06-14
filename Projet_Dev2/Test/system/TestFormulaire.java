package system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.epreuve.Epreuve;
import system.epreuve.Modalite;
import system.fraude.Fraude;
import system.fraude.FraudePapier;
import system.fraude.FraudeIAG;
import system.personne.Cursus;
import system.personne.Etudiant;
import system.personne.Surveillant;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormulaire {
    // Test constructeur
    private Fraude[] fraudes;
    private Etudiant[] etudiants;
    private Epreuve[] epreuves;
    private Surveillant[] surveillants;

    @BeforeEach
    public void setUp(){
        fraudes = new Fraude[]{
                new FraudeIAG(LocalDate.of(2026, 01, 19), "Copie de cours", "Etudiant a été trouvé avec une copie de cours pendant l'épreuve"),
                new FraudeIAG(LocalDate.of(2026, 02, 27), "Triche électronique", "Etudiant a été trouvé avec un appareil électronique non autorisé pendant l'épreuve")
        };
        etudiants = new Etudiant[]{
                new Etudiant("12345","Maréchal", "Jean", Cursus.E2),
                new Etudiant("67890", "Durand", "Sulyvan", Cursus.E1)
        };
        surveillants = new Surveillant[] {
                new Surveillant("1", "Jean", "Mark"),
                new Surveillant("2", "Marie", "Anne")
        };
        epreuves = new Epreuve[]{
                new Epreuve("ECUE125", null, null, Modalite.TP, 180, null, surveillants)
        };
    }

    @Test
    public void testConstructeurValide() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        assertArrayEquals(fraudes, formulaire.getFraudes(), "Les fraudes doivent être égales à celles fournies dans le constructeur");
        assertArrayEquals(etudiants, formulaire.getEtudiants(), "Les étudiants doivent être égaux à ceux fournis dans le constructeur");
        assertArrayEquals(epreuves, formulaire.getEpreuves(), "Les épreuves doivent être égales à celles fournies dans le constructeur");
    }

    @Test
    public void testConstructeurNull() {
        assertThrows(IllegalArgumentException.class, () -> new Formulaire(null, etudiants, epreuves), "Le constructeur doit lancer une exception si le tableau de fraudes est null");
        assertThrows(IllegalArgumentException.class, () -> new Formulaire(fraudes, null, epreuves), "Le constructeur doit lancer une exception si le tableau d'étudiants est null");
        assertThrows(IllegalArgumentException.class, () -> new Formulaire(fraudes, etudiants, null), "Le constructeur doit lancer une exception si le tableau d'épreuves est null");
    }

    @Test
    public void testRechercheFiltre(){
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre("Maréchal", null, null);
        assertTrue(resultat.contains("Maréchal"), "Le résultat doit contenir le nom de l'étudiant recherché");
        assertFalse(resultat.contains("Durand"), "Le résultat ne doit pas contenir le nom de l'étudiant non recherché");
    }

    @Test
    public void testRechercheAucunResultat() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre("Dupont", null, null);
        assertEquals("Aucun étudiant trouvé pour les critères donnés.\n", resultat, "Le résultat doit indiquer qu'aucun étudiant n'a été trouvé pour les critères donnés");
    }

    @Test
    public void testRechercheParPrenom() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre(null, "Jean", null);
        assertTrue(resultat.contains("Jean"), "Le résultat doit contenir le prénom de l'étudiant recherché");
        assertFalse(resultat.contains("Sulyvan"), "Le résultat ne doit pas contenir le prénom de l'étudiant non recherché");
    }

    @Test
    public void testRechercheParNumero() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre(null, null, "12345");
        assertTrue(resultat.contains("12345") , "Le résultat doit contenir le numéro de l'étudiant recherché");
        assertFalse(resultat.contains("67890"), "Le résultat ne doit pas contenir le numéro de l'étudiant non recherché");
    }

    @Test
    public void testRechercheSansCritere() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre("", "", "");
        assertTrue(resultat.contains("Maréchal"), "Le résultat doit contenir le nom de l'étudiant Maréchal");
        assertTrue(resultat.contains("Durand"), "Le résultat doit contenir le nom de l'étudiant Durand");
    }

    @Test
    public void testStatistiques(){
        Formulaire formulaire1 = new Formulaire(fraudes, etudiants, epreuves);
        Formulaire formulaire2 = new Formulaire(fraudes, etudiants, epreuves);
        Formulaire[] liste = {formulaire1, formulaire2};
        String statistiques = Formulaire.statistiques(liste);
        assertTrue(statistiques.contains("Nombre total de fraudes : 4"), "Le résultat doit contenir le nombre total de fraudes");
        assertTrue(statistiques.contains("Nombre d'étudiants uniques : 2"), "Le résultat doit contenir le nombre total d'étudiants");
    }

    @Test
    public void testGraphe(){
        Epreuve ep1 = new Epreuve("ECUE125", LocalDate.of(2026,1,15), null, Modalite.TP, 180, null, surveillants);
        Epreuve ep2 = new Epreuve("ECUE125", LocalDate.of(2026,1,15), null, Modalite.TP, 180, null, surveillants);
        Epreuve[] epreuves = {ep1, ep2};
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String graphe = formulaire.graphe();
        assertTrue(graphe.contains("Graphe de plagiat"), "Le résultat doit contenir le titre du graphe");
        assertTrue(graphe.contains("Maréchal"), "Le résultat doit contenir le nom de l'étudiant impliqué dans la fraude");
        assertTrue(graphe.contains("Durand"), "Le résultat doit contenir le nom de l'étudiant impliqué dans la fraude");
    }

    @Test
    public void testGrapheVide() {
        Epreuve ep1 = new Epreuve("ECUE125", LocalDate.of(2026,1,1), null, Modalite.TP, 180, null, surveillants);
        Epreuve ep2 = new Epreuve("ECUE126", LocalDate.of(2026,2,1), null, Modalite.TP, 180, null, surveillants);
        Epreuve[] epreuves = {ep1, ep2};
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.graphe();
        assertEquals("Aucune relation de plagiat détectée.\n", resultat, "Le résultat doit indiquer qu'aucune relation de plagiat n'a été détectée");
    }
}
