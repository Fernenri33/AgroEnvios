// Funciones para usuarios (admin)

export async function crearUsuario(token, usuario) {
    try {
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
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }
}

export async function crearUsuarioVacio(token) {
    try {
        const res = await fetch('https://agroenvios.xyz/api/crearUsuarioVacio', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
        });
        if (!res.ok) throw new Error((await res.json()).error || 'Error al crear usuario');
        return await res.json();
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }
}

export async function getTodosLosUsuarios(token) {
    try {
        const res = await fetch('https://agroenvios.xyz/api/todosLosUsuarios', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
        if (!res.ok) throw new Error((await res.json()).error || 'Error al obtener usuarios');
        return await res.json();
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }
}

export async function getUsuarioPorId(token, id) {
    try {
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
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }
}

export async function actualizarUsuario(usuario, token) {
    try {
        const res = await fetch(`https://agroenvios.xyz/api/crearUsuario`, {
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
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }
}

export async function getUsuarioActual(token) {
    try {
        const res = await fetch('https://agroenvios.xyz/api/usuarioActual', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!res.ok) throw new Error((await res.json()).error || 'Error al obtener el usuario actual');
        return await res.json();
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }

    
}


export async function getRol(token) {
    try {
        const res = await fetch('https://agroenvios.xyz/api/getRolUsuario', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!res.ok) throw new Error((await res.json()).error || 'Error al obtener el usuario rol actual');
        return await res.json();
    } catch (error) {
        window.location.href = '/error';
        throw error;
    }

}