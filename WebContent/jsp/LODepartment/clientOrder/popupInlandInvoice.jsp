<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<body>
	<t:saveState value="#{clienOrderControl.inlandCS }"/>
	<t:saveState value="#{clienOrderControl.supplierInfo }"/>	
	
	<rich:modalPanel id="inlandInfoPopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true">
		
		<f:facet name="header">
	    	<h:outputText value="Inland Info" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideInlandPopupLink" onclick="#{rich:component('inlandInfoPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
       	
    	<h:form id="formInlandInfoPopup">
    		<rich:hotKey key="esc" handler="alert('holsss')" type="onkeypress"/>
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>
				<tr>
					<td align="right">
						<a4j:jsFunction name="newItemInlandCS" action="#{clienOrderControl.newInlandCSAction}" reRender="inlandInfoDataTable" status="none"></a4j:jsFunction>
					</td>
				</tr>
				<tr>
					<td>
						<rich:extendedDataTable width="600px" height="240px" id="inlandInfoDataTable" 
							value="#{clienOrderControl.clientOrder.inlandCostSaleList }" var="inlandCS"
							rows="20" title="Inland info" rowClasses="row1,row2" binding="#{clienOrderControl.tableInlandCS}">
							
							<rich:column sortable="false" label="Cost" id="col_1" width="12%" >
								<f:facet name="header">
									<h:outputText value="Cost" ></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{inlandCS.cost}" id="itemCost" style="width:60px" 
			                 		onkeydown="if(event.keyCode == 13){newItemInlandCS();return false;}" onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
			                 		<f:converter converterId="#{clienOrderControl.converterName }"/>
			                	</h:inputText>	
							</rich:column>
							
							<rich:column sortable="false" label="Sale" id="col_2" width="12%" >
								<f:facet name="header">
									<h:outputText value="Sale"></h:outputText>
								</f:facet>				                        
			                    <h:inputText styleClass="styleInput6" value="#{inlandCS.sales}" id="itemSale" style="width:60px" 
			                 		onkeydown="if(event.keyCode == 13){newItemInlandCS();return false;}" onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
			                 		<f:converter converterId="#{clienOrderControl.converterName }"/>
			                	</h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Truck Company" id="col_3" width="36%" >
								<f:facet name="header">
									<h:outputText value="Truck Company"></h:outputText>
								</f:facet>
								<h:selectOneMenu id="truckCompList" value="#{inlandCS.truckCompany.valueId}" style="width:200px" onkeydown="if(event.keyCode == 13){newItemInlandCS();return false;}" >
									<f:selectItem itemLabel="" itemValue=""/>
									<f:selectItems value="#{clienOrderControl.truckCompanies}"/>
								</h:selectOneMenu>
							</rich:column>
							
							<rich:column sortable="false" label="Tracking Number" id="col_4" width="33%" >
								<f:facet name="header">
									<h:outputText value="Tracking Number" ></h:outputText>
								</f:facet> 				                        
			                    <h:inputText styleClass="styleInput6" value="#{inlandCS.trackingNumber}" id="itemTrackingNumber" style="width:183px" 
			                 		onkeydown="if(event.keyCode == 13){newItemInlandCS();return false;}" onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
			                	</h:inputText>	
							</rich:column>	
							<rich:column width="5%">
								<h:selectBooleanCheckbox></h:selectBooleanCheckbox>
							</rich:column>		
							<rich:column width="5%">
								<a4j:commandLink ajaxSingle="true" id="deleteInlandCSLink" 
									action = "#{clienOrderControl.selectToDeleteInlandCSAction}"
									oncomplete="#{rich:component('ConfirmDeleteInlandPopup')}.show()"
									status="none">										
									<h:graphicImage value="/css/images/Delete.png" style="border:0" />		
								</a4j:commandLink>
								<rich:toolTip for="deleteInlandCSLink" value="Delete Inland" direction="bottom-left" followMouse="true" />
							</rich:column>								
						</rich:extendedDataTable>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td width="100px;" nowrap="nowrap">
									<h:outputLabel value="TOTAL" style="font-weight: bold;"/>
								</td>
								<td align="center" width="100px;" nowrap="nowrap">
									Cost: <h:inputText id="inlandCostPopupText" value="#{clienOrderControl.totalInlandCosts }" readonly="true" style="background-color:#FFFFFF; color:#000000; width:60px;">
										<f:converter converterId="#{clienOrderControl.converterName }"/>
									</h:inputText>
								</td>
								<td align="center" width="160px;" nowrap="nowrap">
									Sale: <h:inputText id="inlandSalePopupText" value="#{clienOrderControl.totalInlandSales }" readonly="true" style="background-color:#FFFFFF; color:#000000; width:60px;">
										<f:converter converterId="#{clienOrderControl.converterName }"/>
									</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center" >
						<a4j:commandButton value="Save" reRender="inlandInfoDataTable,inlandCostText,inlandSaleText,inlandCostPopupText,inlandSalePopupText" action="#{clienOrderControl.saveInlandCSAction }"/>
						&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('inlandInfoPopup') }.hide();" reRender="inlandInfoDataTable,inlandCostText,inlandSaleText" status="none"/>
					</td>
				</tr>
			</table>
		</h:form>
	 </rich:modalPanel>
	 
	 
	 <rich:modalPanel id="ConfirmDeleteInlandPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="ConfirmDeleteInlandPopup" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{clienOrderControl.deleteInlandCSAction}"
	                            onclick="#{rich:component('ConfirmDeleteInlandPopup')}.hide();"
	                            reRender="inlandInfoDataTable,inlandCostText,inlandSaleText,inlandCostPopupText,inlandSalePopupText" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel" status="none"
                            onclick="#{rich:component('ConfirmDeleteInlandPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>   
    
    
  	<rich:modalPanel id="supplierInvoicePopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true">
		<f:facet name="header">
	    	<h:outputText value="Supplier Invoice"/>
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideSuppInvoicePopupLink" onclick="#{rich:component('supplierInvoicePopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
    	<h:form id="formSuppInvoicePopup">
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<rich:hotKey key="esc" handler="#{rich:component('supplierInvoicePopup') }.hide();" selector="#formSuppInvoicePopup"/>
			<table>
				<tr>
					<td align="right">
						<a4j:jsFunction name="newItemSuppInv" action="#{clienOrderControl.newSupplierInfoAction}" reRender="suppInvoiceDataTable" status="none"></a4j:jsFunction>
					</td>
				</tr>
				<tr>
					<td>
						<rich:extendedDataTable width="480px" height="110px" id="suppInvoiceDataTable" 
							value="#{clienOrderControl.clientOrder.supplierInfoList }" var="SuppInvoice"
							rows="20" title="Supplier Invoice" rowClasses="row1,row2" binding="#{clienOrderControl.tableSuppInvoice}">
							
							<rich:column sortable="false" label="Invoice Number" id="col_1" width="30%" >
								<f:facet name="header">
									<h:outputText value="Supplier Invoice #" ></h:outputText>
								</f:facet>				                        
			                    <h:inputText styleClass="styleInput6" value="#{SuppInvoice.supplierInvoiceNum}" id="itemSuppInvNum" style="width:130px" 
			                 		onkeydown="if(event.keyCode == 13){newItemSuppInv();return false;}" onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
			                	</h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Supplier Invoice $" id="col_2" width="27%" >
								<f:facet name="header">
									<h:outputText value="Supplier Invoice $"></h:outputText>
								</f:facet>			                        
			                    <h:inputText styleClass="styleInput6" value="#{SuppInvoice.totalSupplierInvoice}" id="itemSuppInv" style="width:120px" 
			                 		onkeydown="if(event.keyCode == 13){newItemSuppInv();return false;}" onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
			                 		<f:converter converterId="#{clienOrderControl.converterName }"/>
			                	</h:inputText>
							</rich:column>
							<rich:column width="38%" sortable="false" id="col_3">
								<h:selectOneRadio value="#{SuppInvoice.invoiceType.valueId}">
									<f:selectItem value="#{clienOrderControl.invoiceTypeOriginal }"/>
									<f:selectItem value="#{clienOrderControl.invoiceTypeCopy }"/>
									<f:selectItem itemLabel="None" />
								</h:selectOneRadio>
							</rich:column>
							<rich:column width="8%" sortable="false" id="col_4">
								<a4j:commandLink ajaxSingle="true" id="deleteSuppInvLink" 
									action="#{clienOrderControl.selectToDeleteSupplierInfoAction}"
									oncomplete="#{rich:component('ConfirmDeleteSuppInvPopup')}.show()" status="none">										
									<h:graphicImage value="/css/images/Delete.png" style="border:0" />		
								</a4j:commandLink>
								<rich:toolTip for="deleteSuppInvLink" value="Delete Item" direction="bottom-left" followMouse="true" />
							</rich:column>								
						</rich:extendedDataTable>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center" >
						<table>
							<tr>
								<td width="100px;" nowrap="nowrap" align="right">
									<h:outputLabel value="TOTAL" style="font-weight: bold;"/>
								</td>
								<td align="left" width="150px;" nowrap="nowrap">
									&nbsp;&nbsp;&nbsp;<h:inputText id="totalInvoicePopupText" value="#{clienOrderControl.totalSuppInvoice }" readonly="true" style="background-color:#FFFFFF; color:#000000; width:60px;">
										<f:converter converterId="#{clienOrderControl.converterName }"/>
									</h:inputText>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center" >
						<a4j:commandButton value="Save" reRender="suppInvoiceDataTable,suppInfoText,totalInvoicePopupText" action="#{clienOrderControl.saveSupplierInfoAction }"/>
						&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('supplierInvoicePopup') }.hide();" reRender="suppInfoText,suppInvoiceDataTable" status="none"/>
					</td>
				</tr>
			</table>
		</h:form>
	 </rich:modalPanel>
	 
	 
	  <rich:modalPanel id="ConfirmDeleteSuppInvPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink3" />
                <rich:componentControl for="ConfirmDeleteSuppInvPopup" attachTo="hidelink3"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{clienOrderControl.deleteSupplierInfoAction}"
	                            onclick="#{rich:component('ConfirmDeleteSuppInvPopup')}.hide();"
	                            reRender="suppInvoiceDataTable,suppInfoText,totalInvoicePopupText" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick="#{rich:component('ConfirmDeleteSuppInvPopup')}.hide();return false;" status="none"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
</body>
</html>