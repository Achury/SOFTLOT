var alertTimerId;
jQuery('document').ready(
	function(){
		alertTimerId = setTimeout("DisplaySessionTimeout()",1620000);   //se activara el popup cuando no haya actividad en 27 minutos, es decir, no haya un refresco de alguna pagina.
	}
);

function DisplaySessionTimeout(){
	var newWindow = window.open("../../jsp/login/timeout.jsp","Timeout","width=350,height=200,menubar=no,hotkeys=no,resizable=no,Location=no,toolbar=no,status=no,scrollbars=no,directories=no,status=yes,titlebar=no");
	newWindow.focus();
	parent.opener.location.close();
}