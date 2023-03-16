package com.illia.testtaskvs.controller;

import com.illia.testtaskvs.model.dto.UserDTO;
import com.illia.testtaskvs.service.RequestProcessingException;
import com.illia.testtaskvs.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testRequestProcessingExceptionHandling() throws Exception {
        var exceptionMsg = "msg";
        var userId = 1;

        when(userService.getUser(userId))
                .thenThrow(new RequestProcessingException(exceptionMsg));

        mvc.perform(MockMvcRequestBuilders.get(String.format("/task/user/%d", userId)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(exceptionMsg));
        verify(userService, times(1))
                .getUser(userId);
    }

    @Test
    public void performGetUserInfoRequestShouldReturnUser() throws Exception {
        var userDTO = UserDTO.builder().build();
        var userId = 1;

        when(userService.getUser(userId))
                .thenReturn(userDTO);

        mvc.perform(MockMvcRequestBuilders.get(String.format("/task/user/%d", userId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userService, times(1))
                .getUser(userId);
    }

}
