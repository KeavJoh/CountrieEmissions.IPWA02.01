package countryEmissions.fallstudie.controller;

import java.io.Serializable;

import countryEmissions.fallstudie.dao.RegistrationDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class RegistrationController implements Serializable {

	@Inject
	private RegistrationDao registrationDao;
	
	public RegistrationController() {	
	}
	
	public String getFirstNameString() {
		return registrationDao.getFrstNameString();
	}
	
	public String getLastNameString() {
		return registrationDao.getLastNameString();
	}
	
	public String getEmailString( ) {
		return registrationDao.getEmailString();
	}
	
	public String getPasswordString() {
		return registrationDao.getPasswordString();
	}
	
	public String getOrganizationCodeString() {
		return registrationDao.getOrganizationCodeString();
	}
	
	public void setFirstNameString(String firstNameString) {
		registrationDao.setFrstNameString(firstNameString);
	} 
	
	public void setLastNameString(String lastNameString) {
		registrationDao.setLastNameString(lastNameString);
	}
	
	public void setEmailString(String emailString) {
		registrationDao.setEmailString(emailString);
	}
	
	public void setPasswordString(String passwordString) {
		registrationDao.setPasswordString(passwordString);
	}
	
	public void setOrganizationCodeString(String organizationCodeString) {
		registrationDao.setOrganizationCodeString(organizationCodeString);
	}
	
	public void userRegistration() {
		registrationDao.userRegistration();
	}
}
