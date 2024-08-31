package services;

import entities.Utilisateur;
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
        return utilisateurs;
    }

    public Utilisateur rechercheUserById(long id){
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id) {
                return utilisateur;
            }
        }
        return null;
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




    }



