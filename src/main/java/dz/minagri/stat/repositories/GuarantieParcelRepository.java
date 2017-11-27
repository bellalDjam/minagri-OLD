package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Guarantie;
@Repository
public interface GuarantieParcelRepository extends JpaRepository<Guarantie, Long> {

}