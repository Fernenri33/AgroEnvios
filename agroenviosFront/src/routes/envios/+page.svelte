<script>
    import { onMount } from 'svelte';
    import { checkAuthentication, fetchEnvios } from '$lib/envios';
    import AppMenu from '../../components/appMenu.svelte';

    let envios = [];
    let error = null;
    let mensaje = '';

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
        <h1 class="text-2xl font-bold mb-4">Lista de Envios</h1>

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
                            <th class="border border-gray-300 px-4 py-2 text-left">Supervisor</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Proveedor</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Fecha de creación</th>
                            <th class="border border-gray-300 px-4 py-2 text-left">Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        {#each envios as envio}
                            <tr class="hover:bg-gray-50">
                                <td class="border border-gray-300 px-4 py-2">{envio.id}</td>
                                <td class="border border-gray-300 px-4 py-2">
                                    {envio.supervisor.nombre} {envio.supervisor.apellido}
                                </td>
                                <td class="border border-gray-300 px-4 py-2">
                                    {envio.proveedor.nombre} {envio.proveedor.apellido}
                                </td>

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
                            </tr>
                        {/each}
                    </tbody>
                </table>
            </div>
        {/if}
    </main>
</div>