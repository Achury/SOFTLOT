<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
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
<rich:panel id="costSalesPanel" style="border: none; height: 300px" rendered="#{!blControl.bl.master}">
		<table border="0">
			<tr>
				<td rowspan="4" width="310px" valign="top" >
					<table>
						<tr>
							<td colspan = "3">
								
								<a4j:jsFunction name="openInlandFreightCSFormJs" oncomplete="#{rich:component('inlandFreightCSPopup') }.show()" reRender="inlandFreightCSPopup" status="none"/>
								
								<rich:scrollableDataTable id="costSalesDataTable" var="costSale"
									rowClasses="row1, row2" rows="#{blControl.rowsCS}"
									height="250px" width="288px"
									sortMode="#{blControl.sortMode }"
									value="#{blControl.bl.blCostsSales}"
									binding="#{blControl.blCostsSalesTable}"
									>
									
									<a4j:support event="onRowDblClick" action="#{blControl.selectCSAction}" immediate="true" oncomplete="#{blControl.superCSForm}();" status="none" />
									
									<rich:column sortable="false" label="Type" id="col_1" width="90px" selfSorted="false">
										<f:facet name="header" >
											<h:outputText value="Type" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.chargeType.value1}"  style="width:88px" readonly="#{blControl.locked}"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" onkeypress="return false">
										</h:inputText>										
									</rich:column>									
							   
									<rich:column sortable="false" label="Cost" id="col_2" width="63px" style="text-align:right">
										<f:facet name="header">
											<h:outputText value="Cost" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.cost}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'; " onblur="this.className='styleInput6'"  readonly= "#{blControl.locked}"
											rendered="#{!costSale.inlandFreight}">
											
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
										<h:inputText styleClass="styleInput6" value="#{costSale.cost}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'; " onblur="this.className='styleInput6'"  readonly= "#{costSale.inlandFreight}"
											rendered="#{costSale.inlandFreight}">											
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
										
										
										
										
									</rich:column>
									
									<rich:column sortable="false" label="Sale" id="col_3" width="63px">
										<f:facet name="header">
											<h:outputText value="Sale" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.sale}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{blControl.locked}"
											rendered="#{!costSale.inlandFreight}">
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
										<h:inputText styleClass="styleInput6" value="#{costSale.sale}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{costSale.inlandFreight}"
											rendered="#{costSale.inlandFreight}">
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
										
									</rich:column>
									
									<rich:column sortable="false" label="SelectToBlDoc" id="col_4" width="23px">
										<h:selectBooleanCheckbox value="#{costSale.selectedToBlDoc}" rendered="#{costSale.showSelectToBlDoc }"/>
									</rich:column>
									
									<rich:column sortable="false" label="Accounting" id="col_5" width="23px" >
										<f:facet name="header">
											<h:outputText value="Acc" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox />
									</rich:column>	
																							
								</rich:scrollableDataTable>										
							</td>
					
							<td colspan = "3">
								<a4j:jsFunction name="newOtherCostSaleJs" action="#{blControl.newOtherCostSaleAction}" reRender="otherCostSalesDataTable" status="none" ajaxSingle="true" process="otherCostSalesDataTable"/>
								<rich:scrollableDataTable id="otherCostSalesDataTable" var="costSale"
									rowClasses="row1, row2" rows="#{blControl.rowsOtherCS}"
									height="250px" width="650px" 
									sortMode="#{blControl.sortMode }"														
									value="#{blControl.bl.blOtherCostsSales}"
									binding="#{blControl.blOtherCostsSalesTable}">										
									
									<rich:column sortable="false" label="Type" id="col_1" width="150px" selfSorted="false">
										<f:facet name="header" >
											<h:outputText value="Type" style="font-weight:bold;"></h:outputText>
										</f:facet>
																				
										<h:selectOneMenu id="otherChargesComboBox" value="#{costSale.chargeType.valueId}"  style="width:146px; background-color: transparent;"  onfocus="if(#{blControl.locked}){blur()}"
												readonly="#{blControl.locked}">
											<f:selectItem itemLabel="-- SELECT --" />
											<f:selectItems value="#{blControl.otherChargesList}"/>
					                   	</h:selectOneMenu>
									</rich:column>
									
									<rich:column sortable="false" label="Cost" id="col_2" width="63px">
										<f:facet name="header">
											<h:outputText value="Cost" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.cost}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{blControl.locked}"
											onkeydown="if(event.keyCode == 13){newOtherCostSaleJs();return false;}">
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
									</rich:column>
									
									<rich:column sortable="false" label="Sale" id="col_3" width="63px">
										<f:facet name="header">
											<h:outputText value="Sale" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.sale}" style="width:60px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{blControl.locked}"
											onkeydown="if(event.keyCode == 13){newOtherCostSaleJs();return false;}">
											<f:converter converterId="#{blControl.converterName }"/>
										</h:inputText>
									</rich:column>	
									<rich:column sortable="false" label="Notes" id="col_4" width="284px">
										<f:facet name="header">
											<h:outputText value="Notes" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:inputText styleClass="styleInput6" value="#{costSale.notes}" style="width:280px;  text-align: right"
											onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'" readonly="#{blControl.locked}"
											onkeydown="if(event.keyCode == 13){newOtherCostSaleJs();return false;}">											
										</h:inputText>
									</rich:column>	
									
									<rich:column sortable="false" label="SelectToBlDoc" id="col_5" width="23px" >
										<h:selectBooleanCheckbox value="#{costSale.selectedToBlDoc}"/>
									</rich:column>
									
									<rich:column sortable="false" label="Accounting" id="col_6" width="23px" >
										<f:facet name="header">
											<h:outputText value="Acc" style="font-weight:bold;"></h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox />
									</rich:column>	
									
									<rich:column sortable="false" label="" id="col_7" width="18px">										
										<a4j:commandLink ajaxSingle="true" id="deleteCostsSalesLink"  rendered="#{costSale.blCostSaleId > 0 && costSale.otherCost}"
											disabled="#{blControl.locked}" 
											action = "#{blControl.selectToDeleteCSAction}"		
											oncomplete="#{rich:component('ConfirmDeleteCSPopup')}.show()"					
											status="none">										
											<h:graphicImage value="/css/images/Delete.png" style="border:0"  />									
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
								<h:inputText value="#{blControl.bl.totalCost}" style="width:67px;  text-align: right" 
								  readonly="#{blControl.locked}" onkeydown="return false;">
									<f:converter converterId="#{blControl.converterName }"/>
								</h:inputText>
							</td>
							<td width="104px" align="left">
								<h:inputText value="#{blControl.bl.totalSale}" style="width:59px;  text-align: right"
									readonly="#{blControl.locked}" onkeydown="return false;">
									<f:converter converterId="#{blControl.converterName }"/>
								</h:inputText>
							</td>
						</tr>
					</table>
				</td>
				
				<td height="97px" valign="bottom" style="padding:0px 15px;">					
					<div style="position: relative;">
						<div class="headerPanel" align="center" style="top: -9%;width: 25%;" > Margin </div>
						<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:140px;">
							<table cellpadding="1" cellspacing="1"  border="0" >
								<tr height="30px" valign="bottom" >
									<td nowrap="nowrap" align="left">
										Margin(%):
									</td>
									
									<td nowrap="nowrap" align="left" >													
										<h:inputText  value="#{blControl.bl.totalSale > 0 ?(1 - (blControl.bl.totalCost/blControl.bl.totalSale)):0}" 
											style="width: 55px;background: #FFFFFF;color: #000000;text-align:right" readonly="true" >
											<f:convertNumber groupingUsed="false" type="percent" maxFractionDigits="2" minFractionDigits="2"/>														
										</h:inputText>														
									</td>
											
								</tr>
								<tr height="30px" valign="bottom">
									<td nowrap="nowrap" align="left">
										Total Margin ($):
									</td>
									<td nowrap="nowrap" align="left" >
										<h:inputText id="marginText" value="#{blControl.bl.totalSale - blControl.bl.totalCost}" readonly="true" 
											style="width: 55px;background: #FFFFFF;color: #000000;text-align:right">
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
										<h:selectOneRadio value="#{blControl.bl.paymentType.valueId}" disabled="#{blControl.locked}">
											<f:selectItems value="#{blControl.paymentTypeList }" />											
										</h:selectOneRadio>
									</td>
								</tr>
							</table>
						</rich:panel>
					</div>	
				</td>
			</tr>
			<tr>		
				<td align="left" valign="bottom" style="padding:0px 10px;">
					<table>
						<tr align="left">
							<td align="center">	
								<a4j:commandButton id="unlockButton" value="#{blControl.locked ? 'Unlock':'Lock'}" action="#{blControl.changeLockedValueAction}" reRender="costSalesPanel" status="none" ajaxSingle="true" />
							</td>
							<td align="center">
								<a4j:commandButton id="recalculateButton" value="Recalculate" action="#{blControl.validEffectiveDate}"   status="none" process="effectiveDateHidden"
								disabled="#{blControl.locked}" oncomplete="openPopupIfIsValidJS()"  reRender="effectiveDateHidden"/> 
								
								<h:inputHidden id="effectiveDateHidden" value="#{blControl.bl.validEffectiveDate}"/>
								
								<a4j:jsFunction name="openPopupIfIsValidJS" oncomplete="if(#{rich:element('effectiveDateHidden')}.value == 'true'){recalculateJS();}else{#{rich:component('confirmRecalculatePopup')}.show();}"/>
								<a4j:jsFunction name="recalculateJS" action="#{blControl.recalculateCostSalesAction}" reRender="costSalesPanel"/>
								
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
	
	
	<rich:panel id="costSalesHousePanel2" style="border: none; height: 255px" rendered="#{blControl.bl.master}">
		<table border="0" >
			<tr>
				<td>
					<rich:dataTable  value="#{blControl.dataMasterCS}" var="model" rowClasses="row1, row2" rendered="#{blControl.haveBLHouses}">
						<f:facet name="header">
							<rich:columnGroup>
								<rich:column width="50px">
									 <h:outputText value=""></h:outputText>
								</rich:column>
								<rich:columns value="#{blControl.headersMasterCS}" var="columns" index="ind" colspan="2"  width="40px">
									<h:outputText value="#{columns}" />
			               		</rich:columns>			                 
				            </rich:columnGroup>    
				         </f:facet>
				            
			            <rich:columns value="#{blControl.subHeadersMasterCS}" var="columns" index="ind" width="100px" >
			                 
			                <f:facet name="header">
			                    <h:outputText value="#{columns}" />
			                </f:facet>
			                	
			            	<h:outputText value="#{model[ind]} " style=" text-align:left"  rendered="#{ind < 1 ?true:false}">			                	
			                </h:outputText>
			                <h:outputText value="#{model[ind]} " style=" text-align:right"  rendered="#{ind > 0 ?true:false}">			                	
			                </h:outputText>	
			              
			              
			                 
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
					<td>
						Costos no estan vigentes
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						<a4j:commandButton value="Yes"
						    action="#{blControl.recalculateCostSalesAction}" reRender="costSalesPanel"
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