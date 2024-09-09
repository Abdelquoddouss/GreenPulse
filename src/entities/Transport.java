package entities;

public class Transport extends CarbonConsommation {
    private double distanceParcourue;
    private String typeDeVehicule;

    public Transport(double distanceParcourue, String typeDeVehicule) {
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
    }

    @Override
    public double calculerImpact() {
        switch (typeDeVehicule.toLowerCase()) {
            case "voiture":
                impactCarbone = distanceParcourue * 0.5;
                break;
            case "train":
                impactCarbone = distanceParcourue * 0.1;
                break;
            default:
                impactCarbone = 0;
                System.out.println("Type de véhicule inconnu.");
        }
        return impactCarbone;
    }
}
