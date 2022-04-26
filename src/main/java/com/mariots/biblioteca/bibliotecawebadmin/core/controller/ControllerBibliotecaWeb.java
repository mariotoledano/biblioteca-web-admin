package com.mariots.biblioteca.bibliotecawebadmin.core.controller;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Supertema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.web.TextoWeb;
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
    public Autor conseguirAutorPorId(@RequestParam int idAutor){
        return service.recuperarAutorPorId(idAutor);
    }
    @GetMapping(value="/temaPorId")
    public Tema conseguirTemaPorId(@RequestParam int idTema){
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
        List<Autor> listaAutores = service.recuperarAutores();
        model.addAttribute("listaAutores", listaAutores);
        List<Tema> listaTemas = service.recuperarTemas();
        model.addAttribute("listaTemas", listaTemas);
        return new ModelAndView("nuevo_texto", model);
    }

//REGISTRAR NUEVO
    @PostMapping(value="/registrarNuevoAutor")
    public ResponseEntity registrarNuevoAutor(@RequestBody Autor autor){
       System.out.println("recibido");
       Autor autorGuardado = service.guardarAutor(autor);
       return new ResponseEntity<Autor>(autorGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoTema")
    public ResponseEntity registrarNuevoTema(@RequestBody Tema tema){
        System.out.println("recibido");
        Tema temaGuardado = service.guardarTema(tema);
        return new ResponseEntity<Tema>(temaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoSupertema")
    public ResponseEntity registrarNuevoSupertema(@RequestBody Supertema supertema){
        System.out.println("recibido");
        Supertema supertemaGuardado = service.guardarSupertema(supertema);
        return new ResponseEntity<Supertema>(supertemaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value="/registrarNuevoTexto")
    public ResponseEntity registrarNuevoTexto(@RequestBody TextoWeb textoWeb){
        System.out.println(textoWeb);
        Integer idTema = textoWeb.getIdTema();
        List<Integer> idTemas = new ArrayList<>();
        idTemas.add(idTema);
        Texto textoNuevo = new Texto(textoWeb.getTextoString(), textoWeb.getLongitud(), textoWeb.getIdAutor(), idTemas);
        System.out.println(textoNuevo);
        Texto textoGuardado = service.guardarTexto(textoNuevo);
        return new ResponseEntity<Texto>(textoGuardado, HttpStatus.CREATED);
    }


}

