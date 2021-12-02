package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.Evacuation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvacuationRepository extends JpaRepository<Evacuation,Long> {
    Evacuation findByObjet(String objet);
}
