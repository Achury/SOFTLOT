<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/awb.css"/>
	<script type="text/javascript" src="../../../js/general.js"></script>	
	
	<script type="text/javascript">
		function createMessage(email, subject, body_message){
			var mailto_link = 'mailto:'+email+'?subject='+subject+'&body='+body_message;

			win = window.open(mailto_link,'emailWindow');
			if (win && win.open &&!win.closed) win.close();
		}
		
		var tab_title;
		var tab_id;
		var isNew = false;
		jQuery(function() {	
			jQuery('[id$="masterNumberInput2"]').live("click", function() {	
				objInput= jQuery('[id$="openMaster_link"]');
				tab_id = objInput.attr("rel");
				tab_title = objInput.attr("title");
				parent.srcIframe = 'awb.jsp?status=existe';
				parent.addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
		});
		
		function openMaster(){
			objInput= jQuery('[id$="openMaster_link"]');
			tab_id = objInput.attr("rel");
			tab_title = objInput.attr("title");
			parent.srcIframe = 'awb.jsp?status=existe';
			parent.addTab(tab_id, tab_title);
			return false; //Evitar que el navegador siga el link
		}
		
		function refreshNameTab(name, id){
			if(isNew){
				parent.refreshTabName(name, id);
			}
		}
		
		function checkIsNew(id){
			if(id <= 0){
				isNew = true;
			}
			return true;
		}
		
		
	</script>	
	
</head>
<body onload="focusFirtsElementJs()">
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	<t:saveState value="#{awbControl.selectedTab}"/>
	<t:saveState value="#{awbControl.awbFilter}"/>
	<t:saveState value="#{awbControl.awbList}"/>
	<t:saveState value="#{awbControl.awb}"/>
	<t:saveState value="#{awbControl.locked}"/>
	<t:saveState value="#{awbControl.converterName}"/>
	<t:saveState value="#{awbControl.saidToContain}"/>
	<t:saveState value="#{awbControl.when}" />
	<t:saveState value="#{awbControl.whReceipt }"/>
	<t:saveState value="#{awbControl.awbCostSale }"/>
	<t:saveState value="#{awbControl.awbUnCode }"/>
	<t:saveState value="#{awbControl.awbEEI }"/>
	<t:saveState value="#{awbControl.invoice}"/>
	<t:saveState value="#{awbControl.awbItem}"/>
	<t:saveState value="#{awbControl.invoiceItems}"/>
	<t:saveState value="#{awbControl.freightInvoice}"/>
	<t:saveState value="#{awbControl.merchandiseInvoice}"/>
	<t:saveState value="#{awbControl.invoiceList }"/>
	<t:saveState value="#{awbControl.regionSwitch}" />
	<t:saveState value="#{awbControl.mainContactMail}"/>
	<t:saveState value="#{awbControl.awbHousesAvailableList}"/>
	<t:saveState value="#{awbControl.subjectMessage}"/>
	<t:saveState value="#{awbControl.bodyMessage}"/>
	<t:saveState value="#{awbControl.dataMasterCS}"/>
	<t:saveState value="#{awbControl.headersMasterCS}"/>
	<t:saveState value="#{awbControl.subHeadersMasterCS}"/>
	
	<h:form id="formAwb" >
		<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelDetails" style="width:1230px" >
			<a4j:jsFunction name="focusFirtsElementJs" oncomplete="#{rich:element('supplierText')}.focus();" status="none" ajaxSingle="true"/>
			<h:inputHidden value="#{awbControl.awb.awbId }"/>
			<table border="0" cellspacing="2" width="1225" height="665">
				<tr valign="top" height="40px">
					<td nowrap="nowrap" align="left" colspan="4">
						<table border="0">
							<tr>
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									AWB Int:																
									<h:inputText id="awbNumText" value="#{awbControl.awb.awbNumber}" size="8" readonly="false"/>	
					               	<h:inputHidden id="awbId" value="#{awbControl.awb.awbId}"/>
								</td>
								
								<td nowrap="nowrap" align="left" style="padding:1px 0px;">
									Completed:
								</td>
								<td  nowrap="nowrap" align="left" style="padding:1px 0px;" >
									<a4j:region id="completedCheckbox_AjxRgn">									
										<h:selectBooleanCheckbox id="completedCheckBox" value="#{awbControl.awb.completed}">
											<a4j:support event="onchange" action="#{awbControl.recalculateCostSalesHousesOfMaster}" rendered="#{awbControl.awb.master}" />
										</h:selectBooleanCheckbox>
									</a4j:region>							
								</td>	
								
								<td  nowrap="nowrap" align="right" style="padding:1px 4px;">
									AWB Date: 
									<rich:calendar id="awbDate" cellWidth="25px" inputSize="10" datePattern="MM/d/yyyy" enableManualInput="true" status="none" readonly="true"
										value="#{awbControl.awb.createdDate}" required="true" requiredMessage="- Date field is required"/>
								</td>	
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									Type:																
									<h:inputText id="awbTypeText" value="#{awbControl.awb.awbType.value1}" size="8" readonly="true"/>					               	
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									Biz unit:
									<h:selectOneMenu id="regionComboBox" value="#{awbControl.regionSwitch}" rendered="#{awbControl.awb.awbId <= 0 }" onchange="#{rich:component('switchRegionPopup')}.show()">
										<f:selectItems value="#{awbControl.regions}"/>
									</h:selectOneMenu>
									<h:inputText id="regionText" size="3" value="#{awbControl.awb.region.value1}" readonly="true" rendered="#{awbControl.awb.awbId > 0}"/>
								</td>
								<td nowrap="nowrap" align="left">
									<a4j:region id="cargoManifest_AjxRgn">																	
										<a4j:commandButton id="cargoManifest_btn" value="Cargo Manifest" action="#{awbControl.recalculateCostSalesHousesOfMaster}" rendered="#{awbControl.awb.master}"/>
									</a4j:region>	
								</td>
								<td nowrap="nowrap" align="left">														
									<a4j:commandButton id="cargoSheet_btn" value="Cargo Sheet" >
										 &nbsp;
									</a4j:commandButton>
									<a4j:commandButton value="Open House File" rendered="#{awbControl.awb.house}">
										 &nbsp;
									</a4j:commandButton>															
									<a4j:commandButton value="Open Master File">
										 &nbsp;
									</a4j:commandButton>															
									<a4j:commandButton value="Label" >
										 &nbsp;
									</a4j:commandButton>														
									<a4j:commandButton value="Print" action="#{awbControl.reportAwbAction}" status="none">
										 &nbsp;
									</a4j:commandButton>
									<a4j:commandButton value="Create Invoice">
										 &nbsp;
									</a4j:commandButton>
								</td>							
							</tr>
						</table>
						<hr align="right" width="99%" style="color:#DADADA" noshade="noshade">
					</td>
				</tr>
				<tr valign="top" height="100px">
					<td nowrap="nowrap">
						<table cellpadding="0" cellspacing="0" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right" >
									Supplier:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="supplierText" value="#{awbControl.awb.supplier.name}" onchange="setFlagModif('true');;" tabindex="1" style="width:240px" />
									<rich:suggestionbox id="supplierSuggestBox" for="supplierText" 
										suggestionAction="#{awbControl.autoCompleteSuppliers}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>		
																
										<a4j:support event="onselect" ajaxSingle="true" reRender="supplierAddressText,shipperComboBox" status="none">  
											<f:setPropertyActionListener value="#{result }" target="#{awbControl.awb.supplier}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="supplierAddressText" rows="3" value="#{awbControl.awb.supplier.address.address }
#{awbControl.awb.supplier.address.city.name }, #{awbControl.awb.supplier.address.city.stateProvince.value1 } 
#{awbControl.awb.supplier.address.postalCode } #{awbControl.awb.supplier.address.city.country.value1 }" readonly="true" style="width:240px" ></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td height="4px"/>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Shipper:
								</td>
								<td nowrap="nowrap">									
									<h:selectOneMenu id="shipperComboBox" value="#{awbControl.awb.pickupFrom.shipPickUpId}" style="width:240px" tabindex="2">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
								        <f:selectItems value="#{awbControl.pickupFromsList}" />	
								        <a4j:support event="onchange" action="#{awbControl.assignAddressToPickup }" reRender="shipperAddressText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="shipperComboBox"/>							        
								    </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="shipperAddressText" rows="3" value="#{awbControl.awb.pickupFrom.address.address }" readonly="true" style="width:240px" ></h:inputTextarea>
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
									<h:inputText id="clienteText" value="#{awbControl.awb.client.name}" onchange="setFlagModif('true');" tabindex="3" style="width:240px" />
									<rich:suggestionbox id="clientSuggestBox" for="clienteText" 
										suggestionAction="#{awbControl.autoCompleteClients}" var="result"
										tokens="," height="150" width="160" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>							
										<a4j:support event="onselect" ajaxSingle="true" reRender="clientAddressText,consigneeComboBox" status="none" oncomplete="if(#{!awbControl.awb.client.searchAndInspConsent}){#{rich:component('searchAndInspectionConsentPopup')}.show();}">  
											<f:setPropertyActionListener value="#{result}" target="#{awbControl.awb.client}" />
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="clientAddressText" rows="3"  value="#{awbControl.awb.client.address.address }
#{awbControl.awb.client.address.city.name }, #{awbControl.awb.client.address.city.stateProvince.value1 } #{awbControl.awb.client.address.postalCode }
#{awbControl.awb.client.address.city.country.value1 }" readonly="true" style="width:240px">
									</h:inputTextarea>
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
									<h:selectOneMenu id="consigneeComboBox" value="#{awbControl.awb.shipTo.shipPickUpId}" style="width:240px" tabindex="4">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
										<f:selectItems value="#{awbControl.consigneesList}" />
										<a4j:support event="onchange" action="#{awbControl.assignAddressToConsignee }" reRender="consigneeAddressText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="consigneeComboBox"/>
								    </h:selectOneMenu>
								</td>
							</tr>	
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="consigneeAddressText" rows="3" value="#{awbControl.awb.shipTo.address.address }" readonly="true" style="width:240px" ></h:inputTextarea>
								</td>
							</tr>						
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0" border="0" >
							<tr>
								<td nowrap="nowrap" align="right" >
									Airline:
								</td>
								<td colspan="3">
									<h:selectOneMenu id="carrierCombobox" value="#{awbControl.awb.carrier.carrierId }"  style="width:168px" tabindex="5">
										<f:selectItem itemLabel="" itemValue="" />
										<f:selectItems value="#{awbControl.carriersList}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="middle">
									<a4j:commandLink value="Get AWB#" action="#{awbControl.loadAwbNumberFromCarriers }" reRender="awbFullNumberExtern" title="Get AWB Number" />
									&nbsp;&nbsp;&nbsp;
									AWB:
								</td>
								<td nowrap="nowrap">
									<h:inputText value="#{awbControl.awb.carrier.carrierCode}" onkeydown="return false" style="width:33px"/>
									<h:inputText id="awbFullNumberExtern" style="width:131px" value="#{awbControl.awb.awbFullNumberExtern }"  tabindex="6"></h:inputText>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" >
									<h:outputText value="AWB Master Int #:" rendered="#{awbControl.awb.house}" />
								</td>
								<td nowrap="nowrap">
									<a4j:commandLink id="openMaster_link"  rel="#{awbControl.awb.awbMaster.awbId}" title="#{awbControl.awb.awbMaster.awbNumber}" />
									<h:inputText id="masterNumberInput" rendered="#{awbControl.awb.house}" style="width:165px" value="#{awbControl.awb.awbMaster.awbNumber }" 
										onkeydown="if (event.keyCode == 8) {clearAwbMasterIdJS();}" title="#{awbControl.awb.awbMaster.awbId}" >
										<a4j:support event="ondblclick" actionListener="#{awbControl.openAwbMasterAction}" rendered="#{awbControl.awb.awbMaster.awbId > 0}" status="none" oncomplete="openMaster()"/>
					
									</h:inputText>
									<rich:suggestionbox id="masterNumberSuggestBox" for="masterNumberInput" 
										suggestionAction="#{awbControl.autoCompleteAwb}" var="result"
										tokens="," height="200" width="70" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.awbNumber}" style="font-style:bold" />
										</h:column>
										<a4j:support event="onselect" ajaxSingle="true" status="none">  
											<f:setPropertyActionListener value="#{result}" target="#{awbControl.awb.awbMaster}"/>
										</a4j:support>
									</rich:suggestionbox>
									<a4j:jsFunction name="clearAwbMasterIdJS" status="none" ajaxSingle="true" reRender="openMaster_link">
										<f:setPropertyActionListener value="0" target="#{awbControl.awb.awbMaster.awbId}"/>
									</a4j:jsFunction>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" >
									Orig. Airport:
								</td>
								<td>
									<h:selectOneMenu id="airportOrigin" value="#{awbControl.awb.airportOrigin.portId}" style="width:168px" tabindex="7" >
										<f:selectItem itemLabel="All" itemValue="" />
										<f:selectItems value="#{awbControl.airportsList}" />
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" >
									Dest. Airport:
								</td>
								<td>
									<h:selectOneMenu id="airportDest" value="#{awbControl.awb.airportDestination.portId}" style="width:168px" tabindex="8" >
										<f:selectItem itemLabel="All" itemValue="" />
										<f:selectItems value="#{awbControl.airportsList}" />
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Client PO #:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="clientPoText" style="width:165px" value="#{awbControl.awb.clientPoNumber}"  tabindex="9"></h:inputText>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Freight Inv:
								</td>
								<td nowrap="nowrap" >
									<h:inputText id="freightInvText" readonly="true" style="background-color:#FFFFFF; color:#000000; width:165px" value="#{awbControl.awb.concatenatedFreightInvoices}"
										ondblclick="if(#{awbControl.awb.awbId} > 0) openFreightInvJs();" />
									<a4j:jsFunction name="openFreightInvJs" action="#{awbControl.loadFreightInvoicesAction}" oncomplete="#{rich:component('freightInvPopup') }.show()" reRender="freightInvDataTable"/>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Merchandise Inv:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="merchandInvText" value="#{awbControl.awb.concatenatedMerchandInvoices }" readonly="true" style="background-color:#FFFFFF; color:#000000; width:165px"
										ondblclick="if(#{awbControl.awb.awbId} > 0) openMerchandInvJs();"/>
									<a4j:jsFunction name="openMerchandInvJs" action="#{awbControl.loadMerchandInvoicesAction}" oncomplete="#{rich:component('MerchandInvPopup') }.show()" reRender="merchandInvDataTable"/>
								</td>
							</tr>
							
							<tr>
								<td nowrap="nowrap" align="right">
									Sales Rep:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="salesRepCombobox" value="#{awbControl.awb.salesRep.employeeId }" style="width:168px" 
											required="true" requiredMessage="- Sales Rep Field is required" onchange="setFlagModif('true');" tabindex="10">
										<f:selectItem itemLabel="" itemValue="" />
										<f:selectItems value="#{awbControl.employeesList}"/>
			                        </h:selectOneMenu>
								</td>
							</tr>								
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td colspan="2">
									NOTIFICATIONS
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="left" style="padding:0px 1px;" >	
									<table>
										<tr>
											<a4j:jsFunction name="invokeMailtoJS" oncomplete="createMessage('#{awbControl.mainContactMail.email}', '#{awbControl.subjectMessage}', '#{awbControl.bodyMessage}')" ajaxSingle="true"/>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >	
												<h:selectBooleanCheckbox id="shipmentNotifCheckbox" value="#{awbControl.awb.shipmentNotification}" tabindex="11" onclick="return false"/>		 
												<a4j:commandLink value="Shimptment" action="#{awbControl.loadShipmentNotifAction }" oncomplete="invokeMailtoJS()" ajaxSingle="true" reRender="shipmentNotifCheckbox"/>
											</td>
											<td rowspan="3" >
											</td>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox id="docsDelNotifCheckbox" value="#{awbControl.awb.docsDeliveryNotification}" tabindex="14" onclick="return false"/>
												<a4j:commandLink value="Docs. Delivery" action="#{awbControl.loadDocsDeliveryNotifAction }" oncomplete="invokeMailtoJS()" ajaxSingle="true" reRender="docsDelNotifCheckbox"/>
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox id="arrivalNotifCheckbox" value="#{awbControl.awb.arrivalNotification}" tabindex="12" onclick="return false"/>
												<a4j:commandLink value="Arrival" action="#{awbControl.loadArrivalNotifAction }" oncomplete="invokeMailtoJS()"ajaxSingle="true" reRender="arrivalNotifCheckbox"/>
											</td>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >		
												<h:selectBooleanCheckbox id="shipDelNotifCheckbox" value="#{awbControl.awb.shipmentDeliveryNotification}" tabindex="15" onclick="return false"/>
												<a4j:commandLink value="Ship. Delivery" action="#{awbControl.loadShipDeliveryNotifAction }" oncomplete="invokeMailtoJS()"ajaxSingle="true" reRender="shipDelNotifCheckbox"/>									               	
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox value="" tabindex="13" style="color: red"/>
												<h:outputText value="POE" />
											</td>
										</tr>
									</table>
								</td>																	
							</tr>
							<tr valign="bottom">
								<td nowrap="nowrap" align="left" style="padding:0px 1px;" >	
									Remarks:
								</td>
							</tr>
							<tr valign="bottom">
								<td nowrap="nowrap" style="padding:0px 1px;">
									<h:inputTextarea id="remarksText" rows="1" value="#{awbControl.awb.description}"  style="width:165px" tabindex="16"
										ondblclick="openPopupEdit(#{rich:element('remarksText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
											#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()" />
								</td>								
							</tr>
							<tr valign="bottom">
								<td nowrap="nowrap" align="left" style="padding:0px 1px;" >		
									WH. Remarks:					               	
								</td>									
							</tr>
							<tr valign="bottom" style="padding:0px 1px;">
								<td nowrap="nowrap" align="left" style="padding:0px 1px;" >		
									<h:inputTextarea id="whRemarksText" value="#{awbControl.awb.whRemarks}" rows="1"  style="width:165px" tabindex="17" readonly="true"
									ondblclick="openPopupEdit(#{rich:element('whRemarksText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
											#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup')}.disabled=true" />							               	
								</td>									
							</tr>
						</table>
					</td>
				</tr>					
				<tr valign="top" height="10px">
					<td colspan="4" align="center" >
						<table style="border-collapse: separate; border-spacing:  26px 0px;">
							<tr>
								<td>
									<a4j:commandButton id="saveButton" value="Save" actionListener="#{awbControl.saveAwbAction}" reRender="tabsAwb,formAwb,unCodesDataTable" data="#{facesContext.maximumSeverity.ordinal ge 2}"
										oncomplete="if (data != false){if(!#{awbControl.awb.client.searchAndInspConsent}){#{rich:component('searchAndInspectionConsentPopup')}.show();}else{#{rich:component('messagesPanel')}.show()}}else{refreshNameTab('#{awbControl.awb.awbNumber}' , '#{awbControl.awb.awbId}')}" status="ajaxStatus" tabindex="18" onclick="checkIsNew(#{awbControl.awb.awbId});"></a4j:commandButton>
									<a4j:commandLink value="Prorratear" action="#{awbControl.prorateAwbAction}" title="don't click me" reRender="freightInvDataTable" status="none"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>						
				<tr valign="top" height="335px">
					<td colspan="8">
						<a4j:jsFunction name="refreshCostSales" reRender="formAwb:awbCostSalesTab:costSalesDataTable,formAwb:awbCostSalesTab:OthercostSalesDataTable" status="none" ajaxSingle="true"/>
						<rich:tabPanel id="tabsAwb" switchType="client" contentStyle="padding:0px; border-spacing:0px; "
							selectedTab="#{awbControl.selectedTab}">
							
							<rich:tab label="House AWB's" name="tabHouseAwb" status="none" disabled="#{awbControl.awb.awbId <= 0}" rendered="#{awbControl.awb.master}">
								<a4j:include id="awbHousesTab" viewId="../../../jsp/LODepartment/awb/awbHousesTab.jsp" />
							</rich:tab>
							
							<rich:tab label="Items" name="tabItems" status="none" disabled="#{awbControl.awb.awbId <= 0}" rendered="#{!awbControl.awb.master}">
								<a4j:include id="awbItemsTab" viewId="../../../jsp/LODepartment/awb/awbItemsTab.jsp"/>
							</rich:tab>
							
							<rich:tab label="Cost & Sales" name="tabCostSales" status="none" disabled="#{awbControl.awb.awbId <= 0}" ontabenter="refreshCostSales()">
							 	<a4j:include id="awbCostSalesTab" viewId="../../../jsp/LODepartment/awb/awbCostSalesTab.jsp"/>
							</rich:tab>
							
							<rich:tab label="More Info" name="tabMoreInfo" status="none" disabled="#{awbControl.awb.awbId <= 0}">					
								<a4j:include id="awbMoreInfoTab" viewId="../../../jsp/LODepartment/awb/awbMoreInfoTab.jsp"/>
							</rich:tab>
							
							<rich:tab label="Product Info" name="tabProdlInfo" status="none" disabled="#{awbControl.awb.awbId <= 0}">
								product info
							</rich:tab>
							
						</rich:tabPanel>	
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding:0px 1px;">
						<table height="80px" >
							<tr >
								<td nowrap="nowrap" valign="top">
									Total Pieces:
								</td>
								<td valign="top">
									<h:inputText id="totalPiecesText" value="#{awbControl.awb.pieces }" readonly="true" style="width: 40px;text-align: right;background: #FFFFFF;color: #000000" />
								</td>
								<td>
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td valign="top">
									<div style="position: relative;">
										<div class="headerPanel" align="center" style="background-color : #F3F8FC; top: -7%; width: 73%; border-radius:8px" >Total Real Weight</div>
										<rich:panel styleClass="stylefilterDatePanel" style="background-color : #{awbControl.awb.totalWeightLbs > awbControl.awb.totalWeightVol ? '#95C5EC' : '#F3F8FC'}; border-radius : 8px; width : 140px;" >
											<table cellpadding="1" cellspacing="1" border="0" >	
												<tr height="30px" valign="bottom">
													<td nowrap="nowrap" align="left">
														Lbs.:
													</td>
													<td nowrap="nowrap" align="left">
														<h:inputText id="totalWeightLbsText" value="#{awbControl.awb.totalWeightLbs }" readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align: right" >
															<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
														</h:inputText>
													</td>
												</tr>
												<tr>
													<td nowrap="nowrap" align="left">
														Kgs.:
													</td>
													<td nowrap="nowrap" align="left">
														<h:inputText id="totalWeightKgsText" value="#{awbControl.awb.totalWeightKgs}" readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align: right">
															<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="0" minFractionDigits="0" />
														</h:inputText>
													</td>
												</tr>																		
											</table>
										</rich:panel>
									</div>	
								</td>
								<td>
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td valign="top">
									<div style="position: relative;">
										<div class="headerPanel" align="center" style="background-color : #F3F8FC; top: -8%; width: 77%; border-radius:8px" >Total Air Vol Weight</div>
										<rich:panel styleClass="stylefilterDatePanel" style="background-color : #{awbControl.awb.totalWeightVol > awbControl.awb.totalWeightLbs ? '#95C5EC' : '#F3F8FC'}; border-radius:8px;width:140px;" >
											<table cellpadding="1" cellspacing="1" border="0">	
												<tr height="30px" valign="bottom">
													<td nowrap="nowrap" align="left">
														Vol. WT[lb]:
													</td>
													<td nowrap="nowrap" align="left">
														<h:inputText id="totalVolLbsText" value="#{awbControl.awb.totalWeightVol }" readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align: right">
															<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
														</h:inputText>
													</td>
												</tr>
												<tr>
													<td nowrap="nowrap" align="left">
														Vol. WT[kg]:
													</td>
													<td nowrap="nowrap" align="left">
														<h:inputText id="totalVolKgsText" value="#{awbControl.awb.totalWeightVolKgs}" readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align: right"> 
															<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="0" minFractionDigits="0" />
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
				</tr>
			</table>
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
					                <a4j:commandButton value="Yes" id="yesRegButton" action="#{awbControl.changeRegionYesAction }"  
					                    oncomplete="#{rich:component('switchRegionPopup')}.hide()" reRender="regionComboBox" process="regionComboBox"
					                    status="none" tabindex="1" ajaxSingle="true"/>
				                </td>
				                <td align="center" width="50px;">
				                	<a4j:commandButton value="No" action="#{awbControl.changeRegionNoAction }"
					                     oncomplete="#{rich:component('switchRegionPopup')}.hide();"  reRender="regionComboBox"
					                     status="none" tabindex="2" ajaxSingle="true" immediate="true" />
				                </td>
		            		</tr>
		            	</table>
		            </td>
	            </tr>
	        </tbody>
	    </table>
	</rich:modalPanel>
	</h:form> 
	
	
	<a4j:include id="loadingPopup" viewId="../../../jsp/messages/loadingPopup.jsp" />
	<a4j:include id="messagesPanelCl" viewId="../../../jsp/businessPartners/messagesPopup.jsp" />
	<a4j:include id="awbPopups" viewId="../../../jsp/LODepartment/awb/awbPopups.jsp" />
	
	<a4j:status for="cargoManifest_AjxRgn" onstart="#{rich:component('waitPanelUpdatingHouses')}.show();" onstop="#{rich:component('waitPanelUpdatingHouses')}.hide();rerenderFormJS()" />
	<a4j:status for="completedCheckbox_AjxRgn" onstart="#{rich:component('waitPanelUpdatingHouses')}.show();" onstop="#{rich:component('waitPanelUpdatingHouses')}.hide();rerenderFormJS()" />
	<rich:modalPanel id="waitPanelUpdatingHouses" autosized="true" width="125">
		<table>
			<tr>
				<td align="center" nowrap="nowrap">
					<h:outputText value="Please wait..."
						style="font-weight:bold;font-size:small" />
				</td>
			</tr>
			<tr>
				<td align="center"  nowrap="nowrap">
					<h:outputText value="AWB Houses inside this Master are being updated"
						style="font-weight:bold;font-size:small" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<h:graphicImage
						value="/css/images/loading.gif" style="border:0" />
				</td>
			</tr>
		</table>
	</rich:modalPanel>
	
	
	
</body>
</html>
</f:view>