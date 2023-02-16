package countryEmissions.fallstudie.validators;

import java.util.Map;
import java.util.regex.Pattern;

import org.primefaces.PrimeFaces;
import org.primefaces.validate.ClientValidator;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EmailValidator implements Validator, ClientValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return null;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	}

	private void msgErrorValidateEmail() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es ist ein Fehler aufgetreten",
				"Bitte geben Sie eine gültige Emailadresse ein");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_error_validation_msg:msg_errorValidate");
	}

	public boolean emailBooleanValidation(String email) {
		if (pattern.matcher(email).matches()) {
			return true;
		} else {
			msgErrorValidateEmail();
			return false;
		}
	}
}
