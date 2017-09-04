package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {

}