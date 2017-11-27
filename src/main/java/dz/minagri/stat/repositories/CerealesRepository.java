package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Cereales;
@Repository
public interface CerealesRepository extends JpaRepository<Cereales, Long> {

	Cereales findByNameLike(String name);
	List<Cereales> findByNameContainingIgnoreCase(String name);
}