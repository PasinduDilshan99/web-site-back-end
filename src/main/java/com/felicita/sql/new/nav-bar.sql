CREATE TABLE `nav_bar_status` (
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
  KEY `fk_navbarstatus_commonstatus` (`common_status_id`),
  CONSTRAINT `fk_navbarstatus_commonstatus` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `nav_bar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_navbar_navbarstatus` (`status_id`),
  CONSTRAINT `fk_navbar_navbarstatus` FOREIGN KEY (`status_id`) REFERENCES `nav_bar_status` (`id`)
);

CREATE TABLE `nav_bar_submenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nav_bar_id` int NOT NULL,
  `parent_submenu_id` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link_url` varchar(255) NOT NULL,
  `icon_class` varchar(100) DEFAULT NULL,
  `sort_order` int DEFAULT '0',
  `opens_in_new_tab` tinyint(1) DEFAULT '0',
  `is_featured` tinyint(1) DEFAULT '0',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nav_bar_id` (`nav_bar_id`),
  KEY `parent_submenu_id` (`parent_submenu_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `nav_bar_submenu_ibfk_1` FOREIGN KEY (`nav_bar_id`) REFERENCES `nav_bar` (`id`) ON DELETE CASCADE,
  CONSTRAINT `nav_bar_submenu_ibfk_2` FOREIGN KEY (`parent_submenu_id`) REFERENCES `nav_bar_submenu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `nav_bar_submenu_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `nav_bar_status` (`id`)
);