package com.mruruc.post.repository;

import com.mruruc.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndIsDeletedFalse(Long id);

    List<Post> findAllByUserIdAndIsDeletedFalse(Long userId);

    boolean existsByIdAndIsDeletedFalse(Long id);
}
