// Funciones para usuarios (admin)

export async function crearUsuario(token, usuario) {
    const res = await fetch('https://agroenvios.xyz/api/crearUsuario', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    });
    if (!res.ok) throw new Error((await res.json()).error || 'Error al crear usuario');
    return await res.json();
}

export async function crearUsuarioVacio(token) {
    const res = await fetch('https://agroenvios.xyz/api/crearUsuarioVacio', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
    });
    if (!res.ok) throw new Error((await res.json()).error || 'Error al crear usuario');
    return await res.json();
}

export async function getTodosLosUsuarios(token) {
    const res = await fetch('https://agroenvios.xyz/api/todosLosUsuarios', {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error((await res.json()).error || 'Error al obtener usuarios');
    return await res.json();
}
