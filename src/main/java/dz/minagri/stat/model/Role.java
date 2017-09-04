/**
 * 
 */
package dz.minagri.stat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author bellal djamel
 *
 */
@Entity
public class Role {
	@Id
	private Long id;
	@Column(name = "code", nullable = false,unique = true)
	private String code;
	
	@NotNull
	private String label;
	
	@Column(name = "ordinal", nullable = false)
	private Integer ordinal;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	@Column(name = "expiresAt")
	private Date expiresAt;
	
	@Column(name = "effectiveAt")
	private Date effectiveAt;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the ordinal
	 */
	public Integer getOrdinal() {
		return ordinal;
	}
	/**
	 * @param ordinal the ordinal to set
	 */
	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the expiresAt
	 */
	public Date getExpiresAt() {
		return expiresAt;
	}
	/**
	 * @param expiresAt the expiresAt to set
	 */
	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
	/**
	 * @return the effectiveAt
	 */
	public Date getEffectiveAt() {
		return effectiveAt;
	}
	/**
	 * @param effectiveAt the effectiveAt to set
	 */
	public void setEffectiveAt(Date effectiveAt) {
		this.effectiveAt = effectiveAt;
	}

	
}
