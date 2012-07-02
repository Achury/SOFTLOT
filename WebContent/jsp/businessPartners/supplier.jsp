<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css" />
	<script type="text/javascript" src="../../js/partners.js"></script>
	<script type="text/javascript" src="../../js/general.js"></script>
	<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	<title>LOTRADING :: SUPPLIER DETAILS</title>
</head>
	<body>
	<t:saveState value="#{partnersControl.callHistory }" />	
	<t:saveState value="#{partnersControl.partner }" />
	<t:saveState value="#{partnersControl.partners }" />
	<t:saveState value="#{partnersControl.styleTableHeader}" />
	<t:saveState value="#{partnersControl.jspClient}" />
	<t:saveState value="#{partnersControl.courierAccount }" />
	<t:saveState value="#{partnersControl.partnerContact }" />	
	<t:saveState value="#{partnersControl.shipPickup }" />	
	<t:saveState value="#{partnersControl.selectedtab }" />	
	<t:saveState value="#{partnersControl.city }" />
	<t:saveState value="#{partnersControl.callsHistoryFiltered }" />
	<t:saveState value="#{partnersControl.shipPickup }" />
	<t:saveState value="#{partnersControl.shipPickupsFiltered }" />
	<t:saveState value="#{partnersControl.phone }" />
	<h:form>
		<rich:panel headerClass="styleClientPanel"	bodyClass="styleSupplierPanel" >
			<table cellpadding="1" cellspacing="1" >
				<tr>
					<td rowspan="2" width="40px">
						<h:graphicImage value="/css/images/clients.png" style="border:0" />	                   
					</td>
					<td >
						Partner Details
					</td>
					
					<td align="right" rowspan="2">
					
						<rich:panel headerClass="styleHeaderQuickSearchSupplier" bodyClass="styleSupplierQuickSearch" styleClass="styleSupplierQuickSearch"  >	
						    <f:facet name="header" >		
						        <h:outputText value="Supplier Quick Search"/>	
						    </f:facet>
						    Supplier name:							    
						    <h:inputText value="#{partnersControl.quickPartner.name }" id="supplierName" styleClass="textBox" style="width:120px"/>
							<rich:suggestionbox id="suggestionBoxId" for="supplierName" 
								suggestionAction="#{partnersControl.autoCompleteQuickSupplier}" var="result"
								tokens="," height="150" width="160" cellpadding="2"
								cellspacing="2"   shadowOpacity="4" shadowDepth="4"
								minChars="3" rules="none" nothingLabel="no matches found"
								status="none">
								<h:column>
									<h:outputText value="#{result.name}" style="font-style:bold" />
								</h:column>								
								<a4j:support event="onselect" ajaxSingle="true" action="#{partnersControl.showQuickSupplierAction}"
									reRender="panelSupplierDetails,tabPanelSupplierDepartments">  
									<f:setPropertyActionListener value="#{result }" target="#{partnersControl.partner}"/>
								</a4j:support>
							</rich:suggestionbox>						    
					   </rich:panel> 						
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td width="375px" > 
						<h:outputText value="#{partnersControl.partner.name }" style="font-weight: bold;"/>
					</td>
				</tr>			
			</table>				
		</rich:panel>
	</h:form>
	<h:form id="formSupplier" styleClass="">
		<table width="1240px" cellpadding="0" cellspacing="0" border="0" Class="">
			<tr>
				<td>
					<a4j:include  viewId="../../jsp/businessPartners/supplierDetails.jsp" />
				</td>
			</tr>
			<tr>			
				<td>
					<rich:tabPanel rendered="#{partnersControl.partner.partnerId > 0 }" id="tabPanelSupplierDepartments" switchType="ajax" contentStyle="padding:0px; border-spacing:0px; " selectedTab="#{partnersControl.selectedtab}" 
						>
						<rich:tab label="Raw Materials" name="rm" ontabenter="#{rich:element('linkRM') }.click()"  >							
							<a4j:include id="rmTab" viewId="../../jsp/businessPartners/clientDeptTabRM.jsp" />
						</rich:tab>
						
						<rich:tab label="Industrial Purchases" name="ip" ontabenter="#{rich:element('linkIP') }.click()" >
							<a4j:include id="ipTab" viewId="../../jsp/businessPartners/clientDeptTabIP.jsp" />
						</rich:tab>
						
						<rich:tab label="Freight Forwarding" name="lo" ontabenter="#{rich:element('linkFF') }.click()" >
							<a4j:include  id="ffTab" viewId="../../jsp/businessPartners/clientDeptTabFF.jsp" />
						</rich:tab>
						
						<rich:tab   label="Pickup From" name="shipto" ontabenter="#{rich:element('linkShipPickup') }.click()">
							<a4j:include  id="shipPickupTab" viewId="../../jsp/businessPartners/partnerShipPickup.jsp" />
						</rich:tab>
						
						<rich:tab   label="Call History" name="call" ontabenter="#{rich:element('linkCallHist') }.click()">
							<a4j:include  id="callTab" viewId="../../jsp/businessPartners/partnerCallHistory.jsp" />
						</rich:tab>
						
					</rich:tabPanel>
				</td>
			</tr>
		</table>		
		<h:commandLink action="#{partnersControl.changeCurrentTabRM}" value="" id="linkRM"/> 	
		<h:commandLink action="#{partnersControl.changeCurrentTabIP}" value="" id="linkIP"/>
		<h:commandLink action="#{partnersControl.changeCurrentTabFF}" value="" id="linkFF"/>
		<h:commandLink action="#{partnersControl.changeCurrentTabCall}" value="" id="linkCallHist"/>
		<h:commandLink action="#{partnersControl.changeCurrentTabShipPickup}" value="" id="linkShipPickup"/>
		<h:commandLink action="#{partnersControl.cancelSupplierAction}" value="" id="linkCancelS" immediate="true"/>					
	</h:form>
	
	<a4j:include id="partnerContactEdit" viewId="../../jsp/businessPartners/partnerContactDetails.jsp" />
	<a4j:include id="EditCityPanel" viewId="../../jsp/businessPartners/cityDetails.jsp" />
	<a4j:include id="EditCallHistPanel" viewId="../../jsp/businessPartners/callHistoryDetails.jsp" />
	<a4j:include id="courierAccountEdit" viewId="../../jsp/businessPartners/courierAccountDetails.jsp" />
	<a4j:include id="shipPickupEdit" viewId="../../jsp/businessPartners/shipPickupDetails.jsp" />

	<a4j:include id="messagesPanelSupp" viewId="../../jsp/businessPartners/messagesPopup.jsp" />
	
			
	<%-- ****************************************************
			Este modal Panel es para la edicion de 
			todos los textArea que existan en la vista client.jsp
		*****************************************************
	--%>
	<rich:modalPanel id="EditTextPanel" autosized="true" width="600" style="background-color:#F3F8FC;">
		<f:facet name="header">
    		<h:outputText value="Edit" />
    	</f:facet>
    	<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="EditTextPanel" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
       	</f:facet>
       	<rich:hotKey key="esc" handler="#{rich:component('EditTextPanel')}.hide();"/>
		<table>
			<tr align="center">
				<td align="center" colspan="2">
					<h:inputTextarea  id="TextAreaPopup" style="width:575px;height:350px" value=""/>
				 	<h:inputHidden id="varAux" value=""/>
				</td>
			</tr>
			<tr align="center" height="40">
				<td align="center">
					<table>
						<tr>
							<td>
								<h:commandButton id="botonOk" value="OK" type="button" styleClass="boton"  
									onclick="savePopupEdit(#{rich:element('TextAreaPopup') },#{rich:element('varAux') });
										#{rich:component('EditTextPanel')}.hide();" />
							</td>
							<td>
							</td>
							<td>
								<h:commandButton id="botonCancelar" value="Cancel" type="button" styleClass="boton"  
									onclick="#{rich:component('EditTextPanel')}.hide()" onblur="#{rich:element('TextAreaPopup') }.focus()"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>	
	</rich:modalPanel>
	
	
	
	<%-- ************************************************
			Este modal Panel es para en indicador de cargando 
		*************************************************
	--%>
	<a4j:status id="ajaxStatus" onstart="#{rich:component('waitPanel')}.show()"
			onstop="#{rich:component('waitPanel')}.hide()" />		
	<rich:modalPanel id="waitPanel" autosized="true" width="125">
		<table>
			<tr >
				<td align="center">
					<h:outputText value="Please wait..." style="font-weight:bold;font-size:small" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<h:graphicImage value="/css/images/loading.gif" style="border:0" />
				</td>
			</tr>
		</table>	
	</rich:modalPanel>
	</body>
	</html>
</f:view>