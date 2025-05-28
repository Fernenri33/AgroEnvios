import { goto } from '$app/navigation';

export async function crearProducto(token) {
    const res = await fetch('https://agroenvios.xyz/api/crearProducto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
    const data = await res.json();
    if (!res.ok) {
        throw new Error(data.mensaje || data.message || 'Error al crear el producto');
    }
    // Redirige a la página de edición del producto recién creado
    goto(`/editarProducto/${data.data.id}`);
    return data.data;
}    

export async function fetchProductoPorId(token, id) {
    const res = await fetch(`https://agroenvios.xyz/api/producto/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
    const data = await res.json();
    if (!res.ok) {
        throw new Error(data.mensaje || data.message || 'Error al obtener el producto');
    }
    // El producto viene en data.data según tu ApiResponse
    return data.data;
}

export async function actualizarProducto(token, producto) {
    const res = await fetch('https://agroenvios.xyz/api/actualizarProducto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(producto)
    });
    const data = await res.json();
    if (!res.ok) {
        throw new Error(data.mensaje || data.message || 'Error al actualizar el producto');
    }
    return data.data;
}