/**
 * 
 */
package dz.minagri.stat.services;

/**
 * @author bellal djamel
 *
 */
public interface VarieteService {

	 void delete(Long id);

	    /**
	     * Evicts all members of the "Variete" cache.
	     */
	    void evictCache();
}
