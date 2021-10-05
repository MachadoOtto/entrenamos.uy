function cambioNavegador( next ) {
	cambiarPerfil( next );
	colorearBoton( next );
}

function cambiarPerfil( next ) {
  var x1 = document.getElementById( "user-consultaPerfil" );
  var x2 = document.getElementById( "user-consultaClases" );
  var x3 = document.getElementById( "user-consultaActividadesDeportivas" );
  var x4 = document.getElementById( "user-consultaCuponeras" );
  var y = document.getElementById( next );
  
  x1.style.display = "none";
  x2.style.display = "none";
  x3.style.display = "none";
  x4.style.display = "none";
  y.style.display = "block";
}

function colorearBoton( next ) {
  var x1 = document.getElementById( "user-consultaPerfil" );
  var x2 = document.getElementById( "user-consultaClases" );
  var x3 = document.getElementById( "user-consultaActividadesDeportivas" );
  var x4 = document.getElementById( "user-consultaCuponeras" );
  var y = document.getElementById( next );
  
  y.focus();
  y.click();
}
