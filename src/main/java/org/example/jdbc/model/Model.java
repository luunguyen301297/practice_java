package org.example.jdbc.model;

import lombok.*;

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
public class Model {

    int id;
    String name;
    String email;

}
