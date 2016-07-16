-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2016 at 04:28 PM
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
-- Table structure for table `groupActivity_progress_week`
--

-- CREATE TABLE IF NOT EXISTS `groupActivity_progress_week` (
--   `id` int(11) NOT NULL,
--   `groupID` int(11) NOT NULL,
--   `totalExerciseTime` double NOT NULL DEFAULT '0',
--   `numberOfAccept` int(11) NOT NULL DEFAULT '0',
--   `totalActivity` int(11) NOT NULL DEFAULT '0',
--   `neck` int(11) NOT NULL DEFAULT '0',
--   `shoulder` int(11) NOT NULL DEFAULT '0',
--   `chest_back` int(11) NOT NULL DEFAULT '0',
--   `wrist` int(11) NOT NULL DEFAULT '0',
--   `waist` int(11) NOT NULL DEFAULT '0',
--   `hip_leg_calf` int(11) NOT NULL DEFAULT '0',
--   `created_at` timestamp NULL DEFAULT NULL,
--   `updated_at` timestamp NULL DEFAULT NULL
-- ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `groupActivity_progress_week`
--

INSERT INTO `groupActivity_progress_week` (`id`, `groupID`, `totalExerciseTime`, `numberOfAccept`, `totalActivity`, `neck`, `shoulder`, `chest_back`, `wrist`, `waist`, `hip_leg_calf`, `created_at`, `updated_at`) VALUES
(1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 15:50:21', '0000-00-00 00:00:00'),
(2, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 15:50:24', '0000-00-00 00:00:00'),
(3, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 16:26:16', '0000-00-00 00:00:00'),
(4, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 16:26:19', '0000-00-00 00:00:00'),
(5, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 16:33:26', '0000-00-00 00:00:00'),
(6, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, '2016-05-31 16:33:28', '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `groupActivity_progress_week`
--
ALTER TABLE `groupActivity_progress_week`
  ADD PRIMARY KEY (`id`),
  ADD KEY `groupID` (`groupID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `groupActivity_progress_week`
--
ALTER TABLE `groupActivity_progress_week`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `groupActivity_progress_week`
--
ALTER TABLE `groupActivity_progress_week`
  ADD CONSTRAINT `groupActivity_progress_week_ibfk_1` FOREIGN KEY (`groupID`) REFERENCES `groups` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
