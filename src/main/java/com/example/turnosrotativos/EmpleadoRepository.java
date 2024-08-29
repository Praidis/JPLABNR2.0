package com.example.turnosrotativos;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Método para obtener todos los empleados
    List<Empleado> findAll();

    // Método para obtener un empleado por ID
    Optional<Empleado> findById(Long idEmpleado);

    // Método para obtener un empleado por número de documento
    Empleado findByDocumento(String Documento);

    // Método para obtener un empleado por email
    Empleado findByEmail(String email);
}