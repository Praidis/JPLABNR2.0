package com.example.turnosrotativos;

import java.util.List;

public interface EmpleadoService {

    // Crear un nuevo empleado
    Empleado crearEmpleado(Empleado empleado);

    // Actualizar un empleado existente
    Empleado actualizarEmpleado(Empleado empleado);

    // Eliminar un empleado
    void eliminarEmpleado(Long idEmpleado);

    // Recuperar todos los empleados
    List<Empleado> getAllEmpleados();

    // Recuperar un empleado por su ID
    Empleado getEmpleadoById(Long idEmpleado);

    // Recuperar empleados que coinciden con un nombre o apellido
    List<Empleado> getEmpleadosByNombreOrApellido(String nombreOrApellido);

    // Validar la información de un empleado
    void validarEmpleado(Empleado empleado);
}
