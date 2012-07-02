<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.rich-table-cell {
			padding: 3px;
		}
	</style>
	
	<script>
		var tab_title;
		var tab_id;

		jQuery(function() {			
			jQuery('[id$="awbLink"]').live("click", function() {		
				objLink = jQuery(this);
				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title");
				parent.srcIframe = 'awb.jsp?status=existe';
				parent.addTab(tab_id, tab_title);
			});
		});
	</script>	
	
</head>
<body>
	<rich:panel id="awbHousesPanel" bodyClass="rich-panel-body2" styleClass="rich-panel2"  rendered="#{awbControl.awb.master}">
		<table height="280px" border="0" >
			<tr>
				<td height="22px" style="padding:0px 6px;">
					<a4j:commandButton value="Add House AWB" reRender="houseAwbsDatatable, awbHousesAvailableDataTable" action="#{awbControl.loadAwbHousesAvailableList}"
						oncomplete="#{rich:component('awbHousesAvailablePopup')}.show();" ></a4j:commandButton>
				</td>
			</tr>
			<tr >
				<td valign="top" >
					<rich:dataTable id="houseAwbsDatatable" rowClasses="row1, row2" rows="20" width="1150px"
						value="#{awbControl.awb.awbHouses}" var="awbHouse" binding="#{awbControl.awbHousesTable}" >
					
						<rich:column sortable="true" label="AWB #" id="col_1" width="75px">
							<f:facet name="header">
								<h:outputText value="AWB #"></h:outputText>
							</f:facet>
							<a4j:commandLink id="awbLink" value="#{awbHouse.awbNumber}" actionListener="#{awbControl.selectHousesFromMaster}" rel="#{awbHouse.awbId}" title="#{awbHouse.awbNumber}" ajaxSingle="true"/>
						</rich:column>
						
						<rich:column sortable="false" label="Client" id="col_2" width="350px">
							<f:facet name="header">
								<h:outputText value="Client"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.client.name}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Supplier" id="col_3" width="290px">
							<f:facet name="header">
								<h:outputText value="Supplier"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.supplier.name}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Client POs" id="col_4" width="220px">
							<f:facet name="header">
								<h:outputText value="Client POs"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.clientPoNumber}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Weight (Kgs)" id="col_5" width="60px" style="text-align: right;">
							<f:facet name="header">
								<h:outputText value="Weight (Kgs)"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.totalWeightKgs}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Weight VLM" id="col_6" width="60px" style="text-align: right;">
							<f:facet name="header">
								<h:outputText value="Weight VLM"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.totalWeightVolKgs}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Pieces" id="col_7" width="40px" style="text-align: right;">
							<f:facet name="header">
								<h:outputText value="Pieces"></h:outputText>
							</f:facet>
							<h:outputText value="#{awbHouse.pieces}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" id="col_8">
							<a4j:commandLink value="remove" action="#{awbControl.removeAwbHouseFromMaster }" reRender="formAwb"/>
						</rich:column>
						
					</rich:dataTable>
				</td>
			</tr>
		</table>
	</rich:panel>
	
	<rich:modalPanel id="awbHousesAvailablePopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true" rendered="#{awbControl.awb.master}">
		<f:facet name="header">
	    	<h:outputText value="Add Awb Houses" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideAwbHousePopupLink" onclick="#{rich:component('awbHousesAvailablePopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>
				<tr>
					<td>
						<rich:extendedDataTable width="500px" id="awbHousesAvailableDataTable" style="white-space: wrap;"
							binding="#{awbControl.awbHousesAvailableTable}" reRender="ds"
							value="#{awbControl.awbHousesAvailableList }" var="awbHouseAvilable" 
							rows="18" title="Awb Houses available">
							
							<rich:column sortable="false" label="Select" id="col_0" width="30px" >
								<f:facet name="header" >
									<h:outputText value="Select" ></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{awbHouseAvilable.selected}" >
								</h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="false" label="AWB Number" id="col_1" width="100px" >
								<f:facet name="header" >
									<h:outputText value="AWB Number" ></h:outputText>
								</f:facet>
								<h:outputText value="#{awbHouseAvilable.awbNumber}" />
							</rich:column>

							<rich:column sortable="false" label="Supplier" id="col_2" width="185px" >
								<f:facet name="header" >
									<h:outputText value="Supplier" ></h:outputText>
								</f:facet>
								<h:outputText value="#{awbHouseAvilable.supplier.name}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="false" label="Client" id="col_3" width="185px" >
								<f:facet name="header" >
									<h:outputText value="Client" ></h:outputText>
								</f:facet>
								<h:outputText value="#{awbHouseAvilable.client.name}"></h:outputText>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="awbHousesAvailableDataTable" maxPages="12" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center">
						<br>
						<a4j:jsFunction name="addAwbHouses" action="#{awbControl.processAwbHouses}" reRender="formAwb"/>
						<a4j:commandButton value="Save" 
							oncomplete="#{rich:component('awbHousesAvailablePopup')}.hide();addAwbHouses()" />
							&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('awbHousesAvailablePopup')}.hide();" status="none"/>
					</td>
				</tr>
			</table>
	</rich:modalPanel>
</body>
</html>