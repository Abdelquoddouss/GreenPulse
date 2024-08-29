package services;

import entities.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateur {
    private List<Utilisateur> utilisateurs;

    public GestionUtilisateur() {
        this.utilisateurs = new ArrayList<>();
    }

    public Utilisateur creerUtilisateur(long id, String name, int age) {
        Utilisateur nouvelUtilisateur = new Utilisateur(id, name, age);
        utilisateurs.add(nouvelUtilisateur);
        return nouvelUtilisateur;
    }
}
