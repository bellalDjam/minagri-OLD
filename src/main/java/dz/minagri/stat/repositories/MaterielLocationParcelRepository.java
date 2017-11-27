package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.MaterielLocation;
@Repository
public interface MaterielLocationParcelRepository extends JpaRepository<MaterielLocation, Long> {

}