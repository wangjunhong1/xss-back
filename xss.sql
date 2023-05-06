DROP TABLE IF EXISTS `detect_result` CASCADE;

DROP TABLE IF EXISTS `detect_recorder` CASCADE;

DROP TABLE IF EXISTS `text` CASCADE;

DROP TABLE IF EXISTS `file_detect_recorder` CASCADE;

DROP TABLE IF EXISTS `file` CASCADE;

DROP TABLE IF EXISTS `file_upload_recorder` CASCADE;

DROP TABLE IF EXISTS `user` CASCADE;

CREATE TABLE `detect_result`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`detect_recorder_id` BIGINT,
	`result` VARCHAR(50),
	`keyword` VARCHAR(256),
	CONSTRAINT `PK_detect_result` PRIMARY KEY (`id`)
)
;

CREATE TABLE `detect_recorder`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`text_id` BIGINT,
	`use_model` VARCHAR(50),
	`date` DATETIME(0),
	CONSTRAINT `PK_detect_result` PRIMARY KEY (`id`)
)
;

CREATE TABLE `text`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`url` TEXT,
	`file_id` BIGINT,
	CONSTRAINT `PK_text` PRIMARY KEY (`id`)
)
;

CREATE TABLE `file_detect_recorder`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`file_id` BIGINT,
	`start_time` DATETIME(0),
	`finish_time` DATETIME(0),
	`use_model` VARCHAR(50),
	CONSTRAINT `PK_file_detect_recorder` PRIMARY KEY (`id`)
)
;

CREATE TABLE `file`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`file_name` VARCHAR(512),
	`file_size` FLOAT(8,2),
	`recoder_num` INT,
	CONSTRAINT `PK_File` PRIMARY KEY (`id`)
)
;

CREATE TABLE `file_upload_recorder`
(
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`file_id` BIGINT,
	`src_file_name` VARCHAR(256),
	`upload_date` DATETIME(0),
	CONSTRAINT `PK_file_upload` PRIMARY KEY (`id`)
)
;

CREATE TABLE `user`
(
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`username` VARCHAR(128),
	`password` VARCHAR(64),
	CONSTRAINT `PK_user` PRIMARY KEY (`id`)
)
;

ALTER TABLE `detect_result` 
 ADD INDEX `IXFK_detect_result_detect_recorder` (`detect_recorder_id` ASC)
;

ALTER TABLE `detect_recorder` 
 ADD INDEX `IXFK_detect_recorder_text` (`text_id` ASC)
;

ALTER TABLE `text` 
 ADD INDEX `IXFK_text_file` (`file_id` ASC)
;

ALTER TABLE `file_detect_recorder` 
 ADD INDEX `IXFK_file_detect_recorder_File` (`file_id` ASC)
;

ALTER TABLE `file_upload_recorder` 
 ADD INDEX `IXFK_file_upload_File` (`file_id` ASC)
;

ALTER TABLE `detect_result` 
 ADD CONSTRAINT `FK_detect_result_detect_recorder`
	FOREIGN KEY (`detect_recorder_id`) REFERENCES `detect_recorder` (`id`) ON DELETE Set Null ON UPDATE Cascade
;

ALTER TABLE `detect_recorder` 
 ADD CONSTRAINT `FK_detect_recorder_text`
	FOREIGN KEY (`text_id`) REFERENCES `text` (`id`) ON DELETE Set Null ON UPDATE Cascade
;

ALTER TABLE `text` 
 ADD CONSTRAINT `FK_text_file`
	FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE Set Null ON UPDATE Cascade
;

ALTER TABLE `file_detect_recorder` 
 ADD CONSTRAINT `FK_file_detect_recorder_File`
	FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE Set Null ON UPDATE Cascade
;

ALTER TABLE `file_upload_recorder` 
 ADD CONSTRAINT `FK_file_upload_File`
	FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE Set Null ON UPDATE Cascade
;

SET FOREIGN_KEY_CHECKS=1
