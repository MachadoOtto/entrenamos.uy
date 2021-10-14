var botonActualID = 'nav-perfil';
var cuadroInfoActualID = 'user-consultaPerfil';

function cambioNavegador( nextConsultaID, nextBotonID ) {
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