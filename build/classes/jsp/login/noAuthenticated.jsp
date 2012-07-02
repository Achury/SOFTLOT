<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../../css/jquery-ui.css">
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.11.custom.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var $dialog = $('<div></div>')
		.html('<br/ ><br >Please go to <A href="login.jsp">login page</A></br>')
		.dialog({
		autoOpen: true,
		title: 'Session Expired!',
		open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
		show: "bounce"
		});
		$('#opener').click(function() {
		$dialog.dialog('open');
		// prevent the default action, e.g., following a link
		return false;
		});
	});
</script> 

</head>
	<body>
		<f:view>
			<%-- <table cellpadding="8">
				<tr>
					<td>
						<h:outputLabel>
							Session Expired!
						</h:outputLabel>
					</td>
				</tr>
				<tr>
					<td>
						<h:outputLabel>
							Please go to Login Page.
						</h:outputLabel>
					</td>
				</tr>
			</table> --%>
		</f:view>
	</body>
</html>