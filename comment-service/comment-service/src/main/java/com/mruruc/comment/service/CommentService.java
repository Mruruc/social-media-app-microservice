package com.mruruc.comment.service;

import com.mruruc.comment.dto.CommentDto;
import com.mruruc.comment.exception.ResourceNotFoundException;
import com.mruruc.comment.mapper.CommentMapper;
import com.mruruc.comment.model.Comment;
import com.mruruc.comment.post.PostClient;
import com.mruruc.comment.repository.CommentRepository;
import com.mruruc.comment.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final PostClient postClient;
    private final UserClient userClient;

    public Long saveComment(CommentDto dto) {
        Long userId = dto.userId();
        if (!userClient.isUserExist(userId)) {
            throw new ResourceNotFoundException(
                    format("User with Id: %s does not exist.", userId)
            );
        }

        Long postId = dto.postId();
        postClient.verifyPostExists(postId);

        Comment comment = mapper.toEntity(dto);
        Comment savedComment = repository.save(comment);
        return savedComment.getId();
    }


    public CommentDto findCommentById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }
}
