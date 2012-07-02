package com.lotrading.softlot.setup.control;
import java.util.*;

import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlExtendedDataTable;

import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.ICityLogic;
import com.lotrading.softlot.setup.logic.IPortLogic;
import com.lotrading.softlot.util.base.AlphabeticComparator;
import com.lotrading.softlot.util.base.control.BaseControl;
import co.com.landsoft.devbase.util.listas.AdministradorListas;
/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class PortControl extends BaseControl{

	private Port port;
	private Port filterPort;
	private List<Port> ports;
 	private IPortLogic portLogic;
	private HtmlExtendedDataTable table;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private List<City> cities;
	private List<City> citiesSearch;
	private ICityLogic cityLogic;
	private List countries;
	private List provinces;


	public PortControl(){
		ports = new ArrayList<Port>();
		port = new Port();
		filterPort = new Port();
	}
	public String searchAirportsAction() {
		try {
			ports.clear();
			List <Port> _auxPorts = portLogic.searchPort(filterPort);
			
			for (Port _port : _auxPorts ){
				if(_port.isAir()){
					ports.add(_port);
				}
			}
			
			if (ports == null || ports.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "";
	}
	public String searchPortsAction() {
		try {
			ports.clear();
			List <Port> _auxPorts = portLogic.searchPort(filterPort);
			
			for (Port _port : _auxPorts ){
				if(!_port.isAir()){
					ports.add(_port);
				}
			}
			if (ports == null || ports.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "";
	}
	public void finalize() throws Throwable {                 

	}

	public Port getPort(){
		return port;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPort(Port newVal){
		port = newVal;
	}

	public List<Port> getPorts(){
		return ports;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPorts(List<Port> newVal){
		ports = newVal;
	}

	public IPortLogic getPortLogic(){
		return portLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPortLogic(IPortLogic newVal){
		portLogic = newVal;
	}

	public java.lang.String selectPortAction(){
		port = (Port) table.getRowData();
		return null;
	}

	public void saveAirportAction(){
		try {
			port.setAir(true);
			if(portLogic.savePort(port)){
				setInfoMessage("");
			}else{
				setWarningMessage("Data was not saved");
			}
		}catch (Exception e) {
			setErrorMessage("Error trying to save the Airport.\n"+e.getMessage());
		}
	}
	
	public void savePortAction(){
		try {
			port.setAir(false);
			if(portLogic.savePort(port)){
				setInfoMessage("");
			}else{
				setWarningMessage("Data was not saved");
			}
		}catch (Exception e) {
			setErrorMessage("Error trying to save the Port.\n"+e.getMessage());
		}
	}
	public void clearCity() {
		port.setCity(new City());
	}
	
	public List<City> autocompleteCity(Object suggest) {
		String pref = (String) suggest;
		ArrayList<City> result = new ArrayList<City>();
		for (City city : getCities()) {
			if ((city != null
					&& city.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0 || ""
						.equals(pref))) {
				result.add(city);
			}
		}
		return result;
	}
	
	public List<City> getCities() {
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
	
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public String boundList() {
		try {
			port.setCity(cityLogic.loadCityById(port.getCity()));
			filterPort.setCity(cityLogic.loadCityById(filterPort.getCity()));
		} catch (Exception e) {
			setErrorMessage("Error trying to bind city, state and country.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	public void clearSearchAction() {
		filterPort = new Port();
	}


	public java.lang.String disablePortAction(){
		return null;
	}

	public void newAirportAction(){
		try {
			port = new Port();
			port.setAir(true);
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new airport.\n"
					+ e.getMessage());
		}
	}
	
	public void newPortAction(){
		try {
			port = new Port();
			port.setAir(false);
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new port.\n"
					+ e.getMessage());
		}
	}
	
	public Port getFilterPort() {
		return filterPort;
	}

	public void setFilterPort(Port filterPort) {
		this.filterPort = filterPort;
	}
	
	public String getSize() {
		if (ports == null) {
			return "Found:0";
		} else {
			System.out.println(ports.size());
			return "Found:" + ports.size();
		}
		
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
	
	public List<Port> getAirports() {
		return ports;
	}
	public void setAirports(List<Port> airports) {
		this.ports = airports;
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
	public ICityLogic getCityLogic() {
		return cityLogic;
	}
	public void setCityLogic(ICityLogic cityLogic) {
		this.cityLogic = cityLogic;
	}

}