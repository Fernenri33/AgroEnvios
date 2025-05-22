<script>
    import { onMount } from 'svelte';
    import { checkAuthentication, fetchEnvios, crearEnvio } from '$lib/misEnvios';
    import AppMenu from '../../components/appMenu.svelte';
    import { goto } from '$app/navigation';

    let envios = [];
    let error = null;
    let mensaje = '';

    async function handleCrearEnvio() {
        try {
            const token = checkAuthentication();
            const nuevoEnvio = await crearEnvio(token, {}); // Puedes personalizar el objeto
            goto(`/editarEnvio/${nuevoEnvio.id}`, { state: { envio: nuevoEnvio } });
        } catch (err) {
            error = err.message;
        }
    }

    onMount(async () => {
        const token = checkAuthentication(); // Obtén el token desde las cookies
        if (!token) return; // Si no hay token, no continúes

        try {
            const result = await fetchEnvios(token); // Llama a la función para obtener los envíos
            envios = result.envios;
            mensaje = result.mensaje;
        } catch (err) {
            error = err.message;
        }
    });
</script>

<div class="flex h-screen">
    <!-- Menú lateral -->
    <AppMenu />

    <!-- Contenido principal -->
    <main class="flex-1 bg-gray-100 p-6">
        <div class="flex items-center justify-between mb-4">
            <h1 class="text-2xl font-bold">Lista de Envios</h1>
            <button
                class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded shadow"
                on:click={handleCrearEnvio}
            >
                Crear Envio
            </button>
        </div>

        {#if error}
            <p class="text-red-500">{error}</p>
        {:else if envios.length === 0}
            <p class="text-gray-700">{mensaje || 'Cargando envíos...'}</p>
        {:else}
            <div class="bg-white shadow-md rounded p-4">
                <table class="table-auto w-full border-collapse border border-gray-200">
                    <thead>
                        <tr class="bg-gray-100">
                            <th class="border border-gray-300 px-4 py-2 text-left">Envío ID</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Fecha de creación</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Estado</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {#each envios as envio}
                            <tr class="hover:bg-gray-50">
                                <td class="border border-gray-300 px-4 py-2">{envio.id}</td>
                                <td class="border border-gray-300 px-4 py-2">
                                    {new Date(envio.fechaCreacion).toLocaleDateString('es-ES', {
                                        year: 'numeric',
                                        month: '2-digit',
                                        day: '2-digit'
                                    })}
                                </td>
                                <td class="border border-gray-300 px-4 py-2">
                                    {envio.estado}
                                </td>
                                <td class="border border-gray-300 px-4 py-2 flex gap-2">
                                    <a
                                        href={`/editarEnvio/${envio.id}`}
                                        class="bg-blue-600 hover:bg-blue-700 text-white px-3 py-1 rounded text-sm"
                                        title="Editar Envío"
                                    >
                                        Editar
                                    </a>
                                    <button
                                        class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded text-sm"
                                        title="Eliminar Envío"
                                        on:click={() => {/* Lógica para eliminar */}}
                                    >
                                        Eliminar
                                    </button>
                                </td>
                            </tr>
                        {/each}
                    </tbody>
                </table>
            </div>
        {/if}
    </main>
</div>