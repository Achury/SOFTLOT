<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.extdt-cell {
			padding: 0px;
		}
		.rich-panel-body2  {
		    padding: 0px;
		}
		.rich-panel2  {
		    border-width: 0px;
		}
	    .extdt-hsep {
	        display: none;
	    }
	</style>
</head>
<body>	
	<rich:panel id="algo" bodyClass="rich-panel-body2" styleClass="rich-panel2"  >
	<table >
		<tr>
			<td style="padding:0px 6px;" align="left" width="100px" >
				<a4j:commandButton value="Add Items from WH"   onclick="#{rich:component('addItemsFromWHPopup') }.show();"  rendered="#{blControl.bl.blId > 0}"></a4j:commandButton>
			</td>
			<td style="padding:0px 6px;" align="left" width="100px">
				<a4j:commandButton value="Add Items from Invoice" onclick="#{rich:component('addItemsFromInvoicePopup') }.show();" rendered="#{blControl.bl.blId > 0}"></a4j:commandButton>
			</td>
			<td style="padding:0px 6px;" align="left">
				<a4j:commandButton value="Palletize" action="#{blControl.blPalletizeItemsAction}" oncomplete="#{rich:component('blPalletizedItemsPopup') }.show();" rendered="#{blControl.bl.blId > 0}" reRender="blPalletizedItemsPopup"></a4j:commandButton>
			</td>
			
		</tr>
		<tr>
			<td colspan="3"  width="1200">		
				<rich:dataTable id="blItemsDataTable"
					width="1200" rowClasses="row1,row2"
					value="#{blControl.bl.blItems}" var="item"
					rows="100" sortMode="#{blControl.sortMode}"
					title="BL Items" binding="#{blControl.blItemsTable}">	
					
					<rich:column sortable="false" label="H" id="col_0" width="15px">
							<f:facet name="header">
								<h:outputText value="H"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox id="itemsHazardousCheckBox" value="#{item.hazardous}" >								
							</h:selectBooleanCheckbox>
						</rich:column>																						
										
					<rich:column sortable="false" label="Pieces" id="col_1" width="50px">
						<f:facet name="header">
							<h:outputText value="Pieces"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.pieces}" id="itemPiecesTxt" style="width:48px;text-align: right"
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
		                </h:inputText>
					</rich:column>
					
					<rich:column sortable="false" label="Type" id="col_2" width="80px">
						<f:facet name="header">
							<h:outputText value="Type"></h:outputText>
						</f:facet>								
		                <h:selectOneMenu  id="unitTypeCombobox" value="#{item.type.valueId}" style="width:78px; background-color: transparent;"
		                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							<f:selectItem itemLabel="" itemValue="" />
							<f:selectItems value="#{blControl.unitTypes}"/>
		                      </h:selectOneMenu>
					</rich:column>
					
					<rich:column sortable="false" label="Length" id="col_3" width="70px">
						<f:facet name="header">
							<h:outputText value="Length [in]"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.itemLength}" id="itemLengthTxt"  style="width:68px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
		                		<a4j:support   event="onchange" ajaxSingle="false" status="none" reRender="blItemsDataTable" >  
								<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength / 1728}" target="#{item.itemVolume}" />										
							</a4j:support>										                 		
		                </h:inputText>
					</rich:column>
					
					<rich:column sortable="false" label="Width" id="col_4" width="70px">
						<f:facet name="header">
							<h:outputText value="Width [in]"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.itemWidth}" id="itemWidthTxt" style="width:68px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
		                	<a4j:support   event="onchange" ajaxSingle="false"  status="none" reRender="blItemsDataTable" >  
								<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength  / 1728 }" target="#{item.itemVolume}" />										
							</a4j:support>				                 		
		                </h:inputText>
					</rich:column>
					
					<rich:column sortable="false" label="Height" id="col_5" width="70px">
						<f:facet name="header">
							<h:outputText value="Height [in]"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.itemHeight}" id="itemHeightTxt" style="width:68px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
		                		<a4j:support   event="onchange" ajaxSingle="false" status="none" reRender="blItemsDataTable" >  
									<f:setPropertyActionListener value="#{item.itemHeight * item.itemWidth * item.itemLength / 1728}" target="#{item.itemVolume}" />										
								</a4j:support>				                 		
		                </h:inputText>
					</rich:column>
					<rich:column sortable="false" label="Volume" id="col_6" width="75px">
						<f:facet name="header">
							<h:outputText value="Volume [ft3]"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.itemVolume}" id="itemVolumeTxt" style="width:73px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">
		                		<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>			                 		
		                </h:inputText>
		                
					</rich:column>
					<rich:column sortable="false" label="Weight" id="col_7" width="75px">
						<f:facet name="header">
							<h:outputText value="Weight [lb]"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.itemWeight}" id="itemWeightTxt"  style="width:73px;text-align: right" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
					<rich:column sortable="false" label="WH #" id="col_8" width="60px">
						<f:facet name="header">
							<h:outputText value="WH #"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.whReceipt.whReceiptNumber > 0 ? item.whReceipt.whReceiptNumber : ''}" id="whNumberTxt" 
							style="width: 58px; background-color: transparent; color: #000000; text-align:right" readonly="true"
							onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
					
					<rich:column sortable="false" label="PO Number" id="col_10" width="80px">
						<f:facet name="header">
							<h:outputText value="PO Number"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.poNumber}" id="poNumberTxt" style="width:78px" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
					<rich:column sortable="false" label="LOT Invoice" id="col_11" width="75px">
						<f:facet name="header">
							<h:outputText value="LOT Invoice"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.invoice.invoiceNumber > 0 ? item.invoice.invoiceNumber : ''}" id="invoiceNumberTxt" 
							style="width:73px; background-color: transparent; color: #000000; text-align:right" readonly="true"
							onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
					<rich:column sortable="false" label="Remarks" id="col_12" >
						<f:facet name="header">
							<h:outputText value="Remarks"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.remarks}" id="remarksNumberTxt"  
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
										
					<rich:column sortable="false" label="Location" id="col_13" width="70px">
						<f:facet name="header">
							<h:outputText value="Location"></h:outputText>
						</f:facet>								
		                <h:selectOneMenu  id="locationCombobox" value="#{item.whLocation.whLocationId}" style="width:78px; background-color: transparent;"
		                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							<f:selectItem itemLabel="" itemValue="" />
							<f:selectItems value="#{blControl.whLocations}"/>
	                      </h:selectOneMenu>
					</rich:column>
					
					<rich:column sortable="false" label="Container" id="col_15" width="60px" rendered="#{blControl.bl.FCL}">
						<f:facet name="header">
							<h:outputText value="Container"></h:outputText>
						</f:facet>								
		                <h:selectOneMenu  id="containerCombobox" value="#{item.container.containerId}" style="width:58px; background-color: transparent;"
		                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
							<f:selectItem itemLabel="" itemValue="" />
							<f:selectItems value="#{blControl.containers}"/>
		                </h:selectOneMenu>
					</rich:column>		
					<rich:column sortable="false" label="Id #" id="col_16" width="50px">
						<f:facet name="header">
							<h:outputText value="Pallet #"></h:outputText>
						</f:facet>
						<h:inputText styleClass="styleInput6" value="#{item.palletId}" onkeydown="return validateNumbers(event)" id="palletNoTxt" style="width:48px" 
		                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
		                </h:inputText>
					</rich:column>
					
					<rich:column width="22px" sortable="false" label="Delete" id="col_14">
						<a4j:commandLink ajaxSingle="true" id="deleteBlItemLink" action="#{blControl.selectToDeleteBlItemAction}"
							oncomplete="#{rich:component('deleteBlItemConfirmPopup')}.show();" status="none">										
							<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
						</a4j:commandLink>
					</rich:column>		
						
				</rich:dataTable>	
			</td>
		</tr>
	</table>
</rich:panel>	
</body>
</html>