
<div id="back" class="absolute w-full h-full -z-50">

  <canvas id="canvas" class="absolute top-0 left-0 w-full h-full z-11"></canvas>
  <div class="backRight absolute right-0 w-1/2 h-full bg-sky-500"></div>
  <div class="backLeft absolute left-0 w-1/2 h-full bg-gray-400"></div>

</div>

<div id="slideBox" class="absolute left-1/2 w-1/2 h-full overflow-hidden shadow-xl">
  <div class="topLayer w-[200%] h-full relative -left-full flex">
    
    
    <div class="right w-1/2 h-full overflow-y-scroll bg-[#f9f9f9] absolute right-0 text-gray-800">

      <div class="content flex flex-col justify-center min-h-full w-4/5 mx-auto relative">

        <h2 class="text-blue-700 text-[2.6em] font-light mt-2 mb-1">Login</h2>

        <form id="form-login" method="post" on:submit="{(event) => event.preventDefault()}">

          <div class="form-element form-stack flex flex-col my-6">

            <label for="username-login" class="uppercase text-sm">Username</label>

            <input id="username-login" type="text" name="username" class="bg-transparent border-b border-gray-800 py-2 mt-1 outline-none" />

          </div>
          <div class="form-element form-stack flex flex-col my-6">

            <label for="password-login" class="uppercase text-sm">Password</label>

            <input id="password-login" type="password" name="password" class="bg-transparent border-b border-gray-800 py-2 mt-1 outline-none" />

          </div>

          <div class="form-element form-submit my-6">

            <button id="logIn" type="submit" class="bg-blue-700 hover:bg-blue-800 text-white font-semibold uppercase text-sm px-5 py-2 rounded shadow mr-3">
              Log In
            </button>
            
          </div>

        </form>

      </div>

    </div>

  </div>

</div>


  