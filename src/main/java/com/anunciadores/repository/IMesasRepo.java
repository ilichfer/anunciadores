package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anunciadores.model.Mesa;

@Repository
public interface IMesasRepo extends JpaRepository<Mesa, Integer>{


}
