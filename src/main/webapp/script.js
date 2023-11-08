

document.addEventListener("click", (e) => {
    if(e.target.dataset.fechaselect) {
        (document.querySelector(".fechas-popup").classList.contains('hidden')) ?
        (document.querySelector(".fechas-popup").classList.remove('hidden')) :
        (document.querySelector(".fechas-popup").classList.add('hidden'));
    }
});

function contruirCalendario(element, indexMonth) {
    const months = [
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    ];

    const today = new Date();
    const currentMonth = (today.getMonth() + indexMonth) % 12; // Utiliza el operador módulo para mantener el mes entre 0 y 11
    const currentYear = today.getFullYear() + Math.floor((today.getMonth() + indexMonth) / 12); // Ajusta el año según los meses extra

    element.parentNode.parentNode.querySelector("h2").querySelector("span").textContent = months[currentMonth];

    // Crear una nueva fecha para el primer día del mes actual
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    
    // Obtener el número de día (0 para domingo, 1 para lunes, etc.)
    const firstDayOfWeek = firstDayOfMonth.getDay();

    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    let dayCounter = 1;

    for (let row = 0; row < 5; row++) {
        const newRow = document.createElement("tr");
        for (let col = 0; col < 7; col++) {
            const newCell = document.createElement("td");
            if (row === 0 && col < firstDayOfWeek) {
                newCell.textContent = "";
            } else if (dayCounter <= daysInMonth) {
                newCell.textContent = dayCounter;

                dayCounter++;
            }


            newCell.addEventListener("click", () => {

                if(!fechaLlegadaReady || !fechaSalidaReady) {
                    console.log(`${newCell.textContent} de ${months[currentMonth]}`);
                    changeStyleCell(newCell);

                    if(fechaLlegadaReady) {
                        if(!fechaSalidaReady)
                        document.querySelector("#txt-fecha-salida").textContent = `${newCell.textContent} de ${months[currentMonth]}`;
                        fechaSalidaReady = true
                    }
                    else {
                        document.querySelector("#txt-fecha-llegada").textContent = `${newCell.textContent} de ${months[currentMonth]}`;
                        fechaLlegadaReady = true;
                    }
                }
            });


            newRow.appendChild(newCell);
        }
        element.appendChild(newRow);
    }
}

let fechaLlegadaReady = false;
let fechaSalidaReady = false;

function changeStyleCell(element) {
    element.style.backgroundColor = "#333050";
    element.style.color = "white";
}


function buildCalendar() {

    contruirCalendario(document.querySelector("#calendar-body-1"), 0);
    contruirCalendario(document.querySelector("#calendar-body-2"), 1);
}

buildCalendar();

