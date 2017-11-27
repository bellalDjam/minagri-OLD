/**
 * 
 */
package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.AspectParcel;


/**
 * @author bellal djamel
 *
 */
@Repository
public interface AspectParcelRepository extends JpaRepository<AspectParcel, Long>{

	
}
