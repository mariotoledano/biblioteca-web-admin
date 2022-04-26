package com.mariots.biblioteca.bibliotecawebadmin.core.controller;

import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.*;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.web.TextoWeb;
import com.mariots.biblioteca.bibliotecawebadmin.core.service.ServiceBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ControllerBibliotecaWeb {

    @Autowired
    ServiceBiblioteca service;

//MÉTODOS DE PRUEBA
    @GetMapping(value="/autorPorId")
    public AutorDto conseguirAutorPorId(@RequestParam int idAutor){
        return service.recuperarAutorPorId(idAutor);
    }
    @GetMapping(value="/temaPorId")
    public TemaDto conseguirTemaPorId(@RequestParam int idTema){
        return service.recuperarTemaPorId(idTema);
    }

//ÓRDENES DE REDIRECCIÓN
    @GetMapping(value="/nuevoTema")
    public ModelAndView irNuevoTema(ModelMap model){
        return new ModelAndView("nuevo_tema");
    }

    @GetMapping(value="/nuevoAutor")
    public ModelAndView irNuevoAutor(ModelMap model){
        return new ModelAndView("nuevo_autor");
    }

    @GetMapping(value="/nuevoTexto")
    public ModelAndView irNuevoTexto(ModelMap model){
        List<AutorDto> listaAutores = service.recuperarAutores();
        model.addAttribute("listaAutores", listaAutores);
        List<TemaDto> listaTemas = service.recuperarTemas();
        model.addAttribute("listaTemas", listaTemas);
        return new ModelAndView("nuevo_texto", model);
    }

//REGISTRAR NUEVO
    @PostMapping(value="/registrarNuevoAutor")
    public ResponseEntity registrarNuevoAutor(@RequestBody AutorDto autor){
       System.out.println("recibido");
       AutorDto autorGuardado = service.guardarAutor(autor);
       return new ResponseEntity<AutorDto>(autorGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoTema")
    public ResponseEntity registrarNuevoTema(@RequestBody TemaDto tema){
        System.out.println("recibido");
        TemaDto temaGuardado = service.guardarTema(tema);
        return new ResponseEntity<TemaDto>(temaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoSupertema")
    public ResponseEntity registrarNuevoSupertema(@RequestBody SupertemaDto supertema){
        System.out.println("recibido");
        SupertemaDto supertemaGuardado = service.guardarSupertema(supertema);
        return new ResponseEntity<SupertemaDto>(supertemaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoTexto")
    public ResponseEntity registrarNuevoTexto(@RequestBody TextoWeb textoWeb){
        System.out.println(textoWeb);
        Integer idTema = textoWeb.getIdTema();
        List<Integer> idTemas = new ArrayList<>();
        idTemas.add(idTema);
        TextoDto textoNuevo = new TextoDto(textoWeb.getTextoString(), textoWeb.getLongitud(), textoWeb.getIdAutor(), idTemas);
        System.out.println(textoNuevo);
        TextoDto textoGuardado = service.guardarTexto(textoNuevo);
        return new ResponseEntity<TextoDto>(textoGuardado, HttpStatus.CREATED);
    }


}

