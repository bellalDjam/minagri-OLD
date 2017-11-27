/**
 * 
 */
package dz.minagri.stat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.minagri.stat.repositories.VarieteRepository;

/**
 * @author bellal djamel
 *
 */
public class VarieteServiceImp implements VarieteService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	VarieteRepository repo;
	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.VarieteService#delete(java.lang.Long)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	@CacheEvict(value = "varieties", key = "#id")
	public void delete(Long id) {
		logger.info("> delete id:{}", id);
		repo.delete(id);
		logger.info("< delete id:{}", id);
	}


	/* (non-Javadoc)
	 * @see dz.minagri.stat.services.VarieteService#evictCache()
	 */
	@Override
	@CacheEvict(
			value = "varieties",
			allEntries = true)
	public void evictCache() {
		logger.info("> evictCache");
		logger.info("< evictCache");

	}
	
	
}
