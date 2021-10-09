var botonActualID = "nav-perfil";
var cuadroInfoActualID = "";

function cambioNavegador( nextConsultaID, nextBotonID ) {
	cambiarPerfil( nextConsultaID );
	colorearBoton( nextBotonID );
}

function cambiarPerfil( nextID ) {
  var x = document.getElementById( cuadroInfoActual );
  var y = document.getElementById( nextID );
  
  x.style.display = "none";
  y.style.display = "block";
  
  cuadroInfoActualID = nextID;
}

function colorearBoton( nextID ) {
  var x = document.getElementById( botonActual );
  var y = document.getElementById( nextID );

  x.classList.remove('active');
  y.classList.add('active');

  botonActualID = nextID;
}