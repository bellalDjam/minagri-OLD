package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.exception.UsernameAlreadyInUseException;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.Role;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUsernameLike(String userName);

	/**
	 * @param filterText
	 * @return
	 */
	List<Account> findByLastNameStartsWithIgnoreCase(String filterText);

	
	List<Account> findByUsernameContaining(String filterText);
}