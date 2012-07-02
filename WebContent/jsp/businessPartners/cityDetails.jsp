<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<body>
		<rich:modalPanel id="cityDetailsPanel" style="background-color: #F3F8FC;"  styleClass="styleGeneralPanel"  autosized="true" width="250" onshow="#{rich:element('cityName') }.focus()">
		    <f:facet name="header">
	    		<h:outputText value="City Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="cityDetailsPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
        </f:facet>
	    	<h:form>
				<rich:messages style="color:red;"></rich:messages>
				
				<rich:hotKey key="esc" handler="#{rich:component('cityDetailsPanel')}.hide();"/>
				
				<table>
					<tr>
						<td align="right">
							<h:outputLabel value="City Name:" />
						</td>
						<td>
							<h:inputText id="cityName" value="#{partnersControl.city.name}" required="true" style="width:127px" tabindex="1"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<h:outputLabel value="State or Province:" />
						</td>
						<td>
							<h:selectOneMenu  id="stateList" value="#{partnersControl.city.stateProvince.valueId }" style="width:130px" tabindex="2" >
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />
								<f:selectItems value="#{partnersControl.provinces }"/>											
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<td align="right">
							<h:outputLabel value="Country:" />
						</td>
						<td>
							<h:selectOneMenu id="countryList" value="#{partnersControl.city.country.valueId}" style="width:130px" tabindex="3">
								<f:selectItem itemLabel="-- SELECT --" itemValue="" />
								<f:selectItems value="#{partnersControl.countries}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr height="60px" align="center">
						<td colspan="2">
							<table>
								<tr>
									<td align="center">
										<a4j:commandButton value="Save" tabindex="4"
										    action="#{partnersControl.saveCityAction}"
											oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('cityDetailsPanel')}.hide();" />
							
									</td>
									<td align="center">
										<h:commandButton value="Cancel" onclick="#{rich:component('cityDetailsPanel')}.hide();"  
											onfocus="blur();" type="button" id="cancelButton" tabindex="5" onblur="#{rich:element('cityName') }focus()"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<h:inputHidden value="#{partnersControl.city.cityId }" />
	       </h:form>
	 </rich:modalPanel>
	</body>
</html>