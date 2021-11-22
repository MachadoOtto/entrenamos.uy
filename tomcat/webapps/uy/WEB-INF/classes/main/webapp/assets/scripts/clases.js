function cambioNavegador( next ) {
	cambiarPerfil( next );
	colorearBoton( next );
}

function cambiarPerfil( next ) {
  var x1 = document.getElementById( "user-consultaInscriptos" );
  var y = document.getElementById( next );
  
  x1.style.display = "none";
  y.style.display = "block";
}

function colorearBoton( next ) {
  var x1 = document.getElementById( "user-consultaInscriptos" );
  var y = document.getElementById( next );
  
  y.focus();
  y.click();
}

function registroGen(){
  $("#cupdiv").hide();
}
function registroCup(){
  $("#cupdiv").show();
}

$(window).on('load',function() {
	try{
	  	document.getElementById('radioGen').addEventListener('input', registroGen);
  		document.getElementById('radioCup').addEventListener('input', registroCup);
  		registroGen();	
	} catch (error) {
		console.error(error)	
	}
	try{
		if(parseInt($("#Valoraciones").attr("data-valoracion"))>0)
			$("#rating3-"+$("#Valoraciones").attr("data-valoracion")).prop("checked", true);
	} catch(error){
		console.error(error)
	}
});