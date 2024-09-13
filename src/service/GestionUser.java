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

   public List<Utilisateur> filterUsersByTotalConsommation(){
         List<Utilisateur> utilisateurs = userRepository.getAllUsers();

         List<Utilisateur> orderUser = utilisateurs.stream()
                 .sorted((usr1 ,usr2) -> Double.compare(ConsommationRepository.getTotalConsommation(usr1),ConsommationRepository.getTotalConsommation(usr2)))
                    .collect(Collectors.toList());
            return orderUser;

  }








}
