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
	
	<script>
		
		var currentLink;
		var tab_title;
		var tab_id;
		
		var srcIframe;

		jQuery(function() {
			var defaultHeight = jQuery(window).height() - 29;

			//formSuppliers:partnerDataTable:tb
			jQuery('[id$="partnerDataTable:n"] tr').live("dblclick", function() {
				currentLink = jQuery(this);

				var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");

				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title").substring(0,13);

				srcIframe = 'supplier.jsp?status=existe';

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
			
			//Permite crear el nuevo supplier
			jQuery('a[id="formSuppliers:linkNew"]').live("click", function() {
				currentLink = jQuery(this);

				tab_id = "New_Supplier";
				tab_title = "New Supplier";
				
				srcIframe = "supplier.jsp?status=nuevo";

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});

			// tabs init with a custom tab template and an "add" callback filling in the content
			var tmp = jQuery("#tabs");
			var ttabs = tmp.tabs({
				tabTemplate: "<li><a href='\#{href}'>\#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
				add: function( event, ui ) {
					jQuery( ui.panel ).append(
						'<iframe id = "iframe_' + tab_title + '" width = "100%" height = "' + defaultHeight + 'px" frameborder = "0" src="' + srcIframe + '"></iframe>'
					);
				}
		});

		// actual addTab function: adds new tab using the title input from the form above
		function addTab(tab_id, tab_title) {
			var idTabContent = "#tabs-" + tab_id;

			//Si no existe
			if( !(jQuery(idTabContent).length > 0) ) {
				ttabs.tabs( "add",idTabContent, tab_title );
				var obj = jQuery("a[href='" + idTabContent + "']");
				obj[0].click();
			} else {
				var obj = jQuery("a[href='" + idTabContent + "']");
				obj[0].click();
			}
			return true;
		}

		// close icon: removing the tab on click
		// note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
		jQuery( "#tabs span.ui-icon-close" ).live( "click", function() {
			var index = jQuery( "li", ttabs ).index( jQuery( this ).parent() );
			ttabs.tabs( "remove", index );
		});
		
		jQuery(document).ready(function(){
			jQuery("body, input, textarea").keypress(function(e){
				if(e.which==13){
					searchSuppliers();
				}
			});
		});
	});
				
	</script>
	
	
	<title>LOTRADING :: CLIENTS</title>
	<%-- <%@ include file="/jsp/menu/tool.inc"%>  --%>
</head>
	<body onload="document.getElementById('formSuppliers:nameText1').focus()">
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
		<a href="#tabs-0">Supplier Search</a>
	</li>
</ul>

<div id="tabs-0">
	<h:form id="formSuppliers">		
		<table width="99%">		
			<tr>
				<td>
					<rich:panel headerClass="stylePanelSuppliers" bodyClass="stylePanelSuppliers" id="panelFilterSupplier" >
					    <f:facet name="header">		
					        <h:outputText value=" Search Supplier"/>	
					    </f:facet>					    
					    <rich:panel>	
					    	<table width="100%"  cellpadding="0" cellspacing="0" border="0">
					    		<tr >
									<td nowrap="nowrap" align="Right" width="120px"  >
										Supplier Name: 
									</td>
									<td colspan ="4">
										<h:inputText id="nameText1"
											value="#{partnersControl.partner.name }" style="width:322px" tabindex="1" />
										<h:inputHidden id="idPartner1" value="#{partnersControl.partner.partnerId }"/>
									</td>
																		
									<td>
										<h:inputHidden id="CityNameText1"
											value="#{partnersControl.partner.address.city.name }"/>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td rowspan="3" align="center" width="500px" >
										<table>
											<tr>
												<td align="center">
													<a4j:commandLink id="linkSearch"
								                        action="#{partnersControl.searchSupplierAction}" reRender="partnerDataTable,found"
								                        tabindex="8" onblur="#{rich:element('nameText1')}.focus()">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0" />
								                    </a4j:commandLink>
								                    <a4j:jsFunction name="searchSuppliers" action="#{partnersControl.searchSupplierAction}" reRender="partnerDataTable,found"/>
													
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
													reRender="panelFilterSupplier" ajaxSingle="true" status="none" immediate="true"/>
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
				                        	onkeydown="if (event.keyCode == 8) { clearCity() }" tabindex="2"/>
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
										<h:selectOneMenu id="stateCombobox" value="#{partnersControl.partner.address.city.stateProvince.valueId }" style="width:120px" tabindex="3">
											<f:selectItem itemLabel="All" itemValue="0" />
											<f:selectItems value="#{partnersControl.provinces}"/>											
				                        </h:selectOneMenu>  	
									</td>
									
									<td nowrap="nowrap" align="Right" width="120px">
										Country:
									</td>
									<td >										
										<h:selectOneMenu id="countryCombobox" value="#{partnersControl.partner.address.city.country.valueId }" style="width:120px" tabindex="4">
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
										<h:inputText id="contactNameText1" style="width:120px"
											value="#{partnersControl.partner.contactName }" tabindex="5"/>
									</td>
									<td align="Right">							
										Notes:									
									</td>	
									<td>							
										<h:inputText id="notesText1" style="width:118px"
											value="#{partnersControl.partner.notes }" tabindex="6" />	
									</td>								
									
									<td nowrap="nowrap" align="Right">
										Status:
									</td>
									<td >																																			
										<h:selectOneMenu id="StatusCombobox" value="#{partnersControl.partner.status.valueId }" style="width:120px" tabindex="7">
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
					<a4j:commandLink action="#{partnersControl.newSupplierAction}" value="New Supplier" id="linkNew" 
					style="font-size:11px;" rendered="#{partnersControl.linkNewPartnerVisible }" status="none" ajaxSingle="true"/>
				</td>
			</tr>				
			
			<tr>
				<td>
					<rich:panel headerClass="stylePanelSuppliers" bodyClass="stylePanelSuppliers"  >	
					    <f:facet name="header">		
					        <h:outputText value=" Suppliers"/>	
					    </f:facet>					
						<rich:extendedDataTable id="partnerDataTable"
							value="#{partnersControl.partners}" var="partner" reRender="ds"
							rows="22" sortMode="#{partnersControl.sortMode}"
							height="478px" rowClasses="row1, row2"
							selectionMode="#{partnersControl.selectionMode}"
							tableState="#{partnersControl.tableState}" title="Suppliers"
							binding="#{partnersControl.table}">
							<a4j:support event="onRowDblClick"
								actionListener="#{partnersControl.selectPartnerActionAux}" />
		
							<rich:column sortable="true" label="Supplier" id="col_1"
								sortBy="#{partner.name}" width="42%" >
								<f:facet name="header">
									<h:outputText value="Supplier"></h:outputText>
								</f:facet>
								<a4j:commandLink rel="#{partner.partnerId}" title="#{partner.name}" id="linkCargar"/>
								<h:outputText value="#{partner.name}"></h:outputText>
							</rich:column>						
							<rich:column sortable="true" label="Phone" id="col_3"
								sortBy="#{partner.phone.phoneNumber}" width="10%">
								<f:facet name="header">
									<h:outputText value="Phone"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.phone.countryCode} #{partner.phone.areaCode} #{partner.phone.phoneNumber}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Fax" id="col_4"
								sortBy="#{partner.fax.phoneNumber}" width="10%">
								<f:facet name="header">
									<h:outputText value="Fax"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.fax.countryCode} #{partner.fax.areaCode} #{partner.fax.phoneNumber}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="City" id="col_5"
								sortBy="#{partner.address.city.name}" width="11%">
								<f:facet name="header">
									<h:outputText value="City"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.name}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="State" id="col_6"
								sortBy="#{partner.address.city.stateProvince.value1}" width="9%">
								<f:facet name="header">
									<h:outputText value="State"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.stateProvince.value1}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Country" id="col_8"
								sortBy="#{partner.address.city.country.value1}" width="9%">
								<f:facet name="header">
									<h:outputText value="Country"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.address.city.country.value1}"></h:outputText>
							</rich:column>		
							<rich:column sortable="false" label="Restricted" id="col_9"  width="9%">
								<f:facet name="header">
									<h:outputText value="Restricted"></h:outputText>
								</f:facet>
								<h:outputText value="#{partner.regUSA ? 'USA' : ' '  }   #{partner.regCOL ? 'COL' : ' '}   #{partner.regGER ? 'GER' : ' '}"/>
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