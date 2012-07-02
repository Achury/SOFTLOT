<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style type="text/css">
			.rich-panel-body {
			  	padding: 0px;
			}			
		</style>
	</head>
	
	<body>			
		<rich:modalPanel id="editShipToPanel"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true" width="700" onshow="#{rich:element('shipPickupNameText') }.focus()">
			<f:facet name="header">
		    	<h:outputText value="#{partnersControl.shipPickLabel } details" />
		    </f:facet>
		    <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideShiplink" onclick="#{rich:element('linkCheckStateT')}.click();"/>
	            </h:panelGroup>
        	</f:facet>
	    	<h:form id="formShipToModal">
	         <rich:messages style="color:red;"></rich:messages>
	         <rich:hotKey key="esc" handler="#{rich:element('cancelButtonshipPick') }.focus();#{rich:element('linkCheckStateT')}.click();"/>
		         <table>
					<tr>
						<td nowrap="nowrap" align="right" width="90px" >
							Name:
						</td>
						<td align="left" colspan="3" >	
							<h:inputHidden id="idPartner" value="#{partnersControl.shipPickup.partnerId }" />
							<h:inputHidden id="idShipPickup" value="#{partnersControl.shipPickup.shipPickUpId}" />																
							<h:inputText id="shipPickupNameText" value="#{partnersControl.shipPickup.name}" style="width:335px" 
								tabindex="1" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'" required="true" requiredMessage="- Name field is required"/>
						</td>	
						<td valign="bottom">
						 	Notes:
						 </td>													
					<tr>
						
						<td  nowrap="nowrap" align="right" valign="top" >
							Address:
						</td>
						<td colspan="3">
							<h:inputHidden  id="shipPickupAddressHide" value="#{partnersControl.shipPickup.address.addressId}"/>
							<h:inputTextarea  id="shipPickupAddressText" style="width:335px" value="#{partnersControl.shipPickup.address.address}" 
								rows="5" tabindex="2" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'"/>
						</td>					
											
						<td rowspan="6" colspan="5" valign="top">							
							<h:inputTextarea  id="shipPickupNotesText"  value="#{partnersControl.shipPickup.notes}" style="height:249px; width:220px" 
								tabindex="8" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'"/>
						</td>
					</tr>	
					<tr>
						<td nowrap="nowrap" align="Right">
							City:
						</td>
						<td >
							<a4j:commandLink id="linkClearCityShipPick" action="#{partnersControl.clearCity }" ajaxSingle="true" status="none" />
							<h:inputText value="#{partnersControl.shipPickup.address.city.name }" id="cityShipPickText" styleClass="textBox" style="width:128px" 
								onkeydown="if (event.keyCode == 8) { #{rich:element('linkClearCityShipPick') }.click()}" tabindex="3" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'"/>
							<rich:suggestionbox id="ShipPicksuggestionBoxId" for="cityShipPickText" 
					             suggestionAction="#{partnersControl.autocompleteCity}" var="resultShipPick"
					             tokens="," height="150" width="160" cellpadding="2"
					             cellspacing="2"   shadowOpacity="4" shadowDepth="4"
					             minChars="3" rules="none" nothingLabel="no matches found" requestDelay="1"
					             frequency="0.01" status="none" ajaxSingle="true" ignoreDupResponses="true">
					             <h:column>
					                 <h:outputText value="#{resultShipPick.name}" style="font-style:bold" />
					             </h:column>
					             <h:column>
					                 <h:outputText value="#{resultShipPick.stateProvince.value1}" style="font-style:bold" />
					             </h:column>
					             <a4j:support event="onselect" ajaxSingle="true" reRender="stateShipPickText,countryShipPickText" action="#{partnersControl.boundListShipPickup}" status="none">  
					             	<f:setPropertyActionListener value="#{resultShipPick.cityId }" target="#{partnersControl.shipPickup.address.city.cityId}"/>
					             </a4j:support>
					         </rich:suggestionbox>            							
						</td>
						<td nowrap="nowrap" align="right">
							State:	
						</td>
						<td>								
							<h:inputText id="stateShipPickText" value="#{partnersControl.shipPickup.address.city.stateProvince.value1 }" disabled="true" style="width:139px"/>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">
							Country:	
						</td>
						<td>	
							<h:inputText id="countryShipPickText" value="#{partnersControl.shipPickup.address.city.country.value1 }" disabled="true" style="width:128px"/>		
						</td>
					</tr>
																			
					<tr>											
						<td nowrap="nowrap" align="right">
							Dest. Airport:	
						</td>
						<td >	
							<h:selectOneMenu id="shipPickupAirportList" value="#{partnersControl.shipPickup.destinationAirport}" 
								style="width:134px" tabindex="4" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'">
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />
								<f:selectItems value="#{partnersControl.airPorts}" />
							</h:selectOneMenu>					
						
						</td>
						<td nowrap="nowrap" align="right">
							Dest. Port:	
						</td>
						<td >
							<h:selectOneMenu id="shipPickupPortList" value="#{partnersControl.shipPickup.destinationPort}" 
								style="width:145px" tabindex="5" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'">
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />
								<f:selectItems value="#{partnersControl.ports}" />
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>											
						<td nowrap="nowrap" align="right">
							Email:	
						</td>
						<td colspan="3">						
							<h:inputText id="shipPickupEmailText" value="#{partnersControl.shipPickup.email}" style="width:335px" 
							tabindex="6" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'"/>
						</td>
					</tr>			
					<tr>
						<td align="right" nowrap="nowrap" valign="top">
							Notify Party:
						</td>
						<td colspan="3"> 
							<h:inputTextarea id="shipPickupPartyText" rows="4" style="width:335px; height:56px"
								value="#{partnersControl.shipPickup.notifyParty}" tabindex="7" onchange="#{rich:element('flag_isModifiedT')}.value = 'true'"/>
						</td>					
					</tr>			
			         <tr>
			          	<td colspan="9" align="center"> 
			          		<table cellspacing="5">
			          			<tr>
			          				<td align="right">
										<a4j:commandButton id="saveShipPickButton"
						          			type="button" value="Save" 
											styleClass="boton" 	tabindex="9"							
											action="#{partnersControl.saveShipPickupAction}"
											oncomplete="if (#{facesContext.maximumSeverity == null}){#{rich:element('linkChangeFlagToFalseT') }.click();#{rich:component('editShipToPanel')}.hide();#{rich:element('searchButtonShipPick') }.click()}"
											reRender="formShipToModal">								
											
										</a4j:commandButton>									
									</td>
									<td align="left">								
											
										<h:commandButton value="Cancel" id="cancelButtonshipPick"
											type="button" tabindex="10"
											styleClass="boton" onclick="#{rich:element('linkCheckStateT')}.click();" onblur="#{rich:element('shipPickupNameText') }.focus()"/>
										<a4j:commandLink id="linkCheckStateT" onclick="if(#{rich:element('flag_isModifiedT')}.value == 'false'){#{rich:component('editShipToPanel')}.hide();}
																			else{ if(#{rich:element('flag_isModifiedT')}.value == 'true'){#{rich:component('confirmShipPickPanel')}.show();}}" 
											ajaxSingle="true" immediate="true" status="none"/>
										<h:inputHidden id="flag_isModifiedT" value="false"/>
										<a4j:commandLink id="linkChangeFlagToFalseT" 
											 onclick="#{rich:element('flag_isModifiedT')}.value = 'false';"
											ajaxSingle="true" immediate="true" status="none"/>
									</td>
								</tr>
							</table>			          
						</td>						
					</tr>
	         </table>
	     </h:form>
	 </rich:modalPanel>
	 <rich:modalPanel id="confirmShipPickPanel" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButtonD') }.focus()">
		<f:facet name="header">
			<h:outputText value="The content of this window has been changed"
		 					style="padding-right:15px;" />
		</f:facet>
	    <table width="100%">
	        <tbody>
	        	<tr>
	         	<td colspan="3">
	         		<h:outputText value="Do you want to save the changes?"
	         			style="padding-right:15px;" />
	         	</td>		
	        	</tr>
	            <tr>
	                <td align="right">
	                 <a4j:commandButton value="Yes" id="yesButtonD"
	                    onclick="#{rich:element('linkChangeFlagToFalseT') }.click();#{rich:component('confirmShipPickPanel')}.hide();#{rich:element('saveShipPickButton') }.click();"
	                     ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
	                </td>
	                <td align="center" width="50px;">
	                	<a4j:commandButton
	                     value="No" 
	                     onclick="#{rich:element('linkChangeFlagToFalseT')}.click();#{rich:component('confirmShipPickPanel')}.hide();#{rich:component('editShipToPanel')}.hide();"
	                     ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
	                </td>
	                <td align="left">
	                	<a4j:commandButton
		                     value="Cancel" 
		                     onclick="#{rich:component('confirmShipPickPanel')}.hide();"
		                     ajaxSingle="true" immediate="true" status="none" tabindex="3" onblur="#{rich:element('yesButtonD') }.focus()"/>
	                </td>
	            </tr>
	        </tbody>
	    </table>
		</rich:modalPanel>
	</body>
</html>