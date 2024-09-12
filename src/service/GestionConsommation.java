package service;

import Repository.ConsommationRepository;
import entities.Alimentation;
import entities.CarbonConsommation;
import entities.Logement;
import entities.Transport;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GestionConsommation {
    private static Connection connection;
    private static Scanner scanner;


    public GestionConsommation(Connection connection) {
        this.connection= connection;
        this.scanner = new Scanner(System.in);
    }



    public static void ajouterConsommation(int utilisateurId) {
        System.out.println("Choisissez le type de consommation: ");
        System.out.println("1. Transport");
        System.out.println("2. Logement");
        System.out.println("3. Alimentation");
        int choix = scanner.nextInt();
        scanner.nextLine();

        CarbonConsommation consommation = null;
        double quantite;
        String option;
        LocalDate startDate, endDate;

        System.out.println("Entrez la date de début (YYYY-MM-DD) : ");
        startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Entrez la date de fin (YYYY-MM-DD) : ");
        endDate = LocalDate.parse(scanner.nextLine());

        switch (choix) {
            case 1:
                System.out.println("Entrez la distance parcourue (en km): ");
                quantite = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Entrez le type de véhicule (ex: voiture, train) : ");
                option = scanner.nextLine();
                consommation = new Transport(quantite, option, startDate, endDate, "transport", 1);  // 1 est la valeur de quantite car on ne mesure que la distance pour le transport
                break;


            case 2:
                System.out.println("Entrez la consommation d'énergie (en kWh): ");
                quantite = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Entrez le type d'énergie (ex:gaz,electricite) : ");
                option = scanner.nextLine();
                consommation = new Logement(quantite, option, startDate, endDate, "logement", 1);
                break;

            case 3:
                System.out.println("Entrez le poids de l'aliment (en kg): ");
                quantite = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Entrez le type d'aliment (ex: legume,viande) : ");
                option = scanner.nextLine();
                consommation = new Alimentation(startDate, endDate, quantite, option, 1);
                break;

            default:
                System.out.println("Choix invalide.");
                return;
        }

        double impactCarbone = consommation.calculerImpact();
        ConsommationRepository consommationRepository = new ConsommationRepository(connection);
        consommationRepository.ajouterConsommation(utilisateurId, consommation, impactCarbone);
    }

    // Dans la classe GestionConsommation
    public static List<CarbonConsommation> obtenirConsommationsParUtilisateur(int utilisateurId) {
        ConsommationRepository repository = new ConsommationRepository(connection);
        return repository.obtenirConsommationsParUtilisateur(utilisateurId);
    }
}
