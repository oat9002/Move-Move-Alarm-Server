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
-- Table structure for table `userActivity_progress_week`
--

-- CREATE TABLE IF NOT EXISTS `userActivity_progress_week` (
--   `id` int(11) NOT NULL,
--   `userID` int(11) NOT NULL,
--   `totalExerciseTime` double NOT NULL DEFAULT '0',
--   `numberOfAccept` int(11) NOT NULL DEFAULT '0',
--   `totalActivity` int(11) NOT NULL DEFAULT '0',
--   `neck` int(11) NOT NULL DEFAULT '0',
--   `shoulder` int(11) NOT NULL DEFAULT '0',
--   `chest_back` int(11) NOT NULL DEFAULT '0',
--   `wrist` int(11) NOT NULL DEFAULT '0',
--   `waist` int(11) NOT NULL DEFAULT '0',
--   `hip_leg_calf` int(11) NOT NULL DEFAULT '0',
--   `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `modifiedDate` datetime DEFAULT NULL
-- ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `userActivity_progress_week`
--

INSERT INTO `userActivity_progress_week` (`id`, `userID`, `totalExerciseTime`, `numberOfAccept`, `totalActivity`, `neck`, `shoulder`, `chest_back`, `wrist`, `waist`, `hip_leg_calf`, `createdDate`, `modifiedDate`) VALUES
(8, 3, -6, 0, -6, -6, -6, -6, -6, -6, -6, '2016-06-01 04:43:31', '2016-06-02 03:09:20'),
(9, 4, -7, 4, -7, -7, -7, -7, -7, -7, -7, '2016-06-22 07:01:07', '2016-06-22 09:20:12'),
(10, 5, -2, 0, -2, -2, -2, -2, -2, -2, -2, '2016-07-09 17:24:57', '2016-07-09 17:30:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `userActivity_progress_week`
--
ALTER TABLE `userActivity_progress_week`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userID` (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `userActivity_progress_week`
--
ALTER TABLE `userActivity_progress_week`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
