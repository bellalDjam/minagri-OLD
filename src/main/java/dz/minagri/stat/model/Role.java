/**
 * 
 */
package dz.minagri.stat.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;



/**
 * @author bellal djamel
 *
 */
@Entity

public class Role   implements GrantedAuthority, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7260920989305721522L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(name = "authority", nullable = false,unique = true)
	@Size(min=4, max=255)
	private String authority;
	
	@Column(name = "description")
	@Size(min=1, max=255)
	private String description;
	
	@Column(name = "authenticated")
	private Boolean authenticated=true;
	
	

	  /**
	 * 
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String authority, String description) {
		  this.authority=authority;
		  this.description=description;
	  }

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


    /**
	 * @param authority2
	 * @param description
	 */
    public Long getId() {
        return id;
    }


    @Override
//	@Column(unique = true)
    public String getAuthority() {
        return authority;
    }
    public Role(String authority) {
        setAuthority(authority);
    }
	 public void setAuthority(String authority) {
	        Objects.requireNonNull(authority);
	        this.authority = authority;
	    }
    @Override
    public String toString() {
        return authority;
    }

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
}
