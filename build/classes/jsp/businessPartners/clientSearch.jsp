<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/ui-lightness/jquery-ui-1.8.18.custom.css">
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>

	<script type="text/javascript" src="../../js/partners.js"></script>
	<script type="text/javascript" src="../../js/general.js"></script>
	<script type="text/javascript" src="../../js/sessionTimeout.js"></script>

	<a4j:loadScript src="../../js/jquery/jquery-1.7.1.min.js"></a4j:loadScript>
	<script type="text/javascript">
	 	jQuery = jQuery.noConflict();
	</script>
	
	<a4j:loadScript src="../../js/jquery/ui/jquery.ui.core.js"></a4j:loadScript>
	<a4j:loadScript src="../../js/jquery/ui/jquery.ui.widget.js"></a4j:loadScript>
	<a4j:loadScript src="../../js/jquery/ui/jquery.ui.tabs.js"></a4j:loadScript>
	
	<script type="text/javascript" src="../../js/tabClients.js"></script>
	<script type="text/javascript">
		function fun(){
			<% out.println(session.getAttribute("partnerClient"));%>
		}
	</script>
		
	<title>LOTRADING :: CLIENTS</title>
	<%-- <%@ include file="/jsp/menu/tool.inc"%>  --%>
</head>
	<body onload="document.getElementById('formClients:codeText1').focus()">
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	<t:saveState value="#{partnersControl.partners}" />
	<t:saveState value="#{partnersControl.partner}" />	
	<t:saveState value="#{partnersControl.styleTableHeader}" />
	<t:saveState value="#{partnersControl.jspClient}" />
	
	
<!-- Inicio de los tabs -->
<div id="tabs" >		
<ul>
	<li>
		<a href="#tabs-0">Clients Search</a>
	</li>
</ul>

