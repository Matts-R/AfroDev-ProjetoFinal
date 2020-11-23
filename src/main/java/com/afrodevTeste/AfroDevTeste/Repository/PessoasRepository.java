package com.afrodevTeste.AfroDevTeste.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afrodevTeste.AfroDevTeste.model.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, Integer>{

}
