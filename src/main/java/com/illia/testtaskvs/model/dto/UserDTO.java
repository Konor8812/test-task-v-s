package com.illia.testtaskvs.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String firstName;
    String lastName;
    long age;
}
