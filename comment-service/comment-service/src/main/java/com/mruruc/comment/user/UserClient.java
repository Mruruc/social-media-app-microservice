package com.mruruc.comment.user;

import com.mruruc.comment.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${application.config.client.uri.user-service}",
        configuration = FeignConfig.class
)
public interface UserClient {

    @GetMapping("/exist/{userId}")
    boolean isUserExist(@PathVariable Long userId);
}
