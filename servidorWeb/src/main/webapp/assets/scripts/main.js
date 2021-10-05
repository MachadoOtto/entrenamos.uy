function swapIniRegModals(){
    $('.modal').css('overflow-y', 'auto');
    bootstrap.Modal.getOrCreateInstance(document.getElementById('inicioSesionModal')).toggle();
    bootstrap.Modal.getOrCreateInstance(document.getElementById('registroModal')).toggle();
}
function registroSocio(){
    $("#institDiv").hide();
    $("#descDiv").hide();
    $("#bioDiv").hide();
    $("#webDiv").hide();
}
function registroProfe(){
    $("#institDiv").show();
    $("#descDiv").show();
    $("#bioDiv").show();
    $("#webDiv").show();
}


function iniciarSesion(){
	xhttp = new XMLHttpRequest();
	xhttp.open("POST",$("#formulario-sesion").attr("data-root")+"/api/login")
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("nick-login="+$("#user").val()+"&pass-login="+$("#pass").val());
	xhttp.onload = function(){
		if(xhttp.responseText.trim()=="OK"){
			window.location.reload();
		} else if(xhttp.responseText.trim()=="FAIL_PWD" || xhttp.responseText.trim()=="FAIL_USR"){
			if($('#errorIni').length==0){
				$("#formulario-sesion").append( "<div class='alert alert-danger' role='alert' id='errorIni' style='margin-top:1em;' form-floating mb-3> La combinación usuario/contraseña es inválida. </div>" );
				$('#errorIni').animate({'margin-left': '+=3px', 'margin-right': '-=3px'},"fast");
				$('#errorIni').animate({'margin-right': '+=3px', 'margin-left': '-=3px'},"fast");

			}
			else{
				$('#errorIni').animate({'margin-left': '+=3px', 'margin-right': '-=3px'},"fast");
				$('#errorIni').animate({'margin-right': '+=3px', 'margin-left': '-=3px'},"fast");
			}
		}
	}
}








$(window).on('load', function() {
	try{
	    document.getElementById('regLink').onclick = swapIniRegModals;
	    document.getElementById('iniLink').onclick = swapIniRegModals;
	    document.getElementById('radioSocio').addEventListener('input',registroSocio);
	    document.getElementById('radioProfe').addEventListener('input',registroProfe);
	    registroSocio();		
	} catch (error){
		pass
	}
});