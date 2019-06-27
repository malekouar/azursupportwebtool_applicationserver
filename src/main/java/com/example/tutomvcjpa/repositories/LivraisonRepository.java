package com.example.tutomvcjpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutomvcjpa.entities.Livraison;

@Repository
public interface LivraisonRepository extends CrudRepository<Livraison, Long> {
    
    //List<Intervention> findByResponsable(String responsable);
    
}
