package entities;

import java.time.LocalDate;

public class Alimentation extends CarbonConsommation {
    private double poids;
    private String typeAliment;

    public Alimentation(LocalDate startDate, LocalDate endDate, double poids, String typeAliment, int quantite) {

        super(startDate, endDate, "alimentation", quantite);
        this.poids = poids;
        this.typeAliment = typeAliment;
    }

    @Override
    public double calculerImpact() {
        switch (typeAliment.toLowerCase()) {
            case "viande":
                impactCarbone = poids * 5.0; // Exemple: 5.0 kg CO₂ par kg de viande
                break;
            case "legume":
                impactCarbone = poids * 0.5; // Exemple: 0.5 kg CO₂ par kg de légume
                break;
            default:
                impactCarbone = 0;
                System.out.println("Type d'aliment inconnu.");
        }

        return impactCarbone;
    }
}
