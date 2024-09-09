package service;

import Repository.UserRepository;
import entities.Utilisateur;

import java.util.List;

public class GestionUser {

    private UserRepository userRepository;

    public GestionUser() {
        this.userRepository = new UserRepository();
    }

    public void createUser(Utilisateur utilisateur) {
        userRepository.create(utilisateur);
        System.out.println("Utilisateur créé avec succès.");
    }

    public List<Utilisateur> getAllUsers() {
        return userRepository.getAllUsers();
    }


    public void updateUser(Utilisateur utilisateur) {
        userRepository.update(utilisateur);
        System.out.println("Utilisateur mis à jour avec succès.");
    }

    public Utilisateur getUserById(long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(Utilisateur utilisateur) {
        userRepository.delete(utilisateur);
        System.out.println("Utilisateur supprimé avec succès.");
    }

}
