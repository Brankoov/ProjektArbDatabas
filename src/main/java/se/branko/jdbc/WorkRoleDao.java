package se.branko.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDao {
    public List<WorkRole> getAllWorkRoles() throws SQLException;

    public WorkRole getWorkRole(Integer id) throws SQLException;

    public void insertWorkRole(WorkRole workRole) throws SQLException;

    public void updateWorkRole(WorkRole workRole) throws SQLException;

    public void deleteWorkRole(Integer workRole) throws SQLException;
}
