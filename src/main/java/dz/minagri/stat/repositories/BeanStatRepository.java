package dz.minagri.stat.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.BeanStat;

@Repository
public interface BeanStatRepository extends JpaRepository<BeanStat, Long> {
	
//	List<BeanStat> findByNameContainingIgnoreCase(String name);
//	List<Category> findByCategoryLike(String productName, Pageable pageable);
//	List<Product> findDistinctByProductNameContainingIgnoreCaseOrAvailabilityInOrCategoryIn(
//            String productName, Collection<Availability> availability,
//            Collection<Category> category, Pageable page);
//	BeanStat findByNameLike(String name);
}