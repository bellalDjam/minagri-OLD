package dz.minagri.stat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.BankOrder;
@Repository
public interface BankOrderRepository extends JpaRepository<BankOrder, Long> {

	

}