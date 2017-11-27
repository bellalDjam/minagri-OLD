package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

	Adresse findByRueAndNumeroLike(String name,String numero);
	/**
	 * @param value
	 * @return
	 */
	List<Adresse> findByRueContaining(String rue);
}