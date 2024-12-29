package com.mruruc.comment.dto;

import lombok.Builder;

@Builder
public record CommentDto(
        Long id,
        String content,
        Long postId,
        Long userId
) {
}
