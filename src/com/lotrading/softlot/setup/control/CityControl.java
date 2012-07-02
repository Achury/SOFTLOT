package com.lotrading.softlot.setup.control;
import java.util.*;

import org.richfaces.component.html.HtmlExtendedDataTable;

import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.ICityLogic;
import com.lotrading.softlot.util.base.AlphabeticComparator;
import com.lotrading.softlot.util.base.control.BaseControl;
import co.com.landsoft.devbase.util.listas.AdministradorListas;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:52 a.m.
 */
public class CityControl extends BaseControl{

	private City city;
	private City filterCity;
	private List<City> cities;
	private ICityLogic cityLogic;
	private HtmlExtendedDataTable table;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private List countries;
	private List provinces;

	public CityControl(){
		city = new City();
		filterCity = new City();
		cities = new ArrayList<City>();
	}

	public void finalize() throws Throwable {

	}

	public City getCity(){
		return city;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCity(City newVal){
		city = newVal;
	}
	
	public List<City> getCitiesAuto() {
		try {
			setCities(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CityList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the cities.\n"
					+ e.getMessage());
		}
		return cities;
	}
	
	public List<City> getCities(){
	  return cities;
	}

	/**
	 * 
	 * @param newVal
	 */

	public ICityLogic getCityLogic(){
		return cityLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCityLogic(ICityLogic newVal){
		cityLogic = newVal;
	}

	public String searchCitiesAction(){
		try {
			cities = cityLogic.searchCity(filterCity);
			if (cities == null || cities.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "";
	}
	
	public String getSize() {
		if (cities == null) {
			return "Found:0";
		} else {
			System.out.println(cities.size());
			return "Found:" + cities.size();
		}
		
	}
	public java.lang.String selectCityAction(){
		city = (City) table.getRowData();
		return null;
	}
	
	public List getProvinces() {
		try {
			setProvinces(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.StateList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the states.\n"
					+ e.getMessage());
		}
		return provinces;
	}

	public void setProvinces(List provinces) {
		Collections.sort(provinces, new AlphabeticComparator());
		this.provinces = provinces;
	}
	
	public List getCountries() {
		try {
			setCountries(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CountryList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the countries.\n"
					+ e.getMessage());
		}
		return countries;
	}

	public void setCountries(List countries) {
		Collections.sort(countries, new AlphabeticComparator());
		this.countries = countries;
	}
	
	public void newCityAction(){
		try {
			city = new City();
			city.getStateProvince().setValue1(null);
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new City.\n"
					+ e.getMessage());
		}
	}
	public void saveCityAction(){
		try {
			
			if(cityLogic.saveCity(city)){
				setInfoMessage("");
			}else{
				setWarningMessage("Data was not saved");
			}
		}catch (Exception e) {
			setErrorMessage("Error trying to save the City.\n"+e.getMessage());
		}
	}
	
	public void clearCity() {
		city = new City();
	}
	
	public void clearSearchAction() {
		filterCity = new City();
	}
	
	public List<City> autocompleteCity(Object suggest) {
		String pref = (String) suggest;
		ArrayList<City> result = new ArrayList<City>();
		for (City city : getCitiesAuto()) {
			if ((city != null
					&& city.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0 || ""
						.equals(pref))) {
				result.add(city);
			}
		}
		return result;
	}
	
	
	public String boundList() {
		try {
			city = cityLogic.loadCityById(city);
			filterCity = cityLogic.loadCityById(filterCity);
		} catch (Exception e) {
			setErrorMessage("Error trying to bind city, state and country.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public java.lang.String disableCityAction(){
		return null;
	}

	public City getFilterCity() {
		return filterCity;
	}

	public void setFilterCity(City filterCity) {
		this.filterCity = filterCity;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

}