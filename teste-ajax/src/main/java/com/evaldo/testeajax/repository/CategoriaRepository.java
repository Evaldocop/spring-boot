package com.evaldo.testeajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaldo.testeajax.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
