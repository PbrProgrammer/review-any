package org.alima.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private String name;
    private String username;
    private String password;
    private String email;

}
