package dz.minagri.stat.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dz.minagri.stat.util.Identifiable;



@Entity
@Table(name = "Fournisseur")
public class Fournisseur extends Identifiable {

	private static final long serialVersionUID = -8928208908171645420L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Adresse address;
	
	@Embedded
	private MoyenContact moyenContact;
	 
	@OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
	private List<Facture> factureList;
	
	

	public Fournisseur() {
		super();
	}

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

	public Adresse getAddress() {
		return address;
	}

	public void setAddress(Adresse address) {
		this.address = address;
	}

	public MoyenContact getMoyenContact() {
		return moyenContact;
	}

	public void setMoyenContact(MoyenContact moyenContact) {
		this.moyenContact = moyenContact;
	}
	
	public List<Facture> getFactureList() {
		return factureList;
	}


	public void setFactureList(List<Facture> factureList) {
		this.factureList = factureList;
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Fournisseur.class;
	}
	
	
}
