package com.mruruc.post.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostDto(
        Long postId,
        String title,
        String content,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
