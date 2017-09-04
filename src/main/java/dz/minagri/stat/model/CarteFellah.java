package dz.minagri.stat.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
//	
//	 @Version
//	    private int version;
//	
	private String zone;
	
	@Column(name = "registration_date",columnDefinition = "DATE")
	private LocalDate registrationDate;
	
	private String numS12;


	public CarteFellah() {
		
	}

	public Long getId() {
		return id;
	}


	public String getZone() {
		return zone;
	}



	public void setZone(String zone) {
		this.zone = zone;
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

	@Override
	public Class<?> getConcreteClass() {
		return CarteFellah.class;
	}



	public String getNumS12() {
		return numS12;
	}



	public void setNumS12(String numS12) {
		this.numS12 = numS12;
	}

}
