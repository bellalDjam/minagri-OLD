package dz.minagri.stat.model;
import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "Personne")
public class Personne extends Identifiable {
	
	private static final long serialVersionUID = 2220804875980046822L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
    
	@Column(name = "nom",  nullable = false)
	@NotNull
	@Size(min = 3)
	private String nom;
	
	@Column(name = "prenom", nullable = false)
	@NotNull
	@Size(min = 3)
	private String prenom;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Adresse address;
	
	@Column(name = "datenaissance")
	private Date dateNaissance;
	
    @Embedded
    private MoyenContact moyenContact; 

    @Enumerated(EnumType.STRING)
	private TypePersonne typePersonne;
    
    @Version
	@Column(name = "version")
	private Integer version;
    
    
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "departement_id", nullable = false)
  private Departement departement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAddress() {
		return address;
	}

	public void setAddress(Adresse address) {
		this.address = address;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
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

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Personne.class;
	}
}
