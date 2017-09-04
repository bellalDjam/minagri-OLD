package dz.minagri.stat.util;


import com.vaadin.data.provider.DataProvider;
import dz.minagri.stat.model.Product;

public interface ProductDataProvider
        extends DataProvider<Product, String> {
    
	 /**
     * Store given product to the repository.
     *
     * @param product
     *            the updated or new product
     */
    public void save(Product product);

    /**
     * Delete given product from the repository.
     *
     * @param product
     *            the product to be deleted
     */
    public void delete(Product product);
}
