/**
 * 
 */
package dz.minagri.stat.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.services.AccountService;

/**
 * @author bellal djamel
 *
 */
@Service()
public class AccountUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountService accountService;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Account account = accountService.findByUserName(username);
		if(account ==null) {
		//Not found  return null;
			throw new UsernameNotFoundException("User " + username + " is unknown");
		}
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : account.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		User userDetails = new User(account.getUsername(),account.getPassword()
				,account.isEnabled(),account.isNonExpired(),account.isCredentialNonExpired()
				,account.isNonLocked(), grantedAuthorities);
		
		return userDetails;
	}

}
