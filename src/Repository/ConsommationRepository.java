package Repository;

import entities.Alimentation;
import entities.CarbonConsommation;
import entities.Logement;
import entities.Transport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsommationRepository {

    private  Connection connection;

    public ConsommationRepository(Connection connection) {
        this.connection = connection;
    }

    public void ajouterConsommation(int utilisateurId, CarbonConsommation consommation, double impactCarbone) {

        String query = "INSERT INTO consommations (utilisateur_id, consommationType, consomationImpact) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, utilisateurId);
            ps.setString(2, consommation.getClass().getSimpleName().toLowerCase());
            ps.setDouble(3, impactCarbone);
            ps.executeUpdate();

            if (consommation instanceof Transport) {
                ajouterTransport((Transport) consommation);
            } else if (consommation instanceof Logement) {
                ajouterLogement((Logement) consommation);
            } else if (consommation instanceof Alimentation) {
                ajouterAlimentation((Alimentation) consommation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void ajouterTransport(Transport consommation) throws SQLException {
        String query = "INSERT INTO transport (distance_parcourue, type_vehicule) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, consommation.getQuantite());
            ps.setString(2, consommation.getConsommationType());
            ps.executeUpdate();
        }
    }

    private void ajouterLogement(Logement consommation) throws SQLException {
        String query = "INSERT INTO logement (consommation_energie, type_energie) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, consommation.getQuantite());
            ps.setString(2, consommation.getConsommationType());
            ps.executeUpdate();
        }
    }

    private void ajouterAlimentation(Alimentation consommation) throws SQLException {
        String query = "INSERT INTO alimentation (poids, type_aliment) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, consommation.getQuantite());
            ps.setString(2, consommation.getConsommationType());
            ps.executeUpdate();
        }
    }











}
