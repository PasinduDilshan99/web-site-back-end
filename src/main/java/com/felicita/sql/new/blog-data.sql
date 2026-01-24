INSERT INTO `travelagencydb`.`blog_category`
(`id`,
`name`,
`description`,
`status`,
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
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`blog_reactions_types`
(`id`,
`name`,
`emoji`,
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
<{emoji: }>,
<{common_status_id: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`blogs`
(`id`,
`title`,
`subtitle`,
`description`,
`writer_id`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`,
`category_id`,
`views`)
VALUES
(<{id: }>,
<{title: }>,
<{subtitle: }>,
<{description: }>,
<{writer_id: }>,
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>,
<{category_id: }>,
<{views: 0}>);

INSERT INTO `travelagencydb`.`blog_bookmarks`
(`id`,
`user_id`,
`blog_id`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{user_id: }>,
<{blog_id: }>,
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);
INSERT INTO `travelagencydb`.`blog_comments`
(`id`,
`blog_id`,
`user_id`,
`parent_comment_id`,
`comment`,
`comment_date`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{blog_id: }>,
<{user_id: }>,
<{parent_comment_id: }>,
<{comment: }>,
<{comment_date: CURRENT_TIMESTAMP}>,
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);


INSERT INTO `travelagencydb`.`blog_images`
(`id`,
`blog_id`,
`image_url`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{blog_id: }>,
<{image_url: }>,
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`blog_likes`
(`id`,
`blog_id`,
`user_id`,
`reaction_type_id`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{blog_id: }>,
<{user_id: }>,
<{reaction_type_id: 1}>,
<{status: 1}>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`blog_tags`
(`id`,
`blog_id`,
`tag_id`,
`created_at`)
VALUES
(<{id: }>,
<{blog_id: }>,
<{tag_id: }>,
<{created_at: CURRENT_TIMESTAMP}>);

INSERT INTO `travelagencydb`.`blog_comment_reactions`
(`id`,
`comment_id`,
`user_id`,
`reaction_type_id`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`,
`status`)
VALUES
(<{id: }>,
<{comment_id: }>,
<{user_id: }>,
<{reaction_type_id: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>,
<{status: 1}>);