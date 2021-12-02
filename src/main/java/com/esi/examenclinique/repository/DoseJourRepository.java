package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.DoseJour;
import com.esi.examenclinique.entity.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseJourRepository extends JpaRepository<DoseJour, Long> {
}
