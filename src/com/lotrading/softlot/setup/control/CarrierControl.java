package com.lotrading.softlot.setup.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlDataList;
import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.component.html.HtmlExtendedDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;

import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.ICarrierAwbNumberLogic;
import com.lotrading.softlot.setup.logic.ICarrierRateLogic;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.setup.logic.IcarrierLogic;
import com.lotrading.softlot.util.base.AlphabeticComparator;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:51 a.m.
 */
public class CarrierControl extends BaseControl {

	private Carrier carrier;
	private Carrier carrierFilter;
	private List<Carrier> carriers;
	private CarrierPorts carrierRatePort;
	private CarrierPorts carrierAirRatePortFilter;
	private CarrierPorts carrierOceanLCLRatePortFilter;
	private CarrierPorts carrierOcean20RatePortFilter;
	private CarrierPorts carrierOcean40RatePortFilter;
	private CarrierRate carrierRateOtherCharge;
	private IcarrierLogic carrierLogic;
	private ICarrierRateLogic carrierRateLogic;
	private ICarrierAwbNumberLogic carrierAwbNumberLogic;
	private IMasterValueLogic masterValueLogic;

	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;
	private HtmlDataList dataListOceanLCL;
	private HtmlDataList dataListOcean20;
	private HtmlDataList dataListOcean40;
	private HtmlDataList dataListAir;
	private HtmlDataTable airRatesDataTable;
	private HtmlDataTable lclRatesDataTable;
	private HtmlDataTable ocean20RatesDataTable;
	private HtmlDataTable ocean40RatesDataTable;
	private String selectedTab;
	private String rateTypeAirTabName = "tabAir";
	private String rateTypeOceanLCLTabName = "tabOceanLCL";
	private String rateTypeOcean20TabName = "tabOcean20";
	private String rateTypeOcean40TabName = "tabOcean40";
	private String awbNumbersTabName = "tabAwbNumbers";

	private String awbNumStart;
	private String awbNumQuantity;
	private String awbNumEnd;
	private List<CarrierAwbNumber> _tmpNumbers;
	private List<CarrierAwbNumber> awbNums;
	private boolean showUsedAwbNumbers;

	private List<SelectItem> shippingTypes;
	private List<SelectItem> portsList;
	private List<SelectItem> airportsList;
	private List<SelectItem> otherChargesList;
	
	private List<CarrierPorts> airRatesList;
	private List<CarrierPorts> ocean20RatesList;
	private List<CarrierPorts> ocean40RatesList;
	private List<CarrierPorts> oceanLCLRatesList;

	private String converterDollar = Constants.CONVERTER_CURRENCY_DOLLAR;
	private String converterEuro = Constants.CONVERTER_CURRENCY_EURO;

	public CarrierControl() {
		carrier = new Carrier();
		carrierFilter = new Carrier();
		carriers = new ArrayList<Carrier>();
		carrierAirRatePortFilter = new CarrierPorts();
		carrierOcean20RatePortFilter = new CarrierPorts();
		carrierOcean40RatePortFilter = new CarrierPorts();
		carrierOceanLCLRatePortFilter = new CarrierPorts();
		
	}

