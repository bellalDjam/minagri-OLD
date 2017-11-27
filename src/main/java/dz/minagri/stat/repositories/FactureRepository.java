package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

}