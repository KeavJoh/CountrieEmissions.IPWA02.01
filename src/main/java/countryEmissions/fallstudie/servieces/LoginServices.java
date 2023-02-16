package countryEmissions.fallstudie.servieces;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginServices implements Serializable {

	private boolean masterUser = false;
	
	private boolean loggedUser = false;
	
	public LoginServices() {
	}

	public boolean isMasterUser() {
		return masterUser;
	}

	public void setMasterUser(boolean masterUser) {
		this.masterUser = masterUser;
	}

	public boolean isLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(boolean loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
