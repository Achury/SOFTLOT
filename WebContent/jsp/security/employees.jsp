<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib prefix="rich"  uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j"  uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../../js/employees.js"></script>
<script type="text/javascript" src="../../js/general.js"></script>
<script type="text/javascript" src="../../js/sessionTimeout.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
<title>LOTRADING :: EMPLOYEES</title>
</head>
<body onload="document.getElementById('formEmployees:loginText').focus();">
	<t:saveState value="#{employeeControl.employee}"/>
	<t:saveState value="#{employeeControl.employees}"/>
	<t:saveState value="#{employeeControl.roles}"/>
	<t:saveState value="#{employeeControl.availableRoles}"/>
	<t:saveState value="#{employeeControl.departments}"/>
		
	<h:form id="formEmployees">	
		<table width="99%">
			<tr>
				<td>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel" id="searchPanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Search Employees"/>	
					    </f:facet>	
					    <rich:panel >	
					    	<table width="98%" cellpadding="1" cellspacing="1">
								<tr align="right">
									<td nowrap="nowrap" align="right">
										Login:
									</td>
									<td align="left">
										<h:inputText id="loginText"
											value="#{employeeControl.employeeFilter.login}" size="15" />
									</td>
									<td nowrap="nowrap" align="right">
										First Name:
									</td>
									<td align="left">
										<h:inputText id="fnameText"
											value="#{employeeControl.employeeFilter.firstName}" size="20" />
									</td>
									<td nowrap="nowrap" align="right">
										Last Name:
									</td>
									<td align="left">
										<h:inputText id="lnameText" 
											value="#{employeeControl.employeeFilter.lastName}" size="20" />
									</td>	
									<td>
										<table>
											<tr>
												<td align="center">
													<a4j:commandLink ajaxSingle="true"
								                        oncomplete="buscar();">
								                        <h:graphicImage value="/css/images/refresh.png" style="border:0" />								                      
								                    </a4j:commandLink>
								                    <h:commandLink action="#{employeeControl.searchEmployeesAction}"
														value="" id="linkSearch" />
													<rich:hotKey key="return" handler="buscar();" />
												</td>
											</tr>
											<tr>
												<td>
													<h:outputText id="found"
														value="#{employeeControl.size}" style="font-size:9px;"/>
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
		        	<a4j:commandLink action="#{employeeControl.newEmployeeAction}" value="" id="linkNew" 
		        		oncomplete="#{rich:component('editPanel')}.show()" reRender="editPanel" style="font-size:11px;">
						New Employee			
					</a4j:commandLink>
				</td>
			</tr>			
			<tr>
				<td>
					<rich:panel headerClass="stylePanel" bodyClass="stylePanel">	
					    <f:facet name="header">		
					        <h:outputText value=" Employees"/>	
					    </f:facet>
						<rich:extendedDataTable id="employeeDataTable" value="#{employeeControl.employees}" var="employee"
										reRender="ds" rows="23" rowClasses="row1, row2"
										sortMode="#{employeeControl.sortMode}"
										selectionMode="#{employeeControl.selectionMode}"
										tableState="#{employeeControl.tableState}" title="Employees" 
										binding="#{employeeControl.table}" height="491px">
										<a4j:support
	       									event="onRowDblClick"
	       									action="#{employeeControl.selectEmployeeAction}"  
	       									oncomplete="#{rich:component('editPanel')}.show()" 
	       									reRender="formEmployeesModal"/>
							<rich:column sortable="true" label="Login" id="col_1" sortBy="#{employee.login}" width="10%" >
								<f:facet name="header">
									<h:outputText value="Login"></h:outputText>
								</f:facet>
								<h:outputText value="#{employee.login}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="FirstName" id="col_2" sortBy="#{employee.firstName}" width="23%">
								<f:facet name="header">
									<h:outputText value="First Name"></h:outputText>
								</f:facet>
								<h:outputText value="#{employee.firstName}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="LastName" id="col_3" sortBy="#{employee.lastName}" width="23%">
								<f:facet name="header">
									<h:outputText value="Last Name"></h:outputText>
								</f:facet>
								<h:outputText value="#{employee.lastName}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Department" id="col_4" sortBy="#{employee.departmentLotId.value1}" width="20%">
								<f:facet name="header">
									<h:outputText value="Department"></h:outputText>
								</f:facet>
								<h:outputText value="#{employee.departmentLotId.value1}"></h:outputText>
							</rich:column>
							<rich:column sortable="true" label="Email" id="col_5" sortBy="#{employee.email}" width="23%">
								<f:facet name="header">
									<h:outputText value="Email"></h:outputText>
								</f:facet>
								<h:outputText value="#{employee.email}"></h:outputText>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller align="center"
									for="employeeDataTable" maxPages="30" id="ds" />
							</f:facet>
						</rich:extendedDataTable>
					</rich:panel>
				</td>
			</tr>
		</table>
	</h:form>
	<rich:modalPanel id="editPanel" autosized="true" width="650" style="background-color:#F3F8FC;">
		<f:facet name="header">
	    		<h:outputText value="Employee Details" />
    	</f:facet>
    	<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="editPanel" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
       	</f:facet>
		<h:form id="formEmployeesModal">
	  		<rich:messages style="color:red;"></rich:messages>
			<rich:hotKey key="esc" handler="#{rich:component('editPanel')}.hide();"/>
	        <table width="98%" cellpadding="4" cellspacing="0">
				<tr>
					<td nowrap="nowrap" align="right">
						First Name:
					</td>
					<td>
						<h:inputText id="fnameTextM"
							value="#{employeeControl.employee.firstName}" style="width:163px;" required="true" requiredMessage="- First Name is required">
						</h:inputText>
						<rich:message for="fnameTextM" showDetail="false">
	                    	<f:facet name="errorMarker">
	                            <h:graphicImage value="/css/images/error.gif" />   
	                        </f:facet>
	                    </rich:message>
						
					</td>
					<td nowrap="nowrap" align="right" >
						Last Name:
					</td>
					<td width="195px">
						<h:inputText id="lnameTextM"
							value="#{employeeControl.employee.lastName}" style="width:170px;" required="true" requiredMessage="- Last Name is required"/>
						<rich:message for="lnameTextM" showDetail="false">
	                    	<f:facet name="errorMarker">
	                            <h:graphicImage value="/css/images/error.gif" />   
	                        </f:facet>
	                    </rich:message>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right">
						Login:
					</td>
					<td>
						<h:inputHidden id="idEmployeeM"
							value="#{employeeControl.employee.employeeId}"/>
						<h:inputText id="loginTextM"
							value="#{employeeControl.employee.login}" size="3" required="true" requiredMessage="- Login is required"/>
						<rich:message for="loginTextM" showDetail="false">
	                    	<f:facet name="errorMarker">
	                            <h:graphicImage value="/css/images/error.gif" />   
	                        </f:facet>
	                    </rich:message>
						
					</td>
					<td nowrap="nowrap" align="right">
						Password:
					</td>
					<td>
						<h:inputSecret id="passText" redisplay="true"
							value="#{employeeControl.employee.password}" style="width:170px;" required="true" requiredMessage="- Password is required"/>
						<rich:message for="passText" showDetail="false">
	                    	<f:facet name="errorMarker">
	                            <h:graphicImage value="/css/images/error.gif" />   
	                        </f:facet>
	                    </rich:message>	
					</td>
					<%-- <td>

						<h:commandLink action="#{employeeControl.ResetPasswordAction}"
							value="Reset Password" id="linkReset" />
					</td>--%>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right">
						Phone:
					</td>
					<td >
						<h:inputText id="countryCodeText"
							value="#{employeeControl.employee.workPhone.countryCode}"
							size="2" />
							
						<h:inputText id="areaCodeText" 
							value="#{employeeControl.employee.workPhone.areaCode}" size="5" />
						
						<h:inputText id="phoneText"
							value="#{employeeControl.employee.workPhone.phoneNumber}" style="width:89px;" />
						
						
						<h:inputHidden value="#{employeeControl.employee.workPhone.phoneId}"/>
						<h:inputHidden id="phoneDate"
										value="#{employeeControl.employee.workPhone.enteredDate}"/>
						<h:inputHidden id="typePhone"
							value="#{employeeControl.employee.workPhone.type.valueId}"/>
					</td>
					<td nowrap="nowrap" align="right">
						Ext:
					</td>
					<td>
						<h:inputText id="extText"
							value="#{employeeControl.employee.workPhone.phoneExtension}"
							size="5" />
						
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="right">
						Email:
					</td>
					<td>
						<h:inputText id="emailText"
							value="#{employeeControl.employee.email}" style="width:163px;" />
						
					</td>
					<td nowrap="nowrap" align="right">
						Department:
					</td>
					<td >												
						<h:selectOneMenu id="departmentListId" value="#{employeeControl.employee.departmentLotId.valueId}" style="width:173px;">
							<f:selectItems value="#{employeeControl.departments}"/>
						</h:selectOneMenu>
						<h:inputHidden value="#{employeeControl.employee.departmentLotId.value1}" />
					</td>
				</tr>
				<tr>
					<td>
						Region:
					</td>
					<td>
						<h:selectOneMenu value="#{employeeControl.employee.region.valueId }">
							<f:selectItems value="#{employeeControl.regions }"/>
						</h:selectOneMenu>
					</td>
					<td nowrap="nowrap" align="right">
						Entered Date:
					</td>
					<td>
						<h:inputText id="enteredText" readonly="true" style="width:60px;"
							value="#{employeeControl.employee.enteredDate}">
							<f:convertDateTime dateStyle="short" timeZone="EST" type="date" />	
						</h:inputText>
						<h:inputHidden id="enteredDate"
							value="#{employeeControl.employee.enteredDate}"/>
						<h:inputHidden id="updatedDate"
							value="#{employeeControl.employee.updatedDate}"/>
					</td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td colspan="0" align="center">
						<h:panelGrid columns="1" cellpadding="0" cellspacing="0">
							<table width="390" cellspacing="0"  >
								<tr>
									<td width="6"/>
									<td width="140" bgcolor="#DED9D3" align="center">
										Available Roles
									</td>
									<td width="96" bordercolor="#F3F8FC"/>
									<td bgcolor="#DED9D3" align="center" >
										Selected Roles
									</td>
								</tr>
							</table>
							<rich:pickList value="#{employeeControl.roles}" id="rolesList">
				            	<f:selectItems value="#{employeeControl.availableRoles}" />
					        </rich:pickList>
				        </h:panelGrid>
			        </td>
				</tr>
				
				<tr height="40" valign="middle">
					<td colspan="5" align="center">
						<table>
							<tr>
								<td colspan="2" align="center">
									<a4j:commandButton value="Save" type="button"
										styleClass="boton" 
										oncomplete="if (#{facesContext.maximumSeverity == null}) #{rich:component('editPanel')}.hide();"							
										action="#{employeeControl.saveEmployeeAction}" reRender="formEmployeesModal"
										onfocus="blur();"
										/>						
								</td>
								<td width="15"></td>
								<td colspan="3" align="center">
									<h:commandButton value="Cancel" type="button"
										styleClass="boton" onclick="#{rich:component('editPanel')}.hide()"
										onfocus="blur();" />
								</td>
								<td width="15"></td>
								<td>					
									<h:commandButton value="Disable" onclick="#{rich:component('disablePanelEmp')}.show();"
										type="button" styleClass="boton" onfocus="blur();" 
										rendered="#{employeeControl.employee.employeeId > 0}" /> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</h:form>
	  </rich:modalPanel>
	  
	  <rich:modalPanel id="disablePanelEmp" autosized="true" width="200" style="background-color:#F3F8FC;">
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
		                            ajaxSingle="true" action="#{employeeControl.disableEmployeeAction}"
		                            oncomplete="#{rich:component('disablePanelEmp')}.hide();" />
	                        </td>
	                        <td align="center" width="50%">
	                        	<h:commandButton value="Cancel" type="button" styleClass="boton" onfocus="blur();"
	                        		onclick="#{rich:component('disablePanelEmp')}.hide();" />
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