<script>
    import { getTodosLosUsuarios, crearUsuarioVacio } from '$lib/Usuario';
    import { checkAuthentication } from '$lib/misEnvios';
    import { onMount } from 'svelte';
    import AppMenu from '../../components/appMenu.svelte';
    import { goto } from '$app/navigation';

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

    async function handleAgregarUsuario() {

        try{
            const token = checkAuthentication();
            const usuario = await crearUsuarioVacio(token);
            goto(`/editarUsuario/${usuario.id}`);
        } catch (e) {
            error = e.message;
        }
    }

</script>

<div class="flex h-screen">
    <!-- Menú lateral -->
    <AppMenu />

    <!-- Contenido principal -->
    <main class="flex-1 bg-gray-100 p-6 overflow-auto">
        <div class="max-w-4xl mx-auto">
            <div class="flex items-center justify-between mb-6">
                <h1 class="text-2xl font-bold text-gray-800">Lista de Usuarios</h1>
                <button
                on:click={handleAgregarUsuario}
                class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded shadow transition-colors"
                >
                Agregar Usuario
            </button>            
        </div>

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
                                <!-- <th class="border border-gray-300 px-4 py-2">Organización</th> -->
                                <th class="border border-gray-300 px-4 py-2">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {#each usuarios as u}
                                <tr class="hover:bg-gray-50">
                                    <td class="border border-gray-300 px-4 py-2">{u.nombre}</td>
                                    <td class="border border-gray-300 px-4 py-2">{u.apellido}</td>
                                    <!-- <td class="border border-gray-300 px-4 py-2">{u.organizacion?.nombre || '—'}</td> -->
                                     <td class="border border-gray-300 px-4 py-2">{u.roles}</td>
                                    <td class="border border-gray-300 px-4 py-2">
                                        <a href={`/editarUsuario/${u.id}`} class="bg-blue-600 hover:bg-blue-700 text-white px-3 py-1 rounded text-sm mr-2 transition-colors">Editar</a>
                                    </td>
                                </tr>
                            {/each}
                        </tbody>
                    </table>
                </div>
            {/if}
        </div>
    </main>
</div>
