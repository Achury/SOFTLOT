<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.extdt-cell {
			padding: 0px;
		}
		.rich-panel-body2  {
		    padding: 0px;
		}
		.rich-panel2  {
		    border-width: 0px;
		}
	    .extdt-hsep {
	        display: none;
	    }
	</style>
</head>
<body>	
	<rich:panel id="containersPanel" bodyClass="rich-panel-body2" styleClass="rich-panel2"  >
		<table >		
			<tr>
				<td colspan="3"  width="1223">		
					<a4j:jsFunction name="newContainerJs" action="#{blControl.newBlContainerAction}" reRender="blContainersDataTable" status="none" ajaxSingle="true" process="blContainersDataTable"/>
					<rich:extendedDataTable id="blContainersDataTable"
						height="200px" width="1223" rowClasses="row1,row2"
						value="#{blControl.bl.blContainers}" var="container"
						rows="10" sortMode="#{blControl.sortMode}"
						title="BL Containers" binding="#{blControl.blContainersTable}"							
						reRender="ds2">	
																												
											
						<rich:column sortable="false" label="ID" id="col_1" width="50px">
							<f:facet name="header">
								<h:outputText value="ID"></h:outputText>
							</f:facet>
							<h:inputText rendered="#{!blControl.bl.master}" styleClass="styleInput6" value="#{container.lineNumber}" id="containerLineTxt" style="width:40px;text-align: right"
			                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"
			                		onkeydown="if(event.keyCode == 13){newContainerJs();return false;}">
			                </h:inputText>	
			                <h:inputText rendered="#{blControl.bl.master}" readonly="#{blControl.bl.houseLCL }" styleClass="styleInput6" value="#{container.lineNumber}" id="containerLineTxt2" style="width:40px;text-align: right"
			                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"
			                		onkeydown="if(event.keyCode == 13){newContainerJs();return false;}">
			                </h:inputText>
			                	                
						</rich:column>
						
						<rich:column sortable="false" label="Type" id="col_2" width="100px">
							<f:facet name="header">
								<h:outputText value="Type"></h:outputText>
							</f:facet>								
			                <h:selectOneMenu  id="containerTypeCombobox" value="#{container.type.valueId}" style="width:90px; background-color: transparent;"
			                	onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'">			                 		
								<f:selectItem itemLabel="All" itemValue="" />
								<f:selectItems value="#{blControl.containerTypes}"/>
			                      </h:selectOneMenu>
						</rich:column>
						<rich:column sortable="false" label="Container Number" id="col_3" width="200px">
							<f:facet name="header">
								<h:outputText value="Container Number"></h:outputText>
							</f:facet>
							<h:inputText styleClass="styleInput6" value="#{container.containerNumber}" id="containerNumberTxt" style="width:190px;text-align: right"
			                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"
			                		onkeydown="if(event.keyCode == 13){newContainerJs();return false;}">
			                </h:inputText>
						</rich:column>
						<rich:column sortable="false" label="Seal" id="col_4" width="200px">
							<f:facet name="header">
								<h:outputText value="Seal"></h:outputText>
							</f:facet>
							<h:inputText styleClass="styleInput6" value="#{container.seal}" id="containerSealTxt" style="width:190px;text-align: right"
			                		onfocus="this.className='styleInput7'" onblur="this.className='styleInput6'"
			                		onkeydown="if(event.keyCode == 13){newContainerJs();return false;}">
			                </h:inputText>
						</rich:column>
						<rich:column width="22px" sortable="false" label="Delete" id="col_5">
							<a4j:commandLink ajaxSingle="true" id="deleteBlContainerLink" action="#{blControl.selectToDeleteBlContainerAction}"
								oncomplete="#{rich:component('deleteBlContainerConfirmPopup')}.show();" status="none">										
								<h:graphicImage value="/css/images/Delete.png" style="border:0" />									
							</a4j:commandLink>
						</rich:column>
							
					</rich:extendedDataTable>	
				</td>
			</tr>
		</table>
	</rich:panel>	
</body>
</html>