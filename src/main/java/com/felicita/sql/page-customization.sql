use travelagencydb;

CREATE TABLE page (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE component (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE page_component (
    id INT AUTO_INCREMENT PRIMARY KEY,
    page_id INT NOT NULL,
    component_id INT NOT NULL,
    order_index INT DEFAULT 0,
    is_visible BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (page_id) REFERENCES page(id) ON DELETE CASCADE,
    FOREIGN KEY (component_id) REFERENCES component(id) ON DELETE CASCADE
);


CREATE TABLE component_theme (
    id INT AUTO_INCREMENT PRIMARY KEY,
    component_id INT NOT NULL,
    theme_name VARCHAR(100),
    theme_description VARCHAR(255),
    primary_color VARCHAR(200),
    secondary_color VARCHAR(200),
    background_color VARCHAR(200),
    gradient_enabled BOOLEAN DEFAULT FALSE,
    gradient_type VARCHAR(200),
    gradient_direction VARCHAR(500),
    gradient_start VARCHAR(200),
    gradient_end VARCHAR(200),
    text_primary VARCHAR(500),       -- ↑ increased size
    text_secondary VARCHAR(500),     -- ↑ increased size
    banner_image VARCHAR(255),
    custom_css TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (component_id) REFERENCES component(id) ON DELETE CASCADE
);



INSERT INTO page (name, slug) VALUES 
('Home', 'home'),
('About', 'about');


INSERT INTO component (name, description) VALUES
('LinkBar', 'Link Bar'),
('NavBar', 'Navigation bar'),
('HeroSection', 'Top banner section with background image');

INSERT INTO page_component (page_id, component_id, order_index, is_visible) VALUES
(1, 1, 1, TRUE),   -- Home → HeroSection
(1, 2, 2, TRUE),   -- Home → CardList
(1, 3, 3, TRUE),   -- Home → Footer
(2, 1, 1, TRUE),  -- About → HeroSection (hidden)
(2, 2, 2, TRUE),   -- Home → CardList
(2, 3, 3, TRUE);  


INSERT INTO component_theme (
    component_id, theme_name, theme_description,
    primary_color, secondary_color, background_color,
    gradient_enabled, gradient_type, gradient_direction,
    gradient_start, gradient_end,
    text_primary, text_secondary, banner_image, custom_css
) VALUES
-- 🔹 Link Bar container background gradient
(1, 'LINK_BAR_BACKGROUND', 'Gradient background for the Link Bar container',
 NULL, NULL, NULL,
 TRUE, 'linear', 'to right',
 '#4C1D95', '#0C4A6E', -- from-purple-900 → to-sky-900
 '#FFFFFF', '#AAAAAA', NULL,
 'background: linear-gradient(to right, #4C1D95, #5B21B6, #0C4A6E);'),

-- 🔹 Full button (default)
(1, 'LINK_BUTTON_FULL_BACKGROUND', 'Default background for full buttons',
 '#A855F7', '#38BDF8', '#F3F4F6', -- purple-500/20 + sky-400/30
 FALSE, NULL, NULL,
 NULL, NULL,
 '#EDE9FE', '#F9FAFB', NULL,
 'background-color: rgba(168,85,247,0.2); border: 1px solid rgba(56,189,248,0.3);'),

-- 🔹 Full button (hover)
(1, 'LINK_BUTTON_FULL_BACKGROUND_HOVER', 'Hover background for full buttons',
 NULL, NULL, NULL,
 TRUE, 'linear', 'to right',
 'rgba(147,51,234,0.3)', 'rgba(14,165,233,0.3)', -- from-purple-600/30 → to-sky-500/30
 '#FFFFFF', '#CCCCCC', NULL,
 'border: 1px solid rgba(216,180,254,0.4);'),

-- 🔹 Full button text (default)
(1, 'LINK_BUTTON_FULL_TEXT_COLOR', 'Text color for full buttons',
 NULL, NULL, NULL,
 FALSE, NULL, NULL,
 NULL, NULL,
 'rgba(237,233,254,0.9)', '#666666', NULL,
 NULL),

-- 🔹 Full button text (hover)
(1, 'LINK_BUTTON_FULL_TEXT_COLOR_HOVER', 'Text color when hovering full buttons',
 NULL, NULL, NULL,
 FALSE, NULL, NULL,
 NULL, NULL,
 '#FFFFFF', '#999999', NULL,
 NULL),

-- 🔹 Icon-only button (default)
(1, 'LINK_BUTTON_ICON_ONLY_BACKGROUND', 'Default background for icon-only buttons',
 '#A855F7', '#38BDF8', '#F3F4F6',
 FALSE, NULL, NULL,
 NULL, NULL,
 '#FFFFFF', '#AAAAAA', NULL,
 'background-color: rgba(168,85,247,0.2); border: 1px solid rgba(56,189,248,0.3);'),

-- 🔹 Icon-only button (hover)
(1, 'LINK_BUTTON_ICON_ONLY_BACKGROUND_HOVER', 'Hover background for icon-only buttons',
 NULL, NULL, NULL,
 TRUE, 'linear', 'to right',
 'rgba(147,51,234,0.3)', 'rgba(14,165,233,0.3)',
 '#FFFFFF', '#CCCCCC', NULL,
 'border: 1px solid rgba(216,180,254,0.4);');
















