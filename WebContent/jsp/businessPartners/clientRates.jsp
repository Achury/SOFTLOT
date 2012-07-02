<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.rich-table-cell {
			padding: 0px;
		}
	</style>
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
</head>
<body>

	<t:saveState value="#{partnersControl.clientRate}" />
	<t:saveState value="#{partnersControl.clientRatePort}" />
	<t:saveState value="#{partnersControl.clientRateOtherCharge}" />
	<t:saveState value="#{partnersControl.clientAirRatePortFilter}" />
	<t:saveState value="#{partnersControl.clientOceanLCLRatePortFilter}" />
	<t:saveState value="#{partnersControl.clientOcean20RatePortFilter}" />
	<t:saveState value="#{partnersControl.clientOcean40RatePortFilter}" />
	<t:saveState value="#{partnersControl.airRatesList}" />
	<t:saveState value="#{partnersControl.ocean20RatesList}" />
	<t:saveState value="#{partnersControl.ocean40RatesList}" />
	<t:saveState value="#{partnersControl.oceanLCLRatesList}" />

<rich:modalPanel id="clientRatesPopup" width="745" left="168" top="1" height="547">
		<f:facet name="header">
			<h:outputText value="Client Rates" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
	        <h:panelGroup>	            	
	            <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	            <rich:componentControl for="clientRatesPopup" attachTo="hidelink" operation="hide" event="onclick"/>	                
	        </h:panelGroup>
	   	</f:facet>
	   
	   	<h:form id="formClientRates">
			<rich:tabPanel id="tabsRateTypes" switchType="client" contentStyle="padding:0px; border-spacing:0px;" selectedTab="#{partnersControl.selectedTabRates}">
				<rich:tab label="Air Rates" name="tabAir" status="none">
					<table width="100%">
						<tr valign="middle">
							<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
								<a4j:commandLink id="newAirRateLink" action="#{partnersControl.newRateAction}" reRender="listAirRates,filterOriginAirPort,filterDestAirPort" tabindex="1">New Rate</a4j:commandLink>
							</td>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;">
								Filter by Origin Airport:
								<h:selectOneMenu id="filterOriginAirPort" value="#{partnersControl.clientAirRatePortFilter.portOrigin}" style="width:134px" tabindex="2">
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.airPorts}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listAirRates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;" colspan="2">
								Filter by Destination Airport:
								<h:selectOneMenu id="filterDestAirPort" value="#{partnersControl.clientAirRatePortFilter.portDestination}" style="width:134px" tabindex="3" >
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.airPorts}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listAirRates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
					</table>
					<div style="height: 441px; overflow: auto;">
						<rich:dataList id="listAirRates" value="#{partnersControl.airRatesList}" var="clRatesPorts" binding="#{partnersControl.dataListAir }" >
							<rich:panel >
								<table>
									<tr>
										<td>
											Origin Airport:
										</td>
										<td>
											<h:selectOneMenu id="portOrigin" value="#{clRatesPorts.portOrigin}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.airPorts}" />
											</h:selectOneMenu>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											Destination Airport:
										</td>
										<td>
											<h:selectOneMenu id="portDestin" value="#{clRatesPorts.portDestination}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.airPorts}" />
											</h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td align="right">
											Carrier:
										</td>
										<td>
											<h:selectOneMenu id="carrier" value="#{clRatesPorts.carrierId}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.carriers}" />
											</h:selectOneMenu>
										</td>
										<td>
											<a4j:commandButton value="Save" action="#{partnersControl.saveRateAction }" reRender="listAirRates"  tabindex="4"/>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<a4j:commandLink oncomplete="#{rich:component('newOtherChargeRatePopup')}.show()" reRender="listAirRates" action="#{partnersControl.newOtherChargeRateAction }" tabindex="4" rendered="#{clRatesPorts.clientRatePortId > 0}">Add Other Charge</a4j:commandLink>
										</td>
									</tr>
								</table>
								<rich:dataTable id="airRatesDataTable" value="#{clRatesPorts.clientRates}" var="clRate" rowClasses="row1, row2" rows="20" binding="#{partnersControl.airRatesDataTable}" rendered="#{clRatesPorts.clientRatePortId > 0}">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Rate($/Kg)"></h:outputText>
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Minimum($)"></h:outputText>
											</rich:column>
											<rich:column rowspan="2">
												<h:outputText value="to AWB Doc"></h:outputText>
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" breakBefore="true">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Rate"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Min"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									<rich:column sortable="false" label="charge" id="col_1" width="100">
										<h:outputText value="#{clRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Offset" id="col_3" width="62" >
										<h:inputText styleClass="styleInput6" value="#{clRate.rateOffset}" id="offset" style="width:67px;text-align: right"
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			action="#{partnersControl.calculateRate }" reRender="rate">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Rate" id="col_4" width="60" style="text-align: right"> 
										<h:outputText styleClass="styleInput6" value="#{clRate.rate}" id="rate" >
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Offset" id="col_6" width="62">
										<h:inputText styleClass="styleInput6" value="#{clRate.minimumOffset}" id="minOffset" style="width:67px;text-align: right" 
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6' " tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			action="#{partnersControl.calculateRate }" reRender="min">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
																
									<rich:column sortable="false" label="Min" id="col_7" width="62" style="text-align: right">
										<h:outputText styleClass="styleInput6" value="#{clRate.minimumRate}" id="min">
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="toAwbDoc" id="col_8" width="20px" >
										<h:selectBooleanCheckbox value="#{clRate.selectedToAwbDoc}" rendered="#{clRate.showSelectedToAwbDoc}" tabindex="4"/>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_9" width="40">
										&nbsp;&nbsp;
										<h:outputText value="flat" rendered="#{clRate.flat}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_10" width="23">
										<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
												rendered="#{clRate.otherCharge }" action="#{partnersControl.selectToDeleteRateAction}">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
										</a4j:commandLink>
									</rich:column>
								</rich:dataTable>
							</rich:panel>
							<br>
						</rich:dataList>
						<h:commandLink tabindex="4" onfocus="#{rich:element('newAirRateLink')}.focus()"/>
					</div>
				</rich:tab>
				
				<rich:tab label="Ocean LCL" name="tabOceanLCL" status="none">
					<table width="100%">
						<tr valign="middle">
							<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
								<a4j:commandLink id="newLCLRateLink" action="#{partnersControl.newRateAction}" reRender="listLclRates,filterOriginLCLPort,filterDestLCLPort" tabindex="1">New Rate</a4j:commandLink>
							</td>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;">
								Filter by Origin Port:
								<h:selectOneMenu id="filterOriginLCLPort" value="#{partnersControl.clientOceanLCLRatePortFilter.portOrigin}" style="width:134px" tabindex="2">
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listLclRates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;" colspan="2">
								Filter by Destination Port:
								<h:selectOneMenu id="filterDestLCLPort" value="#{partnersControl.clientOceanLCLRatePortFilter.portDestination}" style="width:134px" tabindex="3" >
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listLclRates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
					</table>
					<div style="height: 441px; overflow: auto;">
						<rich:dataList id="listLclRates" value="#{partnersControl.oceanLCLRatesList}" var="clRatesPorts" binding="#{partnersControl.dataListOceanLCL }">
							<rich:panel >
								<table>
									<tr>
										<td>
											Origin Port:
										</td>
										<td>
											<h:selectOneMenu id="portOrigin" value="#{clRatesPorts.portOrigin}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											Destination Port:
										</td>
										<td>
											<h:selectOneMenu id="portDestin" value="#{clRatesPorts.portDestination}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td>
											Carrier:
										</td>
										<td>
											<h:selectOneMenu id="carrier" value="#{clRatesPorts.carrierId}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.carriers}" />
											</h:selectOneMenu>
										</td>
										<td>
											<a4j:commandButton value="Save" action="#{partnersControl.saveRateAction }" reRender="listLclRates" tabindex="4"/>
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup')}.show()" reRender="listLclRates" action="#{partnersControl.newOtherChargeRateAction }" rendered="#{clRatesPorts.clientRatePortId > 0}" tabindex="4">Add Other Charge</a4j:commandLink>
										</td>
									</tr>
								</table>
								<rich:dataTable id="LclRatesDataTable" value="#{clRatesPorts.clientRates}" var="clRate" rowClasses="row1, row2" rows="20" binding="#{partnersControl.lclRatesDataTable}" rendered="#{clRatesPorts.clientRatePortId > 0}">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Rate($/Kg)"></h:outputText>
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Minimum($)"></h:outputText>
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" breakBefore="true">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Rate"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Min"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									<rich:column sortable="false" label="Charge" id="col_1" width="100">
										<h:outputText value="#{clRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Offset" id="col_3" width="62">
										<h:inputText styleClass="styleInput6" value="#{clRate.rateOffset}" id="offset" style="width:67px;text-align: right" 
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			 action="#{partnersControl.calculateRate }" reRender="rate">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Rate" id="col_4" width="60">
										<h:outputText styleClass="styleInput6" value="#{clRate.rate}" id="rate"  style="width:67px;text-align: right">
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Offset" id="col_6" width="62">
										<h:inputText styleClass="styleInput6" value="#{clRate.minimumOffset}" id="minOffset" style="width:67px;text-align: right" 
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			 action="#{partnersControl.calculateRate }" reRender="min">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Min" id="col_7" width="62">
										<h:outputText styleClass="styleInput6" value="#{clRate.minimumRate}" id="min" style="width:67px;text-align: right">
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_9" width="40">
										&nbsp;&nbsp;
										<h:outputText value="flat" rendered="#{clRate.flat}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_10" width="23">
										<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
												rendered="#{clRate.otherCharge }" action="#{partnersControl.selectToDeleteRateAction}" tabindex="4">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
										</a4j:commandLink>
									</rich:column>
								</rich:dataTable>
							</rich:panel>
						</rich:dataList>
						<h:commandLink tabindex="4" onfocus="#{rich:element('newLCLRateLink')}.focus()"/>
					</div>
				</rich:tab>
				
				<rich:tab label="Ocean 20" name="tabOcean20" status="none">
					<table width="100%">
						<tr valign="middle">
							<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
								<a4j:commandLink id="new20RateLink" action="#{partnersControl.newRateAction}" reRender="listOcean20Rates,filterOrig20Port,filterDest20Port" tabindex="1">New Rate</a4j:commandLink>
							</td>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;">
								Filter by Origin Port:
								<h:selectOneMenu id="filterOrig20Port" value="#{partnersControl.clientOcean20RatePortFilter.portOrigin}" style="width:134px" tabindex="2">
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listOcean20Rates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;" colspan="2">
								Filter by Destination Port:
								<h:selectOneMenu id="filterDest20Port" value="#{partnersControl.clientOcean20RatePortFilter.portDestination}" style="width:134px" tabindex="3" >
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listOcean20Rates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
					</table>
					<div style="height: 441px; overflow: auto;">
						<rich:dataList id="listOcean20Rates" value="#{partnersControl.ocean20RatesList}" var="clRatesPorts" binding="#{partnersControl.dataListOcean20 }">
							<rich:panel >
								<table>
									<tr>
										<td>
											Origin Port:
										</td>
										<td>
											<h:selectOneMenu id="portOrigin" value="#{clRatesPorts.portOrigin}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											Destination Port:
										</td>
										<td>
											<h:selectOneMenu id="portDestin" value="#{clRatesPorts.portDestination}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td>
											Carrier:
										</td>
										<td>
											<h:selectOneMenu id="carrier" value="#{clRatesPorts.carrierId}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.carriers}" />
											</h:selectOneMenu>
										</td>
										<td>
											<a4j:commandButton value="Save" action="#{partnersControl.saveRateAction }" reRender="listOcean20Rates" tabindex="4"/>
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup2')}.show()" reRender="listAirRates" action="#{partnersControl.newOtherChargeRateAction }" rendered="#{clRatesPorts.clientRatePortId > 0}" tabindex="4">Add Other Charge</a4j:commandLink>
										</td>
									</tr>
								</table>
								<rich:dataTable id="Ocean20RatesDataTable" value="#{clRatesPorts.clientRates}" var="clRate" rowClasses="row1, row2" rows="20" binding="#{partnersControl.ocean20RatesDataTable}" rendered="#{clRatesPorts.clientRatePortId > 0}">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Rate($/Kg)"></h:outputText>
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" breakBefore="true">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false" >
												<h:outputText value="Rate"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									<rich:column sortable="false" label="Charge" id="col_1" width="100">
										<h:outputText value="#{clRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Offset" id="col_3" width="62">
										<h:inputText styleClass="styleInput6" value="#{clRate.rateOffset}" id="offset" style="width:67px;text-align: right" 
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			 action="#{partnersControl.calculateRate }" reRender="rate">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Rate" id="col_4" width="60">
										<h:outputText styleClass="styleInput6" value="#{clRate.rate}" id="rate" style="width:60px;text-align: right">
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_9" width="40">
										&nbsp;&nbsp;
										<h:outputText value="flat" rendered="#{clRate.flat}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_10" width="23">
										<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
												rendered="#{clRate.otherCharge }" action="#{partnersControl.selectToDeleteRateAction}">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
										</a4j:commandLink>
									</rich:column>
								</rich:dataTable>
							</rich:panel>
						</rich:dataList>
						<h:commandLink tabindex="4" onfocus="#{rich:element('new20RateLink')}.focus()"/>
					</div>
				</rich:tab>
				
				<rich:tab label="Ocean 40" name="tabOcean40" status="none">
					<table width="100%">
						<tr valign="middle">
							<td nowrap="nowrap" align="left" style="padding:5px 1px 0px 40px;">
								<a4j:commandLink id="new40RateLink" action="#{partnersControl.newRateAction}" reRender="listOcean40Rates,filterOrig40Port,filterDest40Port" tabindex="1">New Rate</a4j:commandLink>
							</td>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;">
								Filter by Origin Port:
								<h:selectOneMenu id="filterOrig40Port" value="#{partnersControl.clientOcean40RatePortFilter.portOrigin}" style="width:134px" tabindex="2">
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listOcean40Rates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right" style="padding:0px 50px 0px 0px;" colspan="2">
								Filter by Destination Port:
								<h:selectOneMenu id="filterDest40Port" value="#{partnersControl.clientOcean40RatePortFilter.portDestination}" style="width:134px" tabindex="3" >
									<f:selectItem itemLabel="ALL" itemValue="0" />
									<f:selectItems value="#{partnersControl.ports}" />
									<a4j:support event="onchange" action="#{partnersControl.filtrateByPorts}" reRender="listOcean40Rates"></a4j:support>
								</h:selectOneMenu>
							</td>
						</tr>
					</table>
					<div style="height: 441px; overflow: auto;">
						<rich:dataList id="listOcean40Rates" value="#{partnersControl.ocean40RatesList}" var="clRatesPorts" binding="#{partnersControl.dataListOcean40 }">
							<rich:panel >
								<table>
									<tr>
										<td>
											Origin Port:
										</td>
										<td>
											<h:selectOneMenu id="portOrigin" value="#{clRatesPorts.portOrigin}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											Destination Port:
										</td>
										<td>
											<h:selectOneMenu id="portDestin" value="#{clRatesPorts.portDestination}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.ports}" />
											</h:selectOneMenu>
										</td>
									</tr>
									<tr>
										<td>
											Carrier:
										</td>
										<td>
											<h:selectOneMenu id="carrier" value="#{clRatesPorts.carrierId}" style="width:134px" tabindex="4" >
												<f:selectItem itemLabel="-- SELECT --" itemValue="" />
												<f:selectItems value="#{partnersControl.carriers}" />
											</h:selectOneMenu>
										</td>
										<td>
											<a4j:commandButton value="Save" action="#{partnersControl.saveRateAction }" reRender="listOcean40Rates" tabindex="4"/>
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandLink onclick="#{rich:component('newOtherChargeRatePopup2')}.show()" reRender="listOcean40Rates" action="#{partnersControl.newOtherChargeRateAction }" rendered="#{clRatesPorts.clientRatePortId > 0}" tabindex="4">Add Other Charge</a4j:commandLink>
										</td>
									</tr>
								</table>
								<rich:dataTable id="Ocean40RatesDataTable" value="#{clRatesPorts.clientRates}" var="clRate" rowClasses="row1, row2" rows="20" binding="#{partnersControl.ocean40RatesDataTable}" rendered="#{clRatesPorts.clientRatePortId > 0}">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" colspan="2">
												<h:outputText value="Rate($/Kg)"></h:outputText>
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column rowspan="2">
												 <rich:spacer />
											</rich:column>
											<rich:column sortable="false" breakBefore="true">
												<h:outputText value="Offset"></h:outputText>
											</rich:column>
											<rich:column sortable="false">
												<h:outputText value="Rate"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>
									<rich:column sortable="false" label="Charge" id="col_1" width="100">
										<h:outputText value="#{clRate.chargeType.value1 }" style="font-weight:bold;"></h:outputText>
									</rich:column>
									<rich:column sortable="false" label="Offset" id="col_3" width="62">
										<h:inputText styleClass="styleInput6" value="#{clRate.rateOffset}" id="offset" style="width:67px;text-align: right" 
					                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" tabindex="4">
					                 		<a4j:support event="onchange" status="none" ajaxSingle="true"
					                 			action="#{partnersControl.calculateRate }" reRender="rate">
					                 			<f:setPropertyActionListener value="#{clRatesPorts }" target="#{partnersControl.clientRatePort}"/>
					                 			<f:setPropertyActionListener value="#{clRate }" target="#{partnersControl.clientRate}"/>
					                 		</a4j:support>
					                	</h:inputText>
									</rich:column>
									<rich:column sortable="false" label="Rate" id="col_4" width="60">
										<h:outputText styleClass="styleInput6" value="#{clRate.rate}" id="rate" style="width:60px;text-align: right">
					                	</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_9" width="40">
										&nbsp;&nbsp;
										<h:outputText value="flat" rendered="#{clRate.flat}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Full" id="col_10" width="23">
										<a4j:commandLink id="deleteRateLink" oncomplete="#{rich:component('deleteRateConfirmPopup')}.show();" status="none" 
												rendered="#{clRate.otherCharge }" action="#{partnersControl.selectToDeleteRateAction}">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
										</a4j:commandLink>
									</rich:column>
								</rich:dataTable>
							</rich:panel>
						</rich:dataList>
						<h:commandLink tabindex="4" onfocus="#{rich:element('new40RateLink')}.focus()"/>
					</div>
				</rich:tab>
			</rich:tabPanel>			
		</h:form>
