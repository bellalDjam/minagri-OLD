/**
 * 
 */
package dz.minagri.stat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.MoyenContact;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.RoleRepository;

/**
 * @author bellal djamel
 *
 */
@Service
@Transactional
public class SecurityService {
	static final String ROLE_ADMIN = "ROLE_ADMIN";
			static final String ROLE_USER = "ROLE_USER";

			@Autowired 
			PasswordEncoder bcryptEncoder;
			@Autowired 
			AccountRepository accountRepository;
			@Autowired 
			RoleRepository secRoleRepository;

			public Account findAccountByUsername(String userName){
				Account secUser = accountRepository.findByUsernameLike(userName);
				return secUser;
			}

			public Account findAccountById(Long id){
				Account secUser = accountRepository.findOne(id);
				return secUser;
			}

			public Account getPrincipal(){
				String userName = getAuthentication().getName();
				Account secUser = findAccountByUsername(userName);
				return secUser;
			}

			public Role findRoleByAuthority(String authority){
				return(secRoleRepository.findOneByAuthority(authority));
			}

			public Role findRoleById(Long id){
				return(secRoleRepository.findById(id));
			}

			public Role createRole(String authority, String description){
				Role secRole = findRoleByAuthority(authority);
				if(secRole!=null){
					return(secRole);
				}
				return(secRoleRepository.save(new Role(authority, description)));
			}

			/**
			 * Get the currently logged in user's <code>Authentication</code>. If not authenticated
			 * and the AnonymousAuthenticationFilter is active (true by default) then the anonymous
			 * user's auth will be returned (AnonymousAuthenticationToken with username 'anonymousUser'
			 * unless overridden).
			 *
			 * @return the authentication
			 */
			Authentication getAuthentication() { 
				Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
				if(SecurityContextHolder.getContext()!=null) {
					return authentication;
				} else {
					return null;
				}
					
						}

			public List<Role> findAllRoleByAccount(Account account){
				if(account.getId()!=null) {
					return account.getRoles();
				}else
				return null;
			}

			public List<Role> findAllRoles(){
				return secRoleRepository.findAll();
			}

			public List<Account> findAllAccounts(){
				List<Account> allUsers = accountRepository.findAll();
				return allUsers;
			}
//TODO re implementation
			
//		public	Role grantRole(Account secUser, Role secRole){
//				
//				if(secUser.getId()!=null && secRole.getId()!=null && secUser.getRoles().contains(secRole)) {
//					return secRole;
//				}else if(secUser.getId()!=null && secRole.getId()!=null && !secUser.getRoles().contains(secRole)){
//					secUser.addAuthority(secRole);
//					accountRepository.save(secUser);
//					return secRole;
//				}else 
//					
//					secRole =createRoleIfNotFound(
//				    		"ROLE_USER","role 1st inscription");
//					secUser.addAuthority(secRole);
//					accountRepository.save(secUser);
//					return secRole;
//				 
//			}

			public Role grantRole(Account secUser, Role secRole){
				List<Role> roles  =  new ArrayList<>();
				
				if(secUser!=null && secRole!=null && !secUser.getRoles().contains(secRole)) {
					roles= secUser.getRoles();
					roles.add(secRole);
					 secUser.setRoles(roles);
					 secUser= accountRepository.save(secUser);
				}
					return secRole;
//			
			}
			public List<Role> grantRoleList(Account secUser,List<Role> secRoleList){
				List<Role> roles  =  new ArrayList<>();
				
				if(secUser!=null && !secRoleList.isEmpty() && secUser.getRoles().isEmpty()) {
					roles= secUser.getRoles();
					roles.addAll(secRoleList);
					 secUser.setRoles(roles);
					 accountRepository.save(secUser);
				}
					return secRoleList;
//			
			}
			public Boolean revokeRole(Account secUser, Role secRole){
				if(secUser!=null && secRole!=null && secUser.getRoles().contains(secRole)) {
					secUser.getRoles().remove(secRole);
					return true;
				}else 
					return false;
			
			}

			public Boolean revokeRole(Account secUser, String authority){
				return (revokeRole(secUser, findRoleByAuthority(authority)));
			}

			public Boolean hasRole(Account secUser, Role secRole){
				if(null!=secRole && secUser.getId()!=null){
					
					return(secUser.getRoles().contains(secRole));
				}
				return(!(secUser.getRoles().contains(secRole)));
			}

