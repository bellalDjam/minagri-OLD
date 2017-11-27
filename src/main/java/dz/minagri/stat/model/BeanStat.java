/**
 * 
 */
package dz.minagri.stat.model;

import java.time.LocalDate;

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

import dz.minagri.stat.enumeration.EtatMauvHerb;
import dz.minagri.stat.enumeration.EtatSanitaire;
import dz.minagri.stat.enumeration.EtatVegetParcelle;
import dz.minagri.stat.enumeration.SampleType;
import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name = "beanstat")
public class BeanStat extends Identifiable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6644422942655033827L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Version
	private int version;
	
	@Column(name = "sampletype", nullable = true)
	@Enumerated(EnumType.STRING)
	private SampleType sampleType;
	
	private int episparmettrecar;
	private int nbrgrain1epis;
	private int nbrgrain2epis;
	private int nbrgrain3epis;


	@Column(name = "record_date",columnDefinition = "DATE")
	private LocalDate recorddate;
	@ManyToOne()
	@JoinColumn(name = "productionparcel_id", nullable = false)
	private ProductionParcel productionparcel;
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

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * @return the recorddate
	 */
	public LocalDate getRecorddate() {
		return recorddate;
	}

	/**
	 * @param recorddate the recorddate to set
	 */
	public void setRecorddate(LocalDate recorddate) {
		this.recorddate = recorddate;
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
	 * @return the sampleType
	 */
	public SampleType getSampleType() {
		return sampleType;
	}

	/**
	 * @param sampleType the sampleType to set
	 */
	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	/**
	 * @return the episparmettrecar
	 */
	public int getEpisparmettrecar() {
		return episparmettrecar;
	}

	/**
	 * @param episparmettrecar the episparmettrecar to set
	 */
	public void setEpisparmettrecar(int episparmettrecar) {
		this.episparmettrecar = episparmettrecar;
	}

	/**
	 * @return the nbrgrain1epis
	 */
	public int getNbrgrain1epis() {
		return nbrgrain1epis;
	}

	/**
	 * @param nbrgrain1epis the nbrgrain1epis to set
	 */
	public void setNbrgrain1epis(int nbrgrain1epis) {
		this.nbrgrain1epis = nbrgrain1epis;
	}

	/**
	 * @return the nbrgrain2epis
	 */
	public int getNbrgrain2epis() {
		return nbrgrain2epis;
	}

	/**
	 * @param nbrgrain2epis the nbrgrain2epis to set
	 */
	public void setNbrgrain2epis(int nbrgrain2epis) {
		this.nbrgrain2epis = nbrgrain2epis;
	}

	/**
	 * @return the nbrgrain3epis
	 */
	public int getNbrgrain3epis() {
		return nbrgrain3epis;
	}

	/**
	 * @param nbrgrain3epis the nbrgrain3epis to set
	 */
	public void setNbrgrain3epis(int nbrgrain3epis) {
		this.nbrgrain3epis = nbrgrain3epis;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		return BeanStat.class;
	}

}
