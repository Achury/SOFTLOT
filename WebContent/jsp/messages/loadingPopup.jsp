<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a4j:status id="ajaxStatus"
		onstart="#{rich:component('waitPanel')}.show();"
		onstop="#{rich:component('waitPanel')}.hide()" />
	<rich:modalPanel id="waitPanel" autosized="true" width="125">
		<table>
			<tr>
				<td align="center">
					<h:outputText value="Please wait..."
						style="font-weight:bold;font-size:small" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<h:graphicImage
						value="/css/images/loading.gif" style="border:0" />
				</td>
			</tr>
		</table>
	</rich:modalPanel>	
	</body>
</html>