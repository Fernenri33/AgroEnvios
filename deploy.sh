#!/bin/bash

# Ruta raíz del proyecto clonado
PROJECT_DIR="/opt/miapp/AgroEnvios"

# Rutas específicas
API_DIR="$PROJECT_DIR/apiAE"
FRONT_DIR="$PROJECT_DIR/agoenvosFront"

# 1. Navegar a la carpeta del proyecto y hacer pull
echo "Actualizando repositorio desde Git..."
cd "$PROJECT_DIR"
git pull

# 2. Backend: compilar y reiniciar el servicio
echo "Compilando API Spring Boot..."
cd "$API_DIR"
./mvnw clean package -DskipTests

echo "Reiniciando API..."
API_PID=$(ps aux | grep 'apiAE-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{print $2}')
if [ -n "$API_PID" ]; then
    kill "$API_PID"
    sleep 2
fi
nohup java -jar target/apiAE-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > api.log 2>&1 &

# 3. Frontend: compilar y reiniciar con pm2
echo "🛠 Compilando Frontend Svelte..."
cd "$FRONT_DIR"
npm install
npm run build

echo "Reiniciando Frontend con PM2..."
pm2 restart agroenvios-front

echo "Despliegue completado con éxito."
