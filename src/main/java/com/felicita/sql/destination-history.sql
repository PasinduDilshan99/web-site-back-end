use travelagencydb;

CREATE TABLE destination_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    package_schedule_id INT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    event_date DATE,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id),
    FOREIGN KEY (package_schedule_id) REFERENCES package_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

CREATE TABLE destination_history_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_history_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_history_id) REFERENCES destination_history(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);


INSERT INTO destination_history 
(destination_id, package_schedule_id, title, description, event_date, status, created_by)
VALUES
-- Kitulgala
(1, NULL, 'Adventurer’s Paradise Opens', 
 'Kitulgala gained fame for white-water rafting and adventure tourism during the 1990s.', 
 '1995-06-10', 1, 1),

(1, NULL, 'Eco Lodges Introduced', 
 'The area saw the establishment of eco-lodges promoting sustainable tourism.', 
 '2012-09-15', 1, 1),

-- Horton Plains
(2, NULL, 'Declared a National Park', 
 'Horton Plains was officially declared a national park due to its biodiversity and natural beauty.', 
 '1988-03-01', 1, 1),

(2, NULL, 'World’s End Trail Restoration', 
 'Popular hiking trails at World’s End underwent safety improvements and restoration.', 
 '2017-11-25', 1, 1),

-- Knuckles Mountain Range
(3, NULL, 'UNESCO World Heritage Site', 
 'The Knuckles Range was added to the Central Highlands UNESCO World Heritage list.', 
 '2010-07-31', 1, 1),

(3, NULL, 'Community Eco-Camping Introduced', 
 'Local communities began offering eco-friendly camping experiences to travelers.', 
 '2020-02-18', 1, 1),

-- Adam’s Peak
(4, NULL, 'Pilgrimage Route Restoration', 
 'The historic stairways and shelters for pilgrims were renovated for safety and accessibility.', 
 '2014-01-05', 1, 1),

(4, NULL, 'Lighting Project Inaugurated', 
 'Solar-powered lights were installed along the path to assist pilgrims at night.', 
 '2022-12-20', 1, 1),

-- Sinharaja Forest
(5, NULL, 'Declared a Biosphere Reserve', 
 'Sinharaja Forest Reserve was declared a UNESCO Biosphere Reserve due to its endemic species.', 
 '1978-05-21', 1, 1),

(5, NULL, 'Rainforest Conservation Initiative', 
 'A government-led project began to enhance conservation and eco-tourism opportunities.', 
 '2018-09-10', 1, 1);


INSERT INTO destination_history_images 
(destination_history_id, name, description, image_url, status, created_by)
VALUES
-- Kitulgala (History ID 1 & 2)
(1, 'Rafting on Kelani River', 'Tourists navigating rapids during early rafting events.', 
 'https://example.com/images/kitulgala_rafting1.jpg', 1, 1),
(1, 'Adventure Hub', 'Early adventure camps built along the Kelani River.', 
 'https://example.com/images/kitulgala_rafting2.jpg', 1, 1),

(2, 'Eco Lodge Cabin', 'Sustainable wooden eco-lodge surrounded by forest.', 
 'https://example.com/images/kitulgala_ecolodge1.jpg', 1, 1),
(2, 'Rainforest Lodge Interior', 'Interior view of an eco-lodge designed for nature lovers.', 
 'https://example.com/images/kitulgala_ecolodge2.jpg', 1, 1),

-- Horton Plains (History ID 3 & 4)
(3, 'Grassland View', 'Endless grasslands and cloud forests of Horton Plains.', 
 'https://example.com/images/horton_plains1.jpg', 1, 1),
(3, 'Baker’s Falls', 'Famous waterfall inside Horton Plains National Park.', 
 'https://example.com/images/horton_plains2.jpg', 1, 1),

(4, 'World’s End Viewpoint', 'Visitors enjoying the view from World’s End cliff.', 
 'https://example.com/images/worlds_end1.jpg', 1, 1),
(4, 'Trail Restoration Work', 'Workers maintaining hiking paths to World’s End.', 
 'https://example.com/images/worlds_end2.jpg', 1, 1),

