package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

}