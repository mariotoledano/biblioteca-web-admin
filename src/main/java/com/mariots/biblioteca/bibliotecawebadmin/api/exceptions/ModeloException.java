package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ModeloException{

    private LocalDateTime fechaYHora;
    private String mensaje;
    private String detalles;
}
