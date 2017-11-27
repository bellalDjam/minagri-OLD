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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.enumeration.TypeCulture;
import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name="variete")
public class Variete extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676342614825537146L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private int version;
	private int dureeCroissanceJour; 
	private String name;
	
	@Column(name = "typeculture", nullable = true)
	@Enumerated(EnumType.STRING)
	private TypeCulture typeCulture;

	@Column(name = "originesemence", nullable = true)
	@Enumerated(EnumType.STRING)
	private OrigineSemence origineSemence;
	
	@ManyToOne
	@JoinColumn(name = "espececultivee_id", nullable = false)
	private EspeceCultivee especeCultivee;
	@Override
	
	public Long getId() {
		return id;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	public TypeCulture getTypeCulture() {
		return typeCulture;
	}


	public void setTypeCulture(TypeCulture typeCulture) {
		this.typeCulture = typeCulture;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the origineSemence
	 */
	public OrigineSemence getOrigineSemence() {
		return origineSemence;
	}


	/**
	 * @param origineSemence the origineSemence to set
	 */
	public void setOrigineSemence(OrigineSemence origineSemence) {
		this.origineSemence = origineSemence;
	}

	public int getDureeCroissanceJour() {
		return dureeCroissanceJour;
	}


	public void setDureeCroissanceJour(int dureeCroissanceJour) {
		this.dureeCroissanceJour = dureeCroissanceJour;
	}

	
	/**
	 * @return the especeCultivee
	 */
	public EspeceCultivee getEspeceCultivee() {
		return especeCultivee;
	}

	/**
	 * @param especeCultivee the especeCultivee to set
	 */
	public void setEspeceCultivee(EspeceCultivee especeCultivee) {
		this.especeCultivee = especeCultivee;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		return Variete.class;
	}

}
