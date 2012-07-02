<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
</head>
<body>
	<rich:panel style="border:0px"  styleClass="styleGeneralPanel">
		<table width="100%" >
			<tr>
				<td width="300px">
					<h:outputLabel value="Search Notes: "/>
					<h:inputText id="searchCallSuppText" value="#{clienOrderControl.filtroCallHistory }" onkeydown="if(event.keyCode == 13){ #{rich:element('searchCallSuppButton') }.click() ; }">
					</h:inputText>
					<a4j:commandButton id="searchCallSuppButton" value="Search" action="#{clienOrderControl.filterSupplierCallsHistoryAction}" reRender="callSuppDataTable" />
				</td>
				<td>
					<a4j:commandButton value="Print Selected" />
				</td>
				<td align="right" valign="bottom">
					<a4j:commandLink id="newCallSuppLink" value="Add Note" style="font-size:11px;" 
						action="#{clienOrderControl.newCallHistoryAction }"
						oncomplete="#{rich:component('callHistDetailsPanel')}.show()" reRender="callHistDetailsPanel" onblur="#{rich:element('orderNumText')}.focus();"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<rich:extendedDataTable id="callSuppDataTable"
						height="351px" width="100%" rowClasses="row1,row2"
						value="#{clienOrderControl.callsHistorySupplierFiltered}" var="call"
						rows="13" sortMode="#{clienOrderControl.sortMode}"
						title="Calls History" binding="#{clienOrderControl.tableCallHistSupp}"
						reRender="ds1">	
						
						<a4j:support event="onRowDblClick"
							action="#{clienOrderControl.selectCallHistAction}"
							oncomplete="#{rich:component('callHistDetailsPanel')}.show()" reRender="callHistDetailsPanel"/>
														
						<rich:column sortable="false" label="Print" id="col_1" width="50px" >
							<f:facet name="header">
								<h:outputText value="Print"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox id="checkBoxPrint" />
						</rich:column>						
						<rich:column sortable="true" label="Date" id="col_2" width="100px" sortBy="#{call.enteredDate }">
							<f:facet name="header">
								<h:outputText value="Date"></h:outputText>
							</f:facet>
							<h:outputText value="#{call.enteredDate}"></h:outputText>
						</rich:column>
						<rich:column sortable="true" label="Performed" id="col_3" width="100px" sortBy="#{call.employeeCreator.login }">
							<f:facet name="header">
								<h:outputText value="Performed By"></h:outputText>
							</f:facet>
							<h:outputText value="#{call.employeeCreator.login}"></h:outputText>
						</rich:column>
						<rich:column sortable="true" label="Contact" id="col_4" width="200px" sortBy="#{call.contact.name }">
							<f:facet name="header">
								<h:outputText value="Contact"></h:outputText>
							</f:facet>
							<h:outputText value="#{call.contact.name}"></h:outputText>
						</rich:column>
						<rich:column sortable="false" label="Notes" id="col_5" width="800px" >
							<f:facet name="header">
								<h:outputText value="Notes"></h:outputText>
							</f:facet>
							<h:outputText value="#{call.notes}"></h:outputText>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller id="ds1" align="center" status="none"
								for="callSuppDataTable" maxPages="50" />
						</f:facet>
					</rich:extendedDataTable>
				</td>
			</tr>
		</table>
	</rich:panel>
</body>
</html>