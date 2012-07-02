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
	<title>LOTRADING :: CLIENT ORDERS</title>
	<style type="text/css">
		.rich-table-cell {
			padding: 0px;
		}
				
		.styleTableOtherCarges th{
			background-color: #FFFFE1;
		}
	</style>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<script type="text/javascript" src="../../js/general.js"></script>
	
</head>
<body>
<t:saveState value="#{carrierControl.carrierFilter }"/>
<t:saveState value="#{carrierControl.carriers}"/>
<t:saveState value="#{carrierControl.carrier}"/>
<t:saveState value="#{carrierControl.selectedTab}"/>
<t:saveState value="#{carrierControl.awbNumStart}"/>
<t:saveState value="#{carrierControl.awbNumQuantity}"/>
<t:saveState value="#{carrierControl.awbNumEnd}"/>
<t:saveState value="#{carrierControl._tmpNumbers}"/>
<t:saveState value="#{carrierControl.otherChargesList}"/>
<t:saveState value="#{carrierControl.carrierRatePort}"/>
<t:saveState value="#{carrierControl.carrierAirRatePortFilter}"/>
<t:saveState value="#{carrierControl.carrierOcean20RatePortFilter}"/>
<t:saveState value="#{carrierControl.carrierOcean40RatePortFilter}"/>
<t:saveState value="#{carrierControl.carrierOceanLCLRatePortFilter}"/>
<t:saveState value="#{carrierControl.carrierRateOtherCharge}"/>
<t:saveState value="#{carrierControl.awbNums}"/>
<t:saveState value="#{carrierControl.airRatesList}"/>
<t:saveState value="#{carrierControl.oceanLCLRatesList}"/>
<t:saveState value="#{carrierControl.ocean20RatesList}"/>
<t:saveState value="#{carrierControl.ocean40RatesList}"/>
<h:form id="formCarrierDetails">
	<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelCarrierDetails">
		<table border="0" cellspacing="2" width="1010px">
			<tr>
				<td nowrap="nowrap" align="right" >
					<h:inputHidden id="carrierId" value="#{carrierControl.carrier.carrierId}"/>
					Carrier Name:
				</td>
				<td >
					<h:inputText id="carrierNameText" value="#{carrierControl.carrier.name}" style="width:250px"/>
				</td>
				<td nowrap="nowrap" align="right" >
					IATA Code:
				</td>
				<td>
					<h:inputText id="iataCodeText" value="#{carrierControl.carrier.iataCode}"/>
				</td>
				<td nowrap="nowrap" align="right">
					Carrier Code:
				</td>
				<td >
					<h:inputText id="carrierCodeText" value="#{carrierControl.carrier.carrierCode}"/>
				</td>
				<td nowrap="nowrap" align="right">
					<h:selectBooleanCheckbox value="#{carrierControl.carrier.allowChildrenAWB}"/>
					<h:outputText value="Allow Children AWB" />
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">
					Shipping Type:
				</td>
				<td>
					<h:selectOneMenu id="shippingTypeCombobox" value="#{carrierControl.carrier.carrierType.valueId}" style="width:140px" >
						<f:selectItems value="#{carrierControl.shippingTypes}"/>
                   	</h:selectOneMenu>
				</td>
				<td nowrap="nowrap" align="right">
					LOT Account:
				</td>
				<td>
					<h:inputText id="lotAccountText" value="#{carrierControl.carrier.lotAccount}"/>
				</td>
				<td nowrap="nowrap" align="right">
					Notes:
				</td>
				<td>
					<h:inputText id="NotesText" value="#{carrierControl.carrier.notes}"/>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center" height="40" valign="bottom">
					<a4j:commandButton value="Save" action="#{carrierControl.saveCarrierAction }" oncomplete="#{rich:component('messagesPanel')}.show();" reRender="tabsRateTypes,carrierId"/>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</rich:panel>
	
	<rich:tabPanel id="tabsRateTypes" switchType="client" contentStyle="padding:0px; border-spacing:0px;" selectedTab="#{carrierControl.selectedTab}" >
		<rich:tab label="Air Rates" name="tabAir" status="none" rendered="#{carrierControl.carrierAir}" id="airRatesTab">
			<table width="100%">
				<tr valign="middle">
					<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
						<a4j:commandLink id="newAirRateLink" action="#{carrierControl.newRateAction}" reRender="listAirRates,filterOriginAirPort,filterDestAirPort" tabindex="1">New Rate</a4j:commandLink>
					</td>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;">
						Filter by Origin Airport:
						<h:selectOneMenu id="filterOriginAirPort" value="#{carrierControl.carrierAirRatePortFilter.portOrigin}" style="width:134px" tabindex="2">
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.airportsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listAirRates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;" colspan="2">
						Filter by Destination Airport:
						<h:selectOneMenu id="filterDestAirPort" value="#{carrierControl.carrierAirRatePortFilter.portDestination}" style="width:134px" tabindex="3" >
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.airportsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listAirRates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
			</table>
			<div style="height: 553px; overflow: auto;">
				<rich:dataList id="listAirRates" value="#{carrierControl.airRatesList}" var="carrierPort" binding="#{carrierControl.dataListAir }">
					<rich:panel >
						<rich:message />
						<table>
							<tr>
								<td>
									Origin Airport:
								</td>
								<td>
									<h:selectOneMenu id="portOrigin" value="#{carrierPort.portOrigin}" style="width:134px" tabindex="4">
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.airportsList}" />
									</h:selectOneMenu>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									Destination Airport:
								</td>
								<td>
									<h:selectOneMenu id="portDestin" value="#{carrierPort.portDestination}" style="width:134px" tabindex="4">
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.airportsList}" />
									</h:selectOneMenu>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a4j:commandButton value="Save" action="#{carrierControl.saveRateAction }" oncomplete="#{rich:component('messagesPanel')}.show();" reRender="listAirRates" tabindex="4"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a4j:commandLink oncomplete="#{rich:component('newOtherChargeRatePopup')}.show()" reRender="listAirRates" action="#{carrierControl.newOtherChargeRateAction }" tabindex="4" rendered="#{carrierPort.carrierPortId > 0}">Add Other Charge</a4j:commandLink>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<rich:dataTable id="airRatesDataTable" value="#{carrierPort.carrierRates}" var="carrierRate" rowClasses="row1, row2" rows="20" binding="#{carrierControl.airRatesDataTable}" rendered="#{carrierPort.carrierPortId > 0}">
										<f:facet name="header">
											<rich:columnGroup>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Rate($/Kg)"></h:outputText>
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Minimum($)"></h:outputText>
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" breakBefore="true">
													<h:outputText value="Full"></h:outputText>
												</rich:column>
												<rich:column sortable="false">
													<h:outputText value="Full"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>
										<rich:column sortable="false" label="Full" id="col_3" width="100">
											<h:outputText value="#{carrierRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_1" width="60">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.rate}" id="full" style="width:60px ;text-align: right" 
						                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_5" width="62">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.minimum}" id="minFull" style="width:67px;text-align: right" 
						                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_6" width="40">
											&nbsp;&nbsp;
											<h:outputText value="flat" rendered="#{carrierRate.flat}"/>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_7" width="23">
											<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
													rendered="#{carrierRate.otherCharge }" action="#{carrierControl.selectToDeleteRateAction}">										
												<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
											</a4j:commandLink>
										</rich:column>
									</rich:dataTable>
								</td>
								<td valign="top" style="padding:1px 16px;">
									Effective Date:
									<rich:calendar value="#{carrierPort.effectiveDate}" datePattern="MM/d/yyyy" inputSize="10" oninputblur="#{rich:element('newAirRateLink')}.focus()"/>
								</td>
							</tr>
						</table>
					</rich:panel>
				</rich:dataList>
				<h:commandLink tabindex="4" onfocus="#{rich:element('newAirRateLink')}.focus()"/>
			</div>
		</rich:tab>
		
		<rich:tab label="Ocean LCL" name="tabOceanLCL" status="none" rendered="#{carrierControl.carrierOcean}">
			<table width="100%">
				<tr valign="middle">
					<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
						<a4j:commandLink id="newLCLRateLink" action="#{carrierControl.newRateAction}" reRender="listLclRates,filterDestLCLPort,filterOrigLCLPort" tabindex="1">New Rate</a4j:commandLink>
					</td>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;">
						Filter by Origin Port:
						<h:selectOneMenu id="filterOrigLCLPort" value="#{carrierControl.carrierOceanLCLRatePortFilter.portOrigin}" style="width:134px" tabindex="2">
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listLclRates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;" colspan="2">
						Filter by Destination Port:
						<h:selectOneMenu id="filterDestLCLPort" value="#{carrierControl.carrierOceanLCLRatePortFilter.portDestination}" style="width:134px" tabindex="3" >
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listLclRates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
			</table>
			<div style="height: 553px; overflow: auto;">				
				<rich:dataList id="listLclRates" value="#{carrierControl.oceanLCLRatesList}" var="carrierPort" binding="#{carrierControl.dataListOceanLCL }">
					<rich:panel >
						<table>
							<tr>
								<td>
									Origin Port:
								</td>
								<td>
									<h:selectOneMenu id="portOrigin" value="#{carrierPort.portOrigin}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									Destination Port:
								</td>
								<td>
									<h:selectOneMenu id="portDestin" value="#{carrierPort.portDestination}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a4j:commandButton value="Save" action="#{carrierControl.saveRateAction }" oncomplete="#{rich:component('messagesPanel')}.show();" reRender="listLclRates" tabindex="4"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup')}.show()" reRender="listLclRates" action="#{carrierControl.newOtherChargeRateAction }" tabindex="4" rendered="#{carrierPort.carrierPortId > 0}">Add Other Charge</a4j:commandLink>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<rich:dataTable id="LclRatesDataTable" value="#{carrierPort.carrierRates}" var="carrierRate" rowClasses="row1, row2" rows="20" binding="#{carrierControl.lclRatesDataTable}" rendered="#{carrierPort.carrierPortId > 0}">
										<f:facet name="header">
											<rich:columnGroup>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Rate($/Kg)"></h:outputText>
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Minimum($)"></h:outputText>
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" breakBefore="true">
													<h:outputText value="Full"></h:outputText>
												</rich:column>
												<rich:column sortable="false">
													<h:outputText value="Full"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>
										<rich:column sortable="false" label="Full" id="col_3" width="100">
											<h:outputText value="#{carrierRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_1" width="60">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.rate}" id="full" style="width:60px;text-align: right" 
						                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_5" width="62">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.minimum}" id="minFull" style="width:67px;text-align: right"
													onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_6" width="40">
											&nbsp;&nbsp;
											<h:outputText value="flat" rendered="#{carrierRate.flat}"/>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_7" width="23">
											<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
													rendered="#{carrierRate.otherCharge}" action="#{carrierControl.selectToDeleteRateAction}">										
												<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
											</a4j:commandLink>
										</rich:column>
									</rich:dataTable>
								</td>
								<td valign="top" style="padding:1px 16px;">
									Effective Date:
									<rich:calendar value="#{carrierPort.effectiveDate}" datePattern="MM/d/yyyy" inputSize="10" oninputblur="#{rich:element('newLCLRateLink')}.focus()"/>
								</td>
							</tr>
						</table>
					</rich:panel>
				</rich:dataList>
				<h:commandLink tabindex="4" onfocus="#{rich:element('newLCLRateLink')}.focus()"/>
			</div>
		</rich:tab>
		
		<rich:tab label="Ocean 20" name="tabOcean20" status="none" rendered="#{carrierControl.carrierOcean}">
			<table width="100%">
				<tr valign="middle">
					<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
						<a4j:commandLink id="new20RateLink" action="#{carrierControl.newRateAction}" reRender="listOcean20Rates,filterOrig20Port,filterDest20Port" tabindex="1">New Rate</a4j:commandLink>
					</td>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;">
						Filter by Origin Port:
						<h:selectOneMenu id="filterOrig20Port" value="#{carrierControl.carrierOcean20RatePortFilter.portOrigin}" style="width:134px" tabindex="2">
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listOcean20Rates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;" colspan="2">
						Filter by Destination Port:
						<h:selectOneMenu id="filterDest20Port" value="#{carrierControl.carrierOcean20RatePortFilter.portDestination}" style="width:134px" tabindex="3" >
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listOcean20Rates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
			</table>
			<div style="height: 553px; overflow: auto;">
				<rich:dataList id="listOcean20Rates" value="#{carrierControl.ocean20RatesList}" var="carrierPort" binding="#{carrierControl.dataListOcean20 }">
					<rich:panel >
						<table>
							<tr>
								<td align="right">
									Origin Port:
								</td>
								<td>
									<h:selectOneMenu id="portOrigin" value="#{carrierPort.portOrigin}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td>
									Destination Port:
								</td>
								<td>
									<h:selectOneMenu id="portDestin" value="#{carrierPort.portDestination}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a4j:commandButton value="Save" action="#{carrierControl.saveRateAction }" oncomplete="#{rich:component('messagesPanel')}.show();" reRender="listOcean20Rates" tabindex="4"/>
								</td>
							</tr>
							<tr>
								<td  colspan="2">
									<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup2')}.show()" reRender="listOcean20Rates" action="#{carrierControl.newOtherChargeRateAction }" tabindex="4" rendered="#{carrierPort.carrierPortId > 0}">Add Other Charge</a4j:commandLink>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<rich:dataTable id="Ocean20RatesDataTable" value="#{carrierPort.carrierRates}" var="carrierRate" rowClasses="row1, row2" rows="20" binding="#{carrierControl.ocean20RatesDataTable}" rendered="#{carrierPort.carrierPortId > 0}">
										<f:facet name="header">
											<rich:columnGroup>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Rate($/Kg)"></h:outputText>
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" breakBefore="true">
													<h:outputText value="Full"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>
										<rich:column sortable="false" label="Full" id="col_3" width="100">
											<h:outputText value="#{carrierRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_1" width="60">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.rate}" id="full" style="width:60px;text-align: right" 
						                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_6" width="40">
											&nbsp;&nbsp;
											<h:outputText value="flat" rendered="#{carrierRate.flat}"/>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_7" width="23">
											<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
													rendered="#{carrierRate.otherCharge}" action="#{carrierControl.selectToDeleteRateAction}">										
												<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
											</a4j:commandLink>
										</rich:column>
									</rich:dataTable>
								</td>
								<td valign="top" style="padding:1px 16px;">
									Effective Date:
									<rich:calendar value="#{carrierPort.effectiveDate}" datePattern="MM/d/yyyy" inputSize="10" oninputblur="#{rich:element('new20RateLink')}.focus()"/>
								</td>
							</tr>
						</table>
					</rich:panel>
				</rich:dataList>
				<h:commandLink tabindex="4" onfocus="#{rich:element('new20RateLink')}.focus()"/>
			</div>
		</rich:tab>
		
		<rich:tab label="Ocean 40" name="tabOcean40" status="none" rendered="#{carrierControl.carrierOcean}">
			<table width="100%">
				<tr valign="middle">
					<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
						<a4j:commandLink id="new40RateLink" action="#{carrierControl.newRateAction}" reRender="listOcean40Rates,filterOrig40Port,filterDest40Port" tabindex="1">New Rate</a4j:commandLink>
					</td>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;">
						Filter by Origin Port:
						<h:selectOneMenu id="filterOrig40Port" value="#{carrierControl.carrierOcean40RatePortFilter.portOrigin}" style="width:134px" tabindex="2">
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listOcean40Rates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;;" colspan="2">
						Filter by Destination Port:
						<h:selectOneMenu id="filterDest40Port" value="#{carrierControl.carrierOcean40RatePortFilter.portDestination}" style="width:134px" tabindex="3" >
							<f:selectItem itemLabel="ALL" itemValue="0" />
							<f:selectItems value="#{carrierControl.portsList}" />
							<a4j:support event="onchange" action="#{carrierControl.filtrateByPorts}" reRender="listOcean40Rates"></a4j:support>
						</h:selectOneMenu>
					</td>
				</tr>
			</table>
			<div style="height: 553px; overflow: auto;">
				<rich:dataList id="listOcean40Rates" value="#{carrierControl.ocean40RatesList}" var="carrierPort" binding="#{carrierControl.dataListOcean40 }">
					<rich:panel >
						<table>
							<tr>
								<td align="right">
									Origin Port:
								</td>
								<td>
									<h:selectOneMenu id="portOrigin" value="#{carrierPort.portOrigin}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td>
									Destination Port:
								</td>
								<td>
									<h:selectOneMenu id="portDestin" value="#{carrierPort.portDestination}" style="width:134px" tabindex="4" >
										<f:selectItem itemLabel="-- SELECT --" itemValue="" />
										<f:selectItems value="#{carrierControl.portsList}" />
									</h:selectOneMenu>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a4j:commandButton value="Save" action="#{carrierControl.saveRateAction }" oncomplete="#{rich:component('messagesPanel')}.show();" reRender="listOcean40Rates" tabindex="4"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup2')}.show()" reRender="listOcean40Rates" action="#{carrierControl.newOtherChargeRateAction }" tabindex="4" rendered="#{carrierPort.carrierPortId > 0}">Add Other Charge</a4j:commandLink>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<rich:dataTable id="Ocean40RatesDataTable" value="#{carrierPort.carrierRates}" var="carrierRate" rowClasses="row1, row2" rows="20" binding="#{carrierControl.ocean40RatesDataTable}" rendered="#{carrierPort.carrierPortId > 0}">
										<f:facet name="header">
											<rich:columnGroup>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" colspan="1">
													<h:outputText value="Rate($/Kg)"></h:outputText>
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column rowspan="2">
													 <rich:spacer />
												</rich:column>
												<rich:column sortable="false" breakBefore="true" >
													<h:outputText value="Full"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>
										<rich:column sortable="false" label="Full" id="col_3" width="100">
											<h:outputText value="#{carrierRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_1" width="60">
											<h:inputText styleClass="styleInput6" value="#{carrierRate.rate}" id="full" style="width:60px;text-align: right" 
						                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
						                	</h:inputText>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_6" width="40">
											&nbsp;&nbsp;
											<h:outputText value="flat" rendered="#{carrierRate.flat}"/>
										</rich:column>
										<rich:column sortable="false" label="Full" id="col_7" width="23">
											<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
													rendered="#{carrierRate.otherCharge}" action="#{carrierControl.selectToDeleteRateAction}">										
												<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
											</a4j:commandLink>
										</rich:column>
									</rich:dataTable>
								</td>
								<td valign="top" style="padding:1px 16px;">
									Effective Date:
									<rich:calendar value="#{carrierPort.effectiveDate}" datePattern="MM/d/yyyy" inputSize="10" tabindex="20"/>
								</td>
							</tr>
						</table>
					</rich:panel>
				</rich:dataList>
				<h:commandLink tabindex="21" onfocus="#{rich:element('new40RateLink')}.focus()"/>
			</div>
		</rich:tab>
		
		<rich:tab label="AWB Numbers" name="tabAwbNumbers" status="none" rendered="#{carrierControl.carrierAir}">
			
				<table style="border-collapse:separate; border-spacing:20px 6px;">
					<tr>
						<td>
							<a4j:commandLink reRender="awbNumbersDataTable" onclick="#{rich:component('generateAwbNumbersPopup')}.show()">Add numbers</a4j:commandLink>
						</td>
						<td>
							<h:selectBooleanCheckbox value="#{carrierControl.showUsedAwbNumbers}" >
								<a4j:support event="onclick" reRender="awbNumbersDataTable" ajaxSingle="true" status="none"/>
							</h:selectBooleanCheckbox>
							Show Used Numbers
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="height: 513px; overflow: auto; width: 177px" >
								<rich:dataTable id="awbNumbersDataTable" value="#{carrierControl.awbNums}" var="awbNumber" rowClasses="row1, row2">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column width="100">
												<h:outputText value="AWB Number"></h:outputText>
											</rich:column>
											<rich:column width="40">
												<h:outputText value="Used"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									
									<rich:column width="100">
										<h:outputText value="#{awbNumber.awbNumber}" id="awbNum" style="width:98px"/>
									</rich:column>
									<rich:column width="40" style="text-align: center;" >
										<h:selectBooleanCheckbox value="#{awbNumber.used}" onclick="return false"/>
									</rich:column>							
								</rich:dataTable>
							</div>
						</td>
					</tr>
				</table>
		</rich:tab>	
		
		<rich:tab label="BL Numbers" name="tabBlNumbers" status="none" rendered="#{carrierControl.carrierOcean && carrierControl.carrier.showAwbBlNumbersTab}">
			
				<table style="border-collapse:separate; border-spacing:20px 6px;">
					<tr>
						<td>
							<a4j:commandLink reRender="blNumbersDataTable" onclick="#{rich:component('generateBlNumbersPopup')}.show()">Add numbers</a4j:commandLink>
						</td>
						<td>
							<h:selectBooleanCheckbox value="#{carrierControl.showUsedAwbNumbers}" >
								<a4j:support event="onclick" reRender="blNumbersDataTable" ajaxSingle="true" status="none"/>
							</h:selectBooleanCheckbox>
							Show Used Numbers
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="height: 513px; overflow: auto; width: 177px" >
								<rich:dataTable id="blNumbersDataTable" value="#{carrierControl.awbNums}" var="blNumber" rowClasses="row1, row2">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column width="100">
												<h:outputText value="BL Number"></h:outputText>
											</rich:column>
											<rich:column width="40">
												<h:outputText value="Used"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									
									<rich:column width="100">
										<h:outputText value="#{blNumber.awbNumber}" id="blNum" style="width:98px"/>
									</rich:column>
									<rich:column width="40" style="text-align: center;" >
										<h:selectBooleanCheckbox value="#{blNumber.used}" onclick="return false"/>
									</rich:column>							
								</rich:dataTable>
							</div>
						</td>
					</tr>
				</table>
		</rich:tab>	
	</rich:tabPanel>