	public void searchCarriersAction() {
		try {
			carriers = carrierLogic.searchCarrier(carrierFilter);
			if (carriers == null || carriers.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Carrier.\n" + e.getMessage());
		}
	}
	
	public void selectCarrierActionAux(ActionEvent event){
		carrier = (Carrier) table.getRowData();
		setSessionAttribute("_tmpCarrier", carrier);
		carrier = new Carrier();
	}
	
	/* Este metodo se llamara desde el ultimo metodo SET.*/
	public void selectCarrierAction() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
		if(null != id && !id.equals("")) {
			if(id.equals("nuevo")) {
				id = null;
				newCarrierAction();
			}else if(id.equals("existe")) {
				carrier = (Carrier) getSessionAttribute("_tmpCarrier");
				id = null;
				removeSessionAttribute("_tmpAwb");
				
				CarrierPorts _tmpCarrierPort = new CarrierPorts();
				_tmpCarrierPort.setCarrierId(carrier.getCarrierId());
				try {
					_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_AIR); /* se cargan los rates de la pestana AIR del carrier seleccionado. */
					carrier.setCarrierAirRates(carrierLogic.loadCarrierRates(_tmpCarrierPort));
					airRatesList = carrier.getCarrierAirRates();

					_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL); /* se cargan los rates de la pestana OCEAN LCL del carrier seleccionado.*/
					carrier.setCarrierOceanRatesLCL(carrierLogic.loadCarrierRates(_tmpCarrierPort));
					oceanLCLRatesList = carrier.getCarrierOceanRatesLCL();

					_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_20); /* se cargan los rates de la pestananOCEAN 20' delncarriernseleccionado.*/
					carrier.setCarrierOceanRates20(carrierLogic.loadCarrierRates(_tmpCarrierPort));
					ocean20RatesList = carrier.getCarrierOceanRates20();
					
					_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_40); /* se cargan los rates de la pestana OCEAN 40' del carrier seleccionado.*/
					carrier.setCarrierOceanRates40(carrierLogic.loadCarrierRates(_tmpCarrierPort));
					ocean40RatesList = carrier.getCarrierOceanRates40();
					
