package com.mruruc.comment.controller;

import com.mruruc.comment.dto.CommentDto;
import com.mruruc.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto comment = service.findCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Long id) {
        List<CommentDto> comments = service.findCommentsByPostId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto) {
        Long commentId = service.saveComment(dto);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/comments/{id}")
                                .buildAndExpand(commentId)
                                .toUri()
                )
                .build();
    }


}
