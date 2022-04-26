package com.mariots.biblioteca.bibliotecawebadmin.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextoDto {
    private int idTexto;
    private String textoString;
    private String longitud;
    private Integer idAutor;
    private List<Integer> idTemas;

    public TextoDto(String textoString, String longitud, Integer idAutor, List<Integer> idTemas) {
        this.textoString = textoString;
        this.longitud = longitud;
        this.idAutor = idAutor;
        this.idTemas = idTemas;
    }
}
