package com.mruruc.post.mapper;

import com.mruruc.post.dto.PostDto;
import com.mruruc.post.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDto toDto(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUserId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public Post toEntity(PostDto dto) {
        return Post.builder()
                .title(dto.title())
                .content(dto.content())
                .userId(dto.userId())
                .build();
    }
}
