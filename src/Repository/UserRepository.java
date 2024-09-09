package Repository;

import configuration.Connextion;
import entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
        this.connection = Connextion.getInstance().getConnection();
    }


    public void create(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateurs (name, age) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, utilisateur.getName());
            ps.setInt(2, utilisateur.getAge());
            ps.executeUpdate();

            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
            e.printStackTrace();
        }
    }


    public List<Utilisateur> getAllUsers() {
        List<Utilisateur> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getLong("id"));
                utilisateur.setName(rs.getString("name"));
                utilisateur.setAge(rs.getInt("age"));
                users.add(utilisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs.");
            e.printStackTrace();
        }

        return users;
    }

    public void update(Utilisateur utilisateur) {
        String query = "UPDATE utilisateurs SET name = ?, age = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, utilisateur.getName());
            ps.setInt(2, utilisateur.getAge());
            ps.setLong(3, utilisateur.getId());
            ps.executeUpdate();

            System.out.println("Utilisateur mis à jour avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur.");
            e.printStackTrace();
        }
    }

    public Utilisateur getUserById(long id) {
        String query = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(rs.getLong("id"));
                    utilisateur.setName(rs.getString("name"));
                    utilisateur.setAge(rs.getInt("age"));
                    return utilisateur;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur.");
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Utilisateur utilisateur) {
        String query = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, utilisateur.getId());
            ps.executeUpdate();

            System.out.println("Utilisateur supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur.");
            e.printStackTrace();
        }
    }





 }