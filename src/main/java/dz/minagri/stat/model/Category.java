package dz.minagri.stat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "category")
public class Category extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5130400337821748406L;
    @NotNull
    @Id
    @GeneratedValue
    private Long id = (long) -1;
    
    @Version
    private int version;
    
    @NotNull
    private String name;
    
 

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }



	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Category.class;
	}
}
