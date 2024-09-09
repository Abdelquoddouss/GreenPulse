package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connextion {

    // Instance unique (Singleton)
    private static Connextion instance = null;
    // Connexion JDBC unique
    private Connection connection = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/co2";
    private static final String USER = "GreenPulse";
    private static final String PASSWORD = "";

    private Connextion() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données PostgreSQL !");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la connexion à la base de données PostgreSQL.");
        }
    }

    public static Connextion getInstance() {
        if (instance == null) {
            instance = new Connextion();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }


}
