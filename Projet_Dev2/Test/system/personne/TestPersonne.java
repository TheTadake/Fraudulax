package system.personne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.epreuve.Epreuve;
import system.epreuve.Modalite;

import static org.junit.jupiter.api.Assertions.*;

public class TestPersonne {
    // Test constructeur Etudiant
    private Etudiant etudiant;

    @BeforeEach
    public void setUpEtudiant(){
        etudiant = new Etudiant("001", "Ulky", "Lilâ", Cursus.E4);
    }

    @Test
    public void testConstructeurEtudiant(){
        assertEquals("001", etudiant.getNum(), "Le numéro de l'étudiant est incorrect, il doit valoir : 001");
        assertEquals("Ulky", etudiant.getNom(), "Le nom de l'étudiant est incorrect, il doit valoir : Ulky");
        assertEquals("Lilâ", etudiant.getPrenom(), "Le prénom de l'étudiant est incorrect, il doit valoir : Lilâ");
        assertEquals(Cursus.E4, etudiant.getCursus(), "Le cursus de l'étudiant est incorrect, il doit valoir : E4");
    }

    // Test constructeur Professeur
    private Professeur professeur;

    @BeforeEach
    public void setUpProfesseur(){
        professeur = new Professeur("007", "Richard", "Bob");
    }

    @Test
    public void testConstructeurProfesseur(){
        assertEquals("007", professeur.getNum(), "Le numéro du professeur est incorrect, il doit valoir : 007");
        assertEquals("Richard", professeur.getNom(), "Le nom du professeur est incorrect, il doit valoir : Richard");
        assertEquals("Bob", professeur.getPrenom(), "Le prénom du professeur est incorrect, il doit valoir : Bob");
    }

    // Test constructeur Surveillant
    private Surveillant surveillant;

    @BeforeEach
    public void setUpSurveillant(){
        surveillant = new Surveillant("37", "Lopez", "Louis");
    }

    @Test
    public void testConstructeurSurveillant(){
        assertEquals("37", surveillant.getNum(), "Le numéro du surveillant est incorrect, il doit valoir : 37");
        assertEquals("Lopez", surveillant.getNom(), "Le nom du surveillant est incorrect, il doit valoir : Lopez");
        assertEquals("Louis", surveillant.getPrenom(), "Le prénom du surveillant est incorrect, il doit valoir : Louis");
    }
}
