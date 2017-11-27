package dz.minagri.stat.model;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "personne")
public class Personne extends Identifiable {

	private static final long serialVersionUID = 2220804875980046822L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="manager_id")
    private Personne manager;
	
	 @OneToMany(mappedBy="manager")
	    private List<Personne> directs= new ArrayList<Personne>();
	 
	@Column(name = "firstName",  nullable = false)
	@NotNull
	@Size(min = 3)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	@NotNull
	@Size(min = 3)
	private String lastName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Adresse address;

	@Column(name = "datenaissance",columnDefinition = "DATE")
	private LocalDate dateNaissance;

	@Embedded
	private MoyenContact moyenContact; 

	@Enumerated(EnumType.STRING)
	private TypePersonne typePersonne;

	@Version
	@Column(name = "version")
	private Integer version;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "departement_id", nullable = true)
	private Departement departement;
	
//	public Personne() {
//		directs = new ArrayList<Personne>();
//	}

	public Long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Adresse getAddress() {
		return address;
	}

	public void setAddress(Adresse address) {
		this.address = address;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public MoyenContact getMoyenContact() {
		return moyenContact;
	}

	public void setMoyenContact(MoyenContact moyenContact) {
		this.moyenContact = moyenContact;
	}

	public TypePersonne getTypePersonne() {
		return typePersonne;
	}

	public void setTypePersonne(TypePersonne typePersonne) {
		this.typePersonne = typePersonne;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	

	/**
	 * @return the manager
	 */
	public Personne getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(Personne manager) {
		this.manager = manager;
	}

	/**
	 * @return the directs
	 */
	public List<Personne> getDirects() {
		return directs;
	}

	/**
	 * @param directs the directs to set
	 */
	public void setDirects(List<Personne> directs) {
		this.directs = directs;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		return Personne.class;
	}
}
