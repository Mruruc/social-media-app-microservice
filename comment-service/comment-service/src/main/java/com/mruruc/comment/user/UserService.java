package com.mruruc.comment.user;

import com.mruruc.comment.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    public void verifyUserExist(Long userId) {
        if (!userClient.isUserExist(userId)) {
            throw new ResourceNotFoundException(
                    format("User with Id: %s does not exist.", userId)
            );
        }
    }
}
