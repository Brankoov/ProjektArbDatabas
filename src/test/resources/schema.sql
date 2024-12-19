DROP TABLE IF EXISTS WORK_ROLE;
CREATE TABLE work_role
(
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    salary DOUBLE NOT NULL,
    creation_date DATE
);