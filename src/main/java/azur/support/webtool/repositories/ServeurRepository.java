package azur.support.webtool.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Serveur;

@Repository
public interface ServeurRepository extends JpaRepository<Serveur, Long> {
    
    //List<Serveur> findByConfigId(Integer configId);
    List<Serveur> findByClient(Client client);
}
