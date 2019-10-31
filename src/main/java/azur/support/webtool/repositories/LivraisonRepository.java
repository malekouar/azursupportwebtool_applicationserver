package azur.support.webtool.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Livraison;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    
    //List<Intervention> findByResponsable(String responsable);
    
}
