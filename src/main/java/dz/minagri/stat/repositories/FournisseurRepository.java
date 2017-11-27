package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{

}
