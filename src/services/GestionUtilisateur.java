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



}
