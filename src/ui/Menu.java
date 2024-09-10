package ui;

import entities.Utilisateur;
import service.GestionConsommation;
import service.GestionUser;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private GestionUser gestionUser;
    private Scanner scanner;
    private GestionConsommation gestionConsommation;


    public Menu(GestionUser gestionUser) {
        this.gestionUser = new GestionUser();
        this.scanner = new Scanner(System.in);
        this.gestionConsommation = gestionConsommation;

    }

    public void affichageMenu() {
        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Afficher les utilisateurs");
            System.out.println("3. Modifier un utilisateur");
            System.out.println("4. Supprimer un utilisateur");
            System.out.println("5. Ajouter une consommation");
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
                    ajouterConsommation();
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


    private void ajouterConsommation() {
        System.out.print("Entrez l'ID de l'utilisateur pour ajouter une consommation : ");
        int utilisateurId = scanner.nextInt();
        scanner.nextLine();

        GestionConsommation.ajouterConsommation(utilisateurId);

    }











}
