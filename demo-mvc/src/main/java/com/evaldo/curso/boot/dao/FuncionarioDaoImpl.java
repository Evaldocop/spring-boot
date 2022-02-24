package com.evaldo.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.evaldo.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
