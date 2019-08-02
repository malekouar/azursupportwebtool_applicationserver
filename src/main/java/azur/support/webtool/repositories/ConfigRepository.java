package azur.support.webtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Config;

@Repository
public interface ConfigRepository extends CrudRepository<Config, Long> {
    
    //List<Config> findByClientId(String clientId);
//    List<Client> findRaisonSociale(String raisonSociale);
//    List<Client> findByConfigId(String configId);
////    List<Client> findByContact(String contact);
    
}
