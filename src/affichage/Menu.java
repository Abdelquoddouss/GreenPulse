package affichage;

import entities.Utilisateur;
import services.GestionUtilisateur;

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
            System.out.println("5. Quitter");
            System.out.print("Entrez votre choix (1-5) : ");
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
                case "5":
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



}
