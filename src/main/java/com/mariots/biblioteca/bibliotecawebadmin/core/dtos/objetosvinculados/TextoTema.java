package com.mariots.biblioteca.bibliotecawebadmin.core.dtos.objetosvinculados;

import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TextoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextoTema {
    private TextoDto textoDto;
    private TemaDto temaDto;
}
