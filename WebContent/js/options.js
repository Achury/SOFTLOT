
function buscar(){
	var objetoLink = getElementById("formOptions:linkSearch");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist!');
}


