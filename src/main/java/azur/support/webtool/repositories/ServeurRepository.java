package azur.support.webtool.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Config;
import azur.support.webtool.entities.Serveur;

@Repository
public interface ServeurRepository extends CrudRepository<Serveur, Long> {
    
    //List<Serveur> findByConfigId(Integer configId);
    List<Serveur> findByClient(Client client);
}
