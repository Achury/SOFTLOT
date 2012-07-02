<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<a4j:loadScript src="resource:///org/richfaces/renderkit/html/scripts/jquery/jquery.js"/>
	<script type="text/javascript">
		function getChoice(){
			return jQuery("select[name='formAwb:awbMoreInfoTab:saidToContainComboBox'] option:selected").html();
		}
	</script>
	<style type="text/css">
	</style>
</head>
<body>
	<rich:panel id="moreInfoPanel" style="border: none;">
		<table height="280px" border="0">
			<tr >
				<td valign="top" width="200px" rowspan="2">
					<table border="0" cellpadding="0" cellspacing="0">				
						<tr>
							<td align="right" nowrap="nowrap">
								Manifest #:
							</td>
							<td>
								<h:inputText value="#{awbControl.awb.manifestNumber }" style="width: 83px">
								
								</h:inputText>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								Declared Value:
							</td>
							<td>
								<h:inputText id="declaredValueText" value="#{awbControl.awb.declaredValue}" ondblclick="if(#{awbControl.awb.awbId} > 0) openDeclaredValuesJs();" 
									style="background: #FFFFFF;color: #000000;text-align:right;width: 83px"	readonly="true" >
									<f:converter converterId="#{awbControl.converterName }"/>
								</h:inputText>
									<a4j:jsFunction name="openDeclaredValuesJs" action="#{awbControl.loadDeclaredValuesAction}" oncomplete="#{rich:component('declaredValuePopup') }.show()" reRender="declaredValueDataTable,declaredValueText "/>
								
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								Booking:
							</td>
							<td>
								<h:inputText value="#{awbControl.awb.booking }" style="width: 83px"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								Flight No.:
							</td>
							<td>
								<h:inputText value="#{awbControl.awb.flightNumber }" style="width: 83px"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								Arrival Date:
							</td>
							<td>
								<rich:calendar id="arrivalDate" cellWidth="20px" datePattern="MM/d/yyyy" inputStyle="width: 83px"
									value="#{awbControl.awb.arrivalDate }" enableManualInput="true" jointPoint="bottom-right" direction="botton-left"
									oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('formAwb:awbMoreInfoTab:arrivalDateInputDate');">
								</rich:calendar>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								Departure Time:
							</td>
							<td>
								<h:inputText value="#{awbControl.awb.departureTime }" id="time" style="width: 83px"/>
								
							</td>
						</tr>
					</table>
				</td>
				<td valign="top" colspan="2" width="250px">
					<a4j:jsFunction name="newUnCodeItemJs" action="#{awbControl.newItemUnCodesAction}" reRender="unCodesDataTable" status="none" ajaxSingle="true" process="unCodesDataTable"/>
					
					<rich:dataTable id="unCodesDataTable" value="#{awbControl.awb.awbUnCodes }" var="unCode" rowClasses="row1, row2" 
							rows="10" binding="#{awbControl.awbUnCodesTable }" >
						<rich:column sortable="false" label="UN Code" id="col_1" width="69px">
							<f:facet name="header">
								<h:outputText value="UN Code"></h:outputText>
							</f:facet>
							<h:inputText value="#{unCode.unCode }" styleClass="styleInput6" style="width:67px" onfocus="this.className='styleInput7'"
								 onblur="this.className='styleInput6'" rendered="#{!awbControl.awb.master}" disabled="#{!awbControl.containHazardous}"
								 onkeydown="if(event.keyCode == 13){newUnCodeItemJs();return false;}">
							</h:inputText>
							<h:outputText value="#{unCode.unCode }" rendered="#{awbControl.awb.master}"></h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Class" id="col_2" width="10px">
							<f:facet name="header">
								<h:outputText value="Class"></h:outputText>
							</f:facet>
							<h:selectOneMenu id="unClassComboBox" value="#{unCode.unClassId}" disabled="#{awbControl.awb.master || !awbControl.containHazardous}"
									onkeydown="if(event.keyCode == 13){newUnCodeItemJs();}">
								<f:selectItem itemLabel="--SELECT--" />
								<f:selectItems value="#{awbControl.classUnCodeList}"/>
							</h:selectOneMenu>
						</rich:column>
						
						<rich:column sortable="false" label="Packing Group" id="col_3" width="50px">
							<f:facet name="header">
								<h:outputText value="PG"></h:outputText>
							</f:facet>
							<h:selectOneMenu id="packingGroupComboBox" value="#{unCode.packingGroupId}" disabled="#{awbControl.awb.master || !awbControl.containHazardous}"
									onkeydown="if(event.keyCode == 13){newUnCodeItemJs();}">
								<f:selectItem itemLabel="--SELECT--"/>
								<f:selectItems value="#{awbControl.packingGroupUnCodeList}"/>
							</h:selectOneMenu>
						</rich:column>
						<rich:column width="16px" sortable="false" label="Delete" id="col_4" rendered="#{unCode.unCodeId > 0 && !awbControl.awb.master}">
							<a4j:commandLink ajaxSingle="true" id="deleteAwbItemLink" oncomplete="#{rich:component('deleteUnCodeConfirmPopup')}.show();"
								action="#{awbControl.selectToDeleteUnCodeItemAction}" status="none">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
							</a4j:commandLink>
						</rich:column>
					</rich:dataTable>	
					<br>
					# Of UN Codes:&nbsp;&nbsp;<h:inputText value="#{awbControl.awb.numUNCodes}" style="width:40px"/>
				</td>
				
				<td valign="top" width="265px" rowspan="2"  align="center">
					<a4j:jsFunction name="newEeiItemJs" action="#{awbControl.newItemEEIAction}" reRender="eeiDataTable" status="none" ajaxSingle="true" process="eeiDataTable"/>
					<rich:dataTable id="eeiDataTable" value="#{awbControl.awb.awbEEIList }" var="eei" rowClasses="row1, row2" rows="30"  
						binding="#{awbControl.awbEeiTable }" rendered="#{!awbControl.awb.master}">
						<rich:column sortable="false" label="EEI" id="col_1" width="98px">
							<f:facet name="header">
								<h:outputText value="EEI" style="font-weight:bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{eei.eei }" styleClass="styleInput6"   style="width:96px" onfocus="this.className='styleInput7'" rendered="#{!awbControl.awb.master}"
								onblur="this.className='styleInput6'" onkeydown="if(event.keyCode == 13){newEeiItemJs();return false;}">
							</h:inputText>
							<h:outputText value="#{eei.eei }" rendered="#{awbControl.awb.master}"/>
						</rich:column>
						
						<rich:column sortable="false" label="Supplier" id="col_2" width="98px">
							<f:facet name="header">
								<h:outputText value="Supplier" style="font-weight:bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{eei.supplier}" styleClass="styleInput6"   style="width:96px" onfocus="this.className='styleInput7'" rendered="#{!awbControl.awb.master}"
								onblur="this.className='styleInput6'" onkeydown="if(event.keyCode == 13){newEeiItemJs();return false;}">
							</h:inputText>
							<h:outputText value="#{eei.supplier }" rendered="#{awbControl.awb.master}"/>
						</rich:column>
						<rich:column width="19px" sortable="false" label="Delete" id="col_4" rendered="#{!awbControl.awb.master}">
							<a4j:commandLink ajaxSingle="true" id="deleteAwbItemLink" rendered="#{eei.eeiId > 0 }"
								action="#{awbControl.selectToDeleteEeiItemAction }" status="none"
								oncomplete="#{rich:component('deleteEEIConfirmPopup')}.show();">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
							</a4j:commandLink>
						</rich:column>
					</rich:dataTable >
					<a4j:region rendered="#{awbControl.awb.master}">
						<div style="position: relative;">
							<div class="headerPanel" align="center" style="left: 28%; top: -8%;width: 28%;" >Payment Type</div>
							<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:140px;" >
								<table cellpadding="1" cellspacing="1" border="0">	
									<tr height="30px" valign="bottom">
										<td nowrap="nowrap" align="left" colspan="2">
											<h:selectOneRadio value="#{awbControl.awb.paymentType.valueId}">
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
											<h:inputText id="dueAgentText" value="#{awbControl.awb.dueAgent}" style="width:55px;text-align: right" disabled="#{awbControl.awb.prepaid}">
												<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
											</h:inputText>
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="left">
											Due Carrier($):
										</td>
										<td nowrap="nowrap" align="left">
											<h:inputText id="dueCarrierText" value="#{awbControl.awb.dueCarrier}" style="width:55px;text-align: right" disabled="#{awbControl.awb.prepaid}">
												<f:convertNumber groupingUsed="false" type="number" maxFractionDigits="2" minFractionDigits="2"/>
											</h:inputText>
										</td>
									</tr>
								</table>
							</rich:panel>
						</div>
					</a4j:region>
				</td>
				
				<td valign="top" width="260px" rowspan="2">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left">
								Said To Contain:
							</td>
							<td align="right">
								<a4j:commandLink value="[+]" style="text-decoration: none;"  action="#{awbControl.newSaidToContainAction }" onclick="#{rich:component('newSaidToContainPopup') }.show();" status="none" ajaxSingle="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<h:selectOneMenu id="saidToContainComboBox" styleClass="saidToContainItem" style="width:233px" onchange="#{rich:element('saidToContainText')}.value = getChoice()">
									<f:selectItem itemLabel=""/>
									<f:selectItems value="#{awbControl.saidToContainList}" />
							    </h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<h:inputTextarea value="#{awbControl.awb.saidToContain }" id="saidToContainText" style="width:230px; height:155px" />
							</td>
						</tr>
					</table>
				</td>
				<td valign="top" rowspan="1">
					<a4j:region rendered="#{!awbControl.awb.master}">
						<table >
							<tr>
								<td nowrap="nowrap">
									<h:selectBooleanCheckbox value="#{awbControl.awb.nonDeclaredValue }"/>
									NON VALUE DECLARED
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap">
									<h:selectBooleanCheckbox value="#{awbControl.awb.rated }"/>
									RATED
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap">
									<h:selectBooleanCheckbox value="#{awbControl.awb.noEEI }"/>
									NO EEI REQURED
								</td>
							</tr>
						</table>
					</a4j:region>
				</td>
			</tr>
		</table>
	</rich:panel>
</body>
</html>