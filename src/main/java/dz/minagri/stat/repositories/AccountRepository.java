package dz.minagri.stat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.minagri.stat.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}