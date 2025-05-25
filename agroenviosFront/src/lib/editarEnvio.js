// 1. Obtener todos los productos
export async function fetchProductos(token) {
    const res = await fetch('https://agroenvios.xyz/api/getTodosLosProductos', {
        headers: { 'Authorization': `Bearer ${token}` }
    });
    if (!res.ok) throw new Error('Error al obtener productos');
    const apiResponse = await res.json();
    return apiResponse.data;
}

// Obtener todos los detalles de un envío
export async function fetchEnvioDetalles(token, envioId) {
    const res = await fetch(`https://agroenvios.xyz/api/getEnvioDetalles/${envioId}`, {
        headers: { 'Authorization': `Bearer ${token}` }
    });
    if (!res.ok) throw new Error('Error al obtener los detalles');
    const apiResponse = await res.json();
    return apiResponse.data;
}

// 2. Guardar un envío detalle
export async function guardarEnvioDetalle(token, envioDetalle) {
    const params = new URLSearchParams({
        productoId: envioDetalle.productoId,
        cantidad: envioDetalle.cantidad,
        envioId: envioDetalle.envioId
    });

    const res = await fetch(`https://agroenvios.xyz/api/envioDetalle/crear?${params.toString()}`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`
            // No pongas 'Content-Type': 'application/json' aquí
        }
        // No envíes body
    });
    if (!res.ok) throw new Error('Error al guardar el detalle');
    return await res.json();
 }

// 3. Eliminar un envío detalle
export async function eliminarEnvioDetalle(token, idEnvioDetalle) {
    const res = await fetch(`https://agroenvios.xyz/api/envioDetalle/eliminar/${idEnvioDetalle}`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
    });
    if (!res.ok) throw new Error('Error al eliminar el detalle');
    return await res.json();
}


// 5. Editar el envío
export async function editarEnvio(token, envio) {
     const res = await fetch('/api/editarEnvio', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json',
             'Authorization': `Bearer ${token}`
         },
         body: JSON.stringify(envio)
     });
     if (!res.ok) throw new Error('Error al editar el envío');
     return await res.json();
}

export async function enviarEnvio(token, idEnvio) {
    const res = await fetch(`https://agroenvios.xyz/api/enviarEnvio/${idEnvio}`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
    });
    if (!res.ok) throw new Error('Error al enviar el envío');
    return await res.json();
}