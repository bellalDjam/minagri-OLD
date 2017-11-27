package dz.minagri.stat.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.util.Identifiable;
@Entity
@Table(name="semence")
public class Semence extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4383262579323334953L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private int version;
	private String name; 
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "variete_id", nullable = true)
	private Variete variete;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cereales_id", nullable = true)
	private Cereales cereales;


	@Column(name = "originesemence", nullable = true)
	@Enumerated(EnumType.STRING)
	private OrigineSemence origineSemence;


	public Semence() {
		super();
	}


	public Long getId() {
		return id;
	}

	public OrigineSemence getOrigineSemence() {
		return origineSemence;
	}


	public void setOrigineSemence(OrigineSemence origineSemence) {
		this.origineSemence = origineSemence;
	}

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


	/**
	 * @return the variete
	 */
	public Variete getVariete() {
		return variete;
	}


	/**
	 * @param variete the variete to set
	 */
	public void setVariete(Variete variete) {
		this.variete = variete;
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


	@Override
	public Class<?> getConcreteClass() {
		return Semence.class;
	}

}
