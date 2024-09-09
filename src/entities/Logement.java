package entities;

public class Logement extends CarbonConsommation{
    private double consommationEnergie;
    private String typeEnergie;

    public Logement(double consommationEnergie, String typeEnergie) {
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }

    @Override
    public double calculerImpact() {
        switch (typeEnergie.toLowerCase()) {
            case "electricite":
                impactCarbone = consommationEnergie * 1.5;
                break;
            case "gaz":
                impactCarbone = consommationEnergie * 2.0;
                break;
            default:
                impactCarbone = 0;
                System.out.println("Type d'énergie inconnu.");
        }
        return impactCarbone;
    }
}
