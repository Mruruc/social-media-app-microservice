package com.mruruc.comment.config;

import com.mruruc.comment.handler.FeignErrorDecoderImpl;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoderImpl();
    }
}
