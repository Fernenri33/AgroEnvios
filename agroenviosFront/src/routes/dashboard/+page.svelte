<script>
    import { onMount } from 'svelte';
    import { checkAuthentication, logout } from '$lib/dashboard';
    import { getUsuarioActual } from '$lib/Usuario';
    import AppMenu from '../../components/appMenu.svelte';

    let token = '';
    let isAuthenticated = false;
    let usuario = null;

    onMount(async () => {
        const auth = checkAuthentication();
        token = auth.token;
        isAuthenticated = auth.isAuthenticated;
        if (isAuthenticated) {
            try {
                usuario = await getUsuarioActual(token);
            } catch (e) {
                usuario = null;
            }
        }
    });
</script>

<svelte:head>
    <title>Dashboard</title>
</svelte:head>

<main class="min-h-screen flex flex-col justify-center items-center bg-gray-100 text-gray-900">
    {#if isAuthenticated}
        <h1 class="text-3xl font-bold mb-6">
            {#if usuario}
                ¡Hola {usuario.nombre}!
            {:else}
                ¡Bienvenido al Dashboard!
            {/if}
        </h1>
        <button on:click={logout} class="px-6 py-2 bg-red-500 text-white rounded-lg font-medium hover:bg-red-600 transition">
            Cerrar sesión
        </button>
    {:else}
        <p class="text-lg font-medium text-gray-700">Redirigiendo al inicio de sesión...</p>
    {/if}
</main>