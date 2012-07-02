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
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/awb.css"/>		
	<script type="text/javascript" src="../../../js/general.js"></script>	
	
	<script type="text/javascript">
		function createMessage(email, subject, body_message){
			//var body_message = "%0D%0D%0D%0DThank you "+name+" for submitting this error to us. Please tell us in the space above, what you were doing when the error occurred.%0D%0DReferring Page: "+daReferrer+" %0D%0DException Error Message:%0D-------------------------------------------%0D"+errorMsg+"line1`%0Aline2";

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
				parent.srcIframe = 'bl.jsp?status=existe';
				parent.addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
		});
		
		function openMaster(){
			objInput= jQuery('[id$="openMaster_link"]');
			tab_id = objInput.attr("rel");
			tab_title = objInput.attr("title");
			parent.srcIframe = 'bl.jsp?status=existe';
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
	<t:saveState value="#{blControl.selectedTab}"/>
	<t:saveState value="#{blControl.blFilter }"/>
	<t:saveState value="#{blControl.bls }"/>
	<t:saveState value="#{blControl.bl }"/>
	<t:saveState value="#{blControl.locked}"/>
	<t:saveState value="#{blControl.converterName}"/>
	<t:saveState value="#{blControl.saidToContain}"/>
	<t:saveState value="#{blControl.when}" />	
	<t:saveState value="#{blControl.whReceipt}"/>	
	<t:saveState value="#{blControl.blCostSale}"/>
	<t:saveState value="#{blControl.blUnCode}" />
	<t:saveState value="#{blControl.blEEI}" />
	<t:saveState value="#{blControl.invoice}"/>
	<t:saveState value="#{blControl.blItem}"/>
	<t:saveState value="#{blControl.invoiceItems}"/>
	<t:saveState value="#{blControl.freightInvoice}" />
	<t:saveState value="#{blControl.merchandiseInvoice}" />
	<t:saveState value="#{blControl.invoiceList }" />
	<t:saveState value="#{blControl.regionSwitch}" />
	
	<t:saveState value="#{blControl.blHousesAvailableList }" />
	<t:saveState value="#{blControl.blContainer}"/>
	<t:saveState value="#{blControl.subjectMessage}"/>
	<t:saveState value="#{blControl.bodyMessage}"/>
	<t:saveState value="#{blControl.mainContactMail}"/>
	<t:saveState value="#{blControl.headersMasterCS}"/>
	<t:saveState value="#{blControl.subHeadersMasterCS}"/>
	<t:saveState value="#{blControl.dataMasterCS}"/>
	
	<h:form id="formBl">
		<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelDetails" style="width:1230px">
			<a4j:jsFunction name="focusFirtsElementJs" oncomplete="#{rich:element('supplierText')}.focus();" status="none" ajaxSingle="true"/>
			<h:inputHidden value="#{blControl.bl.blId }"/>
			<table border="0" cellspacing="2" width="1225" height ="665">
				<tr>
					<td nowrap="nowrap" align="left" colspan="5">
						<table border="0">
							<tr>
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									Bl Int:																
									<h:inputText id="orderNumText" value="#{blControl.bl.blNumber}" size="8" readonly="false" tabindex="1"/>	
					               	<h:inputHidden id="clieOrdId" value="#{blControl.bl.blId }"/>
					               	
								</td>
								
								<td nowrap="nowrap" align="left" style="padding:1px 0px;">
									Completed:
								</td>
								<td  nowrap="nowrap" align="left" style="padding:1px 0px;">									
									<h:selectBooleanCheckbox id="completedChk" value="#{blControl.bl.completed}"  tabindex="2"  />									
								</td>	
								
								<td  nowrap="nowrap" align="right" style="padding:1px 4px;">
									Bl Date: 
									<rich:calendar id="blDate" cellWidth="25px" inputSize="10" datePattern="MM/d/yyyy" enableManualInput="true" status="none" readonly="true"
										value="#{blControl.bl.createdDate }" required="true" requiredMessage="- Order Date field is required" tabindex="3"/>
								</td>	
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									Type:																
									<h:inputText id="typeText" value="#{blControl.bl.blType.value1}" size="8" readonly="true" tabindex="4"/>						               	
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 4px;">
									Biz unit:
									<h:selectOneMenu id="regionComboBox" value="#{blControl.regionSwitch}" rendered="#{blControl.bl.blId <= 0 }" onchange="#{rich:component('switchRegionPopup')}.show()" tabindex="5">
										<f:selectItems value="#{blControl.regionsList}"/>
									</h:selectOneMenu>
									<h:inputText id="regionText" size="3" value="#{blControl.bl.region.value1}" readonly="true" rendered="#{blControl.bl.blId > 0}" tabindex="5"/>
								</td>									
																	
								<td nowrap="nowrap" align="left" style="padding:0px 1px;">
									Shipping Type:
								</td>
								<td nowrap="nowrap" align="right" style="padding:1px 4px;" >	
									<h:selectOneRadio id="ShippingTypeRadio" layout="lineDirection" value="#{blControl.bl.blShippingType.valueId}" onchange="if(#{blControl.bl.blId} > 0 ){#{rich:element('saveButton') }.click()}" tabindex="6">
									 	<f:selectItems value="#{blControl.blShippingTypeList }"/>	 		
									</h:selectOneRadio>	
								</td>
								
								<td nowrap="nowrap" align="left">
									<a4j:region id="cargoManifest_AjxRgn">																	
										<a4j:commandButton id="cargoManifest_btn" value="C. Manifest" action="#{blControl.recalculateCostSalesHousesOfMaster}" rendered="#{blControl.bl.master}" reRender="formBl" tabindex="7"/>
									</a4j:region>	
								</td>								
								<td nowrap="nowrap" align="left" style="padding:1px 2px;" >																
									<a4j:commandButton value="Open File"  tabindex="8" rendered="false"/>
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 2px;">																
									<a4j:commandButton value="Label" action="#{blControl.reportLabelAction}" status="none" tabindex="9"/>									
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 2px;">																
									<a4j:commandButton value="Print" action="#{blControl.reportBlAction}" status="none" tabindex="10" />									
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 2px;">																
									<a4j:commandButton value="Create Invoice" tabindex="11"/>
								</td>	
								
								<td nowrap="nowrap" align="left" style="padding:1px 0px;">
									Draft
								</td>	
								<td  nowrap="nowrap" align="left" style="padding:1px 0px;">									
									<h:selectBooleanCheckbox id="draftChk" value="#{blControl.bl.printDraft}"  tabindex="12"  />									
								</td>													
							</tr>
						</table>
						<hr align="right" width="99%" style="color:#DADADA" noshade="noshade">
					</td>
				</tr>
				<tr valign="top" height="190px">
					<td nowrap="nowrap">
						<table cellpadding="0" cellspacing="0" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right" >
									Supplier:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="supplierText" value="#{blControl.bl.supplier.name}" required="true" requiredMessage="- Supplier field is required" onchange="setFlagModif('true');"  style="width:244px" tabindex="13"/>
									<rich:suggestionbox id="supplierSuggestBox" for="supplierText" 
										suggestionAction="#{blControl.autoCompleteSuppliers}" var="result"
										tokens="," height="150" width="244" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>								
										<a4j:support event="onselect" ajaxSingle="true" reRender="supplierAddressText,shipperComboBox" status="none">  
											<f:setPropertyActionListener value="#{result }" target="#{blControl.bl.supplier}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="supplierAddressText" rows="3" value="#{blControl.bl.supplier.address.address }
#{blControl.bl.supplier.address.city.name }, #{blControl.bl.supplier.address.city.stateProvince.value1 } 
#{blControl.bl.supplier.address.postalCode } #{blControl.bl.supplier.address.city.country.value1 }" readonly="true" style="width:244px" tabindex="14"></h:inputTextarea>
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
									<h:selectOneMenu id="shipperComboBox" value="#{blControl.bl.pickupFrom.shipPickUpId}" style="width:247px" 
											required="true" requiredMessage="- Pickup From field is required" tabindex="15">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
								        <f:selectItems value="#{blControl.pickupFroms}" />	
								        <a4j:support event="onchange" action="#{blControl.assignAddressToPickup }" reRender="shipperAddressText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="shipperComboBox"/>							        
								    </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="shipperAddressText" rows="3" value="#{blControl.bl.pickupFrom.address.address }" readonly="true" style="width:244px" tabindex="16"></h:inputTextarea>
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
									<h:inputText id="clienteText" value="#{blControl.bl.client.name}" required="true" requiredMessage="- Client field is required" onchange="setFlagModif('true');" tabindex="17" style="width:244px"/>
									<rich:suggestionbox id="clientSuggestBox" for="clienteText" 
										suggestionAction="#{blControl.autoCompleteClients}" var="result"
										tokens="," height="150" width="244" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none">
										<h:column>
											<h:outputText value="#{result.name}" style="font-style:bold" />
										</h:column>								
										<a4j:support event="onselect" ajaxSingle="true" reRender="clientAddressText,consigneeComboBox" status="none" oncomplete="if(#{!blControl.bl.client.searchAndInspConsent}){#{rich:component('searchAndInspectionConsentPopup')}.show();}">  
											<f:setPropertyActionListener value="#{result}" target="#{blControl.bl.client}" />
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea id="clientAddressText" rows="3"  value="#{blControl.bl.client.address.address }
#{blControl.bl.client.address.city.name }, #{blControl.bl.client.address.city.stateProvince.value1 } #{blControl.bl.client.address.postalCode }
#{blControl.bl.client.address.city.country.value1 }" readonly="true" style="width:244px" tabindex="18"></h:inputTextarea>
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
									<h:selectOneMenu id="consigneeComboBox" value="#{blControl.bl.shipTo.shipPickUpId}" style="width:247px"
											required="true" requiredMessage="- Consignee field is required" tabindex="19">
										<f:selectItem itemValue="" itemLabel="--SELECT--"/>
										<f:selectItems value="#{blControl.shipTos}" />
										<a4j:support event="onchange" action="#{blControl.assignShipToInformationToBlAction}" reRender="consigneeAddressText, notifyPartyText" status="none" oncomplete="setFlagModif('true');" ajaxSingle="true" process="consigneeComboBox"/>
								    </h:selectOneMenu>
								</td>
							</tr>	
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea id="consigneeAddressText" rows="3" value="#{blControl.bl.shipTo.address.address }" readonly="true" style="width:244px" tabindex="20"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="right" >
									<table style="border-collapse: separate; border-spacing:  4px 10px;">
										<tr>
											<td>
												<a4j:commandButton id="saveButton" value="Save" actionListener="#{blControl.saveBlAction }" reRender="tabsBl,formBl,unCodesDataTable" data="#{facesContext.maximumSeverity.ordinal ge 2}"
													oncomplete="if (data != false){if(!#{blControl.bl.client.searchAndInspConsent}){#{rich:component('searchAndInspectionConsentPopup')}.show();}else{#{rich:component('messagesPanel')}.show()}}else{refreshNameTab('#{blControl.bl.blNumber}' , '#{blControl.bl.blId}')}" tabindex="41"
													status="ajaxStatus" onclick="checkIsNew(#{blControl.bl.blId});"></a4j:commandButton>
													<a4j:commandLink value="prorate" action="#{blControl.prorateBlAction}" status="none"></a4j:commandLink>												
											</td>								
										</tr>
									</table>
								</td>		
							</tr>				
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td nowrap="nowrap" align="right" valign="top" >
									Carrier:
								</td>
								<td colspan="3">
									<h:selectOneMenu id="carrierCombobox" value="#{blControl.bl.carrier.carrierId }" style="width:148px" tabindex="21">
										<f:selectItem itemLabel="All" itemValue="" />
										<f:selectItems value="#{blControl.carriers}"/>
			                        </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Booking:
								</td>
								<td nowrap="nowrap">
									<h:inputText style="width:145px" value="#{blControl.bl.booking }"  tabindex="22"></h:inputText>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									BL:
								</td>
								<td nowrap="nowrap">
									<h:inputText style="width:145px" value="#{blControl.bl.fullBlNumber }"  tabindex="23"></h:inputText>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" >
									<h:outputText value="BL Master #:" rendered="#{blControl.bl.house}"/>
								</td>
								<td nowrap="nowrap">
									<a4j:commandLink id="openMaster_link"  rel="#{blControl.bl.blMaster.blId}" title="#{blControl.bl.blMaster.blNumber}" />
									<h:inputText id="masterNumberInput" rendered="#{blControl.bl.house}" style="width:145px" value="#{blControl.bl.blMaster.blNumber }" 
										onkeydown="if (event.keyCode == 8) {clearBlMasterIdJS();}" title="#{blControl.bl.blMaster.blId}" tabindex="24">
										<a4j:support event="ondblclick" actionListener="#{blControl.openBlMasterAction}" rendered="#{blControl.bl.blMaster.blId > 0}" status="none" oncomplete="openMaster()"/>
									</h:inputText>
									
									<rich:suggestionbox id="masterNumberSuggestBox" for="masterNumberInput" 
										suggestionAction="#{blControl.autoCompleteBl}" var="result"
										tokens="," height="200" width="70" cellpadding="2"
										cellspacing="2"   shadowOpacity="4" shadowDepth="4"
										minChars="3" rules="none" nothingLabel="no matches found"
										status="none" >
										<h:column>
											<h:outputText value="#{result.blNumber}" style="font-style:bold" />
										</h:column>
										<a4j:support event="onselect" ajaxSingle="true" status="none">  
											<f:setPropertyActionListener value="#{result }" target="#{blControl.bl.blMaster}"/>
										</a4j:support>
									</rich:suggestionbox>
									<a4j:jsFunction name="clearBlMasterIdJS" status="none" ajaxSingle="true">
										<f:setPropertyActionListener value="0" target="#{blControl.bl.blMaster.blId}"/>
									</a4j:jsFunction>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Place of Receipt:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="placeOfReceiptText" style="width:145px" value="#{blControl.bl.placeOfReceipt.name }" onchange="setFlagModif('true');" tabindex="25"></h:inputText>
									<rich:suggestionbox id="placeRecSuggestBox" for="placeOfReceiptText" 
										suggestionAction="#{blControl.autocompleteCity}" var="result"
										tokens="," height="150" width="140" cellpadding="2"
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
											<f:setPropertyActionListener value="#{result.cityId }" target="#{blControl.bl.placeOfReceipt.cityId}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Port of Loading:
								</td>
								<td colspan="3">
									<h:selectOneMenu id="OriginPortCombobox" value="#{blControl.bl.portOrigin.portId }" style="width:148px" tabindex="26" >
										<f:selectItem itemLabel="All" itemValue="" />
										<f:selectItems value="#{blControl.ports}"/>
			                        </h:selectOneMenu>								
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top" >
									Port of Discharge:
								</td>
								<td colspan="3">
									<h:selectOneMenu id="DestPortCombobox" value="#{blControl.bl.portDestination.portId }" style="width:148px" tabindex="27">
										<f:selectItem itemLabel="All" itemValue="" />
										<f:selectItems value="#{blControl.ports}"/>
			                        </h:selectOneMenu>										
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Place of Delivery:
								</td>
								
								<td nowrap="nowrap">
									<h:inputText id="placeOfDeliveryText" style="width:145px" value="#{blControl.bl.placeOfDelivery.name }" onchange="setFlagModif('true');" tabindex="28"></h:inputText>
									<rich:suggestionbox id="placeDelSuggestBox" for="placeOfDeliveryText" 
										suggestionAction="#{blControl.autocompleteCity}" var="result"
										tokens="," height="150" width="140" cellpadding="2"
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
											<f:setPropertyActionListener value="#{result.cityId }" target="#{blControl.bl.placeOfDelivery.cityId}"/>
										</a4j:support>
									</rich:suggestionbox>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top" >
									Notify Party:
								</td>
								<td nowrap="nowrap" colspan="2">
									<h:inputTextarea id="notifyPartyText" rows="2" value="#{blControl.bl.notifyParty}"  style="width:145px" tabindex="29"
									ondblclick="openPopupEdit(#{rich:element('notifyPartyText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
									#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup')}"></h:inputTextarea>
								</td>
							</tr>							
								
						</table>
					</td>											
					<td valign="top">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Client PO #:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="clientPoText" style="width:145px" value="#{blControl.bl.clientPoNumber }"  tabindex="30"></h:inputText>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Freight Inv:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="freightInvText" readonly="true" style="background-color:#FFFFFF; color:#000000; width:145px" value="#{blControl.bl.concatenatedFreightInvoices}"
										ondblclick="if(#{blControl.bl.blId} > 0) openFreightInvJs();" tabindex="31"/>
									<a4j:jsFunction name="openFreightInvJs" action="#{blControl.loadFreightInvoicesAction}" oncomplete="#{rich:component('freightInvPopup') }.show()" reRender="freightInvDataTable"/>									
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Merch. Inv:
								</td>
								<td nowrap="nowrap">
									<h:inputText id="merchandInvText" value="#{blControl.bl.concatenatedMerchandInvoices }" readonly="true" style="background-color:#FFFFFF; color:#000000; width:145px"
									ondblclick="if(#{blControl.bl.blId} > 0) openMerchandInvJs();" tabindex="32"/>
									<a4j:jsFunction name="openMerchandInvJs" action="#{blControl.loadMerchandInvoicesAction}" oncomplete="#{rich:component('MerchandInvPopup') }.show()" reRender="merchandInvDataTable"/>
								</td>
							</tr>							
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									Sales Rep:
								</td>
								<td nowrap="nowrap">
									<h:selectOneMenu id="salesRepCombobox" value="#{blControl.bl.salesRep.employeeId }" style="width:148px" 
											required="true" requiredMessage="- Sales Rep Field is required" onchange="setFlagModif('true');" tabindex="33">
										<f:selectItem itemLabel="" itemValue="" />
										<f:selectItems value="#{blControl.employees}"/>
			                        </h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top" >
									Remarks:
								</td>
								<td nowrap="nowrap" colspan="2">
									<h:inputTextarea id="remarksText" rows="2" value="#{blControl.bl.remarks}"  style="width:145px" tabindex="34" 
									ondblclick="openPopupEdit(#{rich:element('remarksText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
									#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup')}"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right" valign="top" >		
									WH. Remarks:					               	
								</td>									
							
								<td nowrap="nowrap" colspan="2">		
									<h:inputTextarea id="whRemarksText"  value="#{blControl.bl.whRemarks}" rows="2" style="width:145px" tabindex="35" readonly="true"
									ondblclick="openPopupEdit(#{rich:element('whRemarksText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
									#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup')}.disabled=true"></h:inputTextarea>							               	
								</td>									
							</tr>													
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td nowrap="nowrap" align="Center" valign="top">
									NOTIFICATIONS
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="left" style="padding:0px 1px;" >	
									<table>
										<tr>
											<a4j:jsFunction name="invokeMailtoJS" oncomplete="createMessage('#{blControl.mainContactMail.email}', '#{blControl.subjectMessage}', '#{blControl.bodyMessage}')" ajaxSingle="true"/>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >	
												<h:selectBooleanCheckbox id="shipmentNotifCheckbox" value="#{blControl.bl.shipmentNotif}" onclick="return false" tabindex="36" />		 
												<a4j:commandLink value="Shipment" action="#{blControl.loadShipmentNotifAction }" oncomplete="invokeMailtoJS()" ajaxSingle="true" reRender="shipmentNotifCheckbox"/>										
											</td>
										</tr>
										<tr>
											
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox id="arrivalNotifCheckbox" value="#{blControl.bl.arrivalNotif}"  onclick="return false" tabindex="37"/>
												<a4j:commandLink value="Arrival" action="#{blControl.loadArrivalNotifAction }" oncomplete="invokeMailtoJS()"ajaxSingle="true" reRender="arrivalNotifCheckbox"/>
											</td>
											
										</tr>
										<tr>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox id="docsDelNotifCheckbox" value="#{blControl.bl.docsDelNotif }"  onclick="return false" tabindex="38"/>
												<a4j:commandLink value="Docs. Delivery" action="#{blControl.loadDocsDeliveryNotifAction }" oncomplete="invokeMailtoJS()" ajaxSingle="true" reRender="docsDelNotifCheckbox"/>
											</td>
										<tr>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >		
												<h:selectBooleanCheckbox id="shipDelNotifCheckbox" value="#{blControl.bl.shipDelNotif}"  onclick="return false" tabindex="39"/>
												<a4j:commandLink value="Ship. Delivery" action="#{blControl.loadShipDeliveryNotifAction }" oncomplete="invokeMailtoJS()"ajaxSingle="true" reRender="shipDelNotifCheckbox"/>									               	
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="left" style="padding:0px 1px;" >
												<h:selectBooleanCheckbox value="" tabindex="40" style="color: red"/>
												<h:outputText value="POE" />
											</td>
										</tr>
									</table>
								</td>
							</tr>							
						</table>
					</td>										
				</tr>					
				
				<tr valign="top" height="290px" >
					<td colspan="8">
						<rich:tabPanel id="tabsBl" switchType="client" contentStyle="padding:0px; border-spacing:0px; "
							selectedTab="#{blControl.selectedTab}" >
							
							<rich:tab label="House BL's" name="tabHouseBl"  status="none"  disabled="#{blControl.bl.blId <= 0}"  rendered="#{blControl.bl.master}" ontabenter="changeTabHousesJs();" >
								<a4j:include id="blHousesTab" viewId="../../../jsp/LODepartment/bl/blHousesTab.jsp" style="min-width: 1200px"/>
							</rich:tab>
														
							<rich:tab label="Containers" name="containers"  id="containersMainTab" status="none" rendered="#{blControl.bl.FCL}"  disabled="#{blControl.bl.blId <= 0}" ontabenter="changeTabContainersJs();" >
								<a4j:include id="containersTab" viewId="../../../jsp/LODepartment/bl/blContainersTab.jsp" />
							</rich:tab>
							
							<rich:tab label="Items" name="blItems"  id="blItemsMainTab"  status="none" rendered="#{!blControl.bl.master}" disabled="#{blControl.bl.blId <= 0}" ontabenter="changeTabBlItemsJs();" >
								<a4j:include id="itemsTab" viewId="../../../jsp/LODepartment/bl/blItemsTab.jsp" style="min-width: 1200px" />
							</rich:tab>
								
							<rich:tab label="Costs & Sales" name="costsAndSales"  id="costAndSalesMainTab" status="none" disabled="#{blControl.bl.blId <= 0}" ontabenter="changeTabCostsAndSalesJs();" >
								<a4j:include id="costsSalesTab" viewId="../../../jsp/LODepartment/bl/blCostSalesTab.jsp" />
							</rich:tab>	
							
							<rich:tab label="More Info" name="moreInfo"  id="moreInfoMainTab" status="none" disabled="#{blControl.bl.blId <= 0}" ontabenter="changeTabMoreInfoJs();" >
								<a4j:include id="moreInfoTab" viewId="../../../jsp/LODepartment/bl/blMoreInfoTab.jsp" />
							</rich:tab>
							
							<rich:tab label="Product Info" name="productInfo"   id="productInfoMainTab" status="none" disabled="#{blControl.bl.blId <= 0}" ontabenter="changeTabProductInfoJs();" >
								product info
							</rich:tab>	
						</rich:tabPanel>
						
						<a4j:jsFunction name="changeTabHousesJs" action="#{blControl.changeCurrentTabHouses}" />
						
						<a4j:jsFunction name="changeTabContainersJs" action="#{blControl.changeCurrentTabContainers}" />
								
						<a4j:jsFunction name="changeTabBlItemsJs" action="#{blControl.changeCurrentTabBlItems}"/>
						
						<a4j:jsFunction name="changeTabCostsAndSalesJs" action="#{blControl.changeCurrentTabCostsAndSales}"/>
						
						<a4j:jsFunction name="changeTabMoreInfoJs" action="#{blControl.changeCurrentTabMoreInfo}"/>
						
						<a4j:jsFunction name="changeTabProductInfoJs" action="#{blControl.changeCurrentTabProductInfo }"/>
						
						
					</td>		
				</tr>
				<tr>
					<td colspan = "2" >
					<table height="110px" >
						<tr>
							<td valign="top">
								<table>
									<tr>
										<td nowrap="nowrap" align="right" valign="top" >
											 Total pieces:
										</td>
										<td valign="top" >
											<h:inputText  id="totalPiecesText" value="#{blControl.bl.totalPieces}"  readonly="true" style="width: 30px;background: #FFFFFF;color: #000000;text-align:right" tabindex="50" ></h:inputText>
										</td>										
									</tr>
									<tr  >
										<td nowrap="nowrap" align="right" valign="top" height="20px">
											<h:outputText value="Total Containers:" rendered="#{blControl.bl.FCL}"/>
										</td>
										<td valign="top" >
											<h:inputText id="totalContainersText" value="#{blControl.bl.totalContainers}"  readonly="true" style="width: 30px;background: #FFFFFF;color: #000000;text-align:right" rendered="#{blControl.bl.FCL}" tabindex="51"></h:inputText>
										</td>
									</tr>									
								</table>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td valign="top">
								<div style="position: relative;">
									<div class="headerPanel" align="center" style="background-color : #F3F8FC; top: -7%; width: 73%; border-radius:8px" >Total Real Weight</div>
									<rich:panel styleClass="stylefilterDatePanel" style="background-color : #{blControl.bl.totalRealWTon > blControl.bl.totalOceanVolM3 ? '#95C5EC' : '#F3F8FC'}; border-radius : 8px; width : 140px;" >
										<table cellpadding="1" cellspacing="1" border="0" >							
											<tr height="30px" valign="bottom">
												<td nowrap="nowrap" align="right">
													Lbs.:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="totalRealWLbText" value="#{blControl.bl.totalRealWLb}"  readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="52">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
													</h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Kgs.:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="totalRealWKgText" value="#{blControl.bl.totalRealWKg}"   readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="53">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2" />
													</h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Tons.:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="totalRealWTonText" value="#{blControl.bl.totalRealWTon}"  readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="54">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2" />
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
									<div class="headerPanel" align="center" style="background-color : #F3F8FC; top: -8%; width: 78%; border-radius:8px" >Total Ocean Vol Weight</div>
									<rich:panel styleClass="stylefilterDatePanel" style="background-color : #{blControl.bl.totalOceanVolM3 > blControl.bl.totalRealWTon ? '#95C5EC' : '#F3F8FC'}; border-radius:8px;width:150px;" >
										<table cellpadding="1" cellspacing="1" border="0">	
											<tr height="30px" valign="bottom">										
												<td nowrap="nowrap" align="right">
													Vol. [ft3]:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="totalOceanVolF3Text" value="#{blControl.bl.totalOceanVolF3}" readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="55">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
													</h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Vol. [m3]:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="totalOceanVolM3Text" value="#{blControl.bl.totalOceanVolM3}"   readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="56">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
													</h:inputText>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">
													Real. [ton]:
												</td>
												<td>
													<h:inputText id="totalRealWTon2Text" value="#{blControl.bl.totalRealWTon}"  readonly="true" style="width: 60px;background: #FFFFFF;color: #000000;text-align:right" tabindex="57">
														<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
													</h:inputText>
												</td>
											</tr>
										</table>
									</rich:panel>
								</div>						
							</td>
						</tr>
					</table>
				</tr>
			</table>		
		</rich:panel>
		
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
						                <a4j:commandButton value="Yes" id="yesRegButton" action="#{blControl.changeRegionYesAction }"  
						                    oncomplete="#{rich:component('switchRegionPopup')}.hide()" reRender="regionComboBox" process="regionComboBox"
						                    status="none" tabindex="1" ajaxSingle="true"/>
					                </td>
					                <td align="center" width="50px;">
					                	<a4j:commandButton value="No" action="#{blControl.changeRegionNoAction }"
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
	<a4j:include id="blMoreInfoTab" viewId="../../../jsp/LODepartment/bl/blPopups.jsp" />
</body>
</html>
</f:view>