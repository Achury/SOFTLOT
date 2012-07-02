/**
 * 
 */
function getElementById(id){
    var obj = null;
    if (document.getElementById) {
        obj = document.getElementById(id);
    } else if (document.all) {
        obj = document.all[id];
    }
    return obj;
}

function triggerEvent(control, event){
    var nav = navigator.appName;
    if (nav.indexOf("Internet Explorer") > -1) {
        control.click();
    } else {
        var clickevent = document.createEvent("MouseEvents");
        clickevent.initEvent(event, true, true);
        control.dispatchEvent(clickevent);
    }
}

function validateNumbers(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if ((charCode >=48 && charCode <= 57) || (charCode >=96 && charCode <= 105) || 
		   charCode == 8 || charCode == 9 || charCode == 46 || charCode == 32  || (charCode >= 37 && charCode <= 40))
      return true;
   return false;
}

function validateNumbers2(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if ((charCode >=47 && charCode <= 57) || (charCode >=96 && charCode <= 105) || 
		   charCode == 8 || charCode == 9 || charCode == 46 ||(charCode >= 37 && charCode <= 40))
      return true;
   return false;
}

function autoCompleteDate2(componentName){		
	var fecha = document.getElementById(componentName).value;
	var dateSplit = fecha.split("/");
	if(dateSplit.length >= 2 && dateSplit[1].length > 1 && dateSplit.length < 3){
		var hoy = new Date();	
		fecha = fecha +"/"+hoy.getFullYear();
		 document.getElementById(componentName).value = fecha;
	}else if(dateSplit.length >= 3 && dateSplit[2].length < 1){
		var hoy = new Date();	
		fecha = fecha +hoy.getFullYear();
		 document.getElementById(componentName).value = fecha;
	}
	if(validarFecha(dateSplit[0], dateSplit[1], dateSplit[2]) >= 1){	
		alert("Invalid Format.\n mm/dd/yyyy");
	}		
}

//*********************************************************************************************** 
// validarFecha(dia,mes, año) 
// 
// Valida que el día y el mes introducidos sean correctos. Además valida que el año introducido 
// sea o no bisiesto 
// 
//*********************************************************************************************** 
function validarFecha(mes,dia,anio){ 
	var elMes = parseInt(mes);
	if(elMes>12) 
		return 1; 
	// MES FEBRERO
	if(elMes == 2){ 
		if(esBisiesto(anio)){ 
			if(parseInt(dia) > 29){ 
				return 1; 
			}else 
				return 0; 
			} 
		else{ 
			if(parseInt(dia) > 28){ 
				return 1; 
			}else 
				return 0; 
		} 
	} 
	// RESTO DE MESES

	if(elMes== 4 || elMes==6 || elMes==9 || elMes==11){ 
		if(parseInt(dia) > 30){ 
			return 1; 
		} 
	}else if(parseInt(dia) > 31){
		return 1; 
	}
	return 0; 

} 
// *****************************************************************************************
// esBisiesto(anio)
// 
// Determina si el año pasado com parámetro es o no bisiesto
// *****************************************************************************************
function esBisiesto(anio){ 
	var BISIESTO; 
	if(parseInt(anio)%4==0){ 
		if(parseInt(anio)%100==0){ 
			if(parseInt(anio)%400==0){ 
				BISIESTO=true;	
			}else{ 
				BISIESTO=false; 
			} 
		}else{ 
			BISIESTO=true; 
		} 
	}else 
		BISIESTO=false; 
	return BISIESTO;	
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