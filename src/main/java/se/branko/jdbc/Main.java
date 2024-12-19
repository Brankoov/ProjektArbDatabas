package se.branko.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WorkRoleDaoImpl dao = null;
        Connection connection = null;

        try {
//            // Hämta anslutning från JDBCUtil
            connection = JDBCUtil.getConnection();
            dao = new WorkRoleDaoImpl(connection);
//
//            //Skapa en ny arbetsroll och sätta in

//            WorkRole newRole = new WorkRole("Software Developer", "Developing software..", 10000.00);
//            dao.insertWorkRole(newRole);

            //Hämta en workrole
//            dao.getWorkRole(6);

//            //Hämta och uppdatera för att sedan hämta igen för att kunna se ändringar.
//            WorkRole workrole = dao.getWorkRole(6);
//
//            if (workrole != null) {
//                workrole.setTitle("Doktor");
//                workrole.setDescription("Doktoring");
//                workrole.setSalary(50000.00);
//
//                dao.updateWorkRole(workrole);
//                System.out.println("Arbetsrollen har uppdaterats");
//            }
//            else {
//                System.out.println("Arbetsrollen hittades inte");
//            }

//            dao.getWorkRole(10);


//            //Hämta alla arbetsroller
//            List<WorkRole> workRoles = dao.getAllWorkRoles();
//            System.out.println("Alla arbetsroller:");
//            for (WorkRole role : workRoles) {
//                System.out.println(role);
//            }
//            //Radera alla
//            dao.deleteAllWorkRoles();

//            //Radera en
//            dao.deleteWorkRole(6);

            JDBCUtil.commit(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtil.rollback(connection);  // Rollback vid fel
        } finally {
            JDBCUtil.closeConnection(connection);  // Stäng anslutningen
        }
    }
}



