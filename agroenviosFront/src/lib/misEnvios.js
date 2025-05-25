import { goto } from '$app/navigation';

// Función para obtener el valor de una cookie
export function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null; // Devuelve null si la cookie no existe
}

// Función para verificar si el usuario está autenticado
export function checkAuthentication() {
    const token = getCookie('token');
    const isAuthenticated = !!token; // Si el token existe, el usuario está autenticado
    if (!isAuthenticated) {
        goto('/login'); // Redirige al login si no está autenticado
    }
    return token; // Devuelve el token para usarlo en la solicitud
}

// Función para obtener todos los envíos
export async function fetchEnvios(token) {
    try {
        const response = await fetch('https://agroenvios.xyz/api/getMisEnvios', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        const result = await response.json();
        if (result.data) {
            return { envios: result.data, mensaje: result.message };
        } else {
            throw new Error(result.message || 'Error desconocido');
        }
    } catch (err) {
        throw new Error(err.message);
    }
}

export async function crearEnvio(token, envioData = {}) {
    const response = await fetch('https://agroenvios.xyz/api/crearEnvioVacio', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) {
        throw new Error('Error al crear el envío');
    }
    const apiResponse = await response.json();
    return apiResponse.data; // Se espera que retorne el nuevo envío, incluyendo su id
}

export async function eliminarEnvio(token, envio) {
    const response = await fetch('https://agroenvios.xyz/api/eliminarEnvio', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(envio)
    });
    if (!response.ok) {
        throw new Error('Error al eliminar el envío');
    }
    const apiResponse = await response.json();
    return apiResponse.data;
}
