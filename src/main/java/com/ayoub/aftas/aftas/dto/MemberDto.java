package com.ayoub.aftas.aftas.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @Nullable
    private Long id;

    private Integer num;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;
    private String identityDocument;
    private String identityNumber;

}
