package dz.minagri.stat.repositories;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.minagri.stat.enumeration.Availability;
import dz.minagri.stat.model.Category;
import dz.minagri.stat.model.Product;

@Repository
public interface ProductRepository  extends  JpaRepository<Product, Long>{
	
	List<Product> findByProductNameLike(String productName, Pageable pageable);
	List<Category> findByCategoryLike(String productName, Pageable pageable);
	List<Product> findDistinctByProductNameContainingIgnoreCaseOrAvailabilityInOrCategoryIn(
            String productName, Collection<Availability> availability,
            Collection<Category> category, Pageable page);

}