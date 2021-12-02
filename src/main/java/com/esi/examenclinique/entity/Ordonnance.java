package com.esi.examenclinique.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Entity
@Table(name = "ORDONNANCE_TBL")
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    private String doctor;
    private String patient;

    //insuline par voie sous-cutanee
    @OneToOne(mappedBy = "ordonnance", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private DoseJour doses;

    //aiguilles pour stylo a insuline
    private int aiguillesJ; // 4 a/j

    // auto-surveillance glycemique
    private int lancettesJ;   // 6 l/j
    private int bandelettesJ; // 4 b/j

    //Autre medications
    private String autre;

    @JsonIgnore
    @OneToOne
    @MapsId
    // pk of emp tbl is used as pk for ord tbl
    private ExamenClinique examenClinique;
}
