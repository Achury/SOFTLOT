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
	<title>Insert title here</title>
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
			
			//awbSearchForm:awbDataTable:tb
			jQuery('[id$="awbDataTable:n"] tr').live("dblclick", function() {
				currentLink = jQuery(this);

				var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");

				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title");

				srcIframe = 'awb.jsp?status=existe';

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
			
			//Permite crear el nuevo AWB
			jQuery('a[id="awbSearchForm:newAwbActionLink"]').live("click", function() {
				currentLink = jQuery(this);

				tab_id = "New_AWB";
				tab_title = "New AWB";
				
				srcIframe = "awb.jsp?status=nuevo";

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});			

			// tabs init with a custom tab template and an "add" callback filling in the content
			var tmp = jQuery("#tabs");
			ttabs = tmp.tabs({
				tabTemplate: "<li><a href='\#{href}'>\#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
				add: function( event, ui ) {
					jQuery( ui.panel ).append(
						'<iframe id = "iframe_' + tab_title + '" width = "100%" height = "' + defaultHeight + 'px" frameborder = "0" src="' + srcIframe + '"></iframe>'
					);
				}
			});

		// actual addTab function: adds new tab using the title input from the form above
		window.addTab = function (tab_id, tab_title) {
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
		};
		
		window.refreshTabName = function (name, id) {
			jQuery("li:has(a[href='#tabs-New_AWB'])").children(":first-child").text(name);
			jQuery("li:has(a[href='#tabs-New_AWB'])").children(":first-child").attr("href","#tabs-"+id);
			jQuery('#tabs-New_AWB').attr("id","tabs-"+id);
			return true;
		};
			

		// close icon: removing the tab on click
		// note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
		jQuery( "#tabs span.ui-icon-close" ).live( "click", function() {
			var index = jQuery( "li", ttabs ).index( jQuery( this ).parent() );
			ttabs.tabs( "remove", index );
		});
		
		jQuery(document).keypress(function(event) { /* Cuando se presione enter se ejecuta la busqueda.*/
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if (keycode == '13') {
		    	searchJS();
		    	return false;
		    }
		});
	});
		
		
	</script>	
</head>
<body onload="focusFirstElement();">
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	 
	<t:saveState value="#{awbControl.awbFilter}"/>
	<t:saveState value="#{awbControl.awbList}"/>
	<t:saveState value="#{awbControl.when}"/>
	<t:saveState value="#{awbControl.awb}"/>
	
<!-- Inicio de los tabs -->
<div id="tabs" >		
<ul>
	<li>
		<a href="#tabs-0">AWB Search</a>
	</li>
</ul>

