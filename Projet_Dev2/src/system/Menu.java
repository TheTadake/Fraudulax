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
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    private Formulaire[] formulaires;
    private Epreuve[] epreuves ;
    private Scanner scanner;
    private Etudiant[] etudiants;
    private int nbFormulaires;
    private int nbEpreuves;
    private DateTimeFormatter dateFormatterFR = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Menu(Epreuve[] epreuves,Etudiant[] etudiants) {
        this.epreuves = epreuves ;
        this.formulaires = new Formulaire[100];
        this.nbFormulaires = 0;
        this.nbEpreuves = epreuves.length;
        this.etudiants = etudiants;
        this.scanner = new Scanner(System.in);
    }

    // Getters et setters pour l'accès aux formulaires
    public Formulaire[] getFormulaires() {
        return formulaires;
    }

    public void setNbFormulaires(int nbFormulaires) {
        this.nbFormulaires = nbFormulaires;
    }

    public int getNbFormulaires() {
        return nbFormulaires;
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
            System.out.println("7. Recherche filtrée d'étudiants");
            System.out.println("8. Afficher le graphe de plagiat");
            System.out.println("9. Quitter");
            System.out.print("Sélectionnez une action (1-9) : ");

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
                    rechercheFiltre();
                    break;
                case "8":
                    afficherGraphe();
                    break;
                case "9":
                    System.out.println("Au revoir!");
                    continuer = false;
                    break;
                default:
                    System.out.println("Erreur : veuillez saisir un numéro entre 1 et 9.");
            }
        }
        scanner.close();
    }

    private void creerEpreuve() {
        System.out.println("\n===== CRÉER UNE ÉPREUVE =====");

        System.out.print("Code ECUE : ");
        String codeECUE = scanner.nextLine().trim();

        System.out.print("Date de passage (jj/mm/aaaa) : ");
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
            if (epreuves[i] != null) {
                System.out.println((i + 1) + ". " + epreuves[i].getCodeECUE() + " - " + epreuves[i].getDatePassage().format(dateFormatterFR));
            }
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
             System.out.println("1. Fraude Calculatrice");
             System.out.println("2. Fraude Papier");
             System.out.println("3. Fraude IAG");
             System.out.println("4. Fraude IAG Connectée");
             System.out.println("5. Fin des fraudes");
             System.out.print("Sélectionnez un type (1-5) : ");

             String typeFraude = scanner.nextLine().trim();

             if (typeFraude.equals("5")) {
                 ajouterFraude = false;
                 break;
             }

             System.out.print("Date de la fraude (jj/mm/aaaa) : ");
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
                 fraude = new FraudeCalculatrice(date, contenu, description);
             } else if (typeFraude.equals("2")) {
                 fraude = new FraudePapier(date, contenu, description);
             } else if (typeFraude.equals("3")) {
                 fraude = new FraudeIAG(date, contenu, description);
             } else if (typeFraude.equals("4")) {
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

        // Création du tableau d'épreuves avec la même taille
        Epreuve[] epreuvesFinal = new Epreuve[nbFraudes];
        for (int i = 0; i < nbFraudes; i++) {
            epreuvesFinal[i] = epreuveSelectionnee;
        }

        // Création du formulaire
        Formulaire nouveauFormulaire = new Formulaire(fraudesFinal, etudiantsFinal, epreuvesFinal);
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
            System.out.println("\n" + "=".repeat(60));
            System.out.println("FORMULAIRE " + (i + 1));
            System.out.println("=".repeat(60));

            // Affichage des dates de création et modification
            System.out.println("\n--- INFORMATIONS DU FORMULAIRE ---");
            System.out.println("Date de création : " + formulaires[i].getCreationDate().format(dateFormatterFR) + " à " + formulaires[i].getCreationTime().format(timeFormatter));
            if (formulaires[i].getModificationDate() != null) {
                System.out.println("Date de modification : " + formulaires[i].getModificationDate().format(dateFormatterFR) + " à " + formulaires[i].getModificationTime().format(timeFormatter));
            } else {
                System.out.println("Date de modification : Aucune modification");
            }

            // Affichage des épreuves
            System.out.println("\n--- INFORMATIONS DE L'ÉPREUVE ---");
            Epreuve[] epreuves = formulaires[i].getEpreuves();
            for (int j = 0; j < epreuves.length; j++) {
                if (epreuves[j] != null) {
                    System.out.println("Code ECUE : " + epreuves[j].getCodeECUE());
                    System.out.println("Date : " + epreuves[j].getDatePassage().format(dateFormatterFR));
                    System.out.println("Heure : " + epreuves[j].getHeurePassage().format(timeFormatter));
                    System.out.println("Modalité : " + epreuves[j].getModalite());
                    System.out.println("Durée : " + epreuves[j].getDuree() + " minutes");
                    System.out.println("Professeur : " + epreuves[j].getProfesseur().getNom() + " " + epreuves[j].getProfesseur().getPrenom());
                }
            }

            // Affichage des étudiants
            System.out.println("\n--- ÉTUDIANTS IMPLIQUÉS ---");
            Etudiant[] etudiants = formulaires[i].getEtudiants();
            for (int j = 0; j < etudiants.length; j++) {
                if (etudiants[j] != null) {
                    System.out.println((j + 1) + ". Nom : " + etudiants[j].getNom());
                    System.out.println("   Prénom : " + etudiants[j].getPrenom());
                    System.out.println("   Numéro apprenant : " + etudiants[j].getNum());
                    System.out.println("   Cursus : " + etudiants[j].getCursus());
                }
            }

            // Affichage des fraudes
            System.out.println("\n--- FRAUDES COMMISES ---");
            Fraude[] fraudes = formulaires[i].getFraudes();
            if (fraudes.length == 0) {
                System.out.println("Aucune fraude enregistrée.");
            } else {
                for (int j = 0; j < fraudes.length; j++) {
                    if (fraudes[j] != null) {
                        System.out.println((j + 1) + ". Type : " + fraudes[j].getClass().getSimpleName());
                        System.out.println("   Date : " + fraudes[j].getDate().format(dateFormatterFR));
                        System.out.println("   Description : " + fraudes[j].getDescription());
                        System.out.println("   Contenu : " + fraudes[j].getContenu());
                        if (fraudes[j].getClass().getSimpleName().equals("FraudeIAGConnecte")) {
                            System.out.println("   Adresse IP : " + ((system.fraude.FraudeIAGConnecte)fraudes[j]).getAdresseIP());
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    private void afficherEpreuves() {
        System.out.println("\n===== ÉPREUVES DISPONIBLES =====");

        if (nbEpreuves == 0) {
            System.out.println("Aucune épreuve disponible.");
            return;
        }

        for (int i = 0; i < nbEpreuves; i++) {
            if (epreuves[i] != null) {
                System.out.println("\nCode ECUE : " + epreuves[i].getCodeECUE());
                System.out.println("Date : " + epreuves[i].getDatePassage().format(dateFormatterFR));
                System.out.println("Heure : " + epreuves[i].getHeurePassage().format(timeFormatter));
                System.out.println("Modalité : " + epreuves[i].getModalite());
                System.out.println("Durée : " + epreuves[i].getDuree() + " minutes");
                System.out.println("Professeur : " + epreuves[i].getProfesseur().getNom() + " " + epreuves[i].getProfesseur().getPrenom());

                System.out.println("Surveillants :");
                Surveillant[] surveillants = epreuves[i].getSurveillants();
                if (surveillants != null && surveillants.length > 0) {
                    for (int j = 0; j < surveillants.length; j++) {
                        if (surveillants[j] != null) {
                            System.out.println("  - " + surveillants[j].getNom() + " " + surveillants[j].getPrenom());
                        }
                    }
                } else {
                    System.out.println("  Aucun surveillant assigné.");
                }
            }
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

        System.out.println(formulairesActuels[0].statistiques(formulairesActuels));
    }

    private void rechercheFiltre() {
        System.out.println("\n===== RECHERCHE FILTRÉE D'ÉTUDIANTS =====");

        if (nbFormulaires == 0) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }

        System.out.println("\nFormulaires disponibles :");
        for (int i = 0; i < nbFormulaires; i++) {
            System.out.println((i + 1) + ". Formulaire " + (i + 1) + " - " + formulaires[i].getEpreuves()[0].getCodeECUE());
        }

        System.out.print("Sélectionnez un formulaire (numéro) : ");
        int choixFormulaire = lireEntier(1, nbFormulaires);
        if (choixFormulaire == -1) {
            System.out.println("Erreur : choix invalide.");
            return;
        }

        Formulaire formulaireSelectionne = formulaires[choixFormulaire - 1];

        System.out.println("\n--- Critères de recherche (appuyez sur Entrée pour ignorer un critère) ---");
        System.out.print("Nom (ou laisser vide) : ");
        String nom = scanner.nextLine().trim();

        System.out.print("Prénom (ou laisser vide) : ");
        String prenom = scanner.nextLine().trim();

        System.out.print("Numéro apprenant (ou laisser vide) : ");
        String numero = scanner.nextLine().trim();

        System.out.println("\n--- Résultats de la recherche ---");
        String resultats = formulaireSelectionne.rechercheFiltre(
                nom.isEmpty() ? null : nom,
                prenom.isEmpty() ? null : prenom,
                numero.isEmpty() ? null : numero
        );
        System.out.println(resultats);
    }

    private void afficherGraphe() {
        System.out.println("\n===== GRAPHE DE PLAGIAT =====");

        if (nbFormulaires == 0) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }

        System.out.println("\nFormulaires disponibles :");
        for (int i = 0; i < nbFormulaires; i++) {
            System.out.println((i + 1) + ". Formulaire " + (i + 1) + " - " + formulaires[i].getEpreuves()[0].getCodeECUE());
        }

        System.out.print("Sélectionnez un formulaire (numéro) : ");
        int choixFormulaire = lireEntier(1, nbFormulaires);
        if (choixFormulaire == -1) {
            System.out.println("Erreur : choix invalide.");
            return;
        }

        Formulaire formulaireSelectionne = formulaires[choixFormulaire - 1];
        System.out.println("\n" + formulaireSelectionne.graphe());
    }

    private LocalDate lireDate() {
        try {
            String dateStr = scanner.nextLine().trim();
            return LocalDate.parse(dateStr, dateFormatterFR);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : format de date invalide (jj/mm/aaaa).");
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
