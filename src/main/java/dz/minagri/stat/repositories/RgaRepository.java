package dz.minagri.stat.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.Exploitation;
import dz.minagri.stat.model.Rga;

@Repository
public interface RgaRepository extends JpaRepository<Rga, Long> {
	
//	List<Rga> findByExploitation(Long id);
//	List<Category> findByCategoryLike(String productName, Pageable pageable);
//	List<Product> findDistinctByProductNameContainingIgnoreCaseOrAvailabilityInOrCategoryIn(
//            String productName, Collection<Availability> availability,
//            Collection<Category> category, Pageable page);
}