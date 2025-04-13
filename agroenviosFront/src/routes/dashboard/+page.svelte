<script>
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation';

    let token = '';
    let isAuthenticated = false;

    // Función para obtener el valor de una cookie
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
    }

    // Función para verificar si el usuario está autenticado
    function checkAuthentication() {
        token = getCookie('token');
        isAuthenticated = !!token; // Si el token existe, el usuario está autenticado
        if (!isAuthenticated) {
            goto('/login'); // Redirige al login si no está autenticado
        }
    }

    // Función para cerrar sesión
    function logout() {
        document.cookie = 'token=; Max-Age=0; path=/'; // Elimina la cookie del token
        isAuthenticated = false;
        goto('/login'); // Redirige al login
    }

    onMount(() => {
        checkAuthentication(); // Verifica la autenticación al cargar la página
    });
</script>

<svelte:head>
    <title>Dashboard</title>
</svelte:head>

<main class="min-h-screen flex flex-col justify-center items-center bg-gray-100 text-gray-900">
    {#if isAuthenticated}
        <h1 class="text-3xl font-bold mb-6">¡Bienvenido al Dashboard!</h1>
        <div class="mb-6 p-4 bg-white shadow-md rounded-lg border border-gray-300 max-w-lg w-full">
            <h2 class="text-xl font-semibold mb-2 text-gray-700">Tu token:</h2>
            <p class="text-sm font-mono bg-gray-100 p-2 rounded-md overflow-x-auto break-all text-gray-800">
                {token}
            </p>
        </div>
        <button on:click={logout} class="px-6 py-2 bg-red-500 text-white rounded-lg font-medium hover:bg-red-600 transition">
            Cerrar sesión
        </button>
    {:else}
        <p class="text-lg font-medium text-gray-700">Redirigiendo al inicio de sesión...</p>
    {/if}
</main>
