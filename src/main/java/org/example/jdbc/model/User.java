package org.example.jdbc.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    int id;
    String username;
    String email;
    LocalDateTime createdAt;

}
