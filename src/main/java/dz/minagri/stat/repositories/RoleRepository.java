package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}