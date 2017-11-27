/**
 * 
 */
package dz.minagri.stat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import dz.minagri.stat.model.Account;
import dz.minagri.stat.repositories.AccountRepository;

/**
 * @author bellal djamel
 *
 */
@Service
public class UserAccountService {

	 @Autowired
	    private AccountRepository userRepository;

	    @Secured("ROLE_ADMIN")
	    public void deleteUser(Account user) {
	        userRepository.delete(user);
	    }

	    public Iterable<Account> getAllUsers() {
	        return userRepository.findAll();
	    }
}
