/**
 * 
 */
package dz.minagri.stat.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;


/**
 * @author bellal djamel
 *
 */
@Service
public class AccountServiceBean implements AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	public Account findByUserName(String userName) {
		 Account account = accountRepo.findByUsernameLike(userName);
		 return account;
	}
	
	public void save(String username,String password,String firstName
			,String lastName,String createdBy,List<Role> roles
			,boolean isAccountNonExpired,boolean isAccountNonLocked
			,boolean isCredentialsNonExpired,boolean isEnabled) {
		Account account =new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setCreatedBy(createdBy);
		account.setCreatedAt(LocalDate.now());
		account.setRoles(roles);
		account.setEnabled(true);
		account.setAccountNonLocked(true);
		account.setAccountNonExpired(true);
		account.setCredentialNonExpired(true);
		accountRepo.save(account);
	}

}
