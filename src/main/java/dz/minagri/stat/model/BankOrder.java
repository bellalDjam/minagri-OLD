package dz.minagri.stat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dz.minagri.stat.enumeration.EtatCheque;
import dz.minagri.stat.util.Identifiable;

/**
 * Entity implementation class for Entity: Bank_Order
 *
 */
@Entity
@Table(name="BankOrder")
public class BankOrder extends Identifiable  {

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	 @Version
	    private int version;
    
	@Column(name = "etatCheque", nullable = false)
	@Enumerated(EnumType.STRING)
    private EtatCheque etatCheque;
   
	@Column(name = "Montant")
	private Integer montant;

    @Column(name = "numero_cheque", nullable = false)
    private Integer numero_cheque;

    @Column(name = "numero_depart", nullable = false)
    private Integer numero_depart;
    
    @Column(name = "serie_cheque")
    private String serie_cheque;
    
    @Column(name = "date_singnatur")
    private Date date_singnatur;
    
    @Column(name = "date_echeance")
    private Date date_echeance;
    
    @Column(name = "date_validation")
    private Date date_validation;

    

	public BankOrder() {
		super();
	}
 
    public EtatCheque getEtatCheque() {
		return etatCheque;
	}

	public void setEtatCheque(EtatCheque etatCheque) {
		this.etatCheque = etatCheque;
	}

  
    public String getSerie_cheque() {
		return serie_cheque;
	}

	public void setSerie_cheque(String serie_cheque) {
		this.serie_cheque = serie_cheque;
	}


	public Date getDate_validation() {
		return date_validation;
	}



	public void setDate_validation(Date date_validation) {
		this.date_validation = date_validation;
	}




	public Long getId() {
		return id;
	}

	
	public Integer getMontant() {
		return montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}

	public Integer getNumero_cheque() {
		return numero_cheque;
	}

	public void setNumero_cheque(Integer numero_cheque) {
		this.numero_cheque = numero_cheque;
	}

	public Integer getNumero_depart() {
		return numero_depart;
	}

	public void setNumero_depart(Integer numero_depart) {
		this.numero_depart = numero_depart;
	}

	public Date getDate_singnatur() {
		return date_singnatur;
	}



	public void setDate_singnatur(Date date_singnatur) {
		this.date_singnatur = date_singnatur;
	}



	public Date getDate_echeance() {
		return date_echeance;
	}



	public void setDate_echeance(Date date_echeance) {
		this.date_echeance = date_echeance;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "BankOrder [fournisseur=" + ", facture="
				 + ", montant=" + montant + ", numero_cheque="
				+ numero_cheque + ", numero_depart=" + numero_depart
				+ ", date_singnatur=" + date_singnatur + ", date_echeance="
				+ date_echeance + "]";
	}

	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		// TODO Auto-generated method stub
		return BankOrder.class;
	}
	

   
}
