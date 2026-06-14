package system.epreuve;

import system.personne.Surveillant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEpreuve {
    // Test constructeur
    private Epreuve epreuve;
    private Surveillant[] surveillants;

    @BeforeEach
    public void setUp(){
        surveillants = new Surveillant[] {
                new Surveillant("1", "Jean", "Mark"),
                new Surveillant("2", "Marie", "Anne")
        };
        epreuve = new Epreuve("ECUE123", null, null, Modalite.Oral, 120, null, surveillants);
    }

    @Test
    public void testConstructeur() {
        assertEquals("ECUE123", epreuve.getCodeECUE(), "Le code ECUE est incorrect, il doit valoir ECUE123");
        assertNull(epreuve.getDatePassage(), "La date de passage doit être null");
        assertNull(epreuve.getHeurePassage(), "L'heure de passage doit être null");
        assertEquals(Modalite.Oral, epreuve.getModalite(), "La modalité doit être : Oral");
        assertEquals(120, epreuve.getDuree(), "La durée est incorrecte, elle doit valoir 120 minutes");
        assertNull(epreuve.getProfesseur(), "Le professeur doit être null");
        assertArrayEquals(surveillants, epreuve.getSurveillants(), "Les surveillants sont incorrects, ils doivent correspondre à ceux fournis dans le constructeur");
    }

    // Test de la méthode addSurveillant
    @Test
    public void testAddSurveillant() {
        Surveillant newSurveillant = new Surveillant("3", "Patrick", "Dupont");
        epreuve.addSurveillant(newSurveillant);
        Surveillant[] attendusSurveillants = new Surveillant[]{
                new Surveillant("1", "Jean", "Mark"),
                new Surveillant("2", "Marie", "Anne"),
                new Surveillant("3", "Patrick", "Dupont")
        };
        assertArrayEquals(attendusSurveillants, epreuve.getSurveillants(), "Les surveillants doivent être égal aux attendusSurveillants");
    }
}
