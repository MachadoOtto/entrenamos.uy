function cambioNavegador( nextConsulta, nextBoton ) {
	cambiarPerfil( nextConsulta );
	colorearBoton( nextBoton );
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
  var x1 = document.getElementById( "user-nav1" );
  var x2 = document.getElementById( "user-nav2" );
  var x3 = document.getElementById( "user-nav3" );
  var x4 = document.getElementById( "user-nav4" );
  var y = document.getElementById( next );
  
  x1.classList.remove('active');
  x2.classList.remove('active');
  x3.classList.remove('active');
  x4.classList.remove('active');
  y.classList.add('active');
}
function swappy(){
	if($("#btnSeguimiento").html()=="Dejar de seguir"){
		$("#btnSeguimiento").html("Seguir");
	}else
	 $("#btnSeguimiento").html("Dejar de seguir");
}

$(window).load(function() {
    document.getElementById('btnSeguimiento').onclick = swappy;
});
