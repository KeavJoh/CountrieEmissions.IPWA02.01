package countryEmissions.fallstudie.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@Entity
@SessionScoped
@Table(name = "all_user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email", nullable = false)
	private String emailString;

	@Column(name = "password", nullable = false)
	private String passwordString;

	@Column(name = "firstName", nullable = false)
	private String firstNameString;

	@Column(name = "lastName", nullable = false)
	private String lastNameString;

	@Column(name = "salt")
	@Lob
	private byte[] saltCode;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;

	@ManyToOne
	@JoinColumn(name = "organizationId")
	private Organization organization;

	public User() {
	}

	public User(String emailString, String passwordString, String firstNameString, String lastNameString,
			byte[] saltCode, UserType type, Organization organization) {
		super();
		this.emailString = emailString;
		this.passwordString = passwordString;
		this.firstNameString = firstNameString;
		this.lastNameString = lastNameString;
		this.saltCode = saltCode;
		this.type = type;
		this.organization = organization;
	}

	public enum UserType {
		normal, admin
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public String getFirstNameString() {
		return firstNameString;
	}

	public void setFirstNameString(String firstNameString) {
		this.firstNameString = firstNameString;
	}

	public String getLastNameString() {
		return lastNameString;
	}

	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
	}

	public byte[] getSaltCode() {
		return saltCode;
	}

	public void setSaltCode(byte[] saltCode) {
		this.saltCode = saltCode;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
