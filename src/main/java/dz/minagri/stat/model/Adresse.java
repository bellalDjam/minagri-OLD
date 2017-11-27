package dz.minagri.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import dz.minagri.stat.util.Identifiable;


@Entity
@Table(name = "adress")
public class Adresse extends Identifiable {

    private static final long serialVersionUID = 178515645232651000L;

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private int version;

    @Column(name = "rue")
    @Size(max = 80)
    private String rue;

    @Column(name = "numero")
    private String numero;

    @Column(name = "boitePostale")
    private String boitePostale;

    @Column(name = "codePostal")
    private Long codePostal;
    
    @ManyToOne
	@JoinColumn(name = "commune_id", nullable = false)
	private Commune commune;
    
//    @OneToOne(mappedBy="address")
//    private Departement departement;
    /**
     * Constructeur.
     */
    public Adresse() {

    }

    
    public 	Adresse(String numero,String rue,Long codePostal,Commune commune) {
    	this.numero =numero;
    	this.rue =rue;
    	this.codePostal =codePostal;
    	this.commune =commune;
    }
    public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}

    public String getRue() {
	return rue;
    }

    public void setRue(String rue) {
	this.rue = rue;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getBoitePostale() {
	return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
	this.boitePostale = boitePostale;
    }

	public Long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the commune
	 */
	public Commune getCommune() {
		return commune;
	}


	/**
	 * @param commune the commune to set
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", numero=" + numero + ", boitePostale=" + boitePostale
				+ ", codePostal=" + codePostal + ", nomCommune=" + "]";
	}

	/**
	 * @return the departement
	 */
//	public Departement getDepartement() {
//		return departement;
//	}
//
//
//	/**
//	 * @param departement the departement to set
//	 */
//	public void setDepartement(Departement departement) {
//		this.departement = departement;
//	}


	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	public boolean persisted() {
		return getId()!=null;
	}
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Adresse.class;
	}
}
