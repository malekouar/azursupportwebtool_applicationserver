package azur.support.webtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Etat;

@Repository
public interface EtatRepository extends CrudRepository<Etat, Long> {
    
    //List<Etat> findByLibelle(String libelle);
    
}
