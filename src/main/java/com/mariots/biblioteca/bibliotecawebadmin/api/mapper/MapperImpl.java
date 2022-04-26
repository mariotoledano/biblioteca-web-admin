package com.mariots.biblioteca.bibliotecawebadmin.api.mapper;

import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.CampoEnBlancoException;
import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.RecursoNoEncontradoException;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.AutorDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.SupertemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TemaDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.TextoDto;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest.AutorRest;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest.SupertemaRest;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest.TemaRest;
import com.mariots.biblioteca.bibliotecawebadmin.core.dtos.inputrest.TextoRest;
import com.mariots.biblioteca.bibliotecawebadmin.persistence.entities.AutorEntity;
import com.mariots.biblioteca.bibliotecawebadmin.persistence.entities.SupertemaEntity;
import com.mariots.biblioteca.bibliotecawebadmin.persistence.entities.TemaEntity;
import com.mariots.biblioteca.bibliotecawebadmin.persistence.entities.TextoEntity;
import com.mariots.biblioteca.bibliotecawebadmin.persistence.repository.RepositoryBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper{

    @Autowired
    RepositoryBiblioteca repository;

    //ENTITY -->> DTO
    @Override
    public AutorDto toDto(AutorEntity autorEntity) {
        AutorDto autorDto = new AutorDto();
        autorDto.setIdAutor(autorEntity.getIdAutor());
        autorDto.setNombreAutor(autorEntity.getNombreAutor());
        autorDto.setFechaAutor(autorEntity.getFechaAutor());
        autorDto.setDescripcionBreve(autorEntity.getDescripcionBreve());
        autorDto.setDescripcionLarga(autorEntity.getDescripcionLarga());
        if (autorEntity.getTextos() == null){
        } else autorDto.setIdTextos(autorEntity.getTextos().stream().map((t)->(t.getIdTexto())).collect(Collectors.toList()));
        return autorDto;
    }

    @Override
    public TextoDto toDto(TextoEntity textoEntity) {
        TextoDto textoDto =new TextoDto();
        textoDto.setIdTexto(textoEntity.getIdTexto());
        textoDto.setTextoString(textoEntity.getTextoString());
        textoDto.setLongitud(textoEntity.getLongitud());
        if (textoEntity.getTemas() == null) {
        } else textoDto.setIdTemas(textoEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
        if (textoEntity.getAutor() == null) {
        } else textoDto.setIdAutor(textoEntity.getAutor().getIdAutor());
        return textoDto;
    }

    @Override
    public TemaDto toDto(TemaEntity temaEntity) {
        TemaDto temaDto= new TemaDto();
        temaDto.setIdTema(temaEntity.getIdTema());
        temaDto.setNombreTema(temaEntity.getNombreTema());
        if (temaEntity.getSupertema() == null) {
        } else temaDto.setIdSupertema(temaEntity.getSupertema().getIdSupertema());
        if (temaEntity.getTextos() == null) {
        } else temaDto.setIdTextos(temaEntity.getTextos().stream().map(TextoEntity::getIdTexto).collect(Collectors.toList()));
        return temaDto;
    }

    @Override
    public SupertemaDto toDto(SupertemaEntity supertemaEntity) {
        SupertemaDto supertemaDto = new SupertemaDto();
        supertemaDto.setIdSupertema(supertemaEntity.getIdSupertema());
        supertemaDto.setNombreSupertema(supertemaEntity.getNombreSupertema());
        if(supertemaEntity.getTemas() == null){
        } else supertemaDto.setIdTemas(supertemaEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
        return supertemaDto;
    }

    //DTO -->> ENTITY
    @Override
    public AutorEntity toEntity(AutorDto autorDto) {
        AutorEntity autorEntity = new AutorEntity();
        if (autorDto.getNombreAutor()==null){
            throw new CampoEnBlancoException("nombreAutor");
        }
        //El id se define en BD
        autorEntity.setNombreAutor(autorDto.getNombreAutor());
        autorEntity.setFechaAutor(autorDto.getFechaAutor());
        autorEntity.setDescripcionBreve(autorDto.getDescripcionBreve());
        autorEntity.setDescripcionLarga(autorDto.getDescripcionLarga());
        if (autorDto.getIdTextos() == null) {
        } else {
            List<TextoEntity> listaTextosEntity= new ArrayList<>();
            autorDto.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
            autorEntity.setTextos(listaTextosEntity);
        }
        return autorEntity;
    }

    @Override
    public SupertemaEntity toEntity(SupertemaDto supertemaDto) {
        SupertemaEntity supertemaEntity = new SupertemaEntity();
        if(supertemaDto.getNombreSupertema()==null){
            throw new CampoEnBlancoException("nombreSupertema");
        }

        supertemaEntity.setNombreSupertema(supertemaDto.getNombreSupertema());
        if (supertemaDto.getIdTemas() == null) {
        } else {
            List<TemaEntity> listaTemasEntity = new ArrayList<>();
            supertemaDto.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
            supertemaEntity.setTemas(listaTemasEntity);
        }
        return supertemaEntity;
    }

    @Override
    public TemaEntity toEntity(TemaDto temaDto) {
        TemaEntity temaEntity= new TemaEntity();
        if (temaDto.getNombreTema()==null){
            throw new CampoEnBlancoException("nombreTema");
        }
        temaEntity.setNombreTema(temaDto.getNombreTema());
        if (temaEntity.getTextos() == null) {
        }else{
            List<TextoEntity> listaTextosEntity = new ArrayList<>();
            temaDto.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
            temaEntity.setTextos(listaTextosEntity);
        }
        return temaEntity;
    }

    @Override
    public TextoEntity toEntity(TextoDto textoDto) {
        TextoEntity textoEntity = new TextoEntity();
        if (textoDto.getTextoString()==null){
            throw new CampoEnBlancoException("textoString");
        }
        textoEntity.setTextoString(textoDto.getTextoString());
        textoEntity.setLongitud(textoDto.getLongitud());
        if (textoDto.getIdAutor() == null) {
        } else{
           textoEntity.setAutor(repository.recuperarAutorPorId(textoDto.getIdAutor()).get());
        }
        if (textoDto.getIdTemas() == null) {
        } else{
            List<TemaEntity> listaTemasEntity = new ArrayList<>();
            textoDto.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
            textoEntity.setTemas(listaTemasEntity);
        }
        return textoEntity;
    }

    //NUEVO-REST --> DTO
    @Override
    public AutorDto toDto(AutorRest autorRest) {
        return new AutorDto().builder()
                .nombreAutor(autorRest.getNombreAutor())
                .fechaAutor(autorRest.getFechaAutor())
                .descripcionBreve(autorRest.getDescripcionBreve())
                .descripcionLarga(autorRest.getDescripcionLarga())
                .build();
    }

    @Override
    public TextoDto toDto(TextoRest textoRest) {
        TextoDto textoDto = new TextoDto().builder()
                .textoString(textoRest.getTextoString())
                .longitud(textoRest.getLongitud())
                .build();
        List<Integer> idTemas = new ArrayList<>();
        //comprobamos si el nombre de autor se ha introducido
        if(textoRest.getNombreAutor()==null) {
            //no se ha introducido --> excepción
            throw new CampoEnBlancoException();
        } else {
            //se ha introducido -->
            //comprobamos si existe el nombre de autor en BD
            if(repository.recuperarAutorPorNombre(textoRest.getNombreAutor()).isPresent()){
                //existe entonces añadimos el autor al textoDto
                int idAutor =repository.recuperarAutorPorNombre(textoRest.getNombreAutor()).get().getIdAutor();
                textoDto.setIdAutor(idAutor);
            } else {
                //no existe --> excepción y pedimos otro
                throw new RecursoNoEncontradoException("No existe el nombre de autor aportado, ingrese un nombre de autor previamente registrado");
            }
        }
        //comprobamos si existen los temas aportados
        if(textoRest.getNombreTemas()==null){
            throw new CampoEnBlancoException();
        } else {
            //comprobamos si existen en BD los temas aportados, si hay alguno que no está en BD se lanza ex
            if(textoRest.getNombreTemas().stream().allMatch((nombreTema)->repository.recuperarTemaPorNombre(nombreTema).isPresent())){
                textoRest.getNombreTemas().stream().map((nombreTema)->idTemas.add(repository.recuperarTemaPorNombre(nombreTema).get().getIdTema())).collect(Collectors.toList());
                textoDto.setIdTemas(idTemas);
            } else {
                throw new RecursoNoEncontradoException("No existe el nombre de tema aportado, ingrese un nombre de autor previamente registrado");
            }
        }
    return textoDto;
    }

    @Override
    public TemaDto toDto(TemaRest temaRest) {
        return new TemaDto().builder()
                .nombreTema(temaRest.getNombreTema())
                .build();
    }

    @Override
    public SupertemaDto toDto(SupertemaRest supertemaRest) {
        return new SupertemaDto().builder()
                .nombreSupertema(supertemaRest.getNombreSupertema())
                .build();
    }

}
