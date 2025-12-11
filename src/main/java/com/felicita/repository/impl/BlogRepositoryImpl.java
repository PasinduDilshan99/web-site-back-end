package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.BlogCommentDto;
import com.felicita.model.dto.BlogImageDto;
import com.felicita.model.dto.BlogLikeDto;
import com.felicita.model.request.BlogDetailsRequest;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.model.response.PromotionTourResponse;
import com.felicita.queries.BlogQueries;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.HistoryManagementQueries;
import com.felicita.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public BlogResponse getBlogDetailsById(BlogDetailsRequest blogDetailsRequest) {

        String GET_BLOG_DETAILS_BY_ID = BlogQueries.GET_BLOG_DETAILS_BY_ID;

        try {
            LOGGER.info("Executing query to fetch blog details for ID: {}", blogDetailsRequest.getId());

            BlogResponse blog = jdbcTemplate.queryForObject(
                    GET_BLOG_DETAILS_BY_ID,
                    new Object[]{blogDetailsRequest.getId()},
                    (rs, rowNum) -> {

                        BlogResponse response = new BlogResponse();
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.findAndRegisterModules(); // For Java time parsing

                        response.setBlogId(rs.getLong("blog_id"));
                        response.setTitle(rs.getString("title"));
                        response.setSubtitle(rs.getString("subtitle"));
                        response.setDescription(rs.getString("description"));
                        response.setWriterId(rs.getLong("writer_id"));
                        response.setWriterName(rs.getString("writer_name"));
                        response.setBlogStatus(rs.getString("blog_status"));
                        response.setBlogCreatedAt(rs.getTimestamp("blog_created_at").toLocalDateTime());

                        // Parse images JSON
                        String imagesJson = rs.getString("images");
                        if (imagesJson != null) {
                            try {
                                List<BlogResponse.BlogImage> images = objectMapper.readValue(
                                        imagesJson,
                                        new TypeReference<List<BlogResponse.BlogImage>>() {}
                                );
                                response.setImages(images);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("Error parsing blog images JSON", e);
                            }
                        }

                        // Like count
                        response.setLikeCount(rs.getInt("like_count"));

                        // Parse reactions JSON
                        String reactionsJson = rs.getString("blog_reactions");
                        if (reactionsJson != null) {
                            try {
                                List<BlogResponse.BlogReaction> reactions = objectMapper.readValue(
                                        reactionsJson,
                                        new TypeReference<List<BlogResponse.BlogReaction>>() {}
                                );
                                response.setBlogReactions(reactions);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("Error parsing blog reactions JSON", e);
                            }
                        }

                        // Parse comments JSON
                        String commentsJson = rs.getString("comments");
                        if (commentsJson != null) {
                            try {
                                List<BlogResponse.CommentResponse> comments = objectMapper.readValue(
                                        commentsJson,
                                        new TypeReference<List<BlogResponse.CommentResponse>>() {}
                                );
                                response.setComments(comments);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("Error parsing blog comments JSON", e);
                            }
                        }

                        return response;
                    }
            );

            LOGGER.info("Successfully fetched blog details for ID {}", blog.getBlogId());
            return blog;

        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Blog not found with ID: {}", blogDetailsRequest.getId());
            throw new DataNotFoundErrorExceptionHandler("Blog not found");
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching blog details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch blog details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching blog details");
        }
    }

    @Override
    public void createBlog(CreateBlogRequest createBlogRequest, Long userId) {
        String INSERT_BLOG = BlogQueries.INSERT_BLOG;

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_BLOG, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, createBlogRequest.getTitle());
                ps.setString(2, createBlogRequest.getSubTitle());
                ps.setString(3, createBlogRequest.getDescription());
                ps.setLong(4, userId); // writer_id
                ps.setLong(5, userId); // created_by
                ps.setLong(6, userId); // updated_by
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting blog data");
            }

            Number generatedId = keyHolder.getKey();
            if (generatedId == null) {
                throw new InsertFailedErrorExceptionHandler("Failed to retrieve inserted blog ID");
            }

            Long blogId = generatedId.longValue();
            LOGGER.info("Inserted Blog ID: {}", blogId);
            if (blogId > 0){
                insertImages(createBlogRequest,userId,blogId);
            }

        } catch (InsertFailedErrorExceptionHandler e) {
            throw new InsertFailedErrorExceptionHandler("Failed to insert blog record");
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }



    public void insertImages(CreateBlogRequest createBlogRequest, Long userId, Long blogId) {
        String INSERT_BLOG_IMAGES = BlogQueries.INSERT_BLOG_IMAGES;
        try {
            for (String image: createBlogRequest.getImageUrls()){
                int rowsAffected = jdbcTemplate.update(INSERT_BLOG_IMAGES,
                        blogId,
                        image,
                        userId,
                        userId
                );
                if (rowsAffected == 0) {
                    throw new InsertFailedErrorExceptionHandler("No rows affected when inserting blog image");
                }
            }

        } catch (InsertFailedErrorExceptionHandler e) {
            throw new InsertFailedErrorExceptionHandler("Failed to insert blog image record");
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

}
