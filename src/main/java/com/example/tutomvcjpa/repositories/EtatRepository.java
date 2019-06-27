package com.example.tutomvcjpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutomvcjpa.entities.Etat;

@Repository
public interface EtatRepository extends CrudRepository<Etat, Long> {
    
    //List<Etat> findByLibelle(String libelle);
    
}
