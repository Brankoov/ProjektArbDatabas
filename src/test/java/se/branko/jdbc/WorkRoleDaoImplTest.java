package se.branko.jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkRoleDaoImplTest {

    private Connection connection;
    private WorkRoleDao dao;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = JDBCUtil.getConnection();
        dao = new WorkRoleDaoImpl(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Stäng anslutningen efter varje test
        JDBCUtil.closeConnection(connection);
    }

    @Test
    public void testTableCreation() {
        // Hantera ResultSet korrekt
        try (ResultSet rs = connection.getMetaData().getTables(null, null, "WORK_ROLE", null)) {
            assertTrue(rs.next(), "Tabellen WORK_ROLE bör finnas.");
        } catch (SQLException e) {
            fail("Ett fel inträffade vid hämtning av tabellmetadata: " + e.getMessage());
        }
    }

//    @Test
//    public void testConnection() throws SQLException {
//        // Kontrollera om anslutningen från JDBCUtil är korrekt
//        assertNotNull(connection, "Connection should not be null");
//        System.out.println("Connection is valid: " + connection.isValid(1));
//        System.out.println("Url: " + connection.getMetaData().getURL());
//    }
    @Test
    public void testInsertAndGetAllWorkRoles() throws SQLException {
        // Skapa en ny arbetsroll
        WorkRole newRole = new WorkRole("Developer", "Responsible for developing software", 50000.00);

        // Lägg till arbetsrollen i databasen
        dao.insertWorkRole(newRole);

        // Hämta alla arbetsroller
        List<WorkRole> allRoles = dao.getAllWorkRoles();

        // Kontrollera att listan inte är tom
        assertFalse(allRoles.isEmpty(), "Det ska finnas arbetsroller i listan.");

        // Kontrollera att den skapade arbetsrollen finns i listan
        boolean roleExists = allRoles.stream()
                .anyMatch(role -> {
                    boolean matches = role.getTitle().equals("Developer") &&
                            role.getDescription().equals("Responsible for developing software") &&
                            role.getSalary() == 50000.00;
                    if (matches) {
                        System.out.println("Arbetsrollen finns i listan: " + role.getTitle());
                    }
                    return matches;

                });

        assertTrue(roleExists, "Den skapade arbetsrollen ska finnas i listan.");
    }

}