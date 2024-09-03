package entities;

import java.time.LocalDate;

public class CarbonConsommation {
    private LocalDate startDate;
    private LocalDate endDate;
    private double carbonAmount;
    public CarbonConsommation(LocalDate startDate, LocalDate endDate, double carbonAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.carbonAmount = carbonAmount;
    }
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
    public double getCarbonAmount() {
        return carbonAmount;
    }
    public void setCarbonAmount(double carbonAmount) {
        this.carbonAmount = carbonAmount;
    }
}