-- Knuckles (History ID 5 & 6)
(5, 'Knuckles Mountain Peaks', 'Misty peaks of Knuckles mountain range.', 
 'https://example.com/images/knuckles_peaks1.jpg', 1, 1),
(5, 'UNESCO Recognition Plaque', 'Plaque marking Knuckles as a World Heritage site.', 
 'https://example.com/images/knuckles_peaks2.jpg', 1, 1),

(6, 'Eco Camp Setup', 'Tents set up by local eco-tour guides.', 
 'https://example.com/images/knuckles_camp1.jpg', 1, 1),
(6, 'Campfire Experience', 'Travelers enjoying eco-friendly camping at Knuckles.', 
 'https://example.com/images/knuckles_camp2.jpg', 1, 1),

-- Adam’s Peak (History ID 7 & 8)
(7, 'Pilgrims on Trail', 'Devotees ascending the Adam’s Peak staircase.', 
 'https://example.com/images/adams_peak1.jpg', 1, 1),
(7, 'Rest Stop Area', 'Renovated resting point for pilgrims midway up the peak.', 
 'https://example.com/images/adams_peak2.jpg', 1, 1),

(8, 'Solar Lights at Dusk', 'Solar lights illuminating the trail during pilgrimage season.', 
 'https://example.com/images/adams_peak_lights1.jpg', 1, 1),
(8, 'Night Pilgrimage', 'Pilgrims walking under newly installed solar lighting.', 
 'https://example.com/images/adams_peak_lights2.jpg', 1, 1),

-- Sinharaja Forest (History ID 9 & 10)
(9, 'Dense Rainforest Canopy', 'Thick canopy of the Sinharaja Forest Reserve.', 
 'https://example.com/images/sinharaja1.jpg', 1, 1),
(9, 'Endemic Flora', 'Rare plant species endemic to Sinharaja Forest.', 
 'https://example.com/images/sinharaja2.jpg', 1, 1),

(10, 'Tree Planting Project', 'Volunteers planting trees under the conservation initiative.', 
 'https://example.com/images/sinharaja_project1.jpg', 1, 1),
(10, 'Community Awareness Program', 'Local students learning about rainforest conservation.', 
 'https://example.com/images/sinharaja_project2.jpg', 1, 1);



SELECT 
    dh.id AS history_id,
    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location AS destination_location,
    d.latitude,
    d.longitude,
    dh.title AS history_title,
    dh.description AS history_description,
    dh.event_date,
    dh.status AS history_status_id,
    cs_history.name AS history_status_name,
    u_created.username AS history_created_by_username,
    u_updated.username AS history_updated_by_username,
    u_terminated.username AS history_terminated_by_username,
    dh.created_at AS history_created_at,
    dh.updated_at AS history_updated_at,
    dh.terminated_at AS history_terminated_at,
    dhi.id AS image_id,
    dhi.name AS image_name,
    dhi.description AS image_description,
    dhi.image_url,
    dhi.status AS image_status_id,
    cs_image.name AS image_status_name,
    ui_created.username AS image_created_by_username,
    ui_updated.username AS image_updated_by_username,
    ui_terminated.username AS image_terminated_by_username,
    dhi.created_at AS image_created_at,
    dhi.updated_at AS image_updated_at,
    dhi.terminated_at AS image_terminated_at
FROM destination_history dh
JOIN destination d ON dh.destination_id = d.destination_id
LEFT JOIN destination_history_images dhi ON dhi.destination_history_id = dh.id
LEFT JOIN common_status cs_history ON dh.status = cs_history.id
LEFT JOIN common_status cs_image ON dhi.status = cs_image.id
LEFT JOIN user u_created ON dh.created_by = u_created.user_id
LEFT JOIN user u_updated ON dh.updated_by = u_updated.user_id
LEFT JOIN user u_terminated ON dh.terminated_by = u_terminated.user_id
LEFT JOIN user ui_created ON dhi.created_by = ui_created.user_id
LEFT JOIN user ui_updated ON dhi.updated_by = ui_updated.user_id
LEFT JOIN user ui_terminated ON dhi.terminated_by = ui_terminated.user_id
ORDER BY d.destination_id, dh.event_date, dhi.id;

