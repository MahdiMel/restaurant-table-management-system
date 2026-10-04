package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://aws-1-eu-west-3.pooler.supabase.com:6543/postgres?sslmode=require";
    private static final String USER = "postgres.fbgoyotxyqahgbmizwmn";

    private static final String PASSWORD = "VOTRE_MOT_DE_PASSE";

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion au serveur Supabase établie avec succès !");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Échec de la connexion : " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}