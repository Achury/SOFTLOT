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
	<title>LOTRADING :: BLs</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/ui-lightness/jquery-ui-1.8.18.custom.css">
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>	
	<style type="text/css">
		.extdt-cell{
			padding: 0px;
		}
	</style>
	<script type="text/javascript" src="../../../js/general.js"></script>
	
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.core.js"></a4j:loadScript>
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.widget.js"></a4j:loadScript>
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.tabs.js"></a4j:loadScript>
	
	
	
	<script>
		
		var currentLink;
		var tab_title;
		var tab_id;
		
		window.srcIframe;
		window.ttabs;

		jQuery(function() {
			var defaultHeight = jQuery(window).height() - 29;
			
						
			//Cuando un objeto "tr" tenga como padre un id que termine con la palabra "BlsDataTable:n" y 
			//a este objeto se le de click, ejecutará este evento
			jQuery('[id$="BlsDataTable:n"] tr').live("dblclick", function() {
				
				//obtiene las propiedades del objeto anterior
				currentLink = jQuery(this);
				
				//Encuentra el link contenido en el a href que lo precede para capturar las propiedades del mismo
				var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");
				
				//obtiene el atributo "rel" del link para asignar el id de la pestaña
				tab_id = objLink.attr("rel");
				
				//obtiene el atributo "title" del link para asignar el titulo de la pestaña
				tab_title = objLink.attr("title");
				
				//Pagina que debe abrir y se le asigna un parámetro id con el valor existe
				srcIframe = 'bl.jsp?status=existe';

				//Llama la función addTab para crear o abrir la pestaña al objeto seleccionado
				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
			
			
						
			
			//Cuando un objeto con id "formBlSearch:linkNew " se le de click, ejecutará este evento
			//el cual permite crear el nuevo cliente

			jQuery('a[id="formBlSearch:linkNewBlAction"]').live("click", function() {
				currentLink = jQuery(this);

				//Asignar el id de la pestaña
				tab_id = "New_BL";
				
				//Asignar el titulo de la pestaña
				tab_title = "New BL";
				
				//Pagina que debe abrir y se le asigna un parámetro id con el valor nuevo
				srcIframe = "bl.jsp?status=nuevo";
				
				//Llama la función addTab para crear o abrir la pestaña al objeto seleccionado
				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});

			//Se personaliza la nueva pestaña que se crea
			var tmp = jQuery("#tabs");
			
			//Se crea la pestaña y se le agrega el link para removerla
			var ttabs = tmp.tabs({
				tabTemplate: "<li><a href='\#{href}'>\#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
				add: function( event, ui ) {
					jQuery( ui.panel ).append(
						'<iframe id = "iframe_' + tab_title + '" width = "100%" height = "' + defaultHeight + 'px" frameborder = "0" src="' + srcIframe + '"></iframe>'
					);
				}
		});

		//La función addTab añade una nueva pestaña con el título de la entrada en la parte superior del
		//formulario
		// actual addTab function: adds new tab using the title input from the form above

		window.addTab = function (tab_id, tab_title) {
			var idTabContent = "#tabs-" + tab_id;

			//Si no existe una pestaña con este id lo crea ejecutando el link del objeto
			if( !(jQuery(idTabContent).length > 0) ) {
				ttabs.tabs( "add",idTabContent, tab_title );
				var obj = jQuery("a[href='" + idTabContent + "']");
				obj[0].click();
				
			//Si existe una pestaña con este id me muestra la pestaña ejecutando el link del objeto
			} else {
				var obj = jQuery("a[href='" + idTabContent + "']");
				obj[0].click();
			}
			return true;
		};
		
		window.refreshTabName = function (name, id) {
			jQuery("li:has(a[href='#tabs-New_BL'])").children(":first-child").text(name);
			jQuery("li:has(a[href='#tabs-New_BL'])").children(":first-child").attr("href","#tabs-"+id);
			jQuery('#tabs-New_BL').attr("id","tabs-"+id);
			return true;
		};

		// close icon: removing the tab on click
		// note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
		//Remover la nueva pestana
		jQuery( "#tabs span.ui-icon-close" ).live( "click", function() {
			var index = jQuery( "li", ttabs ).index( jQuery( this ).parent() );
			ttabs.tabs( "remove", index );
		});
		
		jQuery(document).keypress(function(event) { /* Cuando se presione enter se ejecuta la busqueda.*/
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if (keycode == '13') {
		    	searchJS();
		    }
		});
		
		window.hola = function(){
			alert("Hola desde awbSearch *-*");
		};
		
	});
				
	</script>	
	
</head>
<body onload="focusFirstElement();" id="bodyId1">
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	
	<t:saveState value="#{blControl.blFilter }"/>
	<t:saveState value="#{blControl.bls }"/>	
	<t:saveState value="#{awbControl.when}"/>
	<t:saveState value="#{blControl.bl }"/>	
	
	
	<!-- Inicio de los tabs -->
	<div id="tabs">		
	<ul>
		<li>
			<a href="#tabs-0">BL Search</a>
		</li>
	</ul>
	
	<div id="tabs-0">

	<h:form id="formBlSearch">		
		<table width="99%">		
			<tr>
				<td>
					<a4j:jsFunction name="focusFirstElement" oncomplete="#{rich:element('codeText')}.focus()" status="none" ajaxSingle="true"/>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="blSearchPanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Bls"/>	
					    </f:facet>	
					    <rich:panel id="blFilterPanel">	
					    	<table width="98%" cellpadding="0" cellspacing="1" border="0" style="border-collapse: separate;border-spacing:  4px 0px;">
								<tr  style="height:10px" >
									<td rowspan="2">
										<table cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td nowrap="nowrap" align="right" >
													Client Code:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="codeText"
														value="#{blControl.blFilter.client.code}" style="width:29px"/>
												</td>
												<td nowrap="nowrap" align="right" width="42px">
													WH No:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="whNumberText"
														 value="#{blControl.blFilter.whNumber}" style="width:52px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Client:
												</td>
												<td colspan="3">
													<h:inputText id="clientText"
														value="#{blControl.blFilter.client.name}" style="width:126px"/>
													<rich:suggestionbox id="clientSuggestBox" for="clientText" 
														suggestionAction="#{blControl.autoCompleteClients}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{blControl.blFilter.client}"/>
														</a4j:support>
													</rich:suggestionbox>													
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Supplier:
												</td>
												<td colspan="3">
													<h:inputText id="shipperText"
														value="#{blControl.blFilter.supplier.name}" style="width:126px"/>
													<rich:suggestionbox id="shipperSuggestBox" for="shipperText" 
														suggestionAction="#{blControl.autoCompleteSuppliers}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{blControl.blFilter.supplier}"/>
														</a4j:support>
													</rich:suggestionbox>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													BL Type:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="StatusCombobox" value="#{blControl.blFilter.blType.valueId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.blTypesList}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Sales Rep:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="salesRepCombobox" value="#{blControl.blFilter.salesRep.employeeId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.employees}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
										
										</table>
									</td>
									<td rowspan="2">
										<table cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td nowrap="nowrap" align="right" >
													Client PO No:
												</td>
												<td>
													<h:inputText id="ClientPOText"
														value="#{blControl.blFilter.clientPoNumber}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													BL No:
												</td>
												<td>
													<h:inputText id="fullBlText"
														value="#{blControl.blFilter.fullBlNumber}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Other Invoices:
												</td>
												<td>
													<h:inputText id="blOtherInvoiceText"
														value="#{blControl.blFilter.blOtherInvoice}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Freight Invoices:
												</td>
												<td>
													<h:inputText id="blFreightInvoiceText"
														value="#{blControl.blFilter.blFreightInvoice}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													BL Int:
												</td>
												<td>
													<h:inputText id="blText"
														value="#{blControl.blFilter.blNumber}" style="width:100px"/>
												</td>
											</tr>
										</table>	
									</td>
									<td valign="top">
										<table cellpadding="0" cellspacing="1" border="0" >											
											<tr>
												<td nowrap="nowrap" align="right" >
													Dest. Port:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="DestPortCombobox" value="#{blControl.blFilter.portDestination.portId }" style="width:120px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.ports}"/>
							                        </h:selectOneMenu>									
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Orig. Port:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="OriginPortCombobox" value="#{blControl.blFilter.portOrigin.portId }" style="width:120px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.ports}"/>
							                        </h:selectOneMenu>									
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Carrier:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="carrierCombobox" value="#{blControl.blFilter.carrier.carrierId }" style="width:120px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.carriers}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Remarks:
												</td>
												<td>
													<h:inputText id="remarksText"
														value="#{blControl.blFilter.remarks}" style="width:118px"/>
												</td>
												
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Shipping Type:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="shipTypeCombobox" value="#{blControl.blFilter.blShippingType.valueId }" style="width:120px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{blControl.blShippingTypes}"/>
							                        </h:selectOneMenu>
							                        
												</td>
											</tr>
										
										</table>
									</td>
									<td colspan="2">
										<div style="position: relative;">
											<div class="headerPanel" align="center" style="top: -8%;width: 25%" > Date Shipped</div>
											<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:262px;" >
												<table cellpadding="0" cellspacing="1" border="0">
													<tr>
														<td nowrap="nowrap" align="left" colspan="2">
															<h:selectOneRadio id="dateFilter" layout="lineDirection" value="#{blControl.when }">
															 	<f:selectItem itemLabel="Today" itemValue="today"/>
															 	<f:selectItem itemLabel="This week" itemValue="thisWeek"/>
															 	<f:selectItem itemLabel="This month" itemValue="thisMonth"/>
															 	<f:selectItem itemLabel="All" itemValue="all"/>
															 	<a4j:support event="onclick" action="#{blControl.changeDateFilter}" reRender="dateFrom,dateTo" ajaxSingle="true" status="none"/>														 		
															</h:selectOneRadio> 
														</td>		
													</tr>
													<tr>
														<td nowrap="nowrap">
															&nbsp;From:
														</td>
														<td nowrap="nowrap">
															To:
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap">&nbsp;
															<rich:calendar id="dateFrom" cellWidth="20px" inputSize="10" datePattern="MM/d/yyyy" 
																value="#{blControl.blFilter.dateFromFilter}" enableManualInput="true" 
																oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('formBlSearch:dateFromInputDate');">
																<a4j:support event="oninputclick" action="#{blControl.changeDateFilter}" reRender="dateFilter" ajaxSingle="true" status="none">
																	<f:setPropertyActionListener value="rango" target="#{blControl.when}"/>
															 	</a4j:support>
															</rich:calendar>
														</td>
														<td nowrap="nowrap">
															<rich:calendar id="dateTo" cellWidth="20px" inputSize="10" datePattern="MM/d/yyyy" oninputkeypress="return validateNumbers2(event);"
																value="#{blControl.blFilter.dateToFilter }" enableManualInput="true" oninputblur="autoCompleteDate2('formBlSearch:dateToInputDate');"/>
														</td>
													</tr>											
												</table>
											</rich:panel>
										</div>	
									</td>
									<td rowspan="2" align="right">
										<table>
											<tr>
												<td align="right" nowrap="nowrap">&nbsp;
													<a4j:jsFunction name="searchJS" action="#{blControl.searchBlAction }"></a4j:jsFunction>
													<a4j:commandLink action="#{blControl.searchBlAction }" id="searchButton" reRender="BlsDataTable, found" status="ajaxStatus">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
								                    </a4j:commandLink>
													<rich:hotKey key="return" handler="#{rich:element('searchButton') }.click();" />
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap">&nbsp;&nbsp;
													<h:outputText id="found"
														value="found:#{blControl.size}" style="font-size:9px;"/>
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">&nbsp;
													<a4j:commandButton id="clearButton" value="Clear" styleClass="styleButtonFromSearch"  onblur="focusFirstElement();"
														reRender="blFilterPanel" ajaxSingle="true" status="none" immediate="true" action="#{blControl.clearFilterFields}"/>
												</td>
											</tr>
										</table>
									</td>
									<td align="right" rowspan="4">
										<h:graphicImage value="/css/images/logo.png" width="180" height="70" />
									</td>	
								</tr>								
							</table>	
						</rich:panel>			
					</rich:panel>
				</td>
			</tr>
			<tr>
				<td align="right" style="padding:0px 1px;">
					<a4j:commandLink  value="New BL " id="linkNew" style="font-size:11px;" ajaxSingle="true" status="none" onclick="#{rich:component('selectBlTypePopup')}.show();"/>
				</td>
			</tr>
			<tr>
				<td>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value="Bl Results"/>	
					    </f:facet>					
						<rich:extendedDataTable id="BlsDataTable"
							height="455px" rowClasses="row1, row2" rows="20"
							title="BL Results" value="#{blControl.bls }" var="bl" reRender="ds"
							sortMode="#{blControl.sortMode }" selectionMode="#{blControl.selectionMode }"
							tableState="#{blControl.tableState }" binding="#{blControl.blTable}"> 
							
							<a4j:support event="onRowDblClick" actionListener="#{blControl.selectBlActionAux}" />
							
							<rich:column sortable="true" label="Bl Int" id="col_1" 
								sortBy="#{bl.blNumber}" width="75px">
								<f:facet name="header">
									<h:outputText value="Bl Int"></h:outputText>
								</f:facet>
								<a4j:commandLink rel="#{bl.blId}" title="#{bl.blNumber}" id="linkCargar"/>
								<h:outputText value="#{bl.blNumber}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Completed" id="col_2"  style="text-align:center"
								sortBy="#{bl.completed}" width="45px">
								<f:facet name="header">
									<h:outputText value="Compl" title="completed"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.completed}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="shipmentNotif" id="col_3"  style="text-align:center"
								sortBy="#{bl.shipmentNotif}" width="25px">
								<f:facet name="header">
									<h:outputText value="S" title="Shipment Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.shipmentNotif}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="arrivalNotif" id="col_4"  style="text-align:center"
								sortBy="#{bl.arrivalNotif}" width="25px">
								<f:facet name="header">
									<h:outputText value="A" title="Arrival Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.arrivalNotif}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="docsDelNotif" id="col_5"  style="text-align:center"
								sortBy="#{bl.docsDelNotif}" width="25px">
								<f:facet name="header">
									<h:outputText value="D" title="Documents Delivery Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.docsDelNotif}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="shipDelNotif" id="col_6"  style="text-align:center"
								sortBy="#{bl.shipDelNotif}" width="30px">
								<f:facet name="header">
									<h:outputText value="SD" title="Shipment Delivery Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.shipDelNotif}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="POE" id="col_7"  style="text-align:center"
								sortBy="#{bl.poeNotif}" width="34px">
								<f:facet name="header">
									<h:outputText value="POE"  title="Proof of Export Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{bl.poeNotif}" disabled="true" ></h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="true" label="Freight Inv." id="col_8" 
								sortBy="#{bl.concatenatedFreightInvoices2}" width="90px">
								<f:facet name="header">
									<h:outputText value="Freight Invoices"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.concatenatedFreightInvoices2}"></h:outputText>
							</rich:column>
																					
							<rich:column sortable="true" label="Date" id="col_9" 
								sortBy="#{bl.createdDate}" width="75px">
								<f:facet name="header">
									<h:outputText value="Date"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.createdDate}">
									<f:convertDateTime type="date" pattern="MM/dd/yyyy"/>
								</h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Remarks" id="col_10" 
								sortBy="#{bl.remarks}" width="100px">
								<f:facet name="header">
									<h:outputText value="Remarks"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.remarks}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Client PO" id="col_11" 
								sortBy="#{bl.clientPoNumber}" width="100px">
								<f:facet name="header">
									<h:outputText value="Client PO"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.clientPoNumber}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Carrier" id="col_12" 
								sortBy="#{bl.carrier.name}" width="120px">
								<f:facet name="header">
									<h:outputText value="Carrier"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.carrier.name}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="BL" id="col_13" 
								sortBy="#{bl.fullBlNumber}" width="100px">
								<f:facet name="header">
									<h:outputText value="BL"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.fullBlNumber}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Client" id="col_14" 
								sortBy="#{bl.client.code}" width="55px">
								<f:facet name="header">
									<h:outputText value="Code"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.client.code}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Shipper" id="col_15" 
								sortBy="#{bl.supplier.name}" width="150px">
								<f:facet name="header">
									<h:outputText value="Shipper"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.supplier.name}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="true" label="Origin" id="col_16" 
								sortBy="#{bl.portOrigin.name}" width="90px">
								<f:facet name="header">
									<h:outputText value="Origin"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.portOrigin.name}"></h:outputText>
							</rich:column>
							
							
							<rich:column sortable="true" label="Destination" id="col_17" 
								sortBy="#{bl.portDestination.name}" width="90px">
								<f:facet name="header">
									<h:outputText value="Destination"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.portDestination.name}"></h:outputText>
							</rich:column>	
							
							<rich:column sortable="true" label="Sales Rep" id="col_18" 
								sortBy="#{bl.salesRep.login}" width="30px">
								<f:facet name="header">
									<h:outputText value="Sales Rep"></h:outputText>
								</f:facet>
								<h:outputText value="#{bl.salesRep.login}"></h:outputText>
							</rich:column>
							
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="BlsDataTable" maxPages="30" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>
						
					</rich:panel>
				</td>
			</tr>
		</table>
		<rich:modalPanel id="selectBlTypePopup" autosized="true" width="200" style="background-color:#F3F8FC;">
			<rich:hotKey key="esc" handler="#{rich:component('selectBlTypePopup') }.hide();" />
	        <f:facet name="header">
	            <h:outputText value="Select the BL Type to create"
	                style="padding-right:15px;" />
	        </f:facet>
	        <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png"
	                    styleClass="hidelink" id="hidelink8" />
	                <rich:componentControl for="selectBlTypePopup" attachTo="hidelink8"
	                    operation="hide" event="onclick" />
	            </h:panelGroup>
	        </f:facet>
        	
        	<a4j:commandLink id="linkNewBlAction"  oncomplete="#{rich:component('selectBlTypePopup')}.hide()"/>
        	<br><br>
       		<div style="position: relative;">
				<div class="headerPanel" align="center" style="top: -8%; width: 25%" >Date Shipped</div>
				<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:262px;" >
					<table cellpadding="3" cellspacing="1" border="0">
		       			<tr>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="Regular" action="#{blControl.assignBlTypeRegular}" oncomplete="#{rich:element('linkNewBlAction')}.click();" ajaxSingle="true" status="none"/>
		       				</td>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="House" action="#{blControl.assignBlTypeHouse}" oncomplete="#{rich:element('linkNewBlAction')}.click();" ajaxSingle="true" status="none"/>
		       				</td>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="Master" action="#{blControl.assignBlTypeMaster}" oncomplete="#{rich:element('linkNewBlAction')}.click();" ajaxSingle="true" status="none"/>
		       				</td>
		       			</tr>
		       		</table>
		       	</rich:panel>
		       	<br>
		       	<br>
	       		<table align="center">
	       			<tr>
	       				<td align="center" >
	       					<a4j:commandButton value="Cancel" onclick="#{rich:component('selectBlTypePopup')}.hide();"/>
	       				</td>
	       			</tr>
	       		</table>
			</div>
	</rich:modalPanel>
	</h:form>	
</div>
</div>
</body>
</html>
</f:view>