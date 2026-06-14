package system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.epreuve.Epreuve;
import system.epreuve.Modalite;
import system.fraude.Fraude;
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
                new Fraude(LocalDate.of(2026,01,19), "Copie de cours", "Etudiant a été trouvé avec une copie de cours pendant l'épreuve"),
                new Fraude(LocalDate.of(2026,02,27), "Triche électronique", "Etudiant a été trouvé avec un appareil électronique non autorisé pendant l'épreuve")
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
                new Epreuve("ECUE125", null, null, Modalite.TP, 180, null, surveillants),

        };
    }

    @Test
    public void testConstructeur() {
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        assertArrayEquals(fraudes, formulaire.getFraudes(), "Les fraudes doivent être égales à celles fournies dans le constructeur");
        assertArrayEquals(etudiants, formulaire.getEtudiants(), "Les étudiants doivent être égaux à ceux fournis dans le constructeur");
        assertArrayEquals(epreuves, formulaire.getEpreuves(), "Les épreuves doivent être égales à celles fournies dans le constructeur");
    }

    @Test
    public void testRechercheFiltre(){
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String resultat = formulaire.rechercheFiltre("Maréchal", null, null);
        assertTrue(resultat.contains("Maréchal"), "Le résultat doit contenir le nom de l'étudiant recherché");
        assertFalse(resultat.contains("Durand"), "Le résultat ne doit pas contenir le nom de l'étudiant non recherché");
    }
    
    @Test
    public void testStatistiques(){
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String statistiques = formulaire.statistiques();
        assertTrue(statistiques.contains("Nombre total de fraudes : 2"), "Le résultat doit contenir le nombre total de fraudes");
        assertTrue(statistiques.contains("Nombre total d'étudiants : 2"), "Le résultat doit contenir le nombre total d'étudiants");
        assertTrue(statistiques.contains("Nombre total d'épreuves : 1"), "Le résultat doit contenir le nombre total d'épreuves");
    }

    @Test
    public void testGraphe(){
        Formulaire formulaire = new Formulaire(fraudes, etudiants, epreuves);
        String graphe = formulaire.graphe();
        assertTrue(graphe.contains("Graphe des fraudes"), "Le résultat doit contenir le titre du graphe");
        assertTrue(graphe.contains("Etudiant : Maréchal"), "Le résultat doit contenir le nom de l'étudiant impliqué dans la fraude");
        assertTrue(graphe.contains("Etudiant : Durand"), "Le résultat doit contenir le nom de l'étudiant impliqué dans la fraude");
    }
}
