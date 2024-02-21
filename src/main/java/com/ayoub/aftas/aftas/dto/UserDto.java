package com.ayoub.aftas.aftas.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;


    private String email;


    private String password;

    private Integer num;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;

    private String identityDocument;

    private String identityNumber;

    private String role;

    private Set<String> permissions;
}
