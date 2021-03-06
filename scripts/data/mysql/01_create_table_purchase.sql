use demo;

create table purchase (
	id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	customer_id INT,
    product VARCHAR(50),
    price DECIMAL(11,2),
	CREATE_TS TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	UPDATE_TS TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
