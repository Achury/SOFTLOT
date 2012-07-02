function buscar(){
	var objetoLink = getElementById("formResources:linkSearch");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist!');
}