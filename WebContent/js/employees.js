/**
 * 
 */

function buscar(){
	var objetoLink = getElementById("formEmployees:linkSearch");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist!');
}