					CarrierAwbNumber _tmpAwbNumber = new CarrierAwbNumber(); /* se cargan los AWB Numbers del carrier seleccionado. */
					_tmpAwbNumber.getCarrier().setCarrierId(carrier.getCarrierId());
					carrier.setCarrierAwbNumbers(carrierLogic.loadCarrierAwbNumber(_tmpAwbNumber));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveCarrierAction() {
		try {
			if (carrierLogic.saveCarrier(carrier)) {
				setInfoMessage("carrier Order was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Carrier.\n"+ e.getMessage());
		}
	}

	public void newCarrierAction() {
		carrier = new Carrier();
		awbNums = new ArrayList<CarrierAwbNumber>();
	}

	public void newRateAction() {
		CarrierPorts _tmpRatePort = new CarrierPorts();
		_tmpRatePort.setCarrierId(carrier.getCarrierId());
		MasterValue _tmpMasterValue = new MasterValue();
		if (selectedTab.equals(rateTypeAirTabName)) {
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_AIR);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_AIR));
			carrier.getCarrierAirRates().add(0, _tmpRatePort);
			airRatesList = carrier.getCarrierAirRates();
			carrierAirRatePortFilter = new CarrierPorts();

		} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_LCL);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_LCL));
			carrier.getCarrierOceanRatesLCL().add(0, _tmpRatePort);
			oceanLCLRatesList = carrier.getCarrierOceanRatesLCL();
			carrierOceanLCLRatePortFilter = new CarrierPorts();

		} else if (selectedTab.equals(rateTypeOcean20TabName)) {
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_20_40);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_20));
			carrier.getCarrierOceanRates20().add(0, _tmpRatePort);
			ocean20RatesList = carrier.getCarrierOceanRates20();
			carrierOcean20RatePortFilter = new CarrierPorts();

		} else if (selectedTab.equals(rateTypeOcean40TabName)) {
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_20_40);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_40));
			carrier.getCarrierOceanRates40().add(0, _tmpRatePort);
			ocean40RatesList = carrier.getCarrierOceanRates40();
			carrierOcean40RatePortFilter = new CarrierPorts();
		}
		try {
			List<MasterValue> chargesList = masterValueLogic.searchMasterValue(_tmpMasterValue);
			for (MasterValue _tmpMasterVal : chargesList) {
				if(_tmpMasterVal.getDisabledDate() == null){
					CarrierRate _tmpCarrierRate = new CarrierRate();
					_tmpCarrierRate.setChargeType(_tmpMasterVal);
					_tmpCarrierRate.setFlat((_tmpMasterVal.getValue3().equalsIgnoreCase("flat")? true : false));
					_tmpRatePort.getCarrierRates().add(_tmpCarrierRate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Error trying to retrieve the charges types.\n"+ e.getMessage());
		}
	}

	public void saveRateAction() {
		CarrierPorts _tmpCarrierPort = new CarrierPorts();
		if (selectedTab.equals(rateTypeAirTabName)) {
			_tmpCarrierPort = (CarrierPorts) dataListAir.getRowData();

		} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
			_tmpCarrierPort = (CarrierPorts) dataListOceanLCL.getRowData();

		} else if (selectedTab.equals(rateTypeOcean20TabName)) {
			_tmpCarrierPort = (CarrierPorts) dataListOcean20.getRowData();

		} else if (selectedTab.equals(rateTypeOcean40TabName)) {
			_tmpCarrierPort = (CarrierPorts) dataListOcean40.getRowData();
		}

		try {
			boolean valid = true;
			if(_tmpCarrierPort.getEffectiveDate() == null){
				setErrorMessage("- Effective Date field is required");
				valid = false;
			}
			if(_tmpCarrierPort.getPortOrigin() <= 0){
				setErrorMessage("- Origin Port field is required");
				valid = false;
			}
			if(_tmpCarrierPort.getPortDestination() <= 0){
				setErrorMessage("- 	Destination port field is required");
				valid = false;
			}
			if(valid){
				_tmpCarrierPort = carrierRateLogic.saveCarrierRate(_tmpCarrierPort);
				setInfoMessage("Rates were successfully saved");
			} else {
				setErrorMessage("**** Data was not saved ****");
			}
			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the rates.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectToDeleteRateAction(){
		try{
			carrierRateOtherCharge = new CarrierRate();
			if (selectedTab.equals(rateTypeAirTabName)) {
				carrierRateOtherCharge = (CarrierRate) airRatesDataTable.getRowData();
			} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
				carrierRateOtherCharge = (CarrierRate) lclRatesDataTable.getRowData();
			} else if (selectedTab.equals(rateTypeOcean20TabName)) {
				carrierRateOtherCharge = (CarrierRate) ocean20RatesDataTable.getRowData();
			} else if (selectedTab.equals(rateTypeOcean40TabName)) {
				carrierRateOtherCharge = (CarrierRate) ocean40RatesDataTable.getRowData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRateAction(){
		try {
			if (carrierRateLogic != null){
				if(carrierRateLogic.deleteCarrierRate(carrierRateOtherCharge)){
					CarrierPorts _tmpCarrierPort = new CarrierPorts();
					_tmpCarrierPort.setCarrierId(carrier.getCarrierId());
					if (selectedTab.equals(rateTypeAirTabName)) {
						_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_AIR); /* se cargan los rates de la pestana AIR del carrier seleccionado. */
						carrier.setCarrierAirRates(carrierLogic.loadCarrierRates(_tmpCarrierPort));
						airRatesList = carrier.getCarrierAirRates();
					} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
						_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL); /* se cargan los rates de la pestana OCEAN LCL del carrier seleccionado.*/
						carrier.setCarrierOceanRatesLCL(carrierLogic.loadCarrierRates(_tmpCarrierPort));
						oceanLCLRatesList = carrier.getCarrierOceanRatesLCL();
					} else if (selectedTab.equals(rateTypeOcean20TabName)) {
						_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_20); /* se cargan los rates de la pestananOCEAN 20' delncarriernseleccionado.*/
						carrier.setCarrierOceanRates20(carrierLogic.loadCarrierRates(_tmpCarrierPort));
						ocean20RatesList = carrier.getCarrierOceanRates20();
					} else if (selectedTab.equals(rateTypeOcean40TabName)) {
						_tmpCarrierPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_40); /* se cargan los rates de la pestana OCEAN 40' del carrier seleccionado.*/
						carrier.setCarrierOceanRates40(carrierLogic.loadCarrierRates(_tmpCarrierPort));
						ocean40RatesList = carrier.getCarrierOceanRates40();
					}
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Rate. \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void preCalculateAwbNumbersAction() {
		if(carrier.getCarrierId() == Constants.CARRIER_LOT_LOGISTICS_ID){
			preCalculateBlNumbersAction(); /* Si el carrier es LOT LOGISTICS entonces aplica este metodo que aumenta de uno en uno y 
											no de 4 en 4 como es para los demas carriers tipo air*/
		}else{
			if(!awbNumStart.isEmpty()){
				if (!awbNumQuantity.isEmpty() && awbNumEnd.isEmpty()) {
					int awbNum = Integer.parseInt(awbNumStart);
					_tmpNumbers = new ArrayList<CarrierAwbNumber>();
					if (awbNum > 0) {
						for (int i = 0; i < Integer.parseInt(awbNumQuantity); ++i) {
							CarrierAwbNumber _tmpCarrAwbNum = new CarrierAwbNumber(String.valueOf(awbNum), carrier.getCarrierId());
							_tmpNumbers.add(_tmpCarrAwbNum);
							if (i < Integer.parseInt(awbNumQuantity) - 1) {
								if (awbNum % 10 == 6) {
									awbNum = awbNum + 4;
								} else {
									awbNum = awbNum + 11;
								}
							}
						}
						setAwbNumEnd(String.valueOf(awbNum));
					}
				} else if (awbNumQuantity.isEmpty() && !awbNumEnd.isEmpty()) {
					int awbNumInicio = Integer.parseInt(awbNumStart);
					int awbNumFin = Integer.parseInt(awbNumEnd);
					int _tmpAux = 0;
					_tmpNumbers = new ArrayList<CarrierAwbNumber>();
					if (awbNumInicio > 0 && awbNumInicio <= awbNumFin) {
						_tmpNumbers.add(new CarrierAwbNumber(String
								.valueOf(awbNumInicio), carrier.getCarrierId()));
						while (awbNumInicio < awbNumFin) {
							if (awbNumInicio % 10 == 6) {
								_tmpAux = awbNumInicio + 4;
							} else {
								_tmpAux = awbNumInicio + 11;
							}
							if (_tmpAux <= awbNumFin) {
								_tmpNumbers.add(new CarrierAwbNumber(String
										.valueOf(_tmpAux), carrier.getCarrierId()));
							}
							awbNumInicio = _tmpAux;
						}
						awbNumQuantity = String.valueOf(_tmpNumbers.size());
						awbNumEnd = String.valueOf(_tmpNumbers.get(
								_tmpNumbers.size() - 1).getAwbNumber());
					} else {
						setErrorMessage("Error trying to generate the AWB Number");
						_tmpNumbers = null;
					}
				}else{
					setErrorMessage("- Ending Number or Quantity is required.");
					_tmpNumbers = null;
				}
			}else{
				setErrorMessage("- Starting Number is required.");
				_tmpNumbers = null;
			}
		}
	}
	
	public void preCalculateBlNumbersAction() { /* Este metodo agrega a BL numbers valores de uno en uno*/
		if(!awbNumStart.isEmpty()){
			if (!awbNumQuantity.isEmpty() && awbNumEnd.isEmpty()) {
				int awbNum = Integer.parseInt(awbNumStart);
				_tmpNumbers = new ArrayList<CarrierAwbNumber>();
				if (awbNum > 0) {
					for (int i = 0; i < Integer.parseInt(awbNumQuantity); ++i) {
						_tmpNumbers.add(new CarrierAwbNumber(String.valueOf(awbNum), carrier.getCarrierId()));
						if (i < Integer.parseInt(awbNumQuantity) - 1) {
							awbNum++;
						}
					}
					setAwbNumEnd(String.valueOf(awbNum));
				}
			} else if (awbNumQuantity.isEmpty() && !awbNumEnd.isEmpty()) {
				int awbNumInicio = Integer.parseInt(awbNumStart);
				int awbNumFin = Integer.parseInt(awbNumEnd);
				_tmpNumbers = new ArrayList<CarrierAwbNumber>();
				if (awbNumInicio > 0 && awbNumInicio <= awbNumFin) {
					while(awbNumInicio >= awbNumFin){
						_tmpNumbers.add(new CarrierAwbNumber(String.valueOf(awbNumInicio), carrier.getCarrierId()));
						awbNumInicio++;
					}
					awbNumQuantity = String.valueOf(_tmpNumbers.size());
				} else {
					setErrorMessage("Error trying to generate the BL Number");
					_tmpNumbers = null;
				}
			}else{
				setErrorMessage("- Ending Number or Quantity is required.");
				_tmpNumbers = null;
			}
		}else{
			setErrorMessage("- Starting Number is required.");
			_tmpNumbers = null;
		}
	}

	public void saveAwbNumbersAction() {
		clearMessages();
		if(_tmpNumbers != null){
			if(!_tmpNumbers.isEmpty()){
				try {
					carrierAwbNumberLogic.saveCarrierAwbNumber(_tmpNumbers);
		
					CarrierAwbNumber _tmpAwbNumber = new CarrierAwbNumber(); // se cargan los AWB Numbers del carrier seleccionado.
					_tmpAwbNumber.getCarrier().setCarrierId(carrier.getCarrierId());
					carrier.setCarrierAwbNumbers(carrierLogic.loadCarrierAwbNumber(_tmpAwbNumber));
					setInfoMessage("AWB Numbers were succesfully generated");
					clearValuesGenerateAwbBlNumberAction();
				} catch (Exception e) {
					setErrorMessage("Error trying to save the AWB Numbers.\n"
							+ e.getMessage());
					e.printStackTrace();
				}
			}else{
				setWarningMessage("No data to save.");
			}
		}else{
			setWarningMessage("No data to save.");
		}
	}
	
	public void clearValuesGenerateAwbBlNumberAction(){
		awbNumStart = "";
		awbNumQuantity = "";
		awbNumEnd = "";
		_tmpNumbers = null;
	}
	
	private void clearValuesRatespopups(){
		carrierRatePort = null;
		carrierRateOtherCharge = null;
	}
	
	public void newOtherChargeRateAction() {
		carrierRatePort = new CarrierPorts();
		if (selectedTab.equals(rateTypeAirTabName)) {
			carrierRatePort = (CarrierPorts) dataListAir.getRowData();

		} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
			carrierRatePort = (CarrierPorts) dataListOceanLCL.getRowData();

		} else if (selectedTab.equals(rateTypeOcean20TabName)) {
			carrierRatePort = (CarrierPorts) dataListOcean20.getRowData();

		} else if (selectedTab.equals(rateTypeOcean40TabName)) {
			carrierRatePort = (CarrierPorts) dataListOcean40.getRowData();

		}
		carrierRateOtherCharge = new CarrierRate();
		carrierRateOtherCharge.setOtherCharge(true);
	}

	public void saveOtherChargeRateAction() {
		carrierRatePort.getCarrierRates().add(carrierRateOtherCharge);
		for (SelectItem _tmpItem : otherChargesList) {
			if ((Integer) _tmpItem.getValue() == carrierRateOtherCharge
					.getChargeType().getValueId()) {
				carrierRateOtherCharge.getChargeType().setValue1(
						_tmpItem.getLabel());
			}
		}
		try {
			carrierRatePort = carrierRateLogic.saveCarrierRate(carrierRatePort);
			setInfoMessage("Rates were successfully saved");
			clearValuesRatespopups();
		} catch (Exception e) {
			setErrorMessage("Error trying to save the rates.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void filtrateByPorts(){
		List<CarrierPorts> filteredItems = new ArrayList<CarrierPorts>();
		if (selectedTab.equals(rateTypeAirTabName)) {
			for(CarrierPorts _tmpCarrierPort: carrier.getCarrierAirRates()){
				if (carrierAirRatePortFilter.getPortDestination() == _tmpCarrierPort.getPortDestination() || carrierAirRatePortFilter.getPortDestination() == 0) {
					if(carrierAirRatePortFilter.getPortOrigin() == _tmpCarrierPort.getPortOrigin() || carrierAirRatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpCarrierPort);
					}
				}
			}
			airRatesList = filteredItems;

		} else if (selectedTab.equals(rateTypeOceanLCLTabName)) {
			for(CarrierPorts _tmpCarrierPort: carrier.getCarrierOceanRatesLCL()){
				if (carrierOceanLCLRatePortFilter.getPortDestination() == _tmpCarrierPort.getPortDestination() || carrierOceanLCLRatePortFilter.getPortDestination() == 0) {
					if(carrierOceanLCLRatePortFilter.getPortOrigin() == _tmpCarrierPort.getPortOrigin() || carrierOceanLCLRatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpCarrierPort);
					}
				}
			}
			oceanLCLRatesList = filteredItems;

		} else if (selectedTab.equals(rateTypeOcean20TabName)) {
			for(CarrierPorts _tmpCarrierPort: carrier.getCarrierOceanRates20()){
				if (carrierOcean20RatePortFilter.getPortDestination() == _tmpCarrierPort.getPortDestination() || carrierOcean20RatePortFilter.getPortDestination() == 0) {
					if(carrierOcean20RatePortFilter.getPortOrigin() == _tmpCarrierPort.getPortOrigin() || carrierOcean20RatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpCarrierPort);
					}
				}
			}
			ocean20RatesList = filteredItems;

		} else if (selectedTab.equals(rateTypeOcean40TabName)) {
			for(CarrierPorts _tmpCarrierPort: carrier.getCarrierOceanRates40()){
				if (carrierOcean40RatePortFilter.getPortDestination() == _tmpCarrierPort.getPortDestination() || carrierOcean40RatePortFilter.getPortDestination() == 0) {
					if(carrierOcean40RatePortFilter.getPortOrigin() == _tmpCarrierPort.getPortOrigin() || carrierOcean40RatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpCarrierPort);
					}
				}
			}
			ocean40RatesList = filteredItems;

		}
	}

	public boolean isCarrierAir() {
		if (carrier.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_AIR
				|| carrier.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_OCEAN_AND_AIR) {
			return true;
		}
		return false;
	}

	public boolean isCarrierOcean() {
		if (carrier.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_OCEAN
				|| carrier.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_OCEAN_AND_AIR) {
			return true;
		}
		return false;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * @param newVal
	 */
	public void setCarrier(Carrier newVal) {
		carrier = newVal;
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

	/**
	 * @param newVal
	 */
	public void setCarriers(List<Carrier> newVal) {
		carriers = newVal;
	}

	public int getSize() {
		if (carrier != null) {
			return carriers.size();
		}
		return 0;
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

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public Carrier getCarrierFilter() {
		return carrierFilter;
	}

	public void setCarrierFilter(Carrier carrierFilter) {
		this.carrierFilter = carrierFilter;
	}

	public List<SelectItem> getShippingTypes() {
		try {
			setShippingTypes(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ShippingTypesList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Shipping types.\n"
					+ e.getMessage());
		}
		return shippingTypes;
	}

	public void setShippingTypes(List<SelectItem> shippingTypes) {
		this.shippingTypes = shippingTypes;
	}

	public List<SelectItem> getPortsList() {
		try {
			List<Port> _tmpList = AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.PortsList").getElements(
					"faces");
			if (_tmpList != null) {
				List<SelectItem> _tmpPorts = new ArrayList<SelectItem>();
				for (Port _tmpPort : _tmpList) {
					if (!_tmpPort.isAir()) {
						SelectItem element = new SelectItem(new Integer(
								_tmpPort.getPortId()), _tmpPort.getName());
						_tmpPorts.add(element);
					}
				}
				setPortsList(_tmpPorts);
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Ports.\n"
					+ e.getMessage());
		}
		return portsList;
	}

	public void setPortsList(List<SelectItem> portsList) {
		this.portsList = portsList;
	}

	public List<SelectItem> getAirportsList() {
		try {
			List<Port> _tmpList = AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.PortsList").getElements(
					"faces");
			if (_tmpList != null) {
				List<SelectItem> _tmpAirports = new ArrayList<SelectItem>();
				for (Port _tmpPort : _tmpList) {
					if (_tmpPort.isAir()) {
						SelectItem element = new SelectItem(new Integer(
								_tmpPort.getPortId()), _tmpPort.getName());
						_tmpAirports.add(element);
					}
				}
				setAirportsList(_tmpAirports);
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Airports.\n"
					+ e.getMessage());
		}
		return airportsList;
	}

	public void setAirportsList(List<SelectItem> airportsList) {
		this.airportsList = airportsList;
	}

	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}

	public void setMasterValueLogic(IMasterValueLogic masterValueLogic) {
		this.masterValueLogic = masterValueLogic;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public ICarrierRateLogic getCarrierRateLogic() {
		return carrierRateLogic;
	}

	public void setCarrierRateLogic(ICarrierRateLogic carrierRateLogic) {
		this.carrierRateLogic = carrierRateLogic;
	}

	public HtmlDataList getDataListOceanLCL() {
		return dataListOceanLCL;
	}

	public void setDataListOceanLCL(HtmlDataList dataListOceanLCL) {
		this.dataListOceanLCL = dataListOceanLCL;
	}

	public HtmlDataList getDataListOcean20() {
		return dataListOcean20;
	}

	public void setDataListOcean20(HtmlDataList dataListOcean20) {
		this.dataListOcean20 = dataListOcean20;
	}

	public HtmlDataList getDataListOcean40() {
		return dataListOcean40;
	}

	public void setDataListOcean40(HtmlDataList dataListOcean40) {
		this.dataListOcean40 = dataListOcean40;
	}

	public HtmlDataList getDataListAir() {
		return dataListAir;
	}

	public void setDataListAir(HtmlDataList dataListAir) {
		this.dataListAir = dataListAir;
	}

	public String getAwbNumEnd() {
		return awbNumEnd;
	}

	public void setAwbNumEnd(String awbNumEnd) {
		this.awbNumEnd = awbNumEnd;
	}

	public String getAwbNumQuantity() {
		return awbNumQuantity;
	}

	public void setAwbNumQuantity(String awbNumQuantity) {
		this.awbNumQuantity = awbNumQuantity;
	}

	public String getAwbNumStart() {
		return awbNumStart;
	}

	public void setAwbNumStart(String awbNumStart) {
		this.awbNumStart = awbNumStart;
	}

	public ICarrierAwbNumberLogic getCarrierAwbNumberLogic() {
		return carrierAwbNumberLogic;
	}

	public void setCarrierAwbNumberLogic(
			ICarrierAwbNumberLogic carrierAwbNumberLogic) {
		this.carrierAwbNumberLogic = carrierAwbNumberLogic;
	}

	public List<CarrierAwbNumber> get_tmpNumbers() {
		return _tmpNumbers;
	}

	public void set_tmpNumbers(List<CarrierAwbNumber> _tmpNumbers) {
		this._tmpNumbers = _tmpNumbers;
	}

	public List<CarrierAwbNumber> getAwbNums() {
		awbNums = new ArrayList<CarrierAwbNumber>();
		if (showUsedAwbNumbers) {
			setAwbNums(carrier.getCarrierAwbNumbers());
		} else {
			for (CarrierAwbNumber _tmpItem : carrier.getCarrierAwbNumbers()) {
				if (!_tmpItem.isUsed()) {
					awbNums.add(_tmpItem);
				}
			}
		}
		return awbNums;
	}

	public void setAwbNums(List<CarrierAwbNumber> awbNums) {
		this.awbNums = awbNums;
	}

	public boolean isShowUsedAwbNumbers() {
		return showUsedAwbNumbers;
	}

	public void setShowUsedAwbNumbers(boolean showUsedAwbNumbers) {
		this.showUsedAwbNumbers = showUsedAwbNumbers;
	}

	public String getConverterDollar() {
		return converterDollar;
	}

	public void setConverterDollar(String converterDollar) {
		this.converterDollar = converterDollar;
	}

	public String getConverterEuro() {
		return converterEuro;
	}

	public void setConverterEuro(String converterEuro) {
		this.converterEuro = converterEuro;
	}

	public List<SelectItem> getOtherChargesList() {
		List<MasterValue> _tmpOCList;
		try {
			_tmpOCList = masterValueLogic.searchMasterValue(new MasterValue(0,
					Constants.MASTER_CHARGE_TYPE_OTHER));
			otherChargesList = new ArrayList<SelectItem>();
			for (MasterValue _tmpMasterVal : _tmpOCList) {
				otherChargesList.add(new SelectItem(new Integer(_tmpMasterVal
						.getValueId()), _tmpMasterVal.getValue1(),
						_tmpMasterVal.getValue1()));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Other Charges.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		Collections.sort(otherChargesList, new AlphabeticComparator());
		return otherChargesList;
	}

	public void setOtherChargesList(List<SelectItem> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}

	public CarrierRate getCarrierRateOtherCharge() {
		return carrierRateOtherCharge;
	}

	public void setCarrierRateOtherCharge(CarrierRate carrierRateOtherCharge) {
		this.carrierRateOtherCharge = carrierRateOtherCharge;
	}

	public CarrierPorts getCarrierRatePort() {
		return carrierRatePort;
	}

	public void setCarrierRatePort(CarrierPorts carrierRatePort) {
		this.carrierRatePort = carrierRatePort;
	}

	public IcarrierLogic getCarrierLogic() {
		return carrierLogic;
	}

	/**
	 * @param newVal
	 */
	public void setCarrierLogic(IcarrierLogic newVal) {
		carrierLogic = newVal;
		selectCarrierAction();
	}

	public HtmlDataTable getAirRatesDataTable() {
		return airRatesDataTable;
	}

	public void setAirRatesDataTable(HtmlDataTable airRatesDataTable) {
		this.airRatesDataTable = airRatesDataTable;
	}

	public HtmlDataTable getLclRatesDataTable() {
		return lclRatesDataTable;
	}

	public void setLclRatesDataTable(HtmlDataTable lclRatesDataTable) {
		this.lclRatesDataTable = lclRatesDataTable;
	}

	public HtmlDataTable getOcean20RatesDataTable() {
		return ocean20RatesDataTable;
	}

	public void setOcean20RatesDataTable(HtmlDataTable ocean20RatesDataTable) {
		this.ocean20RatesDataTable = ocean20RatesDataTable;
	}

	public HtmlDataTable getOcean40RatesDataTable() {
		return ocean40RatesDataTable;
	}

	public void setOcean40RatesDataTable(HtmlDataTable ocean40RatesDataTable) {
		this.ocean40RatesDataTable = ocean40RatesDataTable;
	}

	public List<CarrierPorts> getAirRatesList() {
		return airRatesList;
	}

	public void setAirRatesList(List<CarrierPorts> airRatesList) {
		this.airRatesList = airRatesList;
	}

	public List<CarrierPorts> getOcean40RatesList() {
		return ocean40RatesList;
	}

	public void setOcean40RatesList(List<CarrierPorts> ocean40RatesList) {
		this.ocean40RatesList = ocean40RatesList;
	}

	public List<CarrierPorts> getOcean20RatesList() {
		return ocean20RatesList;
	}

	public void setOcean20RatesList(List<CarrierPorts> ocean20RatesList) {
		this.ocean20RatesList = ocean20RatesList;
	}

	public List<CarrierPorts> getOceanLCLRatesList() {
		return oceanLCLRatesList;
	}

	public void setOceanLCLRatesList(List<CarrierPorts> oceanLCLRatesList) {
		this.oceanLCLRatesList = oceanLCLRatesList;
	}

	public CarrierPorts getCarrierAirRatePortFilter() {
		return carrierAirRatePortFilter;
	}

	public void setCarrierAirRatePortFilter(CarrierPorts carrierRatePortFilter) {
		this.carrierAirRatePortFilter = carrierRatePortFilter;
	}

	public CarrierPorts getCarrierOceanLCLRatePortFilter() {
		return carrierOceanLCLRatePortFilter;
	}

	public void setCarrierOceanLCLRatePortFilter(
			CarrierPorts carrierOceanLCLRatePortFilter) {
		this.carrierOceanLCLRatePortFilter = carrierOceanLCLRatePortFilter;
	}

	public CarrierPorts getCarrierOcean20RatePortFilter() {
		return carrierOcean20RatePortFilter;
	}

	public void setCarrierOcean20RatePortFilter(
			CarrierPorts carrierOcean20RatePortFilter) {
		this.carrierOcean20RatePortFilter = carrierOcean20RatePortFilter;
	}

	public CarrierPorts getCarrierOcean40RatePortFilter() {
		return carrierOcean40RatePortFilter;
	}

	public void setCarrierOcean40RatePortFilter(
			CarrierPorts carrierOcean40RatePortFilter) {
		this.carrierOcean40RatePortFilter = carrierOcean40RatePortFilter;
	}
}