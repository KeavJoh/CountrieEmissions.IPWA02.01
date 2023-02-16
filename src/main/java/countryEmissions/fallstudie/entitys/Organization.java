package countryEmissions.fallstudie.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String organizationNameString;

	@Column(name = "code", nullable = false)
	private String organizationCodeString;

	@OneToMany(mappedBy = "organization")
	private List<User> userList;

	public Organization() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationNameString() {
		return organizationNameString;
	}

	public void setOrganizationNameString(String organizationNameString) {
		this.organizationNameString = organizationNameString;
	}

	public String getOrganizationCodeString() {
		return organizationCodeString;
	}

	public void setOrganizationCodeString(String organizationCodeString) {
		this.organizationCodeString = organizationCodeString;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
