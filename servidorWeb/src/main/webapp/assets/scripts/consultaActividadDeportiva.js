function altaCL(){
	/*Verificar Formulario*/
	if ($("#nomclase").val() == "" || $("#fechaIni").val() == "" || $("#urlin").val() == ""){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-clase");
		return false;
	}
	if ($("#minax").val() > $("#asd").val()){
		errorMsgForm("La cantidad máxima de cupos debe ser mayor a la mínima", "formulario-clase");
		return false;
	}
	if ((new Date($("#fechaIni").val())) < (new Date())){
		errorMsgForm("No es posible dictar clases en el pasado.", "formulario-clase");
		return false;
	}
	else
		return true;
}

