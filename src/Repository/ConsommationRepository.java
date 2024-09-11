package Repository;

import entities.Alimentation;
import entities.CarbonConsommation;
import entities.Logement;
import entities.Transport;

import java.sql.*;

public class ConsommationRepository {

    private  Connection connection;

    public ConsommationRepository(Connection connection) {
        this.connection = connection;
    }

    public void ajouterConsommation(int utilisateurId, CarbonConsommation consommation, double impactCarbone) {

        String query = "INSERT INTO consommations (utilisateur_id, consommationType, consomationImpact, startDate, endDate,quantite) VALUES (?, ?, ?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, utilisateurId);
            ps.setString(2, consommation.getClass().getSimpleName().toLowerCase());
            ps.setDouble(3, impactCarbone);
            ps.setDate(4, Date.valueOf(consommation.getStartDate()));
            ps.setDate(5, Date.valueOf(consommation.getEndDate()));
            ps.setInt(6, consommation.getQuantite());
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
        String query = "INSERT INTO transport (consommation_id, distance_parcourue, type_vehicule) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, obtenirDernierIdConsommation());  // Obtention de l'ID de la consommation récemment insérée
            ps.setDouble(2, consommation.getDistanceParcourue());
            ps.setString(3, consommation.getTypeDeVehicule());

            // Utilisation de executeUpdate() pour l'insertion
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



    private int obtenirDernierIdConsommation() throws SQLException {
        String query = "SELECT currval(pg_get_serial_sequence('consommations','id'))";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }







}
