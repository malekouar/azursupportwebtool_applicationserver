package azur.support.webtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Livraison;

@Repository
public interface LivraisonRepository extends CrudRepository<Livraison, Long> {
    
    //List<Intervention> findByResponsable(String responsable);
    
}
