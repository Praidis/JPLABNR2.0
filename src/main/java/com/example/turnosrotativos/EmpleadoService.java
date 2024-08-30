package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;  // Añadir esta línea

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Crear un nuevo empleado
    public Empleado crearEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        return empleadoRepository.save(empleado);
    }

    // Actualizar un empleado existente
    public Empleado actualizarEmpleado(Long idEmpleado, Empleado empleado) {
        validarEmpleado(empleado);
        Empleado existingEmpleado = empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));
        existingEmpleado.setNombre(empleado.getNombre());
        existingEmpleado.setApellido(empleado.getApellido());
        existingEmpleado.setDocumento(empleado.getDocumento());
        existingEmpleado.setEmail(empleado.getEmail());
        return empleadoRepository.save(existingEmpleado);
    }

    // Eliminar un empleado
    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }

    // Recuperar todos los empleados
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // Recuperar un empleado por su ID
    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));
    }

    // Validar la información de un empleado
    public void validarEmpleado(Empleado empleado) {
        if (empleado.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("La edad del empleado no puede ser menor a 18 años.");
        }

        if (empleadoRepository.findByDocumento(empleado.getDocumento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el documento ingresado.");
        }

        if (empleadoRepository.findByEmail(empleado.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el email ingresado.");
        }

        if (empleado.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser posterior al día de la fecha.");
        }

        if (empleado.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior al día de la fecha.");
        }
    }
}
