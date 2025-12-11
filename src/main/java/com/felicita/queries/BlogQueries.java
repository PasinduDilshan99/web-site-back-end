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
                cs_blog.name AS blog_status,
                b.created_at AS blog_created_at,
            
                -- Images (grouped as JSON array)
                (SELECT JSON_ARRAYAGG(JSON_OBJECT(
                    'id', bi.id,
                    'image_url', bi.image_url
                ))
                FROM blog_images bi
                WHERE bi.blog_id = b.id AND bi.status = 1
                ) AS images,
            
                -- Likes (count)
                (SELECT COUNT(*) FROM blog_likes bl WHERE bl.blog_id = b.id AND bl.status = 1) AS like_count,
            
                -- Reactions (grouped)
            COALESCE(
                (
                    SELECT JSON_ARRAYAGG(JSON_OBJECT(
                        'reaction_type_id', t.reaction_type_id,
                        'reaction_type_name', brt.name,
                        'count', t.cnt
                    ))
                    FROM (
                        SELECT bl.reaction_type_id, COUNT(*) AS cnt
                        FROM blog_likes bl
                        WHERE bl.blog_id = b.id
                        GROUP BY bl.reaction_type_id
                    ) AS t
                    LEFT JOIN blog_reactions_types brt ON t.reaction_type_id = brt.id
                ),
                JSON_ARRAY()
            ) AS blog_reactions
            
            ,
            
                -- Comments with replies and reactions
                (SELECT JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'comment_id', bc.id,
                        'user_id', bc.user_id,
                        'username', u_comment.username,
                        'comment', bc.comment,
                        'comment_date', bc.comment_date,
                        'reactions', (
                            SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                'user_id', cr.user_id,
                                'username', u_react.username,
                                'reaction_type_id', cr.reaction_type_id
                            ))
                            FROM blog_comment_reactions cr
                            LEFT JOIN user u_react ON cr.user_id = u_react.user_id
                            WHERE cr.comment_id = bc.id
                        ),
                        'replies', (
                            SELECT JSON_ARRAYAGG(
                                JSON_OBJECT(
                                    'comment_id', r.id,
                                    'user_id', r.user_id,
                                    'username', u_reply.username,
                                    'comment', r.comment,
                                    'comment_date', r.comment_date,
                                    'reactions', (
                                        SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                            'user_id', cr2.user_id,
                                            'username', u_react2.username,
                                            'reaction_type_id', cr2.reaction_type_id
                                        ))
                                        FROM blog_comment_reactions cr2
                                        LEFT JOIN user u_react2 ON cr2.user_id = u_react2.user_id
                                        WHERE cr2.comment_id = r.id
                                    )
                                )
                            )
                            FROM blog_comments r
                            LEFT JOIN user u_reply ON r.user_id = u_reply.user_id
                            WHERE r.parent_comment_id = bc.id
                        )
                    )
                )
                FROM blog_comments bc
                LEFT JOIN user u_comment ON bc.user_id = u_comment.user_id
                WHERE bc.blog_id = b.id AND bc.parent_comment_id IS NULL
                ) AS comments
            FROM blogs b
            LEFT JOIN user u_writer ON b.writer_id = u_writer.user_id
            LEFT JOIN common_status cs_blog ON b.status = cs_blog.id
            WHERE b.status = 1
            ORDER BY b.created_at DESC
            """;


    public static final String GET_BLOG_DETAILS_BY_ID = """
            SELECT
                            b.id AS blog_id,
                            b.title,
                            b.subtitle,
                            b.description,
                            b.writer_id,
                            u_writer.username AS writer_name,
                            cs_blog.name AS blog_status,
                            b.created_at AS blog_created_at,
                                        (SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                'id', bi.id,
                                'image_url', bi.image_url
                            ))
                            FROM blog_images bi
                            WHERE bi.blog_id = b.id AND bi.status = 1
                            ) AS images,
            
                            (SELECT COUNT(*) FROM blog_likes bl WHERE bl.blog_id = b.id AND bl.status = 1) AS like_count,
            
                        COALESCE(
                            (
                                SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                    'reaction_type_id', t.reaction_type_id,
                                    'reaction_type_name', brt.name,
                                    'count', t.cnt
                                ))
                                FROM (
                                    SELECT bl.reaction_type_id, COUNT(*) AS cnt
                                    FROM blog_likes bl
                                    WHERE bl.blog_id = b.id
                                    GROUP BY bl.reaction_type_id
                                ) AS t
                                LEFT JOIN blog_reactions_types brt ON t.reaction_type_id = brt.id
                            ),
                            JSON_ARRAY()
                        ) AS blog_reactions
            
                        ,
            
                            (SELECT JSON_ARRAYAGG(
                                JSON_OBJECT(
                                    'comment_id', bc.id,
                                    'user_id', bc.user_id,
                                    'username', u_comment.username,
                                    'comment', bc.comment,
                                    'comment_date', bc.comment_date,
                                    'reactions', (
                                        SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                            'user_id', cr.user_id,
                                            'username', u_react.username,
                                            'reaction_type_id', cr.reaction_type_id
                                        ))
                                        FROM blog_comment_reactions cr
                                        LEFT JOIN user u_react ON cr.user_id = u_react.user_id
                                        WHERE cr.comment_id = bc.id
                                    ),
                                    'replies', (
                                        SELECT JSON_ARRAYAGG(
                                            JSON_OBJECT(
                                                'comment_id', r.id,
                                                'user_id', r.user_id,
                                                'username', u_reply.username,
                                                'comment', r.comment,
                                                'comment_date', r.comment_date,
                                                'reactions', (
                                                    SELECT JSON_ARRAYAGG(JSON_OBJECT(
                                                        'user_id', cr2.user_id,
                                                        'username', u_react2.username,
                                                        'reaction_type_id', cr2.reaction_type_id
                                                    ))
                                                    FROM blog_comment_reactions cr2
                                                    LEFT JOIN user u_react2 ON cr2.user_id = u_react2.user_id
                                                    WHERE cr2.comment_id = r.id
                                                )
                                            )
                                        )
                                        FROM blog_comments r
                                        LEFT JOIN user u_reply ON r.user_id = u_reply.user_id
                                        WHERE r.parent_comment_id = bc.id
                                    )
                                )
                            )
                            FROM blog_comments bc
                            LEFT JOIN user u_comment ON bc.user_id = u_comment.user_id
                            WHERE bc.blog_id = b.id AND bc.parent_comment_id IS NULL
                            ) AS comments
                        FROM blogs b
                        LEFT JOIN user u_writer ON b.writer_id = u_writer.user_id
                        LEFT JOIN common_status cs_blog ON b.status = cs_blog.id
                        WHERE b.id= ?
                        ORDER BY b.created_at DESC
            """;

    public static final String INSERT_BLOG = """
        INSERT INTO blogs 
            (title, subtitle, description, writer_id, created_by, updated_by) 
        VALUES 
            (?, ?, ?, ?, ?, ?)
        """;

    public static final String INSERT_BLOG_IMAGES = """
        INSERT INTO blog_images 
            (blog_id, image_url, created_by, updated_by)
        VALUES 
            (?, ?, ?, ?)
        """;




}
