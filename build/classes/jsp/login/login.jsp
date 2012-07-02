<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/login.css"/>
	<link rel="shortcut icon" href="../../css/images/favicon.ico" />
	<script type="text/javascript" language="JavaScript1.2" src="../../js/general.js"></script>
	<script type="text/javascript" language="JavaScript1.2" src="../../js/login.js"></script>
	
	<title>LOTRADING :: SOFTLOT - LOGIN</title>
</head>
<body onload="document.getElementById('formLogin:user').focus();resize()" onmousemove="resize();">
	<rich:hotKey id="hotKeylogin" rendered="#{!loginControl.passExpired }" key="return" handler="auth();"/>
	<rich:hotKey id="hotKeySavepwd" rendered="#{loginControl.passExpired }" key="return" handler="changePassword();"/>
	<t:saveState value="#{loginControl.passExpired }"/>
	<h:form id="formLogin" >
		<div class="formLoginback" id="divLoginback"></div>
		<div class="formLogin" id="divLogin">
			<table id="tableLogin">
				<tbody>
					<tr>
						<td colspan="3">
							<rich:messages  warnLabelClass="labelerrorStyle" rendered="#{loginControl.passExpired }"></rich:messages>
						</td>
					</tr>
					<tr>
						<td align="right"  width="113px">
							<h:outputText value="USERNAME"/>
						</td>
						<td  width="120px" >
							<h:inputText styleClass="inputText" id="user" maxlength="128" required="true" value="#{loginControl.employee.login}" 
							onkeyup="return userChanged('formLogin:user', 'linfoUser');" tabindex="1" />
						</td>
						<td align="left">
							<span id="linfoUser"><br></span>
						</td>
					</tr>
					<tr>
						<td align="right"  width="113px">
							<h:outputText value="PASSWORD"/>
						</td>
						<td  width="120px">
							<h:inputSecret styleClass="inputText" id="pass" maxlength="128" required="true" value="#{loginControl.employee.password}" 
							autocomplete="off" tabindex="2"/>
						</td>
						<td align="left">
							<span id="linfoUser"><br></span>
						</td>
					</tr>
					<a4j:region rendered="#{loginControl.passExpired }">
						<tr>
							<td align="right"  width="113px">
								<h:outputText value="NEW PWD"/>
							</td>
							<td>
								<h:inputSecret value="#{loginControl.newPass }" maxlength="128" id="newPass" autocomplete="off" 
									onkeyup="return newPasswordChanged('formLogin:newPass','linfoNewPass');" tabindex="3" styleClass="inputText"/>
							</td>
							<td align="left">
								<span id="linfoNewPass"><br></span>
							</td>
						</tr>
						<tr>
							<td align="right" width="113px">
								<h:outputText value="VERIFY NEW PWD"/>
							</td>
							<td>
								<h:inputSecret maxlength="128" value="" id="newPassAgain" autocomplete="off" onkeyup="labelSpan('linfoNewPass_confirm',0,' ');" 
									styleClass="inputText" tabindex="4"/>
							</td>
							<td align="left">
								<span id="linfoNewPass_confirm"><br></span>	
							</td>
						</tr>
						
					</a4j:region>
					<tr><td><br></td></tr>
				</tbody>
			</table>									
			<div style="text-align:center;">
				<a4j:commandButton value="LOGIN" styleClass="buttonsLogin"  onclick="javascript:auth();" rendered="#{!loginControl.passExpired }" ajaxSingle="true" tabindex="5" onblur="#{rich:element('user')}.focus();"/> 
				<a4j:commandButton value="SAVE" styleClass="buttonsLogin" type="button" rendered="#{loginControl.passExpired }" ajaxSingle="true"
					onclick="javascript:changePassword();" tabindex="6" onblur="#{rich:element('user')}.focus();"/>
				<a4j:commandButton value="CLEAR" onfocus="blur();" styleClass="buttonsLogin"
					onclick="clearFields();" ajaxSingle="true" immediate="true"/>
				<a4j:commandLink action="#{loginControl.authenticateAction}" value="" id="linkAuth" reRender="formLogin" />
				<a4j:commandLink action="#{loginControl.changePasswordAction}" value="" id="linkChangPass" reRender="formLogin" rendered="#{loginControl.passExpired }"/>
				<rich:messages  warnLabelClass="labelerrorStyle" rendered="#{!loginControl.passExpired }"></rich:messages>
			</div>		
		</div>
	</h:form>					
</body>
</html>
</f:view>