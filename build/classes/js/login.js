/**
 * 
 */
defaultStatus = 'LOTRADING :: SOFTLOT';
var sMessage = document.location.href;
var qwe = document.location.href;

/*document.onkeydown = checkKeyCode;

function checkKeyCode(ev){
	var e;
	if(document.all){
		e = window.event;
	}else{
		e = ev;
	}
	if(e.keyCode == 13){
		auth();
	}
}
*/

//if (parent.opener && !parent.opener.closed) {
//	if (parent.opener.parent && parent.opener.parent.opener
//			&& !parent.opener.parent.opener.closed) {
//		parent.opener.parent.opener.top.location.href = location.href;
//		parent.opener.parent.opener.top.focus();
//		parent.opener.top.self.close();
//	} else {
//		parent.opener.top.location.href = location.href;
//		parent.opener.top.focus();
//	}
//	top.self.close();
//} else {
//	if (window != top) {
//		top.location.href = location.href;
//	}
//}

document.getElementById('divLoginback').style.height = document.getElementById('divLogin').offsetHeight-30 + 'px';
function resize(){
	document.getElementById('divLoginback').style.height = document.getElementById('divLogin').offsetHeight-30 + 'px';
}

function loadRoot() {
	var form = document.forms[0];
	var winNew = window
			.open(
					'root.jsp',
					//'SOFTLOT_ROOT',
					'_self',
					'left=0,top=0,screenX=0,screenY=0,type=fullscreen,alwaysRaised=yes,directories=no,hotkeys=no,menubar=no,resizable=no,location=no,toolbar=no,status=no,scrollbars=no,directories=no,status=yes,height='
							+ (screen.availHeight - 50)
							+ ',width='
							+ (screen.availWidth - 10)
							+ ',dependent=true,titlebar=false,z-lock=true,channelmode=true,innerWidth='
							+ screen.width + ',innerHeight=' + screen.height);
	winNew.focus();
}

function auth() {
	if (validateForm()) {
		var objetoLink = getElementById("formLogin:linkAuth");
		if (objetoLink)
			triggerEvent(objetoLink, "click");
		else
			alert('The command does not exist!');
	}
}

function logout(){	
	var objetoLink = getElementById("formLogout:linkLogout");
	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist!');
}

function cancel() {
	var objetoLink = getElementById("formLogin:linkCancel");

	if (objetoLink)
		triggerEvent(objetoLink, "click");
	else
		alert('The command does not exist!');
}

function validateForm() {
	var username = getElementById("formLogin:user");
	var password = getElementById("formLogin:pass");

	if (username.value == '') {
		alert('Username is required.');
		return false;
	}

	if (password.value =='') {
		alert('Password is required.');
		return false;
	}

	return true;
}

function newPasswordChanged(input, infoLabel) {
	var strength = document.getElementById(infoLabel);
	var strongRegex = new RegExp("^(?=.{7,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	var enoughRegex = new RegExp("(?=.{5,}).*", "g");
	var pwd = document.getElementById(input);
	if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = 'More Characters';
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green">Strong!</span>';
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange">Medium!</span>';
	} else {
		strength.innerHTML = '<span style="color:red">Weak!</span>';
	}
}

function labelSpan(infoLabel,level,msg) {
	var label = document.getElementById(infoLabel);
	if(level == 0){
		label.innerHTML = '<span'+msg+'</span>';
	}else if(level == 1){
		label.innerHTML = '<span style="color:red">'+msg+'</span>';
	}
	
}

function userChanged(input, infoLabel) {
	var strength = document.getElementById(infoLabel);
	var user = document.getElementById(input);
	var userRegex = new RegExp("^[a-zA-Z]{2,}$");   //Esto testea si hay dos o mas caracteres (solo letras) mayusculas o minusculas.
	if (user.value.length==1) {
		strength.innerHTML = 'More characters';
	} else if (false == userRegex.test(user.value) && user.value.length > 0) {
		strength.innerHTML = 'Only letters';
	} else {
		strength.innerHTML = '';
	}
}

function tiene_numeros(texto){
	numeros="0123456789";
	for(i=0; i<texto.length; i++){
		if (numeros.indexOf(texto.charAt(i),0)!=-1){
			return true;
		}
	}
	return false;
}

function tiene_letras(texto){
	var letras="ABCDEFGHYJKLMNÑOPQRSTUVWXYZabcdefghyjklmnñopqrstuvwxyz";
   for(i=0; i<texto.length; i++){
      if (letras.indexOf(texto.charAt(i),0)!=-1){
         return true;
      }
   }
   return false;
} 


function changePassword(){
	if(validateForm()){
		var pass = getElementById("formLogin:newPass").value;
		var pass_confirm = getElementById("formLogin:newPassAgain").value;
		if(pass.length >= 5 && (tiene_numeros(pass) && tiene_letras(pass))){
			if(pass == pass_confirm){
				var objetoLink = getElementById("formLogin:linkChangPass");
				if (objetoLink)
					triggerEvent(objetoLink, "click");
				else
					alert('The command does not exist!');
			}else{
				labelSpan('linfoNewPass_confirm',1,'Passwords don\'t match.');
			}
		}else{
			alert('invalid format \n \nPassword must have 5 alphanumerical characters minimum.');
		}
	}
}

function clearFields(){
	getElementById("formLogin:user").value = "";
	getElementById("formLogin:pass").value = "";
	if(getElementById("formLogin:newPass") != null)getElementById("formLogin:newPass").value = "";
	if(getElementById("formLogin:newPassAgain") != null)getElementById("formLogin:newPassAgain").value = "";
}

