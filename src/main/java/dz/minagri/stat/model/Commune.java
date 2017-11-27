/**
 * 
 */
package dz.minagri.stat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.util.Identifiable;

/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name = "commune")
public class Commune extends Identifiable {
	private static final long serialVersionUID = 2413461820531944335L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "name",unique=true)
	private String name;

	@Version
	private int version;

	@ManyToOne()
	@JoinColumn(name = "wilaya_id", nullable = false)
	private Wilaya wilaya;

	@Column(name = "type_commune")
	@Enumerated(EnumType.STRING)
	private TypeCommune typeCommune;

	private Integer caract1;

	private String caract2;

	@OneToMany(mappedBy = "commune",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Zone> zones;
	
	@Column(name = "codesubdivision")
	private Long codeSubdiv;

	@Column(unique= true, name = "codecommune" )
	private Long codeCommune;

	@OneToMany(mappedBy = "commune", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Adresse> adresses;

	public Commune() {
		
	}
	/**
	 * @return the caract1
	 */
	public Integer getCaract1() {
		return caract1;
	}

	/**
	 * @param caract1 the caract1 to set
	 */
	public void setCaract1(Integer caract1) {
		this.caract1 = caract1;
	}

	/**
	 * @return the caract2
	 */
	public String getCaract2() {
		return caract2;
	}

	/**
	 * @param caract2 the caract2 to set
	 */
	public void setCaract2(String caract2) {
		this.caract2 = caract2;
	}

	public Long getId() {
		return id;
	}

	public Wilaya getWilaya() {
		return wilaya;
	}

	public void setWilaya(Wilaya wilaya) {
		this.wilaya = wilaya;
	}

	public Long getCodeSubdiv() {
		return codeSubdiv;
	}

	public void setCodeSubdiv(Long codeSubdiv) {
		this.codeSubdiv = codeSubdiv;
	}

	public Long getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(Long codeCommune) {
		this.codeCommune = codeCommune;
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
	 * @return the typeCommune
	 */
	public TypeCommune getTypeCommune() {
		return typeCommune;
	}

	/**
	 * @param typeCommune the typeCommune to set
	 */
	public void setTypeCommune(TypeCommune typeCommune) {
		typeCommune = typeCommune;
	}

	/**
	 * @return the adresses
	 */
	public List<Adresse> getAdresses() {
		return adresses;
	}
	/**
	 * @param adresses the adresses to set
	 */
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	/**
	 * @return the zones
	 */
	public List<Zone> getZones() {
		return zones;
	}

	/**
	 * @param zones the zones to set
	 */
	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public void addZone(Zone zone) {
		zone.setCommune(this);
		zones.add(zone);
	}

	public void addAdresse(Adresse adresse) {
		adresse.setCommune(this);
		adresses.add(adresse);
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
	@Override
	public Class<?> getConcreteClass() {
		return Commune.class;
	}

}

