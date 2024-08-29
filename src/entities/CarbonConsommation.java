package entities;

import java.time.LocalDate;

public class CarbonConsommation {
    private LocalDate date;
    private double carbonAmount;

    public CarbonConsommation(LocalDate date, double carbonAmount) {
        this.date = date;
        this.carbonAmount = carbonAmount;
    }

    // Getters et setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCarbonAmount() {
        return carbonAmount;
    }

    public void setCarbonAmount(double carbonAmount) {
        this.carbonAmount = carbonAmount;
    }
}
