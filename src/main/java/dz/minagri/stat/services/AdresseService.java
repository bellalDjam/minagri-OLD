/**
 * 
 */
package dz.minagri.stat.services;

import java.util.List;

import dz.minagri.stat.model.Adresse;

/**
 * The AdresseService interface defines all public business behaviors for
 * operations on the Adresse entity model.
 * @author bellal djamel
 *
 */
public interface AdresseService {
	
	/**
     * Find all Adresse entities.
     * @return A Collection of Adresse objects.
     */
	List<Adresse> findAll();
	  /**
     * Find a single Adresse entity by primary key identifier.
     * @param id A Long primary key identifier.
     * @return A Adresse or <code>null</code> if none found.
     */
	Adresse findOne(Long id);
	/**
     * Persists a Adresse entity in the data store.
     * @param adresse A Adresse object to be persisted.
     * @return The persisted Adresse entity.
     */
	Adresse create(Adresse adresse);
	/**
     * Updates a previously persisted Adresse entity in the data store.
     * @param adresse A Adresse object to be update.
     * @return The updated Adresse entity.
     */
	Adresse update(Adresse adresse);

	/**
     * Removes a previously persisted Adresse entity from the data store.
     * @param id A Long primary key identifier.
     */
    void delete(Long id);

    /**
     * Evicts all members of the "adresse" cache.
     */
    void evictCache();
	
}
