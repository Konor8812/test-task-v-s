package com.illia.testtaskvs.model;

import com.illia.testtaskvs.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @Column(name = "f_name")
    String firstName;

    @Column(name = "l_name")
    String lastName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    public UserDTO parseDTO() {
        return UserDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(Duration.between(dateOfBirth.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays()/ 365)
                .build();
    }
}
