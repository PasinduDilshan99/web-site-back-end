package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.BlogCommentDto;
import com.felicita.model.dto.BlogImageDto;
import com.felicita.model.dto.BlogLikeDto;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.model.response.PromotionTourResponse;
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

            // Use jdbcTemplate to fetch the result
            List<BlogResponse> results = jdbcTemplate.query(GET_ALL_BLOGS, (rs, rowNum) -> {
                BlogResponse blog = new BlogResponse();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.findAndRegisterModules(); // for LocalDateTime parsing

                blog.setBlogId(rs.getLong("blog_id"));
                blog.setTitle(rs.getString("title"));
                blog.setSubtitle(rs.getString("subtitle"));
                blog.setDescription(rs.getString("description"));
                blog.setWriterId(rs.getLong("writer_id"));
                blog.setWriterName(rs.getString("writer_name"));
                blog.setBlogStatus(rs.getString("blog_status"));
                blog.setBlogCreatedAt(rs.getTimestamp("blog_created_at").toLocalDateTime());

                // Parse images JSON
                String imagesJson = rs.getString("images");
                if (imagesJson != null) {
                    List<BlogResponse.BlogImage> images = null;
                    try {
                        images = objectMapper.readValue(
                                imagesJson,
                                new TypeReference<List<BlogResponse.BlogImage>>() {}
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    blog.setImages(images);
                }

                // Like count
                blog.setLikeCount(rs.getInt("like_count"));

                // Parse blog reactions JSON
                String reactionsJson = rs.getString("blog_reactions");
                if (reactionsJson != null) {
                    List<BlogResponse.BlogReaction> reactions = null;
                    try {
                        reactions = objectMapper.readValue(
                                reactionsJson,
                                new TypeReference<List<BlogResponse.BlogReaction>>() {}
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    blog.setBlogReactions(reactions);
                }

                // Parse comments JSON (nested replies + reactions)
                String commentsJson = rs.getString("comments");
                if (commentsJson != null) {
                    List<BlogResponse.CommentResponse> comments = null;
                    try {
                        comments = objectMapper.readValue(
                                commentsJson,
                                new TypeReference<List<BlogResponse.CommentResponse>>() {}
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    blog.setComments(comments);
                }

                return blog;
            });

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
