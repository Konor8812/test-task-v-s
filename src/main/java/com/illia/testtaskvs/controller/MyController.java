package com.illia.testtaskvs.controller;

import com.illia.testtaskvs.model.dto.UserDTO;
import com.illia.testtaskvs.service.RequestProcessingException;
import com.illia.testtaskvs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task")
public class MyController {

    @Autowired
    UserService userService;

    @GetMapping(value = "user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable(name = "id") long id) throws RequestProcessingException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @ExceptionHandler(value = {RequestProcessingException.class})
    public ResponseEntity<String> handleRequestProcessingException( Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
