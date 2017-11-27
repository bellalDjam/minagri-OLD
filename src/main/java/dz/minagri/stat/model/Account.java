/**
 * 
 */
package dz.minagri.stat.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dz.minagri.stat.enumeration.ContributorCredibility;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.util.Identifiable;


/**
 * @author bellal djamel
 *
 */
@Entity
@Table(name="account")
public class Account extends Identifiable implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 514961507487071910L;

	@Id
	@GeneratedValue
	private Long id;
	@Autowired

	private String firstName;

	private String lastName;

//	@Column(name = "referenceId", nullable = false,unique = true)
//	@Size(min = 2, message = " must have at least two characters")
//	private String referenceId;

	@Column(name = "username", nullable = false,unique = true)
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String createdBy;

	@NotNull
	@Column(name = "createdAt",columnDefinition = "DATE")
	private LocalDate createdAt;

	private String updatedBy;

	@Column(name = "updatedAt",columnDefinition = "DATE")
	private LocalDate updatedAt;
	@Column(name = "birthdate",columnDefinition = "DATE")
	private LocalDate birthDate;
	@Column(name = "lastMailSentDate",columnDefinition = "DATE")
	private LocalDate lastMailSentDate;
	@Column(name = "lastAccess",columnDefinition = "DATE")
	private LocalDate lastAccess;
	@Column(name = "registrationDate",columnDefinition = "DATE")
	private LocalDate registrationDate;
	@Column(name = "lastFailedLoginDate",columnDefinition = "DATE")
	private LocalDate lastFailedLoginDate;


	private String lastLoginIp;
	private String lockReason;
	@Version
	@Column(name = "version")
	private Integer version;

	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="manager_id")
	private Account manager;

	@OneToMany(mappedBy="manager")
	private List<Account> directs= new ArrayList<Account>();

	@Column(columnDefinition = "text")
	private String shortInfo;

	@NotNull
	private boolean enabled =true;

	@NotNull
	private boolean credentialNonExpired =true;

	@NotNull
	private boolean nonExpired =true;

	@NotNull
	private boolean nonLocked =true;

	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="accountrole",joinColumns= {@JoinColumn
	(name="account_Id",referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="role_Id",referencedColumnName="id")})
	@Fetch(FetchMode.SELECT)
	private List<Role> roles = new ArrayList<>();

	@Embedded
	private MoyenContact moyenContact; 
	
	@Enumerated(EnumType.STRING)
	private TypeAccount typeAccount;
	
	@Enumerated(EnumType.STRING)
	private ContributorCredibility contributorCredibility;
//	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Account() {
		super();
	}

	
	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param createdBy
	 * @param createdAt
	 * @param enabled
	 * @param credentialExpired
	 * @param expired
	 * @param locked
	 * @param roles
	 * @param typeAccount
	 */
	public Account(String firstName, String lastName, String username, String password, String createdBy,
			LocalDate createdAt, boolean enabled, boolean credentialExpired, boolean expired, boolean locked,
			List<Role> roles, TypeAccount typeAccount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.enabled = enabled;
		this.credentialNonExpired = credentialExpired;
		this.nonExpired = expired;
		this.nonLocked = locked;
		this.roles = roles;
		this.typeAccount = typeAccount;
	}



	/**
	 * @param username2
	 * @param encode
	 * @param firstName2
	 * @param lastName2
	 * @param shortInfo2
	 * @param email
	 * @param phoneNumber
	 * @param createdBy2
	 * @param birthdate2
	 * @param createdAt2
	 * @param registrationDate2
	 * @param b
	 * @param typeAccount2
	 * @param gender2
	 */
	
	public Account(String username, String firstName,String lastName
			,String shortInfo,LocalDate birthdate
			,LocalDate registrationDate,TypeAccount typeAccount
			,Gender gender,List<Role> roles,MoyenContact moyenContact) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.moyenContact =moyenContact;
//		this.password = password;
//		this.createdBy = createdBy;
//		this.createdAt = createdAt;
//		this.enabled = enabled;
		this.typeAccount = typeAccount;
		this.roles=roles;
		this.gender = gender;
		this.shortInfo = shortInfo;
		this.birthDate = birthdate;
		this.registrationDate = registrationDate;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//
