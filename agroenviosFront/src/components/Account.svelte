<div class="bg-[#13232f]/90 p-10 max-w-xl mx-auto mt-10 rounded-md shadow-lg shadow-[#13232f]/30">
    <!-- Tabs -->
    <ul class="flex mb-10">
      <li class="w-1/2">
        <a href="#signup" class="block text-center py-3 text-lg bg-[#1ab188] text-white transition hover:bg-[#179f76]">Sign Up</a>
      </li>
      <li class="w-1/2">
        <a href="#login" class="block text-center py-3 text-lg bg-gray-400/25 text-gray-400 transition hover:bg-[#148a66] hover:text-white">Log In</a>
      </li>
    </ul>
  
    <!-- Content -->
    <div>
      <!-- Sign Up -->
      <div id="signup">
        <h1 class="text-center text-white font-light text-3xl mb-10">Sign Up for Free</h1>
        <form action="/" method="post">
          <div class="flex gap-4 mb-10">
            <div class="relative w-1/2">
              <label class="absolute text-white/50 left-3 top-1.5 text-lg">First Name<span class="text-[#1ab188] ml-1">*</span></label>
              <input type="text" required autocomplete="off"
                class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
            </div>
            <div class="relative w-1/2">
              <label class="absolute text-white/50 left-3 top-1.5 text-lg">Last Name<span class="text-[#1ab188] ml-1">*</span></label>
              <input type="text" required autocomplete="off"
                class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
            </div>
          </div>
  
          <div class="relative mb-10">
            <label class="absolute text-white/50 left-3 top-1.5 text-lg">Email Address<span class="text-[#1ab188] ml-1">*</span></label>
            <input type="email" required autocomplete="off"
              class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
          </div>
  
          <div class="relative mb-10">
            <label class="absolute text-white/50 left-3 top-1.5 text-lg">Set A Password<span class="text-[#1ab188] ml-1">*</span></label>
            <input type="password" required autocomplete="off"
              class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
          </div>
  
          <button type="submit"
            class="w-full bg-[#1ab188] text-white uppercase font-semibold tracking-wide text-2xl py-4 transition hover:bg-[#179f76]">Get
            Started</button>
        </form>
      </div>
  
      <!-- Log In -->
      <div id="login" class="hidden">
        <h1 class="text-center text-white font-light text-3xl mb-10">Welcome Back!</h1>
        <form action="/" method="post">
          <div class="relative mb-10">
            <label class="absolute text-white/50 left-3 top-1.5 text-lg">Email Address<span class="text-[#1ab188] ml-1">*</span></label>
            <input type="email" required autocomplete="off"
              class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
          </div>
  
          <div class="relative mb-6">
            <label class="absolute text-white/50 left-3 top-1.5 text-lg">Password<span class="text-[#1ab188] ml-1">*</span></label>
            <input type="password" required autocomplete="off"
              class="pt-8 pb-2 px-3 w-full bg-transparent border border-gray-400 text-white text-xl focus:outline-none focus:border-[#1ab188]" />
          </div>
  
          <p class="text-right mb-6">
            <a href="#" class="text-[#1ab188] hover:text-[#179f76] transition">Forgot Password?</a>
          </p>
  
          <button
            class="w-full bg-[#1ab188] text-white uppercase font-semibold tracking-wide text-2xl py-4 transition hover:bg-[#179f76]">Log
            In</button>
        </form>
      </div>
    </div>
  </div>
  