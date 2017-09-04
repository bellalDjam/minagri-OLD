package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Semence;

public interface SemenceRepository extends JpaRepository<Semence, Long> {

}