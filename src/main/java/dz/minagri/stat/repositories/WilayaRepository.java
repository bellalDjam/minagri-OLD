/**
 * 
 */
package dz.minagri.stat.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Wilaya;

/**
 * @author bellal djamel
 *
 */
@Repository
public interface WilayaRepository extends JpaRepository<Wilaya, Long> {
	
	Wilaya findOneBynomWilaya(String nomWilaya);
//	List<Commune> findAllCommunesByNomWilaya(String nomWilaya);

	/**
	 * @param filterText
	 * @return
	 */
	Collection<Wilaya> findByNomWilayaStartsWithIgnoreCase(String filterText);

}
