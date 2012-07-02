function searchRoles(){
	var objetoLink = getElementById("formRoles:linkSearch");
	if (objetoLink){
		triggerEvent(objetoLink, "click");
	}else{
		alert('The command does not exist1!');
	}
}

function nuevo(){
	var objetoLink = getElementById("formRoles:linkNew");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist2!');
}

function guardar(){
	var objetoLink = getElementById("formRole:linksave");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist3!');
}

function disable(){
	var objetoLink = getElementById("formRole:linkDisable");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist5!');
}
