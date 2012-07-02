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
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/clientOrder.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/ui-lightness/jquery-ui-1.8.18.custom.css">
	
	<script type="text/javascript" src="../../../js/clientOrder.js"></script>	
	<script type="text/javascript" src="../../../js/general.js"></script>	
	
	<a4j:loadScript src="../../../js/jquery/jquery-1.7.1.min.js"></a4j:loadScript>
	<script type="text/javascript">
	 	jQuery = jQuery.noConflict();
	</script>
	
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.core.js"></a4j:loadScript>
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.widget.js"></a4j:loadScript>
	<a4j:loadScript src="../../../js/jquery/ui/jquery.ui.tabs.js"></a4j:loadScript>
	
	
	<script>
		
		var currentLink;
		var tab_title;
		var tab_id;
		
		var srcIframe;

		jQuery(function() {
			var defaultHeight = jQuery(window).height() - 29;

			//formClients:partnerDataTable:tb
			jQuery('[id$="OrdersDataTable:n"] tr').live("dblclick", function() {
				currentLink = jQuery(this);

				var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");

				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title");

				srcIframe = 'clientOrder.jsp?status=existe';

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
			
			//Permite crear el nuevo cliente
			jQuery('a[id="formClientOrdSearch:linkNew"]').live("click", function() {
				currentLink = jQuery(this);

				tab_id = "New_Client_Order";
				tab_title = "New Client Order";
				
				srcIframe = "clientOrder.jsp?status=nuevo";

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
					searchOrdersJS();
				}
			});
		});
	});
	</script>	
	
	<title>LOTRADING :: CLIENT ORDERS</title>
</head>
<body onload="focusFirstElement();">
	<%
		session.removeAttribute("RECURSO_DESPLEGADO");
		session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
	%>
	
	<t:saveState value="#{clienOrderControl.clientOrder }"/>
	<t:saveState value="#{clienOrderControl.clientOrderFilter }"/>
	<t:saveState value="#{clienOrderControl.clientOrders }"/>
	<t:saveState value="#{clienOrderControl.when}" />
	
<!-- Inicio de los tabs -->
<div id="tabs" >		
<ul>
	<li>
		<a href="#tabs-0">Client Orders Search</a>
	</li>
</ul>

