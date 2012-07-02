<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<body>
		<rich:modalPanel id="callHistDetailsPanel" autosized="true" width="800" style="background-color:#F3F8FC;" onshow="#{rich:element('contactList') }.focus()" >
		    <f:facet name="header">
	    		<h:outputText value="Call History Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink" onclick="#{rich:element('linkCheckStateJ')}.click();"/>
	            </h:panelGroup>
        	</f:facet>
	    	<h:form>
				<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:element('cancelButtonCH')}.focus();#{rich:element('cancelButtonCH')}.click();"/>
				<table>
					<tr>
						<td align="right" width="100px;">
							<h:outputLabel value="Contact:" />
						</td>
						<td>
							<h:selectOneMenu  id="contactList" value="#{clienOrderControl.callHistory.contact.contactId }" style="width:130px" 
								tabindex="1" required="true" requiredMessage="- Contact field is required">
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />								
								<f:selectItems value="#{clienOrderControl.partnerContactsList }"/>											
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<td align="right">
							<h:outputLabel value="Date:" />
						</td>
						<td>
							<h:inputText value="#{clienOrderControl.callHistory.enteredDate}" style="width:127px" disabled="true">
								<f:convertDateTime dateStyle="short" timeZone="EST" type="both" />
							</h:inputText>
						</td>
					</tr>
					<tr>
						<td align="right">
							<h:outputLabel value="Performed By:" />
						</td>
						<td>
							<h:selectOneMenu id="employeeList" style="width:130px" value="#{clienOrderControl.callHistory.employeeCreator.employeeId }" 
								tabindex="2" required="true" requiredMessage="- Perform By field is required">
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />
								<f:selectItems value="#{clienOrderControl.employees}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							<h:outputLabel value="Notes:" />
						</td>
						<td>
							<h:inputTextarea value="#{clienOrderControl.callHistory.notes}" rows="10" style="width:650px" 
							tabindex="3" />
						</td>
					</tr>
					<tr height="60px" align="center">
						<td colspan="2">
							<table>
								<tr>
									<td align="center">
										<a4j:commandButton id="saveCallButton" value="Save" tabindex="4"
										    action="#{clienOrderControl.saveCallHistoryAction}" 
											oncomplete="if (#{facesContext.maximumSeverity==null}){#{rich:element('searchCallSuppButton') }.click();#{rich:element('searchCallClieButton') }.click(); #{rich:component('callHistDetailsPanel')}.hide();}"  
											 />								
									</td>
									<td align="center">
										<a4j:commandButton id="cancelButtonCH" value="Cancel"   tabindex="5" onclick="#{rich:component('callHistDetailsPanel')}.hide()"
											onblur="#{rich:element('contactList') }.focus()" type="button" ajaxSingle="true" immediate="true" status="none"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<h:inputHidden value="#{clienOrderControl.callHistory.callId }" />
	       </h:form>
	 </rich:modalPanel>
	 <rich:modalPanel id="confirmCallHistPanel" autosized="true" width="200" style="background-color:#F3F8FC;" onshow="#{rich:element('yesButtonF') }.focus()">
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
	                 <a4j:commandButton value="Yes" id="yesButtonF"
	                    onclick="#{rich:element('linkChangeFlagToFalseJ') }.click();#{rich:component('confirmCallHistPanel')}.hide();#{rich:element('saveCallButton') }.click();"
	                     ajaxSingle="true" immediate="true" status="none" tabindex="1"/>
	                </td>
	                <td align="center" width="50px;">
	                	<a4j:commandButton
	                     value="No" 
	                     onclick="#{rich:element('linkChangeFlagToFalseJ')}.click();#{rich:component('confirmCallHistPanel')}.hide();#{rich:component('callHistDetailsPanel')}.hide();"
	                     ajaxSingle="true" immediate="true" status="none" tabindex="2"/>
	                </td>
	                <td align="left">
	                	<a4j:commandButton
		                     value="Cancel" 
		                     onclick="#{rich:component('confirmCallHistPanel')}.hide();"
		                     ajaxSingle="true" immediate="true" status="none" tabindex="3" onblur="#{rich:element('yesButtonF') }.focus()"/>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	</rich:modalPanel>
	</body>
</html>