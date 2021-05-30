package com.unla.grupo21.repositories;

import com.unla.grupo21.entities.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Serializable> {
    //
    Lugar findById(int id);
    Lugar findByLugar(String lugar);
    Lugar findByCodigoPostal(String codigoPostal);
}