<script>
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation'; // Asegúrate de importar `goto` si no lo has hecho

    let envios = [];
    let error = null;
    let mensaje = '';

    // Función para obtener el valor de una cookie
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
        return null; // Devuelve null si la cookie no existe
    }

    // Función para verificar si el usuario está autenticado
    function checkAuthentication() {
        const token = getCookie('token');
        //console.log('Token obtenido de las cookies:', token); // Depuración
        const isAuthenticated = !!token; // Si el token existe, el usuario está autenticado
        if (!isAuthenticated) {
            goto('/login'); // Redirige al login si no está autenticado
        }
        return token; // Devuelve el token para usarlo en la solicitud
    }

    onMount(async () => {
        const token = checkAuthentication(); // Obtén el token desde las cookies
        if (!token) return; // Si no hay token, no continúes

        try {
            const response = await fetch('http://localhost:8080/api/getTodosLosEnvios', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error(`Error: ${response.status} ${response.statusText}`);
            }

            const result = await response.json();
            //console.log(result); // Verifica la estructura de los datos aquí

            if (result.data) {
                envios = result.data;
                mensaje = result.message;
            } else {
                throw new Error(result.message || 'Error desconocido');
            }
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
            <!-- <p>{mensaje}</p> -->
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