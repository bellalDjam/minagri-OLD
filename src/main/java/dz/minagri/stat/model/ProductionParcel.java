package dz.minagri.stat.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "productionparcel")
public class ProductionParcel extends Identifiable {

	private static final long serialVersionUID = -7931126853315605525L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private int version;
	
	private  double rendementAttend ;
	private boolean stressHydrique;
	private boolean rotation;
	private double rotationFrequency;
	private String rotationdescription;
	
	@Column(name = "plantation_date",columnDefinition = "DATE")
	private LocalDate plantationDate;
	
	@Column(name = "recolt_date",columnDefinition = "DATE")
	private LocalDate recoltdate;
	
	@Column(name = "quantity", nullable = true)
	private double quantityprod;

	@Column(name = "surface", nullable = true)
	private String surface;
	

	
	@ManyToOne()
	@JoinColumn(name = "exploitation_id", nullable = false)
	private Exploitation exploitation;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "irrigation_id", nullable = true)
	private Irrigation irrigation;
	
	@OneToMany(mappedBy = "productionparcel")
	private List<PreviewsProduction> previewsproductions = new ArrayList<PreviewsProduction>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "espececultivee_id", nullable = true)
	private EspeceCultivee espececultivee;
	
	@Column(name = "originesemence", nullable = true)
	@Enumerated(EnumType.STRING)
	private OrigineSemence origineSemence;
	
	@OneToOne(mappedBy = "productionparcel")
	private AspectParcel aspectParcel;
	
	
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

	
	public OrigineSemence getOrigineSemence() {
		return origineSemence;
	}

	public void setOrigineSemence(OrigineSemence origineSemence) {
		this.origineSemence = origineSemence;
	}


	public double getRendement() {
		return rendementAttend;
	}

	public void setRendement(double rendement) {
		this.rendementAttend = rendement;
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
	/**
	 * @return the stressHydrique
	 */
	public boolean isStressHydrique() {
		return stressHydrique;
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
	 * @param stressHydrique the stressHydrique to set
	 */
	public void setStressHydrique(boolean stressHydrique) {
		this.stressHydrique = stressHydrique;
	}

	/**
	 * @return the plantationDate
	 */
	public LocalDate getPlantationDate() {
		return plantationDate;
	}

	/**
	 * @param plantationDate the plantationDate to set
	 */
	public void setPlantationDate(LocalDate plantationDate) {
		this.plantationDate = plantationDate;
	}

	/**
	 * @return the recoltdate
	 */
	public LocalDate getRecoltdate() {
		return recoltdate;
	}

	/**
	 * @param recoltdate the recoltdate to set
	 */
	public void setRecoltdate(LocalDate recoltdate) {
		this.recoltdate = recoltdate;
	}
	
	public void addPreviewsProduction(PreviewsProduction  previewsproduction) {
		previewsproduction.setProductionparcel(this);
		previewsproductions.add(previewsproduction);
	}
	
	/**
	 * @return the rotationFrequency
	 */
	public double getRotationFrequency() {
		return rotationFrequency;
	}

	/**
	 * @param rotationFrequency the rotationFrequency to set
	 */
	public void setRotationFrequency(double rotationFrequency) {
		this.rotationFrequency = rotationFrequency;
	}

	/**
	 * @return the rotationdescription
	 */
	public String getRotationdescription() {
		return rotationdescription;
	}

	/**
	 * @param rotationdescription the rotationdescription to set
	 */
	public void setRotationdescription(String rotationdescription) {
		this.rotationdescription = rotationdescription;
	}
	

	/**
	 * @return the espececultivee
	 */
	public EspeceCultivee getEspececultivee() {
		return espececultivee;
	}

	/**
	 * @param espececultivee the espececultivee to set
	 */
	public void setEspececultivee(EspeceCultivee espececultivee) {
		this.espececultivee = espececultivee;
	}

	/**
	 * @return the previewsproductions
	 */
	public List<PreviewsProduction> getPreviewsproductions() {
		return previewsproductions;
	}

	/**
	 * @param previewsproductions the previewsproductions to set
	 */
	public void setPreviewsproductions(List<PreviewsProduction> previewsproductions) {
		this.previewsproductions = previewsproductions;
	}

	/**
	 * @return the aspectParcel
	 */
	public AspectParcel getAspectParcel() {
		return aspectParcel;
	}

	/**
	 * @param aspectParcel the aspectParcel to set
	 */
	public void setAspectParcel(AspectParcel aspectParcel) {
		this.aspectParcel = aspectParcel;
	}

	/**
	 * @return the rendementAttend
	 */
	public double getRendementAttend() {
		return rendementAttend;
	}

	/**
	 * @param rendementAttend the rendementAttend to set
	 */
	public void setRendementAttend(double rendementAttend) {
		this.rendementAttend = rendementAttend;
	}

	@Override
	public Class<?> getConcreteClass() {
		return ProductionParcel.class;
	}

	
}
