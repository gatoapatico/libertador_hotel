//Fecha Actual
document.addEventListener("DOMContentLoaded", function () {
    // Obtén la fecha actual
    var fechaHoy = new Date();

    // Formatea la fecha como "YYYY-MM-DD" (puedes ajustar el formato según tus necesidades)
    var fechaFormateada = fechaHoy.getFullYear() + "-" + (fechaHoy.getMonth() + 1).toString().padStart(2, "0") + "-" + fechaHoy.getDate().toString().padStart(2, "0");

    // Establece la fecha formateada como valor del campo de entrada oculto
    document.getElementById("fechaActual").value = fechaFormateada;
});


