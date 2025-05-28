<script>
    import { onMount } from 'svelte';
    import { page } from '$app/stores';
    import { goto } from '$app/navigation';
    import { checkAuthentication } from '$lib/misEnvios';
    import { actualizarProducto, fetchProductoPorId } from '$lib/productos';

    let producto = {
        nombre: '',
        descripcion: '',
        cantidad: '',
        unidadMedida: ''
    };
    let error = null;
    let loading = true;
    let mensaje = '';
    $: productoId = $page.params.id;

    onMount(async () => {
        loading = true;
        error = null;
        try {
            const token = checkAuthentication();
            const data = await fetchProductoPorId(token, productoId);
            producto = {
                nombre: data.nombre,
                descripcion: data.descripcion,
                cantidad: data.cantidad,
                unidadMedida: data.unidadMedida
            };
        } catch (err) {
            error = err.message;
        } finally {
            loading = false;
        }
    });

    async function handleSubmit() {
        loading = true;
        error = null;
        try {
            const token = checkAuthentication();
            await actualizarProducto(token, { id: productoId, ...producto });
            mensaje = 'Producto actualizado correctamente';
            setTimeout(() => goto('/productos'), 1200);
        } catch (err) {
            error = err.message;
        } finally {
            loading = false;
        }
    }
</script>

<div class="flex h-screen">
    <main class="flex-1 bg-gray-100 p-6 min-h-screen flex flex-col items-center">
        <h1 class="text-2xl font-bold mb-6">Editar Producto</h1>
        {#if loading}
            <p>Cargando...</p>
        {:else}
            <form class="bg-white shadow-md rounded p-6 w-full max-w-lg" on:submit|preventDefault={handleSubmit}>
                {#if error}
                    <p class="text-red-600 mb-4">{error}</p>
                {/if}
                {#if mensaje}
                    <p class="text-green-600 mb-4">{mensaje}</p>
                {/if}
                <div class="mb-4">
                    <label class="block font-semibold mb-1">Nombre</label>
                    <input class="w-full border rounded px-3 py-2" bind:value={producto.nombre} required />
                </div>
                <div class="mb-4">
                    <label class="block font-semibold mb-1">Descripción</label>
                    <textarea class="w-full border rounded px-3 py-2" bind:value={producto.descripcion} required />
                </div>
                <div class="mb-4">
                    <label class="block font-semibold mb-1">Cantidad</label>
                    <input type="number" min="0" class="w-full border rounded px-3 py-2" bind:value={producto.cantidad} required />
                </div>
                <div class="mb-6">
                    <label class="block font-semibold mb-1">Unidad de medida</label>
                    <input class="w-full border rounded px-3 py-2" bind:value={producto.unidadMedida} required />
                </div>
                <div class="flex justify-between">
                    <button
                        type="button"
                        class="bg-gray-400 hover:bg-gray-500 text-white px-4 py-2 rounded"
                        on:click={() => goto('/productos')}
                    >
                        Cancelar
                    </button>
                    <button
                        type="submit"
                        class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
                        disabled={loading}
                    >
                        Guardar Cambios
                    </button>
                </div>
            </form>
        {/if}
    </main>
</div>