<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.rich-panel-body2  {
		    padding: 0px;
		}
		.rich-panel2  {
		    border-width: 0px;
		}
		.rich-table-cell {
			padding: 0px;
		}
	</style>
</head>
<body>

<rich:panel bodyClass="rich-panel-body2" styleClass="rich-panel2" rendered="#{!awbControl.awb.master}">
	<table height="300px" border="0">
		<tr>
			<td style="padding:0px 6px;" align="left" width="140px" height="25px">
				<a4j:commandButton value="Add Items from WH" onclick="#{rich:component('addWhItemsPopup') }.show();" rendered="#{awbControl.awb.awbId > 0}" status="none"/>
			</td>
			<td style="padding:0px 6px;" align="left" width="164px" >
				<a4j:commandButton value="Add Items from Invoice" onclick="#{rich:component('addItemsFromInvoicePopup') }.show();" rendered="#{awbControl.awb.awbId > 0}" status="none"/>
			</td>
			<td style="padding:0px 6px;" align="left">
				<a4j:commandButton value="Palletize" action="#{awbControl.palletizeItemsAction}" oncomplete="#{rich:component('awbPalletizedItemsPopup') }.show();" rendered="#{awbControl.awb.awbId > 0}" reRender="awbPalletizedItemsPopup"/>
			</td>
		</tr>
		<tr valign="top">
			<td colspan="3">
				<table>
					<tr>
						<td>
							<rich:dataTable id="awbItemsDataTable"
								 width="1198px" rowClasses="row1,row2"
								value="#{awbControl.awb.awbItems}" var="item"
								rows="100" sortMode="#{awbControl.sortMode}"
								title="AWB Items" binding="#{awbControl.awbItemsTable}">
													
								<rich:column sortable="false" label="Pieces" id="col_1" width="45px">
									<f:facet name="header">
										<h:outputText value="Pieces"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.pieces}" id="itemPiecesText" style="width:40px;text-align: right" 
								   		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
								   </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Type" id="col_2" width="100px">
									<f:facet name="header">
										<h:outputText value="Type"></h:outputText>
									</f:facet>								
							   		<h:selectOneMenu  id="unitTypeCombobox" value="#{item.type.valueId}" style="width:100px; background-color: transparent;"
										onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
										<f:selectItems value="#{awbControl.unitTypesList}"/>
									</h:selectOneMenu>
								</rich:column>
								
								<rich:column sortable="false" label="Length" id="col_3" width="67px">
									<f:facet name="header">
										<h:outputText value="Length [in]"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.itemLength}" id="itemLengthTxt" style="width:62px;text-align: right" 
										onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">	
										<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>		                 		
									</h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Width" id="col_4" width="61px">
									<f:facet name="header">
										<h:outputText value="Width [in]"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.itemWidth}" id="itemWidthTxt" style="width:57px;text-align: right" 
					            		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">	
					            		<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>		                 		
						            </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Height" id="col_5" width="65px">
									<f:facet name="header">
										<h:outputText value="Height [in]"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.itemHeight}" id="itemHeightTxt" style="width:60px;text-align: right" 
					            		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">	
					            		<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>		                 		
						            </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Volume" id="col_6" width="77px">
									<f:facet name="header">
										<h:outputText value="Volume [ft3]"></h:outputText>
									</f:facet>
									<h:inputText id="itemVolumeTxt" readonly="true" value="#{item.itemVolume}"
										style="width: 70px; background: transparent; color: #000000;text-align: right">		
						            	<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>	                 		
						            </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Weight" id="col_7" width="70px">
									<f:facet name="header">
										<h:outputText value="Weight [lb]"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.weightLbs}" id="itemWeightTxt" style="width:65px;text-align: right" 
						            		 onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
						            	<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>	                 		
						            </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="Rate Class" id="col_8" width="50px">
									<f:facet name="header">
										<h:outputText value="Rate Class"></h:outputText>
									</f:facet>								
							   		<h:selectOneMenu  id="rateClassCombobox" value="#{item.rateClass.valueId}" style="width:56px; background-color: transparent;"
										onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
										<f:selectItems value="#{awbControl.rateClassList}"/>
									</h:selectOneMenu>
								</rich:column>
								
								<rich:column sortable="false" label="WH #" id="col_9" width="50px">
									<f:facet name="header">
										<h:outputText value="WH #"></h:outputText>
									</f:facet>
						            <h:outputText id="whNumberTxt" value="#{item.whReceipt.whReceiptNumber > 0 ? item.whReceipt.whReceiptNumber : ''}"/>
								</rich:column>
								
								<rich:column sortable="false" label="PO Number" id="col_10" width="80px">
									<f:facet name="header">
										<h:outputText value="PO Number"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.poNumber}" id="poNumberTxt" style="width:77px" 
			      			 			onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
						            </h:inputText>
								</rich:column>
								
								<rich:column sortable="false" label="LOT Invoice" id="col_11" width="50px">
									<f:facet name="header">
										<h:outputText value="LOT Invoice"></h:outputText>
									</f:facet>
						            <h:outputText id="invoiceNumberTxt" value="#{item.invoice.invoiceNumber}"/>
								</rich:column>
								
								<rich:column sortable="false" label="Remarks" id="col_12">
									<f:facet name="header">
										<h:outputText value="Remarks"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.remarks}" id="remarksNumberTxt" style="width:300px" 
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
										<f:selectItems value="#{awbControl.whLocations}"/>
				                      </h:selectOneMenu>
								</rich:column>
								
								<rich:column sortable="false" label="Id #" id="col_16" width="50px">
									<f:facet name="header">
										<h:outputText value="Pallet #"></h:outputText>
									</f:facet>
									<h:inputText styleClass="styleInput6" value="#{item.palletId}" id="palletNumTxt" style="width:35px" 
					                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" onkeydown="return validateNumbers(event)">			                 		
					                </h:inputText>
								</rich:column>
								
								<rich:column width="22px" sortable="false" label="Delete" id="col_14">
									<a4j:commandLink ajaxSingle="true" id="deleteAwbItemLink" action="#{awbControl.selectToDeleteAwbItemAction}"
										oncomplete="#{rich:component('deleteAwbItemConfirmPopup')}.show();" status="none" reRender="formAwb">										
										<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
									</a4j:commandLink>
								</rich:column>
							</rich:dataTable>
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</table>
</rich:panel>	

</body>
</html>