package countryEmissions.fallstudie.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.primefaces.event.RowEditEvent;

import countryEmissions.fallstudie.dao.CountrieDao;
import countryEmissions.fallstudie.entitys.Countrie;
import countryEmissions.fallstudie.servieces.CountrieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CountrieController implements Serializable {

	@Inject
	private CountrieDao countrieDao;

	@Inject
	private CountrieService countrieService;
	

	public CountrieController() {
	}

	public List<Countrie> getCountrieList() {
		return countrieDao.getListOfCountries();
	}

	public boolean getGlobalFilterFunction(Object value, Object filter, Locale locale) {
		return countrieService.globalFilterFunction(value, filter, locale);
	}
	
	public boolean valueOfColumn2015() {
		return countrieService.getColumn2015();
	}
	
	public boolean valueOfColumn2016() {
		return countrieService.getColumn2016();
	}
	
	public boolean valueOfColumn2017() {
		return countrieService.getColumn2017();
	}
	
	public boolean valueOfColumn2018() {
		return countrieService.getColumn2018();
	}
	
	public boolean valueOfColumn2019() {
		return countrieService.getColumn2019();
	}
	
	public boolean valueOfColumn2020() {
		return countrieService.getColumn2020();
	}
	
	public boolean valueOfColumn2021() {
		return countrieService.getColumn2021();
	}
	
	public boolean valueOfColumn2022() {
		return countrieService.getColumn2022();
	}
	
	public void onRowEdit(RowEditEvent<Countrie> countrie) {
		countrieDao.onRowEdit(countrie);
	}
	
	public void onRowCancel(RowEditEvent<Countrie> countrie) {
		countrieDao.onRowCancel(countrie);
	}

}
