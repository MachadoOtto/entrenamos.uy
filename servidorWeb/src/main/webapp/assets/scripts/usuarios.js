/* ESTRELLITAS */


/*porcentajeEstrellal = (estrellas*16) + "%";
  document.getElementById('estrellitas-interior' ).style.width = porcentajeEstrellal;
*/
function cargarEstrellitas() {
  estrellas = 0;
  estrellas = (document.getElementById('usr-promedio').innerHTML);
  porcentajeEstrellal = (estrellas*2.33) + "rem";
  document.getElementById('estrellitas-interior').style.width = porcentajeEstrellal;
}
/*
function estrellato( idEstrellal ) {
  var colorEstrellas = '#eee';
  var estrellismo = 50;
console.log('-webkit-linear-gradient(right,' + colorEstrellas + ' ' + estrellismo.toString +'%, #ffe234 100%)');
  document.getElementById( idEstrellal ).style.background = '-webkit-linear-gradient(right,' + colorEstrellas + ' ' + estrellismo.toString +'%, #ffe234 100%)';
}*/


/* BOTONES */


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


/* VERIFICACION DE DATOS */

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
