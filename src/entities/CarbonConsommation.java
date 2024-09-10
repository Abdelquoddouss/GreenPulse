package entities;

import java.time.LocalDate;

public abstract class CarbonConsommation {
    protected double impactCarbone;
    private LocalDate startDate;
    private LocalDate endDate;
    private String consommationType;
    private int quantite;

    // Constructor with start and end date
    public CarbonConsommation(LocalDate startDate, LocalDate endDate, String consommationType, int quantite) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.consommationType = consommationType;
        this.quantite = quantite;
    }




    // Getters and Setters
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getConsommationType() {
        return consommationType;
    }

    public void setConsommationType(String consommationType) {
        this.consommationType = consommationType;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculerImpact();

    // Get the impact carbone value
    public double getImpactCarbone() {
        return impactCarbone;
    }

}
