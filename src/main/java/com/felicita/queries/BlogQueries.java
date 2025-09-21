package com.felicita.queries;

public class BlogQueries {

    public static final String GET_ALL_BLOGS = """
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
            ORDER BY b.created_at DESC
            """;
}
