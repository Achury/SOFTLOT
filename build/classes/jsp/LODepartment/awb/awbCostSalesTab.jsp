<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
	   	.rich-sdt-column-cell-body{
		    height: 18px;
		}
		.rich-std-header-row {
		    background-color: #F1EEE9;
		}
		.rich-sdt-hsep{
			 display: none;
		}
	</style>
</head>
<body>
<rich:panel id="costSalesPanel" style="border: none; height: 300px" rendered="#{!awbControl.awb.master}">
		<table border="0" >
			<tr>
				<td rowspan="4" width="400px" valign="top">
					<table>
						<tr>
							<td colspan="3">
							
								<a4j:jsFunction name="newCostSaleJs" action="#{awbControl.newCostSaleAction}" reRender="costSalesDataTable" status="none" ajaxSingle="true" process="OthercostSalesDataTable"/>
								<a4j:jsFunction name="openInlandFreightCSFormJs" oncomplete="#{rich:component('inlandFreightCSPopup') }.show()" reRender="inlandFreightCSPopup" status="none"/>
								
								<rich:scrollableDataTable id="costSalesDataTable" value="#{awbControl.awb.awbCostsSales}" var="costSale" rowClasses="row1, row2" 
									rows="30" height="250px" width="288px" binding="#{awbControl.awbCostsSalesTable }">
									
									<a4j:support event="onRowDblClick" action="#{awbControl.selectCSAction}" immediate="true" oncomplete="if(#{!awbControl.locked}){#{awbControl.superCSForm}();}" status="none" />
									
									<rich:column sortable="false" label="Type" id="col_1" width="90px" selfSorted="false" >
										<f:facet name="header">
											<h:outputText value="Type" style="font-weight:bold;" id="typeText"></h:outputText>
										</f:facet>
										<h:outputText styleClass="styleInput6" value="#{costSale.chargeType.value1}"  style="width:88px">
										</h:outputText>
									</rich:column>
									
									<rich:column sortable="false" label="Cost" id="col_2" width="63px">
										<f:facet name="header">
											<h:outputText value="Cost" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText id="costText" styleClass="styleInput6" value="#{costSale.cost}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{awbControl.locked}">
											<f:converter converterId="#{awbControl.converterName }"/>
										</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Sale" id="col_3" width="63px" >
										<f:facet name="header">
											<h:outputText value="Sale" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText id="saleText" styleClass="styleInput6" value="#{costSale.sale}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{awbControl.locked}">
											<f:converter converterId="#{awbControl.converterName }"/>
										</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="SelectToAwbDoc" id="col_4" width="23px">
										<h:selectBooleanCheckbox value="#{costSale.selectedToAwbDoc}" rendered="#{costSale.showSelectToAwbDoc }"/>
									</rich:column>
									
									<rich:column sortable="false" label="Accounting" id="col_5" width="23px" >
										<f:facet name="header">
											<h:outputText value="Acc" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox />
									</rich:column>
												
								</rich:scrollableDataTable>
							</td>
							<td colspan="3">
								<rich:scrollableDataTable id="OthercostSalesDataTable" value="#{awbControl.awb.awbOtherCostsSales}" var="costSale" rowClasses="row1, row2" 
									rows="30" height="250px" width="650px" sortMode="#{awbControl.sortMode}" binding="#{awbControl.awbOtherCostsSalesTable }">
									<rich:column sortable="false" label="Type" id="col_1" width="150px" selfSorted="false" >
										<f:facet name="header">
											<h:outputText value="Type" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:selectOneMenu id="otherChargesComboBox" value="#{costSale.chargeType.valueId}"  style="width:146px; background-color: transparent;"  onfocus="if(#{awbControl.locked}){blur()}"
												readonly="#{awbControl.locked}">
											<f:selectItem itemLabel="-- SELECT --" />
											<f:selectItems value="#{awbControl.otherChargesList}"/>
					                   	</h:selectOneMenu>
									</rich:column>
									
									<rich:column sortable="false" label="Cost" id="col_2" width="63px">
										<f:facet name="header">
											<h:outputText value="Cost" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText id="costText" styleClass="styleInput6" value="#{costSale.cost}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{awbControl.locked}"
											onkeydown="if(event.keyCode == 13){newCostSaleJs();}">
											<f:converter converterId="#{awbControl.converterName }"/>
										</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Sale" id="col_3" width="63px" >
										<f:facet name="header">
											<h:outputText value="Sale" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText id="saleText" styleClass="styleInput6" value="#{costSale.sale}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{awbControl.locked}"
											onkeydown="if(event.keyCode == 13){newCostSaleJs();}" >
											<f:converter converterId="#{awbControl.converterName }"/>
										</h:inputText>
									</rich:column>	
									
									<rich:column sortable="false" label="Notes" id="col_4" width="284px" >
										<f:facet name="header">
											<h:outputText value="Notes" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText id="notesText" styleClass="styleInput6" value="#{costSale.notes}" style="width:280px;  text-align: left"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{awbControl.locked}"
											onkeydown="if(event.keyCode == 13){newCostSaleJs();}" >
										</h:inputText>
									</rich:column>	
									
									<rich:column sortable="false" label="SelectToAwbDoc" id="col_5" width="23px" >
										<h:selectBooleanCheckbox value="#{costSale.selectedToAwbDoc}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Accounting" id="col_6" width="23px" >
										<f:facet name="header">
											<h:outputText value="Acc" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox />
									</rich:column>	
										
									<rich:column width="18px" sortable="false" label="Delete" id="col_7" >
										<a4j:commandLink ajaxSingle="true" id="deleteAwbItemLink" disabled="#{awbControl.locked}" rendered="#{costSale.awbCostsaleId > 0 && costSale.otherCost}"
											status="none" action="#{awbControl.selectToDeleteCostSaleItemAction}"
											oncomplete="#{rich:component('deleteCostSaleConfirmPopup')}.show();">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
										</a4j:commandLink>
									</rich:column>
								</rich:scrollableDataTable>
							</td>
						</tr>
						<tr>
							<td width="141px">
								Total:
							</td>
							<td>
								<h:inputText value="#{awbControl.awb.totalCosts}" style="width:67px;  text-align: right" 
								  readonly="#{awbControl.locked}" onkeydown="return false;">
									<f:converter converterId="#{awbControl.converterName }"/>
								</h:inputText>
							</td>
							<td width="104px" align="left">
								<h:inputText value="#{awbControl.awb.totalSales}" style="width:59px;  text-align: right"
									readonly="#{awbControl.locked}" onkeydown="return false;">
									<f:converter converterId="#{awbControl.converterName }"/>
								</h:inputText>
							</td>
						</tr>
					</table>
				</td>
				<td height="97px" valign="top" style="padding:0px 15px;">
					<div style="position: relative;">
						<div class="headerPanel" align="center" style="top: -9%; width: 25%" >Margin</div>
						<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:140px;" >
							<table cellpadding="1" cellspacing="1" border="0">
								<tr height="30px" valign="bottom">
									<td nowrap="nowrap" align="left">
										Margin(%):
									</td>
									<td nowrap="nowrap" align="left">
										<h:inputText value="#{awbControl.awb.totalSales > 0 ? (1 - (awbControl.awb.totalCosts/awbControl.awb.totalSales)) : 0 }" 
												style="width:55px;background: #FFFFFF;color: #000000;text-align: right" readonly="true">
											<f:convertNumber groupingUsed="false" type="percent" maxFractionDigits="2" minFractionDigits="2" pattern="##.## %"/>
										</h:inputText>
									</td>
								</tr>	
								<tr height="30px" valign="bottom">
									<td nowrap="nowrap" align="left">
										Total Margin($):
									</td>
									<td nowrap="nowrap" align="left">
										<h:inputText value="#{awbControl.awb.totalSales - awbControl.awb.totalCosts}" 
												style="width:55px;background: #FFFFFF;color: #000000;text-align: right" readonly="true">
											<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
										</h:inputText>
									</td>
								</tr>										
							</table>
						</rich:panel>
					</div>	
				</td>
			</tr>
			<tr>	
				<td colspan="2" style="padding:0px 15px;">
					<div style="position: relative;">
						<div class="headerPanel" align="center" style="top: -15%; width: 46%" >Payment Type</div>
						<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:140px;" >
							<table cellpadding="1" cellspacing="1" border="0">	
								<tr height="30px" valign="bottom">
									<td nowrap="nowrap" align="left" colspan="2">
										<h:selectOneRadio value="#{awbControl.awb.paymentType.valueId}" disabled="#{awbControl.locked}">
											<f:selectItems value="#{awbControl.paymentTypeList }" />
											<a4j:support event="onchange" reRender="dueAgentText,dueCarrierText" status="none" ajaxSingle="true"></a4j:support>
										</h:selectOneRadio>
									</td>
								</tr>	
								<tr>
									<td nowrap="nowrap" align="left">
										Due Agent($):
									</td>
									<td nowrap="nowrap" align="left">
										<h:inputText id="dueAgentText" value="#{awbControl.awb.dueAgent}" style="width:55px;text-align: right" disabled="#{awbControl.awb.prepaid || awbControl.locked}">
											<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
										</h:inputText>
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" align="left">
										Due Carrier($):
									</td>
									<td nowrap="nowrap" align="left">
										<h:inputText id="dueCarrierText" value="#{awbControl.awb.dueCarrier}" style="width:55px;text-align: right" disabled="#{awbControl.awb.prepaid || awbControl.locked}">
											<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
										</h:inputText>
									</td>
								</tr>
							</table>
						</rich:panel>
					</div>	
				</td>
			</tr>
			<tr>
				<td align="left" style="padding:0px 10px;">
					<table>
						<tr align="left">
							<td align="center">
								<a4j:commandButton id="unlockButton" value="#{awbControl.locked ? 'Unlock' : 'Lock'}" action="#{awbControl.changeLockedValueAction}" reRender="costSalesPanel" status="none" ajaxSingle="true" />
							</td>
							<td align="center">
								<a4j:commandButton id="recalculateButton" value="Recalculate" action="#{awbControl.validEffectiveDate}" status="none" process="effectiveDateHidden"
									disabled="#{awbControl.locked}" oncomplete="openPopupIfIsValidJS()"  reRender="effectiveDateHidden"/>
									
								<h:inputHidden id="effectiveDateHidden" value="#{awbControl.awb.validEffectiveDate}"/>
								
								<a4j:jsFunction name="openPopupIfIsValidJS" oncomplete="if(#{rich:element('effectiveDateHidden')}.value == 'true'){recalculateJS();}else{#{rich:component('confirmRecalculatePopup')}.show();}"/>
								<a4j:jsFunction name="recalculateJS" action="#{awbControl.recalculateCostSalesAction}" reRender="costSalesDataTable,OthercostSalesDataTable"/>
							</td>
						</tr>
					</table>	
				</td>		
			</tr>
			<tr>
				<td>
				</td>
			</tr>
		</table>
	</rich:panel>
	
	
	<rich:panel id="costSalesHousesPanel2" style="border: none; height: 300px" rendered="#{awbControl.awb.master}">
		<table border="0" >
			<tr>
				<td>
					<rich:dataTable  value="#{awbControl.dataMasterCS}" var="model" rowClasses="row1, row2" rendered="#{awbControl.haveAwbHouses }">
						<f:facet name="header">
							<rich:columnGroup>
								<rich:column width="50px">
									 <h:outputText value="" style="font-weight:bold"></h:outputText>
								</rich:column>
								<rich:columns value="#{awbControl.headersMasterCS}" var="columns" index="ind" colspan="2"  width="40px">
									<h:outputText value="#{columns}" style="font-weight:bold"/>
			               		</rich:columns>			                 
				            </rich:columnGroup>    
				         </f:facet>
				            
			            <rich:columns value="#{awbControl.subHeadersMasterCS}" var="columns" index="ind" width="100px" style="text-align: right;">
			                 
			                <f:facet name="header">
			                    <h:outputText value="#{columns}" style="font-weight:bold"/>
			                </f:facet>
			            	<h:outputText value="#{model[ind]}" rendered="#{ind < 1 ?true:false}" style="font-weight:bold;"/>
			                <h:outputText value="#{model[ind]}" rendered="#{ind > 0 ?true:false}" />		                				              
       
           			 	</rich:columns>	
						
					</rich:dataTable>
				</td>
			</tr>
		</table>
	</rich:panel>	
	
	<rich:modalPanel id="confirmRecalculatePopup" autosized="true">
		<f:facet name="header">
			<h:outputText value="Confirm Recalculate" />
		</f:facet>
		<f:facet name="controls">
			<h:panelGroup>
				<h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" onclick="#{rich:component('confirmRecalculatePopup') }.hide();"/>
			</h:panelGroup>
		</f:facet>
		<table width="100%">
			<tbody>
				<tr>
					<td colspan="3" nowrap="nowrap">
						The actual costs were updated after the AWB creation date.
					</td>
				</tr>
				<tr>
					<td colspan="3" nowrap="nowrap">
						Do you still want to recalculate?
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						<a4j:commandButton value="Yes"
						    action="#{awbControl.recalculateCostSalesAction}" reRender="costSalesDataTable,OthercostSalesDataTable"
							oncomplete="#{rich:component('confirmRecalculatePopup')}.hide();"/>
					</td>
					<td>
						&nbsp;&nbsp;
					</td>
					<td align="left" width="50%">
						<a4j:commandButton value="No" status="none" ajaxSingle="true"
						  	 onclick="#{rich:component('confirmRecalculatePopup')}.hide();return false;" />
					</td>
				</tr>
			</tbody>
		</table>
	</rich:modalPanel>
</body>
</html>