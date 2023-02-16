package countryEmissions.fallstudie.controller;

import java.io.IOException;
import java.io.Serializable;

import org.primefaces.PrimeFaces;

import countryEmissions.fallstudie.servieces.LoginServices;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class LogoutController implements Serializable {

	@Inject
	private LoginServices loginServices;
	
	public LogoutController() {
	}
	
	public void userLogout() {
		setMasterUserForLogout();
		setLoggedUserForLogout();
		msgSuccesfullLogout();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("logout.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setMasterUserForLogout() {
		loginServices.setMasterUser(false);
	}
	
	private void setLoggedUserForLogout() {
		loginServices.setLoggedUser(false);
	}
	
	private void msgSuccesfullLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout erfolgreich",
				"Bis zum nächsten mal");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("msg_logout");
	}
	
}