<div id="tabs-0">
	  
	<h:form id="awbSearchForm">
		<a4j:jsFunction name="searchJS" action="#{awbControl.searchAwbAction}" reRender="awbDataTable, found" status="ajaxStatus"></a4j:jsFunction>
		<table width="99%">		
			<tr>
				<td>
					<a4j:jsFunction name="focusFirstElement" oncomplete="#{rich:element('codeText')}.focus()" status="none" ajaxSingle="true"/>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="awbSearchPanel">	
					    <f:facet name="header">		
					        <h:outputText value="Search AWB"/>
					    </f:facet>	
					    <rich:panel id="awbFilterPanel">	
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
														value="#{awbControl.awbFilter.client.code}" style="width:29px"/>
												</td>
												<td nowrap="nowrap" align="right" width="42px">
													WH No:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="whNumberText" value="#{awbControl.awbFilter.whNumber}" style="width:51px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Client:
												</td>
												<td colspan="3">
													<h:inputText id="clientText"
														value="#{awbControl.awbFilter.client.name}" style="width:126px"/>
													<rich:suggestionbox id="clientSuggestBox" for="clientText" 
														suggestionAction="#{awbControl.autoCompleteClients}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{awbControl.awbFilter.client}"/>
														</a4j:support>
													</rich:suggestionbox>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Shipper:
												</td>
												<td colspan="3">
													<h:inputText id="shipperText"
														value="#{awbControl.awbFilter.supplier.name}" style="width:126px"/>
													<rich:suggestionbox id="shipperSuggestBox" for="shipperText" 
														suggestionAction="#{awbControl.autoCompleteSuppliers}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{awbControl.awbFilter.supplier}"/>
														</a4j:support>
													</rich:suggestionbox>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													AWB Type:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="StatusCombobox" value="#{awbControl.awbFilter.awbType.valueId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{awbControl.awbTypesList}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Sales Rep:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="salesRepCombobox" value="#{awbControl.awbFilter.salesRep.employeeId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{awbControl.employeesList}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
										</table>
									</td>
									
									<td rowspan="2">
										<table cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td nowrap="nowrap" align="right" >
													AWB No.:
												</td>
												<td>
													<h:inputText id="awbNoText"
														value="#{awbControl.awbFilter.awbFullNumberExtern}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Client PO No.:
												</td>
												<td>
													<h:inputText id="clientPoNumText"
														value="#{awbControl.awbFilter.clientPoNumber}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Other Invoices:
												</td>
												<td>
													<h:inputText id="otherInvNumText" value="#{awbControl.awbFilter.otherInvoice}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Freight Invoices:
												</td>
												<td>
													<h:inputText id="freightInvNumText" value="#{awbControl.awbFilter.freightInvoice}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													AWB Int.:
												</td>
												<td>
													<h:inputText id="awbIntText"
														value="#{awbControl.awbFilter.awbNumber}" style="width:100px"/>
												</td>
											</tr>
										</table>	
									</td>
									<td align="left" rowspan="2" valign="top">
										<table cellpadding="0" cellspacing="1" border="0">
											
											<tr>
												<td nowrap="nowrap" align="right" >
													&nbsp;&nbsp;&nbsp;Orig. Airport:
												</td>
												<td>
													<h:selectOneMenu id="airportOriginCombobox" value="#{awbControl.awbFilter.airportOrigin.portId}" style="width:120px">
														<f:selectItem itemLabel="All" itemValue=""/>
														<f:selectItems value="#{awbControl.airportsList}" />
													</h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Dest. Airport:
												</td>
												<td>
													<h:selectOneMenu id="airportDestCombobox" value="#{awbControl.awbFilter.airportDestination.portId}" style="width:120px">
														<f:selectItem itemLabel="All" itemValue=""/>
														<f:selectItems value="#{awbControl.airportsList}" />
													</h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Airline:
												</td>
												<td>
													<h:selectOneMenu id="carrierCombobox" value="#{awbControl.awbFilter.carrier.carrierId }" style="width:120px">
														<f:selectItem itemLabel="" itemValue="" />
														<f:selectItems value="#{awbControl.carriersList}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Remarks:
												</td>
												<td>
													<h:inputText value="#{awbControl.awbFilter.description}" style="width:118px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Region:
												</td>
												<td>
													<h:selectOneMenu id="regionCombobox" value="#{awbControl.awbFilter.region.valueId }" style="width:120px">
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{awbControl.regionsList}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
										</table>
									</td>
									<td colspan="2">
										<div style="position: relative;">
											<div class="headerPanel" align="center" style="top: -8%; width: 25%" >Date Shipped</div>
											<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:262px;" >
												<table cellpadding="0" cellspacing="1" border="0">
													<tr>
														<td nowrap="nowrap" align="left" colspan="2">
															<h:selectOneRadio id="dateFilter" layout="lineDirection" value="#{awbControl.when }">
															 	<f:selectItem itemLabel="Today" itemValue="today" />
															 	<f:selectItem itemLabel="This week" itemValue="thisWeek"/>
															 	<f:selectItem itemLabel="This month" itemValue="thisMonth"/>
															 	<f:selectItem itemLabel="All" itemValue="all"/>
															 	<a4j:support event="onclick" action="#{awbControl.changeDateFilter}" reRender="awbSearchPanel"  ajaxSingle="true" status="none"/>														 		
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
																value="#{awbControl.awbFilter.dateFromFilter }" enableManualInput="true" 
																oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('awbSearchForm:dateFromInputDate');">
																<a4j:support event="oninputclick" action="#{awbControl.changeDateFilter}" reRender="dateFilter" ajaxSingle="true" status="none">
																	<f:setPropertyActionListener value="rango" target="#{awbControl.when}"/>
															 	</a4j:support>
															</rich:calendar>
														</td>
														<td nowrap="nowrap">
															<rich:calendar id="dateTo" cellWidth="20px" inputSize="10" datePattern="MM/d/yyyy" oninputkeypress="return validateNumbers2(event);"
																value="#{awbControl.awbFilter.dateToFilter }" enableManualInput="true" oninputblur="autoCompleteDate2('awbSearchForm:dateToInputDate');"/>
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
													<a4j:commandLink action="#{awbControl.searchAwbAction }" id="searchButton" reRender="awbDataTable, found" status="ajaxStatus">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
								                    </a4j:commandLink>
													
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap">&nbsp;&nbsp;
													<h:outputText id="found"
														value="found:#{awbControl.size}" style="font-size:9px;"/>
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">&nbsp;
													<a4j:commandButton id="clearButton" value="Clear" styleClass="styleButtonFromSearch"  onblur="focusFirstElement();"
														reRender="awbFilterPanel" ajaxSingle="true" status="none" immediate="true" action="#{awbControl.clearFilterFields}"/>
												</td>
											</tr>
										</table>
									</td>	
									<td align="right" rowspan="4" >
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
					<a4j:commandLink  value="New AWB" id="linkNew" style="font-size:11px;" ajaxSingle="true" status="none" onclick="#{rich:component('selectAwbTypemPopup')}.show();"/>
				</td>
			</tr>
			<tr>
				<td>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value="AWB Results"/>	
					    </f:facet>					
						<rich:extendedDataTable id="awbDataTable" height="455px" rowClasses="row1, row2" rows="20"
							title="AWB Results" value="#{awbControl.awbList }" var="awb" reRender="ds"
							sortMode="#{awbControl.sortMode }" selectionMode="#{awbControl.selectionMode }"
							tableState="#{awbControl.tableState }" binding="#{awbControl.awbTable }"> 
							<a4j:support event="onRowDblClick"
								actionListener="#{awbControl.selectAwbActionAux}" />
							<rich:column sortable="true" label="AWB Int" id="col_1" 
								sortBy="#{awb.awbNumber}" width="5.6%">
								<f:facet name="header">
									<h:outputText value="AWB Int"></h:outputText>
								</f:facet>
								<a4j:commandLink rel="#{awb.awbId}" title="#{awb.awbNumber}" id="linkCargar"/>
								<h:outputText value="#{awb.awbNumber}" title="#{awb.awbNumber}"></h:outputText>
							</rich:column>
							<rich:column sortable="false" label="Completed" id="col_21" width="45px" style="text-align:center">
								<f:facet name="header">
									<h:outputText value="Compl" title="completed"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.completed}" title="#{awb.completed}"/>
							</rich:column>
							<rich:column sortable="false" label="Shipment" title="Shipment" width="25px" style="text-align:center" >
								<f:facet name="header">
									<h:outputText value="S" title="Shipment Notification"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.shipmentNotification}" title="#{awb.shipmentNotification}"/>
							</rich:column>
							<rich:column sortable="false" label="Arrival" width="25px" style="text-align:center">
								<f:facet name="header">
									<h:outputText value="A" title="Arrival Notification" ></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.arrivalNotification}" title="#{awb.arrivalNotification}"/>
							</rich:column>
							<rich:column sortable="false" label="Docs. Delivery" width="25px" style="text-align:center">
								<f:facet name="header">
									<h:outputText value="D" title="Docs. Delivery Notif."></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.docsDeliveryNotification}" title="#{awb.docsDeliveryNotification}"/>
							</rich:column>
							<rich:column sortable="false" label="Ship. Delivery" width="30px" style="text-align:center">
								<f:facet name="header">
									<h:outputText value="SD" title="Shipment Delivery Notif."></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.shipmentDeliveryNotification}" title="#{awb.shipmentDeliveryNotification}"/>
							</rich:column>
							<rich:column sortable="false" label="POE" width="34px" style="text-align:center">
								<f:facet name="header">
									<h:outputText value="POE" title="Proof of Export Notif."></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awb.poeNotification}" title="#{awb.poeNotification}"/>
							</rich:column>
							<rich:column sortable="true" label="Freight Inv."
								sortBy="#{awb.concatenatedFreightInvoices2}" width="90px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Freight Inv." title="Freight Invoices"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.concatenatedFreightInvoices2}" title="#{awb.concatenatedFreightInvoices2}"/>
							</rich:column>
							<rich:column sortable="true" label="Date"
								sortBy="#{awb.createdDate}" width="75px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Date" title="Date"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.createdDate}" title="#{awb.createdDate}">
									<f:convertDateTime type="date" pattern="MM/dd/yyyy"/>
								</h:outputText>
							</rich:column>
							<rich:column sortable="false" label="Remarks" width="100px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Remarks" title="Remarks"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.description}" title="#{awb.description}"/>
							</rich:column>
							<rich:column sortable="false" label="Client PO" width="100px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Client PO" title="Client PO"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.clientPoNumber}" title="#{awb.clientPoNumber}"/>
							</rich:column>
							<rich:column sortable="true" label="Airline" sortBy="#{awb.carrier.name}" width="120px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Airline" title="Airline"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.carrier.name}" title="#{awb.carrier.name}"/>
							</rich:column>
							<rich:column sortable="true" label="AWB" sortBy="#{awb.carrier.carrierCode }-#{awb.awbFullNumberExtern}" width="100px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="AWB" title="AWB"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.carrier.carrierCode }-#{awb.awbFullNumberExtern}" title="#{awb.carrier.carrierCode }-#{awb.awbFullNumberExtern}" rendered="#{awb.awbFullNumberExtern != ''}"/>
							</rich:column>
							<rich:column sortable="true" label="Client" sortBy="#{awb.client.code}" width="55px" style="text-align:left">
								<f:facet name="header">
									<h:outputText value="Client" title="Client"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.client.code}" title="#{awb.client.code}"/>
							</rich:column>
							<rich:column sortable="true" label="Shipper" sortBy="#{awb.supplier.name}" width="150px" >
								<f:facet name="header">
									<h:outputText value="Shipper" title="Shipper"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.supplier.name}" title="#{awb.supplier.name}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Dest Airport" id="col_3" sortBy="#{awb.airportDestination.name}" width="90px" >
								<f:facet name="header">
									<h:outputText value="Dest Airport" title="Destination Airport"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.airportDestination.name}" title="#{awb.airportDestination.name}"></h:outputText>
							</rich:column>					
							<rich:column sortable="true" label="Orig Airport" id="col_4" sortBy="#{awb.airportOrigin.name}" width="90px" >
								<f:facet name="header">
									<h:outputText value="Orig Airport" title="Orig Airport"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.airportOrigin.name}" title="#{awb.airportOrigin.name}"></h:outputText>
							</rich:column>
							
							
							<rich:column sortable="false" label="Sales Rep" id="col_12" width="30px" >
								<f:facet name="header">
									<h:outputText value="Sales Rep"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.salesRep.login}"></h:outputText>
							</rich:column>
							<rich:column sortable="false" label="Sales Rep" id="col_13" width="90px" >
								<f:facet name="header">
									<h:outputText value="Type"></h:outputText>
								</f:facet>
								<h:outputText value="#{awb.awbType.value1}"></h:outputText>
							</rich:column>
							
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="awbDataTable" maxPages="30" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>		
					</rich:panel>
				</td>
			</tr>
		</table>
		<rich:modalPanel id="selectAwbTypemPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
	        <f:facet name="header">
	            <h:outputText value="Select AWB Type" style="padding-right:15px;" />
	        </f:facet>
	        <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png"
	                    styleClass="hidelink" id="hidelink8" />
	                <rich:componentControl for="selectAwbTypemPopup" attachTo="hidelink8"
	                    operation="hide" event="onclick" />
	            </h:panelGroup>
	        </f:facet>

        	<a4j:commandLink id="newAwbActionLink"  oncomplete="#{rich:component('selectAwbTypemPopup')}.hide()"/>
        	<br><br>
       		<div style="position: relative;">
				<div class="headerPanel" align="center" style="top: -8%; width: 25%" >AWB Type</div>
				<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:262px;" >
					<table cellpadding="3" cellspacing="1" border="0">
		       			<tr>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="Regular" action="#{awbControl.assignAwbTypeRegular}" oncomplete="#{rich:element('newAwbActionLink')}.click()" ajaxSingle="true" status="none"/>
		       				</td>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="House" action="#{awbControl.assignAwbTypeHouse}" oncomplete="#{rich:element('newAwbActionLink')}.click()" ajaxSingle="true" status="none"/>
		       				</td>
		       				<td style="padding:1px 11px;">
		       					<a4j:commandButton value="Master" action="#{awbControl.assignAwbTypeMaster}" oncomplete="#{rich:element('newAwbActionLink')}.click()" ajaxSingle="true" status="none"/>
		       				</td>
		       			</tr>
		       		</table>
		       	</rich:panel>
		       	<br>
		       	<br>
	       		<table align="center">
	       			<tr>
	       				<td align="center">
	       					<a4j:commandButton value="Cancel" onclick="#{rich:component('selectAwbTypemPopup')}.hide();"/>
	       				</td>
	       			</tr>
	       		</table>
			</div>
	</rich:modalPanel>
	</h:form>
</div>
</div>
<a4j:include id="loadingPopup" viewId="../../../jsp/messages/loadingPopup.jsp" />
</body>
</html>
</f:view>