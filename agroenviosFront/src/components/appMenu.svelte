<script>
    import { goto } from '$app/navigation';
    import { getRol } from '$lib/Usuario';

    let userRole = null;
    let menuItems = [];

    async function setMenuByRole() {
        try {
            const token = typeof window !== 'undefined' ? localStorage.getItem('token') : null;
            if (token) {
                userRole = await getRol(token);
            }
        } catch (e) {
            userRole = null;
        }
        if (userRole === 'Admin') {
            menuItems = [
                { name: 'Envíos', path: '/envios' },
                { name: 'Mis envíos', path: '/enviosPendientes' },
                { name: 'Mis envíos', path: '/misEnvios' },
                { name: 'Mis envíos', path: '/misEnviosAceptados' },
                { name: 'Mis envíos', path: '/misEnviosRechazados' },
                { name: 'Usuarios', path: '/usuarios' },
                { name: 'Dashboard', path: '/dashboard' }
            ];
        } else if (userRole === 'Supervisor') {
            menuItems = [
                { name: 'Envíos', path: '/envios' },
                { name: 'Mis envíos', path: '/enviosPendientes' },
                { name: 'Dashboard', path: '/dashboard' }
            ];
        } else if (userRole === 'Proveedor') {
            menuItems = [
                { name: 'Mis envíos', path: '/misEnvios' },
                { name: 'Mis envíos', path: '/misEnviosAceptados' },
                { name: 'Mis envíos', path: '/misEnviosRechazados' },
                { name: 'Dashboard', path: '/dashboard' }
            ];
        } else {
            menuItems = [
                { name: 'Dashboard', path: '/dashboard' }
            ];
        }
    }

    setMenuByRole();

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


