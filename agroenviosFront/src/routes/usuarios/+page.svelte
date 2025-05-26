<script>
    import { getTodosLosUsuarios } from '$lib/Usuario';
    import { checkAuthentication } from '$lib/misEnvios';
    import { onMount } from 'svelte';
    import AppMenu from '../../components/appMenu.svelte';

    let usuarios = [];
    let error = '';
    let loading = true;

    onMount(async () => {
        try {
            const token = checkAuthentication();
            usuarios = await getTodosLosUsuarios(token);
        } catch (e) {
            error = e.message;
        } finally {
            loading = false;
        }
    });
</script>

<div class="flex h-screen">
    <!-- Menú lateral -->
    <AppMenu />

    <!-- Contenido principal -->
    <main class="flex-1 bg-gray-100 p-6 overflow-auto">
        <div class="max-w-4xl mx-auto">
            <h1 class="text-2xl font-bold mb-6 text-gray-800">Lista de Usuarios</h1>

            {#if loading}
                <p class="text-gray-600">Cargando usuarios...</p>
            {:else if error}
                <p class="text-red-600 font-semibold">{error}</p>
            {:else if usuarios.length === 0}
                <p class="text-gray-700">No hay usuarios registrados.</p>
            {:else}
                <div class="bg-white shadow-md rounded p-4">
                    <table class="table-auto w-full border-collapse border border-gray-200">
                        <thead>
                            <tr class="bg-gray-100 text-left">
                                <th class="border border-gray-300 px-4 py-2">Nombre</th>
                                <th class="border border-gray-300 px-4 py-2">Apellido</th>
                                <th class="border border-gray-300 px-4 py-2">Organización</th>
                            </tr>
                        </thead>
                        <tbody>
                            {#each usuarios as u}
                                <tr class="hover:bg-gray-50">
                                    <td class="border border-gray-300 px-4 py-2">{u.nombre}</td>
                                    <td class="border border-gray-300 px-4 py-2">{u.apellido}</td>
                                    <td class="border border-gray-300 px-4 py-2">{u.organizacion?.nombre || '—'}</td>
                                </tr>
                            {/each}
                        </tbody>
                    </table>
                </div>
            {/if}
        </div>
    </main>
</div>
