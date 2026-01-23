INSERT INTO `travelagencydb`.`link_bar_type`
(`id`,
`name`,
`description`,
`common_status_id`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{name: }>,
<{description: }>,
<{common_status_id: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`link_bar_status`
(`id`,
`name`,
`common_status_id`,
`description`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{name: }>,
<{common_status_id: }>,
<{description: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`link_bar`
(`id`,
`name`,
`description`,
`link_bar_type_id`,
`icon_url`,
`link_url`,
`link_bar_status_id`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{name: }>,
<{description: }>,
<{link_bar_type_id: }>,
<{icon_url: }>,
<{link_url: }>,
<{link_bar_status_id: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);