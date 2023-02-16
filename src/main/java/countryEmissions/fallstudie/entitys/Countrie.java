package countryEmissions.fallstudie.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Entity
@Table(name = "countries_new")
@Named
public class Countrie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "Countrie_Name")
	private String countrieNameString;

	@Column(name = "Countrie_Code")
	private String countrieCodeString;

	@Column(name = "Year_2015")
	private BigDecimal impactYear2015;

	@Column(name = "Year_2016")
	private BigDecimal impactYear2016;

	@Column(name = "Year_2017")
	private BigDecimal impactYear2017;

	@Column(name = "Year_2018")
	private BigDecimal impactYear2018;

	@Column(name = "Year_2019")
	private BigDecimal impactYear2019;

	@Column(name = "Year_2020")
	private BigDecimal impactYear2020;

	@Column(name = "Year_2021")
	private BigDecimal impactYear2021;

	@Column(name = "Year_2022")
	private BigDecimal impactYear2022;
	
	@OneToMany
	@JoinColumn(name="id")
	List<UnconfirmCountrie> unconfirmCountries;

	public Countrie() {
	}

	public Countrie(String countrieNameString, String countrieCodeString, BigDecimal impactYear2015,
			BigDecimal impactYear2016, BigDecimal impactYear2017, BigDecimal impactYear2018, BigDecimal impactYear2019,
			BigDecimal impactYear2020, BigDecimal impactYear2021, BigDecimal impactYear2022) {
		super();
		this.countrieNameString = countrieNameString;
		this.countrieCodeString = countrieCodeString;
		this.impactYear2015 = impactYear2015;
		this.impactYear2016 = impactYear2016;
		this.impactYear2017 = impactYear2017;
		this.impactYear2018 = impactYear2018;
		this.impactYear2019 = impactYear2019;
		this.impactYear2020 = impactYear2020;
		this.impactYear2021 = impactYear2021;
		this.impactYear2022 = impactYear2022;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountrieNameString() {
		return countrieNameString;
	}

	public void setCountrieNameString(String countrieNameString) {
		this.countrieNameString = countrieNameString;
	}

	public String getCountrieCodeString() {
		return countrieCodeString;
	}

	public void setCountrieCodeString(String countrieCodeString) {
		this.countrieCodeString = countrieCodeString;
	}

	public BigDecimal getImpactYear2015() {
		return impactYear2015;
	}

	public void setImpactYear2015(BigDecimal impactYear2015) {
		this.impactYear2015 = impactYear2015;
	}

	public BigDecimal getImpactYear2016() {
		return impactYear2016;
	}

	public void setImpactYear2016(BigDecimal impactYear2016) {
		this.impactYear2016 = impactYear2016;
	}

	public BigDecimal getImpactYear2017() {
		return impactYear2017;
	}

	public void setImpactYear2017(BigDecimal impactYear2017) {
		this.impactYear2017 = impactYear2017;
	}

	public BigDecimal getImpactYear2018() {
		return impactYear2018;
	}

	public void setImpactYear2018(BigDecimal impactYear2018) {
		this.impactYear2018 = impactYear2018;
	}

	public BigDecimal getImpactYear2019() {
		return impactYear2019;
	}

	public void setImpactYear2019(BigDecimal impactYear2019) {
		this.impactYear2019 = impactYear2019;
	}

	public BigDecimal getImpactYear2020() {
		return impactYear2020;
	}

	public void setImpactYear2020(BigDecimal impactYear2020) {
		this.impactYear2020 = impactYear2020;
	}

	public BigDecimal getImpactYear2021() {
		return impactYear2021;
	}

	public void setImpactYear2021(BigDecimal impactYear2021) {
		this.impactYear2021 = impactYear2021;
	}

	public BigDecimal getImpactYear2022() {
		return impactYear2022;
	}

	public void setImpactYear2022(BigDecimal impactYear2022) {
		this.impactYear2022 = impactYear2022;
	}

}
