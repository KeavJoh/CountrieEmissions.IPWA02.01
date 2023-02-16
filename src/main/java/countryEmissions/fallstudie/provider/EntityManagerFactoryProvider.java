package countryEmissions.fallstudie.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class EntityManagerFactoryProvider {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CountrieImpact");
	
	public EntityManagerFactory getEntityManagerFactory() {
		System.out.println("emf wurde aufgerufen");
		return emf;
	}
	
}
