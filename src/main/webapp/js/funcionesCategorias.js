$("#serviciosSeleccionados").change(function () {
    var idservi = $(this).val();
    var url = "../PruebaCategoria?Op=AgregarServicios&id=" + idservi; // Agrega el parámetro id a la URL
    $.ajax({
        type: 'get', // Cambia 'post' a 'get' para enviar una solicitud GET
        url: url,
        success: function (data, textStatus, jqXHR) {
            // Redirecciona después de agregar el servicio
            location.href = "../PruebaCategoria?Op=Listar";
        }
    });
});

$("#serviciosSeleccionadosMo").change(function () {
    var idservi = $(this).val();
    var url = "../PruebaCategoria?Op=AgregarServicioModificar&servicioId=" + idservi;
    $.ajax({
        type: 'get',
        url: url,
        success: function (data, textStatus, jqXHR) {
            location.href = "../PruebaCategoria?Op=Listar";
        }
    });
});