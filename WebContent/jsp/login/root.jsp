<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@page import="org.w3c.tools.codec.Base64Decoder"%>
<%@page import="org.w3c.tools.codec.Base64FormatException" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15" />
	<%
		String urlCodificada = request.getParameter("url");
		String newUrl = "../menu/vacio.jsp";
		String division = "../menu/division.jsp";
		String title = "LOTRADING :: SOFTLOT V1.1.0";
		if(null!=urlCodificada) {
			Base64Decoder decoder = new Base64Decoder(urlCodificada);
			try {
				newUrl = "../.."+decoder.processString();
				title = request.getParameter("tit");
				System.out.println(newUrl);
			} catch (Base64FormatException e) {
				e.printStackTrace();
			}
			division = "../menu/division.jsp?url="+urlCodificada;
		}
	%>

	<title><%=title%></title>
	<link rel="shortcut icon" href="../../css/images/favicon.ico" />
	<script type="text/javascript" src="../../js/general.js"></script>
	<script type="text/javascript">
// 		setBunload(true);
// 		function unload() {
// 			if (parent.opener && !parent.opener.closed) {
//				parent.opener.location.href = "logout.jsp";
// 			}
// 		}
// 		function setBunload(on) {
// 			window.onbeforeunload = (on) ? unload : null;
// 		}
// 		function cargo(e) {
// 			window.focus();
// 		}
	</script>
</head>
<frameset frameborder="no" framespacing="0" border="0" cols="*" rows="*">
	<%--  <frame name="toolbar" marginwidth="0" marginheight="0" src="" noresize="noresize" scrolling="no">--%>
	<frameset cols="210,22,*" frameborder="no" framespacing="0" border="0">
		<frame marginwidth="no" marginheight="0" src="../menu/menu.menu" id="framemenu" name="menu" frameborder="0" noresize="noresize">
		<frame marginwidth="no" marginheight="0" src="<%=division%>" id="framediv" name="division" frameborder="0" noresize="noresize">
		<frame marginwidth="no" marginheight="0" src="<%=newUrl%>" name="datos" frameborder="0" noresize="noresize">
	</frameset>
</frameset>
</html>