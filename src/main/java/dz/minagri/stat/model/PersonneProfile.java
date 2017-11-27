/**
 * 
 */
package dz.minagri.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dz.minagri.stat.enumeration.EtatExploitation;
import dz.minagri.stat.enumeration.PersonneProfileType;
import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name="personneprofile")
public class PersonneProfile extends Identifiable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4666788719978873998L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	@Enumerated(EnumType.STRING)
	private PersonneProfileType personneProfileType;
	
	
	
	
	@Override
	public Long getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		return PersonneProfile.class;
	}




}

