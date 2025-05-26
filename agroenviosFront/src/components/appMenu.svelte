<script>
    import { goto } from '$app/navigation';

    let menuItems = [
        { name: 'Envíos', path: '/envios' }, // Admin y Supervisor
        { name: 'Mis envíos', path: '/misEnvios' }, // Admin y Proveedor
        { name: 'Dashboard', path: '/dashboard' }, // todos
    ];

    function navigateTo(path) {
        goto(path);
    }
</script>

<div class="flex">
    <!-- Sidebar -->
    <div class="w-64 h-screen bg-[color:var(--color-primary)] text-white flex flex-col">
        <div class="p-4">
            AgroEnvios
        </div>
        <nav class="flex-1 p-4">
            {#each menuItems as item}
                <button
                    on:click={() => navigateTo(item.path)}
                    class="w-full text-left px-4 py-2 mb-2 rounded hover:text-[color:var(--color-tertiary)] transition"
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


