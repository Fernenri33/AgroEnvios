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

export async function getUsuarioPorId(token, id) {
    const res = await fetch(`https://agroenvios.xyz/api/usuario/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    });

    if (!res.ok) {
        const error = await res.json();
        throw new Error(error.error || 'Error al obtener el usuario');
    }

    return await res.json();
}

export async function actualizarUsuario(id, usuario, token) {
    const res = await fetch(`https://agroenvios.xyz/api/usuario/${id}`, {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    });

    if (!res.ok) {
        const error = await res.json();
        throw new Error(error.error || 'Error al actualizar el usuario');
    }

    return await res.json();
}