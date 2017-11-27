/**
 * 
 */
package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Retard;

/**
 * @author bellal djamel
 *
 */
@Repository
public interface RetardRepository extends JpaRepository<Retard, Long>{

}
