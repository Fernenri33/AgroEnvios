<main class="flex-1 bg-gray-100 p-6 min-h-screen">
    <h1 class="text-2xl font-bold mb-4">Editar Envío</h1>

    <div class="bg-white shadow-md rounded p-4 mb-6">
        <div class="mb-4">
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block font-semibold mb-1">Comentario proveedor</label>
            <input class="w-full border rounded px-3 py-2" name="comentarioProveedor" />
        </div>
        <div class="mb-4">
            <label class="block font-semibold mb-1" for="motorista">Motorista</label>
            <input id="motorista" class="w-full border rounded px-3 py-2" name="motorista" />
        </div>
        <button class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
            Guardar Envío
        </button>
    </div>

    <div class="bg-white shadow-md rounded p-4">
        <h2 class="text-xl font-semibold mb-4">Detalles del Envío</h2>
        <table class="table-auto w-full border-collapse border border-gray-200 mb-4">
            <thead>
                <tr class="bg-gray-100">
                    <th class="border border-gray-300 px-4 py-2">Producto</th>
                    <th class="border border-gray-300 px-4 py-2">Cantidad</th>
                    <th class="border border-gray-300 px-4 py-2">Unidad de medida</th>
                    <th class="border border-gray-300 px-4 py-2">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Ejemplo de fila de detalle existente -->
                <tr>
                    <td class="border border-gray-300 px-4 py-2">
                        <input class="w-full border rounded px-2 py-1" name="producto" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <input type="number" min="1" class="w-20 border rounded px-2 py-1" name="cantidad" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <input class="w-full border rounded px-2 py-1" name="unidad" placeholder="Unidad de medida" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <button class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded text-sm">
                            Eliminar
                        </button>
                    </td>
                </tr>
                <!-- Fila para agregar nuevo detalle -->
                <tr>
                    <td class="border border-gray-300 px-4 py-2">
                        <input class="w-full border rounded px-2 py-1" placeholder="Producto" name="nuevoProducto" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <input type="number" min="1" class="w-20 border rounded px-2 py-1" placeholder="Cantidad" name="nuevaCantidad" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <input class="w-full border rounded px-2 py-1" placeholder="Unidad de medida" name="nuevaUnidad" />
                    </td>
                    <td class="border border-gray-300 px-4 py-2">
                        <button class="bg-blue-600 hover:bg-blue-700 text-white px-3 py-1 rounded text-sm">
                            Agregar
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>