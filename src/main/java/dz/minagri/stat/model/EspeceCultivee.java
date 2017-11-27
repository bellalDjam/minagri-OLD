package dz.minagri.stat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="espececultivee")
public class EspeceCultivee extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8181288722082554897L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private int version;
	private int dureeCroissanceJour; 
	private String name;
 
	
	@OneToMany(mappedBy = "especeCultivee",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "variete_id", nullable = true)
	private List<Variete> varieties =new ArrayList<Variete>();

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

	public EspeceCultivee() {
		super();
	}


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
	 * @return the varieties
	 */
	public List<Variete> getVarieties() {
		return varieties;
	}


	/**
	 * @param varieties the varieties to set
	 */
	public void setVarieties(List<Variete> varieties) {
		this.varieties = varieties;
	}
	
	public void AddVariete(Variete variete) {
		variete.setEspeceCultivee(this);
		varieties.add(variete);
	}

	public int getDureeCroissanceJour() {
		return dureeCroissanceJour;
	}


	public void setDureeCroissanceJour(int dureeCroissanceJour) {
		this.dureeCroissanceJour = dureeCroissanceJour;
	}
	@Override
	public Class<?> getConcreteClass() {
		return EspeceCultivee.class;
	}

}
