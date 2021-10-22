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
$(window).load(function() {
    document.getElementById('regLink').onclick = swapIniRegModals;
    document.getElementById('iniLink').onclick = swapIniRegModals;
    document.getElementById('radioSocio').addEventListener('input', registroSocio);
    document.getElementById('radioProfe').addEventListener('input', registroProfe);
    registroSocio();
});