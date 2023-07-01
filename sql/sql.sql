-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for loan_calculator
CREATE DATABASE IF NOT EXISTS `loan_calculator` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `loan_calculator`;

-- Dumping structure for table loan_calculator.loan
CREATE TABLE IF NOT EXISTS `loan` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `loan_amount` double DEFAULT NULL,
  `loan_interest` double DEFAULT NULL,
  `loan_number_of_payments` int(11) DEFAULT NULL,
  `loan_payment_frequency` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`loan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.loan: ~0 rows (approximately)
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;

-- Dumping structure for table loan_calculator.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_number` int(11) DEFAULT NULL,
  `loan_payment` double DEFAULT NULL,
  `ending_balance` double DEFAULT NULL,
  `principal_applied` double DEFAULT NULL,
  `interest_payment` double DEFAULT NULL,
  `loan_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `payment_loan` (`loan_fk`),
  CONSTRAINT `payment_loan` FOREIGN KEY (`loan_fk`) REFERENCES `loan` (`loan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.payment: ~0 rows (approximately)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumping structure for table loan_calculator.request
CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_time` varchar(128) DEFAULT NULL,
  `request_method` varchar(128) DEFAULT NULL,
  `request_path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.request: ~2 rows (approximately)
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` (`request_id`, `request_time`, `request_method`, `request_path`) VALUES
	(1, 'Sat Jul 01 14:27:04 CEST 2023', 'POST', ''),
	(2, 'Sat Jul 01 14:27:31 CEST 2023', 'POST', '/loans/simple');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;

-- Dumping structure for table loan_calculator.simple_loan
CREATE TABLE IF NOT EXISTS `simple_loan` (
  `simple_loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `simple_loan_amount` double DEFAULT NULL,
  `simple_loan_interest` int(11) DEFAULT NULL,
  `simple_loan_term` int(11) DEFAULT NULL,
  `simple_loan_term_type` varchar(32) DEFAULT NULL,
  `monthly_payment` double DEFAULT NULL,
  `total_interest_paid` double DEFAULT NULL,
  PRIMARY KEY (`simple_loan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.simple_loan: ~2 rows (approximately)
/*!40000 ALTER TABLE `simple_loan` DISABLE KEYS */;
INSERT INTO `simple_loan` (`simple_loan_id`, `simple_loan_amount`, `simple_loan_interest`, `simple_loan_term`, `simple_loan_term_type`, `monthly_payment`, `total_interest_paid`) VALUES
	(11, 500, 5, 2, '1', 251.56358281358382, 3.127165627167642),
	(12, 500, 5, 2, '1', 251.56358281358382, 3.127165627167642);
/*!40000 ALTER TABLE `simple_loan` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
