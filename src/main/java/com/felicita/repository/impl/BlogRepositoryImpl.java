package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.BlogCommentDto;
import com.felicita.model.dto.BlogImageDto;
import com.felicita.model.dto.BlogLikeDto;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.queries.BlogQueries;
import com.felicita.queries.DestinationQueries;
import com.felicita.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BlogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BlogResponse> getAllBlogs() {
        String GET_ALL_BLOGS = BlogQueries.GET_ALL_BLOGS;

        try {
            LOGGER.info("Executing query to fetch all blogs...");

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_BLOGS);

            Map<Long, BlogResponse> blogMap = new LinkedHashMap<>();

            for (Map<String, Object> row : rows) {
                Long blogId = ((Number) row.get("blog_id")).longValue();

                BlogResponse blog = blogMap.get(blogId);
                if (blog == null) {
                    blog = new BlogResponse();
                    blog.setId(blogId);
                    blog.setTitle((String) row.get("title"));
                    blog.setSubtitle((String) row.get("subtitle"));
                    blog.setDescription((String) row.get("description"));
                    blog.setWriterId(row.get("writer_id") != null ? ((Number) row.get("writer_id")).longValue() : null);
                    blog.setWriterName((String) row.get("writer_name"));
                    blog.setStatus((String) row.get("blog_status"));
                    blog.setCreatedAt(row.get("created_at") != null ? ((Timestamp) row.get("created_at")).toLocalDateTime() : null);
                    blog.setCreatedBy(row.get("created_by") != null ? ((Number) row.get("created_by")).longValue() : null);
                    blog.setUpdatedAt(row.get("updated_at") != null ? ((Timestamp) row.get("updated_at")).toLocalDateTime() : null);
                    blog.setUpdatedBy(row.get("updated_by") != null ? ((Number) row.get("updated_by")).longValue() : null);
                    blog.setTerminatedAt(row.get("terminated_at") != null ? ((Timestamp) row.get("terminated_at")).toLocalDateTime() : null);
                    blog.setTerminatedBy(row.get("terminated_by") != null ? ((Number) row.get("terminated_by")).longValue() : null);

                    blog.setImages(new ArrayList<>());
                    blog.setLikes(new ArrayList<>());
                    blog.setComments(new ArrayList<>());

                    blogMap.put(blogId, blog);
                }

                // Add image if exists
                if (row.get("image_id") != null) {
                    BlogImageDto image = new BlogImageDto();
                    image.setId(((Number) row.get("image_id")).longValue());
                    image.setImageUrl((String) row.get("image_url"));
                    blog.getImages().add(image);
                }

                // Add like if exists
                if (row.get("like_id") != null) {
                    BlogLikeDto like = new BlogLikeDto();
                    like.setId(((Number) row.get("like_id")).longValue());
                    like.setUsername((String) row.get("liked_by_username"));
                    blog.getLikes().add(like);
                }

                // Add comment if exists
                if (row.get("comment_id") != null) {
                    BlogCommentDto comment = new BlogCommentDto();
                    comment.setId(((Number) row.get("comment_id")).longValue());
                    comment.setUsername((String) row.get("comment_by_username"));
                    comment.setComment((String) row.get("comment"));
                    comment.setCommentDate(row.get("comment_date") != null ? ((Timestamp) row.get("comment_date")).toLocalDateTime() : null);
                    blog.getComments().add(comment);
                }
            }

            List<BlogResponse> results = new ArrayList<>(blogMap.values());

            LOGGER.info("Successfully fetched {} blogs.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching blogs: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch blogs from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching blogs: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching blogs");
        }
    }

}
