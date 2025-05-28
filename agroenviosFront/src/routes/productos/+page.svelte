<script>
import { onMount } from 'svelte';
import { fetchProductos } from '$lib/editarEnvio';
import { checkAuthentication } from '$lib/misEnvios';
import AppMenu from '../../components/appMenu.svelte';

let productos = [];
let error = null;
let mensaje = '';

onMount(async () => {
    const token = checkAuthentication();
    if (!token) return;
    try {
        const result = await fetchProductos(token);
        productos = result.productos || [];
        mensaje = result.mensaje || '';
    } catch (err) {
        error = err.message;
    }
});
</script>

<div class="flex h-screen">
    <AppMenu />
    <main class="flex-1 bg-gray-100 p-6">
        <div class="flex items-center justify-between mb-4">
            <h1 class="text-2xl font-bold">Lista de Productos</h1>
        </div>
        {#if error}
            <p class="text-red-500">{error}</p>
        {:else if productos.length === 0}
            <p class="text-gray-700">{mensaje || 'No hay productos.'}</p>
        {:else}
            <div class="bg-white shadow-md rounded p-4">
                <table class="table-auto w-full border-collapse border border-gray-200">
                    <thead>
                        <tr class="bg-gray-100">
                            <th class="border border-gray-300 px-4 py-2 text-left">ID</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Nombre</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Descripción</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Cantidad</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Unidad de medida</th>

                        </tr>
                    </thead>
                    <tbody>
                        {#each productos as producto}
                            <tr class="hover:bg-gray-50">
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
