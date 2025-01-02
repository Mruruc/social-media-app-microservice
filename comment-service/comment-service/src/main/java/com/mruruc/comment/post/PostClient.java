package com.mruruc.comment.post;

import com.mruruc.comment.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "${application.config.client.uri.post-service}",
        configuration = FeignConfig.class
)
public interface PostClient {

    @GetMapping("/exist/{postId}")
    void verifyPostExists(@PathVariable Long postId);
}
