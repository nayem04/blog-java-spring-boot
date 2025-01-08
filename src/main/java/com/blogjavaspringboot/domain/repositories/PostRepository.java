package com.blogjavaspringboot.domain.repositories;

import com.blogjavaspringboot.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE (:query IS NULL OR p.title LIKE %:query%) AND p.deleted = false")
    Page<Post> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.id = :id AND p.deleted = false")
    Optional<Post> find(@Param("id") Long id);
}
