package azur.support.webtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Dossier;

@Repository
public interface DossierRepository extends CrudRepository<Dossier, Long> {
    
    //List<Dossier> findByResponsable(String responsable);
    
//    List<Client> findRaisonSociale(String raisonSociale);
//    List<Client> findByConfigId(String configId);
////    List<Client> findByContact(String contact);
    
}
