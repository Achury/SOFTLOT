<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

	</head>
	<body>		
		<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelDetails">
			<table border="0">
				<tr>
					<td nowrap="nowrap" align="right" width="90px">
						Client Code:
					</td>
					<td align="left">	
						<a4j:region>															
							<h:inputText id="codeText" value="#{partnersControl.partner.code}" size="3" required="true" autocomplete="off" 
							requiredMessage="Field required" validator="#{partnersControl.validateClientCode }" tabindex="1" onkeydown="return validarChars(event)" 
							readonly="#{partnersControl.partner.partnerId > 0}" >
	                    		
	                    			<rich:ajaxValidator event="onblur" requestDelay="0" rendered="#{partnersControl.partner.partnerId > 0 }" ignoreDupResponses="true" />
	                    			<a4j:support event="onblur" status="none" bypassUpdates="true"
	                    				reRender="panelDetails" oncomplete="if(#{rich:element('nameText') }.disabled == true){#{rich:element('codeText') }.focus();#{rich:element('codeText') }.select();}"/>             		     
			               	</h:inputText>
			               	<rich:message for="codeText" errorLabelClass="labelerrorStyle" showSummary="true" showDetail="false" >
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif"/>   
		                        </f:facet>
		                    </rich:message>
						</a4j:region>
					</td>
					<td  nowrap="nowrap" align="right" >
						Client Name: 
					</td>
					<td colspan ="4" >
						<h:inputText id="nameText" value="#{partnersControl.partner.name}" style="width:405px" required="true" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}" tabindex="2" requiredMessage="Client Name is required" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'">
                    		<rich:ajaxValidator event="onblur" requestDelay="0" status="none" />
						</h:inputText>
						<h:inputHidden id="idPartner" value="#{partnersControl.partner.partnerId}"/>
						<rich:message for="nameText" errorLabelClass="labelerrorStyle" showDetail="false" showSummary="false" >
	                    	<f:facet name="errorMarker">
	                            <h:graphicImage value="/css/images/error.gif" />   
	                        </f:facet>
	                    </rich:message>
					</td>							
					<td nowrap="nowrap" align="right" >
						Tax ID: 
					</td>
					<td>
						<h:inputText id="taxidText" value="#{partnersControl.partner.taxId}" style="width:150px" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}" tabindex="3" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>
					<td align="right">
						Status: 
					</td>
					<td>
						<h:selectOneMenu  id="statusCombobox" value="#{partnersControl.partner.status.valueId }" style="width:100px" disabled="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}" tabindex="4" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'">
							<f:selectItems value="#{partnersControl.status }"/>											
	                    </h:selectOneMenu> 
					</td>
				</tr>
				<tr>
					<td align="right">
						Address:
					</td>
					<td rowspan="2" nowrap="nowrap" >
						
					 	<h:inputTextarea  id="addressText" style="width:150px" value="#{partnersControl.partner.address.address}" 
					 		readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}" tabindex="5" 
					 		ondblclick="if(#{rich:element('addressText') }.getAttribute('readonly') == null){openPopupEdit(#{rich:element('addressText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
					 			#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus();}"
					 		onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>						
				
					<td nowrap="nowrap" align="Right">
						City:
					</td>
					<td >
						<a4j:commandLink id="linkClearCity" action="#{partnersControl.clearCity }" ajaxSingle="true" status="none" reRender="stateText,countryText"/>
						<h:inputText value="#{partnersControl.partner.address.city.name }" id="cityText" styleClass="textBox" style="width:128px" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}" 
							onkeydown="if (event.keyCode == 8) { #{rich:element('linkClearCity') }.click()}" tabindex="6" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
						<rich:suggestionbox id="suggestionBoxId" for="cityText" 
				             suggestionAction="#{partnersControl.autocompleteCity}" var="result"
				             tokens="," height="150" width="160" cellpadding="2"
				             cellspacing="2"   shadowOpacity="4" shadowDepth="4"
				             minChars="3" rules="none" nothingLabel="no matches found" requestDelay="1"
				             frequency="0.01" status="none" ajaxSingle="true" ignoreDupResponses="true">
				             <h:column>
				                 <h:outputText value="#{result.name}" style="font-style:bold" />
				             </h:column>
				             <h:column>
				                 <h:outputText value="#{result.stateProvince.value1}" style="font-style:bold" />
				             </h:column>
				             <a4j:support event="onselect" ajaxSingle="true" reRender="stateText,countryText" action="#{partnersControl.boundList}" status="none">  
				             	<f:setPropertyActionListener value="#{result.cityId }" target="#{partnersControl.partner.address.city.cityId}"/>
				             </a4j:support>
				         </rich:suggestionbox>            							
					</td>
					<td width="57px" align="left">
						<a4j:commandLink  action="#{partnersControl.newCityAction }" value="Add City" id="linkNewCity" disabled="#{!partnersControl.partner.validCode }"
							oncomplete="#{rich:component('cityDetailsPanel')}.show()" style="font-size:11px;" reRender="cityDetailsPanel" 
							rendered="#{partnersControl.linkNewCityVisible }" ajaxSingle="true"/>						
					</td>
					<td align="right">
						State:
					</td>
					<td>
						<h:inputText id="stateText" value="#{partnersControl.partner.address.city.stateProvince.value1 }" readonly="true" style="width:167px" />
					</td>						
					
					<td nowrap="nowrap" align="right"  >
						Country:
					</td>
					<td>									
						<h:inputText id="countryText" value="#{partnersControl.partner.address.city.country.value1 }" readonly="true" style="width:150px"/>						
				
					</td>
					<td align="right">
						Postal Code:	
					</td>
					<td>						
						<h:inputText id="postalCodeText" size="12" tabindex="7" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
						value="#{partnersControl.partner.address.postalCode}" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>							
					<td nowrap="nowrap" align="right">
						Phone:
					</td>
					<td width="130px" colspan="2" nowrap="nowrap">
						<h:inputText id="countryCodeText" title="Country Code" tabindex="8" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
							value="#{partnersControl.partner.phone.countryCode}" size="2" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/>								
						<h:inputText id="areaCodeText" title="Area Code" tabindex="9" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
							value="#{partnersControl.partner.phone.areaCode}" size="3" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/> 
						<h:inputText id="phoneText" title="Phone Number" tabindex="10" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
							value="#{partnersControl.partner.phone.phoneNumber}" size="16" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/>
						<h:inputHidden value="#{partnersControl.partner.phone.phoneId}"></h:inputHidden>
						<h:inputHidden value="#{partnersControl.partner.phone.type.valueId}"></h:inputHidden>														
					</td>
					<td nowrap="nowrap" align="right" colspan="1">
						Fax:
					</td>
					<td width="150px" nowrap="nowrap">
						<h:inputText id="countryCodeFaxText" title="Country Code" tabindex="11" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
						value="#{partnersControl.partner.fax.countryCode}" size="2" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/> 
						<h:inputText id="areaCodeFaxText" title="Area Code" tabindex="12" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
						value="#{partnersControl.partner.fax.areaCode}" size="3" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/>
						<h:inputText id="faxText" title="Fax Number" tabindex="13" onkeydown="return validateNumbers(event)" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"
						value="#{partnersControl.partner.fax.phoneNumber}" size="13" readonly="#{!partnersControl.partner.validCode || !partnersControl.changeGenInfoActive}"/>
						<h:inputHidden value="#{partnersControl.partner.fax.phoneId}"></h:inputHidden>
						<h:inputHidden value="#{partnersControl.partner.fax.type.valueId}"></h:inputHidden>
											
					<td nowrap="nowrap" align="right">
						Website:	
					</td>
					<td>						
						<h:inputText id="websiteText" value="#{partnersControl.partner.website}" style="width:150px" disabled="#{!partnersControl.partner.validCode }" tabindex="14" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>
					<td align="right">							
						Language:
					</td>
					<td>
						<h:selectOneMenu id="languageListId" value="#{partnersControl.partner.language.valueId}" disabled="#{!partnersControl.partner.validCode }" style="width:100px" tabindex="15" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'">
							<f:selectItems value="#{partnersControl.languages}"/>
						</h:selectOneMenu>						
					</td>
				</tr>			
				<tr>
					<td nowrap="nowrap" align="right"  >
						Truck Company: 
					</td>
					<td>								
						<h:selectOneMenu id="truckCompanyList" value="#{partnersControl.partner.truckCompany.valueId}" style="width:152px" disabled="#{!partnersControl.partner.validCode }" tabindex="16" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'">
							<f:selectItem itemLabel="-- SELECT --" itemValue="" />
							<f:selectItems value="#{partnersControl.truckCompanies}" />
						</h:selectOneMenu>
					
					</td>
					
					<td nowrap="nowrap" align="right">
						Payment:
					</td>
					<td >
						<h:selectOneMenu id="paymentList" value="#{partnersControl.partner.paymentTerm.valueId}" style="width:134px" disabled="#{!partnersControl.partner.validCode || partnersControl.readOnlyPaymentTerm }" tabindex="17" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'">
							<f:selectItem itemLabel="-- SELECT --" itemValue="" />
							<f:selectItems value="#{partnersControl.paymentTermsList}"/>
						</h:selectOneMenu>
					
					</td>
					<td nowrap="nowrap" colspan="2" align="right">	
						Inf Destination: 	
					</td>
					<td>						
						<h:selectBooleanCheckbox id="destinationRadio" value="#{partnersControl.partner.informFinalDest }" disabled="#{!partnersControl.partner.validCode }" tabindex="18" onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>
					<td align="right" nowrap="nowrap">
						Client Notes:
					</td>
					<td rowspan="2"> 
						<h:inputTextarea id="notesText" rows="2"  tabindex="19" value="#{partnersControl.partner.notes}" 
							disabled="#{!partnersControl.partner.validCode }" 
							ondblclick="openPopupEdit(#{rich:element('notesText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
								#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()" style="width:150px"
								onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>
					<td align="right" nowrap="nowrap">
						Invoice Notes:
					</td>								
						
					<td  rowspan="2" >
	
						<h:inputTextarea id="InvoiceNotesText" rows="2" tabindex="20" value="#{partnersControl.partner.invoiceNotes}" 
							disabled="#{!partnersControl.partner.validCode }" 
							ondblclick="openPopupEdit(#{rich:element('InvoiceNotesText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
								#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()" style="width:135px"
								onchange="#{rich:element('flag_isModifiedCl')}.value = 'true'"/>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						Created by:
					</td>
					<td> 
						
						<h:inputText id="employeeCreatorTxt" style="width:150px" disabled="true"  value="#{partnersControl.partner.employeeCreator.firstName } #{partnersControl.partner.employeeCreator.lastName }"/>								
						<h:inputHidden id="emplCreatorIdHidden" value="#{partnersControl.partner.employeeCreator.employeeId}" />
						<h:inputHidden id="enteredDate" value="#{partnersControl.partner.enteredDate}" />
						
						
						<h:inputHidden id="isClient" value="#{partnersControl.partner.client}" /> 
						<h:inputHidden id="isSupplier" value="#{partnersControl.partner.supplier}" /> 
					</td>
					<td align="right">
						Modified by: 
					</td>
					<td >
						<h:inputText id="employeeUpdate" style="width:132px" disabled="true" value="#{partnersControl.partner.employeeUpdate.firstName } #{partnersControl.partner.employeeUpdate.lastName }"/>									
						<h:inputHidden id="emplUpdateIdHidden" value="#{partnersControl.partner.employeeUpdate.employeeId}" />
						<h:inputHidden id="updatedDate" value="#{partnersControl.partner.updatedDate}" />
					</td>						
					<td align="center" colspan="4">
						<table>
							<tr>
								<td>
									<a4j:commandButton id="botonChangeGen" value="Change General Info." type="button" styleClass="boton" disabled="#{partnersControl.partner.partnerId <= 0 }"
										action="#{partnersControl.changeState }" onfocus="blur();"  rendered="#{partnersControl.savePartnerVisible }" reRender="panelDetails" status="none"/>
								</td>
								<td>
									<a4j:commandButton id="botonSave" value="Save" type="button" styleClass="boton" disabled="#{!partnersControl.partner.validCode }" 
										action="#{partnersControl.savePartnerAction}" tabindex="21" rendered="#{partnersControl.savePartnerVisible }"
										onbeforedomupdate="#{rich:component('messagesPanel')}.show();" reRender="formClient"/>
								</td>
								<td width="6"></td>
								<td>										
									<h:inputHidden id="flag_isModifiedCl" value="false"/>	
									<a4j:commandLink id="linkCheckStateCl" onclick="if(#{rich:element('flag_isModifiedCl')}.value == 'false'){#{rich:element('linkCancel')}.click();}
																			else{ if(#{rich:element('flag_isModifiedCl')}.value == 'true'){#{rich:component('confirmClientDetPanel')}.show();}}" 
										ajaxSingle="true" immediate="true" status="none"/>
										
									<a4j:commandLink id="linkChangeFlagToFalseCl" 
										onclick="#{rich:element('flag_isModifiedCl')}.value = 'false';"
										ajaxSingle="true" immediate="true" status="none"/>
								</td>
							</tr>
						</table> 								
					</td>	
				</tr>
			</table>
		</rich:panel>
		<rich:modalPanel id="confirmClientDetPanel" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButtomC') }.focus()">
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
		                 <a4j:commandButton value="Yes" id="yesButtomC"
		                    onclick="#{rich:element('linkChangeFlagToFalseCl') }.click();#{rich:component('confirmClientDetPanel')}.hide();#{rich:element('botonSave') }.click();"
		                     ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
		                </td>
		                <td align="center" width="50px;">
		                	<a4j:commandButton
		                     value="No" 
		                     onclick="#{rich:element('linkChangeFlagToFalseCl')}.click();#{rich:component('confirmClientDetPanel')}.hide();#{rich:element('linkCancel')}.click();"
		                     ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
		                </td>
		                <td align="left">
		                	<a4j:commandButton
			                     value="Cancel" 
			                     onclick="#{rich:component('confirmClientDetPanel')}.hide();"
			                     ajaxSingle="true" immediate="true" status="none" tabindex="3" onblur="#{rich:element('yesButtomC') }.focus()"/>
		                </td>
		            </tr>
		        </tbody>
		    </table>
		</rich:modalPanel>
	</body>
</html>