<div id="tabs-0">	

	<h:form id="formClientOrdSearch">		
		<table width="99%">		
			<tr>
				<td>
					<a4j:jsFunction name="focusFirstElement" oncomplete="#{rich:element('codeText')}.focus();" status="none" ajaxSingle="true"/>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="clientOrdSearchPanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Client Orders"/>	
					    </f:facet>	
					    <rich:panel id="clientOrdFilterPanel">	
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
														value="#{clienOrderControl.clientOrderFilter.client.code}" style="width:24px"/>
												</td>
												<td nowrap="nowrap" align="right" width="42px">
													WH No:
												</td>
												<td nowrap="nowrap" align="left">
													<h:inputText id="whNumberText"
														 style="width:56px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Client:
												</td>
												<td colspan="3">
													<h:inputText id="clientText"
														value="#{clienOrderControl.clientOrderFilter.client.name}" style="width:126px"/>
													<rich:suggestionbox id="clientSuggestBox" for="clientText" 
														suggestionAction="#{clienOrderControl.autoCompleteClients}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{clienOrderControl.clientOrderFilter.client}"/>
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
														value="#{clienOrderControl.clientOrderFilter.supplier.name}" style="width:126px"/>
													<rich:suggestionbox id="shipperSuggestBox" for="shipperText" 
														suggestionAction="#{clienOrderControl.autoCompleteSuppliers}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{clienOrderControl.clientOrderFilter.supplier}"/>
														</a4j:support>
													</rich:suggestionbox>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Status:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="StatusCombobox" value="#{clienOrderControl.clientOrderFilter.status.valueId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{clienOrderControl.status}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Sales Rep:
												</td>
												<td colspan="3">
													<h:selectOneMenu id="salesRepCombobox" value="#{clienOrderControl.clientOrderFilter.salesRep.employeeId }" style="width:128px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{clienOrderControl.employees}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
										</table>
									</td>
									
									<td rowspan="2">
										<table cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td nowrap="nowrap" align="right" >
													Invoice No:
												</td>
												<td>
													<h:inputText id="InvoiceText"
														value="#{clienOrderControl.clientOrderFilter.invoiceId}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													AWB No:
												</td>
												<td>
													<h:inputText id="awbText"
														value="#{clienOrderControl.clientOrderFilter.awbNumber}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													BL No:
												</td>
												<td>
													<h:inputText id="blText"
														value="#{clienOrderControl.clientOrderFilter.blNumber}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Client PO No:
												</td>
												<td>
													<h:inputText id="clientPoText"
														value="#{clienOrderControl.clientOrderFilter.numberPO}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Destination:
												</td>
												<td>
													<h:inputText id="destinationText"
														value="#{clienOrderControl.clientOrderFilter.destinationCity.name}" style="width:100px"/>
													<rich:suggestionbox id="destinSuggestBox" for="destinationText" 
														suggestionAction="#{clienOrderControl.autocompleteCity}" var="result"
														tokens="," height="150" width="160" cellpadding="2"
														cellspacing="2"   shadowOpacity="4" shadowDepth="4"
														minChars="3" rules="none" nothingLabel="no matches found"
														status="none">
														<h:column>
															<h:outputText value="#{result.name}" style="font-style:bold" />
														</h:column>								
														<a4j:support event="onselect" ajaxSingle="true" status="none">  
															<f:setPropertyActionListener value="#{result }" target="#{clienOrderControl.clientOrderFilter.destinationCity}"/>
														</a4j:support>
													</rich:suggestionbox>
												</td>
											</tr>
										</table>	
									</td>
									<td align="left" rowspan="2" valign="top">
										<table cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td nowrap="nowrap" align="right" >
													Remarks:
												</td>
												<td>
													<h:inputText id="remarksText"
														value="#{clienOrderControl.clientOrderFilter.remarks}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Order No:
												</td>
												<td>
													<h:inputText id="orderNoText"
														value="#{clienOrderControl.clientOrderFilter.clientOrderNo}" style="width:100px"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													Region:
												</td>
												<td>
													<h:selectOneMenu id="regionCombobox" value="#{clienOrderControl.clientOrderFilter.region.valueId }" style="width:102px" >
														<f:selectItem itemLabel="All" itemValue="" />
														<f:selectItems value="#{clienOrderControl.regions}"/>
							                        </h:selectOneMenu>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right" >
													No Inland:
												</td>
												<td>
													<h:selectBooleanCheckbox value="#{clienOrderControl.clientOrderFilter.inlandSaleZero}"/>
												</td>
											</tr>
										</table>
									</td>
									<td colspan="2">
										<div style="position: relative;">
											<div class="headerPanel" align="center" style="top: -8%; width: 20%" > Order Date</div>
											<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:262px;" >
												<table cellpadding="0" cellspacing="1" border="0">
													<tr>
														<td nowrap="nowrap" align="left" colspan="2">
															<h:selectOneRadio id="dateFilter" layout="lineDirection" value="#{clienOrderControl.when }">
															 	<f:selectItem itemLabel="Today" itemValue="today"/>
															 	<f:selectItem itemLabel="This week" itemValue="thisWeek"/>
															 	<f:selectItem itemLabel="This month" itemValue="thisMonth"/>
															 	<f:selectItem itemLabel="All" itemValue="all"/>
															 	<a4j:support event="onclick" action="#{clienOrderControl.changeDateFilter}" reRender="dateFrom,dateTo" ajaxSingle="true" status="none"/>														 		
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
																value="#{clienOrderControl.clientOrderFilter.dateFromFilter }" enableManualInput="true" 
																oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('formClientOrdSearch:dateFromInputDate');">
																<a4j:support event="oninputclick" action="#{clienOrderControl.changeDateFilter}" reRender="dateFilter" ajaxSingle="true" status="none">
																	<f:setPropertyActionListener value="rango" target="#{clienOrderControl.when}"/>
															 	</a4j:support>
															</rich:calendar>
														</td>
														<td nowrap="nowrap">
															<rich:calendar id="dateTo" cellWidth="20px" inputSize="10" datePattern="MM/d/yyyy" oninputkeypress="return validateNumbers2(event);"
																value="#{clienOrderControl.clientOrderFilter.dateToFilter }" enableManualInput="true" oninputblur="autoCompleteDate2('formClientOrdSearch:dateToInputDate');"/>
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
													<a4j:commandLink action="#{clienOrderControl.searchClientOrderAction }" id="searchButton" reRender="OrdersDataTable,found" status="ajaxStatus">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
								                    </a4j:commandLink>
													<a4j:jsFunction name="searchOrdersJS" action="#{clienOrderControl.searchClientOrderAction }" reRender="OrdersDataTable,found"/>
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap">&nbsp;&nbsp;
													<h:outputText id="found"
														value="found:#{clienOrderControl.size}" style="font-size:9px;"/>
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">&nbsp;
													<a4j:commandButton id="clearButton" value="Clear" styleClass="styleButtonFromSearch"  onblur="focusFirstElement();"
														reRender="clientOrdFilterPanel" ajaxSingle="true" status="none" immediate="true" action="#{clienOrderControl.clearFilterFields}" />
												</td>
											</tr>
										</table>
									</td>	
									<td align="right" rowspan="4">
										<h:graphicImage value="/css/images/logo.png" width="180" height="70" />
									</td>		
								</tr>
								<tr>											
									<td nowrap="nowrap">
										<h:selectBooleanCheckbox value="#{clienOrderControl.clientOrderFilter.showShipped }" />
										<h:outputText value="Shipped Orders" />
									</td>
									
									<td nowrap="nowrap">												
										<h:selectBooleanCheckbox value="#{clienOrderControl.clientOrderFilter.showCancelled }"/>
										<h:outputText value="Cancelled Orders" />
									</td>
								</tr>
							</table>	
						</rich:panel>			
					</rich:panel>
				</td>
			</tr>
			<tr>
				<td align="right">
					<a4j:commandLink  value="New Client Order" id="linkNew" style="font-size:11px;" 
						 ajaxSingle="true" action="#{clienOrderControl.newClientOrderAction }"/>
				</td>
			</tr>
			<tr>
				<td>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value="Client Orders"/>	
					    </f:facet>					
						<rich:extendedDataTable id="OrdersDataTable" height="455px" rowClasses="row1, row2" rows="21"
							title="Client Orders" value="#{clienOrderControl.clientOrders }" var="clientOrd" reRender="ds"
							sortMode="#{clienOrderControl.sortMode }" selectionMode="#{clienOrderControl.selectionMode }"
							tableState="#{clienOrderControl.tableState }" binding="#{clienOrderControl.table }"> 
							
							<a4j:support event="onRowDblClick" actionListener="#{clienOrderControl.selectClientOrderActionAux}" />
							
							<rich:column sortable="true" label="Order No" id="col_1" 
								sortBy="#{clientOrd.clientOrderNo}" width="5.6%">
								<f:facet name="header">
									<h:outputText value="Order No"></h:outputText>
								</f:facet>
								<a4j:commandLink rel="#{clientOrd.clientOrderId}" title="#{clientOrd.clientOrderNo}" id="linkCargar"/>
								<h:outputText value="#{clientOrd.clientOrderNo}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Order Date" id="col_2" 
								sortBy="#{clientOrd.createdDate}" width="6.8%" >
								<f:facet name="header">
									<h:outputText value="Order Date"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.createdDate}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="PO Number" id="col_3" 
								sortBy="#{clientOrd.numberPO}" width="10%" >
								<f:facet name="header">
									<h:outputText value="PO Number"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.numberPO}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Code" id="col_4" 
								sortBy="#{clientOrd.client.code}" width="3.6%" >
								<f:facet name="header">
									<h:outputText value="Code"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.client.code}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Supplier" id="col_5" 
								sortBy="#{clientOrd.supplier.name}" width="15%" >
								<f:facet name="header">
									<h:outputText value="Supplier"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.supplier.name}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Status" id="col_6" 
								sortBy="#{clientOrd.status.value1}" width="8%" >
								<f:facet name="header">
									<h:outputText value="Status"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.status.value1}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Pickup Date" id="col_7" 
								sortBy="#{clientOrd.pickupDate}" width="7.8%" >
								<f:facet name="header">
									<h:outputText value="Pickup Date"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.pickupDate}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Schedule Date" id="col_8" 
								sortBy="#{clientOrd.scheduledPickupDate}" width="9%" >
								<f:facet name="header">
									<h:outputText value="Schedule Date"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.scheduledPickupDate}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="ETA" id="col_9" 
								sortBy="#{clientOrd.eta}" width="7%" style="background:#{clientOrd.etaDateLess}">
								<f:facet name="header">
									<h:outputText value="ETA"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.eta}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="WH Receipt #" id="col_10" 
								sortBy="#{clientOrd.eta}" width="9%">
								<f:facet name="header">
									<h:outputText value="WH Receipt #"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.eta}" ></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="LOT invoice #" id="col_11" 
								sortBy="#{clientOrd.invoiceId}" width="9%" >
								<f:facet name="header">
									<h:outputText value="LOT invoice #"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.invoiceId}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Sales Rep" id="col_12" 
								sortBy="#{clientOrd.salesRep.firstName}" width="10%" >
								<f:facet name="header">
									<h:outputText value="Sales Rep"></h:outputText>
								</f:facet>
								<h:outputText value="#{clientOrd.salesRep.firstName} #{clientOrd.salesRep.lastName}"></h:outputText>
							</rich:column>
							
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="OrdersDataTable" maxPages="30" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>		
					</rich:panel>
				</td>
			</tr>
		</table>
	</h:form>
	
</div>
</div>
	<a4j:include id="loadingPopup" viewId="../../../jsp/messages/loadingPopup.jsp" />
</body>
</html>
</f:view>