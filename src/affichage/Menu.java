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

    // Couleurs de fond
    public static final String LIGHT_BLUE_BACKGROUND = "\u001B[104m";

    // Couleurs de texte
    public static final String BLACK = "\u001B[30m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_CYAN = "\u001B[96m";

    // Texte en gras
    public static final String BOLD_WHITE = "\u001B[1;97m";

    // Reset (pour réinitialiser la couleur)
    public static final String RESET = "\u001B[0m";


    public Menu(GestionUtilisateur gestionUtilisateur) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    public void MenuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        String choix;
        boolean continuer = true;


        do {
            System.out.println(LIGHT_BLUE_BACKGROUND + BLACK + "╔════════════════════════════════════════╗" + RESET);
            System.out.println(LIGHT_BLUE_BACKGROUND + BLACK + "║          " + BOLD_WHITE + "MENU PRINCIPAL" + LIGHT_BLUE_BACKGROUND + "           ║" + RESET);
            System.out.println(LIGHT_BLUE_BACKGROUND + BLACK + "╚════════════════════════════════════════╝" + RESET);

            System.out.println(BRIGHT_YELLOW + "1. " + BRIGHT_WHITE + "Créer un nouvel utilisateur" + RESET);
            System.out.println(BRIGHT_YELLOW + "2. " + BRIGHT_WHITE + "Afficher tous les utilisateurs" + RESET);
            System.out.println(BRIGHT_YELLOW + "3. " + BRIGHT_WHITE + "Modifier un utilisateur" + RESET);
            System.out.println(BRIGHT_YELLOW + "4. " + BRIGHT_WHITE + "Supprimer un utilisateur" + RESET);
            System.out.println(BRIGHT_YELLOW + "5. " + BRIGHT_WHITE + "Ajouter une consommation de carbone" + RESET);
            System.out.println(BRIGHT_YELLOW + "6. " + BRIGHT_WHITE + "Afficher les détails d'un utilisateur" + RESET);
            System.out.println(BRIGHT_YELLOW + "8. " + BRIGHT_WHITE + "Gestion Rapport" + RESET);
            System.out.println(BRIGHT_RED + "7. Quitter" + RESET);
            System.out.print(BRIGHT_CYAN + "Entrez votre choix (1-8) : " + RESET);

            choix = scanner.nextLine();

            System.out.println(LIGHT_BLUE_BACKGROUND + BLACK + "╟────────────────────────────────────────╢" + RESET);


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
                    System.out.println("=========================================");
                    System.out.println("Merci d'avoir utilisé l'application !");
                    System.out.println("=========================================");                    continuer = false;
                    break;
                default:
                    System.out.println("⚠️ Choix invalide, veuillez entrer un chiffre entre 1 et 8.");
            }
        } while (continuer);
    }


    private void creerNouvelUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      *** CRÉER UN NOUVEL UTILISATEUR ***");
        System.out.println("=========================================");

        System.out.print("Entrez le nom de l'utilisateur : ");
        String name = scanner.nextLine();

        System.out.print("Entrez l'âge de l'utilisateur : ");
        int age = scanner.nextInt();

        Utilisateur nouvelUtilisateur = gestionUtilisateur.creerUtilisateur(name, age);
        System.out.println("✅ Utilisateur créé avec succès !");
        System.out.println("ID : " + nouvelUtilisateur.getId());
        System.out.println("Nom : " + nouvelUtilisateur.getName());
        System.out.println("Âge : " + nouvelUtilisateur.getAge());
    }

    private void afficherTousLesUtilisateurs() {
        List<Utilisateur> utilisateurs = gestionUtilisateur.getUtilisateurs();

        System.out.println("=========================================");
        System.out.println("      *** LISTE DES UTILISATEURS ***     ");
        System.out.println("=========================================");

        if (utilisateurs.isEmpty()) {
            System.out.println("____________________________");
              System.out.println("Aucun utilisateur trouvé.");
            System.out.println("____________________________");

        } else {
            System.out.println("=== Liste des Utilisateurs ===");
            for (Utilisateur utilisateur : utilisateurs) {
                System.out.println("🔹 ID : " + utilisateur.getId());
                System.out.println("🔹 Nom : " + utilisateur.getName());
                System.out.println("🔹 Âge : " + utilisateur.getAge());
                System.out.println("------------------------------");
            }
        }
    }

    private void modifierUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("       *** MODIFIER UTILISATEUR ***      ");
        System.out.println("=========================================");

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
            System.out.println("✅ Utilisateur modifié avec succès !");
        } else {
            System.out.println("⚠️ Échec de la modification de l'utilisateur !");
        }
    }

    private void supprimerUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("       *** SUPPRIMER UTILISATEUR ***     ");
        System.out.println("=========================================");
        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");
        long id = scanner.nextLong();
        boolean result = gestionUtilisateur.supprimerUtilisateur(id);
        if (result) {
            System.out.println("✅ Utilisateur supprimé avec succès.");
        } else {
            System.out.println("⚠️ Utilisateur avec cet identifiant n'existe pas.");
        }
    }


    private void ajouterConsommation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("  *** AJOUTER CONSOMMATION DE CARBONE ***");
        System.out.println("=========================================");
        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        if (!gestionUtilisateur.existeUtilisateur(id)) {
            System.out.println("⚠️ Utilisateur non trouvé !");
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

        System.out.println("=========================================");
        System.out.println("   *** DÉTAILS DE L'UTILISATEUR ***      ");
        System.out.println("=========================================");
        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Utilisateur utilisateur = gestionUtilisateur.rechercheUserById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }

        System.out.println("ID : " + utilisateur.getId());
        System.out.println("Nom : " + utilisateur.getName());
        System.out.println("Âge : " + utilisateur.getAge());

        List<CarbonConsommation> consommations = utilisateur.getConsommation();
        if (consommations.isEmpty()) {
            System.out.println("Aucune consommation de carbone enregistrée.");
        } else {
            System.out.println("=== Détails des consommations de carbone ===");
            for (CarbonConsommation consommation : consommations) {
                System.out.println("📅 Date de début : " + consommation.getStartDate());
                System.out.println("📅 Date de fin : " + consommation.getEndDate());
                System.out.println("💨 Quantité de carbone : " + consommation.getCarbonAmount() + " kg");
                System.out.println("------------------------------");
            }
            System.out.println("🌍 Consommation totale : " + utilisateur.calculerConsommationTotale() + " kg");

        }
        System.out.println("=========================================");

    }


    private void afficherRapportConsommation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("    *** RAPPORT DE CONSOMMATION ***      ");
        System.out.println("=========================================");
        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Choisissez le type de rapport : ");
        System.out.println("1. Quotidien");
        System.out.println("2. Hebdomadaire");
        System.out.println("3. Mensuel");
        System.out.print("🔷 Votre choix (1-3) : ");

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
