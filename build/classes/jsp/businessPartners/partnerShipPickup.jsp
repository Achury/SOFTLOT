<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
<script type="text/javascript" src="../../js/partners.js"></script>
</head>
<body>
	<rich:panel style="border:0px" styleClass="styleGeneralPanel">
		<table width="100%">
			<tr>
				<td >
					<h:outputLabel value="Search #{partnersControl.shipPickLabel }: "/>
					<h:inputText id="searchShipToText" value="#{partnersControl.filtroShipPickup }" onkeydown="if(event.keyCode == 13){ #{rich:element('searchButtonShipPick') }.click();}"/>
					<a4j:commandButton id="searchButtonShipPick" value="Search" action="#{partnersControl.filterShipPickup }" reRender="ShipsPickupTable"/>
				</td>
				<td align="right" valign="bottom">							
					<a4j:commandLink action="#{partnersControl.newShipPickupAction}" value="New #{partnersControl.shipPickLabel }" id="linkNewShipto" 
						oncomplete="#{rich:component('editShipToPanel')}.show()" reRender="editShipToPanel" status="none" ajaxSingle="true"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<rich:extendedDataTable id="ShipsPickupTable" height="397px" width="100%"
							value="#{partnersControl.shipPickupsFiltered}" var="ShipPickUp"
							rows="16" sortMode="#{partnersControl.sortMode}" reRender="ds"
							selectionMode="#{partnersControl.selectionMode}"
							tableState="#{partnersControl.tableStateShipPickup}"
							binding="#{partnersControl.tableShipPickup}" 
							headerClass="#{partnersControl.styleTableHeader }"
							rowClasses="row1,row2" title="#{partnersControl.shipPickLabel }">
						<a4j:support event="onRowDblClick"
							action="#{partnersControl.selectShipPickupAction}"
							oncomplete="#{rich:component('editShipToPanel')}.show()" reRender="editShipToPanel"/>	
			
						<rich:column sortable="true" label="Ship to" id="col_1"
							sortBy="#{ShipPickUp.name }" width="288px" >
							<f:facet name="header">
								<h:outputText value="#{partnersControl.shipPickLabel }"></h:outputText>
							</f:facet>
							<h:outputText value="#{ShipPickUp.name}"></h:outputText>
						</rich:column>
						<rich:column sortable="true" label="Address" id="col_2"
							sortBy="#{ShipPickUp.address.address}" width="900px" >
							<f:facet name="header">
								<h:outputText value="Address"></h:outputText>
							</f:facet>
							<h:outputText value="#{ShipPickUp.address.address}"></h:outputText>
						</rich:column>	
						<rich:column width="30px">
							<a4j:commandLink ajaxSingle="true" id="deleteShiptolink" 
								rendered="#{partnersControl.linkDeleteContactVisible }"
								action = "#{partnersControl.selectShipPickupAction}"							
								oncomplete="#{rich:component('ConfirmDelete')}.show()"
								status="none">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />	
										
							</a4j:commandLink>
							<rich:toolTip for="deletelink" value="Delete" />
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller align="center"
								for="ShipsPickupTable" maxPages="10" id="ds" boundaryControls="hide"/>
						</f:facet>
					</rich:extendedDataTable>
				</td>
			</tr>
		</table>	
	</rich:panel>
	
	<rich:modalPanel id="ConfirmDelete" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="ConfirmDelete" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{partnersControl.disableShipPickupAction}"
	                            onclick="#{rich:component('ConfirmDelete')}.hide();" reRender="ShipsPickupTable"/>
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick="#{rich:component('ConfirmDelete')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
</body>
</html>