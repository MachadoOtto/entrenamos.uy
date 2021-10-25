var botonActualID = 'nav-perfil';
var cuadroInfoActualID = 'user-consultaPerfil';

function cambioNavegador( nextConsultaID,  nextBotonID ) {
	cambiarPerfil( nextConsultaID );
	colorearBoton( nextBotonID );
}

function cambiarPerfil( nextID ) {
  document.getElementById( cuadroInfoActualID ).style.display = 'none';
  document.getElementById( nextID ).style.display = 'block';

  cuadroInfoActualID = nextID;
}

function colorearBoton( nextID ) {
  document.getElementById( botonActualID ).classList.remove('active');
  document.getElementById( nextID ).classList.add('active');

  botonActualID = nextID;
}

function modif() {
	if ($("#nomm").val().trim() == "" || $("#ape").val().trim() == "" || $("#nac").val() == "" || $("#desc").val().trim() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-modif");
		return false;
	}
	if ($("#pas1").val() != $("#pas2").val()){
		errorMsgForm("Las contraseñas no coinciden.", "formulario-modif");
		return false;
	}
	if ((new Date($("#nac").val())) > (new Date())){
		errorMsgForm("No es posible nacer en el futuro.", "formulario-modif");
		return false;
	}
	else {
		$("#nomm").val($("#nomm").val().trim());
		$("#ape").val($("#ape").val().trim());
		$("#desc").val($("#desc").val().trim());
		return true;
	}	
}
