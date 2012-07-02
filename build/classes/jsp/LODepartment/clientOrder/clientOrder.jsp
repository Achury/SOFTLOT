<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/clientOrder.css"/>
	<script type="text/javascript" src="../../../js/clientOrder.js"></script>
	<title>LOTRADING :: CLIENT ORDERS</title>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			changeColorETA();
		});
	
		function setFlagModif(valor){
			document.getElementById('formClieOrd:flag_isModified').value = valor;
		}
		
		function isFlagModif(){
			if(document.getElementById('formClieOrd:flag_isModified').value == 'false'){
				return false;
			}else if(document.getElementById('formClieOrd:flag_isModified').value == 'true'){
				return true;
			}
		}
		
		function setColorInputETA(color){
			document.getElementById('formClieOrd:etaDateInputDate').style.backgroundColor=color;
		}
	</script>
</head>
<body>
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	
	<t:saveState value="#{clienOrderControl.clientOrder }"/>
	<t:saveState value="#{clienOrderControl.clientOrderFilter }"/>
	<t:saveState value="#{clienOrderControl.clientOrders }"/>
	<t:saveState value="#{clienOrderControl.callsHistoryClientFiltered }" />
	<t:saveState value="#{clienOrderControl.callsHistorySupplierFiltered }" />
	<t:saveState value="#{clienOrderControl.callHistory}" />
	<t:saveState value="#{clienOrderControl.partnerContactsList}" />
	<t:saveState value="#{clienOrderControl.selectedTab}" />
	<t:saveState value="#{clienOrderControl.consigneesObjects}" />
	<t:saveState value="#{clienOrderControl.pickupFromsObjects}" />
	<t:saveState value="#{clienOrderControl.regionSwitch}" />
	<t:saveState value="#{clienOrderControl.when}" />
	
	<h:form id="formClieOrd">
		<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelDetails">
			<h:inputHidden id="flag_isModified" value="false"/>
			<table border="0" cellspacing="2" width="99%">
				<tr>
					<td nowrap="nowrap" align="left" colspan="3">
						<table border="0">
							<tr>
								<td nowrap="nowrap" align="left" style="padding:1px 11px;">
									Order No:																
									<h:inputText id="orderNumText" value="#{clienOrderControl.clientOrder.clientOrderNo}" size="8" readonly="true"/>	
					               	<h:inputHidden id="clieOrdId" value="#{clienOrderControl.clientOrder.clientOrderId }"/>
								</td>
								<td  nowrap="nowrap" align="right" style="padding:1px 11px;">
									Order Date: 
									<rich:calendar id="orderDate" cellWidth="25px" inputSize="10" datePattern="MM/d/yyyy" enableManualInput="true" status="none" readonly="true"
										value="#{clienOrderControl.clientOrder.createdDate }" required="true" requiredMessage="- Order Date field is required"/>
								</td>							
								<td nowrap="nowrap" align="right" style="padding:1px 11px;">
									Status:
									<h:selectOneMenu id="StatusCombobox" value="#{clienOrderControl.clientOrder.status.valueId }" style="width:129px" onchange="setFlagModif('true');">
										<f:selectItems value="#{clienOrderControl.status}"/>
			                        </h:selectOneMenu>
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 11px;">
									Biz unit:
									<h:selectOneMenu id="regionComboBox" value="#{clienOrderControl.regionSwitch}" rendered="#{clienOrderControl.clientOrder.clientOrderId <= 0 }" onchange="#{rich:component('switchRegionPopup')}.show()">
										<f:selectItems value="#{clienOrderControl.regions}"/>
									</h:selectOneMenu>
									<h:inputText id="regionText" size="3" value="#{clienOrderControl.clientOrder.region.value1 }" readonly="true" rendered="#{clienOrderControl.clientOrder.clientOrderId > 0 }"/>
								</td>
								<td>
									<a4j:commandButton value="Generate Report" action="#{clienOrderControl.generarReport }"></a4j:commandButton>
								</td>
							</tr>
						</table>
						<hr align="right" width="101%" style="color:#DADADA" noshade="noshade">
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						<table cellpadding="0" cellspacing="0" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right" >
									Supplier:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="supplierText" value="#{clienOrderControl.clientOrder.supplier.name}" required="true" requiredMessage="- Supplier field is required" onchange="setFlagModif('true');;" tabindex="1"/>
									<rich:suggestionbox id="supplierSuggestBox" for="supplierText" 
										suggestionAction="#{clienOrderControl.autoCompleteSuppliers}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>								
										<a4j:support event="onselect" ajaxSingle="true" reRender="supplierAddressText,pickupFromComboBox" status="none">  
											<f:setPropertyActionListener value="#{result }" target="#{clienOrderControl.clientOrder.supplier}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="supplierAddressText" rows="3" value="#{clienOrderControl.clientOrder.supplier.address.address }
