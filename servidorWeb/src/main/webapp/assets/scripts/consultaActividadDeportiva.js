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
	if (($("#cantPremios").val().trim() != "") ^ (($("#descPremio").val().trim() != ""))){
		errorMsgForm("La infromación del premio no puede estar parcialmente incompleta ⚠", "formulario-clase");
		return false;
	}
	if (parseInt($("#cantPremios").val()) < 1){
		errorMsgForm("La cantidad de premios debe ser mayor a 0 ⚠", "formulario-clase");
		return false;
	}
	d = new Date($("#fechaIni").val())
	d.setDate(d.getDate()+1) //I love javascript?
	d.setMinutes(parseInt($("#minutosInicio").val()))
	d.setHours(parseInt($("#horaInicio").val()))
	if (d < (new Date())){
		errorMsgForm("No es posible dictar clases en el pasado. ", "formulario-clase");
		return false;
	}
	else {
		$("#nomclase").val($("#nomclase").val().trim());
		return true;
	}
}

