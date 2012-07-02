<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/softlot.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="../../../css/clientOrder.css"/>
	<script type="text/javascript" src="../../../js/clientOrder.js"></script>
	<title>LOTRADING :: CLIENT ORDERS</title>
</head>
<body>
<f:view>
	<h:form>
		<rich:panel headerClass="styleGeneralPanel"	bodyClass="styleGeneralPanel" id="panelDetails">
			<table border="0" cellspacing="2" width="99%">
				<tr>
					<td nowrap="nowrap" align="left" colspan="3">
						<table border="0" cellspacing="1">
							<tr>
								<td nowrap="nowrap" align="left" style="padding:1px 11px;">
									<h:outputLabel value="Order No:"/>																
									<h:outputLabel value="#{clienOrderControl.clientOrder.clientOrderNo}"/>	
								</td>
								<td  nowrap="nowrap" align="right" style="padding:1px 11px;">
									Order Date: 
									<h:outputLabel value="#{clienOrderControl.clientOrder.createdDate }"/>
								</td>							
								<td nowrap="nowrap" align="right" style="padding:1px 11px;">
									Status:
									<h:outputLabel  value="#{clienOrderControl.clientOrder.status.value1 }" style="width:129px"/>
								</td>
								<td nowrap="nowrap" align="left" style="padding:1px 11px;">
									Biz unit:
									<h:outputLabel  value="#{clienOrderControl.clientOrder.region.value1 }" />
								</td>
							</tr>
						</table>
						<hr align="right" width="101%" style="color:#DADADA" noshade>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						<table cellpadding="0" cellspacing="1" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right" >
									<h:outputLabel value="Supplier:" />		
								</td>
								<td nowrap="nowrap">
									<h:outputLabel value="#{clienOrderControl.clientOrder.supplier.name}" style="color: #0000FF"/>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea rows="3" value="#{clienOrderControl.clientOrder.supplier.address.address }
#{clienOrderControl.clientOrder.supplier.address.city.name }, #{clienOrderControl.clientOrder.supplier.address.city.stateProvince.value1 } #{clienOrderControl.clientOrder.supplier.address.postalCode }
#{clienOrderControl.clientOrder.supplier.address.city.country.value1 }"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td height="4px"/>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Pickup From:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel value="#{clienOrderControl.clientOrder.pickupFrom.shipPickUpId}" style="width:287px" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea value="#{clienOrderControl.clientOrder.pickupFrom.address.address }"></h:inputTextarea>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table cellpadding="0" cellspacing="0" class="styleInput">
							<tr>
								<td nowrap="nowrap" align="right">
									Client:
								</td>
								<td nowrap="nowrap"> 
									<h:outputLabel value="#{clienOrderControl.clientOrder.client.name}" style="color: #FF0000"/>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea  value="#{clienOrderControl.clientOrder.client.address.address }
#{clienOrderControl.clientOrder.client.address.city.name }, #{clienOrderControl.clientOrder.client.address.city.stateProvince.value1 } #{clienOrderControl.clientOrder.client.address.postalCode }
#{clienOrderControl.clientOrder.client.address.city.country.value1 }"></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td height="4px"/>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Consignee:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel id="consigneeComboBox" value="#{clienOrderControl.clientOrder.shipTo.shipPickUpId}" style="width:287px"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td nowrap="nowrap">
									<h:inputTextarea value="#{clienOrderControl.clientOrder.shipTo.address.address }"></h:inputTextarea>
								</td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td nowrap="nowrap" align="right" valign="top">
									PO Number:
								</td>
								<td nowrap="nowrap">
									<h:inputTextarea style="width:174px" value="#{clienOrderControl.clientOrder.numberPO }" ></h:inputTextarea>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Destination:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel style="width:179px" value="#{clienOrderControl.clientOrder.destinationCity.name }" ></h:outputLabel>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Incoterm:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel value="#{clienOrderControl.clientOrder.incoterm.value1}" style="width:181px" />
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									City:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel style="width:179px" value="#{clienOrderControl.clientOrder.incotermCity.name }"></h:outputLabel>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" align="right">
									Sales Rep:
								</td>
								<td nowrap="nowrap">
									<h:outputLabel value="#{clienOrderControl.clientOrder.salesRep.firstName} #{clienOrderControl.clientOrder.salesRep.lastName}" style="width:181px"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</rich:panel>
	</h:form>
</f:view>
</body>
</html>