#{clienOrderControl.clientOrder.supplier.address.city.name }, #{clienOrderControl.clientOrder.supplier.address.city.stateProvince.value1 } #{clienOrderControl.clientOrder.supplier.address.postalCode }
#{clienOrderControl.clientOrder.supplier.address.city.country.value1 }" readonly="true"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td height="4px"/>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Pickup From:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="pickupFromComboBox" value="#{clienOrderControl.clientOrder.pickupFrom.shipPickUpId}" style="width:287px" 
											required="true" requiredMessage="- Pickup From field is required" tabindex="2">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
								        <f:selectItems value="#{clienOrderControl.pickupFroms}" />	
								        <a4j:support event="onchange" action="#{clienOrderControl.assignAddressToPickup }" reRender="pickupFromAddressText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="pickupFromComboBox"/>							        
								    </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="pickupFromAddressText" rows="3" value="#{clienOrderControl.clientOrder.pickupFrom.address.address }" readonly="true"></h:inputTextarea>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table cellpadding="0" cellspacing="0" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right">
									Client:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="clienteText" value="#{clienOrderControl.clientOrder.client.name}" required="true" requiredMessage="- Client field is required" onchange="setFlagModif('true');" tabindex="3"/>
									<rich:suggestionbox id="clientSuggestBox" for="clienteText" 
										suggestionAction="#{clienOrderControl.autoCompleteClients}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none">
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>								
										<a4j:support event="onselect" ajaxSingle="true" reRender="clientAddressText,consigneeComboBox" status="none">  
											<f:setPropertyActionListener value="#{result}" target="#{clienOrderControl.clientOrder.client}" />
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="clientAddressText" rows="3"  value="#{clienOrderControl.clientOrder.client.address.address }
#{clienOrderControl.clientOrder.client.address.city.name }, #{clienOrderControl.clientOrder.client.address.city.stateProvince.value1 } #{clienOrderControl.clientOrder.client.address.postalCode }
#{clienOrderControl.clientOrder.client.address.city.country.value1 }" readonly="true"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td height="4px"/>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Consignee:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="consigneeComboBox" value="#{clienOrderControl.clientOrder.shipTo.shipPickUpId}" style="width:287px"
											required="true" requiredMessage="- Consignee field is required" tabindex="4">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
										<f:selectItems value="#{clienOrderControl.consignees}" />
										<a4j:support event="onchange" action="#{clienOrderControl.assignAddressToConsignee }" reRender="consigneeAddressText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="consigneeComboBox"/>
								    </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="consigneeAddressText" rows="3" value="#{clienOrderControl.clientOrder.shipTo.address.address }" readonly="true"></h:inputTextarea>
								</td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									PO Number:
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea style="width:174px" value="#{clienOrderControl.clientOrder.numberPO }" onchange="setFlagModif('true');" tabindex="5"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Destination:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="destinationText" style="width:179px" value="#{clienOrderControl.clientOrder.destinationCity.name }" onchange="setFlagModif('true');" tabindex="6"></h:inputText>
									<rich:suggestionbox id="destinSuggestBox" for="destinationText" 
										suggestionAction="#{clienOrderControl.autocompleteCity}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none">
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>	
										<h:column>
										    <h:outputText value="#{result.stateProvince.value1}" style="font-style:bold" />
										</h:column>							
										<a4j:support event="onselect" ajaxSingle="true" status="none">  
											<f:setPropertyActionListener value="#{result.cityId }" target="#{clienOrderControl.clientOrder.destinationCity.cityId}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Incoterm:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="incotermCombobox" value="#{clienOrderControl.clientOrder.incoterm.valueId}" style="width:181px" onchange="setFlagModif('true');" tabindex="7">
										<f:selectItem itemLabel="" itemValue="" />
										<f:selectItems value="#{clienOrderControl.incoterms}"/>
			                        </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									City:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="IncotermCityText" style="width:179px" value="#{clienOrderControl.clientOrder.incotermCity.name }" onchange="setFlagModif('true');" tabindex="8"></h:inputText>
									<rich:suggestionbox id="IncotermCitySuggestBox" for="IncotermCityText"
										suggestionAction="#{clienOrderControl.autocompleteCity}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none">
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>	
										<h:column>
										    <h:outputText value="#{result.stateProvince.value1}" style="font-style:bold" />
										</h:column>							
										<a4j:support event="onselect" ajaxSingle="true" status="none">  
											<f:setPropertyActionListener value="#{result.cityId }" target="#{clienOrderControl.clientOrder.incotermCity.cityId}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Sales Rep:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="salesRepCombobox" value="#{clienOrderControl.clientOrder.salesRep.employeeId }" style="width:181px" 
											required="true" requiredMessage="- Sales Rep Field is required" onchange="setFlagModif('true');" tabindex="9">
										<f:selectItem itemLabel="" itemValue="" />
										<f:selectItems value="#{clienOrderControl.employees}"/>
			                        </h:selectOneMenu>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" >
						<table style="border-collapse: separate; border-spacing:  26px 0px;">
							<tr>
								<td>
									<a4j:commandButton id="saveButton" value="Save" action="#{clienOrderControl.saveClientOrderAction }" oncomplete="setFlagModif('false');#{rich:component('messagesPanel')}.show();setColorInputETA('#{clienOrderControl.clientOrder.etaDateLess }');" reRender="formClieOrd,InlandCSPopup" status="ajaxStatus"></a4j:commandButton>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<rich:tabPanel id="tabsClientOrder" switchType="client" contentStyle="padding:0px; border-spacing:0px; "
				selectedTab="#{clienOrderControl.selectedTab}">
				<rich:tab label="General Info" name="generalInfo" status="none">
					<table border="0" style="padding:5px;border-collapse: separate;border-spacing:  7px 20px;">
						<tr valign="top">
							<td>
								<table>
									<tr>
										<td>
											<div style="position: relative;">
												<div class="headerPanel" align="center" style="top: -5%;width: 14%;" > History</div>
												<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 276px;">
													<table cellpadding="0" cellspacing="1" class="styleInput3" style="padding: 3px;">
														<tr>
															<td nowrap="nowrap" align="right">
																Schedule Date:
															</td>
															<td nowrap="nowrap">
																<rich:calendar id="scheduleDate" cellWidth="25px" datePattern="MM/d/yyyy" onchanged="setFlagModif('true');"
																	value="#{clienOrderControl.clientOrder.scheduledPickupDate }" enableManualInput="true" tabindex="10" ondateselected="changeToScheduled()"/>	
																<a4j:jsFunction name="changeToScheduled" action="#{clienOrderControl.changeStatusScheduled }" ajaxSingle="true" status="none" reRender="StatusCombobox"/>									
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap" align="right">
																Pickup Date:
															</td>
															<td nowrap="nowrap">
																<rich:calendar id="pickupDate" cellWidth="25px" datePattern="MM/d/yyyy" onchanged="setFlagModif('true');"
																	value="#{clienOrderControl.clientOrder.pickupDate }" enableManualInput="true" tabindex="11" ondateselected="changeToInTransit()"/>	
																<a4j:jsFunction name="changeToInTransit" action="#{clienOrderControl.changeStatusInTransit }" ajaxSingle="true" status="none" reRender="StatusCombobox"/>										
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap" align="right">
																ETA:
															</td>
															<td nowrap="nowrap">
																<rich:calendar id="etaDate" cellWidth="25px" datePattern="MM/d/yyyy" onchanged="setFlagModif('true');" 
																	value="#{clienOrderControl.clientOrder.eta}" enableManualInput="true" tabindex="12" style="#{clienOrderControl.clientOrder.etaDateLess }">
																</rich:calendar>		
																<a4j:jsFunction name="changeColorETA" onbeforedomupdate="setColorInputETA('#{clienOrderControl.clientOrder.etaDateLess }')" ajaxSingle="true" status="none"/>	
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap" align="right">
																WH Arrival Date:
															</td>
															<td nowrap="nowrap">
																<rich:calendar id="whArrivalDate" cellWidth="25px" datePattern="MM/d/yyyy" onchanged="setFlagModif('true');"
																	value="#{clienOrderControl.clientOrder.whArrivalDate}" enableManualInput="true" tabindex="13"/>										
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap" align="right">
																Shipping Date:
															</td>
															<td nowrap="nowrap">
																<rich:calendar id="shippingDate" cellWidth="25px" datePattern="MM/d/yyyy" onchanged="setFlagModif('true');"
																	value="#{clienOrderControl.clientOrder.shippingDate}" enableManualInput="true" tabindex="14"/>							
															</td>
														</tr>
													</table>
												</rich:panel>
											</div>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td>
											<div style="position: relative;">
												<div class="headerPanel" align="center" style="top: -11%;width: 29%;" >Inland Info</div>
												<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 190px;">
													<table cellpadding="0" cellspacing="0" class="styleInput4" style="padding: 3px;">
														<tr>
															<td nowrap="nowrap" align="right" width="81px;">
																Inland Cost:&nbsp;
															</td>
															<td>
																<h:inputText id="inlandCostText" value="#{clienOrderControl.totalInlandCosts }" readonly="true" style="background-color:#FFFFFF; color:#000000;"
																	ondblclick="if(#{clienOrderControl.clientOrder.clientOrderId > 0}){#{rich:component('inlandInfoPopup') }.show();}">
																	<f:converter converterId="#{clienOrderControl.converterName }"/>
																</h:inputText>
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap" align="right">
																Inland Sale:&nbsp;
															</td>
															<td>
																<h:inputText id="inlandSaleText" value="#{clienOrderControl.totalInlandSales }" readonly="true" style="background-color:#FFFFFF; color:#000000;"
																	ondblclick="if(#{clienOrderControl.clientOrder.clientOrderId > 0})#{rich:component('inlandInfoPopup') }.show();">
																	<f:converter converterId="#{clienOrderControl.converterName }"/>	
																</h:inputText>
															</td>
														</tr>
													</table>
												</rich:panel>
											</div>
											<br>
										</td>
									</tr>
									<tr>
										<td>
											<div style="position: relative;">
												<div class="headerPanel" align="center" style="top: -16%;width: 32%;" >Supplier Info</div>
												<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 190px;">
													<table cellpadding="0" cellspacing="0" class="styleInput4" style="padding: 3px;">
														<tr>
															<td nowrap="nowrap" align="right" width="63px;">
																Sup. Invoice:&nbsp;
															</td>
															<td>
																<h:inputText id="suppInfoText" value="#{clienOrderControl.totalSuppInvoice }" readonly="true" style="background-color:#FFFFFF; color:#000000;"
																	ondblclick="if(#{clienOrderControl.clientOrder.clientOrderId > 0})#{rich:component('supplierInvoicePopup') }.show();">
																	<f:converter converterId="#{clienOrderControl.converterName }"/>
																</h:inputText>
															</td>
														</tr>
													</table>
												</rich:panel>
											</div>	
										</td>
									</tr>
								</table>
							</td>
							<td>
								Description:<br>
								<h:inputTextarea style="width:200px; height:200px;" value="#{clienOrderControl.clientOrder.description }" onchange="setFlagModif('true');" tabindex="15"></h:inputTextarea>
							</td>
							<td>
								Commodity:<br>
								<h:inputTextarea style="width:200px; height:200px;" value="#{clienOrderControl.clientOrder.comodity}" onchange="setFlagModif('true');" tabindex="16"></h:inputTextarea>
	
							</td>
							<td>
								Remarks:<br>
								<h:inputTextarea style="width:200px; height:200px;" value="#{clienOrderControl.clientOrder.remarks }" onchange="setFlagModif('true');" onblur="#{rich:element('supplierText')}.focus();" tabindex="17"></h:inputTextarea>
							</td>
						</tr>
					</table>
				</rich:tab>
				<rich:tab label="Shipping Info" name="shippingInfo" status="none">
					<table border="0" style="padding:5px;border-collapse: separate;border-spacing:  10px 20px;">
						<tr valign="top">
							<td>
								<div style="position: relative;">
									<div class="headerPanel" align="center" style="top: -11%;width: 32%;" >Air Shipment Info</div>
									<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 263px;">
										<table cellpadding="0" cellspacing="0" class="styleInput2" style="padding: 5px 4px 5px 9px;">
											<tr>
												<td nowrap="nowrap" align="right">
													Int. AWB:
												</td>
												<td>
													<h:inputText id="intAWBNumText" value="#{clienOrderControl.clientOrder.awb }" onchange="setFlagModif('true');" tabindex="10"></h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Full AWB:
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.awbNumber }" onchange="setFlagModif('true');" tabindex="11"></h:inputText>
												</td>
											</tr>
										</table>
									</rich:panel>
								</div>
							</td>
							<td rowspan="2">
								<div style="position: relative;">
									<div class="headerPanel" align="center" style="top: -7%;width: 46%;" >Cargo Information</div>
									<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 187px;">
										<table cellpadding="0" cellspacing="0" class="styleInput5" style="padding: 5px 4px 5px 8px;">
											<tr>
												<td nowrap="nowrap" align="right" width="90px">
													Weight (Kgs):
												</td>
												<td>
													<h:inputText id="weightText" value="#{clienOrderControl.clientOrder.weightKilograms }" onchange="setFlagModif('true');" tabindex="12"></h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Weight (Vlm):
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.weightVolumen }" onchange="setFlagModif('true');" tabindex="13"></h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Volume (cbm):
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.volumeCubicMeter }" onchange="setFlagModif('true');" tabindex="14"></h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Pieces:
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.numPieces }" onchange="setFlagModif('true');" tabindex="15"></h:inputText>
												</td>
											</tr>
										</table>
									</rich:panel>
								</div>
							</td>
							<td rowspan="2">				
								<table cellpadding="0" cellspacing="0" class="styleInput2" style="padding: 5px 4px 5px 8px;">
									<tr>
										<td nowrap="nowrap" align="right">
											Shipping Type:
										</td>
										<td>
											<h:selectOneMenu id="shippingTypeCombobox" value="#{clienOrderControl.clientOrder.shippingType.valueId }" style="width:157px" onchange="setFlagModif('true');" tabindex="16">
												<f:selectItem itemLabel="" itemValue="" />
												<f:selectItems  value="#{clienOrderControl.shippingTypes }"/>
												 <a4j:support event="onchange" reRender="shippingTypeCombobox,carrierCombobox" ajaxSingle="true"></a4j:support>
					                        </h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="right">
											Carrier:
										</td>
										<td>
											<h:selectOneMenu id="carrierCombobox" value="#{clienOrderControl.clientOrder.carrierId }" style="width:157px" onchange="setFlagModif('true');" tabindex="17">
												<f:selectItem itemLabel="" itemValue="" />
												<f:selectItems value="#{clienOrderControl.itemsCarriers}"/>
					                        </h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="right">
											LOT Invoice #:
										</td>
										<td>
											<h:inputText id="lotInvoiceText" value="#{clienOrderControl.clientOrder.invoiceId }" onchange="setFlagModif('true');" tabindex="18"></h:inputText>
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="right">
											WH Receipt:
										</td>
										<td>
											<h:inputText onchange="setFlagModif('true');" tabindex="19" onblur="#{rich:element('supplierText')}.focus();"></h:inputText>
										</td>
									</tr>
								</table>			
							</td>
						</tr>
						<tr valign="top">
							<td rowspan="2">
								<div style="position: relative;">
									<div class="headerPanel" align="center" style="top: -11%;width: 36%;" >Ocean Shipment Info</div>
									<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 263px;">
										<table cellpadding="0" cellspacing="0" class="styleInput2" style="padding: 5px 4px 5px 0px;">
											<tr>
												<td nowrap="nowrap" align="right" width="90px">
													Int BL:
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.bl }" onchange="setFlagModif('true');"></h:inputText>
													
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Full BL:
												</td>
												<td>
													<h:inputText value="#{clienOrderControl.clientOrder.blNumber }" onchange="setFlagModif('true');" onblur="#{rich:element('intAWBNumText')}.focus();"></h:inputText>
												</td>
											</tr>
										</table>
									</rich:panel>
								</div>
							</td>
						</tr>
					</table>
				</rich:tab>
				
				<rich:tab label="Supplier Call History" name="#{clienOrderControl.suppCallHistTabName}" ontabenter="#{rich:element('linkSupplierCallHist') }.click()" status="none" rendered="#{clienOrderControl.clientOrder.clientOrderId > 0 }">
					<a4j:include id="supplierCallTab" viewId="../../../jsp/LODepartment/clientOrder/callHistoryTabSupplier.jsp" />
				</rich:tab>
				
				<rich:tab label="Client Call History" name="#{clienOrderControl.clieCallHistTabName}" ontabenter="#{rich:element('linkClientCallHist') }.click()" status="none"  rendered="#{clienOrderControl.clientOrder.clientOrderId > 0 }">
					<a4j:include id="clientCallTab" viewId="../../../jsp/LODepartment/clientOrder/callHistoryTabClient.jsp" />
				</rich:tab>
				
			</rich:tabPanel>
			<h:commandLink action="#{clienOrderControl.changeCurrentTabClientCall}" id="linkClientCallHist" />
			<h:commandLink action="#{clienOrderControl.changeCurrentTabSupplierCall}" id="linkSupplierCallHist" />
		</rich:panel>	
		
		<rich:modalPanel id="switchRegionPopup" autosized="true" width="250" style="background-color:#F3F8FC;" onshow="#{rich:element('yesRegButton') }.focus()">
		<f:facet name="header">
			<h:outputText value="Change Biz unit" style="padding-right:15px;" />
		</f:facet>
	    <table width="100%">
	        <tbody>
	        	<tr>
		         	<td align="center">
		         		<h:outputText value="Are you sure you want to change the Biz unit for this transaction?" style="padding-right:15px;" />
		         		<br>
		         		<br>
		         	</td>		
	        	</tr>
	            <tr align="center">
	            	<td>
		            	<table>
		            		<tr>
		            			<td align="center" width="50px;">
					                <a4j:commandButton value="Yes" id="yesRegButton" action="#{clienOrderControl.changeYesAction }"  
					                    oncomplete="#{rich:component('switchRegionPopup')}.hide()" reRender="regionComboBox" process="regionComboBox"
					                    status="none" tabindex="1" ajaxSingle="true"/>
				                </td>
				                <td align="center" width="50px;">
				                	<a4j:commandButton value="No" action="#{clienOrderControl.changeNoAction }"
					                     oncomplete="#{rich:component('switchRegionPopup')}.hide();"  reRender="regionComboBox"
					                     status="none" tabindex="2" ajaxSingle="true" immediate="true"
				                     />
				                </td>
		            		</tr>
		            	</table>
		            </td>
	            </tr>
	        </tbody>
	    </table>
	</rich:modalPanel>
		
	</h:form>
	<a4j:include id="InlandCSPopup" viewId="../../../jsp/LODepartment/clientOrder/popupInlandInvoice.jsp" />
	<a4j:include id="callHistDetails" viewId="../../../jsp/LODepartment/clientOrder/callHistoryDetailsClientOrder.jsp" />
	<a4j:include id="messagesPanelCl" viewId="../../../jsp/businessPartners/messagesPopup.jsp" />
	<a4j:include id="loadingPopup" viewId="../../../jsp/messages/loadingPopup.jsp" />
	
	<rich:modalPanel id="confirmationPopup" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButton') }.focus()">
		<f:facet name="header">
			<h:outputText value="The content of this window has been changed" style="padding-right:15px;" />
		</f:facet>
	    <table width="100%">
	        <tbody>
	        	<tr>
		         	<td colspan="3">
		         		<h:outputText value="Do you want to save the changes?" style="padding-right:15px;" />
		         	</td>		
	        	</tr>
	            <tr>
	                <td align="right">
	                <a4j:commandButton value="Yes" id="yesButton"
	                    onclick="setFlagModif('false');#{rich:component('confirmationPopup')}.hide();#{rich:element('saveButton') }.click();"
	                    ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
	                </td>
	                <td align="center" width="50px;">
	                	<a4j:commandButton value="No" onclick="setFlagModif('false');#{rich:component('confirmationPopup')}.hide();#{rich:element('cancelLink') }.click();"
	                     ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
	                </td>
	                <td align="left">
	                	<a4j:commandButton value="Cancel"  onclick="#{rich:component('confirmationPopup')}.hide();"
		                     ajaxSingle="true" immediate="true" status="none" tabindex="3" onblur="#{rich:element('yesButton') }.focus()"/>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	</rich:modalPanel>
</body>
</html>
</f:view>