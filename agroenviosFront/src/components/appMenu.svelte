<script>
    import { goto } from '$app/navigation';
    import { onMount } from 'svelte';
    import { getUsuarioActual } from '$lib/Usuario';
    import { checkAuthentication } from '$lib/misEnvios';

    let usuario = null;
    let menuItems = [
        { name: 'Envíos', path: '/envios', roles: ['Admin', 'Supervisor'] },
        { name: 'Mis envíos', path: '/misEnvios', roles: ['Admin', 'Proveedor'] },
        { name: 'Usuarios', path: '/usuarios', roles: ['Admin'] },
        { name: 'Dashboard', path: '/dashboard', roles: ['Admin', 'Proveedor', 'Supervisor'] },
    ];
    let filteredMenu = [];

    onMount(async () => {
        try {
            const token = checkAuthentication();
            usuario = await getUsuarioActual(token);
            filteredMenu = menuItems.filter(item =>
                !item.roles || item.roles.includes(usuario.rol)
            );
        } catch (e) {
            filteredMenu = menuItems.filter(item => item.name === 'Dashboard');
        }
    });

    function navigateTo(path) {
        goto(path);
    }
</script>

<div class="flex">
    <!-- Sidebar -->
    <div class="w-64 h-screen bg-[color:var(--color-primary)] text-white flex flex-col">
        <div class="p-4 font-bold text-lg tracking-wide">
            AgroEnvios
        </div>
        <nav class="flex-1 p-4">
            {#each filteredMenu as item}
                <button
                    on:click={() => navigateTo(item.path)}
                    class="w-full text-left px-4 py-2 mb-2 rounded hover:text-[color:var(--color-terciary)] transition"
                >
                    {item.name}
                </button>
            {/each}
        </nav>
    </div>

    <!-- Main Content -->
    <div class="flex-1">
        <slot />
    </div>
</div>


