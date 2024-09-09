package entities;

import java.time.LocalDate;

public abstract class CarbonConsommation {
    protected double impactCarbone;

    public abstract double calculerImpact();


    public double getImpactCarbone() {
        return impactCarbone;
    }

}
