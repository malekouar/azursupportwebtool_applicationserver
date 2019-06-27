package com.example.tutomvcjpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutomvcjpa.entities.Serveur;

@Repository
public interface ServeurRepository extends CrudRepository<Serveur, Long> {
    
    //List<Serveur> findByConfigId(Integer configId);
    
}
