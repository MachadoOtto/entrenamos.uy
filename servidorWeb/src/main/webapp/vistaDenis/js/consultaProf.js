function cambioNavegador( nextConsulta, nextBoton ) {
	cambiarPerfil( nextConsulta );
	colorearBoton( nextBoton );
}

function cambiarPerfil( next ) {
  var x1 = document.getElementById( "user-consultaPerfil" );
  var x2 = document.getElementById( "user-consultaClases" );
  var x3 = document.getElementById( "user-consultaActividadesDeportivas" );
  var x4 = document.getElementById( "user-consultaCuponeras" );
 var x5 = document.getElementById( "user-consultaAD" );
 var x6 = document.getElementById( "user-consultaADI" );
  var y = document.getElementById( next );
  
  x1.style.display = "none";
  x2.style.display = "none";
  x3.style.display = "none";
  x4.style.display = "none";
  x5.style.display = "none";
  x6.style.display = "none";
  y.style.display = "block";
}

function colorearBoton( next ) {
  var x1 = document.getElementById( "user-nav1" );
  var x2 = document.getElementById( "user-nav2" );
  var x3 = document.getElementById( "user-nav3" );
  var x4 = document.getElementById( "user-nav4" );
  var x5 = document.getElementById( "user-nav5" );
  var x6 = document.getElementById( "user-nav6" );
  var y = document.getElementById( next );
  
  x1.classList.remove('active');
  x2.classList.remove('active');
  x3.classList.remove('active');
  x4.classList.remove('active');
  x5.classList.remove('active');
  x6.classList.remove('active');
  y.classList.add('active');
}