//	/**
//	 * @return the referenceId
//	 */
//	public String getReferenceId() {
//		return referenceId;
//	}
//
//
//	/**
//	 * @param referenceId the referenceId to set
//	 */
//	public void setReferenceId(String referenceId) {
//		this.referenceId = referenceId;
//	}
//

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
	public LocalDate getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDate createdAt) {
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
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}


	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}


	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}


	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	/**
	 * @return the lastMailSentDate
	 */
	public LocalDate getLastMailSentDate() {
		return lastMailSentDate;
	}


	/**
	 * @param lastMailSentDate the lastMailSentDate to set
	 */
	public void setLastMailSentDate(LocalDate lastMailSentDate) {
		this.lastMailSentDate = lastMailSentDate;
	}


	/**
	 * @return the lastAccess
	 */
	public LocalDate getLastAccess() {
		return lastAccess;
	}


	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(LocalDate lastAccess) {
		this.lastAccess = lastAccess;
	}


	/**
	 * @return the registrationDate
	 */
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}


	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}


	/**
	 * @return the lastFailedLoginDate
	 */
	public LocalDate getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}


	/**
	 * @param lastFailedLoginDate the lastFailedLoginDate to set
	 */
	public void setLastFailedLoginDate(LocalDate lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}


	/**
	 * @return the lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}


	/**
	 * @param lastLoginIp the lastLoginIp to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}


	/**
	 * @return the lockReason
	 */
	public String getLockReason() {
		return lockReason;
	}


	/**
	 * @param lockReason the lockReason to set
	 */
	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
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


	/**
	 * @return the manager
	 */
	public Account getManager() {
		return manager;
	}


	/**
	 * @param manager the manager to set
	 */
	public void setManager(Account manager) {
		this.manager = manager;
	}


	/**
	 * @return the directs
	 */
	public List<Account> getDirects() {
		return directs;
	}


	/**
	 * @param directs the directs to set
	 */
	public void setDirects(List<Account> directs) {
		this.directs = directs;
	}


	/**
	 * @return the shortInfo
	 */
	public String getShortInfo() {
		return shortInfo;
	}


	/**
	 * @param shortInfo the shortInfo to set
	 */
	public void setShortInfo(String shortInfo) {
		this.shortInfo = shortInfo;
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

	public void addRole(Role role) {
		this.roles.add(role);
	}
	/**
	 * @return the moyenContact
	 */
	public MoyenContact getMoyenContact() {
		return moyenContact;
	}


	/**
	 * @param moyenContact the moyenContact to set
	 */
	public void setMoyenContact(MoyenContact moyenContact) {
		this.moyenContact = moyenContact;
	}


	/**
	 * @return the typeAccount
	 */
	public TypeAccount getTypeAccount() {
		return typeAccount;
	}


	/**
	 * @param typeAccount the typeAccount to set
	 */
	public void setTypeAccount(TypeAccount typeAccount) {
		this.typeAccount = typeAccount;
	}


	/**
	 * @return the contributorCredibility
	 */
	public ContributorCredibility getContributorCredibility() {
		return contributorCredibility;
	}


	/**
	 * @param contributorCredibility the contributorCredibility to set
	 */
	public void setContributorCredibility(ContributorCredibility contributorCredibility) {
		this.contributorCredibility = contributorCredibility;
	}


	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	 @Transient
	    public void setUnencryptedPassword(String password) {
	        setPassword(new BCryptPasswordEncoder().encode(password));
	    }

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	   @Override
	    @ManyToMany(fetch = FetchType.EAGER)
	    public Collection<Role> getAuthorities() {
	        return roles;
	    }

	   @SuppressWarnings("unused")
	private void setAuthorities(List<Role> roles) {
	        this.roles = roles;
	    }

	
	public Boolean authenticate(String username, String password){
		if(username.equals(getUsername()) && password.equals(getPassword())){
			return true;
		}
		return false;
	}
	public void addAuthority(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
	/**
	 * @param password the password to set
	 */
	@Transient
	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassowrd = passwordEncoder.encode(password.trim());
		this.password = hashedPassowrd;
	}
	
	public boolean hasAuthority(String[] requiredRoles) {
        if (getAuthorities().isEmpty() && requiredRoles.length > 0) {
            return false;
        }

        for (String requiredRole : requiredRoles) {
            for (Role role : getAuthorities()) {
                if (role.getAuthority().equals(requiredRole)) {
                    return true;
                }
            }
        }
        return false;
    }
	
//	 @Override
//	    public String toString() {
//	        return MoreObjects.toStringHelper(this)
//	                .add("username", username)
//	                .add("firstName", firstName)
//	                .add("lastName", lastName)
//	                .add("roles", roles)
//	                .toString();
//	    }
	
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String getFullName(){
		return "SecUser [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	/* (non-Javadoc)
	 * @see dz.minagri.stat.util.Identifiable#getConcreteClass()
	 */
	@Override
	public Class<?> getConcreteClass() {
		return Account.class;
	}



	@Override
	public Long getId() {
		return id;
	}


	/**
	 * @return the credentialNonExpired
	 */
	public boolean isCredentialNonExpired() {
		return credentialNonExpired;
	}


	/**
	 * @param credentialNonExpired the credentialNonExpired to set
	 */
	public boolean isCredentialsNonExpired( ) {
		return credentialNonExpired ;
	}


	/**
	 * @return the nonExpired
	 */
	public boolean isAccountNonExpired() {
		return nonExpired;
	}


	/**
	 * @param nonExpired the nonExpired to set
	 */
	public void setNonExpired(boolean nonExpired) {
		this.nonExpired = nonExpired;
	}



	/**
	 * @param nonLocked the nonLocked to set
	 */
	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}


	/**
	 * @return the nonExpired
	 */
	public boolean isNonExpired() {
		return nonExpired;
	}


	/**
	 * @return the nonLocked
	 */
	public boolean isNonLocked() {
		return nonLocked;
	}


	/**
	 * @param credentialNonExpired the credentialNonExpired to set
	 */
	public void setCredentialNonExpired(boolean credentialNonExpired) {
		this.credentialNonExpired = credentialNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.nonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.credentialNonExpired = accountNonLocked;
	}
	

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return nonLocked;
	}


	/**
	 * @return
	 */
	public boolean isPersisted() {
		return id !=null;
	}
}
