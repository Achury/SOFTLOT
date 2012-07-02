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
	<title>LOTRADING :: CARRIERS SEARCH</title>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/ui-lightness/jquery-ui-1.8.18.custom.css">
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<style type="text/css">
		.rich-panel-body {
			padding:5px;
		}
	</style>
	
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

			jQuery('[id$="carriersDataTable:n"] tr').live("dblclick", function() {
				currentLink = jQuery(this);

				var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");

				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title");

				srcIframe = 'carrierDetails.jsp?status=existe';

				addTab(tab_id, tab_title);
				return false; //Evitar que el navegador siga el link
			});
			
			//Permite crear el nuevo Carrier
			jQuery('a[id="carrierSearchForm:linkNew"]').live("click", function() {
				currentLink = jQuery(this);

				tab_id = "New_Carrier";
				tab_title = "New Carrier";
				
				srcIframe = "carrierDetails.jsp?status=nuevo";

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
	});
	</script>
</head>
<body>
	<t:saveState value="#{carrierControl.carrierFilter }"/>
	<t:saveState value="#{carrierControl.carriers}"/>
	
	<div id="tabs" >		
		<ul>
			<li>
				<a href="#tabs-0">Carriers Search</a>
			</li>
		</ul>
		
		<div id="tabs-0">
	
			<h:form id="carrierSearchForm">
				<table width="99%">		
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="carrierSearchPanel">	
							    <f:facet name="header">		
							        <h:outputText value="Search Carriers"/>	
							    </f:facet>	
							    <rich:panel id="carriersFilterPanel">	
							    	<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: separate;border-spacing:  4px 0px;" >
										<tr>
											<td nowrap="nowrap" align="left" width="60">
												Carrier Name:
											</td>
											<td nowrap="nowrap" align="left" width="200">
												<h:inputText id="carrierSearchText" value="#{carrierControl.carrierFilter.name}" style="width:200px"/>
											</td>
											<td align="right" width="100">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<a4j:commandLink action="#{carrierControl.searchCarriersAction }" id="searchButton" reRender="carriersDataTable" status="ajaxStatus">
										                        <h:graphicImage value="/css/images/refresh.png" style="border:0"/>								                      
										                    </a4j:commandLink>
															<rich:hotKey key="return" handler="#{rich:element('searchButton') }.click();" />
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap">
															<h:outputText id="found"
																value="found:#{carrierControl.size}" style="font-size:9px;"/>
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
						<td align="right" style="padding:0px 1px;">
							<a4j:commandLink value="New Carrier" id="linkNew" style="font-size:11px;padding:0px 1px;" ajaxSingle="true" />
						</td>
					</tr>
					<tr>
						<td>
							<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
							    <rich:extendedDataTable id="carriersDataTable" height="510px" rowClasses="row1, row2" rows="24"
									title="Carriers" value="#{carrierControl.carriers }" var="carrier" reRender="ds"
									sortMode="#{carrierControl.sortMode }" selectionMode="#{carrierControl.selectionMode }"
									tableState="#{carrierControl.tableState }" binding="#{carrierControl.table }"> 
									
									<a4j:support event="onRowDblClick"
										actionListener="#{carrierControl.selectCarrierActionAux}" />
										
									<rich:column sortable="true" label="Name" id="col_1" 
										sortBy="#{carrier.name}" width="20%">
										<f:facet name="header">
											<h:outputText value="Name"></h:outputText>
										</f:facet>
										<a4j:commandLink rel="#{carrier.carrierId}" title="#{carrier.name}" id="linkCargar"/>
										<h:outputText value="#{carrier.name}"></h:outputText>
									</rich:column>
									
									<rich:column sortable="true" label="LOT Account" id="col_2" 
										sortBy="#{carrier.lotAccount}" width="20%" >
										<f:facet name="header">
											<h:outputText value="LOT Account"></h:outputText>
										</f:facet>
										<h:outputText value="#{carrier.lotAccount}"></h:outputText>
									</rich:column>
									
									<rich:column sortable="true" label="Carrier Code" id="col_3" 
										sortBy="#{carrier.carrierCode}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Carrier Code"></h:outputText>
										</f:facet>
										<h:outputText value="#{carrier.carrierCode}"></h:outputText>
									</rich:column>
									
									<rich:column sortable="true" label="IATA Code" id="col_4" 
										sortBy="#{carrier.iataCode}" width="20%" >
										<f:facet name="header">
											<h:outputText value="IATA Code"></h:outputText>
										</f:facet>
										<h:outputText value="#{carrier.iataCode}"></h:outputText>
									</rich:column>
									
									<rich:column sortable="true" label="Carrier Type" id="col_5" 
										sortBy="#{carrier.iataCode}" width="20%" >
										<f:facet name="header">
											<h:outputText value="Carrier Type"></h:outputText>
										</f:facet>
										<h:outputText value="#{carrier.carrierType.value1}"></h:outputText>
									</rich:column>
									
									<f:facet name="footer">
										<rich:datascroller align="center" for="carriersDataTable" maxPages="30" id="ds" status="none"/>
									</f:facet>
								</rich:extendedDataTable>
							</rich:panel>
						</td>
					</tr>
				</table>
			</h:form>
		</div>
	</div>
</body>
</html>
</f:view>