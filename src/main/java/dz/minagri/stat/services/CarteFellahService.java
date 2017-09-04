/**
 * 
 */
package dz.minagri.stat.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.repositories.CarteFellahRepository;

/**
 * @author bellal djamel
 *
 */
@Service
@Transactional
public class CarteFellahService {
	
	@Autowired
	CarteFellahRepository carteFellahRepository;
	
	public List<CarteFellah> getAllCartesF(){
		return carteFellahRepository.findAll();
	}

}
