<script>
  let menuOpen = false;
</script>

<nav class="bg-[color:var(--color-background)] text-[color:var(--color-primary)] px-6 md:px-10 py-4 flex justify-between items-center relative">
  <!-- Logo -->
  <div class="text-2xl font-bold tracking-wide font-sans">
    <a href="/">AgroEnvios</a>
  </div>

  <!-- Botón hamburguesa -->
  <button class="md:hidden text-2xl focus:outline-none" on:click={() => menuOpen = !menuOpen}>
    ☰
  </button>

  <!-- Menú -->
  <div class={`absolute top-full left-0 w-full bg-[color:var(--color-background)] md:static md:flex md:w-auto md:space-x-10 font-bold font-serif transition-all duration-300 ease-in-out ${menuOpen ? 'block' : 'hidden'}`}>
    <a href="http://localhost:5173/#home" class="block px-6 py-2 md:px-0 md:py-0 hover:text-[color:var(--color-secondary)] transition">Inicio</a>
    <a href="http://localhost:5173" class="block px-6 py-2 md:px-0 md:py-0 hover:text-[color:var(--color-secondary)] transition">Servicios</a>
    <a href="http://localhost:5173" class="block px-6 py-2 md:px-0 md:py-0 hover:text-[color:var(--color-secondary)] transition">Sobre Nosotros</a>
    <a href="http://localhost:5173" class="block px-6 py-2 md:px-0 md:py-0 hover:text-[color:var(--color-secondary)] transition">Contáctanos</a>
    <a href="/login" class="block px-6 py-2 md:px-0 md:py-0 hover:text-[color:var(--color-secondary)] transition">Iniciar Sesión</a>
  </div>
</nav>
