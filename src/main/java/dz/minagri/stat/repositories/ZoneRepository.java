package dz.minagri.stat.repositories;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.enumeration.Availability;
import dz.minagri.stat.model.Category;
import dz.minagri.stat.model.Product;
import dz.minagri.stat.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
	
	List<Zone> findByNameContainingIgnoreCase(String name);
//	List<Category> findByCategoryLike(String productName, Pageable pageable);
//	List<Product> findDistinctByProductNameContainingIgnoreCaseOrAvailabilityInOrCategoryIn(
//            String productName, Collection<Availability> availability,
//            Collection<Category> category, Pageable page);

}