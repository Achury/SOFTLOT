/**
 * 
 */

function searchClients(){
	var objetoLink = getElementById("formClients:linkSearch");
	if (objetoLink){
		triggerEvent(objetoLink, "click");		
	}else{
		alert('The command does not exist!');
	}
}

function searchSuppliers(){
	var objetoLink = getElementById("formSuppliers:linkSearch");
	if (objetoLink){
		triggerEvent(objetoLink, "click");	
	}else{
		alert('The command does not exist!');
	}
}

function validarChars(e) { 
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
    patron =/[A-Za-z\t]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
} 



function openPopupEdit(fuente, destino, variableAux){
	if (destino && fuente){
			destino.value = fuente.value;
			variableAux.value = fuente.id;
	}else{
		alert('The command does not exist!');
	}
}

function savePopupEdit(fuente, IdDestino){
	if(fuente && IdDestino){
		var destino = getElementById(IdDestino.value);
		destino.value = fuente.value;
	}else{
		alert('The command does not exist!');
	}
}

function roundNumber(rnum, rlength) { // Arguments: number to round, number of decimal places
	  var newnumber = Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
	  return parseFloat(newnumber); // Output the result to the form field (change for your purposes)
}

function markupToMargin(markup){
	var margin = 1-(100/(parseFloat(markup) + 100));
	return margin;
}

function marginToMarkup(margin){
	var markup = roundNumber(((1/(1-parseFloat(margin)))-1)*100,2);
	return markup;
}

function convertToPercentege(number){
	var percentage = roundNumber(number*100,2);
	return percentage+'%';
}




