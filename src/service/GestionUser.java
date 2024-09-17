package service;

import Repository.ConsommationRepository;
import Repository.UserRepository;
import entities.CarbonConsommation;
import entities.Utilisateur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GestionUser {

    private UserRepository userRepository;

    private ConsommationRepository consommationRepository;


    public GestionUser() {
        this.userRepository = new UserRepository();
        this.consommationRepository = new ConsommationRepository();
    }


    public void createUser(Utilisateur utilisateur) {
        userRepository.create(utilisateur);
        System.out.println("✅ Utilisateur créé avec succès !");
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

    public  List<Utilisateur> filterUsersByTotalConsommation() {

        List<Utilisateur> users = getAllUsers();

        return  users.stream()
                .filter(u -> consommationRepository.getTotalConsommation(u) > 3000)
                .collect(Collectors.toList());



    }

    public List<Utilisateur> getUsersSortedByTotalConsommation() {
        List<Utilisateur> utilisateurs = getAllUsers();

        return utilisateurs.stream()
                .sorted((user1, user2) -> Double.compare(
                        consommationRepository.getTotalConsommation(user2),
                        consommationRepository.getTotalConsommation(user1)
                ))
                .collect(Collectors.toList());
    }

    public ConsommationRepository getConsommationRepository() {
        return consommationRepository;
    }





}
