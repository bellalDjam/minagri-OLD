/**
 * 
 */
package dz.minagri.stat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author bellal djamel
 *
 */
@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "referenceId", nullable = false,unique = true)
	@Size(min = 2, message = " must have at least two characters")
	private String referenceId;
	
	@Column(name = "username", nullable = false,unique = true)
	private String username;
	
	@NotNull
	private String createdBy;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	private String updatedBy;
	
	@Column(name = "updatedAt")
	private Date updatedAt;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	@NotNull
	private String password;
	
	@NotNull
	private boolean enabled =true;
	
	@NotNull
	private boolean credentialExpired =false;
	
	@NotNull
	private boolean expired =false;
	
	@NotNull
	private boolean locked =false;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="accountrole",joinColumns=@JoinColumn
	(name="accountId",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="roleId",referencedColumnName="id"))
	private List<Role> roles = new ArrayList<>();
	
	
	/**
	 * 
	 */
	public Account() {
		super();
	}
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the credentialExpired
	 */
	public boolean isCredentialExpired() {
		return credentialExpired;
	}
	/**
	 * @param credentialExpired the credentialExpired to set
	 */
	public void setCredentialExpired(boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}
	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}
	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}
	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", credentialExpired=" + credentialExpired + ", expired=" + expired + ", locked=" + locked + "]";
	}
	
	
	
}
