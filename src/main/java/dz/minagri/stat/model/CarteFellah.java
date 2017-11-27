package dz.minagri.stat.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "cartefellah")
public class CarteFellah extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4986186010714212286L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

	@ManyToOne()
	@JoinColumn(name = "zone_id", nullable = false)
	private Zone zone;

	@Column(name = "registration_date",columnDefinition = "DATE")
	private LocalDate registrationDate;

	private String numS12;


	public CarteFellah() {

	}

	public Long getId() {
		return id;
	}
	/**
	 * @return the registrationDate
	 */
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param localDate the registrationDate to set
	 */
	public void setRegistrationDate(LocalDate localDate) {
		this.registrationDate = localDate;
	}

	public String getNumS12() {
		return numS12;
	}



	public void setNumS12(String numS12) {
		this.numS12 = numS12;
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
	 * @return the zone
	 */
	public Zone getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	@Override
	public Class<?> getConcreteClass() {
		return CarteFellah.class;
	}

}
