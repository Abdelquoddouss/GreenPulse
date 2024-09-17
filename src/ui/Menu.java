package ui;

import Repository.ConsommationRepository;
import Repository.UserRepository;
import entities.CarbonConsommation;
import entities.Utilisateur;
import service.GestionConsommation;
import service.GestionUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private GestionUser gestionUser;
    private Scanner scanner;
    private GestionConsommation gestionConsommation;


    public Menu(GestionUser gestionUser, GestionConsommation gestionConsommation) {
        this.gestionUser = new GestionUser();
        this.scanner = new Scanner(System.in);
        this.gestionConsommation = gestionConsommation;
        this.scanner = new Scanner(System.in);


    }

    public void affichageMenu() {
        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Afficher les utilisateurs");
            System.out.println("3. Modifier un utilisateur");
            System.out.println("4. Supprimer un utilisateur");
            System.out.println("5. Gérer les consommations");
            System.out.println("6. Filtrer les utilisateurs par consommation");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterUtilisateur();
                    break;
                case 2:
                    afficherUtilisateurs();
                    break;
                case 3:
                    modifierUtilisateur();
                    break;
                case 4:
                    supprimerUtilisateur();
                    break;
                case 5:
                    affichageMenuConsommation();
                    break;
                case 6:
                    filtrerUserParConsommation();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        } while (choix != 0);
    }


    private void ajouterUtilisateur() {
        System.out.print("Entrez le nom: ");
        String name = scanner.nextLine();
        System.out.print("Entrez l'âge: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Utilisateur utilisateur = new Utilisateur(0, name, age);
        gestionUser.createUser(utilisateur);
    }

    private void afficherUtilisateurs() {
        List<Utilisateur> utilisateurs = gestionUser.getAllUsers();
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé.");
        } else {
            for (Utilisateur utilisateur : utilisateurs) {
                System.out.println("ID: " + utilisateur.getId());
                System.out.println("Nom: " + utilisateur.getName());
                System.out.println("Âge: " + utilisateur.getAge());
                System.out.println("--------");
            }
        }
    }


    private void modifierUtilisateur() {
        System.out.print("Entrez l'ID de l'utilisateur à modifier: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Utilisateur utilisateur = gestionUser.getUserById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur avec ID " + id + " non trouvé.");
            return;
        }

        System.out.print("Entrez le nouveau nom: ");
        String name = scanner.nextLine();
        System.out.print("Entrez le nouvel âge: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        utilisateur.setName(name);
        utilisateur.setAge(age);

        gestionUser.updateUser(utilisateur);
    }

    private void supprimerUtilisateur() {
        System.out.print("Entrez l'ID de l'utilisateur à supprimer: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Utilisateur utilisateur = gestionUser.getUserById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur avec ID " + id + " non trouvé.");
            return;
        }

        gestionUser.deleteUser(utilisateur);
    }

    public void affichageMenuConsommation() {
        int choix;
        do {
            System.out.println("\n=== Menu Consommation ===");
            System.out.println("1. Ajouter une consommation");
            System.out.println("2. Afficher les consommations");
            System.out.println("3. Afficher la moyenne des consommations");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterConsommation();
                    break;
                case 2:
                    afficherConsommations(scanner);
                    break;
                case 3:
                    afficherMoyenneConsommation();
                    break;
                case 0:
                    affichageMenu();
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        } while (choix != 0);
    }


    private void afficherMoyenneConsommation() {
        System.out.print("Entrez l'ID de l'utilisateur pour voir la moyenne de ses consommations: ");
        int utilisateurId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez la date de début (format YYYY-MM-DD): ");
        String debutStr = scanner.nextLine();
        LocalDate dateDebut = LocalDate.parse(debutStr);

        System.out.print("Entrez la date de fin (format YYYY-MM-DD): ");
        String finStr = scanner.nextLine();
        LocalDate dateFin = LocalDate.parse(finStr);

        GestionUser utilisateurService = new GestionUser();
        Utilisateur utilisateur = utilisateurService.getUserById(utilisateurId);

        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        // Calcul de la moyenne
        double moyenneConsommation = ConsommationRepository.getAverageConsommation(utilisateur, dateDebut, dateFin);

        // Affichage de la moyenne
        if (moyenneConsommation == 0) {
            System.out.println("Aucune consommation trouvée pour cet utilisateur pendant cette période.");
        } else {
            System.out.println("La moyenne des consommations pour l'utilisateur " + utilisateur.getName() + " entre " + dateDebut + " et " + dateFin + " est de : " + moyenneConsommation + " KgCO₂eq.");
        }
    }



    private void ajouterConsommation() {
        System.out.print("Entrez l'ID de l'utilisateur pour ajouter une consommation : ");
        int utilisateurId = scanner.nextInt();
        scanner.nextLine();

        GestionConsommation.ajouterConsommation(utilisateurId);

    }

    public void afficherConsommations(Scanner scanner) {
        System.out.print("Entrez l'ID de l'utilisateur pour voir ses consommations: ");
        int utilisateurId = scanner.nextInt();
        scanner.nextLine();

        List<CarbonConsommation> consommations = GestionConsommation.obtenirConsommationsParUtilisateur(utilisateurId);

        if (consommations.isEmpty()) {
            System.out.println("Aucune consommation trouvée pour cet utilisateur.");
        } else {
            for (CarbonConsommation consommation : consommations) {
                System.out.println("Type de consommation : " + consommation.getConsommationType());
                System.out.println("Quantité : " + consommation.getQuantite());
                System.out.println("Date de début : " + consommation.getStartDate());
                System.out.println("Date de fin : " + consommation.getEndDate());
                System.out.println("Impact carbone : " + consommation.calculerImpact());
                System.out.println("--------------");
            }
        }
    }

    public void filtrerUserParConsommation() {

        List<Utilisateur> utilisateursFiltres = gestionUser.filterUsersByTotalConsommation();

        if (utilisateursFiltres.isEmpty()) {
            System.out.println("Aucun utilisateur avec une consommation supérieure à  KgCO₂eq.");
        } else {
            System.out.println("\nUtilisateurs avec une consommation >  3000 KgCO₂eq :");
            for (Utilisateur utilisateur : utilisateursFiltres) {
                System.out.println("ID: " + utilisateur.getId() + ", Nom: " + utilisateur.getName() + ", Âge: " + utilisateur.getAge());
                System.out.println("Consommations :");
                System.out.println(gestionConsommation.getTotalConsommation(utilisateur));
            }
        }
    }



}
