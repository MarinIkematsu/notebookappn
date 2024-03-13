CREATE TABLE Dates (
    `date_id` SERIAL NOT NULL,
    `user_id` BIGINT(16) NOT NULL,
    `note_date` DATE,
    `create_at` DATETIME,
	`update_at` DATETIME,
	`delete_at` DATETIME
)ENGINE=InnoDB;
