<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<a4j:loadScript src="resource:///org/richfaces/renderkit/html/scripts/jquery/jquery.js"/>
	<script type="text/javascript">
		function getChoice(){
			return jQuery("select[name='formBl:moreInfoTab:saidToContainComboBox'] option:selected").html();
		}
	</script>
	<style type="text/css">
		.rich-table-cell {
			padding: 0px;
		}	
		.rich-sdt-column-cell {
			padding: 0px;
		}
		.rich-sdt-hsep {
	        display: none;
	    }
	    .rich-sdt-c-f{
	    	 display: none;
	    }
	  
	</style>
</head>
<body>
	<rich:panel id="moreInfoPanel" style="border: none;">
		<table border="0" style="padding:0px;border-collapse: separate;border-spacing:  0px 0px;">
			<tr valign="top">
				
				<td valign="top" width="265px" >
					<table cellspacing="0">
						
						<tr>
							<td nowrap="nowrap" align="right">
								Vessel/Voyage:
							</td>
							<td>
								<h:inputText id="vesselVoyageText" value="#{blControl.bl.vessel_voyage}" onchange="setFlagModif('true');" tabindex="19"></h:inputText>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">
								Declared Value:
							</td>
							<td>
								<h:inputText id="declaredValueText" value="#{blControl.bl.declaredValue}" ondblclick="if(#{blControl.bl.blId} > 0) openDeclaredValuesJs();" 
									style="background: #FFFFFF;color: #000000;text-align:right"	readonly="true"  >
									<f:converter converterId="#{blControl.converterName }"/>
								</h:inputText>
									<a4j:jsFunction name="openDeclaredValuesJs" action="#{blControl.loadDeclaredValuesAction}" oncomplete="#{rich:component('declaredValuePopup') }.show()" reRender="declaredValueDataTable,declaredValueText "/>									
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">
								Loading Pier Terminal:
							</td>
							<td>
								<h:inputText id="loadingPierTerminal" value="#{blControl.bl.pierOfOrigin}" onchange="setFlagModif('true');" tabindex="21"></h:inputText>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">
								ETD:
							</td>
							<td>								
								<rich:calendar id="etd" cellWidth="20px" datePattern="MM/d/yyyy"  tabindex="24" inputStyle="width:134px;"
									value="#{blControl.bl.etd }" enableManualInput="true" 
									oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('formBl:blMoreInfoTab:etdInputDate');">
								</rich:calendar>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">
								ETA:
							</td>
							<td>
								<rich:calendar id="eta" cellWidth="20px" datePattern="MM/d/yyyy"  tabindex="25" inputStyle="width:134px;"
									value="#{blControl.bl.eta }" enableManualInput="true" 
									oninputkeypress="return validateNumbers2(event);"  oninputblur="autoCompleteDate2('formBl:blMoreInfoTab:etaInputDate');">
								</rich:calendar>
							</td>
						</tr>	
						
					</table>
				</td>
				<td valign="top" colspan="2" width="275px" >
					<a4j:jsFunction name="newUnCodeItemJs" action="#{blControl.newItemUnCodesAction}" reRender="unCodesDataTable" status="none" ajaxSingle="true" process="unCodesDataTable"/>
					
					<rich:dataTable id="unCodesDataTable" value="#{blControl.bl.blUnCodes }" var="unCode"  rowClasses="row1, row2" 
						rows="10" binding="#{blControl.blUnCodesTable}">
						<rich:column sortable="false" label="UN Code" id="col_1" width="65px" >
							<f:facet name="header">
								<h:outputText value="UN Code" ></h:outputText>
							</f:facet>
							<h:inputText value="#{unCode.unCode }" styleClass="styleInput6" style="width:63px" onfocus="this.className='styleInput7'"
								 onblur="this.className='styleInput6'" rendered="#{!blControl.bl.master}"  disabled="#{!blControl.bl.containHazardousItems}"
								 onkeydown="if(event.keyCode == 13){newUnCodeItemJs();return false;}">
							</h:inputText>
							<h:outputText value="#{unCode.unCode }" rendered="#{blControl.bl.master}" styleClass="styleInput6" style=" background-color: transparent; color: #000000;"></h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Class" id="col_2" width="53px">
							<f:facet name="header">
								<h:outputText value="Class" ></h:outputText>
							</f:facet>
							
							<h:selectOneMenu id="unClassComboBox" value="#{unCode.unClassId}" disabled="#{blControl.bl.master || !blControl.bl.containHazardousItems }"
								onkeydown="if(event.keyCode == 13){newUnCodeItemJs();}">
								<f:selectItem itemLabel="--SELECT--" />
								<f:selectItems value="#{blControl.classUnCodeList}"/>
							</h:selectOneMenu>
						</rich:column>
						
						<rich:column sortable="false" label="Packing Group" id="col_3" width="50px">
							<f:facet name="header">
								<h:outputText value="PG" > </h:outputText>
							</f:facet>							
							<h:selectOneMenu id="packingGroupComboBox" value="#{unCode.packingGroupId}" disabled="#{blControl.bl.master || !blControl.bl.containHazardousItems}"
								onkeydown="if(event.keyCode == 13){newUnCodeItemJs();}">
								<f:selectItem itemLabel="--SELECT--"/>
								<f:selectItems value="#{blControl.packingGroupUnCodeList}"/>
							</h:selectOneMenu>
						</rich:column>	
						
						<rich:column width="16px" sortable="false" label="Delete" id="col_4"  >							
							<a4j:commandLink ajaxSingle="true" id="deleteUNItemLink" oncomplete="#{rich:component('deleteUnCodeConfirmPopup')}.show();" 
								action="#{blControl.selectToDeleteUnCodeItemAction}" status="none"  rendered="#{unCode.unCodeId > 0 && !blControl.bl.master }">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
							</a4j:commandLink>
						</rich:column>
					</rich:dataTable>	
					<br>
					# Of UN Codes:&nbsp;&nbsp;<h:outputText value="#{blControl.numUNCodes}" style="width:40px"/>
				</td>
				<td valign="top" align="center" width="228px" rowspan="2">
					<a4j:jsFunction name="newEeiItemJs" action="#{blControl.newItemEEIAction}" reRender="eeiDataTable" status="none" ajaxSingle="true" process="eeiDataTable"/>
										
					<rich:dataTable id="eeiDataTable" value="#{blControl.bl.blEEIList}" var="eei" rowClasses="row1, row2" rows="30"
						binding="#{blControl.blEeiTable }" rendered="#{!blControl.bl.master}">
						<rich:column sortable="false" label="EEI" id="col_1" width="100px">
							<f:facet name="header">
								<h:outputText value="EEI" style="font-weight:bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{eei.eei }" styleClass="styleInput6"   style="width:98px" onfocus="this.className='styleInput7'" 
								onblur="this.className='styleInput6'" onkeydown="if(event.keyCode == 13){newEeiItemJs();return false;}" rendered="#{!blControl.bl.master}">
							</h:inputText>
							<h:outputText value="#{eei.eei}" rendered="#{blControl.bl.master}" styleClass="styleInput6" style=" background-color: transparent; color: #000000;"></h:outputText>
						</rich:column>
						
						<rich:column sortable="false" label="Supplier" id="col_2" width="98px">
							<f:facet name="header">
								<h:outputText value="Supplier" style="font-weight:bold;" ></h:outputText>
							</f:facet>
							<h:inputText value="#{eei.supplier}" styleClass="styleInput6"   style="width:96px" onfocus="this.className='styleInput7'" 
								onblur="this.className='styleInput6'" onkeydown="if(event.keyCode == 13){newEeiItemJs();return false;}" rendered="#{!blControl.bl.master}">
							</h:inputText>
							<h:outputText value="#{eei.supplier}" rendered="#{blControl.bl.master}" styleClass="styleInput6" style=" background-color: transparent; color: #000000;"></h:outputText>
						</rich:column>
						<rich:column width="19px" sortable="false" label="Delete" id="col_3" >
							<a4j:commandLink ajaxSingle="true" id="deleteBlItemLink" rendered="#{eei.eeiId > 0  && !blControl.bl.master }"
								action="#{blControl.selectToDeleteEeiItemAction }" status="none"
								oncomplete="#{rich:component('deleteEEIConfirmPopup')}.show();">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
							</a4j:commandLink>
						</rich:column>
						
					</rich:dataTable>
					
					<a4j:region rendered="#{blControl.bl.master}">
						<div style="position: relative;" >
							<div class="headerPanel" align="center" style="left: 28%; top: -8%; width: 28%" >Payment Type</div>
							<rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px;width:140px;" >
								<table cellpadding="1" cellspacing="1" border="0">	
									<tr height="30px" valign="bottom">
										<td nowrap="nowrap" align="left" colspan="2">
											<h:selectOneRadio value="#{blControl.bl.paymentType.valueId}" >
												<f:selectItems value="#{blControl.paymentTypeList }" />											
											</h:selectOneRadio>
										</td>
									</tr>
								</table>
							</rich:panel>
						</div>
					</a4j:region>	
									
				</td>
				<td valign="top" width="221px" rowspan="2">	
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left">
								Said To Contain:
							</td>
							<td align="right">
								<a4j:commandLink value="[+]" style="text-decoration: none;" action="#{blControl.newSaidToContainAction }" onclick="#{rich:component('newSaidToContainPopup') }.show();" status="none" ajaxSingle="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<h:selectOneMenu  id="saidToContainComboBox" styleClass="saidToContainItem" style="width:238px" onchange="#{rich:element('saidToContainText')}.value = getChoice();" >
									<f:selectItem itemLabel=""   />
									<f:selectItems value="#{blControl.saidToContainList}" />
							    </h:selectOneMenu>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="93px"  valign="top">
								<h:inputTextarea value="#{blControl.bl.saidToContain }" id="saidToContainText" style="width:235px; height:80px" />
							</td>
						</tr>
					</table>
					
					Export Instructions:<br>
					<h:inputTextarea style="width:235px; height:66px;" value="#{blControl.bl.exportInstructions }" onchange="setFlagModif('true');" tabindex="23"></h:inputTextarea>
				</td>
				<td valign="top" rowspan="1">
					<table>						
						<tr>
							<td nowrap="nowrap">
								<h:selectBooleanCheckbox value="#{blControl.bl.rated }"/>
								RATED
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap">
								<h:selectBooleanCheckbox value="#{blControl.bl.noEEI }"/>
								NO EEI REQURED
							</td>
						</tr>
						
						<tr height="30px" valign="bottom" >
							<td height="97px" valign="bottom" style="padding:39px 1px;" align="left">					
								<div style="position: relative;">
									<div class="headerPanel" align="center" style="top: -9%;width: 50%;" > Type Of Move </div>
									 <rich:panel styleClass="stylefilterDatePanel" style="border-radius:8px; width: 160px;">	
										<h:panelGrid columns="1">										   
										   	<h:panelGroup >	
												<t:selectOneRadio id="moves" layout="spread"  value="#{blControl.bl.typeOfMove.valueId}" >
													<f:selectItems value="#{blControl.blTypeOfMovesList }" />	
												</t:selectOneRadio>
												 <h:panelGrid columns="2">
											        <t:radio for="moves" index="0" />
											        <t:radio for="moves" index="1" />
											        <t:radio for="moves" index="2" />
											        <t:radio for="moves" index="3" />
											        <t:radio for="moves" index="4" />
									      		</h:panelGrid>		
									      	</h:panelGroup>	
									      </h:panelGrid>
								 	</rich:panel> 	
								 </div>																
							</td>
						</tr>	
					</table>
				</td>				
				
			</tr>
		</table>
	</rich:panel>
</body>
</html>