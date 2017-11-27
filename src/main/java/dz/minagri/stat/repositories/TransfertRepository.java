package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Transfert;
@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {

}