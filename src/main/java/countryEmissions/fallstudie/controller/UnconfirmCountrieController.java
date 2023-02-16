package countryEmissions.fallstudie.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import countryEmissions.fallstudie.dao.UnconfirmCountrieDao;
import countryEmissions.fallstudie.entitys.UnconfirmCountrie;
import countryEmissions.fallstudie.servieces.UnconfirmCountrieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UnconfirmCountrieController implements Serializable {
	
	@Inject
	private UnconfirmCountrieService unconfirmCountrieService;
	@Inject
	private UnconfirmCountrieDao unconfirmCountrieDao;
	
	public List<UnconfirmCountrie> getListOfUnconfirmCountries() {
		return unconfirmCountrieDao.getListOfUnconfirmCountries();
	}
	
	public boolean getGlobalFilterFunction(Object value, Object filter, Locale locale) {
		return unconfirmCountrieService.globalFilterFunction(value, filter, locale);
	}
	
	public boolean valueOfColumn2015() {
		return unconfirmCountrieService.isColumn2015();
	}
	
	public boolean valueOfColumn2016() {
		return unconfirmCountrieService.isColumn2016();
	}
	
	public boolean valueOfColumn2017() {
		return unconfirmCountrieService.isColumn2017();
	}
	
	public boolean valueOfColumn2018() {
		return unconfirmCountrieService.isColumn2018();
	}
	
	public boolean valueOfColumn2019() {
		return unconfirmCountrieService.isColumn2019();
	}
	
	public boolean valueOfColumn2020() {
		return unconfirmCountrieService.isColumn2020();
	}
	
	public boolean valueOfColumn2021() {
		return unconfirmCountrieService.isColumn2021();
	}
	
	public boolean valueOfColumn2022() {
		return unconfirmCountrieService.isColumn2022();
	}
	
	public void confirmChange(UnconfirmCountrie countrieColumn) {
		unconfirmCountrieDao.confirmChange(countrieColumn);
	}
	
	public void confirmCancel(UnconfirmCountrie countrieColumn) {
		unconfirmCountrieDao.confirmCancel(countrieColumn);
	}
}
