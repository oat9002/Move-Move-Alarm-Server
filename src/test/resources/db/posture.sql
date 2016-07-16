-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2016 at 04:29 PM
-- Server version: 5.5.47-MariaDB
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `MoveAlarm`
--

-- --------------------------------------------------------

--
-- Table structure for table `posture`
--

-- CREATE TABLE IF NOT EXISTS `posture` (
--   `id` int(11) NOT NULL,
--   `title` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
--   `imageData` int(11) DEFAULT '0',
--   `description` text COLLATE utf8_unicode_ci,
--   `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   `modifiedDate` datetime DEFAULT NULL
-- ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `posture`
--

INSERT INTO `posture` (`id`, `title`, `imageData`, `description`, `createdDate`, `modifiedDate`) VALUES
(1, 'kadodtob', 0, 'kadodlaewtob', '2016-05-31 17:01:45', NULL),
(2, 'punchingleed', 0, 'awhoosaichubhookwa', '2016-05-31 17:10:29', NULL),
(3, 'squat', 0, 'idon''tknow', '2016-05-31 17:10:29', NULL),
(4, 'kidmaiaok', 0, NULL, '2016-05-31 17:10:29', NULL),
(5, 'kidmaiaokchingching', 0, NULL, '2016-05-31 17:10:29', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `posture`
--
ALTER TABLE `posture`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posture`
--
ALTER TABLE `posture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
