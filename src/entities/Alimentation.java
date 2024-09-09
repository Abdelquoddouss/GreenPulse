package entities;

public class Alimentation extends CarbonConsommation {
        private double poids;
        private String typeAliment;

        public Alimentation(double poids, String typeAliment) {
            this.poids = poids;
            this.typeAliment = typeAliment;
        }

        @Override
        public double calculerImpact() {
            switch (typeAliment.toLowerCase()) {
                case "viande":
                    impactCarbone = poids * 5.0;
                    break;
                case "legume":
                    impactCarbone = poids * 0.5;
                    break;
                default:
                    impactCarbone = 0;
                    System.out.println("Type d'aliment inconnu.");
            }
            return impactCarbone;
        }
}
