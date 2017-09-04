 package dz.minagri.stat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import dz.minagri.stat.util.Identifiable;

/**
 * Entity implementation class for Entity: Facture
 *
 */
@Entity
@Table(name="Facture")
public class Facture extends Identifiable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Fournisseur_id", nullable = false)
	private Fournisseur fournisseur;
	
	@Column(name = "numFacture", nullable = false)
	private String numFacture;
	
	@Column(name = "codFacture", nullable = false)
	private Integer codFacture;
	
	@Column(name = "totFacture", nullable = false)
	private Integer totFacture;
	
	@Column(name = "dateFacture", nullable = false)
	private Date dateFacture;
	
	@Column(name = "dateEcheanceFacture", nullable = false)
	private Date dateEcheanceFacture;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bankOrder_id", nullable = true)
		private BankOrder bankOrder;

	public Facture() {
		super();
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}



	public Integer getCodFacture() {
		return codFacture;
	}

	public void setCodFacture(Integer codFacture) {
		this.codFacture = codFacture;
	}

	public Integer getTotFacture() {
		return totFacture;
	}

	public void setTotFacture(Integer totFacture) {
		this.totFacture = totFacture;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Date getDateEcheanceFacture() {
		return dateEcheanceFacture;
	}

	public void setDateEcheanceFacture(Date dateEcheanceFacture) {
		this.dateEcheanceFacture = dateEcheanceFacture;
	}

	public Long getId() {
		return id;
	}
	

	public BankOrder getBankOrder() {
		return bankOrder;
	}

	public void setBankOrder(BankOrder bankOrder) {
		this.bankOrder = bankOrder;
	}

	@Override
	public String toString() {
		return "Facture [id=" + id + ", fournisseur=" + fournisseur
				+ ", numFacture=" + numFacture + ", codFacture=" + codFacture
				+ ", totFacture=" + totFacture + ", dateFacture=" + dateFacture
				+ ", dateEcheanceFacture=" + dateEcheanceFacture + "]";
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return Facture.class;
	}
   
}
