package countryEmissions.fallstudie.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.PropertyValueException;
import org.primefaces.PrimeFaces;

import com.lambdaworks.crypto.SCryptUtil;

import countryEmissions.fallstudie.entitys.Organization;
import countryEmissions.fallstudie.entitys.User;
import countryEmissions.fallstudie.provider.EntityManagerFactoryProvider;
import countryEmissions.fallstudie.validators.EmailValidator;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegistrationDao {

	@Inject
	private EntityManagerFactoryProvider emfProvider;
	
	@Inject
	private EmailValidator emailValidator;

	private String firstNameString;

	private String lastNameString;

	private String emailString;

	private String passwordString;

	private String organizationCodeString;

	Organization currentOrganization;

	public RegistrationDao() {
	}

	public String getFrstNameString() {
		return firstNameString;
	}

	public void setFrstNameString(String frstNameString) {
		this.firstNameString = frstNameString;
	}

	public String getLastNameString() {
		return lastNameString;
	}

	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
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

	public String getOrganizationCodeString() {
		return organizationCodeString;
	}

	public void setOrganizationCodeString(String organizationCodeString) {
		this.organizationCodeString = organizationCodeString;
	}

	private boolean checkForDuplicateEmail() {
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();

		TypedQuery<User> queryUser = eManager.createQuery("Select u FROM User u WHERE u.emailString = :email",
				User.class);
		queryUser.setParameter("email", emailString.toLowerCase());
		try {
			User usersExists = queryUser.getSingleResult();
			errorEmailFailed();
			eManager.close();
			return false;
		} catch (NoResultException e) {
			eManager.close();
			return true;
		}
	}

	private boolean checkForOrganizationCodeExists() {
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();

		TypedQuery<Organization> query = eManager
				.createQuery("SELECT o FROM Organization o WHERE o.organizationCodeString = :code", Organization.class);
		query.setParameter("code", organizationCodeString);
		List<Organization> organizationsList = query.getResultList();

		if (!organizationsList.isEmpty()) {
			for (Organization organizationFromList : organizationsList) {
				if (organizationFromList.getOrganizationCodeString().equals(organizationCodeString)) {
					currentOrganization = organizationFromList;
					eManager.close();
					return true;
				}
			}
		}

		errorOrganizationFailed();
		eManager.close();
		return false;
	}
	
	private void updateNewUserOfRegistration() {
		EntityManager eManager = null;
		try {
			eManager = emfProvider.getEntityManagerFactory().createEntityManager();
			EntityTransaction transaction = eManager.getTransaction();
			transaction.begin();
			createUser(eManager);
			transaction.commit();
			resetRegistrationData();
			msgSuccesfullRegistration();
			forwardingAfterSuccesfullRegistration();
		} catch (Exception e) {
			handleException(e);
		} finally {
			if (eManager != null) {
				eManager.close();
			}
		}
	}
	
	private void createUser(EntityManager eManager) {
		byte[] salt = new byte[16];
		try {
			SecureRandom.getInstanceStrong().nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException");
			errorDefault();
		}
		String hashesPassword = SCryptUtil.scrypt(passwordString, 32768, 7, 2);

		User user = new User();
		user.setFirstNameString(firstNameString);
		user.setLastNameString(lastNameString);
		user.setEmailString(emailString);
		user.setPasswordString(hashesPassword);
		user.setOrganization(currentOrganization);
		user.setType(countryEmissions.fallstudie.entitys.User.UserType.normal);
		user.setSaltCode(salt);
		eManager.persist(user);
	}
	
	private void handleException(Exception e) {
		if (e instanceof PropertyValueException) {
			System.out.println("PropertyValueException");
			errorDefault();
		} else if (e instanceof NoSuchAlgorithmException) {
			System.out.println("NoSuchAlgorithmException");
			errorDefault();
		} else if (e instanceof NoResultException) {
			System.out.println("NoResultException");
			errorOrganizationFailed();
		}
	}
	
	private void resetRegistrationData() {
		this.firstNameString = null;
		this.lastNameString = null;
		this.emailString = null;
		this.organizationCodeString = null;
		this.passwordString = null;
		currentOrganization = null;
	}
	
	private void forwardingAfterSuccesfullRegistration() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void errorDefault() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es ist ein Fehler aufgetreten",
				"Bitte versuchen Sie es später noch einmal");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_registration:msg_registration");
	}
	
	private void errorOrganizationFailed() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler bei der Registrierung",
				"Organisation nicht vorhanden");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_registration", "form_registration:msg_registration");
	}
	
	private void errorEmailFailed() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler bei der Registrierung",
				"Für die genannte Email exisitert bereits ein Account");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_registration:msg_registration");
	}
	
	private void msgSuccesfullRegistration() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrierung erfolgreich",
				"Bitte Loggen Sie sich erneut ein");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_index:msg_index");
	}

	public void userRegistration() {
		if (emailValidator.emailBooleanValidation(emailString)) {
			if (checkForDuplicateEmail()) {
				if (checkForOrganizationCodeExists()) {
					updateNewUserOfRegistration();
				} else {
					return;
				}
			} else {
				return;
			}
		}else {
			return;
		}
	}
}
