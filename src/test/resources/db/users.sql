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
-- Table structure for table `users`
--

-- CREATE TABLE IF NOT EXISTS `users` (
--   `id` int(11) NOT NULL,
--   `firstName` text COLLATE utf8_unicode_ci,
--   `lastName` text COLLATE utf8_unicode_ci,
--   `userName` text COLLATE utf8_unicode_ci,
--   `birthday` text COLLATE utf8_unicode_ci,
--   `age` int(11) DEFAULT '0',
--   `score` int(11) DEFAULT '0',
--   `height` int(11) DEFAULT '0',
--   `weight` int(11) DEFAULT '0',
--   `waistline` int(11) DEFAULT '0',
--   `bmi` double DEFAULT '0',
--   `gender` int(11) DEFAULT '0',
--   `groupID` int(11) DEFAULT '0',
--   `email` text COLLATE utf8_unicode_ci,
--   `profileImage` int(11) DEFAULT '0',
--   `password` text COLLATE utf8_unicode_ci,
--   `facebookID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
--   `facebookFirstName` text COLLATE utf8_unicode_ci,
--   `facebookLastName` text COLLATE utf8_unicode_ci,
--   `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--   `updated_at` timestamp NULL DEFAULT NULL
-- ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstName`, `lastName`, `userName`, `birthday`, `age`, `score`, `height`, `weight`, `waistline`, `bmi`, `gender`, `groupID`, `email`, `profileImage`, `password`, `facebookID`, `facebookFirstName`, `facebookLastName`, `created_at`, `updated_at`) VALUES
(1, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, NULL, 'fb1083723354973258', 'Pakin', NULL, '2016-05-31 18:32:15', '2016-05-31 06:32:15'),
(2, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, NULL, '0', '0', NULL, '2016-06-01 03:24:34', '2016-06-01 03:24:34'),
(3, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, NULL, 'fb1039923566047311', 'Monthon', NULL, '2016-06-01 04:43:28', '2016-06-01 04:43:28'),
(4, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, NULL, 'fb1235215809827231', 'Puntavut', NULL, '2016-06-22 07:00:27', '2016-06-22 07:00:27'),
(5, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, NULL, 'fb10205444891267766', 'Pittayarat', NULL, '2016-07-09 17:24:39', '2016-07-09 05:24:39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `facebookID` (`facebookID`),
  ADD KEY `groupID` (`groupID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
