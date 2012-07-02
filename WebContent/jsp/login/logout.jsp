<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" language="JavaScript1.2" src="../../js/general.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../../js/login.js"></script>
<title>Insert title here</title>
</head>
<body onload="javascript:logout();">
<f:view>
	<h:form id="formLogout">
		<h:commandLink action="#{loginControl.logoutAction}" value="" id="linkLogout" />
	</h:form>
</f:view>
</body>
</html>