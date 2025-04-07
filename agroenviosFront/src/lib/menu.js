document.addEventListener("DOMContentLoaded", function () {
  const listItem = document.querySelectorAll(".menu-list li");
  const menuDrop = document.querySelector(".menu-backdrop");
  console.log("menu.js cargado correctamente");

  if (!menuDrop) {
    console.error("No se encontró el elemento con la clase 'menu-backdrop'.");
    return;
  }

  listItem.forEach((e) => {
    e.addEventListener("mouseenter", () => {
      const rect = e.getBoundingClientRect();
      const padding = -4; // Ajuste para reducir el tamaño del backdrop<

      menuDrop.style.setProperty("top", `${rect.top + padding}px`);
      menuDrop.style.setProperty("left", `${rect.left + padding}px`);
      menuDrop.style.setProperty("height", `${e.clientHeight - padding * 2}px`);
      menuDrop.style.setProperty("width", `${e.clientWidth - padding * 2}px`);
      menuDrop.style.setProperty("opacity", "1");
      menuDrop.style.setProperty("visibility", "visible");
    });

    e.addEventListener("mouseleave", () => {
      menuDrop.style.setProperty("opacity", "0");
      menuDrop.style.setProperty("visibility", "hidden");
    });
  });
});
