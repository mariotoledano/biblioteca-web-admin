package com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextoRest {

    private String textoString;
    private String longitud;
    private String nombreAutor;
    private List<String> nombreTemas;
}
