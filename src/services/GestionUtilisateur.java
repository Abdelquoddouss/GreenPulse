package services;

import entities.CarbonConsommation;
import entities.Utilisateur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateur {
    private static long idCounter = 1;
    private List<Utilisateur> utilisateurs;



    public GestionUtilisateur() {
        this.utilisateurs = new ArrayList<>();
    }

    public Utilisateur creerUtilisateur( String name, int age) {
        long id = idCounter++;
        Utilisateur nouvelUtilisateur = new Utilisateur(id, name, age);
        utilisateurs.add(nouvelUtilisateur);
        return nouvelUtilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

    public Utilisateur rechercheUserById(long id){
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id) {
                return utilisateur;
            }
        }
        return null;
    }

    public boolean ajouterConsommation(long id, LocalDate startDate, LocalDate endDate, double carbonAmount) {
        Utilisateur utilisateur = rechercheUserById(id);
        if (utilisateur != null) {
            CarbonConsommation nouvelleConsommation = new CarbonConsommation(startDate, endDate, carbonAmount);
            utilisateur.getConsommation().add(nouvelleConsommation);

            return true;
        }
        return false;
    }


    public boolean modifierUtilisateur(long id, String newName, int newAge) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id){
                utilisateur.setName(newName);
                utilisateur.setAge(newAge);
                return true;
            }
        }


        return false;
    }

    public boolean existeUtilisateur(long id) {
        return utilisateurs.stream().anyMatch(utilisateur -> utilisateur.getId() == id);
    }


    public boolean supprimerUtilisateur(long id) {
        return utilisateurs.removeIf(utilisateur -> utilisateur.getId() == id);
    }


    }



