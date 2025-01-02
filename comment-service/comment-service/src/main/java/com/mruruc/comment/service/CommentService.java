package com.mruruc.comment.service;

import com.mruruc.comment.dto.CommentDto;
import com.mruruc.comment.exception.ResourceNotFoundException;
import com.mruruc.comment.mapper.CommentMapper;
import com.mruruc.comment.model.Comment;
import com.mruruc.comment.post.PostService;
import com.mruruc.comment.repository.CommentRepository;
import com.mruruc.comment.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final PostService postService;
    private final UserService userService;

    public Long saveComment(CommentDto dto) {
        Long userId = dto.userId();
        userService.verifyUserExist(userId);

        Long postId = dto.postId();
        postService.verifyPostExist(postId);

        Comment comment = mapper.toEntity(dto);
        Comment savedComment = repository.save(comment);
        return savedComment.getId();
    }


    public CommentDto findCommentById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("User with Id: %s does not exist.", id)
                ));
    }

    public List<CommentDto> findCommentsByPostId(Long postId) {
        postService.verifyPostExist(postId);
        return repository.findAllByPostId(postId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
