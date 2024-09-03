package affichage;

import entities.CarbonConsommation;
import entities.Utilisateur;
import services.GestionUtilisateur;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
 import java.util.Scanner;

public class Menu {

    private GestionUtilisateur gestionUtilisateur;

    public Menu(GestionUtilisateur gestionUtilisateur) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    public void MenuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        String choix;
        boolean continuer = true;


        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouvel utilisateur");
            System.out.println("2. Afficher tous les utilisateurs");
            System.out.println("3. Modifier un utilisateur");
            System.out.println("4. Supprimmer un utilisateur");
            System.out.println("5. Ajouter une consommation de carbone");
            System.out.println("6. Afficher les détails d'un utilisateur");
            System.out.println("7. Quitter");
            System.out.println("8. Gestion Rapport");
            System.out.print("Entrez votre choix (1-6) : ");
            choix = String.valueOf(scanner.nextLine());
            switch (choix) {
                case "1":
                    creerNouvelUtilisateur();
                    break;
                case "2":
                    afficherTousLesUtilisateurs();
                    break;
                case "3":
                    modifierUtilisateur();
                    break;
                case "4":
                    supprimerUtilisateur();
                    break;
                case "5":
                    ajouterConsommation();
                    break;
                case "6":
                    afficherDetailsUtilisateur();
                    break;
                case "8":
                    afficherRapportConsommation();
                    break;
                case "7":
                    System.out.println("Merci d'avoir utilisé l'application !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez entrer un chiffre entre 1 et 5.");
            }
        } while (continuer);
    }


    private void creerNouvelUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le nom de l'utilisateur : ");
        String name = scanner.nextLine();

        System.out.print("Entrez l'âge de l'utilisateur : ");
        int age = scanner.nextInt();

        Utilisateur nouvelUtilisateur = gestionUtilisateur.creerUtilisateur(name, age);
        System.out.println("Utilisateur créé avec succès !");
        System.out.println("ID : " + nouvelUtilisateur.getId());
        System.out.println("Nom : " + nouvelUtilisateur.getName());
        System.out.println("Âge : " + nouvelUtilisateur.getAge());
    }

    private void afficherTousLesUtilisateurs() {
        List<Utilisateur> utilisateurs = gestionUtilisateur.getUtilisateurs();

        if (utilisateurs.isEmpty()) {
            System.out.println("____________________________");
              System.out.println("Aucun utilisateur trouvé.");
            System.out.println("____________________________");

        } else {
            System.out.println("=== Liste des Utilisateurs ===");
            for (Utilisateur utilisateur : utilisateurs) {
                System.out.println("ID : " + utilisateur.getId());
                System.out.println("Nom : " + utilisateur.getName());
                System.out.println("Âge : " + utilisateur.getAge());
                System.out.println("----------------------------");
            }
        }
    }

    private void modifierUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de l'utilisateur à modifier : ");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (!gestionUtilisateur.existeUtilisateur(id)) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }

        System.out.print("Entrez le nouveau nom de l'utilisateur : ");
        String newName = scanner.nextLine();

        System.out.print("Entrez le nouvel âge de l'utilisateur : ");
        int newAge = scanner.nextInt();

        boolean isModified = gestionUtilisateur.modifierUtilisateur(id, newName, newAge);

        if (isModified) {
            System.out.println("Utilisateur modifié avec succès !");
        } else {
            System.out.println("Utilisateur non trouvé !");
        }
    }

    private void supprimerUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");
        long id = scanner.nextLong();
        boolean result = gestionUtilisateur.supprimerUtilisateur(id);
        if (result) {
            System.out.println("Utilisateur supprimé avec succès.");
        } else {
            System.out.println("Utilisateur avec cet identifiant n'existe pas.");
        }
    }


    private void ajouterConsommation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        if (!gestionUtilisateur.existeUtilisateur(id)) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }

        System.out.print("Entrez la date de début de la consommation (AAAA-MM-JJ) : ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Entrez la date de fin de la consommation (AAAA-MM-JJ) : ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Entrez la quantité de carbone consommée : ");
        double carbonAmount = scanner.nextDouble();

        boolean isAdded = gestionUtilisateur.ajouterConsommation(id, startDate, endDate, carbonAmount);
        if (isAdded) {
            System.out.println("Consommation ajoutée avec succès !");
        } else {
            System.out.println("Échec de l'ajout de la consommation.");
        }
    }


    private void afficherDetailsUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Utilisateur utilisateur = gestionUtilisateur.rechercheUserById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }

        System.out.println("=== Détails de l'utilisateur ===");
        System.out.println("ID : " + utilisateur.getId());
        System.out.println("Nom : " + utilisateur.getName());
        System.out.println("Âge : " + utilisateur.getAge());

        List<CarbonConsommation> consommations = utilisateur.getConsommation();
        if (consommations.isEmpty()) {
            System.out.println("Aucune consommation de carbone enregistrée.");
        } else {
            System.out.println("=== Détails des consommations de carbone ===");
            for (CarbonConsommation consommation : consommations) {
                System.out.println("Date de début : " + consommation.getStartDate());
                System.out.println("Date de fin : " + consommation.getEndDate());
                System.out.println("Quantité de carbone : " + consommation.getCarbonAmount() + " kg");
                System.out.println("Consommation totale de carbone : " + utilisateur.calculerConsommationTotale() + " kg");
                System.out.println("-----------------------------");
            }
        }
    }


    private void afficherRapportConsommation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Choisissez le type de rapport : ");
        System.out.println("1. Quotidien");
        System.out.println("2. Hebdomadaire");
        System.out.println("3. Mensuel");
        int choix = scanner.nextInt();
        scanner.nextLine();

        String reportType;
        switch (choix) {
            case 1:
                reportType = "quotidien";
                break;
            case 2:
                reportType = "hebdomadaire";
                break;
            case 3:
                reportType = "mensuel";
                break;
            default:
                System.out.println("Type de rapport invalide.");
                return;
        }

        gestionUtilisateur.generateConsumptionReport(id, reportType);
    }




}
