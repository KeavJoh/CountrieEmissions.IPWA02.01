package countryEmissions.fallstudie.servieces;

import java.util.Locale;

import org.primefaces.util.LangUtils;

import countryEmissions.fallstudie.entitys.UnconfirmCountrie;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UnconfirmCountrieService {
	
	private boolean Column2015 = false;
	private boolean Column2016 = false;
	private boolean Column2017 = false;
	private boolean Column2018 = true;
	private boolean Column2019 = true;
	private boolean Column2020 = true;
	private boolean Column2021 = true;
	private boolean Column2022 = false;
	
	public UnconfirmCountrieService() {
	}
	
	

	public boolean isColumn2015() {
		return Column2015;
	}



	public void setColumn2015(boolean column2015) {
		Column2015 = column2015;
	}



	public boolean isColumn2016() {
		return Column2016;
	}



	public void setColumn2016(boolean column2016) {
		Column2016 = column2016;
	}



	public boolean isColumn2017() {
		return Column2017;
	}



	public void setColumn2017(boolean column2017) {
		Column2017 = column2017;
	}



	public boolean isColumn2018() {
		return Column2018;
	}



	public void setColumn2018(boolean column2018) {
		Column2018 = column2018;
	}



	public boolean isColumn2019() {
		return Column2019;
	}



	public void setColumn2019(boolean column2019) {
		Column2019 = column2019;
	}



	public boolean isColumn2020() {
		return Column2020;
	}



	public void setColumn2020(boolean column2020) {
		Column2020 = column2020;
	}



	public boolean isColumn2021() {
		return Column2021;
	}



	public void setColumn2021(boolean column2021) {
		Column2021 = column2021;
	}



	public boolean isColumn2022() {
		return Column2022;
	}



	public void setColumn2022(boolean column2022) {
		Column2022 = column2022;
	}



	private int getInteger(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}
		int filterInt = getInteger(filterText);

		UnconfirmCountrie customer = (UnconfirmCountrie) value;
		return customer.getCountrieNameString().toLowerCase().contains(filterText)
				|| customer.getCountrieCodeString().toLowerCase().contains(filterText);

	}

}
