package com.example.tutomvcjpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutomvcjpa.entities.Intervention;

@Repository
public interface InterventionRepository extends CrudRepository<Intervention, Long> {
    
   //List<Intervention> findByResponsable(String responsable);
    
}
