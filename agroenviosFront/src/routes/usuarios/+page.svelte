<script>
import { getTodosLosUsuarios } from '$lib/Usuario';
import { checkAuthentication } from '$lib/misEnvios';
import { onMount } from 'svelte';

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

<main class="max-w-2xl mx-auto mt-8 p-6 bg-white rounded shadow">
    <h1 class="text-2xl font-bold mb-4">Usuarios</h1>
    {#if loading}
        <div>Cargando...</div>
    {:else if error}
        <div class="text-red-600">{error}</div>
    {:else}
        <table class="table-auto w-full border-collapse border border-gray-200">
            <thead>
                <tr class="bg-gray-100">
                    <th class="border px-4 py-2">Nombre</th>
                    <th class="border px-4 py-2">Apellido</th>
                    <th class="border px-4 py-2">Organización</th>
                </tr>
            </thead>
            <tbody>
                {#each usuarios as u}
                    <tr>
                        <td class="border px-4 py-2">{u.nombre}</td>
                        <td class="border px-4 py-2">{u.apellido}</td>
                        <td class="border px-4 py-2">{u.organizacion?.nombre || ''}</td>
                    </tr>
                {/each}
            </tbody>
        </table>
    {/if}
</main>
