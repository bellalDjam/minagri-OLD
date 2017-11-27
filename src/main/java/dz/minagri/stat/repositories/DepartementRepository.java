package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Departement;
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

}