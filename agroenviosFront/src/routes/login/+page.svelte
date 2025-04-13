<script>
    import Navbar from '../../components/Navbar.svelte';
    import Footer from '../../components/Footer.svelte';
    import { goto } from '$app/navigation';
    import { onMount } from 'svelte';
    
    // Variables para el formulario de inicio de sesión, en caso de que la pagina use otras variables agregarlas
    let email = '';
    let password = '';
    let error = '';
    let loading = false;

    async function loginUser() {
    error = '';
    loading = true;

    try {
        const response = await fetch('http://localhost:8080/api/login', {
            // En el futuro vamos a usar el token en el header de las peticiones
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        // Intentamos parsear la respuesta como JSON, incluso si hay error
        const data = await response.json().catch(() => ({}));

        if (!response.ok) {
            // Error personalizado enviado desde el backend
            const mensaje = data.error || data.message || 'Error al iniciar sesión';
            throw new Error(mensaje);
        }

        // Éxito: guardar token y redirigir
        document.cookie = `token=${data.token}; path=/; max-age=86400`;
        goto('/dashboard');

    } catch (err) {
        error = err.message || 'Ocurrió un error inesperado';
    } finally {
        loading = false;
    }
}

</script>

<svelte:head>
    <title>Login</title>
    <meta name="Página de inicio de sesión" content="Inicie su sesión" />
</svelte:head>

<Navbar />

<main class="flex flex-col items-center justify-center h-120 p-4 bg-[color:var(--color-background)]">
    <div class="w-full max-w-md bg-[color:var(--color-primary)] p-6 rounded-lg shadow-md text-[color:var(--color-font)] ">
        <h1 class="text-2xl font-semibold mb-4 text-center font-sans">Iniciar sesión</h1>

        {#if error}
            <p class="text-[color:var(--color-tertiary)] text-sm mb-4">{error}</p>
        {/if}

        <form on:submit|preventDefault={loginUser} class="space-y-4">
            <div>
                <label for="email" class="block text-sm font-medium">Correo electrónico</label>
                <input id="email" type="email" bind:value={email} class="w-full px-4 py-2 border rounded-md" required />
            </div>

            <div>
                <label for="password" class="block text-sm font-medium">Contraseña</label>
                <input id="password" type="password" bind:value={password} class="w-full px-4 py-2 border rounded-md" required />
            </div>

            <button type="submit" class="w-full py-2 bg-[color:var(--color-tertiary)] text-[color:var(--color-primary)] rounded-md font-bold
                                        hover:bg-[color:var(--color-font)]" disabled={loading}>
                {#if loading}
                    Iniciando...
                {:else}
                    Iniciar sesión
                {/if}
            </button>
        </form>
    </div>
</main>

<Footer />
