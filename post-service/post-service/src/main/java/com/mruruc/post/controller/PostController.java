package com.mruruc.post.controller;

import com.mruruc.post.dto.PostDto;
import com.mruruc.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;


    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getAllUserPost(@PathVariable("id") Long userId) {
        List<PostDto> posts = service.findAllPostByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> posts = service.findAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto post = service.findPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDto post) {
        Long postId = service.savePost(post);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/posts/{id}")
                                .buildAndExpand(postId)
                                .toUri()
                )
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @RequestParam("user-id") Long userId) {
        service.deletePost(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Void> isPostExist(@PathVariable Long id) {
        service.verifyPostExists(id);
        return ResponseEntity.ok().build();
    }
}
