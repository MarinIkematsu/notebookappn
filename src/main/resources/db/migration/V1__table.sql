CREATE TABLE `Users` (
	`user_id` SERIAL NOT NULL,
	`username` VARCHAR(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`password` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`create_at` DATETIME,
	`update_at` DATETIME,
	`delete_at` DATETIME,
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;
