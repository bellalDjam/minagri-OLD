package dz.minagri.stat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "irrigation")
public class Irrigation extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6118832007676378043L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	 @Version
	    private int version;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
