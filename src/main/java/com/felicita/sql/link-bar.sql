CREATE TABLE `link_bar_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `link_bar_type_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `link_bar_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `common_status_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `link_bar_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `link_bar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link_bar_type_id` int NOT NULL,
  `icon_url` varchar(100) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `link_bar_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `link_bar_type_id` (`link_bar_type_id`),
  KEY `link_bar_status_id` (`link_bar_status_id`),
  CONSTRAINT `link_bar_ibfk_1` FOREIGN KEY (`link_bar_type_id`) REFERENCES `link_bar_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `link_bar_ibfk_2` FOREIGN KEY (`link_bar_status_id`) REFERENCES `link_bar_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);