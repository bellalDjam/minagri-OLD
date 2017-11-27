package dz.minagri.stat.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@GeneratedValue
	private Long id;
	
	@Version
	private int version;
	
	@Column(name = "nomwilaya", unique= true, nullable = false)
	private String nomWilaya;
	
	@Column(name = "codewilaya",unique= true, nullable = false)
	private Long codeWilaya;

	@Column(name = "totarea")
	private Integer totarea;
	
	@Column(name = "totpopulation")
	private Integer totpopulation;
	
	@OneToMany(mappedBy = "wilaya",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
	 * 
	 */
	public Wilaya() {
	}

	/**
	 * @param nomWilaya
	 * @param codeWilaya
	 * @param totarea
	 * @param totpopulation
	 */
	public Wilaya(String nomWilaya, long codeWilaya, Integer totarea, Integer totpopulation) {
		super();
		this.nomWilaya = nomWilaya;
		this.codeWilaya = codeWilaya;
		this.totarea = totarea;
		this.totpopulation = totpopulation;
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

	/**
	 * @return the totarea
	 */
	public Integer getTotarea() {
		return totarea;
	}
	/**
	 * @param totarea the totarea to set
	 */
	public void setTotarea(Integer totarea) {
		this.totarea = totarea;
	}
	/**
	 * @return the totpopulation
	 */
	public Integer getTotpopulation() {
		return totpopulation;
	}
	/**
	 * @param totpopulation the totpopulation to set
	 */
	public void setTotpopulation(Integer totpopulation) {
		this.totpopulation = totpopulation;
	}
	@Override
	public Class<?> getConcreteClass() {
		return Wilaya.class;
	}
	public void addCommune(Commune commune) {
		commune.setWilaya(this);
		communes.add(commune);
	}

}
