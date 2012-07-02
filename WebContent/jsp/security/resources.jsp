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
		<script type="text/javascript" src="../../js/resources.js"></script>
		<script type="text/javascript" src="../../js/general.js"></script>
		<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
		
		<title>LOTRADING :: OPTIONS</title>
	</head>
	<body onload="document.getElementById('formResources:nameText').focus()">
	<t:saveState value="#{resourceControl.resources}" />
	<t:saveState value="#{resourceControl.resource}" />
		<h:form id="formResources">
			<table width="100%">
				<tr>
					<td width="40%">
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Resources"/>	
					    </f:facet>	
					    <rich:panel>
					    	
					    	<table width="100%" cellpadding="0" cellspacing="1" border="0" class="">
								<tr>
									<td nowrap="nowrap" align="left" width="380">
										Name Resource:
									
										<h:inputText id="nameText"
											value="#{resourceControl.resourceFilter.name}" size="40" />
									</td>	
									<td align="left">
										<table>
											<tr>
												<td align="center">
													<a4j:commandLink ajaxSingle="true" id="buscarLink"
								                        oncomplete="buscar();">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0" />								                    </a4j:commandLink>
													<h:commandLink action="#{resourceControl.searchResourceAction}"
														value="" id="linkSearch" />
													<rich:hotKey key="return" handler="buscar();" />
												</td>
											</tr>
											<tr>
												<td>
													<h:outputText id="found"
												value="#{resourceControl.size}" style="font-size:9px;"/>
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
	                    <a4j:commandLink action="#{resourceControl.newResourceAction}" value="" id="linkNew" 
	                    	style="font-size:11px;" oncomplete="#{rich:component('editPanel')}.show()" reRender="editPanel">
							New Resource			
						</a4j:commandLink>
					</td>
				</tr>				
				<tr>
					<td width="40%">
						<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
						    <f:facet name="header">		
						        <h:outputText value=" Resources"/>	
						    </f:facet>
							<rich:extendedDataTable id="resourcesDataTable"
								rowClasses="row1, row2"
								value="#{resourceControl.resources}" var="resourc" reRender="ds" rows="23"
								sortMode="#{resourceControl.sortMode}"
								selectionMode="#{resourceControl.selectionMode}"
								tableState="#{resourceControl.tableState}" title="Resources"
								binding="#{resourceControl.table}" >
								<a4j:support event="onRowDblClick"
									action="#{resourceControl.selectResourceAction}"
									oncomplete="#{rich:component('editPanel')}.show()" reRender="formResourceModal"/>
								<rich:column sortable="true" label="name" id="col_1" sortBy="#{resourc.name}" width="23%">
									<f:facet name="header">
										<h:outputText value="Resource Name"></h:outputText>
									</f:facet>
									<h:outputText value="#{resourc.name}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="description" id="col_2"
									sortBy="#{resourc.description}" width="30%">
									<f:facet name="header">
										<h:outputText value="Description"></h:outputText>
									</f:facet>
									<h:outputText value="#{resourc.description}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="description" id="col_3" 
										sortBy="#{resourc.type}" width="10%">
									<f:facet name="header">
										<h:outputText value="Type"></h:outputText>
									</f:facet>
									<h:outputText value="#{resourc.type}"></h:outputText>
								</rich:column>
								<f:facet name="footer">
	                				<rich:datascroller align="center" for="resourcesDataTable" 
	                							   maxPages="30" id="ds" />
	                			</f:facet>
							</rich:extendedDataTable>
						</rich:panel>
					</td>					
				</tr>
			</table>
		</h:form>
		<rich:modalPanel id="editPanel" autosized="true" width="450" style="background-color:#F3F8FC;">
			<f:facet name="header">
		    		<h:outputText value="Resource Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	       	</f:facet>
			<h:form id="formResourceModal">
		  		<rich:messages style="color:red;"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
				<table width="98%" cellpadding="4">
					<tr height="50" valign="bottom">
						<td nowrap="nowrap" width="100" align="right">Resource Name:</td>
						<td>
							<h:inputText id="nameText"
								value="#{resourceControl.resource.name}" style="width:180px;" required="true" requiredMessage="- Name field is required"/>
							<h:inputHidden id="resourceId" value="#{resourceControl.resource.resourceId}"/>
							<h:inputHidden id="disabledDate" value="#{resourceControl.resource.disabledDate}"/>
							<rich:message for="nameText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					
					<tr>
						<td nowrap="nowrap" width="150" align="right">Description:</td>
						<td>
							<h:inputText id="descriptText"
								value="#{resourceControl.resource.description}" style="width:180px;" required="true" requiredMessage="- Description field is required"/>
							<rich:message for="nameText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">URL:</td>
						<td>
							<h:inputText id="URLText"
							value="#{resourceControl.resource.url}" style="width:180px;"/></td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">Type:</td>
						<td>
							<h:selectOneMenu  id="typeList" value="#{resourceControl.resource.type}" style="width:70px"  >
								<f:selectItem itemLabel="Form" itemValue="F" />
								<f:selectItem itemLabel="Tool" itemValue="T"/>											
							</h:selectOneMenu>
						</td>
							
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">Function:</td>
						<td>
							<h:inputText id="actionText"
							value="#{resourceControl.resource.action}" style="width:180px;"/></td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">Entered Date:</td>
						<td>
							<h:inputText style="width:60px;"
								value="#{resourceControl.resource.enteredDate}"  readonly="true">
								<f:convertDateTime dateStyle="short" timeZone="EST" type="date" />	
							</h:inputText>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<table cellspacing="17">
								<tr >				
									<td align="center">
										<a4j:commandButton value="Save" action="#{resourceControl.saveResourceAction}"
											type="button" styleClass="boton" onfocus="blur();" status="ajaxStatus"
											reRender="formResourceModal"/> 
									</td>
									<td align="center">
										<h:commandButton value="Cancel" type="button"
											styleClass="boton" onclick="#{rich:component('editPanel')}.hide()" onfocus="blur();" /> 
									</td>
									<td >
										<h:commandButton value="Disable" onclick="#{rich:component('disablePanelRec')}.show();"
											type="button" styleClass="boton" onfocus="blur();" rendered="#{resourceControl.resource.resourceId > 0 }"/> 
									</td>
								</tr>
							</table>
						</td>
					</tr>				
				</table>
			</h:form>
		</rich:modalPanel>
		
		<rich:modalPanel id="disablePanelRec" autosized="true" width="200" style="background-color:#F3F8FC;">
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
		                            ajaxSingle="true" action="#{resourceControl.disableResourceAction}"
		                            oncomplete="#{rich:component('disablePanelRec')}.hide();" />
	                        </td>
	                        <td align="center" width="50%">
	                        	<h:commandButton value="Cancel" type="button" styleClass="boton" onfocus="blur();"
	                        		onclick="#{rich:component('disablePanelRec')}.hide();" />
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </h:form>
	    </rich:modalPanel>
		
		
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