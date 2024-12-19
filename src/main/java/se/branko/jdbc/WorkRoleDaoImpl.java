package se.branko.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDaoImpl implements WorkRoleDao {

    private Connection connection;

    public WorkRoleDaoImpl(Connection connection) {
        this.connection = connection;

    }
    @Override
    public List<WorkRole> getAllWorkRoles() throws SQLException {
        String sql = "SELECT * FROM work_role";
        List<WorkRole> workRoles = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                workRoles.add(mapResultSetToWorkRole(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return workRoles;
    }

    @Override
    public WorkRole getWorkRole(Integer roleId) throws SQLException {
        String sql = "SELECT * FROM work_role WHERE role_id = ?";
        WorkRole workRole = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, roleId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                workRole = mapResultSetToWorkRole(rs);
                System.out.println(workRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return workRole;
    }
    public void insertWorkRole(WorkRole workRole) throws SQLException {

        String sql = "INSERT INTO work_role(title, description, salary, creation_date) VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, new java.sql.Date(workRole.getCreateDate().getTime()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }


    @Override
    public void updateWorkRole(WorkRole workRole) throws SQLException {
        String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, new java.sql.Date(workRole.getCreateDate().getTime()));  // Omvandla till SQL Date
            pstmt.setInt(5, workRole.getRoleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    @Override
    public void deleteWorkRole(Integer roleId) throws SQLException {
        String sql = "DELETE FROM work_role WHERE role_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, roleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Kasta vidare undantaget
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }
    public void deleteAllWorkRoles() throws SQLException {
        String sql = "DELETE FROM work_role";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }
    
    private WorkRole mapResultSetToWorkRole(ResultSet rs) throws SQLException {
        Integer roleId = rs.getInt("role_id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        double salary = rs.getDouble("salary");
        Date creationDate = rs.getDate("creation_date");

        // Skapa ett WorkRole-objekt
        WorkRole workRole = new WorkRole(roleId, title, description, salary);
        workRole.setCreateDate(creationDate);

        return workRole;
    }
}


