<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<body>
		<rich:modalPanel id="editCourierAccountPanel" style="background-color: #F3F8FC;"  styleClass="styleGeneralPanel" autosized="true" width="300" onshow="#{rich:element('courierList') }.focus()">
		    <f:facet name="header">
	    		<h:outputText value="Courier Account Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideAcclink" onclick="#{rich:element('linkCheckStateA')}.click();"/>
	            </h:panelGroup>
        	</f:facet>
	    	<h:form>
	    		<rich:messages  errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
	    		<rich:hotKey key="esc" handler="#{rich:element('cancelButtonCourier') }.focus();#{rich:element('linkCheckStateA')}.click();"/>					
					<table align="center" >
						<tr>
							<td align="right"> 
								<h:outputText value="Courier:" />	
								<h:inputHidden value="#{partnersControl.courierAccount.courierAccountId}"/>	
								<h:inputHidden id="flag_isModifiedA" value="false"/>
							</td>
							<td>							
								<h:selectOneMenu  id="courierList" value="#{partnersControl.courierAccount.courier.valueId}" style="width:100px" 
								onchange="#{rich:element('flag_isModifiedA')}.value = 'true'" required="true" requiredMessage="- Courier field  is required">
									<f:selectItem itemLabel="-- SELECT --" itemValue="" />
									<f:selectItems value="#{partnersControl.couriers}"/>											
								</h:selectOneMenu>	
								<h:inputHidden value="#{partnersControl.courierAccount.courier.value1}" />
								
							</td>
						<tr>
							<td align="right">				
								<h:outputText value="Account Number:" />
							</td>
							<td>
								<h:inputText value="#{partnersControl.courierAccount.accountNumber}" style="width:96px" onchange="#{rich:element('flag_isModifiedA')}.value = 'true'"/>
							</td>
						<tr>
							<td align="right">
								<h:outputText value="Main:" />
							</td>
							<td>
								<h:selectBooleanCheckbox id="mainRadio" value="#{partnersControl.courierAccount.main}" onchange="#{rich:element('flag_isModifiedA')}.value = 'true'"/>
							</td>
						<tr>
						<tr>
							<td align="center" colspan="2">
								<a4j:commandButton value="Save" 
									id="saveAccount"
				          			type="button"
									styleClass="boton"  
									onclick="#{rich:element('linkChangeFlagToFalseA')}.click()"								
									action="#{partnersControl.saveCourierAccountAction}"
									oncomplete="if (#{facesContext.maximumSeverity == null}){#{rich:component('editCourierAccountPanel')}.hide();}" 
									reRender="courierAccountDataTableRM, courierAccountDataTableIP, courierAccountDataTableFF"/>
																	
								<h:commandButton value="Cancel" 
									type="button" id="cancelButtonCourier"
									styleClass="boton" onclick="#{rich:element('linkCheckStateA')}.click();"
									onblur="#{rich:element('courierList')}.focus()" />
								<a4j:commandLink id="linkCheckStateA" onclick="if(#{rich:element('flag_isModifiedA')}.value == 'false'){#{rich:component('editCourierAccountPanel')}.hide();}
																			else{ if(#{rich:element('flag_isModifiedA')}.value == 'true'){#{rich:component('confirmCourierAccPanel')}.show();}}" 
									ajaxSingle="true" immediate="true" status="none"/>
								<a4j:commandLink id="linkChangeFlagToFalseA" 
									 onclick="#{rich:element('flag_isModifiedA')}.value = 'false';"
									ajaxSingle="true" immediate="true" status="none"/>
							</td>
						</tr>
					</table>
	       </h:form>
	 </rich:modalPanel>
	 <rich:modalPanel id="confirmCourierAccPanel" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButtonB') }.focus()">
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
			                 <a4j:commandButton value="Yes" id="yesButtonB"
			                    onclick="#{rich:element('linkChangeFlagToFalseA') }.click();#{rich:component('confirmCourierAccPanel')}.hide();#{rich:element('saveAccount') }.click();"
			                     ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
			                </td>
			                <td align="center" width="50px;">
			                	<a4j:commandButton
			                     value="No" 
			                     onclick="#{rich:element('linkChangeFlagToFalseA')}.click();#{rich:component('confirmCourierAccPanel')}.hide();#{rich:component('editCourierAccountPanel')}.hide();"
			                     ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
			                </td>
			                <td align="left">
			                	<a4j:commandButton
				                     value="Cancel" 
				                     onclick="#{rich:component('confirmCourierAccPanel')}.hide();"
				                     ajaxSingle="true" immediate="true" status="none" tabindex="3" onblur="#{rich:element('yesButtonB') }.focus()"/>
			                </td>
			            </tr>
			        </tbody>
			    </table>
			</rich:modalPanel>
	</body>
</html>