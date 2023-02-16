package countryEmissions.fallstudie.dao;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.primefaces.PrimeFaces;

import com.lambdaworks.crypto.SCryptUtil;

import countryEmissions.fallstudie.controller.LoginController;
import countryEmissions.fallstudie.entitys.User;
import countryEmissions.fallstudie.entitys.User.UserType;
import countryEmissions.fallstudie.provider.EntityManagerFactoryProvider;
import countryEmissions.fallstudie.validators.EmailValidator;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginDao implements Serializable {

	@Inject
	private EntityManagerFactoryProvider emfProvider;
	
	@Inject
	private LoginController loginController;
	
	@Inject
	private EmailValidator emailValidator;

	private String email;

	private String password;
	
	User user;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDao() {
	}

	private boolean checkIfEmailExist() {
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();

		TypedQuery<User> query = eManager.createQuery("Select u FROM User u WHERE u.emailString = :email", User.class);
		query.setParameter("email", email.toLowerCase());
		try {
			user = query.getSingleResult();
			eManager.close();
			return true;
		} catch (NoResultException e) {
			eManager.close();
			errorEmailNotExist();
			return false;
		}

	}
	
	private void checkIfPasswordCorrect() {
		if (SCryptUtil.check(password, user.getPasswordString())) {
			forwardingAfterSuccesfullLogin();
		} else {
			System.out.println("falsch");
			errorPasswordFailer();
		}
	}
	
	private void forwardingAfterSuccesfullLogin() {
		try {
			if (user.getType().equals(UserType.admin)) {
				loginController.setMasterUser(true);
			} else {
				loginController.setMasterUser(false);
			}
			loginController.setLoggedUser(true);
			msgSuccesfullLogin();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void errorEmailNotExist() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es ist ein Fehler aufgetreten",
				"Account mit der angegebenenen Email nicht vorhanden");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_login:msg_login");
	}
	
	private void errorPasswordFailer() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es ist ein Fehler aufgetreten",
				"Passwort falsch");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_login:msg_login");
	}
	
	private void msgSuccesfullLogin() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Wilkommen" + " " + user.getFirstNameString() + " " + user.getLastNameString(),
				"Login erfolgreich");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_index:msg_index");
	}

	public void userLogin() {
		if (emailValidator.emailBooleanValidation(email)) {
			if (checkIfEmailExist()) {
				checkIfPasswordCorrect();
			}else {
				return;
			}
		} else {
			return;
		}
	}
}
