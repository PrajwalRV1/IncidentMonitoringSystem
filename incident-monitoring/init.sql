DELIMITER //

CREATE PROCEDURE insert_incident(
    IN p_title VARCHAR(255),
    IN p_location VARCHAR(255),
    IN p_severity VARCHAR(20),
    IN p_status VARCHAR(20)
)
BEGIN
    INSERT INTO incidents (title, location, severity, status, timestamp)
    VALUES (p_title, p_location, p_severity, p_status, NOW());
END //

CREATE PROCEDURE get_all_incidents()
BEGIN
    SELECT * FROM incidents;
END //

DELIMITER ;

CREATE TABLE incidents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    severity ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
    status ENUM('OPEN', 'IN_PROGRESS', 'RESOLVED') NOT NULL,
    timestamp DATETIME NOT NULL
);