<script>
    import { onMount } from 'svelte';
    import { page } from '$app/stores';
    import { checkAuthentication } from '$lib/misEnvios';
    import { getUsuarioPorId, actualizarUsuario } from '$lib/Usuario'; // Asegúrate de tener estas funciones

    $: usuarioId = $page.params.id;

    let usuario = {
        nombre: '',
        apellido: '',
        direccion: '',
        email: '',
        telefono: '',
        organizacion: { nombre: '' }
    };
    let error = '';
    let mensaje = '';
    let nuevaPassword = '';
    let showPassword = false;

    onMount(async () => {
        try {
            const token = checkAuthentication();
            const data = await getUsuarioPorId(token, usuarioId); // Corrige el orden de argumentos
            usuario = data;
        } catch (e) {
            error = e.message;
        }
    });

    async function guardarCambios() {
        try {
            const token = checkAuthentication();
            await actualizarUsuario(params.id, usuario, token); // El orden aquí está bien
            mensaje = 'Cambios guardados correctamente.';
            error = '';
        } catch (e) {
            error = e.message;
            mensaje = '';
        }
    }

    function generarPassword() {
        const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$!';
        nuevaPassword = Array.from({ length: 12 }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
        showPassword = true;
    }
</script>

<div class="flex h-screen">
    <!-- Menú lateral -->

    <!-- Contenido principal -->
    <main class="flex-1 bg-gray-100 p-6 overflow-auto">
        <div class="max-w-3xl mx-auto bg-white p-6 rounded shadow">
            <h1 class="text-2xl font-bold mb-6 text-gray-800">Editar Usuario</h1>

            {#if error}
                <p class="text-red-600 mb-4">{error}</p>
            {/if}
            {#if mensaje}
                <p class="text-green-600 mb-4">{mensaje}</p>
            {/if}

            <form on:submit|preventDefault={guardarCambios} class="space-y-4">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Nombre</label>
                        <input bind:value={usuario.nombre} type="text" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Apellido</label>
                        <input bind:value={usuario.apellido} type="text" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Email</label>
                        <input bind:value={usuario.email} type="email" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Teléfono</label>
                        <input bind:value={usuario.telefono} type="text" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700">Dirección</label>
                        <input bind:value={usuario.direccion} type="text" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700">Organización</label>
                        <input bind:value={usuario.organizacion.nombre} type="text" class="w-full mt-1 px-3 py-2 border rounded" />
                    </div>
                </div>

                <div class="flex items-center gap-4 pt-4">
                    <button
                        type="submit"
                        class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded shadow"
                    >
                        Guardar Cambios
                    </button>

                    <button
                        type="button"
                        class="bg-yellow-500 hover:bg-yellow-600 text-white px-4 py-2 rounded shadow"
                        on:click={generarPassword}
                    >
                        Cambiar Contraseña
                    </button>
                </div>

                {#if showPassword}
                    <div class="mt-4 bg-gray-100 border border-gray-300 rounded p-3">
                        <p class="text-sm text-gray-700 font-medium">Nueva contraseña generada:</p>
                        <code class="text-blue-700 font-mono">{nuevaPassword}</code>
                    </div>
                {/if}
            </form>
        </div>
    </main>
</div>