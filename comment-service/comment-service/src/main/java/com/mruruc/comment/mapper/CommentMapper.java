package com.mruruc.comment.mapper;

import com.mruruc.comment.dto.CommentDto;
import com.mruruc.comment.model.Comment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommentMapper {

    public Comment toEntity(CommentDto dto) {
        Objects.requireNonNull(dto);
        return Comment.builder()
                .content(dto.content())
                .postId(dto.postId())
                .userId(dto.userId())
                .build();
    }

    public CommentDto toDto(Comment comment) {
        Objects.requireNonNull(comment);
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .userId(comment.getUserId())
                .build();
    }
}
