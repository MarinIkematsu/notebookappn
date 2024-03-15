CREATE TABLE `Notes` (
	`note_id` SERIAL NOT NULL,
	`date_id` BIGINT(16) NOT NULL,
	`title` VARCHAR(128) NOT NULL,
	`content` TEXT NOT NULL,
	`create_at` DATETIME,
	`update_at` DATETIME,
	`delete_at` DATETIME,
	PRIMARY KEY (`note_id`)
) ENGINE=InnoDB;