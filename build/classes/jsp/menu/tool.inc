<%
	session.removeAttribute("RECURSO_DESPLEGADO");
	session.setAttribute("RECURSO_DESPLEGADO", request.getServletPath());
%>
<script language="javascript" type="text/javascript">
if(top.toolbar.location.href == "about:blank"){
	top.toolbar.location.href="/SOFTLOT/faces/jsp/menu/toolbar.jsp";
}else{
	<%if (request.getServletPath().equals("/jsp/menu/vacio.jsp")) {%>
	top.toolbar.location.href="toolbar.jsp";	
	<%} else {%>
		top.toolbar.location.reload();
	<%}%>
}
</script>