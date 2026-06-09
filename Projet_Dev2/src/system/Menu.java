package system;

import system.epreuve.Epreuve;
import system.epreuve.Modalite;
import system.fraude.*;
import system.personne.Cursus;
import system.personne.Etudiant;
import system.personne.Professeur;
import system.personne.Surveillant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private Formulaire[] formulaires;
    private Epreuve[] epreuves;
    private Scanner scanner;
    private int nbFormulaires;
    private int nbEpreuves;

    public Menu() {
        this.epreuves = new Epreuve[100];
        this.formulaires = new Formulaire[100];
        this.nbFormulaires = 0;
        this.nbEpreuves = 0;
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenuPrincipal() {
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Créer une épreuve");
            System.out.println("2. Ajouter un formulaire");
            System.out.println("3. Retirer un formulaire");
            System.out.println("4. Afficher les formulaires");
            System.out.println("5. Afficher les épreuves");
            System.out.println("6. Afficher les statistiques");
            System.out.println("7. Quitter");
            System.out.print("Sélectionnez une action (1-7) : ");

            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1":
                    creerEpreuve();
                    break;
                case "2":
                    ajouterFormulaire();
                    break;
                case "3":
                    retirerFormulaire();
                    break;
                case "4":
                    afficherFormulaires();
                    break;
                case "5":
                    afficherEpreuves();
                    break;
                case "6":
                    afficherStatistiques();
                    break;
                case "7":
                    System.out.println("Au revoir!");
                    continuer = false;
                    break;
                default:
                    System.out.println("Erreur : veuillez saisir un numéro entre 1 et 7.");
            }
        }
        scanner.close();
    }

    private void creerEpreuve() {
        System.out.println("\n===== CRÉER UNE ÉPREUVE =====");

        System.out.print("Code ECUE : ");
        String codeECUE = scanner.nextLine().trim();

        System.out.print("Date de passage (YYYY-MM-DD) : ");
        LocalDate datePassage = lireDate();
        if (datePassage == null) return;

        System.out.print("Heure de passage (HH:MM) : ");
        LocalTime heurePassage = lireHeure();
        if (heurePassage == null) return;

        System.out.println("Modalité (Oral/Ecrit/QCM/Ordi/Projet/TP) : ");
        Modalite modalite = lireModalite();
        if (modalite == null) return;

        System.out.print("Durée en minutes : ");
        int duree = lireEntier(1, 9999);
        if (duree == -1) return;

        System.out.print("Nom du professeur : ");
        String nomProf = scanner.nextLine().trim();
        System.out.print("Prénom du professeur : ");
        String prenomProf = scanner.nextLine().trim();
        System.out.print("Numéro du professeur : ");
        String numProf = scanner.nextLine().trim();
        Professeur professeur = new Professeur(numProf, nomProf, prenomProf);

        Epreuve epreuve = new Epreuve(codeECUE, datePassage, heurePassage, modalite, duree, professeur, null);

        boolean ajouterSurveillants = true;
        while (ajouterSurveillants) {
            System.out.print("Ajouter un surveillant ? (oui/non) : ");
            String reponse = scanner.nextLine().trim().toLowerCase();
            if (reponse.equals("oui")) {
                System.out.print("Nom du surveillant : ");
                String nomSurv = scanner.nextLine().trim();
                System.out.print("Prénom du surveillant : ");
                String prenomSurv = scanner.nextLine().trim();
                System.out.print("Numéro du surveillant : ");
                String numSurv = scanner.nextLine().trim();
                Surveillant surveillant = new Surveillant(numSurv, nomSurv, prenomSurv);
                epreuve.addSurveillant(surveillant);
            } else {
                ajouterSurveillants = false;
            }
        }

        epreuves[nbEpreuves] = epreuve;
        nbEpreuves++;
        System.out.println("✓ Épreuve créée avec succès!");
    }

    private void ajouterFormulaire() {
        System.out.println("\n===== AJOUTER UN FORMULAIRE =====");

        if (nbEpreuves == 0) {
            System.out.println("Erreur : aucune épreuve disponible. Créez une épreuve d'abord.");
            return;
        }

        // Étape 1 : Sélection de l'épreuve
        System.out.println("\nÉpreuves disponibles :");
        for (int i = 0; i < nbEpreuves; i++) {
            System.out.println((i + 1) + ". " + epreuves[i].getCodeECUE() + " - " + epreuves[i].getDatePassage());
        }
        System.out.print("Sélectionnez une épreuve (numéro) : ");

        int choixEpreuve = lireEntier(1, nbEpreuves);
        if (choixEpreuve == -1) {
            System.out.println("Erreur : choix invalide.");
            return;
        }
        Epreuve epreuveSelectionnee = epreuves[choixEpreuve - 1];

        // Étape 2 : Saisie des étudiants
        System.out.println("\n--- Saisie des étudiants ---");
        Etudiant[] etudiants = new Etudiant[100];
        int nbEtudiants = 0;
        boolean ajouterEtudiant = true;

        while (ajouterEtudiant) {
            System.out.print("Nom de l'étudiant (ou 'fin' pour terminer) : ");
            String nom = scanner.nextLine().trim();
            if (nom.equalsIgnoreCase("fin")) {
                ajouterEtudiant = false;
                break;
            }

            if (nom.isEmpty()) {
                System.out.println("Erreur : le nom ne peut pas être vide.");
                continue;
            }

            System.out.print("Prénom de l'étudiant : ");
            String prenom = scanner.nextLine().trim();

            if (prenom.isEmpty()) {
                System.out.println("Erreur : le prénom ne peut pas être vide.");
                continue;
            }

            System.out.print("Numéro apprenant : ");
            String numero = scanner.nextLine().trim();

            if (numero.isEmpty()) {
                System.out.println("Erreur : le numéro apprenant ne peut pas être vide.");
                continue;
            }

            System.out.print("Cursus (E1/E2/E3/E4/E5) : ");
            String cursusStr = scanner.nextLine().trim().toUpperCase();
            Cursus cursus;
            try {
                cursus = Cursus.valueOf(cursusStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : cursus invalide. Cursus par défaut utilisé (E1).");
                cursus = Cursus.E1;
            }

            etudiants[nbEtudiants] = new Etudiant(numero, nom, prenom, cursus);
            nbEtudiants++;
            System.out.println("✓ Étudiant ajouté.");
        }

        if (nbEtudiants == 0) {
            System.out.println("Erreur : au moins un étudiant doit être saisi.");
            return;
        }

        Etudiant[] etudiantsFinal = new Etudiant[nbEtudiants];
        for (int i = 0; i < nbEtudiants; i++) {
            etudiantsFinal[i] = etudiants[i];
        }

        // Étape 3 : Saisie des fraudes
        System.out.println("\n--- Saisie des fraudes ---");
        Fraude[] fraudes = new Fraude[100];
        int nbFraudes = 0;
        boolean ajouterFraude = true;

        while (ajouterFraude) {
            System.out.println("\nTypes de fraude disponibles :");
            System.out.println("1. Fraude IAG");
            System.out.println("2. Fraude IAG Connectée");
            System.out.println("3. Fin des fraudes");
            System.out.print("Sélectionnez un type (1-3) : ");

            String typeFraude = scanner.nextLine().trim();

            if (typeFraude.equals("3")) {
                ajouterFraude = false;
                break;
            }

            System.out.print("Date de la fraude (YYYY-MM-DD) : ");
            LocalDate date = lireDate();
            if (date == null) continue;

            System.out.print("Contenu de la fraude : ");
            String contenu = scanner.nextLine().trim();
            if (contenu.isEmpty()) {
                System.out.println("Erreur : le contenu ne peut pas être vide.");
                continue;
            }

            System.out.print("Description de la fraude : ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Erreur : la description ne peut pas être vide.");
                continue;
            }

            Fraude fraude;
            if (typeFraude.equals("1")) {
                fraude = new FraudeIAG(date, contenu, description);
            } else if (typeFraude.equals("2")) {
                System.out.print("Adresse IP : ");
                String adresseIP = scanner.nextLine().trim();
                if (adresseIP.isEmpty()) {
                    System.out.println("Erreur : l'adresse IP ne peut pas être vide.");
                    continue;
                }
                fraude = new FraudeIAGConnecte(date, contenu, description, adresseIP);
            } else {
                System.out.println("Erreur : type de fraude invalide.");
                continue;
            }

            fraudes[nbFraudes] = fraude;
            nbFraudes++;
            System.out.println("✓ Fraude ajoutée.");
        }

        Fraude[] fraudesFinal = new Fraude[nbFraudes];
        for (int i = 0; i < nbFraudes; i++) {
            fraudesFinal[i] = fraudes[i];
        }

        // Création du formulaire
        Formulaire nouveauFormulaire = new Formulaire(fraudesFinal, etudiantsFinal, new Epreuve[]{epreuveSelectionnee});
        formulaires[nbFormulaires] = nouveauFormulaire;
        nbFormulaires++;

        System.out.println("\n✓ Formulaire ajouté avec succès!");
    }

    private void retirerFormulaire() {
        System.out.println("\n===== RETIRER UN FORMULAIRE =====");

        if (nbFormulaires == 0) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }

        afficherFormulaires();
        System.out.print("Entrez l'identifiant du formulaire à retirer (1-" + nbFormulaires + ") : ");

        int id = lireEntier(1, nbFormulaires);
        if (id == -1) {
            System.out.println("Erreur : identifiant invalide.");
            return;
        }

        for (int i = id - 1; i < nbFormulaires - 1; i++) {
            formulaires[i] = formulaires[i + 1];
        }
        nbFormulaires--;

        System.out.println("✓ Formulaire retiré avec succès!");
    }

    private void afficherFormulaires() {
        System.out.println("\n===== FORMULAIRES DISPONIBLES =====");

        if (nbFormulaires == 0) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }

        for (int i = 0; i < nbFormulaires; i++) {
            System.out.println("\nFormulaire " + (i + 1) + " :");
            System.out.println("  - Épreuve : " + formulaires[i].getEpreuves()[0].getCodeECUE());
            System.out.println("  - Étudiants : " + formulaires[i].getEtudiants().length);
            System.out.println("  - Fraudes : " + formulaires[i].getFraudes().length);
        }
    }

    private void afficherEpreuves() {
        System.out.println("\n===== ÉPREUVES DISPONIBLES =====");

        if (nbEpreuves == 0) {
            System.out.println("Aucune épreuve disponible.");
            return;
        }

        for (int i = 0; i < nbEpreuves; i++) {
            System.out.println("\n" + epreuves[i].toString());
        }
    }

    private void afficherStatistiques() {
        System.out.println("\n===== STATISTIQUES =====");

        if (nbFormulaires == 0) {
            System.out.println("Aucun formulaire disponible pour les statistiques.");
            return;
        }

        Formulaire[] formulairesActuels = new Formulaire[nbFormulaires];
        for (int i = 0; i < nbFormulaires; i++) {
            formulairesActuels[i] = formulaires[i];
        }

        Formulaire formulaire = new Formulaire(null, null, null);
        System.out.println(formulaire.statisques(formulairesActuels));
    }

    private LocalDate lireDate() {
        try {
            String dateStr = scanner.nextLine().trim();
            return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : format de date invalide (YYYY-MM-DD).");
            return null;
        }
    }

    private LocalTime lireHeure() {
        try {
            String heureStr = scanner.nextLine().trim();
            return LocalTime.parse(heureStr, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : format d'heure invalide (HH:MM).");
            return null;
        }
    }

    private Modalite lireModalite() {
        System.out.println("Modalités disponibles :");
        System.out.println("1. Oral");
        System.out.println("2. Ecrit");
        System.out.println("3. QCM");
        System.out.println("4. Ordi");
        System.out.println("5. Projet");
        System.out.println("6. TP");
        System.out.print("Sélectionnez une modalité (1-6) : ");

        int choix = lireEntier(1, 6);
        if (choix == -1) {
            System.out.println("Erreur : modalité invalide.");
            return null;
        }

        return Modalite.values()[choix - 1];
    }

    private int lireEntier(int min, int max) {
        try {
            int valeur = Integer.parseInt(scanner.nextLine().trim());
            if (valeur >= min && valeur <= max) {
                return valeur;
            } else {
                System.out.println("Erreur : veuillez saisir un numéro entre " + min + " et " + max + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur : veuillez saisir un nombre entier.");
        }
        return -1;
    }
}