<div id="tabs-0">

	<h:form id="formClients">

		<table width="100%" style="padding: 0px">		
			<tr>
				<td>
					<a4j:jsFunction name="search" action="#{partnersControl.searchClientAction}" reRender="partnerDataTable"/>
					<rich:panel headerClass="styleClientSearchPanel" bodyClass="styleClientSearchPanel" id="panelFilterClient" >	
					    <f:facet name="header">		
					        <h:outputText value=" Clients Search"/>	
					    </f:facet>					    
					    <rich:panel>	
					    	<table width="100%"  cellpadding="0" cellspacing="0" border="0" >
					    		<tr>
					    			<td nowrap="nowrap" align="Right">
										Client Code:
									</td>
									<td>
										<h:inputText id="codeText1" size="3" value="#{partnersControl.partner.code }" maxlength="3" 
												onkeypress="if(event.keyCode == 13){if(#{rich:element('codeText1')}.value.length == 3){#{rich:element('linkSearch')}.click();}else{#{rich:element('linkSearch')}.click();}}" >
										</h:inputText>
										
										<h:inputHidden id="idPartner1" value="#{partnersControl.partner.partnerId }"/>
										<h:inputHidden id="CityNameText1" value="#{partnersControl.partner.address.city.name }"/>
									</td>
									<td nowrap="nowrap" align="Right" width="120px" >
										Client Name:
									</td>
									<td nowrap="nowrap">
										<h:inputText id="nameText1" onkeydown="if(event.keyCode == 13) search()"
											value="#{partnersControl.partner.name }" style="width:143px"  />
									</td>
									<td nowrap="nowrap" align="Right" width="120px">
										Sales Rep.:							
									</td>
									<td>
										<h:selectOneMenu id="salesRepComboBox" value="#{partnersControl.partner.employeeUpdate.employeeId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()">
											<f:selectItem itemLabel="All" itemValue="" />
											<f:selectItems value="#{partnersControl.employees}"/>
										</h:selectOneMenu> 
									</td>
									<td rowspan="3" align="center" width="500px" >
										<table cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													
													
													<a4j:commandLink id="linkSearch" action="#{partnersControl.searchClientAction}" reRender="partnerDataTable,found">
														<h:graphicImage value="/css/images/refresh.png" style="border:0" />
													</a4j:commandLink>
												</td>
											</tr>
											<tr>
												<td>
													<h:outputText id="found"
												value= "Found :#{partnersControl.size}" style="font-size:9px;"/>
												</td>
											</tr>
											<tr>
												<td align="center">
													<a4j:commandButton id="clearButton"
													value="Clear" styleClass="styleButtonFromSearch" action="#{partnersControl.clearSearchAction }" 
													reRender="panelFilterClient" ajaxSingle="true" status="none" immediate="true"/>
												</td>
											</tr>
										</table>
									</td>
									<td rowspan="3" align="right">
										<h:graphicImage value="/css/images/logo.png" style="border:0" width="180" height="70" />
									</td>
																						
								</tr>
								<tr>
									<td nowrap="nowrap" align="Right">
										City:
									</td>
									<td >
				                        <h:inputText value="#{partnersControl.partner.address.city.name }" id="cityText" styleClass="textBox" style="width:120px" 
				                        	onkeydown="if (event.keyCode == 8){clearCity();}" onkeyup="if(event.keyCode == 13) search()"/>
				                        <a4j:jsFunction name="clearCity" action="#{partnersControl.clearCity}" status="none" ajaxSingle="true" reRender="stateCombobox,countryCombobox"/>
				                        <rich:suggestionbox id="suggestionBoxId" for="cityText" 
	                                        suggestionAction="#{partnersControl.autocompleteCity}" var="result"
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
	                                        		action="#{partnersControl.boundList}" status="none">  
	                                        	<f:setPropertyActionListener value="#{result.cityId }" target="#{partnersControl.partner.address.city.cityId}"/>
	                                        </a4j:support>
	                                    </rich:suggestionbox>
									</td>
									<td align="right" width="120px"   >
										State:
									</td>
									<td >	
										<h:selectOneMenu id="stateCombobox" value="#{partnersControl.partner.address.city.stateProvince.valueId }" style="width:146px" onkeydown="if(event.keyCode == 13) search()">
											<f:selectItem itemLabel="All" itemValue="0" />
											<f:selectItems value="#{partnersControl.provinces}"/>											
				                        </h:selectOneMenu>  	
									</td>
									
									<td nowrap="nowrap" align="Right" width="120px">
										Country:
									</td>
									<td >										
										<h:selectOneMenu id="countryCombobox" value="#{partnersControl.partner.address.city.country.valueId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()" >
											<f:selectItem itemLabel="All" itemValue="" />
											<f:selectItems value="#{partnersControl.countries}"/>											
				                        </h:selectOneMenu> 
									</td>																												
								</tr>
								<tr>
									<td nowrap="nowrap" align="Right">
										Contact Name:
									</td>
									<td nowrap="nowrap" >									
										<h:inputText id="contactNameText1" style="width:120px" onkeydown="if(event.keyCode == 13) search()"
											value="#{partnersControl.partner.contactName }" />
									</td>
									<td align="Right">							
										Notes:									
									</td>	
									<td>							
										<h:inputText id="notesText1" style="width:143px" onkeydown="if(event.keyCode == 13) search()"
											value="#{partnersControl.partner.notes }"  />	
									</td>								
									
									<td nowrap="nowrap" align="Right">
										Status:
									</td>
									<td >
										<h:selectOneMenu id="StatusCombobox" value="#{partnersControl.partner.status.valueId }" style="width:123px" onkeydown="if(event.keyCode == 13) search()" >
											<f:selectItems value="#{partnersControl.status}"/>
											<f:selectItem itemLabel="All" itemValue="" />
										</h:selectOneMenu>
									</td>
								</tr>
							</table>
					    </rich:panel>
					</rich:panel>
				</td>
			</tr>
			<tr style="padding:0px 1px;">
				<td align="right" style="padding:0px 1px;">
					<a4j:commandLink action="#{partnersControl.newClientAction}" value="New Client" id="linkNew" style="font-size:11px; " 
						status="none" ajaxSingle="true"/>
				</td>
			</tr>				
			
			<tr>
				<td>
					<rich:panel headerClass="styleClientSearchPanel" bodyClass="styleClientSearchPanel"  >	
					    <f:facet name="header">		
					        <h:outputText value=" Clients"/>	
					    </f:facet>					
						<rich:extendedDataTable id="partnerDataTable"
							value="#{partnersControl.partners}" var="partner" reRender="ds"
							rows="22" sortMode="#{partnersControl.sortMode}"
							height="486px" rowClasses="row1, row2"
							selectionMode="#{partnersControl.selectionMode}"
							tableState="#{partnersControl.tableState}" title="Clients"
							binding="#{partnersControl.table}">
							
							<a4j:support event="onRowDblClick" actionListener="#{partnersControl.selectPartnerActionAux}" />
							<rich:column sortable="true" label="Client" id="col_1" sortBy="#{partner.name}" width="42%" >
								<f:facet name="header">
									<h:outputText value="Client"></h:outputText>
								</f:facet>
 								<a4j:commandLink rel="#{partner.partnerId}" title="#{partner.code}" id="linkCargar"/>
								<h:outputText value="#{partner.name}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Code" id="col_2" sortBy="#{partner.code}" width="5%" >
								<f:facet name="header">
									<h:outputText value="Code" style=""></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.code}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Phone" id="col_3" sortBy="#{partner.phone.phoneNumber}" width="10%">
								<f:facet name="header">
									<h:outputText value="Phone"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.phone.countryCode} #{partner.phone.areaCode} #{partner.phone.phoneNumber}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Fax" id="col_4" sortBy="#{partner.fax.phoneNumber}" width="10%">
								<f:facet name="header">
									<h:outputText value="Fax"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.fax.countryCode} #{partner.fax.areaCode} #{partner.fax.phoneNumber}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="City" id="col_5" sortBy="#{partner.address.city.name}" width="10%">
								<f:facet name="header">
									<h:outputText value="City"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.name}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="State" id="col_6" sortBy="#{partner.address.city.stateProvince.value1}" width="10%">
								<f:facet name="header">
									<h:outputText value="State"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.stateProvince.value1}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Country" id="col_8" sortBy="#{partner.address.city.country.value1}" width="10%">
								<f:facet name="header">
									<h:outputText value="Country"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.country.value1}"></h:outputText>
							</rich:column>						
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="partnerDataTable" maxPages="30" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>
					</rich:panel>
				</td>
			</tr>
		</table>
	</h:form>
</div>
</div>
<!-- Fin de los tabs -->
		
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