/**
 * 
 */
package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.ProductionParcel;

/**
 * @author bellal djamel
 *
 */
@Repository
public interface ProductionParcelRepository extends JpaRepository<ProductionParcel, Long>{

}
