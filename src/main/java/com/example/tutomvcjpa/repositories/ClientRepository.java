package com.example.tutomvcjpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutomvcjpa.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    
//    List<Client> findByContact(String contact);
//    List<Client> findByRaisonSociale(String raisonSociale);
//    List<Client> findByConfigId(String configId);
//    List<Client> findByContact(String contact);
    
}
