package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}