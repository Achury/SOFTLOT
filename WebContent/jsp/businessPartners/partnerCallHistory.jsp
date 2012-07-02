<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
<script type="text/javascript" src="../../js/partners.js"></script>
</head>
<body>
	<rich:panel style="border:0px"  styleClass="styleGeneralPanel">
		<table width="100%" >
			<tr>
				<td width="300px">
					<h:outputLabel value="Search Notes: "/>
					<h:inputText id="searchNoteText" value="#{partnersControl.filtroCallHistory }" onkeydown="if(event.keyCode == 13){ #{rich:element('filterBoton') }.click() ; }">
					</h:inputText>
					<a4j:commandButton id="filterBoton" value="Search" action="#{partnersControl.FilterCallsHistory }" reRender="callHistDataTable" />
				</td>
				<td>
					<a4j:commandButton value="Print Selected" action="#{partnersControl.repotCallHistoyAction}" status="none" />
				</td>
				<td align="right" valign="bottom">
					<a4j:commandLink id="linkNewCallHist" value="Add Note" style="font-size:11px;" 
						action="#{partnersControl.newCallHistAction }"
						oncomplete="#{rich:component('callHistDetailsPanel')}.show()" reRender="callHistDetailsPanel" />
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<rich:extendedDataTable id="callHistDataTable"
						height="424px" width="100%" rowClasses="row1, row2"
						value="#{partnersControl.callsHistoryFiltered}" var="call"
						rows="15" sortMode="#{partnersControl.sortMode}"
						title="Calls History" binding="#{partnersControl.tableCallHistory}"
						headerClass="#{partnersControl.styleTableHeader }" reRender="ds">	
						
						<a4j:support event="onRowDblClick"
							action="#{partnersControl.selectCallHistoryAction}"
							oncomplete="#{rich:component('callHistDetailsPanel')}.show()" reRender="callHistDetailsPanel"/>
														
						<rich:column sortable="false" label="Print" id="col_1" width="50px" >
							<f:facet name="header">
								<h:outputText value="Print"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox id="checkBoxPrint" value="#{call.print}"/>
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
							<rich:datascroller align="center" status="none"
								for="callHistDataTable" maxPages="50" id="ds"/>
						</f:facet>
					</rich:extendedDataTable>
				</td>
			</tr>
		</table>
	</rich:panel>
</body>
</html>