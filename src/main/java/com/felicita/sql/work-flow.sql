-- Work Flow Status Table
CREATE TABLE work_flow_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    common_status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_common_status_workflow FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Work Flow Table
CREATE TABLE work_flow (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    order_number INT,
    icon_url VARCHAR(500),
    icon_color VARCHAR(50),
    bg_color VARCHAR(50),
    connect_text VARCHAR(255),
    link_url VARCHAR(500),
    work_flow_status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_work_flow_status FOREIGN KEY (work_flow_status_id) REFERENCES work_flow_status(id)
);


-- Insert into work_flow_status
INSERT INTO work_flow_status (name, description, common_status_id, created_by)
VALUES
('ACTIVE', 'Work flow step is active', 1, 1),
('INACTIVE', 'Work flow step is not active', 2, 1),
('TERMINATED', 'Work flow step discontinued', 3, 1);

-- Insert into work_flow
INSERT INTO work_flow (title, description, order_number, icon_url, icon_color, bg_color, connect_text, link_url, work_flow_status_id, created_by)
VALUES
('Step 1: Sign Up', 'Register to get started with our services', 1, '/icons/work-flow-icons/icon-1.png', '#FFFFFF', '#0073D2', 'Next', '/signup', 1, 1),
('Step 2: Choose Plan', 'Select a plan that fits your needs', 2, '/icons/work-flow-icons/icon-2.png', '#FFFFFF', '#16A34A', 'Next', '/plans', 1, 1),
('Step 3: Payment', 'Securely pay for your selected plan', 3, '/icons/work-flow-icons/icon-3.png', '#FFFFFF', '#F59E0B', 'Next', '/payment', 1, 1),
('Step 4: Get Started', 'Access your dashboard and start using our services', 4, '/icons/work-flow-icons/icon-4.png', '#FFFFFF', '#DC2626', 'Finish', '/dashboard', 1, 1);


SELECT 
	wf.id AS WF_ID,
    wf.title AS WF_TITLE,
    wf.description AS WF_DESCRIPTION,
    wf.order_number AS WF_ORDER,
    wf.icon_url AS WF_ICON_URL,
    wf.icon_color AS WF_ICON_COLOR,
    wf.bg_color AS WF_BG_COLOR,
    wf.connect_text AS WF_CONNECT_TEXT,
    wf.link_url AS WF_LINK_URL,
    wfs.name AS WF_STATUS,
    cs.name AS WF_STATUS_STATUS,
	wf.created_at AS WF_CREATED_AT,
    wf.created_by AS WF_CREATED_BY,
    wf.updated_at AS WF_UPDATED_AT,
    wf.updated_by AS WF_UPDATED_BY,
    wf.terminated_at AS WF_TERMINATED_AT,
    wf.terminated_by AS WF_TERMINATED_BY
FROM work_flow wf
LEFT JOIN work_flow_status wfs
ON wf.work_flow_status_id = wfs.id
LEFT JOIN common_status cs
ON wfs.common_status_id = cs.id;















