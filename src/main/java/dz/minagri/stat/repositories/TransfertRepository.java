package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, Long> {

}