package entities;

import java.time.LocalDate;

public class Transport extends CarbonConsommation {


    private double distanceParcourue;
    private String typeDeVehicule;

    public Transport(double distanceParcourue, String typeDeVehicule, LocalDate startDate, LocalDate endDate, String consommationType, int quantite) {
        super(startDate, endDate, consommationType, quantite);
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
    }



    public double getDistanceParcourue() {
        return distanceParcourue;
    }
    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public String getTypeDeVehicule() {
        return typeDeVehicule;
    }

    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeDeVehicule = typeDeVehicule;
    }



    @Override
    public double calculerImpact() {
        switch (typeDeVehicule.toLowerCase()) {
            case "voiture":
                impactCarbone = distanceParcourue * 0.5; // Exemple: 0.5 kg CO₂ par km
                break;
            case "train":
                impactCarbone = distanceParcourue * 0.1; // Exemple: 0.1 kg CO₂ par km
                break;
            default:
                impactCarbone = 0;
                System.out.println("Type de véhicule inconnu.");
        }
        return impactCarbone;
    }


}
