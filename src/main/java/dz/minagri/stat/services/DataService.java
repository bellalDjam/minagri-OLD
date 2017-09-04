/**
 * 
 */
package dz.minagri.stat.services;

import java.io.Serializable;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.minagri.stat.model.Category;
import dz.minagri.stat.model.Product;
import dz.minagri.stat.repositories.CategoryRepository;
import dz.minagri.stat.repositories.ProductRepository;

/**
 * @author bellal djamel
 *
 */
@Service
@Transactional
public  class DataService  implements Serializable{
	 /**
	 * 
	 */
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Collection<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.delete(productId);
    }

    public Product getProduct(Long productId) {
        return productRepository.findOne(productId);
    }
}
