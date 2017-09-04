package dz.minagri.stat.model;

import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.EtatSanitaire;
import dz.minagri.stat.enumeration.EtatVegetParcelle;
import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "exploitation")
public class ProductionParcel extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7931126853315605525L;

	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	 @Version
	    private int version;
		
	private boolean rotation;

	@Column(name = "quantity", nullable = true)
	private double quantityprod;
	
	@Column(name = "surface", nullable = true)
	private String surface;
	
	
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JoinColumn(name = "exploitation_id", nullable = false)
	private Exploitation exploitation;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "irrigation_id", nullable = true)
	private Irrigation irrigation;
	
	 @Column(name = "etatsanitaire", nullable = true)
	 @Enumerated(EnumType.STRING)
	private EtatSanitaire etatSanitaire;
	 
	 @Column(name = "etatvegetparcelle", nullable = true)
	 @Enumerated(EnumType.STRING)
	private EtatVegetParcelle etatVegetParcelle;
	 
	 @Column(name = "originesemence", nullable = true)
	 @Enumerated(EnumType.STRING)
	private OrigineSemence origineSemence;
	 
	public boolean isRotation() {
		return rotation;
	}

	public void setRotation(boolean rotation) {
		this.rotation = rotation;
	}

	public double getQuantityprod() {
		return quantityprod;
	}

	public void setQuantityprod(double quantityprod) {
		this.quantityprod = quantityprod;
	}

	public Exploitation getExploitation() {
		return exploitation;
	}

	public void setExploitation(Exploitation exploitation) {
		this.exploitation = exploitation;
	}

	public Irrigation getIrrigation() {
		return irrigation;
	}

	public void setIrrigation(Irrigation irrigation) {
		this.irrigation = irrigation;
	}

	public EtatSanitaire getEtatSanitaire() {
		return etatSanitaire;
	}

	public void setEtatSanitaire(EtatSanitaire etatSanitaire) {
		this.etatSanitaire = etatSanitaire;
	}

	public EtatVegetParcelle getEtatVegetParcelle() {
		return etatVegetParcelle;
	}

	public void setEtatVegetParcelle(EtatVegetParcelle etatVegetParcelle) {
		this.etatVegetParcelle = etatVegetParcelle;
	}

	public OrigineSemence getOrigineSemence() {
		return origineSemence;
	}

	public void setOrigineSemence(OrigineSemence origineSemence) {
		this.origineSemence = origineSemence;
	}

	public Date getDateplantation() {
		return dateplantation;
	}

	public void setDateplantation(Date dateplantation) {
		this.dateplantation = dateplantation;
	}

	private boolean StresseHydrique;
	
	private Date dateplantation;
	
	private  double rendement ;


	public double getRendement() {
		return rendement;
	}

	public void setRendement(double rendement) {
		this.rendement = rendement;
	}

	public boolean isStresseHydrique() {
		return StresseHydrique;
	}

	public void setStresseHydrique(boolean stresseHydrique) {
		StresseHydrique = stresseHydrique;
	}

	public ProductionParcel() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}

	
	public String getSurface() {
		return surface;
	}


	public void setSurface(String surface) {
		this.surface = surface;
	}

	@Override
	public Class<?> getConcreteClass() {
		return ProductionParcel.class;
	}

}
