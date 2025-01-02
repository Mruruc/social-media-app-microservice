package com.mruruc.comment.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostClient postClient;

    public void verifyPostExist(Long postId) {
        postClient.verifyPostExists(postId);
    }
}
