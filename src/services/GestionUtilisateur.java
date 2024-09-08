package services;

import entities.CarbonConsommation;
import entities.Utilisateur;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateur {
    private static long idCounter = 1;
    private List<Utilisateur> utilisateurs;



    public GestionUtilisateur() {
        this.utilisateurs = new ArrayList<>();
    }

    public Utilisateur creerUtilisateur( String name, int age) {
        long id = idCounter++;
        Utilisateur nouvelUtilisateur = new Utilisateur(id, name, age);
        utilisateurs.add(nouvelUtilisateur);
        return nouvelUtilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

    public Utilisateur rechercheUserById(long id){
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id) {
                return utilisateur;
            }
        }
        return null;
    }

    public boolean ajouterConsommation(long id, LocalDate startDate, LocalDate endDate, double carbonAmount) {
        Utilisateur utilisateur = rechercheUserById(id);
        if (utilisateur != null) {
            CarbonConsommation nouvelleConsommation = new CarbonConsommation(startDate, endDate, carbonAmount);
            utilisateur.getConsommation().add(nouvelleConsommation);

            return true;
        }
        return false;
    }


    public boolean modifierUtilisateur(long id, String newName, int newAge) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id){
                utilisateur.setName(newName);
                utilisateur.setAge(newAge);
                return true;
            }
        }


        return false;
    }

    public boolean existeUtilisateur(long id) {
        return utilisateurs.stream().anyMatch(utilisateur -> utilisateur.getId() == id);
    }


    public boolean supprimerUtilisateur(long id) {
        return utilisateurs.removeIf(utilisateur -> utilisateur.getId() == id);
    }


    public void generateConsumptionReport(long id, String reportType) {
        Utilisateur utilisateur = rechercheUserById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }

        List<CarbonConsommation> consommations = utilisateur.getConsommation();
        if (consommations == null || consommations.isEmpty()) {
            System.out.println("Aucune consommation de carbone enregistrée pour l'utilisateur " + utilisateur.getName());
            return;
        }
        switch (reportType.toLowerCase()) {
            case "quotidien":
                generateReport(consommations, "Quotidien");
                break;
            case "hebdomadaire":
                generateReport(consommations, "Hebdomadaire");
                break;
            case "mensuel":
                generateReport(consommations, "Mensuel");
                break;
            default:
                System.out.println("Type de rapport invalide.");
        }
    }

    private void generateReport(List<CarbonConsommation> consommations, String reportType) {
        System.out.println(reportType + " Rapport de Consommation:");
        for (CarbonConsommation consommation : consommations) {
            long daysBetween = ChronoUnit.DAYS.between(consommation.getStartDate(), consommation.getEndDate()) + 1;
            double dailyConsumption = consommation.getCarbonAmount() / daysBetween;

            LocalDate currentDate = consommation.getStartDate();
            double periodConsumption = 0;
            int dayCounter = 0;

            while (!currentDate.isAfter(consommation.getEndDate())) {
                periodConsumption += dailyConsumption;
                dayCounter++;

                boolean isReportBoundary = false;
                switch (reportType) {
                    case "Quotidien":
                        isReportBoundary = true;
                        break;
                    case "Hebdomadaire":
                        isReportBoundary = dayCounter % 7 == 0 || currentDate.equals(consommation.getEndDate());
                        break;
                    case "Mensuel":
                        isReportBoundary = currentDate.getDayOfMonth() == currentDate.lengthOfMonth() || currentDate.equals(consommation.getEndDate());
                        break;
                }
                if (isReportBoundary) {
                    System.out.printf("%s se terminant le %s, Consommation: %.2f kg%n", reportType, currentDate, periodConsumption);
                    periodConsumption = 0;
                }
                currentDate = currentDate.plusDays(1);
            }
        }
    }









}



