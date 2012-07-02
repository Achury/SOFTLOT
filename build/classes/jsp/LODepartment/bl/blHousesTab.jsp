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
			jQuery('[id$="blLink"]').live("click", function() {		
				objLink = jQuery(this);
				tab_id = objLink.attr("rel");
				tab_title = objLink.attr("title");
				parent.srcIframe = 'bl.jsp?status=existe';
				parent.addTab(tab_id, tab_title);
			});
		});
	</script>	
</head>
<body>
	<rich:panel id="blHousesPanel" bodyClass="rich-panel-body2" styleClass="rich-panel2" rendered="#{blControl.bl.master}"  >
		<table height="235px" border="0" >
			<tr>
				<td height="22px" style="padding:0px 6px;">
					<a4j:commandButton value="Add House BL" reRender="houseBlsDatatable, blHousesAvailableDataTable" action="#{blControl.loadBlHousesAvailableList}" 
						oncomplete="#{rich:component('blHousesAvailablePopup')}.show();"></a4j:commandButton>
				</td>
			</tr>
			<tr >
				<td valign="top" >
					<rich:dataTable id="houseBlsDatatable" rowClasses="row1, row2" rows="20" width="1150px"
						value="#{blControl.bl.blHouses}" var="blHouse" binding="#{blControl.blHousesTable}" >
					
						<rich:column sortable="true" label="BL #" id="col_1" width="75px" >
							<f:facet name="header">
								<h:outputText value="BL #"></h:outputText>
							</f:facet>
							<a4j:commandLink id="blLink" value="#{blHouse.blNumber}" actionListener="#{blControl.selectHousesFromMaster}" rel="#{blHouse.blId}" title="#{blHouse.blNumber}" ajaxSingle="true"/>
						</rich:column>
						
						<rich:column sortable="false" label="Client" id="col_2" width="290px">
							<f:facet name="header">
								<h:outputText value="Client"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.client.name}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Supplier" id="col_3" width="290px">
							<f:facet name="header">
								<h:outputText value="Supplier"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.supplier.name}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Client POs" id="col_4" width="220px">
							<f:facet name="header">
								<h:outputText value="Client POs"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.clientPoNumber}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Weight" id="col_5" width="60px">
							<f:facet name="header">
								<h:outputText value="Weight [Kg])"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.totalRealWKg}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Volume" id="col_6" width="60px">
							<f:facet name="header">
								<h:outputText value="Volume [M3]"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.totalOceanVolM3}">
							</h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Pieces" id="col_7" width="40px">
							<f:facet name="header">
								<h:outputText value="Pieces"></h:outputText>
							</f:facet>
							<h:outputText value="#{blHouse.totalPieces}">
							</h:outputText>
						</rich:column>
						<rich:column sortable="false" label="Container" id="col_8" width="68px" rendered="#{blControl.bl.FCL && blControl.bl.houseLCL}">
							<f:facet name="header">
								<h:outputText value="Container"></h:outputText>
							</f:facet>
							<h:selectOneMenu  id="containerCombobox" value="#{blHouse.blMasterContainer.containerId}"  rendered="#{blControl.bl.houseLCL}" style="width:68px; background-color: transparent;"
		                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							<f:selectItem itemLabel="" itemValue="" />
							<f:selectItems value="#{blControl.containers}"/>
		                </h:selectOneMenu>
						</rich:column>
						
						<rich:column sortable="false" id="col_9" width="22px">
							<a4j:commandLink action="#{blControl.removeBlHouseFromMaster }" reRender="houseBlsDatatable,costSalesHousePanel2,freightInvText,merchandInvText,unCodesDataTable,eeiDataTable,blContainersDataTable">
								<h:graphicImage value="/css/images/Delete.png" style="border:0"/>
							</a4j:commandLink>
						</rich:column>
						
					</rich:dataTable>
				</td>
			</tr>
		</table>
	</rich:panel>
	
	<rich:modalPanel id="blHousesAvailablePopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true">
		<f:facet name="header">
	    	<h:outputText value="Add House Bls" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideBlHousePopupLink" onclick="#{rich:component('blHousesAvailablePopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>    		
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>
				<tr>
					<td>
						<rich:extendedDataTable width="500px" id="blHousesAvailableDataTable" style="white-space: wrap;"
							binding="#{blControl.blHousesAvailableTable}" reRender="ds"
							value="#{blControl.blHousesAvailableList }" var="blHouseAvailable" 
							rows="18" title="House Bls available"  >
							
							<rich:column sortable="false" label="Select" id="col_0" width="30px" >
								<f:facet name="header" >
									<h:outputText value="Select" ></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{blHouseAvailable.selected}" >
								</h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column sortable="false" label="BL Number" id="col_1" width="100px" >
								<f:facet name="header" >
									<h:outputText value="BL Number" ></h:outputText>
								</f:facet>
								<h:outputText value="#{blHouseAvailable.blNumber}" />
							</rich:column>

							<rich:column sortable="false" label="Supplier" id="col_2" width="185px" >
								<f:facet name="header" >
									<h:outputText value="Supplier" ></h:outputText>
								</f:facet>
								<h:outputText value="#{blHouseAvailable.supplier.name}"></h:outputText>
							</rich:column>
							
							<rich:column sortable="false" label="Client" id="col_3" width="185px" >
								<f:facet name="header" >
									<h:outputText value="Client" ></h:outputText>
								</f:facet>
								<h:outputText value="#{blHouseAvailable.client.name}"></h:outputText>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="blHousesAvailableDataTable" maxPages="12" id="ds" status="none"/>
							</f:facet>
						</rich:extendedDataTable>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center">
						<br>
						<a4j:jsFunction name="addBlHouses" action="#{blControl.processBlHouses}" reRender="formBl"/>
						<a4j:commandButton value="Save" 
							 oncomplete="#{rich:component('blHousesAvailablePopup')}.hide();addBlHouses()" />
						&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('blHousesAvailablePopup')}.hide();" status="none"/>
					</td>
				</tr>
			</table>
	</rich:modalPanel>
	
</body>
</html>