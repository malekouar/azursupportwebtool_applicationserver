package azur.support.webtool.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Intervention;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    
   //List<Intervention> findByResponsable(String responsable);
    
}
