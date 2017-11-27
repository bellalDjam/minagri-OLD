/**
 * 
 */
package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.EspeceCultivee;



/**
 * @author bellal djamel
 *
 */
@Repository
public interface EspeceCultiveeRepository extends JpaRepository<EspeceCultivee, Long>{
	EspeceCultivee  findOneByName(String name);
	List<EspeceCultivee> findByNameContainingIgnoreCase(String text);
}
