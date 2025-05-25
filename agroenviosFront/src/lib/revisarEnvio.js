export async function aceptarEnvio(token, idEnvio) {
    const res = await fetch(`https://agroenvios.xyz/api/aceptarEnvio/${idEnvio}`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
    });
    if (!res.ok) throw new Error('Error al aceptar el envío');
    return await res.json();
}