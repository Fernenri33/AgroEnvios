<script>
    
    import Footer from '../components/Footer.svelte';
    import Hero from '../components/Hero.svelte';
    import Navbar from '../components/Navbar.svelte';
  </script>
  
  <svelte:head>
    <title>Mi Página de Inicio</title>
    <meta name="description" content="Bienvenido a la página construida con Svelte y Tailwind CSS." />
  </svelte:head>

  
  
  <Navbar />
  
  <Hero />
  
  <main class="p-6 text-center">
    <h2 class="text-2xl font-bold mb-4">¿Qué ofrecemos?</h2>
    <p class="text-gray-700 mb-4">
      Aprende a construir aplicaciones modernas, rápidas y elegantes usando Svelte.
    </p>
    
    <a href="#mas-info" class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition">
      Ver más
    </a>

   
  </main>

  
  <section id="mas-info" class="bg-gray-100 py-16">
    <div class="container mx-auto text-center">
      <h2 class="text-3xl font-bold mb-4">Más información</h2>
      <p class="text-lg text-gray-600">
        Aquí puedes agregar más detalles sobre tu proyecto o servicio. ¡Hazlo atractivo para los usuarios!
      </p>
    </div>
  </section>
  
  <Footer />
  
  