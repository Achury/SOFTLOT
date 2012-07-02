<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<html>	
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="stylesheet" type="text/css" media="screen" href="../../css/softlot.css"/>
			<link rel="shortcut icon" href="../../css/images/favicon.ico" />
			<script type="text/javascript">

function contador(repite, time){
	var form=document.getElementById('form');
	if(repite){
		tiempo = time;
	}
	if(tiempo >= 0){
		form.second.value=tiempo;
		setTimeout("contador()", 60000);   //se ejecutara recursivamente cada 60 segundos, es decir, descontara uno a tiempo cada 60 segundos
		tiempo = tiempo - 1;
	}
}
 
</script>
	<title>LOTRADING :: SOFTLOT - TIMEOUT</title>
		</head>
		<body bgcolor="#dfdfdf" onload="contador('true',3);">   <!--  el contador que decrece iniciara desde 3 -->
			<form id="form" name="form">	
				<table align="center" class="styleTiemout" cellspacing="20">
					<tr align="center" valign="bottom" height="35">
						<td >
							<h:outputLabel style="font-size:12px;">
								SOFTLOT has been inactive for 27 minutes
							</h:outputLabel>
						</td>
					</tr>
					<tr align="center" valign="bottom" height="25">
						<td>
							<div>	
								<h:outputLabel style="font-size:12px;">
									The session will close aprox. in 
								</h:outputLabel>		             
								<input type="text" class="inputTimeout" name="second" readonly>
								<h:outputLabel>
									minutes if no activity is detected
								</h:outputLabel>              
							</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="bottom" height="40">
							<h:commandButton onclick="window.close();" value="close this window" styleClass="buttomTiemout"/>
						</td>
					</tr>
				</table>   
			</form>	
		</body>
	</html>
</f:view>