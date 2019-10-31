package azur.support.webtool.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.Etat;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {
    
    //List<Etat> findByLibelle(String libelle);
    
}
