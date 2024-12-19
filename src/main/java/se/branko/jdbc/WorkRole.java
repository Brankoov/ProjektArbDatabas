package se.branko.jdbc;

import java.sql.Date;
import java.util.Objects;

public class WorkRole {
    private Integer roleId;
    private String title;
    private String description;
    private double salary;
    private java.sql.Date createDate;

    public WorkRole() {

    }
    public WorkRole(Integer roleId, String title, String description, double salary) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.createDate = new java.sql.Date(System.currentTimeMillis());
    }
    public WorkRole(String title, String description, double salary) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.createDate = new java.sql.Date(System.currentTimeMillis());
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "WorkRole{" +
                "roleId=" + roleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkRole workRole = (WorkRole) o;
        return Double.compare(salary, workRole.salary) == 0 && Objects.equals(roleId, workRole.roleId) && Objects.equals(title, workRole.title) && Objects.equals(description, workRole.description) && Objects.equals(createDate, workRole.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, title, description, salary, createDate);
    }
}
