package countryEmissions.fallstudie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.PrimeFaces;

import countryEmissions.fallstudie.entitys.Countrie;
import countryEmissions.fallstudie.entitys.UnconfirmCountrie;
import countryEmissions.fallstudie.provider.EntityManagerFactoryProvider;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UnconfirmCountrieDao {

	@Inject
	private EntityManagerFactoryProvider emfProvider;
	
	private List<UnconfirmCountrie> unconfirmCountrieListDao;
	
	public UnconfirmCountrieDao() {
	}
	
	public List<UnconfirmCountrie> getListOfUnconfirmCountries() {
		if (unconfirmCountrieListDao == null) {
			loadUnconfirmCountries();
		}
		return unconfirmCountrieListDao;
	}
	
	private void loadUnconfirmCountries() {
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();
		try {
			Query query = eManager.createQuery("Select a from UnconfirmCountrie a");
			unconfirmCountrieListDao = query.getResultList();
		} finally {
			eManager.close();
		}
	}
	
	public void confirmChange(UnconfirmCountrie unconfirmCountrieColumn) {
		
		System.out.println(unconfirmCountrieColumn.getCountrieNameString() + unconfirmCountrieColumn.getImpactYear2019());
		
		FacesMessage msg = new FacesMessage("Änderungen erfolgreich übernommen", String.valueOf(unconfirmCountrieColumn.getCountrieNameString()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();
		
		try {
			Countrie exisCountrie = eManager.find(Countrie.class, unconfirmCountrieColumn.getCountrie().getId());
			
			exisCountrie.setCountrieNameString(unconfirmCountrieColumn.getCountrieNameString());
			exisCountrie.setCountrieCodeString(unconfirmCountrieColumn.getCountrieCodeString());
			exisCountrie.setImpactYear2015(unconfirmCountrieColumn.getImpactYear2015());
			exisCountrie.setImpactYear2016(unconfirmCountrieColumn.getImpactYear2016());
			exisCountrie.setImpactYear2017(unconfirmCountrieColumn.getImpactYear2017());
			exisCountrie.setImpactYear2018(unconfirmCountrieColumn.getImpactYear2018());
			exisCountrie.setImpactYear2019(unconfirmCountrieColumn.getImpactYear2019());
			exisCountrie.setImpactYear2020(unconfirmCountrieColumn.getImpactYear2020());
			exisCountrie.setImpactYear2021(unconfirmCountrieColumn.getImpactYear2021());
			exisCountrie.setImpactYear2022(unconfirmCountrieColumn.getImpactYear2022());
			
			eManager.getTransaction().begin();
			eManager.merge(exisCountrie);
			eManager.getTransaction().commit();
			
			transaction.begin();
			unconfirmCountrieColumn = eManager.merge(unconfirmCountrieColumn);
			eManager.remove(unconfirmCountrieColumn);
			transaction.commit();
			
			unconfirmCountrieListDao = null;
			PrimeFaces.current().ajax().update("form_unconfirm:unconfirmCountrieTable", "form_unconfirm:msg_unconfirm");
		} finally {
			eManager.close();
		}
	}
	
	public void confirmCancel(UnconfirmCountrie unconfirmCountrieColumn) {
		
		FacesMessage msg = new FacesMessage("Änderungen verworfen", String.valueOf(unconfirmCountrieColumn.getCountrieNameString()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		EntityManager eManager = emfProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();
		
		try {
			eManager.getTransaction().begin();
			unconfirmCountrieColumn = eManager.merge(unconfirmCountrieColumn);
			eManager.remove(unconfirmCountrieColumn);
			eManager.getTransaction().commit();
			unconfirmCountrieListDao = null;
			PrimeFaces.current().ajax().update("form_unconfirm:unconfirmCountrieTable", "form_unconfirm:msg_unconfirm");
		} finally {
			eManager.close();
		}
	}

}
