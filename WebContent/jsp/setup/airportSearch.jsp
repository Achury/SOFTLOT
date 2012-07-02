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
	<script type="text/javascript" src="../../js/airports.js"></script>
	<script type="text/javascript" src="../../js/general.js"></script>
	<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
	<title>LOTRADING :: AIRPORTS</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<style type="text/css">
		.rich-panel-body {
			padding:5px;
		}
	</style>
</head>
<body>
	<t:saveState value="#{portControl.port }"/>
	<t:saveState value="#{portControl.filterPort}"/>
	<t:saveState value="#{portControl.ports}"/>
			<h:form id="airportsForm">
				<table width="99%">		
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="airportSearchPanel">	
							    <f:facet name="header">		
							        <h:outputText value="Search Airport"/>	
							    </f:facet>	
							    <rich:panel id="airportsFilterPanel">	
							    	<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: separate;border-spacing:  4px 0px;" >
										<tr>
											<td nowrap="nowrap" align="left" width="30">
												IATA Code:
											</td>
											<td nowrap="nowrap" align="left" width="200">
												<h:inputText id="iataCodeText" value="#{portControl.filterPort.iataCode}" style="width:80px"/>
											</td>										
											<td nowrap="nowrap" align="Right">
												City:
											</td>
											<td nowrap="nowrap" align="left" width="200">
												<h:inputText id="cityText" value="#{portControl.filterPort.city.name}" style="width:150px"/>
											</td>
											<td nowrap="nowrap" align="left" width="30">
												Name:
											</td>
											<td nowrap="nowrap" align="left" width="200">
												<h:inputText id="nameText" value="#{portControl.filterPort.name}" style="width:150px"/>
											</td>															
											<td align="right" width="100">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<a4j:commandLink action="#{portControl.searchAirportsAction }" id="searchButton" reRender="airportsDataTable, found" status="ajaxStatus">
										                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
										                    </a4j:commandLink>
															<rich:hotKey key="return" handler="#{rich:element('searchButton') }.click();" />
														</td>
													</tr>
													<tr>
														<td>
															<h:outputText id="found"
																value="#{portControl.size}" style="font-size:9px;" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<a4j:commandButton id="clearButton"
															value="Clear" styleClass="styleButtonFromSearch" action="#{portControl.clearSearchAction }" 
															reRender="airportSearchPanel" ajaxSingle="true" status="none" immediate="true"/>
														</td>
													</tr>
												</table>
											</td>	
											<td align="right">
												<h:graphicImage value="/css/images/logo.png" width="180" height="70" />
											</td>
										</tr>
									</table>
								</rich:panel>
							</rich:panel>
						</td>
					</tr>
					<tr>
						<td align="right">		
	                    <a4j:commandLink action="#{portControl.newAirportAction}" value="" id="linkNew" 
	                    	style="font-size:11px;" oncomplete="#{rich:component('editPanel')}.show()" reRender="editPanel">
							New Airport			
						</a4j:commandLink>
					</tr>
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
							 	<f:facet name="header">		
					        		<h:outputText value=" Airports"/>	
					    		</f:facet>
							    <rich:extendedDataTable id="airportsDataTable" height="510px" rowClasses="row1, row2" rows="23"
									 value="#{portControl.ports }" var="port" reRender="ds"
									sortMode="#{portControl.sortMode }" selectionMode="#{portControl.selectionMode }"
									tableState="#{portControl.tableState }" binding="#{portControl.table }" title="Airports"> 
									<a4j:support
	       									event="onRowDblClick"
	       									action="#{portControl.selectPortAction}"  
	       									oncomplete="#{rich:component('editPanel')}.show()" 
	       									reRender="formAirportModal"/>
										
									<rich:column sortable="true" label="Name" id="col_1" 
										sortBy="#{port.name}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Name"></h:outputText>
										</f:facet>
										<h:outputText value="#{port.name}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="City" id="col_2" 
										sortBy="#{port.city.name}" width="20%" >
										<f:facet name="header">
											<h:outputText value="City"></h:outputText>
										</f:facet>
										<h:outputText value="#{port.city.name}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="state" id="col_3" 
										sortBy="#{port.city.stateProvince.value1}" width="20%" >
										<f:facet name="header">
											<h:outputText value="State"></h:outputText>
										</f:facet>
										<h:outputText value="#{port.city.stateProvince.value1}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="Country" id="col_4" 
										sortBy="#{port.city.country.value1}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Contry"></h:outputText>
										</f:facet>
										<h:outputText value="#{port.city.country.value1}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="IATA Code" id="col_5" 
										sortBy="#{port.iataCode}" width="20%" >
										<f:facet name="header">
											<h:outputText value="IATA Code"></h:outputText>
										</f:facet>
										<h:outputText value="#{port.iataCode}"></h:outputText>
									</rich:column>
									<f:facet name="footer">
										<rich:datascroller align="center" for="airportsDataTable" maxPages="30" id="ds" status="none"/>
									</f:facet>
								</rich:extendedDataTable>
							</rich:panel>
						</td>
					</tr>
				</table>
			</h:form>
			
			
			
			<rich:modalPanel id="editPanel" autosized="true" width="450" style="background-color:#F3F8FC;">
			<f:facet name="header">
		    		<h:outputText value="Airport Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	       	</f:facet>
			<h:form id="formAirportModal">
		  		<rich:messages style="color:red;"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
				<table width="98%" cellpadding="4">
					<tr height="50" valign="bottom">
						<td nowrap="nowrap" width="100" align="right">Airport Name:</td>
						<td>
							<h:inputText id="nameText"
								value="#{portControl.port.name}" style="width:180px;" required="true" requiredMessage="- Name field is required" tabindex="1"/>
							<rich:message for="nameText" style="color:red;" showDetail="false" >
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					
					<tr>
						<td nowrap="nowrap" align="Right">
						City:
						</td>
						<td >
						  <h:inputText value="#{portControl.port.city.name }" id="cityText" styleClass="textBox" style="width:120px" 
						      onkeydown="if (event.keyCode == 8){ clearCity() }" onkeyup="if(event.keyCode == 13) search()" tabindex="2" required = "true" requiredMessage = "- City field is required"/>
						      <a4j:jsFunction name="clearCity" action="#{portControl.clearCity}" status="none" ajaxSingle="true" reRender="stateCombobox,countryCombobox" />
							   		<rich:suggestionbox id="suggestionBoxId" for="cityText" 
			                	       suggestionAction="#{portControl.autocompleteCity}" var="result"
			                           tokens="," height="150" width="160" cellpadding="2"
			                           cellspacing="2"   shadowOpacity="4" shadowDepth="4"
			                           minChars="3" rules="none" nothingLabel="no matches found"
			                           frequency="0.01" status="none" ajaxSingle="true" 
			                           requestDelay="1" ignoreDupResponses="true">
			                           <h:column>
			                               <h:outputText value="#{result.name}" style="font-style:bold" />
			                           </h:column>
			                            <h:column>
			                               <h:outputText value="#{result.stateProvince.value1}" style="font-style:bold" />
			                            </h:column>
			                            <a4j:support event="onselect" ajaxSingle="true" reRender="stateCombobox,countryCombobox" 
			                                action="#{portControl.boundList}" status="none">  
			                     	        <f:setPropertyActionListener value="#{result.cityId }" target="#{portControl.port.city.cityId}"/>
			                            </a4j:support>
			                     </rich:suggestionbox>
			                     <rich:message for="nameText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    	</rich:message>
						</td>
					</tr>
					<tr>
						<td align="right" width="120px"   >
						State:
						</td>
						<td >	
							<h:selectOneMenu id="stateCombobox" value="#{portControl.port.city.stateProvince.valueId }" style="width:146px" onkeydown="if(event.keyCode == 13) search()" readonly = "true" disabled = "true" tabindex="3">
								<f:selectItem itemLabel="All" itemValue="0" />
								<f:selectItems value="#{portControl.provinces}"/>											
							</h:selectOneMenu>  	
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="Right" width="120px">
						Country:
						</td>
						<td >										
							<h:selectOneMenu id="countryCombobox" value="#{portControl.port.city.country.valueId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()" readonly = "true" disabled = "true" tabindex="4">
								<f:selectItem itemLabel="All" itemValue="" />
								<f:selectItems value="#{portControl.countries}"/>											
						    </h:selectOneMenu> 
						</td>			
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">IATA Code:</td>
						<td>
							<h:inputText id="iataText"
							value="#{portControl.port.iataCode}" style="width:80px;" tabindex="5"/></td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<table cellspacing="17">
								<tr >				
									<td align="center">
										<a4j:commandButton value="Save" action="#{portControl.saveAirportAction}"
											type="button" styleClass="boton"  status="ajaxStatus" data="#{facesContext.maximumSeverity.ordinal ge 2}"
											oncomplete="if (data == false) {#{rich:component('editPanel')}.hide()}" tabindex="6"/> 
									</td>
									<td align="center">
										<h:commandButton value="Cancel" type="button"
											styleClass="boton" onclick="#{rich:component('editPanel')}.hide()" tabindex="7" /> 
									</td>
								</tr>
							</table>
						</td>
					</tr>				
				</table>
			</h:form>
		</rich:modalPanel>
		
		<rich:modalPanel id="disablePanelRec" autosized="true" width="200" style="background-color:#F3F8FC;">
	        <f:facet name="header">
	            <h:outputText value="Are you sure?"
	                style="padding-right:15px;" />
	        </f:facet>
	        <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/images/modal/close.png"
	                    styleClass="hidelink" id="hidelink2" />
	                <rich:componentControl for="disablePanel" attachTo="hidelink2"
	                    operation="hide" event="onclick" />
	            </h:panelGroup>
	        </f:facet>
	        <h:form>
	            <table width="100%">
	                <tbody>
	                    <tr>
	                        <td align="center" width="50%">
		                        <a4j:commandButton value="Yes"
		                            ajaxSingle="true" action="#{resourceControl.disableResourceAction}"
		                            oncomplete="#{rich:component('disablePanelRec')}.hide();" />
	                        </td>
	                        <td align="center" width="50%">
	                        	<h:commandButton value="Cancel" type="button" styleClass="boton" onfocus="blur();"
	                        		onclick="#{rich:component('disablePanelRec')}.hide();" />
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </h:form>
	    </rich:modalPanel>
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