package dz.minagri.stat.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.TypeDeSourceEaux;
import dz.minagri.stat.enumeration.TypeEaux;
import dz.minagri.stat.enumeration.TypeIrrigation;
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
	private LocalDate dateRealisation;
	private String name;
	private String otherEnergy;
	private int profondeur;
	private int consomationElectrique;
	private int consomationGasoil;
	
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeEaux typeEaux;	

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeIrrigation typeIrrigation;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeDeSourceEaux typeDeSourceEaux;
	
	@Column(name = "dLat", nullable = true,unique =true)
	private Double dLat;
	
	@Column(name = "dLon", nullable = true,unique =true)
	private Double dLon;
	
	private double stockCapacity;
	private double debit;

	
	/**
	 * 
	 */
	public Irrigation() {
	}

	/**
	 * @param name
	 * @param typeEaux
	 * @param typeIrrigation
	 * @param typeDeSourceEaux
	 * @param stockCapacity
	 * @param debit
	 */
	public Irrigation(String name, TypeEaux typeEaux, TypeIrrigation typeIrrigation, TypeDeSourceEaux typeDeSourceEaux,
			double stockCapacity, double debit) {
		super();
		this.name = name;
		this.typeEaux = typeEaux;
		this.typeIrrigation = typeIrrigation;
		this.typeDeSourceEaux = typeDeSourceEaux;
		this.stockCapacity = stockCapacity;
		this.debit = debit;
	}

	@Override
	public Long getId() {
		return id;
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
	 * @return the typeEaux
	 */
	public TypeEaux getTypeEaux() {
		return typeEaux;
	}

	/**
	 * @param typeEaux the typeEaux to set
	 */
	public void setTypeEaux(TypeEaux typeEaux) {
		this.typeEaux = typeEaux;
	}

	/**
	 * @return the typeIrrigation
	 */
	public TypeIrrigation getTypeIrrigation() {
		return typeIrrigation;
	}

	/**
	 * @param typeIrrigation the typeIrrigation to set
	 */
	public void setTypeIrrigation(TypeIrrigation typeIrrigation) {
		this.typeIrrigation = typeIrrigation;
	}

	/**
	 * @return the typeDeSourceEaux
	 */
	public TypeDeSourceEaux getTypeDeSourceEaux() {
		return typeDeSourceEaux;
	}

	/**
	 * @param typeDeSourceEaux the typeDeSourceEaux to set
	 */
	public void setTypeDeSourceEaux(TypeDeSourceEaux typeDeSourceEaux) {
		this.typeDeSourceEaux = typeDeSourceEaux;
	}

	/**
	 * @return the stockCapacity
	 */
	public double getStockCapacity() {
		return stockCapacity;
	}

	/**
	 * @param stockCapacity the stockCapacity to set
	 */
	public void setStockCapacity(double stockCapacity) {
		this.stockCapacity = stockCapacity;
	}

	/**
	 * @return the debit
	 */
	public double getDebit() {
		return debit;
	}

	/**
	 * @param debit the debit to set
	 */
	public void setDebit(double debit) {
		this.debit = debit;
	}

	/**
	 * @return the profondeur
	 */
	public int getProfondeur() {
		return profondeur;
	}

	/**
	 * @param profondeur the profondeur to set
	 */
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	/**
	 * @return the consomationElectrique
	 */
	public int getConsomationElectrique() {
		return consomationElectrique;
	}

	/**
	 * @param consomationElectrique the consomationElectrique to set
	 */
	public void setConsomationElectrique(int consomationElectrique) {
		this.consomationElectrique = consomationElectrique;
	}

	/**
	 * @return the consomationGasoil
	 */
	public int getConsomationGasoil() {
		return consomationGasoil;
	}

	/**
	 * @param consomationGasoil the consomationGasoil to set
	 */
	public void setConsomationGasoil(int consomationGasoil) {
		this.consomationGasoil = consomationGasoil;
	}

	/**
	 * @return the otherEnergy
	 */
	public String getOtherEnergy() {
		return otherEnergy;
	}

	/**
	 * @param otherEnergy the otherEnergy to set
	 */
	public void setOtherEnergy(String otherEnergy) {
		this.otherEnergy = otherEnergy;
	}

	/**
	 * @return the dateRealisation
	 */
	public LocalDate getDateRealisation() {
		return dateRealisation;
	}

	/**
	 * @param dateRealisation the dateRealisation to set
	 */
	public void setDateRealisation(LocalDate dateRealisation) {
		this.dateRealisation = dateRealisation;
	}

	/**
	 * @return the dLat
	 */
	public Double getdLat() {
		return dLat;
	}

	/**
	 * @param dLat the dLat to set
	 */
	public void setdLat(Double dLat) {
		this.dLat = dLat;
	}

	/**
	 * @return the dLon
	 */
	public Double getdLon() {
		return dLon;
	}

	/**
	 * @param dLon the dLon to set
	 */
	public void setdLon(Double dLon) {
		this.dLon = dLon;
	}

	@Override
	public Class<?> getConcreteClass() {
		return Irrigation.class;
	}

}
