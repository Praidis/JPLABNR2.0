package com.example.turnosrotativos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JornadaLaboralRepository extends JpaRepository<JornadaLaboral, Long> {
    List<JornadaLaboral> findByConceptoLaboral(ConceptoLaboral conceptoLaboral);
}