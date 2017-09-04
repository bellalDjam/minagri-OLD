package dz.minagri.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.util.Identifiable;
@Entity
@Table(name="semence")
public class Semence extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4383262579323334953L;

	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	 @Version
	    private int version;
	
	private String nomVariete;
	
	private int dureeCroissanceJour; 
	
	 @Column(name = "originesemence", nullable = true)
	    @Enumerated(EnumType.STRING)
		private OrigineSemence origineSemence;


	public Semence() {
		super();
	}


	public Long getId() {
		return id;
	}


	public String getNom() {
		return nomVariete;
	}


	public void setNom(String nom) {
		this.nomVariete = nom;
	}


	public int getDureeCroissanceJour() {
		return dureeCroissanceJour;
	}


	public void setDureeCroissanceJour(int dureeCroissanceJour) {
		this.dureeCroissanceJour = dureeCroissanceJour;
	}




	public String getNomVariete() {
		return nomVariete;
	}


	public void setNomVariete(String nomVariete) {
		this.nomVariete = nomVariete;
	}


	public OrigineSemence getOrigineSemence() {
		return origineSemence;
	}


	public void setOrigineSemence(OrigineSemence origineSemence) {
		this.origineSemence = origineSemence;
	}


	@Override
	public Class<?> getConcreteClass() {
		return Semence.class;
	}

}
