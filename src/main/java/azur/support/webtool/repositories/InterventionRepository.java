package azur.support.webtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Intervention;

@Repository
public interface InterventionRepository extends CrudRepository<Intervention, Long> {
    
   //List<Intervention> findByResponsable(String responsable);
    
}
