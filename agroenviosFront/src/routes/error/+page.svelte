<script>
     import Navbar from '../../components/Navbar.svelte';
     import Footer from '../../components/Footer.svelte';
</script>

<svelte:head>
    <title>Error</title>
    <meta name="Página para errores" content="Errores" />
</svelte:head>

<Navbar />

<main class="flex flex-col items-center justify-center h-120 p-4 bg-[color:var(--color-background)]">
    <div class="w-full max-w-md bg-[color:var(--color-primary)] p-6 rounded-lg shadow-md text-[color:var(--color-font)] ">
        <h1 class="text-2xl font-semibold mb-4 text-center font-sans">Error</h1>

        <p class="text-[color:var(--color-tertiary)] text-sm mb-4">Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.</p>
    </div>
</main>

<Footer />