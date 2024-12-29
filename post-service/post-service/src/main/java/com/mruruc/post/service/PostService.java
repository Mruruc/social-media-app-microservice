package com.mruruc.post.service;

import com.mruruc.post.dto.PostDto;
import com.mruruc.post.exception.AuthorizationException;
import com.mruruc.post.exception.EntityNotFoundException;
import com.mruruc.post.mapper.PostMapper;
import com.mruruc.post.model.Post;
import com.mruruc.post.repository.PostRepository;
import com.mruruc.post.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserClient userClient;


    public List<PostDto> findAllPost() {
        return repository.findAll()
                .stream()
                .filter(post -> !post.isDeleted())
                .map(mapper::toDto)
                .toList();
    }

    public PostDto findPostById(Long postId) {
        return repository.findByIdAndIsDeletedFalse(postId)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(format("Post with Id: %s not found.", postId)));
    }

    public Long savePost(PostDto postDto) {
        Long userId = postDto.userId();
        if (userClient.isUserExist(userId)) {
            throw new EntityNotFoundException(format("User with Id: %s does not exist.", userId));
        }
        Post post = mapper.toEntity(postDto);
        Post savedPost = repository.save(post);
        return savedPost.getId();
    }


    public void deletePost(Long id, Long userId) {
        if (userClient.isUserExist(userId)) {
            throw new EntityNotFoundException(format("User with Id: %s does not exist.", userId));
        }
        Post post = this.findPost(id);
        if (!post.getUserId().equals(userId)) {
            throw new AuthorizationException("User not allowed to delete this post");
        }
        post.setDeleted(true);
        repository.save(post);
    }

    private Post findPost(Long postId) {
        return repository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new EntityNotFoundException(format("Post with Id: %s not found.", postId)));
    }

    public List<PostDto> findAllPostByUserId(Long userId) {
        if (userClient.isUserExist(userId)) {
            throw new EntityNotFoundException(format("User with Id: %s does not exist.", userId));
        }
        return repository.findAllByUserIdAndIsDeletedFalse(userId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public void verifyPostExists(Long id) {
        if (!repository.existsByIdAndIsDeletedFalse(id)) {
            throw new EntityNotFoundException(
                    format("Post with Id: %d, does not exist!", id)
            );
        }
    }
}
