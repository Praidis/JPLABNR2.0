package com.example.turnosrotativos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoLaboralRepository extends JpaRepository<ConceptoLaboral, Long> {
}
