package dz.minagri.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import dz.minagri.stat.util.Identifiable;


@Entity
@Table(name = "adress")
public class Adresse extends Identifiable {

    private static final long serialVersionUID = 178515645232651000L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    private Integer codePostal;
    
    @Column(name = "nomCommune")
    @Size(max = 20)
	private String nomCommune;
    
    


    /**
     * Constructeur.
     */
    public Adresse() {

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

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", numero=" + numero + ", boitePostale=" + boitePostale
				+ ", codePostal=" + codePostal + ", nomCommune=" + nomCommune + "]";
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Adresse.class;
	}
}
