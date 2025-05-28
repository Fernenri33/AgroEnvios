<script>
    import { onMount } from 'svelte';
    import { fetchProductos } from '$lib/editarEnvio';
    import { checkAuthentication } from '$lib/misEnvios';
    import AppMenu from '../../components/appMenu.svelte';

    let productos = [];
    let error = null;
    let loading = true;

    onMount(async () => {
        loading = true;
        error = null;
        try {
            const token = checkAuthentication();
            productos = await fetchProductos(token);
        } catch (err) {
            error = err.message;
        } finally {
            loading = false;
        }
    });
</script>

<div class="flex h-screen">
    <AppMenu />
    <main class="flex-1 bg-gray-100 p-6 min-h-screen">
        <h1 class="text-2xl font-bold mb-4">Lista de Productos</h1>
        {#if loading}
            <p>Cargando...</p>
        {:else if error}
            <p class="text-red-600">{error}</p>
        {:else}
            <div class="bg-white shadow-md rounded p-4">
                <table class="table-auto w-full border-collapse border border-gray-200 mb-4">
                    <thead>
                        <tr class="bg-gray-100">
                            <th class="border border-gray-300 px-4 py-2">ID</th>
                            <th class="border border-gray-300 px-4 py-2">Nombre</th>
                            <th class="border border-gray-300 px-4 py-2">Descripción</th>
                            <th class="border border-gray-300 px-4 py-2">Cantidad</th>
                            <th class="border border-gray-300 px-4 py-2">Unidad de medida</th>
                        </tr>
                    </thead>
                    <tbody>
                        {#each productos as producto}
                            <tr>
                                <td class="border border-gray-300 px-4 py-2">{producto.id}</td>
                                <td class="border border-gray-300 px-4 py-2">{producto.nombre}</td>
                                <td class="border border-gray-300 px-4 py-2">{producto.descripcion}</td>
                                <td class="border border-gray-300 px-4 py-2">{producto.cantidad}</td>
                                <td class="border border-gray-300 px-4 py-2">{producto.unidadMedida}</td>
                            </tr>
                        {/each}
                    </tbody>
                </table>
            </div>
        {/if}
    </main>
</div>
