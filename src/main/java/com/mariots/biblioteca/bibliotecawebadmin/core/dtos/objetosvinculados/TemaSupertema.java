package com.mariots.biblioteca.bibliotecawebadmin.core.dtos.objetosvinculados;

import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.SupertemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TemaDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemaSupertema {
    private TemaDto tema;
    private SupertemaDto supertema;
}
