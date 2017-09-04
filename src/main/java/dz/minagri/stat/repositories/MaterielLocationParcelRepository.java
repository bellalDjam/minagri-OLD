package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.MaterielLocation;

public interface MaterielLocationParcelRepository extends JpaRepository<MaterielLocation, Long> {

}