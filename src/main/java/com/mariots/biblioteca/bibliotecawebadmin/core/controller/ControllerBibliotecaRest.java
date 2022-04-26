package com.mariots.biblioteca.bibliotecawebadmin.core.controller;

import com.mariots.biblioteca.bibliotecawebadmin.api.mapper.Mapper;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.AutorDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.SupertemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TextoDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest.*;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.objetosvinculados.TemaSupertema;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.objetosvinculados.TextoAutor;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.objetosvinculados.TextoTema;
import com.mariots.biblioteca.bibliotecawebadmin.core.service.ServiceBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerBibliotecaRest {

    @Autowired
    ServiceBiblioteca service;
    @Autowired
    Mapper mapper;

//GET resource -- Recuperar todos los resources de un tipo
    @GetMapping(value = "/autores")
    public ResponseEntity recuperarAutores() {
        List<AutorDto> autores = service.recuperarAutores();
        return new ResponseEntity<List<AutorDto>>(autores, HttpStatus.FOUND);
    }

    @GetMapping(value = "/textos")
    public ResponseEntity recuperarTextos() {
        List<TextoDto> textos = service.recupearTextos();
        return new ResponseEntity<List<TextoDto>>(textos, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas")
    public ResponseEntity recuperarTemas() {
        List<TemaDto> temas = service.recuperarTemas();
        return new ResponseEntity<List<TemaDto>>(temas, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas")
    public ResponseEntity recuperarSupertemas() {
        List<SupertemaDto> supertemas = service.recuperarSupertemas();
        return new ResponseEntity<List<SupertemaDto>>(supertemas, HttpStatus.FOUND);
    }

//GET resource/{id} --> Recuperar recurso por id

    @GetMapping(value = "/autores/{id}")
    public ResponseEntity recuperarAutorPorId(@PathVariable int id) {
        AutorDto autor = service.recuperarAutorPorId(id);
        return new ResponseEntity<AutorDto>(autor, HttpStatus.FOUND);
    }

    @GetMapping(value = "/textos/{id}")
    public ResponseEntity recuperarTextoPorId(@PathVariable int id) {
        TextoDto texto = service.recuperarTextoPorId(id);
        return new ResponseEntity<TextoDto>(texto, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas/{id}")
    public ResponseEntity recuperarTemaPorId(@PathVariable int id) {
        TemaDto tema = service.recuperarTemaPorId(id);
        return new ResponseEntity<TemaDto>(tema, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas/{id}")
    public ResponseEntity recuperarSupertemaPorId(@PathVariable int id) {
        SupertemaDto supertema = service.recuperarSupertemaPorId(id);
        return new ResponseEntity<SupertemaDto>(supertema, HttpStatus.FOUND);
    }

//GET resource/nombre/{nombre} --> Recuperar recurso por nombre

    @GetMapping(value = "/autores/nombre/{nombreAutor}")
    public ResponseEntity recuperarAutorPorNombre(@PathVariable String nombreAutor) {
        AutorDto autor = service.recuperarAutorPorNombre(nombreAutor);
        return new ResponseEntity<AutorDto>(autor, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas/nombre/{nombreTema}")
    public ResponseEntity recuperarTemaPorNombre(@PathVariable String nombreTema) {
        TemaDto tema = service.recuperarTemaPorNombre(nombreTema);
        return new ResponseEntity<TemaDto>(tema, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas/nombre/{nombreSupertema}")
    public ResponseEntity recuperarSupertemaPorId(@PathVariable String nombreSupertema) {
        SupertemaDto supertema = service.recuperarSupertemaPorNombre(nombreSupertema);
        return new ResponseEntity<SupertemaDto>(supertema, HttpStatus.FOUND);
    }

//POST resource --> Creación nuevo resource

    @PostMapping(value = "/autores")
    public ResponseEntity registrarNuevoAutor(@RequestBody AutorRest autor) {
        AutorDto autorGuardado = service.guardarAutor(mapper.toDto(autor));
        return new ResponseEntity<AutorDto>(autorGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/temas")
    public ResponseEntity registrarNuevoTema(@RequestBody TemaRest tema) {
        TemaDto temaGuardado = service.guardarTema(mapper.toDto(tema));
        return new ResponseEntity<TemaDto>(temaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/supertemas")
    public ResponseEntity registrarNuevoSupertema(@RequestBody SupertemaRest supertema) {
        SupertemaDto supertemaGuardado = service.guardarSupertema(mapper.toDto(supertema));
        return new ResponseEntity<SupertemaDto>(supertemaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos")
    public ResponseEntity registrarNuevoTexto(@RequestBody TextoRest texto) {
        TextoDto textoGuardado = service.guardarTexto(mapper.toDto(texto));
        return new ResponseEntity<TextoDto>(textoGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/autores/{idAutor}/temas/{idTema}")
    public ResponseEntity registrarNuevoTextoVinculadoPorPath
            (@RequestBody TextoRestSinAT texto, @PathVariable int idAutor, @PathVariable int idTema){
        TextoDto textoGuardado = service.guardarTextoDesdePath(texto, idAutor, idTema);
        return new ResponseEntity<TextoDto>(textoGuardado, HttpStatus.CREATED);
    }

//POST resource/{id}/resource/{id} -->vincular recursos (actualizar vinculos existentes con PUT)

    @PostMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity vincularTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.vincularTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/{idTexto}/temas/{idTema}")
    public ResponseEntity vincularTextoTema(@PathVariable int idTexto, @PathVariable int idTema) {
        TextoTema textoTema = service.vincularTextoTema(idTexto, idTema);
        return new ResponseEntity<TextoTema>(textoTema, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity vincularTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.vincularTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.CREATED);
    }

//PUT resource/{id}/resource/{id} --> sobreescribir vínculos esistentes

    @PutMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity sobreescribirVinculoTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.sobreescribirVinculoTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.CREATED);
    }

    @PutMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity sobreescribirVinculoTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.sobreescribirVinculoTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.CREATED);
    }

//DELETE resource/{id}/resource/{id} --> Eliminar vínculos entre recursos por id

    @DeleteMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity desvincularTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.desvincularTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.OK);
    }

    @DeleteMapping(value = "/textos/{idTexto}/temas/{idTema}")
    public ResponseEntity desvincularTextoTema(@PathVariable int idTexto, @PathVariable int idTema) {
        TextoTema textoTema = service.desvincularTextoTema(idTexto, idTema);
        return new ResponseEntity<TextoTema>(textoTema, HttpStatus.OK);
    }
    @DeleteMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity desvincularTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.desvincularTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.OK);
    }

//PUT resource/{id} + body data -->Actualizar un recurso
    @PutMapping(value = "/autores/{id}")
    public ResponseEntity actualizarAutorPorId(@PathVariable int id, @RequestBody AutorRest autor) {
        AutorDto autorNuevo = service.actualizarAutorPorId(id, autor);
        return new ResponseEntity<AutorDto>(autorNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/textos/{id}")
    public ResponseEntity actualizarTextosPorId(@PathVariable int id, @RequestBody TextoRest texto) {
        TextoDto textoNuevo = service.actualizarTextoPorId(id, texto);
        return new ResponseEntity<TextoDto>(textoNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/temas/{id}")
    public ResponseEntity actualizarTemasPorId(@PathVariable int id, @RequestBody TemaRest tema) {
        TemaDto temaNuevo = service.actualizarTemaPorId(id, tema);
        return new ResponseEntity<TemaDto>(temaNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/supertemas/{id}")
    public ResponseEntity actualizarSupertemasPorId(@PathVariable int id, @RequestBody SupertemaRest supertema) {
        SupertemaDto supertemaNuevo = service.actualizarSupertemaPorId(id, supertema);
        return new ResponseEntity<SupertemaDto>(supertemaNuevo, HttpStatus.OK);
    }

//DELETE resource/{id} --> Eliminar recursos por id

    @DeleteMapping("autores/{idAutor}")
    public ResponseEntity eliminarAutorPorId(@PathVariable int idAutor){
        AutorDto autorDto = service.recuperarAutorPorId(idAutor);
        service.eliminarAutorPorId(idAutor);
        return new ResponseEntity<AutorDto>(autorDto,HttpStatus.OK);
    }
    @DeleteMapping("textos/{idTexto}")
    public ResponseEntity eliminarTextoPorId(@PathVariable int idTexto){
        TextoDto textoDto = service.recuperarTextoPorId(idTexto);
        service.eliminarTextoPorId(idTexto);
        return new ResponseEntity<TextoDto>(textoDto,HttpStatus.OK);
    }
    @DeleteMapping("temas/{idTema}")
    public ResponseEntity eliminarTemaPorId(@PathVariable int idTema){
        TemaDto temaDto = service.recuperarTemaPorId(idTema);
        service.eliminarTemaPorId(idTema);
        return new ResponseEntity<TemaDto>(temaDto,HttpStatus.OK);
    }
    @DeleteMapping("supertemas/{idSupertema}")
    public ResponseEntity eliminarSupertemaPorId(@PathVariable int idSupertema){
        SupertemaDto supertemaDto= service.recuperarSupertemaPorId(idSupertema);
        service.eliminarSupertemaPorId(idSupertema);
        return new ResponseEntity<SupertemaDto>(supertemaDto,HttpStatus.OK);
    }

}