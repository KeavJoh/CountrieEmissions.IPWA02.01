package countryEmissions.fallstudie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import countryEmissions.fallstudie.entitys.Countrie;
import countryEmissions.fallstudie.entitys.UnconfirmCountrie;
import countryEmissions.fallstudie.provider.EntityManagerFactoryProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CountrieDao implements Serializable {

	@Inject
	private EntityManagerFactoryProvider emfProvider;

	private List<Countrie> countrieListDao;

	public CountrieDao() {
	}

	public List<Countrie> getListOfCountries() {
		if (countrieListDao == null) {
			loadCountries();
		}
		return countrieListDao;
	}

	private void loadCountries() {
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();
		try {
			Query query = eManager.createQuery("Select a from Countrie a");
			countrieListDao = query.getResultList();
		} finally {
			eManager.close();
		}
	}

	public void onRowEdit(RowEditEvent<Countrie> countrie) {

		FacesMessage msg = new FacesMessage("Änderungen zur Freigabe gespeichert",
				String.valueOf(countrie.getObject().getCountrieNameString()));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		Countrie currentCountrie = countrie.getObject();
		UnconfirmCountrie newUnconfirmCountrie = new UnconfirmCountrie();

		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();

		try {
			newUnconfirmCountrie.setCountrieNameString(currentCountrie.getCountrieNameString());
			newUnconfirmCountrie.setCountrieCodeString(currentCountrie.getCountrieCodeString());
			newUnconfirmCountrie.setImpactYear2015(currentCountrie.getImpactYear2015());
			newUnconfirmCountrie.setImpactYear2016(currentCountrie.getImpactYear2016());
			newUnconfirmCountrie.setImpactYear2017(currentCountrie.getImpactYear2017());
			newUnconfirmCountrie.setImpactYear2018(currentCountrie.getImpactYear2018());
			newUnconfirmCountrie.setImpactYear2019(currentCountrie.getImpactYear2019());
			newUnconfirmCountrie.setImpactYear2020(currentCountrie.getImpactYear2020());
			newUnconfirmCountrie.setImpactYear2021(currentCountrie.getImpactYear2021());
			newUnconfirmCountrie.setImpactYear2022(currentCountrie.getImpactYear2022());
			newUnconfirmCountrie.setCountrie(currentCountrie);

			transaction.begin();
			eManager.persist(newUnconfirmCountrie);
			transaction.commit();
			countrieListDao = null;
			PrimeFaces.current().ajax().update("form_edit:countrieTable");

		} finally {
			eManager.close();
		}

	}

	public void onRowCancel(RowEditEvent<Countrie> countrie) {
		FacesMessage msg = new FacesMessage("Änderungen verworfen",
				String.valueOf(countrie.getObject().getCountrieNameString()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("form_edit:countrieTable");
	}
}
