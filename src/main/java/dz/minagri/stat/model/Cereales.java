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

import dz.minagri.stat.enumeration.TypeCereales;
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
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}


	private String nom;
	
	private int dureeCroissanceJour; 
	
	 @Column(name = "typecereales", nullable = false)
	    @Enumerated(EnumType.STRING)
		private TypeCereales typeCereales;


	public Cereales() {
		super();
	}


	public Long getId() {
		return id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getDureeCroissanceJour() {
		return dureeCroissanceJour;
	}


	public void setDureeCroissanceJour(int dureeCroissanceJour) {
		this.dureeCroissanceJour = dureeCroissanceJour;
	}


	public TypeCereales getTypeCereales() {
		return typeCereales;
	}


	public void setTypeCereales(TypeCereales typeCereales) {
		this.typeCereales = typeCereales;
	}


	@Override
	public Class<?> getConcreteClass() {
		return Cereales.class;
	}

}
