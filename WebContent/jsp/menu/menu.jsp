<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot/jquery-ui-1.8.11.custom.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
	<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="../../js/jquery-ui-1.8.11.custom.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#accordion").accordion({autoHeight: false,
	            navigation: true});
		});
	</script>
</head>
<body>
<div id="accordion" class="menuacordion" >
    <h3><a style="color: white;" href="#">Security</a></h3>
     <div>

    	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/security/employees.jsp" style="text-decoration:none;" target="datos">Employees</a></li></ul>
    	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/security/options.jsp" style="text-decoration:none;" target="datos">Options</a></li></ul>
    	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/security/resources.jsp" style="text-decoration:none;" target="datos">Resources</a></li></ul>
    	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/security/roles.jsp" style="text-decoration:none;" target="datos">Roles</a></li></ul>

     </div>
    <h3><a href="#">Business Partners</a></h3>
     <div>
     	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/businessPartners/clients.jsp" style="text-decoration:none;" target="datos">Clients</a></li></ul>
     	<ul><li><a href="http://localhost:8080/SOFTLOT/faces/jsp/businessPartners/suppliers.jsp" style="text-decoration:none;" target="datos">Suppliers</a></li></ul>
     </div>
</div>
</body>
</html>