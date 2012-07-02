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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css" />
		<style type="text/css">
			.rich-panel-body {
			  	padding: 0px;
			}		
		</style>
		<title>LOTRADING :: CLIENT DETAILS</title>
	</head>
	<body>
		<t:saveState value="#{partnersControl.partnerContact }"/>
		<h:panelGrid columns="3" width="1024"  cellpadding="0" cellspacing="0" styleClass="styleGeneralPanel">							
				<rich:panel style="border:0px"  styleClass="styleGeneralPanel">	
					<table cellpadding="0px" cellspacing="0px" >
					<tr>
						<td align="right">
							<a4j:commandLink action="#{partnersControl.newContactAction}" value="New Contact" id="linkNewContact" 
								oncomplete="#{rich:component('editContactPanel')}.show();#{rich:element('ContactNameText')}.focus();" 
								reRender="editContactPanel" status="none" ajaxSingle="true"/>
						</td>
					</tr>	
					<tr>
						<td>
							<rich:extendedDataTable id="clientContactDataTableIP" height="430px" width="670px"
								value="#{partnersControl.partner.departmentInfoIP.partnerContacts}" var="partnerContact"
								rows="15" sortMode="#{partnersControl.sortMode}" reRender="ds"
								selectionMode="#{partnersControl.selectionMode}"
								tableState="#{partnersControl.tableStateContactsIP}" title="Contact"
								binding="#{partnersControl.tableContactsIP}" 
								headerClass="#{partnersControl.styleTableHeader }"
								rowClasses="row1, row2">
								
								<a4j:support event="onRowDblClick"
									action="#{partnersControl.selectContactAction}"
									oncomplete="#{rich:component('editContactPanel')}.show();#{rich:element('ContactNameText')}.focus();"
									reRender="editContactPanel" />	
								<rich:column sortable="true" label="Contact" id="col_name"
									sortBy="#{partnerContact.name }" width="130px" >
									<f:facet name="header">
										<h:outputText value="Contact"></h:outputText>
									</f:facet>
									<h:outputText value="#{partnerContact.name}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="title" id="col_title"
									sortBy="#{partnerContact.title }" width="140px" >
									<f:facet name="header">
										<h:outputText value="Title"></h:outputText>
									</f:facet>
									<h:outputText value="#{partnerContact.title}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="email" id="col_email"
									sortBy="#{partnerContact.email}" width="160px" >
									<f:facet name="header">
										<h:outputText value="Email"></h:outputText>
									</f:facet>
									<h:outputText value="<a href='mailto:#{partnerContact.email}'>#{partnerContact.email}</a>" escape="false"></h:outputText>
								</rich:column>
								<rich:column sortable="false" label="phones" id="col_phones" width="186px" >
									<f:facet name="header">
										<h:outputText value="Phones"></h:outputText>
									</f:facet>
									<h:outputText value="#{partnerContact.concatenatedPhones}"></h:outputText>
								</rich:column>
								<rich:column sortable="false" label="Main" id="col_main" width="33px">
									<f:facet name="header">
										<h:outputText value="Main"></h:outputText>
									</f:facet>
									<h:selectBooleanCheckbox id="mainRadio" value="#{partnerContact.main }" onclick="return false"/>
								</rich:column>
								<rich:column width="22px" rendered="#{partnersControl.linkDeleteContactVisible }" id="col_5">
									<a4j:commandLink ajaxSingle="true" id="deleteContactlink" 
										rendered="#{partnersControl.linkDeleteContactVisible }"
										action = "#{partnersControl.selectToDisableContact}"							
										oncomplete="#{rich:component('deletePanelIP')}.show();"
										status="none">										
										<h:graphicImage value="/css/images/Delete.png" style="border:0" />	
												
									</a4j:commandLink>
									<rich:toolTip for="deleteContactlink" value="Delete" />
								</rich:column>
								<f:facet name="footer">
									<rich:datascroller align="center" status="none"
										for="clientContactDataTableIP" maxPages="10" id="ds" boundaryControls="hide"/>
								</f:facet>
							</rich:extendedDataTable>
						</td>	
					</tr>
				</table>
				</rich:panel>
				
			 	<rich:panel style="border:0px" styleClass="styleGeneralPanel">
					<table cellpadding="0px" cellspacing="0px">
						<tr>
							<td>				
								<rich:panel  style="width:533px; height:206px"  styleClass="styleGeneralPanel"  >				
									<table align="left" cellspacing="0" cellpadding="0">	
										<tr height="25px">
											<td align="left" width="110px">
													<h:outputLabel value="Margin:"/>
													<h:inputText id="marginTextip" style="width:60px" onkeydown="if(event.keyCode == 13)blur();"
														 onblur="#{rich:element('marginHiddenip')}.value = #{rich:element('marginTextip')}.value; #{rich:element('marginTextip')}.value = convertToPercentege(#{rich:element('marginTextip')}.value)"
														 onfocus="#{rich:element('marginTextip')}.value = #{rich:element('marginHiddenip')}.value"
														 onchange="#{rich:element('markupTextip')}.value = marginToMarkup(#{rich:element('marginTextip')}.value)" 
														 tabindex="51"/>		 
													 <h:inputHidden id="marginHiddenip" value="#{partnersControl.partner.departmentInfoIP.margin }"/>
											</td>
											<td align="right" width="75px">
												Markup [%]:
											</td>
											<td width="70px">								
												<h:inputText id="markupTextip"  style="width:60px" 
												onchange="#{rich:element('marginHiddenip')}.value = markupToMargin(#{rich:element('markupTextip')}.value);#{rich:element('marginTextip')}.value = convertToPercentege(#{rich:element('marginTextip')}.value)"
												onkeydown="if(event.keyCode == 13)blur();"
												onblur="#{rich:element('marginTextip')}.focus();#{rich:element('marginTextip')}.blur();"
												value="#{partnersControl.partner.departmentInfoIP.markup }" tabindex="52"/>
											</td>
											<td align="right" width="75px">
												Sales Rep:
											</td>
											<td>
												<h:selectOneMenu id="employeesCombobox" value="#{partnersControl.partner.departmentInfoIP.employeeRep }" style="width:190px" tabindex="53">
													<f:selectItem itemLabel="-- SELECT --" itemValue="" />
													<f:selectItems value="#{partnersControl.employees}"/>											
						                        </h:selectOneMenu> 
											</td>
										</tr>	
										<tr >
											<td align="left">
												Notes:
											</td>
										</tr>	
										<tr>
											<td colspan="6" >
												<h:inputTextarea id="notesTextIP"  value="#{partnersControl.partner.departmentInfoIP.notes}" style="width:522px" rows="8" 
													ondblclick="openPopupEdit(#{rich:element('notesTextIP')},#{rich:element('TextAreaPopup')},#{rich:element('varAux')});
														#{rich:component('EditTextPanel')}.show();#{rich:element('TextAreaPopup') }.focus()" tabindex="50"/>
											</td>
										</tr>
										<tr>
											<td align="right" colspan="6">
												 <a4j:commandButton value="Save" type="button" styleClass="boton" rendered="#{partnersControl.saveDeptInfoVisible }"
														action="#{partnersControl.saveDepartmentInfoAction}" onblur="#{rich:element('notesTextIP')}.focus()" oncomplete="#{rich:component('messagesPanel')}.show()"
														tabindex="54"/> 
											</td>
										</tr>		
									</table>				
								</rich:panel>	
							</td>
						</tr>
						<tr>
							<td align="right">
								<a4j:commandLink action="#{partnersControl.newCourierAccountAction}" value="New Account" id="linkNewCourierAccount" 
								oncomplete="#{rich:component('editCourierAccountPanel')}.show();#{rich:element('courierList')}.focus();" 
								reRender="editCourierAccountPanel" status="none" ajaxSingle="true"/>			
							</td>
						</tr>	
						<tr>
							<td align="left">
								<rich:extendedDataTable id="courierAccountDataTableIP" height="213px" width="540px" 
										value="#{partnersControl.partner.departmentInfoIP.courierAccounts}" var="CourierAccount"
										rows="9" sortMode="#{partnersControl.sortMode}" reRender="ds"
										selectionMode="#{partnersControl.selectionMode}"
										tableState="#{partnersControl.tableStateAccountIP}" title="Courier Accounts"
										binding="#{partnersControl.tableAccountIP}"
										headerClass="#{partnersControl.styleTableHeader }"
										rowClasses="row1, row2">
									<a4j:support event="onRowDblClick"
									action="#{partnersControl.selectCourierAccountAction}"
									oncomplete="#{rich:component('editCourierAccountPanel')}.show();#{rich:element('courierList')}.focus();" 
									reRender="editCourierAccountPanel"/>
						
									<rich:column sortable="true" label="Courier" id="col_1"
										sortBy="#{CourierAccount.courier.value1 }" width="81px" >
										<f:facet name="header">
											<h:outputText value="Courier"></h:outputText>
										</f:facet>
										<h:outputText value="#{CourierAccount.courier.value1}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="Account Number" id="col_2"
										sortBy="#{CourierAccount.accountNumber}" width="180px" >
										<f:facet name="header">
											<h:outputText value="Account Number"></h:outputText>
										</f:facet>
										<h:outputText value="#{CourierAccount.accountNumber}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="Main" id="col_3"
										sortBy="#{CourierAccount.main}" width="44px">
										<f:facet name="header">
											<h:outputText value="Main"></h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox id="mainRadio" value="#{CourierAccount.main}" onclick="return false"/>
									</rich:column>
									<rich:column width="30px">
										<a4j:commandLink ajaxSingle="true" id="deleteCourierlink" 
											rendered="#{partnersControl.linkDeleteContactVisible }"
											action = "#{partnersControl.selectToDeleteCourier}"							
											oncomplete="#{rich:component('deletePanelIP')}.show()"
											status="none">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />	
													
										</a4j:commandLink>
										<rich:toolTip for="deleteCourierlink" value="Delete" />
									</rich:column>
									<f:facet name="footer">
										<rich:datascroller align="center" status="none"
											for="courierAccountDataTableIP" maxPages="10" id="ds" boundaryControls="hide"/>
									</f:facet>	
								</rich:extendedDataTable>
							</td>
						</tr>
							
					
				</table>
			</rich:panel>
		</h:panelGrid>		 
	    
	    <rich:modalPanel id="deletePanelIP" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="deletePanelIP" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{partnersControl.disableItemFromTableAction}"
	                            onclick="#{rich:component('deletePanelIP')}.hide();"
	                            reRender="clientContactDataTableIP, clientShipsToDataTableIP, courierAccountDataTableIP" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick="#{rich:component('deletePanelIP')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
	</body>
</html>