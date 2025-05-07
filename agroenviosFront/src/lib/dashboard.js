import { goto } from '$app/navigation';

// Función para obtener el valor de una cookie
export function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

// Función para verificar si el usuario está autenticado
export function checkAuthentication() {
    const token = getCookie('token');
    const isAuthenticated = !!token; // Si el token existe, el usuario está autenticado
    if (!isAuthenticated) {
        goto('/login'); // Redirige al login si no está autenticado
    }
    return { token, isAuthenticated };
}

// Función para cerrar sesión
export function logout() {
    document.cookie = 'token=; Max-Age=0; path=/'; // Elimina la cookie del token
    goto('/login'); // Redirige al login
}