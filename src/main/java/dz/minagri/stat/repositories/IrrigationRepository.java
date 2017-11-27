package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Irrigation;
@Repository
public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {

}