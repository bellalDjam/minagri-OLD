package dz.minagri.stat.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.CarteFellah;
@Repository
public interface CarteFellahRepository extends JpaRepository<CarteFellah, Long> {

	
	 List<CarteFellah> findAllByNumS12ContainingIgnoreCase(String numS12);
}