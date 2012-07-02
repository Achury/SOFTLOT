<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<rich:modalPanel id="freightInvPopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true">
		<f:facet name="header">
	    	<h:outputText value="Freight Invoice" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideFreightInvPopupLink" onclick="#{rich:component('freightInvPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
       	<h:form id="formFreightInvPopup">
    		<rich:hotKey key="esc" handler="#{rich:component('freightInvPopup')}.hide();"/>
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>				
				<tr>
					<td>
						<rich:dataTable width="150px" id="freightInvDataTable"   style="white-space: wrap;"
							value="#{blControl.bl.blFreightInvoices}" var="freightInv" 
							rows="#{blControl.rowsFI}"   rowClasses="row1,row2" binding="#{blControl.blFreightInvoicesTable }">
							
							<rich:column sortable="false" label="Freight Invoices" id="col_1" width="120px" >
								<f:facet name="header">
									<h:outputText value="Freight Invoices" ></h:outputText>
								</f:facet>
								<h:inputText  id="itemFreightInvText" value="#{freightInv.invoice.invoiceNumber}" style="width:120px; background-color:transparent" />
								<rich:suggestionbox id="itemFreightInvSuggestBox" for="itemFreightInvText" 
									suggestionAction="#{blControl.autoCompleteInvoices}" var="result"
									tokens="," height="200" width="120" cellpadding="2"
									cellspacing="2"   shadowOpacity="4" shadowDepth="4"
									minChars="2" rules="none" nothingLabel="no matches found"
									status="none" >
									<h:column>
										<h:outputText value="#{result.invoiceNumber}" style="font-style:bold" />
									</h:column>
									<a4j:support event="onselect" ajaxSingle="true" status="none" reRender="freightInvDataTable" action="#{blControl.newItemFreightInvoiceAction}">  
										<f:setPropertyActionListener value="#{result }" target="#{freightInv.invoice}"/>
									</a4j:support>
								</rich:suggestionbox>	
							</rich:column>		
																			
							<rich:column width="18px" id="col_2">
								<a4j:commandLink ajaxSingle="true" id="deleteFreightInvLink" 
									action = "#{blControl.selectToDeleteFIAction}" status="none"
									oncomplete="#{rich:component('deleteFreightInvConfirmPopup')}.show();">										
									<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
								</a4j:commandLink>
							</rich:column>
						</rich:dataTable>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center" >
						<a4j:commandButton value="Save" action="#{blControl.saveFreightInvoicesAction }" reRender="freightInvText"
						data="#{facesContext.maximumSeverity.ordinal ge 2}" oncomplete="if (data == false){#{rich:component('freightInvPopup')}.hide();}"/>
						&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('freightInvPopup') }.hide();" status="none"/>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="deleteFreightInvConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink8" />
                <rich:componentControl for="deleteFreightInvConfirmPopup" attachTo="hidelink8"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="right" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteFreightInvoicesAction}"
	                            oncomplete="#{rich:component('deleteFreightInvConfirmPopup')}.hide();"
	                            reRender="freightInvDataTable, freightInvText" ajaxSingle="true"/>
                        </td>
                        <td>
                        	&nbsp;&nbsp;
                        </td>
                        <td align="left" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteFreightInvConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel> 	
	
	<rich:modalPanel id="MerchandInvPopup"  style="background-color: #F3F8FC;" styleClass="styleGeneralPanel" autosized="true">
		<f:facet name="header">
	    	<h:outputText value="Merchandise Invoice" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideMerchandInvPopupLink" onclick="#{rich:component('MerchandInvPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
       	<h:form id="formMerchandInvPopup">
    		<rich:hotKey key="esc" handler="#{rich:component('MerchandInvPopup') }.hide();" />
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>				
				<tr>
					<td>
						<rich:dataTable width="150px" id="merchandInvDataTable" 
							value="#{blControl.bl.blOtherInvoices}" var="MerchInv"
							rows="#{blControl.rowsMI}"  rowClasses="row1,row2" binding="#{blControl.blMerchandiseInvoicesTable }">
							
							<rich:column sortable="false" label="Invoices" id="col_1" width="120px" >
								<f:facet name="header">
									<h:outputText value="Invoices" ></h:outputText>
								</f:facet>
								<h:inputText id="itemMerchandInvText" value="#{MerchInv.invoice.invoiceNumber}" style="width:120px; background-color:transparent" />	
								<rich:suggestionbox id="itemMerchandInvSuggestBox" for="itemMerchandInvText" 
									suggestionAction="#{blControl.autoCompleteInvoices}" var="result"
									tokens="," height="200" width="120" cellpadding="2"
									cellspacing="2"   shadowOpacity="4" shadowDepth="4"
									minChars="2" rules="none" nothingLabel="no matches found"
									status="none" >
									<h:column>
										<h:outputText value="#{result.invoiceNumber}" style="font-style:bold" />
									</h:column>
									<a4j:support event="onselect" ajaxSingle="true" status="none" reRender="merchandInvDataTable" action="#{blControl.newItemMerchandiseInvoiceAction}">  
										<f:setPropertyActionListener value="#{result }" target="#{MerchInv.invoice}"/>
									</a4j:support>
								</rich:suggestionbox>	
							</rich:column>
							
							<rich:column width="22px" id="col_5">
								<a4j:commandLink ajaxSingle="true" id="deleteMerchInvLink" 
									action = "#{blControl.selectToDeleteMerchandInvoAction}" status="none"
									oncomplete="#{rich:component('deleteMerchandInvConfirmPopup')}.show();">																			
									<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
								</a4j:commandLink>
							</rich:column>
						</rich:dataTable>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center" >
						<a4j:commandButton value="Save" reRender="merchandInvText" action="#{blControl.saveMerchandInvoicesAction }"
							data="#{facesContext.maximumSeverity.ordinal ge 2}" oncomplete="if (data == false){#{rich:component('MerchandInvPopup')}.hide();}"/>
						&nbsp;&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" ajaxSingle="true" onclick="#{rich:component('MerchandInvPopup') }.hide();" status="none"/>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="deleteMerchandInvConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink9" />
                <rich:componentControl for="deleteMerchandInvConfirmPopup" attachTo="hidelink9"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="right" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteMerchandInvoicesAction}"
	                            oncomplete="#{rich:component('deleteMerchandInvConfirmPopup')}.hide();"
	                            reRender="merchandInvDataTable,merchandInvText" ajaxSingle="true"/>
                        </td>
                        <td>
                        	&nbsp;&nbsp;
                        </td>
                        <td align="left" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteMerchandInvConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
	
	
	<rich:modalPanel id="newSaidToContainPopup" height="180">
		<f:facet name="header">
	    	<h:outputText value="New Said To Contain" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideNewSaidToContainPopupLink" onclick="#{rich:component('newSaidToContainPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="newSaidToContainForm">			
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>
				<tr>
					<td align="right" >
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<h:inputTextarea  value="#{blControl.saidToContain.value3 }" style="width:270px;"/>
					</td>
				</tr>
				<tr>
					<td>
						<br>
					</td>
				</tr>						
				<tr>
					<td align="right">
						<a4j:commandButton value="Save" action="#{blControl.saveSaidToContainAction}" 
						data="#{facesContext.maximumSeverity.ordinal ge 2}"
						oncomplete="if (data == false)#{rich:component('newSaidToContainPopup') }.hide();" reRender="saidToContainComboBox"></a4j:commandButton>
						&nbsp;&nbsp;
					</td>
					<td>
						&nbsp;&nbsp;
						<a4j:commandButton value="Cancel" onclick="#{rich:component('newSaidToContainPopup') }.hide();" status="none" ajaxSingle="true"></a4j:commandButton>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="ConfirmDeleteCSPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="ConfirmDeleteCSPopup" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            ajaxSingle="true" action="#{blControl.deleteCSAction}"
	                            onclick="#{rich:component('ConfirmDeleteCSPopup')}.hide();"
	                            reRender="otherCostSalesDataTable" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel" status="none"
                            onclick="#{rich:component('ConfirmDeleteCSPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>  	
    <rich:modalPanel id="declaredValuePopup" autosized="true">
		<f:facet name="header">
	    	<h:outputText value="Declared Value" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hideDeclaredValuePopupLink" onclick="#{rich:component('declaredValuePopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="declaredValueForm">
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>
				<tr>
					<td align="right" >
						<rich:extendedDataTable id="declaredValueDataTable" width="320px" height="150px" rows="#{blControl.rowsDV}" title="Declared Values" rowClasses="row1,row2"
							value="#{blControl.bl.blDeclaredValues}" var="declaredValue">
							
							<rich:column sortable="false" label="Supplier Name" id="col_1" width="60%" >
								<f:facet name="header">
									<h:outputText value="Supplier Name" ></h:outputText>
								</f:facet>
								<h:outputText value="#{declaredValue.supplier.name}" ></h:outputText>

							</rich:column>
							
							<rich:column sortable="false" label="Supplier Invoice" id="col_2" width="19%" >
								<f:facet name="header">
									<h:outputText value="Invoice" ></h:outputText>
								</f:facet>
									<h:outputText value="#{declaredValue.invoiceNumber} " ></h:outputText>

							</rich:column>
							
							<rich:column sortable="false" label="Invoice Value" id="col_3" width="21%" >
								<f:facet name="header">
									<h:outputText value="Value" ></h:outputText>
								</f:facet>
								<h:outputText value="#{declaredValue.totalInvoce}" style="text-align:right">
									<f:converter converterId="#{blControl.converterName }"/>
								</h:outputText>

							</rich:column>
							
						</rich:extendedDataTable>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a4j:commandButton value="Close" onclick="#{rich:component('declaredValuePopup') }.hide();" status="none" ajaxSingle="true"></a4j:commandButton>
					</td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
	
	
	
	<rich:modalPanel id="addItemsFromWHPopup" width="650" height="410" top="71px" onbeforehide="clearListsWhItems()">
		<f:facet name="header">
	    	<h:outputText value="Add Items From WH" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('addItemsFromWHPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="addItemsFromWHForm">			
			<table>
				<tr>
					<td align="left" colspan="2" height="30px" valign="top">
						<h:outputLabel value="Select the WH Receipt that you want to include" style="font-weight: bolder; font-size: 13px"/>
					 </td>
				</tr>
				<tr>
					<td align="right" width="70px">
						WH. Receipt:
					 </td>
					 <td align="left" >
						<h:inputText style="width:100px" value="#{blControl.whReceipt.whReceiptNumber}">
							<a4j:support event="onchange" action="#{blControl.loadWhItemsAction }" reRender="whItemsDataTable,shipperWhItemText,clientWhItemText,messageError"/>
						</h:inputText>
					 </td>
				</tr>
				
				<tr>
					<td align="right" >
						Shipper:
					 </td>
					 <td align="left" >
						<h:inputText id="shipperWhItemText" style="width:350px" value="#{blControl.whReceipt.supplier.name }"  onkeypress="return false;" />
					 </td>
				</tr>
				<tr>
					<td align="right" >
						Client:
					 </td>
					 <td align="left" >
						<h:inputText id="clientWhItemText" style="width:350px" value="#{blControl.whReceipt.client.name }"  onkeypress="return false;"/>
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="39">
						<rich:messages id="messageError" errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2" >
						<h:outputLabel value="Please select the items you want to include in the shipment and enter the number of pieces to be included" />
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2"> 
						<rich:extendedDataTable id="whItemsDataTable" 
							height="170px" width="623px" rowClasses="row1,row2"
							value="#{blControl.whReceipt.whItems}" var="item"
							rows="100" sortMode="#{blControl.sortMode}"
							title="Wh Items" binding="#{blControl.blWhItemsTable }">
							<rich:column sortable="false" label=" No. Pieces" id="col_1" width="48px" >
								<f:facet name="header">
									<h:outputText value="Pieces"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.pieces }"/>
							</rich:column>	
							
							<rich:column sortable="false" label="Type" id="col_2" width="80px">
								<f:facet name="header">
									<h:outputText value="Type"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.type.value1 }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Length" id="col_3" width="60px">
								<f:facet name="header">
									<h:outputText value="Length"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.itemLength }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Width" id="col_4" width="60px">
								<f:facet name="header">
									<h:outputText value="Width"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.itemWidth }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Height" id="col_5" width="60px">
								<f:facet name="header">
									<h:outputText value="Height"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.itemHeight }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Weight" id="col_6" width="60px">
								<f:facet name="header">
									<h:outputText value="Weight"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.itemWeight }"/>
							</rich:column>
							
							<rich:column sortable="false" label="PO Number" id="col_7" width="110px">
								<f:facet name="header">
									<h:outputText value="PO Number"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.poNumber }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Hazar" id="col_8" width="40px">
								<f:facet name="header">
									<h:outputText value="Hazar"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{item.hazardous }" onclick="return false;"/>
							</rich:column>
							
							<rich:column sortable="false" label="Ship" id="col_9" width="35px">
								<f:facet name="header">
									<h:outputText value="Ship"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{item.ship}" >
									<a4j:support event="onclick" ajaxSingle="true" status="none" process="whItemsDataTable" action="#{blControl.setNumPiecesToShipWHItemsAction}" reRender="numPiecesToShipVal"/>
								</h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column  sortable="false" label="No. Pieces To Ship" id="col_10" width="70px">
								<f:facet name="header">
									<h:outputText value="No.Pieces To Ship" style="white-space:normal"></h:outputText>
								</f:facet>
								<h:inputText id="numPiecesToShipVal" styleClass="styleInput6" value="#{item.numberPiecesToShip }" style="width:67px;"
							   		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							   </h:inputText>
							</rich:column>
						</rich:extendedDataTable>
					 </td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<table>
							<tr>
								<td>
									<a4j:commandButton value="Continue" action="#{blControl.processWhItemsAction}" reRender="blItemsDataTable, addItemsFromWHPopup, clientPoText, declaredValueText,costSalesDataTable "
										oncomplete="#{rich:component('addItemsFromWHPopup') }.hide(); #{rich:element('saveButton')}.click();"/>													
								</td>
								<td>
									<a4j:jsFunction name="clearListsWhItems" action="#{blControl.clearListsInvoiceAndWhItems}" status="none" reRender="addItemsFromWHForm"/>
									<a4j:commandButton value="Cancel" action="#{blControl.clearListsInvoiceAndWhItems}" status="none" ajaxSingle="true" 
										oncomplete="#{rich:component('addItemsFromWHPopup') }.hide();" reRender="blItemsDataTable, addItemsFromWHPopup, declaredValueDataTable"/>
								</td>
							</tr>
						</table>					
					</td>
				</tr>	
			 </table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="addItemsFromInvoicePopup" width="650" height="400" top="71px" onbeforehide="clearListsPalletized()">
		<f:facet name="header">
	    	<h:outputText value="Add Items from Invoice" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('addItemsFromInvoicePopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="addItemsFromInvoiceForm">			
			<table>
				<tr>
					<td align="left" colspan="2" height="30px" valign="top">
						<h:outputLabel value="Select the Invoice that you want to include" style="font-weight: bolder; font-size: 13px"/>
					 </td>
				</tr>
				<tr>
					<td align="right" width="70px">
						Invoice:
					 </td>
					 <td align="left" >
						<h:inputText style="width:100px" value="#{blControl.invoice.invoiceNumber }">
							<a4j:support event="onchange" action="#{blControl.loadInvoiceAction }" reRender="ItemsFromInvoiceDataTable,clientInvoiceItemText"/>
						</h:inputText>
					 </td>
				</tr>
				<tr>
					<td align="right" >
						Client:
					 </td>
					 <td align="left" >
						<h:inputText id="clientInvoiceItemText" value="#{blControl.invoice.client.name }" style="width:350px" />
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="39">
						<rich:messages id="messageError" errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2" >
						<h:outputLabel value="Please select the items you want to include in the shipment and enter the number of pieces to be included" />
					 </td>
				</tr>
				<tr>
					<td align="left" colspan="2"> 
						<rich:extendedDataTable id="ItemsFromInvoiceDataTable" 
							height="170px" width="623px" rowClasses="row1,row2"
							var="item"  value="#{blControl.invoice.palletizedItemsList}"
							rows="100" sortMode="#{blControl.sortMode}"
							title="RM Items" binding="#{blControl.blInvoiceItemsTable}">
							<rich:column sortable="false" label=" No. Pieces" id="col_1" width="48px" >
								<f:facet name="header">
									<h:outputText value="Pieces"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.pieces }"/>
							</rich:column>	
							
							<rich:column sortable="false" label="Type" id="col_2" width="80px">
								<f:facet name="header">
									<h:outputText value="Type"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.type.value1 }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Length" id="col_3" width="60px">
								<f:facet name="header">
									<h:outputText value="Length"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.length }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Width" id="col_4" width="60px">
								<f:facet name="header">
									<h:outputText value="Width"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.width }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Height" id="col_5" width="60px">
								<f:facet name="header">
									<h:outputText value="Height"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.height }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Weight" id="col_6" width="60px">
								<f:facet name="header">
									<h:outputText value="Weight"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.weight }"/>
							</rich:column>
							
							<rich:column sortable="false" label="Hazar" id="col_8" width="40px">
								<f:facet name="header">
									<h:outputText value="Hazar"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{item.hazardous }" onclick="return false;"/>
							</rich:column>
							
							<rich:column sortable="false" label="Ship" id="col_9" width="35px">
								<f:facet name="header">
									<h:outputText value="Ship"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{item.ship}" >
									<a4j:support event="onclick" ajaxSingle="true" status="none" process="ItemsFromInvoiceDataTable" action="#{blControl.setNumPiecesToShipInvoiceItemsAction}" reRender="numPiecesToShipValue"/>
								</h:selectBooleanCheckbox>
							</rich:column>
							
							<rich:column  sortable="false" label="No. Pieces To Ship" id="col_10" width="70px">
								<f:facet name="header">
									<h:outputText value="No.Pieces To Ship" style="white-space:normal"></h:outputText>
								</f:facet>
								<h:inputText id="numPiecesToShipValue" styleClass="styleInput6" value="#{item.numberPiecesToShip }" style="width:67px;"
							   		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							   </h:inputText>
							</rich:column>
						</rich:extendedDataTable>
					 </td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<table>
							<tr>
								<td>
									<a4j:commandButton value="Continue" action="#{blControl.processInvoiceItemsAction}"  reRender="blItemsDataTable, addItemsFromInvoicePopup" 
										oncomplete="#{rich:component('addItemsFromInvoicePopup')}.hide(); #{rich:element('saveButton')}.click();"/>
								</td>
								<td>
									<a4j:jsFunction name="clearListsPalletized" action="#{blControl.clearListsInvoiceAndWhItems}" ajaxSingle="true" status="none" reRender="addItemsFromInvoiceForm"/>
									<a4j:commandButton value="Cancel" action="#{blControl.clearListsInvoiceAndWhItems}" ajaxSingle="true" status="none" 
										oncomplete="#{rich:component('addItemsFromInvoicePopup') }.hide();" reRender="addItemsFromInvoicePopup"/>
								</td>
							</tr>
						</table>					
					</td>
				</tr>	
			 </table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="blPalletizedItemsPopup" width="920" height="400" top="71px">
		<f:facet name="header">
	    	<h:outputText value="Palletized Items" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('blPalletizedItemsPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="blPalletizedItemsForm">			
			<table>				
				<tr>
					<td align="left" colspan="2"> 
						<rich:extendedDataTable id="blPalletizedItemsDataTable"
							height="300px" width="890px" rowClasses="row1,row2"
							value="#{blControl.bl.blPalletizedItems}" var="item"
							rows="100" sortMode="#{blControl.sortMode}"
							title="BL Items" binding="#{blControl.blPalletizedItemsTable}"							
							reRender="ds2">	
							
							<rich:column sortable="false" label="Id #" id="col_8" width="50px">
								<f:facet name="header">
									<h:outputText value="Id #"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.palletId}" id="externalIdTxt1" 
								style="width: 40px; background-color: transparent; color: #000000; text-align:center" readonly="true"
								onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="H" id="col_0" width="30px">
									<f:facet name="header">
										<h:outputText value="H"></h:outputText>
									</f:facet>
									<h:selectBooleanCheckbox id="itemsHazardousCheckBox1" value="#{item.hazardous}" >								
									</h:selectBooleanCheckbox>
								</rich:column>																						
												
							<rich:column sortable="false" label="Pieces" id="col_1" width="50px">
								<f:facet name="header">
									<h:outputText value="Pieces"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.pieces}" id="itemPiecesTxt1" style="width:40px;text-align: right"
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Type" id="col_2" width="100px">
								<f:facet name="header">
									<h:outputText value="Type"></h:outputText>
								</f:facet>								
				                <h:selectOneMenu  id="unitTypeCombobox1" value="#{item.type.valueId}" style="width:90px; background-color: transparent;"
				                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
									<f:selectItem itemLabel="" itemValue="" />
									<f:selectItems value="#{blControl.unitTypes}"/>
				                      </h:selectOneMenu>
							</rich:column>
							
							<rich:column sortable="false" label="Length" id="col_3" width="60px">
								<f:facet name="header">
									<h:outputText value="Length"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.itemLength}" id="itemLengthTxt1"  style="width:57px;text-align: right" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                		<a4j:support   event="onchange" ajaxSingle="false" status="none" reRender="blPalletizedItemsDataTable" >  
										<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength / 1728}" target="#{item.itemVolume}" />										
									</a4j:support>										                 		
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Width" id="col_4" width="60px">
								<f:facet name="header">
									<h:outputText value="Width"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.itemWidth}" id="itemWidthTxt1" style="width:57px;text-align: right" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                	<a4j:support   event="onchange" ajaxSingle="false"  status="none" reRender="blPalletizedItemsDataTable" >  
										<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength  / 1728 }" target="#{item.itemVolume}" />										
									</a4j:support>				                 		
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Height" id="col_5" width="60px">
								<f:facet name="header">
									<h:outputText value="Height"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.itemHeight}" id="itemHeightTxt1" style="width:57px;text-align: right" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                		<a4j:support   event="onchange" ajaxSingle="false" status="none" reRender="blPalletizedItemsDataTable" >  
											<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength / 1728}" target="#{item.itemVolume}" />										
										</a4j:support>				                 		
				                </h:inputText>
							</rich:column>
							<rich:column sortable="false" label="Volume" id="col_6" width="80px">
								<f:facet name="header">
									<h:outputText value="Volume"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.itemVolume}" id="itemVolumeTxt1" style="width:77px;text-align: right" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
				                		<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>			                 		
				                </h:inputText>
				                
							</rich:column>
							<rich:column sortable="false" label="Weight" id="col_7" width="80px">
								<f:facet name="header">
									<h:outputText value="Weight"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.itemWeight}" id="itemWeightTxt1"  style="width:77px;text-align: right" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Remarks" id="col_12" width="150px">
								<f:facet name="header">
									<h:outputText value="Remarks"></h:outputText>
								</f:facet>
								<h:inputText styleClass="styleInput6" value="#{item.remarks}" id="remarksNumberTxt1" style="width:147px" 
				                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
				                </h:inputText>
							</rich:column>
							
							<rich:column sortable="false" label="Location" id="col_13" width="80px">
								<f:facet name="header">
									<h:outputText value="Location"></h:outputText>
								</f:facet>								
				                <h:selectOneMenu  id="locationCombobox" value="#{item.whLocation.whLocationId}" style="width:78px; background-color: transparent;"
				                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
									<f:selectItem itemLabel="" itemValue="" />
									<f:selectItems value="#{blControl.whLocations}"/>
				                      </h:selectOneMenu>
							</rich:column>
							
							
							<rich:column sortable="false" label="Type" id="col_15" width="90px" rendered="#{blControl.bl.FCL}">
								<f:facet name="header">
									<h:outputText value="Container"></h:outputText>
								</f:facet>								
				                <h:selectOneMenu  id="containerCombobox1" value="#{item.container.containerId}" style="width:87px; background-color: transparent;"
				                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
									<f:selectItem itemLabel="All" itemValue="" />
									<f:selectItems value="#{blControl.containers}"/>
				                      </h:selectOneMenu>
							</rich:column>	
						</rich:extendedDataTable>
					 </td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<table>
							<tr>
								<td>
									<a4j:commandButton value="Save"  reRender="blPalletizedItemsDataTable, blPalletizedItemsPopup" 
										oncomplete="#{rich:component('blPalletizedItemsPopup')}.hide(); #{rich:element('saveButton')}.click();"/>
								</td>
								<td>
									<a4j:commandButton value="Cancel" ajaxSingle="true" status="none" 
										oncomplete="#{rich:component('blPalletizedItemsPopup') }.hide();" reRender="blPalletizedItemsPopup"/>
								</td>
							</tr>
						</table>					
					</td>
				</tr>	
			 </table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="deleteBlItemConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this item?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink7" />
                <rich:componentControl for="deleteBlItemConfirmPopup" attachTo="hidelink7"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteBlItemAction}" 
	                            oncomplete="#{rich:component('deleteBlItemConfirmPopup')}.hide(); #{rich:element('saveButton')}.click();"
	                            reRender="blItemsDataTable, declaredValueText" ajaxSingle="true"/>
                        </td>
                        <td align="center" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteBlItemConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
    <rich:modalPanel id="deleteBlContainerConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this container?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls2">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink10" />
                <rich:componentControl for="deleteBlContainerConfirmPopup" attachTo="hidelink10"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteBlContainerAction}" 
	                            oncomplete="#{rich:component('deleteBlContainerConfirmPopup')}.hide();"
	                            reRender="blContainersDataTable,blItemsDataTable," ajaxSingle="true"/>
                        </td>
                        <td align="center" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteBlContainerConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
    
    <rich:modalPanel id="deleteUnCodeConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this UN Code?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink5" />
                <rich:componentControl for="deleteUnCodeConfirmPopup" attachTo="hidelink5"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="right" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteUnCodeItemAction}" 
	                            oncomplete="#{rich:component('deleteUnCodeConfirmPopup')}.hide();"
	                            reRender="unCodesDataTable" ajaxSingle="true"/>
                        </td>
                        <td>
                        	&nbsp;&nbsp;
                        </td>
                        <td align="left" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteUnCodeConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel> 
    
    
     <rich:modalPanel id="deleteEEIConfirmPopup" autosized="true" width="200" style="background-color:#F3F8FC;">
        <f:facet name="header">
            <h:outputText value="Do you want to delete this EEI?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png"
                    styleClass="hidelink" id="hidelink6" />
                <rich:componentControl for="deleteEEIConfirmPopup" attachTo="hidelink6"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="right" width="50%">
	                        <a4j:commandButton value="Yes"
	                            action="#{blControl.deleteEeiItemAction}" 
	                            oncomplete="#{rich:component('deleteEEIConfirmPopup')}.hide();"
	                            reRender="eeiDataTable" ajaxSingle="true"/>
                        </td>
                        <td>
                        	&nbsp;&nbsp;
                        </td>
                        <td align="left" width="50%">
                        	<a4j:commandButton value="No" status="none" ajaxSingle="true"
                           	 	onclick="#{rich:component('deleteEEIConfirmPopup')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
    
    
    <rich:modalPanel id="inlandFreightCSPopup" width="650" height="440">
		<f:facet name="header">
	    	<h:outputText value="Inland Information" />
	    </f:facet>
	    <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('inlandFreightCSPopup') }.hide();"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="inlandFreightCSForm">
			<rich:messages errorLabelClass="labelerrorStyle" infoLabelClass="labelinfoStyle"></rich:messages>
			<table>				
				<tr>
					<td align="left" colspan="2"> 
						<a4j:jsFunction name="newInlandCSItemJs" action="#{blControl.newItemInlandCSAction}" reRender="ItemsFromInlandFreightCSDataTable" status="none" ajaxSingle="true" process="ItemsFromInlandFreightCSDataTable"/>
						<rich:extendedDataTable id="ItemsFromInlandFreightCSDataTable" 
							height="270px" width="623px" rowClasses="row1,row2"
							var="item"  value="#{blControl.bl.blInlandCS}"
							rows="100" sortMode="#{blControl.sortMode}"
							title="Inland cost and sales" binding="#{blControl.blInlandCSItemsTable}">
							<rich:column sortable="false" label="Cost" id="col_1" width="60px" >
								<f:facet name="header">
									<h:outputText value="Cost"></h:outputText>
								</f:facet>
								<h:inputText value="#{item.cost }" onkeydown="if(event.keyCode == 13){newInlandCSItemJs();return false;}"
								styleClass="styleInput6" style="width:57px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"/>
							</rich:column>	
							
							<rich:column sortable="false" label="Sale" id="col_2" width="60px">
								<f:facet name="header">
									<h:outputText value="Sale"></h:outputText>
								</f:facet>
								<h:inputText value="#{item.sale }"  onkeydown="if(event.keyCode == 13){newInlandCSItemJs();return false;}"
								styleClass="styleInput6" style="width:57px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"/>
							</rich:column>
							
							<rich:column sortable="false" label="PO Number" id="col_3" width="120px">
								<f:facet name="header">
									<h:outputText value="PO Number"></h:outputText>
								</f:facet>
								<h:inputText value="#{item.poNumber }" onkeydown="if(event.keyCode == 13){newInlandCSItemJs();return false;}"
								styleClass="styleInput6" style="width:117px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"/>
							</rich:column>
							
							<rich:column sortable="false" label="Truck Company" id="col_4" width="120px">
								<f:facet name="header">
									<h:outputText value="Truck Company"></h:outputText>
								</f:facet>
									                		
		                		<h:selectOneMenu id="truckCompanyList" value="#{item.truckCompany.valueId}"styleClass="styleInput6" style="width:117px;text-align: left" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" >
									<f:selectItem itemLabel="-- SELECT --" itemValue="" />
									<f:selectItems value="#{blControl.truckCompanies}" />
								</h:selectOneMenu>
							</rich:column>
							
							<rich:column sortable="false" label="Tracking Number" id="col_5" width="120px">
								<f:facet name="header">
									<h:outputText value="Tracking Number"></h:outputText>
								</f:facet>
								<h:inputText value="#{item.trackingNumber }" onkeydown="if(event.keyCode == 13){newInlandCSItemJs();return false;}"
								styleClass="styleInput6" style="width:117px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"/>
							</rich:column>							
							
						</rich:extendedDataTable>
					 </td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<table>
							<tr>
								<td>
									<a4j:commandButton value="Save" action="#{blControl.processInlandCSItemsAction}"  reRender="costSalesDataTable, inlandFreightCSPopup" 
										oncomplete="#{rich:component('inlandFreightCSPopup')}.hide(); #{rich:element('saveButton')}.click();"/>
								</td>
								<td>
									<a4j:commandButton value="Cancel" ajaxSingle="true" status="none" 
										oncomplete="#{rich:component('inlandFreightCSPopup') }.hide();" reRender="inlandFreightCSPopup"/>
								</td>
							</tr>
						</table>					
					</td>
				</tr>	
			 </table>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="EditTextPanel" autosized="true" width="600" onhide="#{rich:element('TextAreaPopup')}.disabled=false"
		style="background-color:#F3F8FC;">
		<f:facet name="header">
			<h:outputText value="Edit" />
		</f:facet>
		<f:facet name="controls">
			<h:panelGroup>
				<h:graphicImage value="/css/images/close-popup.png"
					styleClass="hidelink" id="hidelink" />
				<rich:componentControl for="EditTextPanel" attachTo="hidelink"
					operation="hide" event="onclick" />
			</h:panelGroup>
		</f:facet>
		<table>
			<tr align="center">
				<td align="center" colspan="2"><h:inputTextarea
						id="TextAreaPopup" style="width:575px;height:350px" value="" /> <h:inputHidden
						id="varAux" value="" />
				</td>
			</tr>
			<tr align="center" height="40">
				<td align="center">
					<table>
						<tr>
							<td>
								<h:commandButton id="botonOk" value="OK" type="button"
									styleClass="boton" onclick="savePopupEdit(#{rich:element('TextAreaPopup') },#{rich:element('varAux') });#{rich:component('EditTextPanel')}.hide();" />
							</td>
							<td>
							</td>
							<td>
								<h:commandButton id="botonCancelar" value="Cancel"
									type="button" styleClass="boton"
									onclick="#{rich:component('EditTextPanel')}.hide()"
									onblur="#{rich:element('TextAreaPopup') }.focus()" />
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</rich:modalPanel>
	
	<rich:modalPanel id="searchAndInspectionConsentPopup" autosized="true">
		<f:facet name="header">
			<h:outputText value="Search and Inspection Consent" />
		</f:facet>
		<f:facet name="controls">
			<h:panelGroup>
				<h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('searchAndInspectionConsentPopup') }.hide();"/>
			</h:panelGroup>
		</f:facet>
		<table width="100%">
			<tbody>
				<tr>
					<td colspan="3" nowrap="nowrap">
						Sorry,
					</td>
				</tr>
				<tr>
					<td colspan="3" nowrap="nowrap">
						We can not create this shipment until we get the Consent for Search & Inspection from the Client.
					</td>
				</tr>
				<tr>
					
					<td align="center" width="50%">
						<a4j:commandButton value="Ok" status="none" ajaxSingle="true"
						  	 onclick="#{rich:component('searchAndInspectionConsentPopup')}.hide();return false;" />
					</td>
				</tr>
			</tbody>
		</table>
	</rich:modalPanel>
    
</body>
</html>