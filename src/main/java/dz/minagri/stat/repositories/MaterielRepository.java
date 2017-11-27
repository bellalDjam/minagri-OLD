package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Materiel;
@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {

}