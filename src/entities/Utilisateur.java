package entities;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private long id;
    private String name;
    private int age;
    private List<CarbonConsommation> consommation;

    public Utilisateur(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.consommation = new ArrayList<>();
    }

    public Utilisateur() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarbonConsommation> getConsommation() {
        return consommation;
    }

    public void setConsommation(List<CarbonConsommation> consommation) {
        this.consommation = consommation;
    }


    public double calculerConsommationTotale() {
        double total = 0.0;
        for (CarbonConsommation consommation : this.consommation) {
            total += consommation.getCarbonAmount();
        }
        return total;
    }

}
