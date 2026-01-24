CREATE TABLE `blog_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_category_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `blog_reactions_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `emoji` varchar(10) NOT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_blog_react_types_status` (`common_status_id`),
  CONSTRAINT `fk_blog_react_types_status` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `blogs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `description` text,
  `writer_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `views` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `fk_blog_category` (`category_id`),
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`),
  CONSTRAINT `fk_blog_category` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`)
);

CREATE TABLE `blog_bookmarks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `blog_id` int NOT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_bookmarks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `blog_bookmarks_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`),
  CONSTRAINT `blog_bookmarks_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `blog_comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `comment` text NOT NULL,
  `comment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_comments_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`),
  CONSTRAINT `blog_comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `blog_comments_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `blog_comments` (`id`),
  CONSTRAINT `blog_comments_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `blog_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_images_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
);

CREATE TABLE `blog_likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL DEFAULT '1',
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  KEY `fk_blog_likes_reaction_type` (`reaction_type_id`),
  CONSTRAINT `blog_likes_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_likes_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`),
  CONSTRAINT `fk_blog_likes_reaction_type` FOREIGN KEY (`reaction_type_id`) REFERENCES `blog_reactions_types` (`id`)
);

CREATE TABLE `blog_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `blog_tags_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`),
  CONSTRAINT `blog_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
);

CREATE TABLE `blog_comment_reactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_id` (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_comment_reactions_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `blog_comments` (`id`),
  CONSTRAINT `blog_comment_reactions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `blog_comment_reactions_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`),
  CONSTRAINT `blog_comment_reactions_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
);