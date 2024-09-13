package Repository;

import configuration.Connextion;
import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsommationRepository {

    private static Connection connection = Connextion.getInstance().getConnection();
    private static Map<Integer, List<CarbonConsommation>> consommationsParUtilisateur = new HashMap<>();


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
        String query = "INSERT INTO logement (consommation_id, consommation_energie, type_energie) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, obtenirDernierIdConsommation()); // Obtenir l'ID de la consommation récemment insérée
            ps.setDouble(2, consommation.getConsommationEnergie());
            ps.setString(3, consommation.getTypeEnergie());
            ps.executeUpdate();
        }
    }

    private void ajouterAlimentation(Alimentation consommation) throws SQLException {
        String query = "INSERT INTO alimentation (consommation_id, poids, type_aliment) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, obtenirDernierIdConsommation());
            ps.setDouble(2, consommation.getPoids());
            ps.setString(3, consommation.getTypeAliment());
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

    public List<CarbonConsommation> obtenirConsommationsParUtilisateur(int utilisateurId) {
        List<CarbonConsommation> consommations = new ArrayList<>();
        String query = "SELECT * FROM consommations WHERE utilisateur_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, utilisateurId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String typeConsommation = rs.getString("consommationType");
                    LocalDate startDate = rs.getDate("startDate").toLocalDate();
                    LocalDate endDate = rs.getDate("endDate").toLocalDate();
                    int quantite = rs.getInt("quantite");
                    double impactCarbone = rs.getDouble("consomationImpact");

                    CarbonConsommation consommation = null;

                    switch (typeConsommation) {
                        case "transport":
                            consommation = obtenirTransport(rs.getInt("id"), startDate, endDate, quantite);
                            break;
                        case "logement":
                            consommation = obtenirLogement(rs.getInt("id"), startDate, endDate, quantite);
                            break;
                        case "alimentation":
                            consommation = obtenirAlimentation(rs.getInt("id"), startDate, endDate, quantite);
                            break;
                    }

                    if (consommation != null) {
                        consommation.setImpactCarbone(impactCarbone);
                        consommations.add(consommation);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consommations;
    }

    private Transport obtenirTransport(int id, LocalDate startDate, LocalDate endDate, int quantite) throws SQLException {
        String query = "SELECT * FROM transport WHERE consommation_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double distanceParcourue = rs.getDouble("distance_parcourue");
                    String typeVehicule = rs.getString("type_vehicule");
                    return new Transport(distanceParcourue, typeVehicule, startDate, endDate, "transport", quantite);
                }
            }
        }
        return null;
    }

    private Logement obtenirLogement(int id, LocalDate startDate, LocalDate endDate, int quantite) throws SQLException {
        String query = "SELECT * FROM logement WHERE consommation_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double consommationEnergie = rs.getDouble("consommation_energie");
                    String typeEnergie = rs.getString("type_energie");
                    return new Logement(consommationEnergie, typeEnergie, startDate, endDate, "logement", quantite);
                }
            }
        }
        return null;
    }

    private Alimentation obtenirAlimentation(int id, LocalDate startDate, LocalDate endDate, int quantite) throws SQLException {
        String query = "SELECT * FROM alimentation WHERE consommation_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double poids = rs.getDouble("poids");
                    String typeAliment = rs.getString("type_aliment");
                    return new Alimentation(startDate, endDate, poids, typeAliment, quantite);
                }
            }
        }
        return null;
    }


    public static Double getTotalConsommation(Utilisateur user){
        String query = "SELECT SUM(consomationImpact) FROM consommations WHERE utilisateur_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


}
