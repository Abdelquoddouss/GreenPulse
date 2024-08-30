package affichage;

import entities.Utilisateur;
import services.GestionUtilisateur;

import java.util.Scanner;

public class Menu {

    private GestionUtilisateur gestionUtilisateur;

    public Menu(GestionUtilisateur gestionUtilisateur) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    public void MenuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouvel utilisateur");
            System.out.println("2. Afficher tous les utilisateurs");
            System.out.println("3. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    creerNouvelUtilisateur();
                    break;
                case 2:

                    break;
                case 3:
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 3);
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


}
