package dz.minagri.stat.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.model.PreviewsProduction;

@Repository
public interface PreviewsProductionRepository extends JpaRepository<PreviewsProduction, Long> {
	
//	List<PreviewsProduction> findByNameContainingIgnoreCase(String name);
//	List<Category> findByCategoryLike(String productName, Pageable pageable);
//	List<Product> findDistinctByProductNameContainingIgnoreCaseOrAvailabilityInOrCategoryIn(
//            String productName, Collection<Availability> availability,
//            Collection<Category> category, Pageable page);
//	PreviewsProduction findByNameLike(String name);
}