package com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextoRestSinAT {
    private String textoString;
    private String longitud;
}
