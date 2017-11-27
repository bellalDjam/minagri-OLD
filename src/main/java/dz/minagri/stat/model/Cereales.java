package dz.minagri.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.TypeCulture;
import dz.minagri.stat.util.Identifiable;
@Entity
@Table(name="cereales")
public class Cereales extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4030252899533975002L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private int version;

	private String name;

	@Column(name = "typeculture", nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeCulture typeCulture;


	public Cereales() {
		super();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public TypeCulture getTypeCulture() {
		return typeCulture;
	}


	public void setTypeCulture(TypeCulture typeCulture) {
		this.typeCulture = typeCulture;
	}

	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	


	@Override
	public Class<?> getConcreteClass() {
		return Cereales.class;
	}

}
