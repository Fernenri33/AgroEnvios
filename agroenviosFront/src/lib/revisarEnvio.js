export async function aceptarEnvio(token, idEnvio, productos) {
    const res = await fetch(`https://agroenvios.xyz/api/aceptarEnvio/${idEnvio}`, {
        method: 'POST',
        headers: { 
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productos)
    });
    if (!res.ok) throw new Error((await res.json()).message || 'Error al aceptar envío');
    return await res.json();
}

export async function rechazarEnvio(token, idEnvio) {
    const res = await fetch(`https://agroenvios.xyz/api/rechazarEnvio/${idEnvio}`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
        // No body, no Content-Type
    });
    if (!res.ok) throw new Error(res.message);
    return await res.json();
}