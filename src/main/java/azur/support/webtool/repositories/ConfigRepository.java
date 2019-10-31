package azur.support.webtool.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    
    List<Config> findByClient(Client client);
//    List<Client> findRaisonSociale(String raisonSociale);
//    List<Client> findByConfigId(String configId);
////    List<Client> findByContact(String contact);
    
}
