package com.esi.examenclinique.entity;

import com.esi.examenclinique.DTO.EleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXAMEN_CLINIQUE_TBL")
public class ExamenClinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @ElementCollection
    @CollectionTable(name = "ec_general", joinColumns = @JoinColumn(name = "id")) // choose the name of the DB table storing the Map<>
    @JoinColumn(name = "id")      // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @Column(name = "general_ele")
    private Collection<Long> general;

    @ElementCollection
    @CollectionTable(name = "ec_appareil", joinColumns = @JoinColumn(name = "id")) // choose the name of the DB table storing the Map<>
    @JoinColumn(name = "id")      // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @Column(name = "appareil_ele")
    private Collection<Long> appareil;


    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    // owner of relation is ordonnance tbl, it holds forign key
    // if we delete em , ord will be removed
    @PrimaryKeyJoinColumn
    // pk of em tbl will be used as fk for ord tbl
    private Ordonnance ordonnance;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Rapport rapport;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Certificat certificat;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Evacuation evacuation;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Orientation orientation;


    public ExamenClinique(Long id) {
        this.id = id;
    }
}
