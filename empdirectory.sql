-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2025 at 09:33 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `empdirectory`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adm_id` varchar(10) NOT NULL,
  `admName` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `hiringDate` date NOT NULL,
  `isManager` tinyint(1) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `isAgreement` tinyint(1) NOT NULL,
  `dep_id` varchar(10) NOT NULL,
  `post_id` varchar(10) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adm_id`, `admName`, `phone`, `email`, `hiringDate`, `isManager`, `isActive`, `isAgreement`, `dep_id`, `post_id`, `password`) VALUES
('AD-0001', 'Naychi', '09764701330', 'naychi@gmail.com', '2025-06-01', 1, 1, 1, 'DP-01', 'PT-01', '123456'),
('AD-0002', 'Ko Ko', '09764701559', 'koko@gmail.com', '2025-06-08', 1, 1, 1, 'DP-02', 'PT-02', '123465'),
('AD-0003', 'Mg Mg', '09764701667', 'mgmg@gmail.com', '2025-06-08', 1, 1, 1, 'DP-02', 'PT-01', '123654'),
('AD-0004', 'Ma Ma', '09764708765', 'mama@gmail.com', '2025-06-08', 1, 1, 1, 'DP-05', 'PT-03', '436733'),
('AD-0005', 'Chi Chi', '09764709876', 'chichi@gmail.com', '2025-06-08', 1, 1, 1, 'DP-02', 'PT-05', '123465');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dep_id` varchar(10) NOT NULL,
  `depName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_id`, `depName`) VALUES
('DP-01', 'Software'),
('DP-02', 'Network'),
('DP-03', 'Account'),
('DP-04', 'HR'),
('DP-05', 'Design');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` varchar(10) NOT NULL,
  `empName` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `hiringDate` date NOT NULL,
  `isManager` tinyint(1) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `isAgreement` tinyint(1) NOT NULL,
  `dep_id` varchar(10) NOT NULL,
  `post_id` varchar(10) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `post_id` varchar(10) NOT NULL,
  `postName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`post_id`, `postName`) VALUES
('PT-01', 'Programmer'),
('PT-02', 'Tester'),
('PT-03', 'Manager'),
('PT-04', 'Senior Programmer'),
('PT-05', 'Project Leader');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adm_id`),
  ADD KEY `admin_ibfk_1` (`dep_id`),
  ADD KEY `admin_ibfk_2` (`post_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`dep_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `employee_ibfk_1` (`dep_id`),
  ADD KEY `employee_ibfk_2` (`post_id`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`post_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`),
  ADD CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `position` (`post_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`),
  ADD CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `position` (`post_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
