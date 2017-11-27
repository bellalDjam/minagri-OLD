/**
 * 
 */
package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Variete;



/**
 * @author bellal djamel
 *
 */
@Repository
public interface VarieteRepository extends JpaRepository<Variete, Long>{

	Variete findByNameLike(String name);
	List<Variete> findByNameContainingIgnoreCase(String name);
}
