package org.currency.starter.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.currency.starter.utils.AuthorityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS")
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;
    
    @JsonIgnore
    private String oldpassword;
    
    @JsonIgnore
    private String newpassword;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    @JsonIgnore
    private Date lastPasswordResetDate;
    
    @Column(name = "LASTLOGIN")
    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private Date LastLogin;
    
    
    private String phone;
    private String state;
    private String city;
    private String address;
    private String website;
    
	
	
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;
    
    public User() {}
    
    public User(Long id, @NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, @NotNull Date lastPasswordResetDate, List<Authority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}


    public Date getLastLogin() {
		return LastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}
	
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtil.createGrantedAuthorities(authorities);
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}