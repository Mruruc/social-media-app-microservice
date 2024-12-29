package com.mruruc.user.service;

import com.mruruc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public boolean isUserExist(Long userId){
        return repository.existsById(userId);
    }
}
