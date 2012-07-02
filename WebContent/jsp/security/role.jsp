<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib prefix="rich"  uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j"  uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
		<script type="text/javascript" src="../../js/roles.js"></script>
		<script type="text/javascript" src="../../js/general.js"></script>
		<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
		
		<title>LOTRADING :: ROLES</title>
	</head>
	<body onload="document.getElementById('formRole:nameText').focus()">
		<t:saveState value="#{roleControl.role}"/>
		<t:saveState value="#{roleControl.roles}"/>
		<t:saveState value="#{roleControl.availableOptions}"/>
		<t:saveState value="#{roleControl.options}"/>
		<t:saveState value="#{roleControl.availableResources}"/>
		<t:saveState value="#{roleControl.availableActions}"/>
		<t:saveState value="#{roleControl.resources}"/>
		<t:saveState value="#{roleControl.forms}"/>
		<h:form id="formRole">
			<rich:panel headerClass="stylePanel" bodyClass="stylePanel">
				<f:facet name="header">
		    		<h:outputText value="Role Details" />
	    		</f:facet>
	    		<rich:panel>	
					<table cellspacing="1" cellpadding="1" id="tablaedit">
						<tr>
							<td nowrap="nowrap" width="80" align="right">
								Role Name:
							</td>
							<td>
								<h:inputHidden id="roleId" value="#{roleControl.role.roleId}"/>
								<h:inputHidden id="enteredDate" value="#{roleControl.role.enteredDate}"/>
								<h:inputText id="nameText"
									value="#{roleControl.role.name}" style="width:170px;" />
							</td>
						
							<td nowrap="nowrap" width="80" align="right">
								Description:
							</td>
							<td>
								<h:inputText id="descriptText" value="#{roleControl.role.description}" style="width:200px;" />
							</td>
						
							<td style="width:70px;" align="center">
								<a4j:commandButton value="Save" type="button"
									styleClass="boton" action="#{roleControl.saveRoleAction}"
									onfocus="blur();" reRender="tabpanel"/>
							</td>
							<td style="width:70px;" align="center">
								<a4j:commandButton value="Cancel" type="button"
									styleClass="boton" action="#{roleControl.backAction}"
									onfocus="blur();" />
							</td>
							<td style="width:80px;" align="center">
								<a4j:commandButton value="Disable" type="button" status="none" ajaxSingle="true"
									styleClass="boton" onclick="#{rich:component('disablePanelRole')}.show();"
									onfocus="blur();" rendered="#{roleControl.role.roleId > 0 }"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" nowrap="nowrap">
								<%@ include file="/jsp/messages/messages.jsp"%>
							</td>
						</tr>											
					</table>
					<rich:tabPanel id="tabpanel" switchType="client" rendered="#{roleControl.role.roleId>0}" selectedTab="#{roleControl.selectedTab}">
						<rich:tab label="Options" id="Options">
				        	<h:panelGrid columns="1" columnClasses="top, top" cellpadding="0" cellspacing="0" >
				        		<table width="390" cellspacing="0" >
									<tr >
										<td width="6"/>
										<td width="140" bgcolor="#C8DEEC" align="center" bordercolor="#C4C0B9" >
											Available Options
										</td>
										<td width="96" />
										<td bgcolor="#C8DEEC" align="center" >
											Selected Options
										</td>
									</tr>
								</table>
					            <rich:pickList value="#{roleControl.options}">
					            	<f:selectItems value="#{roleControl.availableOptions}" />
						        </rich:pickList>
					        </h:panelGrid>
				        </rich:tab>
				        <rich:tab label="Resources" id="Resources">
				            <h:panelGrid columns="2" columnClasses="top, top" cellspacing="30">
						        <rich:orderingList id="resources" value="#{roleControl.availableResources}" var="res" 
						        	listHeight="150" listWidth="200" rowClasses="row1, row2"
						        	selection="#{roleControl.selectedResources}" fastOrderControlsVisible="false"
						        	orderControlsVisible="false" converter="resourceConverter" >
						        	<rich:column  width="200" headerClass="styleDataTable">
							            <f:facet name="header">
							                <h:outputText value="Resources" />
							            </f:facet> 
							            <h:outputText value="#{res.resourceId} - #{res.name}"></h:outputText>
							        </rich:column>
							        <a4j:support event="onclick" ignoreDupResponses="true" status="none"
							        	action="#{roleControl.selectResourceAction}" reRender="actions"/>
	            					<a4j:support event="onkeyup" ignoreDupResponses="true" status="none"
	            						action="#{roleControl.selectResourceAction}" reRender="actions"/>
						        </rich:orderingList>
						        <h:panelGrid columns="1" cellpadding="0" cellspacing="0">
						        	<table width="390" cellspacing="0" >
										<tr>
											<td width="6"/>
											<td width="140" bgcolor="#C8DEEC" align="center">
												Available Actions
											</td>
											<td width="96" />
											<td bgcolor="#C8DEEC" align="center" >
												Selected Actions
											</td>
										</tr>
									</table>
						        	<rich:pickList id="actions" value="#{roleControl.resources}" >
						            	<f:selectItems value="#{roleControl.availableActions}" />
							        </rich:pickList>
						        </h:panelGrid>							  
						        <a4j:commandButton value="Update" type="button"
									styleClass="boton" reRender="ResourceDataTable"
									onfocus="blur();" action="#{roleControl.addResource}"/>
				            </h:panelGrid>
				            <rich:extendedDataTable id="ResourceDataTable" width="75%" height="263px" 
									value="#{roleControl.forms}" var="res" rows="11"
									reRender="datasc" title="Resources" rowClasses="row1, row2"
									binding="#{roleControl.tableRes}" headerClass="styleDataTable">
								<rich:column width="7%">
									<f:facet name="header">
										<h:outputText value="ID"></h:outputText>
									</f:facet>
									<h:outputText value="#{res.resourceId}"></h:outputText>
								</rich:column>
								<rich:column width="20%">
									<f:facet name="header">
										<h:outputText value="Name"></h:outputText>
									</f:facet>
									<h:outputText value="#{res.name}"></h:outputText>
								</rich:column>
								<rich:column width="15%">
									<f:facet name="header">
										<h:outputText value="Actions"></h:outputText>
									</f:facet>
									<a4j:commandLink action="#{roleControl.deleteResource}"
										value="Remove" id="linkDelete" reRender="msg,ResourceDataTable"/>
								</rich:column>
								<rich:column width="58%">
									<f:facet name="header">
										<h:outputText value=" Actions"></h:outputText>
									</f:facet>
									<h:outputText value="#{res.stringActions}"></h:outputText>
								</rich:column>
								<f:facet name="footer">
			                		<rich:datascroller page="#{roleControl.pagina}" align="center" for="ResourceDataTable" 
			                			maxPages="10" id="datasc" status="none"/>
			                	</f:facet>	
							</rich:extendedDataTable>
				        </rich:tab>
					</rich:tabPanel>
				</rich:panel>
			</rich:panel>
			
		</h:form>
		<a4j:status id="ajaxStatus" onstart="#{rich:component('waitPanel')}.show()"
			onstop="#{rich:component('waitPanel')}.hide()" />
			
		<rich:modalPanel id="waitPanel" autosized="true" width="125">
			<table>
				<tr >
					<td align="center">
						<h:outputText value="Please wait..." style="font-weight:bold;font-size:small" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<h:graphicImage value="/css/images/loading.gif" style="border:0" />
					</td>
				</tr>
			</table>	
		</rich:modalPanel>
		
		<rich:modalPanel id="disablePanelRole" autosized="true" width="200" style="background-color:#F3F8FC;">
	        <f:facet name="header">
	            <h:outputText value="Are you sure?"
	                style="padding-right:15px;" />
	        </f:facet>
	        <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/images/modal/close.png"
	                    styleClass="hidelink" id="hidelink2" />
	                <rich:componentControl for="disablePanel" attachTo="hidelink2"
	                    operation="hide" event="onclick" />
	            </h:panelGroup>
	        </f:facet>
	        <h:form>
	            <table width="100%">
	                <tbody>
	                    <tr>
	                        <td align="center" width="50%">
		                        <a4j:commandButton value="Yes"
		                            ajaxSingle="true" action="#{roleControl.disableRoleAction}"
		                            oncomplete="#{rich:component('disablePanelRole')}.hide();" />
	                        </td>
	                        <td align="center" width="50%">
	                        	<h:commandButton value="Cancel" type="button" styleClass="boton" onfocus="blur();"
	                        		onclick="#{rich:component('disablePanelRole')}.hide();" />
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </h:form>
	    </rich:modalPanel>
	</body>
	</html>
</f:view>