CREATE DATABASE `coo`;
use coo;

CREATE TABLE `task` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;