</h:form>

	<rich:modalPanel id="generateAwbNumbersPopup" autosized="true" left="200" top="155" moveable="false">
		<f:facet name="header">
			<h:outputText value="Generate AWB Numbers" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
            <h:panelGroup>	            	
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="generateAwbNumbersPopup" attachTo="hidelink" operation="hide" event="onclick"/>	                
            </h:panelGroup>
       	</f:facet>
        <h:form>
			<table>
				<tr>
					<td nowrap="nowrap">
						Starting Number:
					</td>
					<td>
						<h:inputText id="statingNumberInput" value="#{carrierControl.awbNumStart}" style="width:80px" onkeydown="return validateNumbers(event)" tabindex="1">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateAwbNumbersAction }" reRender="endingNumberInput, quantityNumberInput"/>
						</h:inputText>
					</td>
					<td nowrap="nowrap">
						Quantity:
					</td>
					<td>
						<h:inputText  id="quantityNumberInput" value="#{carrierControl.awbNumQuantity}" style="width:50px" onkeydown="return validateNumbers(event)" 
								onclick="#{rich:element('endingNumberInput')}.value = ''" onkeypress="#{rich:element('endingNumberInput')}.value = ''"  tabindex="2">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateAwbNumbersAction }" reRender="endingNumberInput" />
						</h:inputText>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						Ending Number:
					</td>
					<td>
						<h:inputText id="endingNumberInput" value="#{carrierControl.awbNumEnd}" onkeydown="return validateNumbers(event)" style="width:80px"
								onclick="#{rich:element('quantityNumberInput')}.value = ''" onkeypress="#{rich:element('quantityNumberInput')}.value = ''"  tabindex="3">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateAwbNumbersAction }" reRender="quantityNumberInput" />
						</h:inputText>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" valign="bottom" height="35">
						<a4j:commandButton id="generateAwbNumButton" value="Generate" reRender="awbNumbersDataTable, statingNumberInput, quantityNumberInput, endingNumberInput" action="#{carrierControl.saveAwbNumbersAction}" 
							oncomplete="if('#{facesContext.maximumSeverity.ordinal}' == 0){setTimeout(function(){#{rich:component('generateAwbNumbersPopup')}.hide();},1500);}"
							 tabindex="4" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" onclick="#{rich:component('generateAwbNumbersPopup')}.hide()" status="none" ajaxSingle="true"  tabindex="5"
							action="#{carrierControl.clearValuesGenerateAwbBlNumberAction}" onblur="#{rich:element('statingNumberInput')}.focus()"
							reRender="statingNumberInput, quantityNumberInput, endingNumberInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle" ></rich:messages>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="generateBlNumbersPopup" autosized="true" left="200" top="155" moveable="false">
		<f:facet name="header">
			<h:outputText value="Generate BL Numbers" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
            <h:panelGroup>	            	
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink2"/>
                <rich:componentControl for="generateBlNumbersPopup" attachTo="hidelink2" operation="hide" event="onclick"/>	                
            </h:panelGroup>
       	</f:facet>
        <h:form>
			<table>
				<tr>
					<td nowrap="nowrap">
						Starting Number:
					</td>
					<td>
						<h:inputText id="statingBlNumberInput" value="#{carrierControl.awbNumStart}" style="width:80px" onkeydown="return validateNumbers(event)" tabindex="1">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateBlNumbersAction }" reRender="endingBlNumberInput, quantityBlNumberInput"/>
						</h:inputText>
					</td>
					<td nowrap="nowrap">
						Quantity:
					</td>
					<td>
						<h:inputText  id="quantityBlNumberInput" value="#{carrierControl.awbNumQuantity}" style="width:50px" onkeydown="return validateNumbers(event)" 
								onclick="#{rich:element('endingBlNumberInput')}.value = ''" onkeypress="#{rich:element('endingBlNumberInput')}.value = ''"  tabindex="2">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateBlNumbersAction }" reRender="endingBlNumberInput" />
						</h:inputText>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						Ending Number:
					</td>
					<td>
						<h:inputText id="endingBlNumberInput" value="#{carrierControl.awbNumEnd}" onkeydown="return validateNumbers(event)" style="width:80px"
								onclick="#{rich:element('quantityBlNumberInput')}.value = ''" onkeypress="#{rich:element('quantityBlNumberInput')}.value = ''"  tabindex="3">
							<a4j:support event="onkeyup" action="#{carrierControl.preCalculateBlNumbersAction }" reRender="quantityBlNumberInput" />
						</h:inputText>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" valign="bottom" height="35">
						<a4j:commandButton id="generateBlNumButton" value="Generate" reRender="blNumbersDataTable, statingBlNumberInput, quantityBlNumberInput, endingBlNumberInput" action="#{carrierControl.saveAwbNumbersAction}" 
							oncomplete="if('#{facesContext.maximumSeverity.ordinal}' == 0){setTimeout(function(){#{rich:component('generateBlNumbersPopup')}.hide();},1500);}"
							 tabindex="4" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" onclick="#{rich:component('generateBlNumbersPopup')}.hide()" status="none" ajaxSingle="true"  tabindex="5"
							action="#{carrierControl.clearValuesGenerateAwbBlNumberAction}" onblur="#{rich:element('statingBlNumberInput')}.focus()"
							reRender="statingBlNumberInput, quantityBlNumberInput, endingBlNumberInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle" ></rich:messages>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="newOtherChargeRatePopup" autosized="true" left="200" top="155" moveable="false" >
		<f:facet name="header">
			<h:outputText value="Add Other Charge Rate" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
            <h:panelGroup>	            	
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink4"/>
                <rich:componentControl for="newOtherChargeRatePopup" attachTo="hidelink4" operation="hide" event="onclick"/>	                
            </h:panelGroup>
       	</f:facet>
        <h:form >
			<table>
				<tr>
					<td>
						<table border="1" style="border-collapse:collapse;" bordercolor="#C4C0C9">
							<thead class="rich-table-thead" >
								<tr class="rich-table-header " >
									<th rowspan="2" valign="bottom">Other Charge</th>
									<th colspan="1">Rate</th>
									<th colspan="1" >Minimum</th>
								</tr>
								<tr class="rich-table-header ">
									<th>
										Full
									</th>
									<th>
										Full
									</th>
								</tr>
								
							</thead>
							<tr>
								<td>
									<h:selectOneMenu id="otherChargesComboBox" value="#{carrierControl.carrierRateOtherCharge.chargeType.valueId}"  style="width:140px" required="true" requiredMessage="A Other Charge item is required">
										<f:selectItem itemLabel="-- SELECT --" />
										<f:selectItems value="#{carrierControl.otherChargesList}"/>
				                   	</h:selectOneMenu>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{carrierControl.carrierRateOtherCharge.rate }" id="fullOC" style="width:60px" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{carrierControl.carrierRateOtherCharge.minimum }" id="minFullOC" style="width:60px" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="bottom" height="35">
						<a4j:commandButton value="Save" reRender="tabsRateTypes,newOtherChargeRatePopup" action="#{carrierControl.saveOtherChargeRateAction}" oncomplete="#{rich:component('messagesPanel')}.show();#{rich:component('newOtherChargeRatePopup')}.hide()" ></a4j:commandButton>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" onclick="#{rich:component('newOtherChargeRatePopup')}.hide()" status="none" ajaxSingle="true" reRender="newOtherChargeRatePopup"></a4j:commandButton>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="newOtherChargeRatePopup2" autosized="true" left="200" top="155" moveable="false" >
		<f:facet name="header">
			<h:outputText value="Add Other Charge Rate" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
            <h:panelGroup>	            	
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink3"/>
                <rich:componentControl for="newOtherChargeRatePopup2" attachTo="hidelink3" operation="hide" event="onclick"/>	                
            </h:panelGroup>
       	</f:facet>
        <h:form >
			<table>
				<tr>
					<td>
						<table border="1" style="border-collapse:collapse;" bordercolor="#C4C0C9">
							<thead class="rich-table-thead" >
								<tr class="rich-table-header " >
									<th rowspan="2" valign="bottom">Other Charge</th>
									<th colspan="1">Rate</th>
								</tr>
								<tr class="rich-table-header ">
									<th>
										Full
									</th>
								</tr>
								
							</thead>
							<tr>
								<td>
									<h:selectOneMenu id="otherChargesComboBox" value="#{carrierControl.carrierRateOtherCharge.chargeType.valueId}"  style="width:140px" required="true" requiredMessage="A Other Charge item is required">
										<f:selectItem itemLabel="-- SELECT --" />
										<f:selectItems value="#{carrierControl.otherChargesList}"/>
				                   	</h:selectOneMenu>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{carrierControl.carrierRateOtherCharge.rate }" id="fullOC2" style="width:60px" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="bottom" height="35">
						<a4j:commandButton value="Save" reRender="tabsRateTypes,newOtherChargeRatePopup2" action="#{carrierControl.saveOtherChargeRateAction}" oncomplete="#{rich:component('messagesPanel')}.show();#{rich:component('newOtherChargeRatePopup2')}.hide()"></a4j:commandButton>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" onclick="#{rich:component('newOtherChargeRatePopup2')}.hide()" status="none" ajaxSingle="true" reRender="newOtherChargeRatePopup2"></a4j:commandButton>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="deleteRateConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this Rate?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink7" />
                <rich:componentControl for="deleteRateConfirmPopup" attachTo="hidelink7"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="right" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{carrierControl.deleteRateAction}" 
	                            oncomplete="#{rich:component('deleteRateConfirmPopup')}.hide();"
	                            reRender="formCarrierDetails"/>
                        </td>
                        <td>
                        	&nbsp;&nbsp;
                        </td>
                        <td align="left" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteRateConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
	
	<a4j:include id="messagesPanelcarriers" viewId="../../jsp/businessPartners/messagesPopup.jsp" />
	
</body>
</html>
</f:view>