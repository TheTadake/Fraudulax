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



}
