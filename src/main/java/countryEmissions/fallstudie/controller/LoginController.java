package countryEmissions.fallstudie.controller;

import java.io.Serializable;

import countryEmissions.fallstudie.dao.LoginDao;
import countryEmissions.fallstudie.servieces.LoginServices;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class LoginController implements Serializable {
	
	@Inject 
	LoginDao loginDao;
	
	@Inject 
	LoginServices loginServices;
	
	public LoginController() {
	}
	
	public String getEmail() {
		return loginDao.getEmail();
	}
	
	public void setEmail(String email) {
		loginDao.setEmail(email);
	}
	
	public String getPassword() {
		return loginDao.getPassword();
	}
	
	public void setPassword(String password) {
		loginDao.setPassword(password);
	}
	
	public boolean isLoggedUser() {
		return loginServices.isLoggedUser();
	}
	
	public boolean isMasterUser() {
		return loginServices.isMasterUser();
	}
	
	public void setLoggedUser(boolean loggedUser) {
		loginServices.setLoggedUser(loggedUser);
	}
	
	public void setMasterUser(boolean masterUser) {
		loginServices.setMasterUser(masterUser);
	}
	
	public void userLogin() {
		loginDao.userLogin();
	}
	
	public void test() {
		System.out.println(loginServices.isLoggedUser());
	}
	
}
