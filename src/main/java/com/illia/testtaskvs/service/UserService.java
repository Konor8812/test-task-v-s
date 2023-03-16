package com.illia.testtaskvs.service;

import com.illia.testtaskvs.model.dto.UserDTO;
import com.illia.testtaskvs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO getUser(long userId) throws RequestProcessingException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RequestProcessingException("No such user"))
                .parseDTO();
    }
}
