// Función para calcular el precio total
function calcularPrecioTotal() {
    var cantidad = parseInt(document.getElementById('cantidad').value);
    var precioPorEvento = parseFloat(document.getElementById('precio_evento').value)
    var precioTotal = cantidad * precioPorEvento;
    document.getElementById('precio_total').value = precioTotal.toFixed(2);
}

// Función para obtener la fecha actual en el formato YYYY-MM-DD
function obtenerFechaActual() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // Enero es 0!
    var yyyy = today.getFullYear();

    return yyyy + '-' + mm + '-' + dd;
}

// Establecer la fecha actual en el campo de entrada de fecha de compra
document.getElementById('fecha_compra').value = obtenerFechaActual();