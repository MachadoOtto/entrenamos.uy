errores = {"e1m":"La combinación Usuario/Contraseña no es correcta.", "e1t":"Error al Iniciar Sesión", "e1c":"rgb(255 0 0 / 60%)", 
		   "e2m":"Ya existe un usuario con el nickname o correo seleccionado.", "e2t":"Error al Registrar Usuario", "e2c":"rgb(255 0 0 / 60%)", 
			"e3m":"Se ha registrado su usuario de manera exitosa.", "e3t":"Registrar Usuario", "e3c":"rgb(86 255 0 / 60%)", 
			"e4m":"El nombre de la actividad deportiva a crear ya existe en el sistema", "e4t":"Error al Registrar Actividad Deportiva", "e4c":"rgb(255 0 0 / 60%)", 
			"e5m":"Se ha registrado la actividad deportiva exitosamente.", "e5t":"Alta de Actividad Deportiva", "e5c":"rgb(86 255 0 / 60%)", 
			"e6m":"Su inscripcion se ha realizado con exito.", "e6t":"Inscripcion a Clase", "e6c":"rgb(86 255 0 / 60%)", 
			"e7m":"Su informacion se ha modificado con exito.", "e7t":"Editar Perfil", "e7c":"rgb(86 255 0 / 60%)",
			"e8m":"Ya existe una clase con ese nombre ingresada en el sistema.", "e8t":"Alta Dictado Clase", "e8c":"rgb(255 0 0 / 60%)",
			"e9m":"Se ha registrado la clase de manera exitosa.", "e9t":"Alta Dictado Clase", "e9c":"rgb(86 255 0 / 60%)"
			};

function shake(element){
	$('#'+element).animate({'margin-left': '+=3px',  'margin-right': '-=3px'}, "fast");
	$('#'+element).animate({'margin-right': '+=3px',  'margin-left': '-=3px'}, "fast");
}
function errorMsgForm(msg, parent){
	if ($('#errorMsg'+parent).length==0){
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
    $('.modal').css('overflow-y',  'auto');
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

function login(){
	/*Verificar Formulario*/
	if ($("#pass").val().trim() == "" || $("#user").val().trim() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-sesion");
		return false;
	}
	return true;
}

function  registrarse(){
	/*Verificar Formulario*/
	if ($("#nickk").val().trim() == "" || $("#emaill").val().trim() == "" || $("#pas1").val() == "" || $("#pas2").val() == "" ||
	 $("#nomm").val().trim() == "" || $("#ape").val().trim() == "" || $("#nac").val() == "" || ($("#radioProfe").is(":checked") && $("#desc").val().trim()=="")){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-registro");
		return false;
	}
	if ($("#pas1").val() != $("#pas2").val()){
		errorMsgForm("Las contraseñas no coinciden.", "formulario-registro");
		return false;
	} 
	if ((new Date($("#nac").val())) > (new Date())){
		errorMsgForm("No es posible nacer en el futuro.", "formulario-registro");
		return false;
	}
	else{
		$("#nickk").val($("#nickk").val().trim());
		$("#emaill").val($("#emaill").val().trim());
		$("#nomm").val($("#nomm").val().trim());
		$("#ape").val($("#ape").val().trim());
		$("#desc").val($("#desc").val().trim());
		return true;
	}
}




function altaAD(){
	/*Verificar Formulario*/
	if ($("#nombreAD").val().trim() == "" || $("#descAD").val().trim() == "" || $("#costoAD").val() == "" || $("#durAD").val() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-altaAD");
		return false;
	}
	if(parseInt($("#costoAD").val())<0){
		errorMsgForm("Los usurios no pueden recibir dinero al realizar una actividad.", "formulario-altaAD");
		return false;
	}
	if(parseInt($("#durAD").val())<0){
		errorMsgForm("No es posible instanciar actividades que alteren la linea temporal.", "formulario-altaAD");
		return false;
	} else {
		$("#nombreAD").val($("#nombreAD").val().trim());
		$("#descAD").val($("#descAD").val().trim());
		return true;
	}	
}

$(window).on('load',  function() {
	try{
		$('.miurl').val(window.location.href);
		ec = $('#tostadas').attr("data-tcode");
		if (ec!=""){
			es = ec.split(", ");
			for (let i=0;i<es.length;i++)
				crearTostada(es[i]);
		}
	    document.getElementById('regLink').onclick = swapIniRegModals;
	    document.getElementById('iniLink').onclick = swapIniRegModals;
	    document.getElementById('radioSocio').addEventListener('input', registroSocio);
	    document.getElementById('radioProfe').addEventListener('input', registroProfe);
	    registroSocio();		
	} catch (error){
		console.error(error);
	}
	try{
		$("#formulario-registro").submit(function(e) {
			if(!registrarse()){
				return
			}
		    // Stop the form submitting
		    e.preventDefault();
			/*Validación nick e email*/
			xhttp = new XMLHttpRequest();
			xhttp.open("GET",$("#formulario-registro").attr("data-root")+"/signup?nick="+$("#nickk").val().trim()+"&email="+$("#emaill").val().trim());
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
			xhttp.onload = function(){
				if(xhttp.responseText.trim()=="OK"){
					$("#nickk").val($("#nickk").val().trim());
					$("#emaill").val($("#emaill").val().trim());
					$("#nomm").val($("#nomm").val().trim());
					$("#ape").val($("#ape").val().trim());
					$("#desc").val($("#desc").val().trim());
					e.currentTarget.submit();
				}
				else if(xhttp.responseText.trim()=="BAD_NICK")
					errorMsgForm("El nickname seleccionado no se encuentra disponible.","formulario-registro");
				else if(xhttp.responseText.trim()=="BAD_MAIL")
					errorMsgForm("El email ingresado no se encuentra disponible.","formulario-registro");
				else
					errorMsgForm("El nickname e email ingresados no se encuentran disponibles.","formulario-registro");
			}
		});		
	}catch(error){console(error)}
});