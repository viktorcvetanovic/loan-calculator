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

-- Dumping structure for table loan_calculator.request
CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_time` varchar(128) DEFAULT NULL,
  `fk_simple_loan` int(11) DEFAULT NULL,
  `fk_loan` int(11) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `request_simple_loan` (`fk_simple_loan`),
  KEY `request_loan` (`fk_loan`),
  CONSTRAINT `request_loan` FOREIGN KEY (`fk_loan`) REFERENCES `loan` (`loan_id`),
  CONSTRAINT `request_simple_loan` FOREIGN KEY (`fk_simple_loan`) REFERENCES `simple_loan` (`simple_loan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.request: ~0 rows (approximately)
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table loan_calculator.simple_loan: ~0 rows (approximately)
/*!40000 ALTER TABLE `simple_loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `simple_loan` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
