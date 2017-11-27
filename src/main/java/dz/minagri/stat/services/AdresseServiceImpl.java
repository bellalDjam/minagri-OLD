/**
 * 
 */
package dz.minagri.stat.services;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.repositories.AdresseRepository;

/**
 * 
 * @author bellal djamel
 *
 */
@Service
@Transactional
public class AdresseServiceImpl implements AdresseService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdresseRepository repo;

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#findAll()
	 */
	@Override
	public List<Adresse> findAll() {
		logger.info("> findAll");
		List<Adresse> adresses = repo.findAll();
		logger.info("< findAll");
		return adresses;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#findOne(java.lang.Long)
	 */
	@Override
	@Cacheable(value = "adresses", key = "#id")
	public Adresse findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Adresse adresse = repo.findOne(id);
		logger.info("< findOne id:{}", id);
		return adresse;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#create(dz.minagri.stat.model.Adresse)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	@CachePut(value = "adresses", key = "#id")
	public Adresse create(Adresse adresse) {
		logger.info("> create");
		// Ensure the entity object to be created does NOT exist in the
		// repository. Prevent the default behavior of save() which will update
		// an existing entity if the entity matching the supplied id exists.

		if(adresse.getId() !=null) {
			// Cannot create Greeting with specified ID value
//			logger.error(
//					"Attempted to Recreat an Existing Entity.");
//			throw new EntityExistsException(
//					"The id attribute must be null to persist a new entity.");
			update( adresse);
		}

		Adresse savedAdresse = repo.save(adresse);
		logger.info("< create");
		return savedAdresse;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#update(dz.minagri.stat.model.Adresse)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	@CachePut(value = "adresses", key = "#adresse.id")
	public Adresse update(Adresse adresse) {
		logger.info("> update id:{}", adresse.getId());
		// Ensure the entity object to be updated exists in the repository to
		// prevent the default behavior of save() which will persist a new
		// entity if the entity matching the id does not exist
		Adresse adresseToUpdate = findOne(adresse.getId());
		if(adresseToUpdate==null) {
			logger.error(
					"Attempted to update a Entity, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}
		Adresse adresseUpdated = repo.save(adresseToUpdate);
		logger.info("< update id:{}", adresse.getId());  
		return adresseUpdated;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#delete(java.lang.Long)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	@CacheEvict(value = "adresses", key = "#id")
	public void delete(Long id) {
		logger.info("> delete id:{}", id);
		repo.delete(id);
		logger.info("< delete id:{}", id);
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.AdresseService#evictCache()
	 */
	@Override
	@CacheEvict(
			value = "adresses",
			allEntries = true)
	public void evictCache() {
		logger.info("> evictCache");
		logger.info("< evictCache");

	}


}
