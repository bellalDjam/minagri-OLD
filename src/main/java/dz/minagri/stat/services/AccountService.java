/**
 * 
 */
package dz.minagri.stat.services;

import dz.minagri.stat.model.Account;

/**
 * @author bellal djamel
 *
 */
public interface AccountService {
	
	Account findByUserName(String userName);

}
