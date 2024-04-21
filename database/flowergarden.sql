-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 21 Apr 2024 pada 10.01
-- Versi server: 10.5.20-MariaDB
-- Versi PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id22068716_flowergarden`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `images` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `firstName`, `lastName`, `password`, `images`) VALUES
(1, 'sally', 'sally@gmail.com', 'Sally', 'Angela', 'sally', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfeaV82gn9UVb12deCLv3I7jvOC5jdkPnw6WpqLzx49g&s'),
(2, 'aileen', 'ai@gmail.com', 'Aileen', 'Averina', 'aileen', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo-707V1FKLMWyirgGmL3FJQGkXdoaNrCXuqcRbDcYRQf3uHw6gywNkcWq'),
(3, 'ket', 'ket', 'Ketrin', 'BC', 'ket', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo-707V1FKLMWyirgGmL3FJQGkXdoaNrCXuqcRbDcYRQf3uHw6gywNkcWq');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
