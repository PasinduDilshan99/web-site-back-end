-- ================================
-- Blogs Table
-- ================================
CREATE TABLE blogs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
    description TEXT,
    writer_id INT,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- ================================
-- Blog Images Table
-- ================================
CREATE TABLE blog_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT NOT NULL,
    image_url VARCHAR(500),
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- ================================
-- Blog Likes Table
-- ================================
CREATE TABLE blog_likes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT NOT NULL,
    user_id INT NOT NULL,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- ================================
-- Blog Comments Table
-- ================================
CREATE TABLE blog_comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT NOT NULL,
    user_id INT NOT NULL,
    comment TEXT,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- ================================
-- Sample Data for Blogs
-- ================================
INSERT INTO blogs (title, subtitle, description, writer_id, status, created_by)
VALUES
('First Blog', 'Introduction', 'This is the first blog description', 1, 1, 1),
('Second Blog', 'Deep Dive', 'This is the second blog description', 2, 1, 1);

-- Sample Images
INSERT INTO blog_images (blog_id, image_url, status, created_by)
VALUES
(1, 'https://example.com/image1.jpg', 1, 1),
(1, 'https://example.com/image2.jpg', 1, 1),
(2, 'https://example.com/image3.jpg', 1, 2);

-- Sample Likes
INSERT INTO blog_likes (blog_id, user_id, status, created_by)
VALUES
(1, 1, 1, 1),
(1, 2, 1, 2),
(2, 3, 1, 3);

-- Sample Comments
INSERT INTO blog_comments (blog_id, user_id, comment, status, created_by)
VALUES
(1, 2, 'Great post!', 1, 2),
(1, 3, 'Thanks for sharing.', 1, 3),
(2, 1, 'Very informative.', 1, 1);


SELECT 
    b.id AS blog_id,
    b.title,
    b.subtitle,
    b.description,
    b.writer_id,
    u_writer.username AS writer_name,
    cs.name AS blog_status,
    b.created_at,
    b.created_by,
    b.updated_at,
    b.updated_by,
    b.terminated_at,
    b.terminated_by,
    bi.id AS image_id,
    bi.image_url,
    bl.id AS like_id,
    u_like.username AS liked_by_username,
    bc.id AS comment_id,
    u_comment.username AS comment_by_username,
    bc.comment,
    bc.comment_date,
    cs2.name AS comment_status
FROM blogs b
LEFT JOIN user u_writer ON b.writer_id = u_writer.user_id
LEFT JOIN common_status cs ON b.status = cs.id
LEFT JOIN blog_images bi ON bi.blog_id = b.id AND bi.status = 1
LEFT JOIN blog_likes bl ON bl.blog_id = b.id AND bl.status = 1
LEFT JOIN user u_like ON bl.user_id = u_like.user_id
LEFT JOIN blog_comments bc ON bc.blog_id = b.id AND bc.status = 1
LEFT JOIN user u_comment ON bc.user_id = u_comment.user_id
LEFT JOIN common_status cs2 ON bc.status = cs2.id
ORDER BY b.created_at DESC;


