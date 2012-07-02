<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<body>
		<rich:modalPanel id="messagesPanel" autosized="true" width="250" onshow="setTimeout(function(){#{rich:component('messagesPanel')}.hide();clearMessages();},2000)">
		    <f:facet name="header">
	    		<h:outputText value="Message" />
	    	</f:facet>
	    	<f:facet name="controls">
	            <h:panelGroup>	            	
	                <h:graphicImage value="/css/images/close-popup.png" styleClass="hidelink" id="hidelink23"/>
	                <rich:componentControl for="messagesPanel" attachTo="hidelink23" operation="hide" event="onclick"/>	                
	            </h:panelGroup>
        	</f:facet>
	    	<h:form>	    		
				<rich:messages infoLabelClass="labelinfoStyle" errorLabelClass="labelerrorStyle" ></rich:messages>				
				<rich:hotKey key="esc" handler="#{rich:component('messagesPanel')}.hide();"/>	
				<a4j:jsFunction name="clearMessages" action="#{clienOrderControl.clearMessages}" ajaxSingle="true" status="none"></a4j:jsFunction>			
	       </h:form>
	 </rich:modalPanel>
	</body>
</html>