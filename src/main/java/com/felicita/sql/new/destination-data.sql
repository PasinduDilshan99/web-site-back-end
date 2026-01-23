INSERT INTO `travelagencydb`.`destination_categories`
(`id`,
`category`,
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
<{category: }>,
<{description: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination`
(`destination_id`,
`name`,
`description`,
`status`,
`destination_category`,
`location`,
`latitude`,
`longitude`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`,
`extra_price`,
`extra_price_note`)
VALUES
(<{destination_id: }>,
<{name: }>,
<{description: }>,
<{status: }>,
<{destination_category: }>,
<{location: }>,
<{latitude: }>,
<{longitude: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>,
<{extra_price: 0}>,
<{extra_price_note: }>);

INSERT INTO `travelagencydb`.`destination_categories_images`
(`id`,
`destination_categories_id`,
`name`,
`description`,
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
<{destination_categories_id: }>,
<{name: }>,
<{description: }>,
<{image_url: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_images`
(`id`,
`destination_id`,
`name`,
`description`,
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
<{destination_id: }>,
<{name: }>,
<{description: }>,
<{image_url: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_history`
(`id`,
`destination_id`,
`package_schedule_id`,
`title`,
`description`,
`event_date`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{id: }>,
<{destination_id: }>,
<{package_schedule_id: }>,
<{title: }>,
<{description: }>,
<{event_date: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);


INSERT INTO `travelagencydb`.`destination_history_images`
(`id`,
`destination_history_id`,
`name`,
`description`,
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
<{destination_history_id: }>,
<{name: }>,
<{description: }>,
<{image_url: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_review`
(`review_id`,
`destination_id`,
`user_id`,
`review_text`,
`rating`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{review_id: }>,
<{destination_id: }>,
<{user_id: }>,
<{review_text: }>,
<{rating: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_review_images`
(`image_id`,
`review_id`,
`name`,
`description`,
`image_url`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`)
VALUES
(<{image_id: }>,
<{review_id: }>,
<{name: }>,
<{description: }>,
<{image_url: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_review_comment`
(`comment_id`,
`review_id`,
`user_id`,
`comment_text`,
`status`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`terminated_at`,
`terminated_by`,
`parent_comment_id`)
VALUES
(<{comment_id: }>,
<{review_id: }>,
<{user_id: }>,
<{comment_text: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>,
<{parent_comment_id: }>);

INSERT INTO `travelagencydb`.`destination_review_reaction`
(`review_reaction_id`,
`review_id`,
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
(<{review_reaction_id: }>,
<{review_id: }>,
<{user_id: }>,
<{reaction_type_id: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_review_comment_reaction`
(`comment_reaction_id`,
`comment_id`,
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
(<{comment_reaction_id: }>,
<{comment_id: }>,
<{user_id: }>,
<{reaction_type_id: }>,
<{status: }>,
<{created_at: CURRENT_TIMESTAMP}>,
<{created_by: }>,
<{updated_at: CURRENT_TIMESTAMP}>,
<{updated_by: }>,
<{terminated_at: }>,
<{terminated_by: }>);

INSERT INTO `travelagencydb`.`destination_wishlist`
(`id`,
`user_id`,
`destination_id`,
`status_id`,
`created_at`)
VALUES
(<{id: }>,
<{user_id: }>,
<{destination_id: }>,
<{status_id: }>,
<{created_at: CURRENT_TIMESTAMP}>);

INSERT INTO `travelagencydb`.`destination_wishlist_history`
(`id`,
`user_id`,
`destination_id`,
`wishlist_id`,
`status_id`,
`created_at`)
VALUES
(<{id: }>,
<{user_id: }>,
<{destination_id: }>,
<{wishlist_id: }>,
<{status_id: }>,
<{created_at: CURRENT_TIMESTAMP}>);