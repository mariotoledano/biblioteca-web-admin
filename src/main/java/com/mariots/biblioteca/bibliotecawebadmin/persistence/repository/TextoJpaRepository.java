package com.mariots.biblioteca.bibliotecawebadmin.persistence.repository;

import com.mariots.biblioteca.bibliotecawebadmin.persistence.entities.TextoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextoJpaRepository extends JpaRepository<TextoEntity, Integer> {
}
