package org.example.design_pattern.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    private int id;
    private String name;
    private String email;

}
