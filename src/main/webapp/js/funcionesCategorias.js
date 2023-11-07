$("#serviciosSeleccionados").change(function () {
    var idservi = $(this).val();
    var url = "../SvCategorias?Op=AgregarServicios&id=" + idservi; // Agrega el parámetro id a la URL
    $.ajax({
        type: 'get', // Cambia 'post' a 'get' para enviar una solicitud GET
        url: url,
        success: function (data, textStatus, jqXHR) {
            // Redirecciona después de agregar el servicio
            location.href = "../SvCategorias?Op=Listar";
        }
    });
});
