<script>
    import { onMount } from 'svelte';
    import { checkAuthentication, fetchEnvios } from '$lib/envios';

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

<main>
    <h1>Lista de Envios</h1>

    {#if error}
        <p style="color: red;">{error}</p>
    {:else if envios.length === 0}
        <p>{mensaje || 'Cargando envíos...'}</p>
    {:else}
        <div>
            <ul>
                {#each envios as envio}
                    <li>
                        Envío ID: {envio.id} - Supervisor: {envio.supervisor.nombre} {envio.supervisor.apellido} - Proveedor {envio.proveedor.nombre} {envio.proveedor.apellido}
                    </li>
                {/each}
            </ul>
        </div>
    {/if}
</main>

<style>
    main {
        font-family: Arial, sans-serif;
        padding: 1rem;
    }
</style>