</rich:modalPanel>

<rich:modalPanel id="newOtherChargeRatePopup" autosized="true" left="200" top="155" moveable="false" >
		<f:facet name="header">
			<h:outputText value="Add Other Charge Rate" style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
            <h:panelGroup>	            	
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink2"/>
                <rich:componentControl for="newOtherChargeRatePopup" attachTo="hidelink2" operation="hide" event="onclick"/>	                
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
									<th colspan="2">Rate</th>
									<th colspan="2" >minimum</th>
								</tr>
								<tr class="rich-table-header ">
									<th>
										Offset
									</th>
									<th>
										Rate
									</th>
									<th>
										Offset
									</th>
									<th>
										Min
									</th>
								</tr>
								
							</thead>
							<tr>
								<td>
									<h:selectOneMenu id="otherChargesComboBox2" value="#{partnersControl.clientRateOtherCharge.chargeType.valueId}"  style="width:140px" required="true" requiredMessage="A Other Charge item is required">
										<f:selectItem itemLabel="-- SELECT --" />
										<f:selectItems value="#{partnersControl.otherChargesList}"/>
				                   	</h:selectOneMenu>
								</td>						
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.rateOffset }" id="offsetOC" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.rate }" id="rateOC" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" onkeydown="return false">
				                	</h:inputText>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.minimumOffset }" id="minOffsetOC" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.minimumRate }" id="minOC" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" onkeydown="return false">
				                	</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="bottom" height="35">
						<a4j:commandButton value="Save" reRender="tabsRateTypes,newOtherChargeRatePopup" action="#{partnersControl.saveOtherChargeRateAction}" oncomplete="if (#{facesContext.maximumSeverity == null}){#{rich:component('newOtherChargeRatePopup')}.hide();}" ></a4j:commandButton>
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
									<th colspan="2">Rate</th>
								</tr>
								<tr class="rich-table-header ">
									<th>
										Offset
									</th>
									<th>
										Rate
									</th>
								</tr>
								
							</thead>
							<tr>
								<td>
									<h:selectOneMenu id="otherChargesComboBox" value="#{partnersControl.clientRateOtherCharge.chargeType.valueId}"  style="width:140px" required="true" requiredMessage="A Other Charge item is required">
										<f:selectItem itemLabel="-- SELECT --" />
										<f:selectItems value="#{partnersControl.otherChargesList}"/>
				                   	</h:selectOneMenu>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.rateOffset }" id="offsetOC2" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	</h:inputText>
								</td>
								<td>
									<h:inputText styleClass="styleInput6" value="#{partnersControl.clientRateOtherCharge.rate }" id="rateOC2" style="width:60px;text-align: right" 
				                 		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" onkeydown="return false">
				                	</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="bottom" height="35">
						<a4j:commandButton value="Save" reRender="tabsRateTypes,newOtherChargeRatePopup2" action="#{partnersControl.saveOtherChargeRateAction}" oncomplete="if (#{facesContext.maximumSeverity == null}){#{rich:component('newOtherChargeRatePopup2')}.hide();}"></a4j:commandButton>
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
	                            action="#{partnersControl.deleteRateAction}" 
	                            oncomplete="#{rich:component('deleteRateConfirmPopup')}.hide();"
	                            reRender="formClientRates"/>
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

</body>
</html>