//package com.mariots.biblioteca.bibliotecawebadmin.repository;
//
//import com.mariots.biblioteca.bibliotecawebadmin.repository.entities.AutorEntity;
//import com.mariots.biblioteca.bibliotecawebadmin.repository.entities.SupertemaEntity;
//import com.mariots.biblioteca.bibliotecawebadmin.repository.entities.TemaEntity;
//import com.mariots.biblioteca.bibliotecawebadmin.repository.entities.TextoEntity;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface RepositoryBiblioteca {
//    public AutorEntity guardarAutor(AutorEntity autorEntity);
//    public TemaEntity guardarTema(TemaEntity temaEntity);
//    public SupertemaEntity guardarSupertema(SupertemaEntity supertemaEntity);
//    public TextoEntity guardarTexto(TextoEntity textoEntity);
//
//    public List<AutorEntity> recuperarAutores();
//    public List<TemaEntity> recuperarTemas();
//    public List<SupertemaEntity> recuperarSupertemas();
//    public List<TextoEntity> recupearTextos();
//
//    public Optional<AutorEntity> recuperarAutorPorId(int idAutor);
//    public Optional<TemaEntity> recuperarTemaPorId(int idTema);
//    public Optional<SupertemaEntity> recuperarSupertemaPorId(int idSupertema);
//    public Optional<TextoEntity> recuperarTextoPorId(int idTexto);
//
//    public Optional<AutorEntity> recuperarAutorPorNombre(String nombreAutor);
//    public Optional<TemaEntity> recuperarTemaPorNombre(String nombreTema);
//    public Optional<SupertemaEntity> recuperarSupertemaPorNombre(String nombreSupertema);
//
//    public void eliminarAutor(AutorEntity autor);
//    public void eliminarTexto(TextoEntity texto);
//    public void eliminarTema(TemaEntity tema);
//    public void eliminarSupertema(SupertemaEntity supertema);
//}
