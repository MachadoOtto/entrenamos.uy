errores = {"e1m":"La combinación Usuario/Contraseña no es correcta.","e1t":"Error al Iniciar Sesión","e1c":"rgb(255 0 0 / 60%)",
		   "e2m":"Ya existe un usuario con el nickname o correo seleccionado.","e2t":"Error al Registrar Usuario","e2c":"rgb(255 0 0 / 60%)",
			"e3m":"Se ha registrado su usuario de manera exitosa.","e3t":"Registrar Usuario","e3c":"rgb(86 255 0 / 60%)",
			"e4m":"El nombre de la actividad deportiva a crear ya existe en el sistema","e4t":"Error al Registrar Actividad Deportiva","e4c":"rgb(255 0 0 / 60%)",
			"e5m":"Se ha registrado la actividad deportiva exitosamente.","e5t":"Alta de Actividad Deportiva","e5c":"rgb(86 255 0 / 60%)"
			};

function shake(element){
	$('#'+element).animate({'margin-left': '+=3px', 'margin-right': '-=3px'},"fast");
	$('#'+element).animate({'margin-right': '+=3px', 'margin-left': '-=3px'},"fast");
}
function errorMsgForm(msg,parent){
	if($('#errorMsg'+parent).length==0){
		$("#"+parent).append( "<div class='alert alert-danger' role='alert' id='errorMsg"+parent +"' style='margin-top:1em;' form-floating mb-3>"+msg+"</div>" );
		shake("errorMsg"+parent);

	}
	else{
		$("#errorMsg"+parent).text(msg)
		shake("errorMsg"+parent);
	}
}

tt=0
function crearTostada(code){
	title = errores["e"+code+"t"]
	msg = errores["e"+code+"m"]
	tt++;
	toast = `<div id='e${tt}' class='toast' role='alert' data-bs-autohide='false' aria-live='assertive' aria-atomic='true' style="background-color:${errores["e"+code+"c"]};">
				  <div class='toast-header'>
				    <strong class="me-auto">${errores["e"+code+"t"]}</strong>
				    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				  </div>
				  <div class="toast-body">
				    ${errores["e"+code+"m"]}
				  </div>
				</div>`;
	$("#tostadas").append(toast);
	var bt = new bootstrap.Toast($("#e"+tt));
	bt.show();
}
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

function  registrarse(){
	/*Verificar Formulario*/
	if($("#nickk").val() == "" || $("#emaill").val() == "" || $("#pas1").val() == "" || $("#pas2").val() == "" ||
	 $("#nomm").val() == "" || $("#ape").val() == "" || $("#nac").val() == "" || ($("#radioProfe").is(":checked") && $("#desc").val()=="")){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.","formulario-registro");
		return false;
	}
	if($("#pas1").val() != $("#pas2").val()){
		errorMsgForm("Las contraseñas no coinciden.","formulario-registro");
		return false;
	}
	else
		return true;
}

function altaAD(){
	/*Verificar Formulario*/
	if($("#nombreAD").val() == "" || $("#descAD").val() == "" || $("#costoAD").val() == "" || $("#durAD").val() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.","formulario-altaAD");
		return false;
	}
	return true;
}

$(window).on('load', function() {
	try{
		$('.miurl').val(window.location.href);
		ec = $('#tostadas').attr("data-tcode");
		if(ec!=""){
			es = ec.split(",");
			for(let i=0;i<es.length;i++)
				crearTostada(es[i]);
		}
	    document.getElementById('regLink').onclick = swapIniRegModals;
	    document.getElementById('iniLink').onclick = swapIniRegModals;
	    document.getElementById('radioSocio').addEventListener('input',registroSocio);
	    document.getElementById('radioProfe').addEventListener('input',registroProfe);
	    registroSocio();		
	} catch (error){
		console.log("Handled info0: "+error);
	}
});