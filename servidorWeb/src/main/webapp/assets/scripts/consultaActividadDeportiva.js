function altaCL(){
	/*Verificar Formulario*/
	if ($("#nomclase").val().trim() == "" || $("#fechaIni").val() == "" || $("#urlin").val() == "" || $("#horaInicio").val() == "-" || $("#minutosInicio").val() == "-"){
		errorMsgForm("Existen campos obligatorios vacíos/sin seleccionar.", "formulario-clase");
		return false;
	}
	if (parseInt($("#minax").val()) > parseInt($("#asd").val())){
		errorMsgForm("La cantidad máxima de cupos debe ser mayor o igual a la mínima ⚠", "formulario-clase");
		return false;
	}
	if ((new Date($("#fechaIni").val())) < (new Date())){
		errorMsgForm("No es posible dictar clases en el pasado. ", "formulario-clase");
		return false;
	}
	else {
		$("#nomclase").val($("#nomclase").val().trim());
		return true;
	}
}

