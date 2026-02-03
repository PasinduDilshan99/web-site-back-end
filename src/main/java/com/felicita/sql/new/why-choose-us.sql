CREATE TABLE `why_choose_us_status` (
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
  CONSTRAINT `why_choose_us_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `why_choose_us` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `sub_title` varchar(100) DEFAULT NULL,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `clicked_url` varchar(255) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `why_choose_us_status_id` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `why_choose_us_status_id` (`why_choose_us_status_id`),
  CONSTRAINT `why_choose_us_ibfk_1` FOREIGN KEY (`why_choose_us_status_id`) REFERENCES `why_choose_us_status` (`id`)
);