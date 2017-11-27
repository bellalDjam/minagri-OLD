package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Personne;
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
//	List<Personne> findByManagerOrFirstNameAndLastNameContaining(String firstName,String lastName);
	List<Personne> findByLastNameStartsWithIgnoreCase(String lastName);
}
