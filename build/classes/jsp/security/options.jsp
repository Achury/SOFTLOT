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
		<script type="text/javascript" src="../../js/options.js"></script>
		<script type="text/javascript" src="../../js/general.js"></script>
		<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
		
		<title>LOTRADING :: OPTIONS</title>      
	</head>
	<body onload="document.getElementById('formOptions:loginText').focus()">
	<t:saveState value="#{optionControl.options}" />
	<t:saveState value="#{optionControl.option}" />
	<t:saveState value="#{optionControl.optionList}" />
	<t:saveState value="#{optionControl.resourceList}" />
	<t:saveState value="#{optionControl.isParentCheckBox }" />
		<h:form id="formOptions">
			<table width="100%">
				<tr>
					<td width="40%">
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Options"/>	
					    </f:facet>	
					    <rich:panel>
					    	
					    	<table width="100%" cellpadding="0" cellspacing="1" border="0" class="">
								<tr>
									<td nowrap="nowrap" align="left" width="380">
										Name Option:
										<h:inputHidden id="idOption"
											value="#{optionControl.optionFilter.optionId}"/>
										<h:inputText id="loginText"
											value="#{optionControl.optionFilter.name}" size="40" />
									</td>	
									<td align="left">
										<table>
											<tr>
												<td align="center">
													<a4j:commandLink ajaxSingle="true" onclick="buscar();">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0" />								                    </a4j:commandLink>													
													<rich:hotKey key="return" handler="buscar();" />
													<h:commandLink action="#{optionControl.searchOptionAction}" id="linkSearch"/>
												</td>
											</tr>
											<tr>
												<td>
													<h:outputText id="found"
												value="#{optionControl.size}" style="font-size:9px;"/>
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
	                    <a4j:commandLink action="#{optionControl.newOptionAction}" value="" id="linkNew" style="font-size:11px;"
							oncomplete="#{rich:component('editPanel')}.show()" reRender="formOptionModal">
							New Option			
						</a4j:commandLink>
					</td>
				</tr>
				<tr>
					<td>
						<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
						    <f:facet name="header">		
						        <h:outputText value=" Options"/>	
						    </f:facet>
							<rich:extendedDataTable id="OptionsDataTable" rowClasses="row1, row2"
								value="#{optionControl.options}" var="option" reRender="ds" rows="23"
								sortMode="#{optionControl.sortMode}"
								selectionMode="#{optionControl.selectionMode}"
								tableState="#{optionControl.tableState}" title="Options"
								binding="#{optionControl.table}">
								<a4j:support event="onRowDblClick"
									action="#{optionControl.selectOptionAction}" 
									oncomplete="#{rich:component('editPanel')}.show()" reRender="formOptionModal"/>
								<rich:column sortable="true" label="name" id="col_1"
									sortBy="#{option.name}" width="20%" >
									<f:facet name="header">
										<h:outputText value="Option Name"></h:outputText>
									</f:facet>
									<h:outputText value="#{option.name}"></h:outputText>
								</rich:column>
								<rich:column sortable="true" label="description" id="col_2"
									sortBy="#{option.description}" width="17%">
									<f:facet name="header">
										<h:outputText value="Description"></h:outputText>
									</f:facet>
									<h:outputText value="#{option.description}"></h:outputText>
								</rich:column>
								<f:facet name="footer">
	                				<rich:datascroller align="center" for="OptionsDataTable" 
	                							   maxPages="30" id="ds" />
	                			</f:facet>
							</rich:extendedDataTable>
						</rich:panel>
					</td>				
				</tr>
			</table>
		</h:form>
		<rich:modalPanel id="editPanel" autosized="true" width="350" style="background-color:#F3F8FC;">
			<f:facet name="header">
		    		<h:outputText value="Options Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	       	</f:facet>
			<h:form id="formOptionModal">
		  		<rich:messages style="color:red;"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
				<table cellpadding="4" align="center">
					<tr height="30" valign="bottom">
						<td nowrap="nowrap"  align="right">Option Name:</td>
						<td>
							<h:inputHidden id="optionId" value="#{optionControl.option.optionId}"/>
							<h:inputHidden id="enteredDate" value="#{optionControl.option.enteredDate}"/>
							<h:inputText id="nameText"
								value="#{optionControl.option.name}" style="width:150px;" required="true" requiredMessage="- Option Name is required"/>
							<rich:message for="nameText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>					
					<tr>
						<td nowrap="nowrap" align="right">Description:</td>
						<td >
							<h:inputText id="descriptText"
								value="#{optionControl.option.description}" style="width:150px;" required="true" requiredMessage="- Description is required"/>
							<rich:message for="descriptText" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>	
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">Is Parent:</td>
						<td >
							<h:selectBooleanCheckbox id="isParentcheckbox" value="#{optionControl.option.parent }" binding="#{optionControl.isParentCheckBox }" >
								<a4j:support event="onchange" reRender="optionListId,ResourceListId"  action="#{optionControl.setValueIsparent }"
									status="none" ajaxSingle="true"/>
							</h:selectBooleanCheckbox>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">Resource:</td>
						<td >
							<h:selectOneMenu id="optionListId" value="#{optionControl.option.resource.resourceId }" 
								style="width:150px;" disabled="#{optionControl.option.parent }">
								<f:selectItem itemLabel="--"/>
								<f:selectItems value="#{optionControl.resourceList}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" >Parent Option:</td>
						<td >
							<h:selectOneMenu id="ResourceListId" value="#{optionControl.option.parentId }" 
								style="width:150px;" disabled="#{optionControl.option.parent }">
								<f:selectItem itemLabel="--"/>
								<f:selectItems value="#{optionControl.optionList}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr height="40" valign="middle">
						<td colspan="2">
							<table align="center">
								<tr>
									<td align="center">	
										<a4j:commandButton value="Save" action="#{optionControl.saveOptionAction}"
											type="button" styleClass="boton" onfocus="blur();" reRender="formOptionModal"/>
									</td>
									<td align="center">
										<h:commandButton value="Cancel" type="button" 
											styleClass="boton" onclick="#{rich:component('editPanel')}.hide()" onfocus="blur();" />
									</td>					
									<td align="center">
										<a4j:commandButton value="Disable" onclick="#{rich:component('disablePanelOpt')}.show();"
											type="button" styleClass="boton" onfocus="blur();" reRender="formOptionModal"
											rendered="#{optionControl.option.optionId > 0 }" status="none" ajaxSingle="true"/>										
									</td>
								</tr>
							</table>
						</td>
					</tr>											
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
		
		<rich:modalPanel id="disablePanelOpt" autosized="true" width="200" style="background-color:#F3F8FC;">
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
		                            ajaxSingle="true" action="#{optionControl.disableOptionAction}"
		                            oncomplete="#{rich:component('disablePanelOpt')}.hide();" />
	                        </td>
	                        <td align="center" width="50%">
	                        	<h:commandButton value="Cancel" type="button" styleClass="boton" onfocus="blur();"
	                        		onclick="#{rich:component('disablePanelOpt')}.hide();" />
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </h:form>
	    </rich:modalPanel>
	</body>
	</html>
</f:view>