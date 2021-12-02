package com.esi.examenclinique.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoseJour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int matinR;
    private int midiR;
    private int soirR;
    private int coucherR;
    private int matinL;
    private int midiL;
    private int soirL;
    private int coucherL;


    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "ordonnance_id")
    private Ordonnance ordonnance;
}
