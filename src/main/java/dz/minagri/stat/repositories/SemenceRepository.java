package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Semence;
@Repository
public interface SemenceRepository extends JpaRepository<Semence, Long> {

	Semence findByNameLike(String name);
}