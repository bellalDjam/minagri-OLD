/**
 * 
 */
package dz.minagri.stat.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name="persistentlogin")
public class PersistentLogin extends Identifiable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1938716889691481543L;

	@Id
	private Long id;

	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="token", unique=true, nullable=false)
	private String token;
	
	@Column(name = "registration_date",columnDefinition = "DATE")
	private LocalDate registrationDate;
	
	@Column(name = "lastused",columnDefinition = "DATE")
	private LocalDate lastUsed;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return PersistentLogin.class;
	}
	
	
}

