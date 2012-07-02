<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<a4j:outputPanel id="msg" styleClass="mensajes">
    <h:panelGrid style="text-align:center" columnClasses="columnaIcono,columnaMensaje" columns="2" cellpadding="0" cellspacing="0" width="100%" rendered="#{! empty facesContext.maximumSeverity}">
            <h:column>
            	<h:graphicImage value="#{pageMessages.messageImage}" style="float: left; vertical-align: top;" />
            </h:column>
            <h:column>
            	<h:panelGrid columns="1" cellpadding="0" cellspacing="0" width="100%">           
                	<h:outputText value="#{pageMessages.messageHeader}" escape="false" rendered="#{pageMessages.renderMessage}"/>
                	<h:messages fatalClass="error" warnClass="advertencia" errorClass="error" infoClass="info" layout="table" globalOnly="true" showDetail="false" showSummary="true"/>
            	</h:panelGrid>
            </h:column>
    </h:panelGrid>
</a4j:outputPanel>