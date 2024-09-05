package com.example.turnosrotativos;

// Esta clase define una excepción personalizada para manejar conflictos
public class ConflictException extends RuntimeException {

    // Constructor que recibe un mensaje de error como parámetro
    public ConflictException(String message) {
        super(message); // Llama al constructor de la clase base RuntimeException con el mensaje
    }
}