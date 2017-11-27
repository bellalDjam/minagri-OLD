package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findAllByAuthorityContaining(String authority);
	 Role findByAuthority(String roleName);
	/**
	 * @param id
	 * @return
	 */
	Role findById(Long id);

	/**
	 * @param authority
	 * @return
	 */
	Role findOneByAuthority(String authority);
}