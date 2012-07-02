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
	<script type="text/javascript" src="../../js/general.js"></script>
	<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
	<title>LOTRADING :: Cities</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<style type="text/css">
		.rich-panel-body {
			padding:5px;
		}
	</style>
</head>
<body>
	<t:saveState value="#{cityControl.city }"/>
	<t:saveState value="#{cityControl.filterCity}"/>
	<t:saveState value="#{cityControl.cities}"/>
			<h:form id="citiesForm">
				<table width="99%">		
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="citiesSearchPanel">	
							    <f:facet name="header">		
							        <h:outputText value="Search Cities"/>	
							    </f:facet>	
							    <rich:panel id="citiesFilterPanel">	
							    	<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: separate;border-spacing:  4px 0px;" >
										<tr>
											<td nowrap="nowrap" align="Right">
												Name:
											</td>
											<td >
						                        <h:inputText value="#{cityControl.filterCity.name }" id="cityText" styleClass="textBox" style="width:120px" 
						                        	onkeydown="if (event.keyCode == 8){ clearCity() }" onkeyup="if(event.keyCode == 13) search()"/>
						                        <a4j:jsFunction name="clearCity" action="#{cityControl.clearCity}" status="none" ajaxSingle="true" reRender="stateCombobox,countryCombobox"/>
						                        <rich:suggestionbox id="suggestionBoxId" for="cityText" 
			                                        suggestionAction="#{cityControl.autocompleteCity}" var="result"
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
			                                        		action="#{cityControl.boundList}" status="none">  
			                                        	<f:setPropertyActionListener value="#{result.cityId }" target="#{cityControl.filterCity.cityId}"/>
			                                        </a4j:support>
			                                    </rich:suggestionbox>
											</td>
											<td align="right" width="120px"   >
												State:
											</td>
											<td >	
												<h:selectOneMenu id="stateCombobox" value="#{cityControl.filterCity.stateProvince.valueId }" style="width:146px" onkeydown="if(event.keyCode == 13) search()" >
													<f:selectItem itemLabel="All" itemValue="0" />
													<f:selectItems value="#{cityControl.provinces}"/>											
						                        </h:selectOneMenu>  	
											</td>
											
											<td nowrap="nowrap" align="Right" width="120px">
												Country:
											</td>
											<td >										
												<h:selectOneMenu id="countryCombobox" value="#{cityControl.filterCity.country.valueId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()" >
													<f:selectItem itemLabel="All" itemValue="" />
													<f:selectItems value="#{cityControl.countries}"/>											
						                        </h:selectOneMenu> 
											</td>																												
											<td align="right" width="100">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<a4j:commandLink action="#{cityControl.searchCitiesAction }" id="searchButton" reRender="citiesDataTable, found" status="ajaxStatus">
										                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
										                    </a4j:commandLink>
															<rich:hotKey key="return" handler="#{rich:element('searchButton') }.click();" />
														</td>
													</tr>
													<tr>
														<td>
															<h:outputText id="found"
																value="#{cityControl.size}" style="font-size:9px;" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<a4j:commandButton id="clearButton"
																value="Clear" styleClass="styleButtonFromSearch" action="#{cityControl.clearSearchAction }" 
																reRender="citiesSearchPanel" ajaxSingle="true" status="none" immediate="true"/>
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
	                    <a4j:commandLink action="#{cityControl.newCityAction}" value="" id="linkNew" 
	                    	style="font-size:11px;" oncomplete="#{rich:component('editPanel')}.show()" reRender="editPanel">
							New City			
						</a4j:commandLink>
					</tr>
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
							 	<f:facet name="header">		
					        		<h:outputText value=" Cities"/>	
					    		</f:facet>
							    <rich:extendedDataTable id="citiesDataTable" height="510px" rowClasses="row1, row2" rows="23"
									 value="#{cityControl.cities }" var="city" reRender="ds"
									sortMode="#{cityControl.sortMode }" selectionMode="#{cityControl.selectionMode }"
									tableState="#{cityControl.tableState }" binding="#{cityControl.table }" title="Cities"> 
									<a4j:support
	       									event="onRowDblClick"
	       									action="#{cityControl.selectCityAction}"  
	       									oncomplete="#{rich:component('editPanel')}.show()" 
	       									reRender="formCityModal"/>
										
									<rich:column sortable="true" label="Name" id="col_1" 
										sortBy="#{city.name}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Name"></h:outputText>
										</f:facet>
										<h:outputText value="#{city.name}"></h:outputText>
									</rich:column>
									<rich:column sortable="true" label="State or Province" id="col_2" 
										sortBy="#{city.stateProvince.value1}" width="20%" >
										<f:facet name="header">
											<h:outputText value="State or Province"></h:outputText>
										</f:facet>
										<h:outputText value="#{city.stateProvince.value1}"></h:outputText>
									</rich:column>
									
									<rich:column sortable="true" label="country" id="col_3" 
										sortBy="#{city.country.value1}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Country"></h:outputText>
										</f:facet>
										<h:outputText value="#{city.country.value1}"></h:outputText>
									</rich:column>
									<f:facet name="footer">
										<rich:datascroller align="center" for="citiesDataTable" maxPages="30" id="ds" status="none"/>
									</f:facet>
								</rich:extendedDataTable>
							</rich:panel>
						</td>
					</tr>
				</table>
			</h:form>
			
			
			
		<rich:modalPanel id="editPanel" autosized="true" width="450" style="background-color:#F3F8FC;">
			<f:facet name="header">
		    		<h:outputText value="City Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	       	</f:facet>
			<h:form id="formCityModal">
		  		<rich:messages style="color:red;"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
				<table width="98%" cellpadding="4">
					<tr height="50" valign="bottom">
						<td nowrap="nowrap" width="100" align="right">Name:</td>
						<td>
							<h:inputText id="nameText"
								value="#{cityControl.city.name}" style="width:180px;" tabindex="1" required="true" requiredMessage="- Name field is required"/>
							<rich:message for="nameText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					<tr><td align="right" width="120px"   >
						State:
					</td>
					  <td >	
						<h:selectOneMenu id="stateCombobox" value="#{cityControl.city.stateProvince.valueId }" style="width:146px" onkeydown="if(event.keyCode == 13) search()"  tabindex="2" required = "true" requiredMessage = "- State filed is required">
							<f:selectItem itemLabel="All" itemValue="#{null}" />
							<f:selectItems value="#{cityControl.provinces}"/>											
						</h:selectOneMenu>  
						<rich:message for="stateCombobox" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                </rich:message>	
					  </td>
					</tr>
					
					<tr>
						<td nowrap="nowrap" align="Right" width="120px">
							Country:
						</td>
						<td >										
							<h:selectOneMenu id="countryCombobox" value="#{cityControl.city.country.valueId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()" tabindex="3" required = "true" requiredMessage = "- Country field is required">
								<f:selectItem itemLabel="All" itemValue="" />
								<f:selectItems value="#{cityControl.countries}"/>											
						    </h:selectOneMenu> 
						    <rich:message for="countryCombobox" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                </rich:message>	
						</td>								
					</tr>
					<tr>
						<td colspan="3" align="center">
							<table cellspacing="17">
								<tr >				
									<td align="center">
										<a4j:commandButton value="Save" action="#{cityControl.saveCityAction}"
											type="button" styleClass="boton" tabindex="4" status="ajaxStatus" data="#{facesContext.maximumSeverity.ordinal ge 2}"
											oncomplete="if (data == false) {#{rich:component('editPanel')}.hide()}"/> 
									</td>
									<td align="center">
										<h:commandButton value="Cancel" type="button"
											styleClass="boton" onclick="#{rich:component('editPanel')}.hide()" tabindex="5" /> 
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