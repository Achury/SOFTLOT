<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
</head>
<body>
<br>
<center><IMG id="ocultar_mostrar" SRC="../../css/images/hide_panel.bmp" WIDTH=15 HEIGHT=15 ALT="Ocultar menú"></center>
</body>
<script>    
    jQuery(function() {
        jQuery("#ocultar_mostrar").click(function() {
           var s=jQuery(window.parent.frames[1].frameElement.parentNode).attr('cols').split(",")[0];
           
           s=s=="210"?"0":"210";
           jQuery(window.parent.frames[1].frameElement.parentNode).attr('cols',s+",22,*");
           if (s == "0") {
                this.src="../../css/images/show_panel.bmp";
                this.alt="Mostrar menú";
           } else {
                this.src="../../css/images/hide_panel.bmp";
                this.alt="Ocultar menú";
           }
        });
        <%
        if(null!=request.getParameter("url") && "null"!=request.getParameter("url")) {
        %>
        	jQuery("img[id*='ocultar_mostrar']").trigger("click");
        <%
        }
        %>
    });
</script>
</html>




