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
function crearTostada(title,msg){
	toast = `<div class='toast' role='alert' aria-live='assertive' aria-atomic='true'>
				  <div class='toast-header'>
				    <strong class="me-auto">${title}</strong>
				    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				  </div>
				  <div class="toast-body">
				    ${msg}
				  </div>
				</div>`
	$("tostadas").append(t)
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


function iniciarSesion(){
	xhttp = new XMLHttpRequest();
	xhttp.open("POST",$("#formulario-sesion").attr("data-root")+"/api/login")
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("nick-login="+$("#user").val()+"&pass-login="+$("#pass").val());
	xhttp.onload = function(){
		if(xhttp.responseText.trim()=="OK"){
			window.location.reload();
		} else if(xhttp.responseText.trim()=="FAIL_PWD" || xhttp.responseText.trim()=="FAIL_USR"){
			errorMsgForm("La combinación usuario/contraseña es inválida.","formulario-sesion");
		}
	}
}
function registrarse(){
	/*Verificar Formulario*/
	if($("#nickk").val() == "" || $("#emaill").val() == "" || $("#pas1").val() == "" || $("#pas2").val() == "" ||
	 $("#nomm").val() == "" || $("#ape").val() == "" || $("#nac").val() == "" || ($("#radioProfe").is(":checked") && $("#desc").val()=="")){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.","formulario-registro");
		return;
	}
	if($("#pas1").val() != $("#pas2").val()){
		errorMsgForm("Las contraseñas no coinciden.","formulario-registro");
		return;
	}
	var formData = new FormData();
	formData.append("nickname",$("#nickk").val())
	formData.append("email",$("#emaill").val())
	formData.append("pwd",$("#pas1").val())
	formData.append("nombre",$("#nomm").val())
	formData.append("apellido",$("#ape").val())
	formData.append("nacimiento",$("#nac").val())

	if($("#radioProfe").is(":checked")){
		formData.append("institucion",$("#instit").val())
		formData.append("descripcion",$("#desc").val())
		formData.append("biografia",$("#bio").val())
		formData.append("website",$("#webs").val())
	}	
	var imgPerfil = $("#imgPerfil")[0].files;	
	if($("#imgPerfil")[0].files.length>0){
		var reader = new FileReader();
	    reader.readAsDataURL(imgPerfil[0]);
	   	reader.onload = function () {
			formData.append("image",reader.result);
			formData.append("imagename",imgPerfil[0].name);
			/*Enviar Formulario*/
			xhttp = new XMLHttpRequest();
			xhttp.open("POST",$("#formulario-registro").attr("data-root")+"/api/signup")
			xhttp.send(formData);
			xhttp.onload = function(){
				if(xhttp.responseText.trim()=="OK"){
					window.location.reload();
				} else if(xhttp.responseText.trim()=="FAIL DATA_IN_USE"){
					errorMsgForm("Ya existe un usuario con los datos ingresados.","formulario-registro");
				}
				else{
					errorMsgForm("Ha ocurrido un error inesperado. Por favor intente nuevamente más tarde.","formulario-registro");
				}
			}		
	   };
	   reader.onerror = function (error) {
	     console.log('Error: ', error);
	   };
	}
	else{
		/*Enviar Formulario*/
		xhttp = new XMLHttpRequest();
		xhttp.open("POST",$("#formulario-registro").attr("data-root")+"/api/signup")
		xhttp.send(formData);
		xhttp.onload = function(){
			if(xhttp.responseText.trim()=="OK"){
				window.location.reload();
			} else if(xhttp.responseText.trim()=="FAIL DATA_IN_USE"){
				errorMsgForm("Ya existe un usuario con los datos ingresados.","formulario-registro");
			}
			else{
				errorMsgForm("Ha ocurrido un error inesperado. Por favor intente nuevamente más tarde.","formulario-registro");
			}
		}		
	}
}
function altaAD(){
	/*Verificar Formulario*/
	if($("#nombreAD").val() == "" || $("#descAD").val() == "" || $("#costoAD").val() == "" || $("#durAD").val() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.","formulario-altaAD");
		return;
	}
	var formData = new FormData();
	formData.append("nombreAD",$("#nombreAD").val())
	formData.append("descAD",$("#descAD").val())
	formData.append("costoAD",$("#costoAD").val())
	formData.append("durAD",$("#durAD").val())
	formData.append("catAD",$("#catAD").val())

	var imgAD = $("#imgAD")[0].files;	
	if($("#imgAD")[0].files.length>0){
		var reader = new FileReader();
	    reader.readAsDataURL(imgAD[0]);
	   	reader.onload = function () {
			formData.append("image",reader.result);
			formData.append("imagename",imgAD[0].name);
			/*Enviar Formulario*/
			xhttp = new XMLHttpRequest();
			xhttp.open("POST",$("#formulario-altaAD").attr("data-root")+"/api/alta_ad")
			xhttp.send(formData);
			xhttp.onload = function(){
				console.log(xhttp.responseText.trim());
				if(xhttp.responseText.trim()=="OK"){
					window.location.reload();
					crearTostada("Notifiación","Se ha dado de alta la actividad deportiva de manera exitosa.");
				} else if(xhttp.responseText.trim()=="FAIL DATA_IN_USE"){
					errorMsgForm("Ya existe un actividad deportiva con el mismo nombre.","formulario-altaAD");
				}
				else{
					errorMsgForm("Ha ocurrido un error inesperado. Por favor intente nuevamente más tarde.","formulario-altaAD");
				}
			}		
	   };
	   reader.onerror = function (error) {
	     console.log('Error: ', error);
	   };
	}
	else{
		/*Enviar Formulario*/
		xhttp = new XMLHttpRequest();
		xhttp.open("POST",$("#formulario-altaAD").attr("data-root")+"/api/alta_ad")
		xhttp.send(formData);
		xhttp.onload = function(){
			if(xhttp.responseText.trim()=="OK"){
				window.location.reload();
				crearTostada("Notifiación","Se ha dado de alta la actividad deportiva de manera exitosa.");
			} else if(xhttp.responseText.trim()=="FAIL DATA_IN_USE"){
				errorMsgForm("Ya existe un actividad deportiva con el mismo nombre.","formulario-altaAD");
			}
			else{
				errorMsgForm("Ha ocurrido un error inesperado. Por favor intente nuevamente más tarde.","formulario-altaAD");
			}
		}		
	}	
}

$(window).on('load', function() {
	try{
	    document.getElementById('regLink').onclick = swapIniRegModals;
	    document.getElementById('iniLink').onclick = swapIniRegModals;
	    document.getElementById('radioSocio').addEventListener('input',registroSocio);
	    document.getElementById('radioProfe').addEventListener('input',registroProfe);
	    registroSocio();		
	} catch (error){
	}
});