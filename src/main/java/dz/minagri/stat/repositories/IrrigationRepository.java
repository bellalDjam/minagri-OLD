package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Irrigation;

public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {

}