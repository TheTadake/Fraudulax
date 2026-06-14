package system.fraude;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TestFraude {
    // Test constructeur Fraude
    private Fraude fraude;

    @BeforeEach
    public void setUpFraude(){
        fraude = new Fraude(LocalDate.of(2026,01,19), "Copie de la feuille de réponses", "Un étudiant a été surpris en train de copier la feuille de réponses d'un autre étudiant pendant une épreuve.") {};
    }

    @Test
    public void testConstructeurFraude(){
        assertEquals(LocalDate.of(2026,01,19), fraude.getDate(), "La date de la fraude est incorrecte, elle doit valoir 2026-01-19");
        assertEquals("Copie de la feuille de réponses", fraude.getContenu(), "Le contenu de la fraude est incorrect, il doit valoir 'Copie de la feuille de réponses'");
        assertEquals("Un étudiant a été surpris en train de copier la feuille de réponses d'un autre étudiant pendant une épreuve.", fraude.getDescription(), "La description de la fraude est incorrecte, elle doit valoir 'Un étudiant a été surpris en train de copier la feuille de réponses d'un autre étudiant pendant une épreuve.'");
    }

    @Test
    public void testSetDate() {
        LocalDate nouvelleDate = LocalDate.of(2027, 1, 1);
        fraude.setDate(nouvelleDate);
        assertEquals(nouvelleDate, fraude.getDate());
    }

    @Test
    public void testSetContenu() {
        String contenu = "Nouveau contenu";
        fraude.setContenu(contenu);
        assertEquals(contenu, fraude.getContenu());
    }

    @Test
    public void testSetDescription() {
        String description = "Nouvelle description";
        fraude.setDescription(description);
        assertEquals(description, fraude.getDescription());
    }

    // Test constructeur FraudeCalculatrice
    private FraudeCalculatrice fraudeCalculatrice;

    @BeforeEach
    public void setUpFraudeCalculatrice(){
        fraudeCalculatrice = new FraudeCalculatrice(LocalDate.of(2026,03,11), "Utilisation d'une calculatrice non autorisée", "Un étudiant a été surpris en train d'utiliser une calculatrice non autorisée pendant une épreuve.");
    }

    @Test
    public void testConstructeurFraudeCalculatrice(){
        assertEquals(LocalDate.of(2026,03,11), fraudeCalculatrice.getDate(), "La date de la fraude est incorrecte, elle doit valoir 2026-03-11");
        assertEquals("Utilisation d'une calculatrice non autorisée", fraudeCalculatrice.getContenu(), "Le contenu de la fraude est incorrect, il doit valoir 'Utilisation d'une calculatrice non autorisée'");
        assertEquals("Un étudiant a été surpris en train d'utiliser une calculatrice non autorisée pendant une épreuve.", fraudeCalculatrice.getDescription(), "La description de la fraude est incorrecte, elle doit valoir 'Un étudiant a été surpris en train d'utiliser une calculatrice non autorisée pendant une épreuve.'");
    }

    // Test constructeur FraudeIAG
    private FraudeIAG fraudeIAG;

    @BeforeEach
    public void setUpFraudeIAG() {
        fraudeIAG = new FraudeIAG(LocalDate.of(2026, 05, 05), "Utilisation d'une intelligence artificielle générative pour tricher", "Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative pour tricher pendant une épreuve.");
    }

    @Test
    public void testConstructeurFraudeIAG(){
        assertEquals(LocalDate.of(2026, 05, 05), fraudeIAG.getDate(), "La date de la fraude est incorrecte, elle doit valoir 2026-05-05");
        assertEquals("Utilisation d'une intelligence artificielle générative pour tricher", fraudeIAG.getContenu(), "Le contenu de la fraude est incorrect, il doit valoir 'Utilisation d'une intelligence artificielle générative pour tricher'");
        assertEquals("Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative pour tricher pendant une épreuve.", fraudeIAG.getDescription(), "La description de la fraude est incorrecte, elle doit valoir 'Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative pour tricher pendant une épreuve.'");
    }

    // Test constructeur FraudeIAGConnecte
    private FraudeIAGConnecte fraudeIAGConnecte;

    @BeforeEach
    public void setUpFraudeIAGConnecte() {
        fraudeIAGConnecte = new FraudeIAGConnecte(LocalDate.of(2026, 06, 01), "Utilisation d'une intelligence artificielle générative connectée pour tricher", "Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative connectée pour tricher pendant une épreuve.", "blabla");
    }

    @Test
    public void testConstructeurFraudeIAGConnecte(){
        assertEquals(LocalDate.of(2026, 06, 01), fraudeIAGConnecte.getDate(), "La date de la fraude est incorrecte, elle doit valoir 2026-06-01");
        assertEquals("Utilisation d'une intelligence artificielle générative connectée pour tricher", fraudeIAGConnecte.getContenu(), "Le contenu de la fraude est incorrect, il doit valoir 'Utilisation d'une intelligence artificielle générative connectée pour tricher'");
        assertEquals("Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative connectée pour tricher pendant une épreuve.", fraudeIAGConnecte.getDescription(), "La description de la fraude est incorrecte, elle doit valoir 'Un étudiant a été surpris en train d'utiliser une intelligence artificielle générative connectée pour tricher pendant une épreuve.'");
        assertEquals("blabla", fraudeIAGConnecte.getAdresseIP(), "L'adresse IP de la fraude est incorrecte, elle doit valoir 'blabla'");
    }

    @Test
    public void testSetAdresseIP() {
        fraudeIAGConnecte.setAdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", fraudeIAGConnecte.getAdresseIP());
    }

    // Test constructeur FraudePapier
    private FraudePapier fraudePapier;

    @BeforeEach
    public void setUpFraudePapier() {
        fraudePapier = new FraudePapier(LocalDate.of(2026, 04, 15), "Utilisation de notes sur papier pendant une épreuve", "Un étudiant a été surpris en train d'utiliser des notes sur papier pendant une épreuve.");
    }

    @Test
    public void testConstructeurFraudePapier(){
        assertEquals(LocalDate.of(2026, 04, 15), fraudePapier.getDate(), "La date de la fraude est incorrecte, elle doit valoir 2026-04-15");
        assertEquals("Utilisation de notes sur papier pendant une épreuve", fraudePapier.getContenu(), "Le contenu de la fraude est incorrect, il doit valoir 'Utilisation de notes sur papier pendant une épreuve'");
        assertEquals("Un étudiant a été surpris en train d'utiliser des notes sur papier pendant une épreuve.", fraudePapier.getDescription(), "La description de la fraude est incorrecte, elle doit valoir 'Un étudiant a été surpris en train d'utiliser des notes sur papier pendant une épreuve.'");
    }
}
