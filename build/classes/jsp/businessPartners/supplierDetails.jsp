<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<rich:panel headerClass="styleGeneralPanel"
		bodyClass="styleGeneralPanel" id="panelSupplierDetails">
		<table border="0">
			<tr>
				<td nowrap="nowrap" align="right">Supplier Name:</td>
				<td colspan="3">
					<h:inputText id="nameText"
						value="#{partnersControl.partner.name}" style="width:387px"
						required="true" autocomplete="off"
						validator="#{partnersControl.validateSupplierName }"
						requiredMessage="Field required" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'">
						<rich:ajaxValidator event="onblur" requestDelay="0"
							reRender="panelSupplierDetails" />
						<a4j:support event="onblur" ajaxSingle="true"
							reRender="panelSupplierDetails" oncomplete="if(#{rich:element('customerNumText') }.disabled == true){#{rich:element('nameText') }.focus();}else{#{rich:element('customerNumText') }.focus();}"/>
					</h:inputText> <rich:message for="nameText" style="color:red;" showSummary="true"
						showDetail="false">
						<f:facet name="errorMarker">
							<h:graphicImage value="/css/images/error.gif" />
						</f:facet>
					</rich:message> <h:inputHidden id="idPartner"
						value="#{partnersControl.partner.partnerId}" />
				</td>
				<td nowrap="nowrap" align="right" colspan="2">Customer No.:</td>
				<td colspan="2">
					<h:inputText id="customerNumText"  
						value="#{partnersControl.partner.customerNumber}" style="width:153px"
						disabled="#{!partnersControl.partner.validCode }" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"/>
				</td>
				<td align="right" width="55px;">Status:</td>
				<td>
					<h:selectOneMenu id="statusCombobox"
						value="#{partnersControl.partner.status.valueId }" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						style="width:100px" disabled="#{!partnersControl.partner.validCode }">
						<f:selectItems value="#{partnersControl.status }" />
					</h:selectOneMenu>
				</td>
				<td align="right" width="77px;">Restricted:</td>
				<td align="left" >
					<h:selectBooleanCheckbox value="#{partnersControl.partner.regUSA}"/>
					<h:outputText value="USA" />&nbsp;
					<h:selectBooleanCheckbox value="#{partnersControl.partner.regCOL }"/>
					<h:outputText value="COL" />&nbsp;
					<h:selectBooleanCheckbox value="#{partnersControl.partner.regGER }"/>
					<h:outputText value="GER" />
				</td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td rowspan="2" nowrap="nowrap" >
					<h:inputTextarea
						id="addressText" style="width:154px" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.address.address}"
						disabled="#{!partnersControl.partner.validCode }"
						ondblclick="openPopupEdit(#{rich:element('addressText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
					 			#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()" />
				</td>

				<td nowrap="nowrap" align="Right">City:</td>
				<td>
					<a4j:commandLink action="#{partnersControl.clearCity}"
						id="linkClearCity" status="none" ajaxSingle="true"
						reRender="stateText,countryText" /> 
					<h:inputText value="#{partnersControl.partner.address.city.name }"
						id="cityText" styleClass="textBox" style="width:153px"
						disabled="#{!partnersControl.partner.validCode }"
						onkeydown="if (event.keyCode == 8) { #{rich:element('linkClearCity') }.click()}" 
						onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"/>
					<rich:suggestionbox id="suggestionBoxCity" for="cityText"
						suggestionAction="#{partnersControl.autocompleteCity}"
						var="result" tokens="," height="150" width="160" cellpadding="2"
						cellspacing="2" shadowOpacity="4" shadowDepth="4" minChars="3"
						rules="none" nothingLabel="no matches found" frequency="0.01"
						status="none" requestDelay="1" ignoreDupResponses="true">
						<h:column>
							<h:outputText value="#{result.name}" style="font-style:bold" />
						</h:column>
						<h:column>
							<h:outputText value="#{result.stateProvince.value1}"
								style="font-style:bold" />
						</h:column>
						<a4j:support event="onselect" ajaxSingle="true"
							reRender="stateText,countryText"
							action="#{partnersControl.boundList}" status="none">
							<f:setPropertyActionListener value="#{result.cityId }"
								target="#{partnersControl.partner.address.city.cityId}" />
						</a4j:support>
					</rich:suggestionbox>
				</td>
				<td width="55px" align="left">
					<a4j:commandLink value="Add City"
						id="linkNewCity" disabled="#{!partnersControl.partner.validCode }"
						rendered="#{partnersControl.linkNewCityVisible }"
						action="#{partnersControl.newCityAction }"
						oncomplete="#{rich:component('cityDetailsPanel')}.show()"
						ajaxSingle="true" />
				</td>
				<td align="right">State:</td>
				<td colspan="2">
					<h:inputText id="stateText"
						value="#{partnersControl.partner.address.city.stateProvince.value1 }"
						disabled="true" style="width:153px" />
				</td>

				<td align="right">Country:</td>
				<td>
					<h:inputText id="countryText"
						value="#{partnersControl.partner.address.city.country.value1 }"
						disabled="true" style="width:150px" />
				</td>
				<td align="right">Postal Code:</td>
				<td>
					<h:inputText id="postalCodeText" size="12" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.address.postalCode}"
						disabled="#{!partnersControl.partner.validCode }" style="width:150px"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td nowrap="nowrap" align="right">Phone:</td>
				<td width="130px" nowrap="nowrap">
					<h:inputText id="countryCodeText"
						title="Country Code" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.phone.countryCode}" size="2"
						disabled="#{!partnersControl.partner.validCode }"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputText onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						id="areaCodeText" title="Area Code"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.phone.areaCode}" size="3"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputText onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						id="phoneText" title="Phone Number"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.phone.phoneNumber}" size="11"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputHidden
						value="#{partnersControl.partner.phone.phoneId}"></h:inputHidden>
					<h:inputHidden
						value="#{partnersControl.partner.phone.type.valueId}"></h:inputHidden>
				<td nowrap="nowrap" align="right" colspan="2">Fax:</td>
				<td width="150px" nowrap="nowrap" colspan="2">
					<h:inputText onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						id="countryCodeFaxText"
						title="Country Code" disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.fax.countryCode}" size="2"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputText onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						id="areaCodeFaxText" title="Area Code"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.fax.areaCode}" size="3"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputText onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						id="faxText" title="Fax Number"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.fax.phoneNumber}" size="11"
						onkeydown="return validateNumbers(event)" /> 
					<h:inputHidden
						value="#{partnersControl.partner.fax.phoneId}"></h:inputHidden> 
					<h:inputHidden
						value="#{partnersControl.partner.fax.type.valueId}"></h:inputHidden>
				<td align="right">Website:</td>
				<td>
					<h:inputText id="websiteText" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.website}" style="width:150px"
						disabled="#{!partnersControl.partner.validCode }" />
				</td>
				<td align="right">Language:</td>
				<td>
					<h:selectOneMenu id="languageListId" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.language.valueId}" style="width:153px"
						disabled="#{!partnersControl.partner.validCode }">
						<f:selectItems value="#{partnersControl.languages}" />
					</h:selectOneMenu>
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">Truck Company:</td>
				<td>
					<h:selectOneMenu id="truckCompanyList" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.truckCompany.valueId}"
						style="width:156px" disabled="#{!partnersControl.partner.validCode }">
						<f:selectItem itemLabel="-- SELECT --" itemValue="" />
						<f:selectItems value="#{partnersControl.truckCompanies}" />
					</h:selectOneMenu>
				</td>

				<td nowrap="nowrap" align="right">Payment:</td>
				<td>
					<h:selectOneMenu id="paymentList" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.paymentTerm.valueId}"
						style="width:155px" disabled="#{!partnersControl.partner.validCode }">
						<f:selectItem itemLabel="-- SELECT --" itemValue="" />
						<f:selectItems value="#{partnersControl.paymentTermsList}" />
					</h:selectOneMenu>
				</td>
				<td nowrap="nowrap" align="right" colspan="2">Tax ID:</td>
				<td colspan="2">
					<h:inputText id="taxidText"
						value="#{partnersControl.partner.taxId}" style="width:153px"
						disabled="#{!partnersControl.partner.validCode }" onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"/>
				</td>
				<td align="right" rowspan="2">Supplier Notes:</td>
				<td rowspan="2">
					<h:inputTextarea id="notesText" rows="2"
						onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.notes}"
						ondblclick="openPopupEdit(#{rich:element('notesText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
					 			#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()"
						style="width:150px" />
				</td>
				<td align="right" rowspan="2">Invoice Notes:</td>

				<td rowspan="2">
					<h:inputTextarea id="InvoiceNotesText" rows="2"
						onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						disabled="#{!partnersControl.partner.validCode }"
						value="#{partnersControl.partner.invoiceNotes}"
						ondblclick="openPopupEdit(#{rich:element('InvoiceNotesText')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
					 			#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()"
						style="width:150px" />
				</td>

			</tr>
			<tr>
				<td align="right">Created by:</td>
				<td>
					<h:inputText id="employeeCreator1Txt" style="width:153px"
						disabled="true"
						value="#{partnersControl.partner.employeeCreator.firstName } #{partnersControl.partner.employeeCreator.lastName }" />
					<h:inputHidden id="enteredDate"
						value="#{partnersControl.partner.enteredDate}" /> <h:inputHidden
						id="isClient" value="#{partnersControl.partner.client}" /> <h:inputHidden
						id="isSupplier" value="#{partnersControl.partner.supplier}" />
				</td>
				<td align="right">Modified by:</td>
				<td>
					<h:inputText id="employeeUpdate" style="width:153px"
						disabled="true"
						value="#{partnersControl.partner.employeeUpdate.firstName } #{partnersControl.partner.employeeUpdate.lastName }" />
					<h:inputHidden id="updatedDate"
						value="#{partnersControl.partner.updatedDate}" />
				</td>
				<td nowrap="nowrap" colspan="2">Inf. Destination:</td>
				<td>
					<h:selectBooleanCheckbox id="destinationRadio"
						onchange="#{rich:element('flag_isModifiedSupp')}.value = 'true'"
						value="#{partnersControl.partner.informFinalDest }"
						disabled="#{!partnersControl.partner.validCode }" />
				</td>
				<td align="right" >
					<table>
						<tr>
							<td>
								<a4j:commandButton value="Save" type="button"
									styleClass="boton" id="saveSuppButton"
									action="#{partnersControl.savePartnerAction}"
									oncomplete="#{rich:component('messagesPanel')}.show()"
									disabled="#{!partnersControl.partner.validCode }"
									rendered="#{partnersControl.savePartnerVisible }"
									reRender="formSupplier" />
							</td>
							<td width="6"></td>
							<td>
								<h:inputHidden id="flag_isModifiedSupp" value="false"/>	
								<a4j:commandLink id="linkCheckStateSupp" onclick="if(#{rich:element('flag_isModifiedSupp')}.value == 'false'){#{rich:element('linkCancelS')}.click();}
																		else{ if(#{rich:element('flag_isModifiedSupp')}.value == 'true'){#{rich:component('confirmSupplierDetPanel')}.show();}}" 
									ajaxSingle="true" immediate="true" status="none"/>
									
								<a4j:commandLink id="linkChangeFlagToFalseSupp" 
									onclick="#{rich:element('flag_isModifiedSupp')}.value = 'false';"
									ajaxSingle="true" immediate="true" status="none"/>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</rich:panel>
	<rich:modalPanel id="confirmSupplierDetPanel" autosized="true" width="200" style="background-color:#F3F8FC;">
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
		                 <a4j:commandButton value="Yes"
		                    onclick="#{rich:element('linkChangeFlagToFalseSupp') }.click();#{rich:component('confirmSupplierDetPanel')}.hide();#{rich:element('saveSuppButton') }.click();"
		                     ajaxSingle="true" immediate="true" status="none"/>
		                </td>
		                <td align="center" width="50px;">
		                	<a4j:commandButton
		                     value="No" 
		                     onclick="#{rich:element('linkChangeFlagToFalseSupp')}.click();#{rich:component('confirmSupplierDetPanel')}.hide();#{rich:element('linkCancelS')}.click();"
		                     ajaxSingle="true" immediate="true" status="none"/>
		                </td>
		                <td align="left">
		                	<a4j:commandButton
			                     value="Cancel" 
			                     onclick="#{rich:component('confirmSupplierDetPanel')}.hide();"
			                     ajaxSingle="true" immediate="true" status="none"/>
		                </td>
		            </tr>
		        </tbody>
		    </table>
		</rich:modalPanel>
		<rich:modalPanel id="supplierResticted" showWhenRendered="#{partnersControl.showNotifRegionRestricted }" height="120">
			<f:facet name="header">
				<h:outputText value="Attention" style="padding-right:15px;" />
			</f:facet>
			<table>
				<tr>
					<td align="center">
						<h:outputText value="This supplier can be only contacted through L.O.T GmbH." style="padding-right:15px;" />	
					</td>
				</tr>
				<tr>
					<td align="center">
						<br>
						<a4j:commandButton value="Ok"  onclick="#{rich:component('supplierResticted')}.hide();"
			                     ajaxSingle="true" immediate="true" status="none"/>
					</td>
				</tr>
			</table>
		</rich:modalPanel>
</body>
</html>