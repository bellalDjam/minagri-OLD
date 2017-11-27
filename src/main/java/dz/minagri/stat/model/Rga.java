package dz.minagri.stat.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dz.minagri.stat.enumeration.EtatExploitation;
import dz.minagri.stat.enumeration.EtatMauvHerb;
import dz.minagri.stat.enumeration.RgaQuality;
import dz.minagri.stat.enumeration.TypeExploitation;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.util.Identifiable;

@Entity
@Table(name = "rga")
public class Rga extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4259120820891043481L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Version
	private int version;
	
	private int durationInMin;
	private String noteSupervisor;
	private String noteAgent;
	private String noteExploitant;
	private String description;
	
	@OneToOne()
	@JoinColumn(name ="account_id",nullable=false, insertable=false, updatable=false)
	private Account account;
	
	@OneToOne()
	@JoinColumn(name ="personne_id",nullable=false, insertable=false, updatable=false)
	private Personne manager;

	@Enumerated(EnumType.STRING)
	private RgaQuality rgaQuality;
	
	@Column(name = "opnening_date",columnDefinition = "DATE")
	private LocalDate opneningDate;
	
	@Column(name = "closing_Date",columnDefinition = "DATE")
	private LocalDate closingDate;
	
	@OneToOne(mappedBy="rga")
	private PreviewsProduction previewsProduction;
	

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "exploitation_id", nullable = false)
	private Exploitation exploitation;
//	
//	@ManyToOne()
//	@JoinColumn(name="zone_id")
//	private Zone zone;
//	
//	@ManyToOne()
//	@JoinColumn(name="wilaya_id")
//	private Wilaya wilaya;
	
//	@OneToMany(mappedBy = "rga")
//	private List<ProductionParcel> productionParcels;
	
	public Rga() {
		super();
	}
	
	@Override
	public Long getId() {
		return id;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the durationInMin
	 */
	public int getDurationInMin() {
		return durationInMin;
	}
	/**
	 * @param durationInMin the durationInMin to set
	 */
	public void setDurationInMin(int durationInMin) {
		this.durationInMin = durationInMin;
	}
	/**
	 * @return the noteSupervisor
	 */
	public String getNoteSupervisor() {
		return noteSupervisor;
	}
	/**
	 * @param noteSupervisor the noteSupervisor to set
	 */
	public void setNoteSupervisor(String noteSupervisor) {
		this.noteSupervisor = noteSupervisor;
	}
	/**
	 * @return the noteAgent
	 */
	public String getNoteAgent() {
		return noteAgent;
	}
	/**
	 * @param noteAgent the noteAgent to set
	 */
	public void setNoteAgent(String noteAgent) {
		this.noteAgent = noteAgent;
	}
	/**
	 * @return the noteExploitant
	 */
	public String getNoteExploitant() {
		return noteExploitant;
	}
	/**
	 * @param noteExploitant the noteExploitant to set
	 */
	public void setNoteExploitant(String noteExploitant) {
		this.noteExploitant = noteExploitant;
	}
	/**
	 * @return the persone
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param persone the persone to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the supervisor
	 */
	public Personne getSupervisor() {
		return manager;
	}
	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(Personne manager) {
		this.manager = manager;
	}
	/**
	 * @return the rgaQuality
	 */
	public RgaQuality getRgaQuality() {
		return rgaQuality;
	}
	/**
	 * @param rgaQuality the rgaQuality to set
	 */
	public void setRgaQuality(RgaQuality rgaQuality) {
		this.rgaQuality = rgaQuality;
	}
	/**
	 * @return the opneningDate
	 */
	public LocalDate getOpneningDate() {
		return opneningDate;
	}
	/**
	 * @param opneningDate the opneningDate to set
	 */
	public void setOpneningDate(LocalDate opneningDate) {
		this.opneningDate = opneningDate;
	}
	/**
	 * @return the closingDate
	 */
	public LocalDate getClosingDate() {
		return closingDate;
	}
	/**
	 * @param closingDate the closingDate to set
	 */
	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}
	/**
	 * @return the previewsProduction
	 */
	public PreviewsProduction getPreviewsProduction() {
		return previewsProduction;
	}
	/**
	 * @param previewsProduction the previewsProduction to set
	 */
	public void setPreviewsProduction(PreviewsProduction previewsProduction) {
		this.previewsProduction = previewsProduction;
	}
//	public Zone getZone() {
//		return zone;
//	}
//	/**
//	 * @param zone the zone to set
//	 */
//	public void setZone(Zone zone) {
//		this.zone = zone;
//	}
//	/**
//	 * @return the wilaya
//	 */
//	public Wilaya getWilaya() {
//		return wilaya;
//	}
//	/**
//	 * @param wilaya the wilaya to set
//	 */
//	public void setWilaya(Wilaya wilaya) {
//		this.wilaya = wilaya;
//	}


	@Override
	public Class<?> getConcreteClass() {
		return Rga.class;
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
	 * @return the exploitation
	 */
	public Exploitation getExploitation() {
		return exploitation;
	}

	/**
	 * @param exploitation the exploitation to set
	 */
	public void setExploitation(Exploitation exploitation) {
		this.exploitation = exploitation;
	}
	
}
