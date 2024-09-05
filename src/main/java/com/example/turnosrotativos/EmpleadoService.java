package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Empleado crearEmpleado(Empleado empleado) {
        try {
            validarEmpleado(empleado);
            return empleadoRepository.save(empleado);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ConflictException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Transactional
    public Empleado updateEmpleado(Long idEmpleado, Empleado empleado) {
        Empleado existingEmpleado = empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));

        if (!existingEmpleado.getDocumento().equals(empleado.getDocumento()) &&
                empleadoRepository.findByDocumento(empleado.getDocumento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el documento ingresado.");
        }

        existingEmpleado.setNombre(empleado.getNombre());
        existingEmpleado.setApellido(empleado.getApellido());
        existingEmpleado.setEmail(empleado.getEmail());
        existingEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
        existingEmpleado.setFechaIngreso(empleado.getFechaIngreso());

        return empleadoRepository.save(existingEmpleado);
    }

    @Transactional
    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));
    }

    private void validarEmpleado(Empleado empleado) {
        // Validación de edad mínima
        if (empleado.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("La edad del empleado no puede ser menor a 18 años.");
        }

        // Validación de documento duplicado
        if (empleado.getId() == null && empleadoRepository.findByDocumento(empleado.getDocumento()).isPresent()) {
            throw new ConflictException("Ya existe un empleado con el documento ingresado.");
        }

        // Validación de email duplicado
        if (empleado.getId() == null && empleadoRepository.findByEmail(empleado.getEmail()).isPresent()) {
            throw new ConflictException("Ya existe un empleado con el email ingresado.");
        }

        // Validación de fecha de ingreso
        if (empleado.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser posterior al día de la fecha.");
        }

        // Validación de fecha de nacimiento
        if (empleado.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior al día de la fecha.");
        }

        // Validación de formato de email
        validateEmail(empleado.getEmail());

        // Validación de nombre y apellido
        validateNameOrApellido(empleado.getNombre(), "nombre");
        validateNameOrApellido(empleado.getApellido(), "apellido");

        // Validación de campos obligatorios
        validateRequiredFields(empleado);
    }

    private void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher mat = pat.matcher(email);
        if (!mat.find()) {
            throw new IllegalArgumentException("El email ingresado no es correcto.");
        }
    }

    private void validateNameOrApellido(String field, String fieldName) {
        String nameRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        Pattern pat = Pattern.compile(nameRegex);
        Matcher mat = pat.matcher(field);
        if (!mat.find()) {
            throw new IllegalArgumentException("Solo se permiten letras en el campo '" + fieldName + "'.");
        }
    }

    private void validateRequiredFields(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("'nombre' es obligatorio.");
        }
        if (empleado.getApellido() == null || empleado.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("'apellido' es obligatorio.");
        }
        if (empleado.getDocumento() == null || empleado.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("'documento' es obligatorio.");
        }
        if (empleado.getEmail() == null || empleado.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("'email' es obligatorio.");
        }
        if (empleado.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("'fechaNacimiento' es obligatorio.");
        }
        if (empleado.getFechaIngreso() == null) {
            throw new IllegalArgumentException("'fechaIngreso' es obligatorio.");
        }
    }
}
