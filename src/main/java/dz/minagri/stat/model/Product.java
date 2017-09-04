package dz.minagri.stat.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dz.minagri.stat.enumeration.Availability;
import dz.minagri.stat.util.Identifiable;

@Entity
public class Product extends Identifiable  {

    @NotNull
    @Id
    @GeneratedValue
    private Long id = (long) -1;

    @Version
    private int version;

    @NotNull
    @Size(min = 2, message = "Product name must have at least two characters")
    private String productName = "";
    @Min(0)
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_and_categories", joinColumns = {
            @JoinColumn(name = "product_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "category_id") })
    private Set<Category> category = new HashSet<>();

    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;
    @NotNull
    private Availability availability = Availability.COMING;

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Product.class;
	}

}