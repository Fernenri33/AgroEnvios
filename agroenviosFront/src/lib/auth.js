import { goto } from '$app/navigation';

export async function loginUser(email, password) {
    let error = '';
    let loading = true;

    try {
        const response = await fetch('https://agroenvios.xyz/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json().catch(() => ({}));

        if (!response.ok) {
            const mensaje = data.error || data.message || 'Error al iniciar sesión';
            throw new Error(mensaje);
        }

        document.cookie = `token=${data.token}; path=/; max-age=86400`;
        goto('/dashboard');
    } catch (err) {
        error = err.message || 'Ocurrió un error inesperado';
        throw new Error(error);
    } finally {
        loading = false;
    }
}