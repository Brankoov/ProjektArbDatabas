package se.branko.jdbc;
import java.io.InputStream;
import java.sql.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
//            System.out.println("Inläsning OK");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }


    public static Connection getConnection() throws SQLException {
        Driver hsqlDriver = new org.hsqldb.jdbc.JDBCDriver();
        DriverManager.registerDriver(hsqlDriver);

        String dbURL = properties.getProperty("db.url");
        System.out.println("Database URL: " + dbURL);

        String userId = properties.getProperty("db.user");
//        System.out.println("User ID: " + userId);

        String password = properties.getProperty("db.password");
//        System.out.println("Password: " + password);

         //Registrera databaskopplingsdrivrutinen om nödvändigt
        try {
            if (dbURL.startsWith("jdbc:hsqldb")) {
                DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
            } else if (dbURL.startsWith("jdbc:h2")) {
                // Ingen registrering behövs
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Skapa anslutning
        Connection conn = DriverManager.getConnection(dbURL, userId, password);
        conn.setAutoCommit(false);
        return conn;
    }

    // Metod: closeConnection()
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metod: closeStatement()
    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metod: closeResultSet()
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metod: commit()
    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
                System.out.println("Ändringar sparade i databasen!");
            }
        } catch (SQLException e) {
            System.out.println("Fel vid commit. Ändringar sparades inte.");
            e.printStackTrace();
        }
    }

    // Metod: rollback()
    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    // Metod: getDatabaseProductName()
//    public static String getDatabaseProductName(Connection conn) {
//        try {
//            if (conn != null) {
//                DatabaseMetaData metadata = conn.getMetaData();
//                return metadata.getDatabaseProductName();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}