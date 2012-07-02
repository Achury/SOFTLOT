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
	<body onload="document.getElementById('formRoles:nameTextF').focus()">
		<t:saveState value="#{roleControl.roles}" />
		<t:saveState value="#{roleControl.availableOptions}" />
		<t:saveState value="#{roleControl.options}" />
		<t:saveState value="#{roleControl.availableResources}" />
		<t:saveState value="#{roleControl.resources}" />
		<h:form id="formRoles">
			<table width="100%">
				<tr>
					<td width="100%">
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Roles"/>	
					    </f:facet>	
					    <rich:panel>
					    	
					    	<table width="100%" cellpadding="0" cellspacing="1" border="0" class="">
								<tr>
									<td nowrap="nowrap" align="left" width="380">
										Role Name:
										<h:inputHidden id="idRole"
											value="#{roleControl.role.roleId}"/>
										<h:inputText id="nameTextF"
											value="#{roleControl.role.name}" size="40" />
									</td>	
									<td align="left">
										<table>
											<tr>
												<td align="center">
													<a4j:commandLink ajaxSingle="true" reRender="RoleDataTable" onclick="searchRoles();">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0" />								                    </a4j:commandLink>												
													<rich:hotKey key="return" handler="searchRoles();" />
													<h:commandLink action="#{roleControl.searchRoleAction}" id="linkSearch"/>
												</td>
											</tr>
											<tr>
												<td>
													<h:outputText id="found"
												value="#{roleControl.size}" style="font-size:9px;"/>
												</td>
											</tr>
										</table>
									</td>	
									<td align="right">
										<h:graphicImage value="/css/images/logo.png" width="180" height="70" />
									</td>		
								</tr>							
							</table>	
					    </rich:panel>		
					</rich:panel>
					</td>
				</tr>
				<tr>
					<td align="right">		
	                    <a4j:commandLink action="#{roleControl.newRoleAction}" value="" id="linkNew" style="font-size:11px;">
							New Role			
						</a4j:commandLink>
					</td>
				</tr>
				<tr>
					<td>
						<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
						    <f:facet name="header">		
						        <h:outputText value=" Roles"/>	
						    </f:facet>
							<rich:extendedDataTable id="RoleDataTable" rowClasses="row1, row2"
								value="#{roleControl.roles}" var="rol" reRender="ds" rows="23"
								sortMode="#{roleControl.sortMode}"
								selectionMode="#{roleControl.selectionMode}"
								tableState="#{roleControl.tableState}" title="Options"
								binding="#{roleControl.table}">
								<a4j:support event="onRowDblClick"
									action="#{roleControl.selectRoleAction}"/>
								<rich:column sortable="true" label="name" id="col_1"
									sortBy="#{rol.name}" width="20%" >
									<f:facet name="header">
										<h:outputText value="Role Name"></h:outputText>
									</f:facet>
									<h:outputText value="#{rol.name}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="description" id="col_2"
									sortBy="#{rol.description}" width="17%">
									<f:facet name="header">
										<h:outputText value="Description"></h:outputText>
									</f:facet>
									<h:outputText value="#{rol.description}"></h:outputText>
								</rich:column>
								<f:facet name="footer">
	                				<rich:datascroller align="center" for="RoleDataTable" 
	                							   maxPages="30" id="ds" />
	                			</f:facet>
							</rich:extendedDataTable>
						</rich:panel>
					</td>
					<td> 
					</td>					
				</tr>
			</table>	
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
	</body>
	</html>
</f:view>