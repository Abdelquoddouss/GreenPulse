package entities;

import java.time.LocalDate;

public class Logement extends CarbonConsommation{
    private double consommationEnergie;
    private String typeEnergie;

    public Logement(double consommationEnergie, String typeEnergie, LocalDate startDate, LocalDate endDate, String consommationType, int quantite) {
        super(startDate, endDate, consommationType, quantite);
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }




    @Override
    public double calculerImpact() {
        switch (typeEnergie.toLowerCase()) {
            case "electricite":
                impactCarbone = consommationEnergie * 1.5; // Exemple: 1.5 kg CO₂ par kWh
                break;
            case "gaz":
                impactCarbone = consommationEnergie * 2.0; // Exemple: 2.0 kg CO₂ par kWh
                break;
            default:
                impactCarbone = 0;
                System.out.println("Type d'énergie inconnu.");
        }
        return impactCarbone;
    }
}
