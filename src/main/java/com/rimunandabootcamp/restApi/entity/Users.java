package com.rimunandabootcamp.restApi.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Integer id;
    private String name;
    private String email;
    private String phone;


}
