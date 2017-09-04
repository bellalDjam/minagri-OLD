package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
