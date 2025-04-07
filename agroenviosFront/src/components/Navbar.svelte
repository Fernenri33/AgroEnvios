<script src="./lib/menu.js" defer>
  

</script>




<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.cdnfonts.com/css/gotham" rel="stylesheet">
    <style>
        body {
            font-family: "Gotham", sans-serif;
            background-color: #f8f9fa;
        }
    </style>
</head>
  <nav class="relative w-full  bg-green-300 p-4 shadow-md">
      <div class="container mx-auto max-w-[90%] flex items-center justify-between py-3 relative z-10" style="margin-left: 65px; ">
          <!-- Logo -->
          <div>
              <h1 class="text-4xl font-bold text-black hover:text-red-400">AgroEnvios 🌱</h1>
          </div>

         

          <!-- Menú derecho -->
          <div class="hidden md:flex">
              <ul class="menu-list flex gap-6 text-lg font-medium">
                  <li><a href="#" class="text-black hover:text-red-400">Shop</a></li>
                  <li><a href="#"  class="text-black hover:text-red-400">Account</a></li>
                  <li><a href="#" class="text-black hover:text-red-400">Menu</a></li>
              </ul>
          </div>
      </div>

      <!-- Backdrop para el efecto -->
      <div class="menu-backdrop absolute bg-gray-200 rounded-lg transition-all duration-300 opacity-0 invisible"></div>
  </nav>
  