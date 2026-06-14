import system.Menu;
import system.epreuve.Epreuve;
import system.personne.Cursus;
import system.personne.Etudiant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import system.epreuve.Modalite;
import system.personne.Professeur;
import system.personne.Surveillant;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Etudiant[] etudiants = new Etudiant[10]  ;
        Etudiant etu= new Etudiant("000", "Doe", "John", Cursus.E1);
        Etudiant etu1= new Etudiant("001", "Smith", "Jane", Cursus.E2);
        Etudiant etu2 = new Etudiant("002", "Brown", "Charlie", Cursus.E3);
        Etudiant etu3 = new Etudiant("003", "Johnson", "Emily", Cursus.E4);
        etudiants[0] = etu;
        etudiants[1] = etu1;
        etudiants[2] = etu2;
        etudiants[3] = etu3;

        Professeur prof = new Professeur("P001", "Smith ", "Alice");
        Professeur prof1 = new Professeur("P002", "Brown ", "Charlie");

        Surveillant[] listeSur = new Surveillant[10];
        Surveillant surve = new Surveillant("S001", "Johnson ", "Emily");
        Surveillant surve1 = new Surveillant("S002", "Davis ", "Michael");
        listeSur[0] = surve;
        listeSur[1] = surve1;

        Epreuve[] epreuves= new Epreuve[10];
        Epreuve ep = new Epreuve("Cocinum", LocalDate.of(2026,6,10), LocalTime.of(9,0),Modalite.Oral , 30, prof, listeSur);
        Epreuve ep1 = new Epreuve("DEEP", LocalDate.of(2026,4,10), LocalTime.of(11,0),Modalite.Ecrit , 120, prof1, listeSur);
        epreuves[0] = ep;
        epreuves[1] = ep1;

        Menu menu = new Menu(epreuves, etudiants);
        menu.afficherMenuPrincipal();


    }
}