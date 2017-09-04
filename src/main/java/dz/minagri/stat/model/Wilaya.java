package dz.minagri.stat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "wilaya")
public class Wilaya extends Identifiable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4806583311402830176L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
//	 @Version
//	    private int version;
	
	@Column(name = "nomwilaya", nullable = false)
	private String nomWilaya;
	
	@Column(name = "codewilaya", nullable = false)
	private Long codeWilaya;

	 @OneToMany(mappedBy = "wilaya")
	private List<Commune> communes;
	 
	public Long getId() {
		return id;
	}
	/**
	 * @return the communes
	 */
	public List<Commune> getCommunes() {
		return communes;
	}


	/**
	 * @param communes the communes to set
	 */
	public void setCommunes(List<Commune> communes) {
		this.communes = communes;
	}


	public String getNomWilaya() {
		return nomWilaya;
	}



	public void setNomWilaya(String nomWilaya) {
		this.nomWilaya = nomWilaya;
	}



	public Long getCodeWilaya() {
		return codeWilaya;
	}



	public void setCodeWilaya(Long codeWilaya) {
		this.codeWilaya = codeWilaya;
	}

	@Override
	public Class<?> getConcreteClass() {
		return Wilaya.class;
	}

}
