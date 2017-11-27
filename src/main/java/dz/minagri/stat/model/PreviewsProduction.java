/**
 * 
 */
package dz.minagri.stat.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name = "previewsproduction")
public class PreviewsProduction extends Identifiable {

	private static final long serialVersionUID = -7931126853315605525L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private int version;

    private String otherdestination;
    
    private double forCCLS;
    
    private double forcustumor;
    
    private double fortransformer;
    
    private double livestock;
    
	private String autre;
	
	@OneToOne()
	@JoinColumn(name ="rga_id",nullable=false)
	private  Rga rga;
	
	@Column(name = "recolt_date",columnDefinition = "DATE")
	private LocalDate recoltDate;
	
	@ManyToOne()
	@JoinColumn(name = "productionparcel_id", nullable = false)
	private ProductionParcel productionparcel;

	/**
	 * @return the otherdestination
	 */
	public String getOtherdestination() {
		return otherdestination;
	}

	/**
	 * @param otherdestination the otherdestination to set
	 */
	public void setOtherdestination(String otherdestination) {
		this.otherdestination = otherdestination;
	}


	/**
	 * @return the forCCLS
	 */
	public double getForCCLS() {
		return forCCLS;
	}

	/**
	 * @param forCCLS the forCCLS to set
	 */
	public void setForCCLS(double forCCLS) {
		this.forCCLS = forCCLS;
	}

	/**
	 * @return the forcustumor
	 */
	public double getForcustumor() {
		return forcustumor;
	}


	/**
	 * @param forcustumor the forcustumor to set
	 */
	public void setForcustumor(double forcustumor) {
		this.forcustumor = forcustumor;
	}


	/**
	 * @return the fortransformer
	 */
	public double getFortransformer() {
		return fortransformer;
	}



	/**
	 * @param fortransformer the fortransformer to set
	 */
	public void setFortransformer(double fortransformer) {
		this.fortransformer = fortransformer;
	}



	/**
	 * @return the livestock
	 */
	public double getLivestock() {
		return livestock;
	}



	/**
	 * @param livestock the livestock to set
	 */
	public void setLivestock(double livestock) {
		this.livestock = livestock;
	}



	/**
	 * @return the rotationdescription
	 */
	public String getRotationdescription() {
		return autre;
	}



	/**
	 * @param rotationdescription the rotationdescription to set
	 */
	public void setRotationdescription(String rotationdescription) {
		this.autre = rotationdescription;
	}



	/**
	 * @return the recoltDate
	 */
	public LocalDate getRecoltDate() {
		return recoltDate;
	}



	/**
	 * @param recoltDate the recoltDate to set
	 */
	public void setRecoltDate(LocalDate recoltDate) {
		this.recoltDate = recoltDate;
	}



	/**
	 * @return the productionparcel
	 */
	public ProductionParcel getProductionparcel() {
		return productionparcel;
	}



	/**
	 * @param productionparcel the productionparcel to set
	 */
	public void setProductionparcel(ProductionParcel productionparcel) {
		this.productionparcel = productionparcel;
	}



	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the rga
	 */
	public Rga getRga() {
		return rga;
	}

	/**
	 * @param rga the rga to set
	 */
	public void setRga(Rga rga) {
		this.rga = rga;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	


	@Override
	public Class<?> getConcreteClass() {
		return PreviewsProduction.class;
	}

	

	
}
