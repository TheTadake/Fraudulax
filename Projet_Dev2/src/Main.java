import system.Menu;
import system.Formulaire;
import system.epreuve.Epreuve;
import system.personne.Cursus;
import system.personne.Etudiant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import system.epreuve.Modalite;
import system.fraude.*;
import system.personne.Professeur;
import system.personne.Surveillant;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        // === CRÉATION DES ÉTUDIANTS (4 cursus différents) ===
        Etudiant[] etudiants = new Etudiant[10];
        Etudiant etu = new Etudiant("000", "Doe", "John", Cursus.E1);
        Etudiant etu1 = new Etudiant("001", "Smith", "Jane", Cursus.E2);
        Etudiant etu2 = new Etudiant("002", "Brown", "Charlie", Cursus.E3);
        Etudiant etu3 = new Etudiant("003", "Johnson", "Emily", Cursus.E4);
        Etudiant etu4 = new Etudiant("004", "Williams", "Michael", Cursus.E5);
        etudiants[0] = etu;
        etudiants[1] = etu1;
        etudiants[2] = etu2;
        etudiants[3] = etu3;
        etudiants[4] = etu4;

        // === CRÉATION DES PROFESSEURS ===
        Professeur prof = new Professeur("P001", "Smith", "Alice");
        Professeur prof1 = new Professeur("P002", "Brown", "Charlie");

        // === CRÉATION DES SURVEILLANTS ===
        Surveillant[] listeSur = new Surveillant[10];
        Surveillant surve = new Surveillant("S001", "Johnson", "Emily");
        Surveillant surve1 = new Surveillant("S002", "Davis", "Michael");
        listeSur[0] = surve;
        listeSur[1] = surve1;

        // === CRÉATION DES ÉPREUVES (2 modalités différentes) ===
        Epreuve[] epreuves = new Epreuve[10];
        Epreuve ep = new Epreuve("Cocinum", LocalDate.of(2026, 6, 10), LocalTime.of(9, 0), Modalite.Oral, 30, prof, listeSur);
        Epreuve ep1 = new Epreuve("DEEP", LocalDate.of(2026, 4, 10), LocalTime.of(11, 0), Modalite.Ecrit, 120, prof1, listeSur);
        epreuves[0] = ep;
        epreuves[1] = ep1;

        // === CRÉATION DU MENU ET INITIALISATION AVEC DONNÉES DE TEST ===
        Menu menu = new Menu(epreuves, etudiants);

        // === FORMULAIRE 1: Fraude Calculatrice et Fraude Papier (ep - Cocinum) ===
        Fraude[] fraudes1 = {
                new FraudeCalculatrice(LocalDate.of(2026, 6, 10), "Utilisation de calculatrice", "Fraude détectée pendant l'examen"),
                new FraudePapier(LocalDate.of(2026, 6, 10), "Anti-sèche trouvée", "Pense-bête non autorisé glissé sous la table")
        };
        Etudiant[] etudiants1 = { etu, etu1 };
        Epreuve[] epreuves1 = { ep, ep };
        Formulaire formulaire1 = new Formulaire(fraudes1, etudiants1, epreuves1);
        menu.getFormulaires()[0] = formulaire1;
        menu.setNbFormulaires(1);

        // === FORMULAIRE 2: Fraude IAG et Fraude IAG Connectée (ep - Cocinum) ===
        // Ces fraudes créent des liens de plagiat car même épreuve (Cocinum, 10/06/2026)
        Fraude[] fraudes2 = {
                new FraudeIAG(LocalDate.of(2026, 6, 10), "Utilisation de ChatGPT", "Réponse générée par IA généraliste"),
                new FraudeIAGConnecte(LocalDate.of(2026, 6, 10), "Copilot utilisé", "IA généraliste connectée détectée", "192.168.1.50")
        };
        Etudiant[] etudiants2 = { etu2, etu3 };
        Epreuve[] epreuves2 = { ep, ep };
        Formulaire formulaire2 = new Formulaire(fraudes2, etudiants2, epreuves2);
        menu.getFormulaires()[1] = formulaire2;

        // === FORMULAIRE 3: Autres fraudes (ep1 - DEEP) ===
        Fraude[] fraudes3 = {
                new FraudeCalculatrice(LocalDate.of(2026, 4, 10), "Calculatrice interdite utilisée", "Détection lors de la surveillance"),
                new FraudeIAGConnecte(LocalDate.of(2026, 4, 10), "Gmail utilisé avec API", "Accès à IA généraliste via internet", "10.0.0.100")
        };
        Etudiant[] etudiants3 = { etu4, etu };
        Epreuve[] epreuves3 = { ep1, ep1 };
        Formulaire formulaire3 = new Formulaire(fraudes3, etudiants3, epreuves3);
        menu.getFormulaires()[2] = formulaire3;
        menu.setNbFormulaires(3);

        menu.afficherMenuPrincipal();

    }
}