			public Boolean hasRole(Account secUser, String authority){
				if(!authority.isEmpty() && secUser.getId()!=null) {
				return(hasRole(secUser, findRoleByAuthority(authority)));
				}
				return null;
			}

			public Boolean hasRole(String authority){
				if(!authority.isEmpty()) {
					return(hasRole(getPrincipal(), authority));
				}else return false;
				
			}

			public Account createAccount(String username,String password, String firstName
					,String lastName,String shortInfo,LocalDate birthdate
					,LocalDate registrationDate	,TypeAccount typeAccount,
					Gender gender,List<Role> roles	,MoyenContact moyenContact){
				
				if(findAccountByUsername(username)!=null){
					return(findAccountByUsername(username));
				}
				Account newUser = new Account( username,  firstName, lastName
						, shortInfo,  birthdate
						, registrationDate, typeAccount
						, gender,roles,moyenContact);
				
//				Role role = createRoleIfNotFound(
//			    		"ROLE_USER","role 1st inscription");
//				newUser.addAuthority(role);
				newUser.setEnabled(true);
				newUser.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				newUser.setCreatedAt(LocalDate.now());
				
				String newPassword =username;
				if(!password.isEmpty() && password.trim()!="") {
					newPassword = password;
				}
				
				newUser.setPassword(newPassword);
				newUser = accountRepository.save(newUser);
				grantRoleList(newUser, roles);
//				grantAndRevokeRoles(newUser, rolesToGrant, rolesToRevoke);
//				grantRole(newUser, findRoleByAuthority(ROLE_USER));
				return(newUser);
			}

			public Account createAccount(Account secUser){
				Account existingUser = findAccountByUsername(secUser.getUsername());
				if(existingUser!=null){
					return(existingUser);
				}
//				secUser.setPassword(bcryptEncoder.encode(secUser.getUsername()))
				return accountRepository.save(secUser);
			}

			public Account  changePassword(Account secUser, String password){
				
				secUser.setPassword(password);
				return accountRepository.save(secUser);
			}

//			public  enableOrDisableRole(Account secUser, Boolean enabled){
//				Role accessRole = findRoleByAuthority(ROLE_USER)
//				if(secUser){
//					if(!enabled){
//						revokeRole(secUser, accessRole)
//					}
//					else{
//						grantRole(secUser, accessRole)
//					}
//					secUser.setEnabled(enabled)
//					secUserRepository.save(secUser)
//				}
//			}

//			public  void grantAndRevokeRoles(Account secUser, List<Role> rolesToGrant, List<Role> rolesToRevoke){
//				rolesToGrant.each{ Role secRole ->
//					grantRole(secUser, secRole);
//				}
//				rolesToRevoke.each{ Role secRole ->
//					revokeRole(secUser, secRole);
//				}
//			}

			public Role updateRole(Long secRoleId, String authority, String description){
				Role secRole = findRoleById(secRoleId);
				if(secRole!=null){
					secRole.setAuthority(authority);
					secRoleRepository.save(secRole);
				}
				return(secRole);
			}

			public Role updateRole(Role secRole){
				 if(secRole.getId()!=null) {
				return secRoleRepository.save(secRole);
				 }else
				return null;
			}

			 void deleteRole(Role secRole){
				 if(secRole.getId()!=null) {
						secRoleRepository.delete(secRole.getId());

				 }
			}

			void deleteAccount(Account secUser){
				if(secUser.getId()!=null) {
					accountRepository.delete(secUser);
				}
			}

//			public Account updateAccount(Account secUser, List<Role> rolesToGrant = null){
//				Account user = secUserRepository.save(secUser)
//				if(rolesToGrant != null){
//					List<Role> currentRoles = findAllRoleByAccount(secUser)
//					currentRoles.each{ Role secRole ->
//						revokeRole(secUser, secRole)
//					}
//					rolesToGrant.each{ Role secRole ->
//						grantRole(secUser, secRole)
//					}
//				}
//				return(user)
//			}
			@Transactional
			public Role createRoleIfNotFound(
		    		String authority,String description) {
		  
				Role role = secRoleRepository.findOneByAuthority(authority);
		        if (role == null) {
		            role = new Role(authority,description);
		            secRoleRepository.save(role);
		        }
		        return role;
		    }

		}
