package com.esi.examenclinique.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EleDTO {
    private Long id;
    private String nom;
    private String remarque;
}
