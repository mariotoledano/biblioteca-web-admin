package com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorRest {
    private String nombreAutor;
    private String fechaAutor;
    private String descripcionBreve;
    private String descripcionLarga;
}
