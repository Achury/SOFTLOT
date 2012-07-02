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
		<script type="text/javascript" src="../../js/general.js"></script>
		<script type="text/javascript" src="../../js/sessionTimeout.js"></script>
		
		<title>LOTRADING :: MASTERS</title>
	</head>
	
	<body onload = "searchMasters()">
			
	<t:saveState value="#{masterControl.masters}"/>
	<t:saveState value="#{masterControl.master}"/>
	<t:saveState value="#{masterControl.masterValues}"/>
	<t:saveState value="#{masterControl.masterValue}"/>
	<t:saveState value="#{masterControl.selectedMaster}"/>
		<h:form id="formMasters">
			<rich:panel headerClass="stylePanel"  >
			<a4j:jsFunction id="searchMasters" name= "searchMasters" action ="#{masterControl.searchMasterAction}" reRender="masterDataTable"/>
				<f:facet name="header">
		    		<h:outputText value="Master Details" style="font-weight:bold"/>
	    		</f:facet>
				<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: separate;border-spacing:  4px 0px;">
				  	<tr>
				  		<td align = "right"> 
				  			<a4j:commandLink value="" id="FackeLink" style="font-size:11px;" oncomplete="#{rich:component('editPanel')}.show()" reRender="editPanel">	
							</a4j:commandLink>
				  		</td>
						<td align = "right"> 
				  			<a4j:commandLink action="#{masterControl.newMasterValueAction}" value="New Value" id="linkNew"  rendered = "#{masterControl.selectedMaster}"
	                    				style="font-size:11px;" oncomplete="#{rich:component('editPanel')}.show()"  reRender="editPanel" >		
							</a4j:commandLink>
				  		</td>
				  	</tr>
			   		<tr>
			   			<td>
			   			 <rich:panel headerClass="stylePanel" style="border-collapse: separate;border-spacing:  4px 0px;" bodyClass="stylePanel">
			   			 <f:facet name="header">
		    				<h:outputText value="Master Types" style="font-weight:bold"/>
	    				</f:facet>
							 <rich:extendedDataTable id="masterDataTable" height="600px" rowClasses="row1, row2" rows="80" width="550px"
										value="#{masterControl.masters }" var="master"
										sortMode="#{masterControl.sortMode }" selectionMode="#{masterControl.selectionMode }"
										tableState="#{masterControl.tableState }" binding="#{masterControl.table }" title="Masters"> 
										
										<a4j:support
       									event="onRowDblClick"
       									action="#{masterControl.selectedMasterAction}"
       									oncomplete = "#{rich:component('linkNew')}.show()"  
       									reRender="masterValuesPanel, formMasters, linkNew" />
       									
										<rich:column sortable="true" label="Name" id="col_1" 
											sortBy="#{master.name}" width="170px" >
											<f:facet name="header">
												<h:outputText value="Name"></h:outputText>
											</f:facet>
											<h:outputText value="#{master.name}"></h:outputText>
										</rich:column>
										<rich:column sortable="true" label="Description" id="col_2" 
											sortBy="#{master.name}" width="380px" >
											<f:facet name="header">
												<h:outputText value="Description"></h:outputText>
											</f:facet>
											<h:outputText value="#{master.description}"></h:outputText>
										</rich:column>
										
								</rich:extendedDataTable>
							</rich:panel>
							<td>
							<rich:panel headerClass="stylePanel" style="border-collapse: separate;border-spacing:  4px 0px;" bodyClass="stylePanel" id="masterValuesPanel" >
			   			 	<f:facet name="header">
		    					<h:outputText value="#{masterControl.masterValueTitleName}" style="font-weight:bold"/>
	    					</f:facet>
								<rich:extendedDataTable id="masterValueDataTable" height="600px" rowClasses="row1, row2" rows="80" width="600px"
											value="#{masterControl.masterValues }" var="masterValue"
											sortMode="#{masterControl.sortMode }" selectionMode="#{masterControl.selectionMode }"
											tableState="#{masterControl.tableState2 }" binding="#{masterControl.table2 }" title="MasterValues"> 
											<a4j:support
	       									event="onRowDblClick"      								 
	       									oncomplete="#{rich:component('editPanel')}.show()" 
	       									action = "#{masterControl.selectedMasterValue}"
	       									reRender="formMasterValueModal"/>
											<rich:column sortable="true" label="Value 1" id="col_1" 
												sortBy="#{masterValue.value1}" width="150px" >
												<f:facet name="header">
													<h:outputText value="Value 1"></h:outputText>
												</f:facet>
												<h:outputText value="#{masterValue.value1}"></h:outputText>
											</rich:column>
											<rich:column sortable="true" label="Value 2" id="col_2" 
												sortBy="#{masterValue.value2}" width="150px" >
												<f:facet name="header">
													<h:outputText value="Value 2"></h:outputText>
												</f:facet>
												<h:outputText value="#{masterValue.value2}"></h:outputText>
											</rich:column>
											<rich:column sortable="true" label="Value 3" id="col_3" 
												sortBy="#{masterValue.value3}" width="150px" >
												<f:facet name="header">
													<h:outputText value="Value 3"></h:outputText>
												</f:facet>
												<h:outputText value="#{masterValue.value3}"></h:outputText>
											</rich:column>
											<rich:column sortable="true" label="Date" id="col_4" 
												sortBy="#{masterValue.enteredDate}" width="150px" >
												<f:facet name="header">
													<h:outputText value="Date"></h:outputText>
												</f:facet>
												<h:outputText value="#{masterValue.disabledDate}"></h:outputText>
											</rich:column>
								</rich:extendedDataTable>
							 </rich:panel>			
							</td>
						</tr>
					</table>
				</rich:panel>
		</h:form>
		<rich:modalPanel id="editPanel" autosized="true" width="450" style="background-color:#F3F8FC;">
			<f:facet name="header">
		    		<h:outputText value="Value Details" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	       	</f:facet>
			<h:form id="formMasterValueModal">
		  		<rich:messages style="color:red;"></rich:messages>
				<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
				<table width="98%" cellpadding="4">
					<tr height="50" valign="bottom">
						<td nowrap="nowrap" width="100" align="right">
							Value 1:
						</td>
						<td>
							<h:inputText id="value1Text"
								value="#{masterControl.masterValue.value1}" style="width:180px;" tabindex="1"/>
							<rich:message for="value1Text" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					
					<tr>
					<tr>
						<td align="right" width="120px"   >
						Value 2:
						</td>
						<td >	
							<h:inputText id="value2Text"
								value="#{masterControl.masterValue.value2}" style="width:180px;" tabindex="2" />
							<rich:message for="Value2Text" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="Right" width="120px">
						Value 3:
						</td>
						<td >										
							<h:inputText id="value3Text"
								value="#{masterControl.masterValue.value3}" style="width:180px;" tabindex="3"/>
							<rich:message for="Value3Text" style="color:red;" showDetail="false">
		                    	<f:facet name="errorMarker">
		                            <h:graphicImage value="/css/images/error.gif" />   
		                        </f:facet>
		                    </rich:message>
						</td>			
					</tr>
					<tr>
						<td colspan="3" align="center">
							<table cellspacing="17">
								<tr >				
									<td align="center" >
										<a4j:commandButton value="Save" action="#{masterControl.saveMasterValueAction}"
											type="button" styleClass="boton"  status="ajaxStatus" tabindex="4" 
											oncomplete="if (#{!masterControl.savedMasterValue}) {#{rich:component('editPanel')}.hide()}" reRender="masterValueDataTable" /> 
									</td>
									<td align="center">
										<h:commandButton value="Cancel" type="button"
											styleClass="boton" onclick="#{rich:component('editPanel')}.hide()"  tabindex="5"/> 
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