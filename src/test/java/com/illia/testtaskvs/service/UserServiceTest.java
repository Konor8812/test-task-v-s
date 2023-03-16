package com.illia.testtaskvs.service;

import com.illia.testtaskvs.model.User;
import com.illia.testtaskvs.model.dto.UserDTO;
import com.illia.testtaskvs.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {UserService.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getUserTestShouldThrowRequestProcessingException() {
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertEquals("No such user",
                assertThrowsExactly(RequestProcessingException.class,
                () -> userService.getUser(1))
                        .getMessage());
        
        verify(userRepository, times(1))
                .findById(1L);
    }

    @Test
    public void getUserTestShouldReturnUserDTO() throws RequestProcessingException {
        when(userRepository.findById(any()))
                .thenReturn(Optional.of(mock(User.class)));

        assertNotNull(userService.getUser(1));

        verify(userRepository, times(1))
                .findById(1L);
    }

}
