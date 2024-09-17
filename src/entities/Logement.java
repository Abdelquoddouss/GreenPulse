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
                impactCarbone = super.getQuantite() * consommationEnergie * 1.5;
                break;
            case "gaz":
                impactCarbone = super.getQuantite() * consommationEnergie * 2.0;
                break;
        }
        return impactCarbone;
    }

    public double getConsommationEnergie() {
        return consommationEnergie;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }

    public void setConsommationEnergie(double consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }
}