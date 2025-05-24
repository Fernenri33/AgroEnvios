<script>
    import { onMount } from 'svelte';
    import { page } from '$app/stores';
    import { fetchProductos, fetchEnvioDetalles, guardarEnvioDetalle, eliminarEnvioDetalle } from '$lib/editarEnvio';
    import { checkAuthentication } from '$lib/misEnvios';

    let productos = [];
    let detalles = [];
    let error = null;
    let loading = true;

    // Para el formulario de agregar detalle
    let nuevoProductoId = '';
    let nuevaCantidad = '';
    let nuevaUnidad = '';

    $: envioId = $page.params.id;

    onMount(async () => {
        loading = true;
        error = null;
        try {
            const token = checkAuthentication();
            productos = await fetchProductos(token);
            detalles = await fetchEnvioDetalles(token, envioId);
        } catch (err) {
            error = err.message;
        } finally {
            loading = false;
        }
    });

    async function handleEliminarDetalle(detalleId) {
        const token = checkAuthentication();
        try {
            await eliminarEnvioDetalle(token, detalleId);
            detalles = await fetchEnvioDetalles(token, envioId); // Recarga la lista
        } catch (err) {
            error = err.message;
        }
    }

    async function agregarDetalle() {
        const producto = productos.find(p => p.id == nuevoProductoId);
        if (!producto || !nuevaCantidad) return;

        const token = checkAuthentication();
        const envioDetalle = {
            productoId: producto.id,
            cantidad: nuevaCantidad,
            envioId: envioId
        };

        try {
            await guardarEnvioDetalle(token, envioDetalle);
            detalles = await fetchEnvioDetalles(token, envioId);
            nuevoProductoId = '';
            nuevaCantidad = '';
        } catch (err) {
            error = err.message;
        }
    }
</script>

<main class="flex-1 bg-gray-100 p-6 min-h-screen">
    <h1 class="text-2xl font-bold mb-4">Editar Envío {envioId}</h1>

    {#if loading}
        <p>Cargando...</p>
    {:else if error}
        <p class="text-red-600">{error}</p>
    {/if}

    <div class="bg-white shadow-md rounded p-4 mb-6">
        <!-- <div class="mb-4">
            <label class="block font-semibold mb-1">Comentario proveedor</label>
            <input class="w-full border rounded px-3 py-2" name="comentarioProveedor" />
        </div>
        <div class="mb-4">
            <label class="block font-semibold mb-1" for="motorista">Motorista</label>
            <input id="motorista" class="w-full border rounded px-3 py-2" name="motorista" />
        </div> -->
        <button class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
            Enviar
        </button>

        <!-- <button class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
            Guardar
        </button> -->
    </div>

    <div class="bg-white shadow-md rounded p-4">
        <h2 class="text-xl font-semibold mb-4">Detalles del Envío</h2>
        <table class="table-auto w-full border-collapse border border-gray-200 mb-4">
            <thead>
                <tr class="bg-gray-100">
                    <th class="border border-gray-300 px-4 py-2">Producto</th>
                    <th class="border border-gray-300 px-4 py-2">Cantidad</th>
                    <th class="border border-gray-300 px-4 py-2">Unidad de medida</th>
                    <th class="border border-gray-300 px-4 py-2">Acciones</th>
                </tr>
            </thead>
            <tbody>
                {#each detalles as detalle, idx}
                    <tr>
                        <td class="border border-gray-300 px-4 py-2">
                            {detalle.producto?.nombre || detalle.producto || ''}
                        </td>
                        <td class="border border-gray-300 px-4 py-2">
                            {detalle.cantidad}
                        </td>
                        <td class="border border-gray-300 px-4 py-2">
                            {detalle.producto?.unidadMedida || ''}
                        </td>
                        <td class="border border-gray-300 px-4 py-2">
                            <button
                                class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded text-sm"
                                on:click={() => handleEliminarDetalle(detalle.id)}
                            >
                                Eliminar
                            </button>
                        </td>
                    </tr>
                {/each}
                <!-- Fila para agregar nuevo detalle -->
                <tr>
                    <td class="border border-gray-300 px-4 py-2">
                        <select
                            class="w-full border rounded px-2 py-1"
                            bind:value={nuevoProductoId}
                        >
                            <option value="">Selecciona producto</option>
                            {#each productos as producto}
                                <option value={producto.id}>{producto.nombre}</option>
                            {/each}
                        </select>
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <input
                            type="number"
                            min="1"
                            class="w-full border rounded px-2 py-1"
                            bind:value={nuevaCantidad}
                            placeholder="Cantidad"
                        />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        {#if nuevoProductoId}
                            {productos.find(p => p.id == nuevoProductoId)?.unidadMedida}
                        {/if}
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <button
                            class="bg-green-600 hover:bg-green-700 text-white px-3 py-1 rounded text-sm"
                            type="button"
                            on:click={agregarDetalle}
                            disabled={!nuevoProductoId || !nuevaCantidad}
                        >
                            Agregar
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>