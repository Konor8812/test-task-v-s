package com.illia.testtaskvs.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.testtaskvs.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:init.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init.sql", executionPhase = AFTER_TEST_METHOD)
})
public class EndToEndTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetUserInfo() throws Exception {
        var userId = 2;
        var userFirstName = "Pagani";
        var userLastName = "Mery";
        var userDateOfBirth = LocalDate.of(1980, 7, 7);
        var user = User.builder()
                .firstName(userFirstName)
                .lastName(userLastName)
                .dateOfBirth(userDateOfBirth)
                .build();

        var expected = new ObjectMapper().writeValueAsString(user.parseDTO());

        mvc.perform(MockMvcRequestBuilders.get(String.format("/task/user/%d", userId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expected));
    }
}
