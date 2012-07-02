<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<body>
		<t:saveState value="#{partnersControl.phoneTypes }"/>	
		<t:saveState value="#{partnersControl.partnerContact }"/>	
		<rich:modalPanel id="editContactPanel"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true" width="500">
			<f:facet name="header">
		    	<h:outputText value="Contact Details" />
		    </f:facet>
		    <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideContactlink" onclick="#{rich:element('linkCheckState')}.click();"/>
	                <%-- <rich:componentControl for="editContactPanel" attachTo="hideContactlink" operation="hide" event="onclick"/> --%>
	            </h:panelGroup>
        	</f:facet>
	    	<h:form id="formClientContactModal">
	         <rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
	         <rich:hotKey key="esc" handler="#{rich:element('cancelButtonContact') }.focus();#{rich:element('linkCheckState')}.click();"/>
		         <table>
					<tr>
						<td nowrap="nowrap" align="right" width="90px" >
							Name:
						</td>
						<td align="left" colspan="3" >	
							<h:inputHidden id="flag_isModified" value="false"/>
							<h:inputHidden id="idPartnerContact" value="#{partnersControl.partnerContact.contactId}" />																
							<h:inputText id="ContactNameText" value="#{partnersControl.partnerContact.name}" style="width:343px" tabindex="1" 
							onchange="#{rich:element('flag_isModified')}.value = 'true';" required="true" requiredMessage="- Name field is required"/>
						</td>
					
						<td width="20px"/>
						<td nowrap="nowrap" align="right">
							Department:
						</td>
						
						<td>
							<h:selectOneMenu id="PhoneTypeListId" value="#{partnersControl.partnerContact.departmentLotId}"
								style="width:180px" onchange="#{rich:element('flag_isModified')}.value = 'true';" tabindex="2">
								<f:selectItems value="#{partnersControl.departments}"/>
							</h:selectOneMenu>			
						</td>
					
						<td nowrap="nowrap" >	
							Main: 	
						</td>
						<td>						
							<h:selectBooleanCheckbox id="mainContactBox" value="#{partnersControl.partnerContact.main}" onchange="#{rich:element('flag_isModified')}.value = 'true';" tabindex="3"/>
						</td>
								
					<tr>
						<td  nowrap="nowrap" align="right">
							Title: 
						</td>
						<td colspan="3">
							<h:inputText id="titleText" value="#{partnersControl.partnerContact.title}" style="width:343px" tabindex="4" onchange="#{rich:element('flag_isModified')}.value = 'true';"/>
							
						</td>
						<td width="20px"/>					
						<td rowspan="6" colspan="5">
							<rich:panel id="phonesPanel"
								style="height:222px;" bodyClass="styleGeneralPanel" styleClass="styleGeneralPanel" rendered="#{partnersControl.partnerContact.contactId > 0}" >
								<table cellpadding="0px" cellspacing="0px" >
									<tr>
										<td align="right">
											<a4j:commandLink action="#{partnersControl.newContactPhoneAction}" value="New Phone" id="linkNewContactPhone" reRender="partnerContactDataTableModal">												 
											</a4j:commandLink>
										</td>
									</tr>	
									<tr>
										<td>
											<rich:extendedDataTable width="362px" height="205px" headerClass="#{partnersControl.styleTableHeader }"
												id="partnerContactDataTableModal" value="#{partnersControl.partnerContact.phones }" var="phone"
												rows="20" title="Contact Phone" rowClasses="row1, row2" binding="#{partnersControl.tableContactPhones}">
												
												<rich:column sortable="false" label="Type" id="first" width="15%" >
													<f:facet name="header"  >									
														<h:outputText value="Type"></h:outputText>
													</f:facet>																			
													<h:selectOneMenu id="PhoneTypeListId" value="#{phone.type.valueId}" style="width:46px" onchange="#{rich:element('flag_isModified')}.value = 'true';" >
														<f:selectItems value="#{partnersControl.phoneTypes}"/>
													</h:selectOneMenu>	
													
												</rich:column>
												<rich:column sortable="false" label="CountryCode" id="col_2" width="15%" >
													<f:facet name="header">
														<h:outputText value="Country" ></h:outputText>
													</f:facet>
													<rich:inplaceInput layout="block" value="#{phone.countryCode}"				                        
								                        id="inplaceCountryCode"	
								                        changedHoverClass="hover" viewHoverClass="hover"
								                        viewClass="inplace" changedClass="inplace"
								                        selectOnEdit="true" editEvent="onclick"
								                        onchange="#{rich:element('flag_isModified')}.value = 'true';">
								                	</rich:inplaceInput>	
												</rich:column>
												<rich:column sortable="false" label="areaCode" id="col_3" width="11%" >
													<f:facet name="header">
														<h:outputText value="Area"></h:outputText>
													</f:facet>
													<rich:inplaceInput layout="block" value="#{phone.areaCode}"				                        
								                        id="inplaceAreaCode" 
								                        changedHoverClass="hover" viewHoverClass="hover"
								                        viewClass="inplace" changedClass="inplace"
								                        selectOnEdit="true" editEvent="onclick" 
														onchange="#{rich:element('flag_isModified')}.value = 'true';">
									               </rich:inplaceInput>
												</rich:column>
												<rich:column sortable="false" label="Phone" id="col_4" width="30%" >
													<f:facet name="header">
														<h:outputText value="Phone"></h:outputText>
													</f:facet>
													<rich:inplaceInput layout="block" value="#{phone.phoneNumber}"				                        
								                        id="inplacePhoneNumber" 
								                        changedHoverClass="hover" viewHoverClass="hover"
								                        viewClass="inplace" changedClass="inplace"
								                        selectOnEdit="true" editEvent="onclick"
								                        onchange="#{rich:element('flag_isModified')}.value = 'true';">
									               </rich:inplaceInput>
												</rich:column>
												<rich:column sortable="false" label="Ext" id="col_5" width="13%" >
													<f:facet name="header">
														<h:outputText value="Ext"></h:outputText>
													</f:facet>									
													<rich:inplaceInput layout="block" value="#{phone.phoneExtension}"				                        
								                        id="inplacePhoneExtension"
								                        changedHoverClass="hover" viewHoverClass="hover"
								                        viewClass="inplace" changedClass="inplace"
								                        selectOnEdit="true" editEvent="onclick"
								                        onchange="#{rich:element('flag_isModified')}.value = 'true';">	
									               </rich:inplaceInput>
												</rich:column>	
												<rich:column sortable="false" label="Main" id="col_6" width="37px">
													<f:facet name="header">
														<h:outputText value="Main"></h:outputText>
													</f:facet>
													<h:selectBooleanCheckbox id="mainRadio" value="#{phone.mainPhone}">
														<a4j:support event="onclick" action="#{partnersControl.setAsMainContactPhone}" reRender="partnerContactDataTableModal" status="none"/>
													</h:selectBooleanCheckbox>
												</rich:column>
												<rich:column width="7%">
													<a4j:commandLink ajaxSingle="true" id="deletePhoneLink" 
														action = "#{partnersControl.selectToDeleteContactPhone}"							
														oncomplete="#{rich:component('ConfirmDeletePhonePanel')}.show()"
														status="none">										
														<h:graphicImage value="/css/images/Delete.png" style="border:0" />		
													</a4j:commandLink>
													<rich:toolTip for="deletePhoneLink" value="Delete Phone" />
												</rich:column>						
											</rich:extendedDataTable>
										</td>
									</tr>
									<tr>
										<td height="5px">
									</tr>
									<%-- <tr>
										<td align="right" valign="bottom">
											<a4j:commandButton id="SavePhoneButton"
												value="Save"  type="button" styleClass="boton" onfocus="blur();"						
												action="#{partnersControl.savePartnerContactPhonesAction}"
												reRender="partnerContactDataTableModal,clientContactDataTableRM,clientContactDataTableIP,clientContactDataTableFF"	>									
											</a4j:commandButton>
										</td>
									</tr> --%>
								</table>
							</rich:panel>
						</td>
					</tr>
					<tr>
						<td  nowrap="nowrap" align="right" valign="top" >
							Address:
						</td>
						<td colspan="3" valign="top">
							<h:inputHidden  id="addressHide" value="#{partnersControl.partnerContact.address.addressId}"/>
							<h:inputTextarea  id="addressText" style="width:343px" value="#{partnersControl.partnerContact.address.address}" tabindex="5" onchange="#{rich:element('flag_isModified')}.value = 'true';"/>
						</td>
					</tr>											
					<tr>				
						<td nowrap="nowrap" align="Right">
							City:
						</td>
						<td>	
		                    
		                    <h:inputText value="#{partnersControl.partnerContact.address.city.name}" id="cityContactText" styleClass="textBox" style="width:130px" tabindex="6" onchange="#{rich:element('flag_isModified')}.value = 'true';"/>
							<rich:suggestionbox id="suggestionContactBoxId" for="cityContactText" 
					             suggestionAction="#{partnersControl.autocompleteCity}" var="result2"
					             tokens="," height="150" width="160" cellpadding="2"
					             cellspacing="2"   shadowOpacity="4" shadowDepth="4"
					             minChars="3" rules="none" nothingLabel="no matches found"
					             frequency="0.01" status="none" >
					             <h:column>
					                 <h:outputText value="#{result2.name}" style="font-style:bold" />
					             </h:column>
					             <h:column>
					                 <h:outputText value="#{result2.stateProvince.value1}" style="font-style:bold" />
					             </h:column>
					             <a4j:support event="onselect" ajaxSingle="true" reRender="stateContactCombobox,countryContactCombobox" action="#{partnersControl.boundListContact}" status="none">  
					             	<f:setPropertyActionListener value="#{result2.cityId }" target="#{partnersControl.partnerContact.address.city.cityId}"/>
					             </a4j:support>
					         </rich:suggestionbox>
						</td>
						<td align="right">
							State:
						</td>
						<td>
							<h:inputText id="stateContactCombobox" value="#{partnersControl.partnerContact.address.city.stateProvince.value1 }" style="width:130px" disabled="true" />
						</td>
												
					</tr>
					<tr>					
						<td nowrap="nowrap" align="right"  >
							Country:
						</td>
						
						<td>									
							<h:inputText id="countryContactCombobox" value="#{partnersControl.partnerContact.address.city.country.value1}" style="width:130px" disabled="true"/>
													
					
						</td>					
						
						<td align="right">
							Postal Code:	
						</td>
						<td>						
							<h:inputText id="postalCodeText" style="width:130px" tabindex="7"
							value="#{partnersControl.partnerContact.address.postalCode}" onchange="#{rich:element('flag_isModified')}.value = 'true';"/>
						</td>
					
					</tr>
					<tr>											
						<td nowrap="nowrap" align="right">
							Email:	
						</td>
						<td colspan="3">						
							<h:inputText id="emailText" value="#{partnersControl.partnerContact.email}" style="width:343px" tabindex="8" onchange="#{rich:element('flag_isModified')}.value = 'true';" />
						</td>
					</tr>			
					<tr>
						<td align="right" valign="top" nowrap="nowrap">
							Notes:
						</td>
						<td colspan="3" valign="top"> 
							<h:inputTextarea id="notesText" rows="2" style="width:343px; height:62px"
								value="#{partnersControl.partnerContact.notes}" tabindex="9" onchange="#{rich:element('flag_isModified')}.value = 'true';"/>
						</td>					
					</tr>			
			         <tr>
			          	<td colspan="8" align="center"> 
			          		<table cellspacing="5">
			          			<tr>
			          				<td>
										<a4j:commandButton id="commandSave"	
											value="Save" 
						          			type="button" tabindex="10"
											styleClass="boton"
											onclick="#{rich:element('linkChangeFlagToFalse')}.click()"
											action="#{partnersControl.savePartnerContactAction}"
											reRender="clientContactDataTableRM,clientContactDataTableIP,clientContactDataTableFF,formClientContactModal"	>																			
										</a4j:commandButton>
									</td>
									<td>		
										<a4j:commandButton value="Cancel" 
											type="button" tabindex="11" id="cancelButtonContact"
											onblur="#{rich:element('ContactNameText')}.focus()"
											styleClass="boton" 
											onclick="#{rich:element('linkCheckState')}.click();"
										 	ajaxSingle="true" immediate="true" status="none"/>
										<a4j:commandLink id="linkCheckState" onclick="if(#{rich:element('flag_isModified')}.value == 'false'){#{rich:component('editContactPanel')}.hide();}
														else{ if(#{rich:element('flag_isModified')}.value == 'true'){#{rich:component('confirmContactPanel')}.show();}}" 
														ajaxSingle="true" immediate="true" status="none"/>
										<a4j:commandLink id="linkChangeFlagToFalse" 
											 onclick="#{rich:element('flag_isModified')}.value = 'false';"
											 immediate="true" ajaxSingle="true" status="none" />
									</td>
								</tr>
							</table>			          
						</td>
					</tr>
	         </table>
	     </h:form>
	     <rich:modalPanel id="confirmContactPanel" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButtonA') }.focus()">
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
			                	<a4j:commandButton value="Yes" id="yesButtonA"
				                    onclick="#{rich:element('linkChangeFlagToFalse')}.click();#{rich:component('confirmContactPanel')}.hide();#{rich:element('commandSave') }.click();"
				                    ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
			                </td>
			                <td align="center" width="50px;">
			                	<a4j:commandButton
		                    		value="No" 
				                    onclick="#{rich:element('linkChangeFlagToFalse')}.click();#{rich:component('confirmContactPanel')}.hide();#{rich:component('editContactPanel')}.hide();"
				                    ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
			                </td>
			                <td align="left">
			                	<a4j:commandButton
				                    value="Cancel" tabindex="3"
				                    onclick="#{rich:component('confirmContactPanel')}.hide();"
				                    ajaxSingle="true" immediate="true" status="none"onblur="#{rich:element('yesButtonA') }.focus()"/>
			                </td>
			            </tr>
			        </tbody>
			    </table>
			</rich:modalPanel>
	 </rich:modalPanel>
	 <rich:modalPanel id="ConfirmDeletePhonePanel" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="ConfirmDeletePhonePanel" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{partnersControl.deleteContactPhoneAction}"
	                            onclick="#{rich:component('ConfirmDeletePhonePanel')}.hide();"
	                            reRender="partnerContactDataTableModal,clientContactDataTableRM,clientContactDataTableIP,clientContactDataTableFF" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick="#{rich:component('ConfirmDeletePhonePanel')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>   
	</body>
</html>