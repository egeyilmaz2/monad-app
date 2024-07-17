# My Modular Project

## Proje Açıklaması

Bu proje, modüler bir mimari kullanarak geliştirilmiş bir backend ve frontend uygulamasıdır. Backend uygulaması Spring Boot kullanılarak geliştirilmiştir ve PostgreSQL veritabanı kullanmaktadır. Frontend uygulaması ise Vaadin kullanarak geliştirilmiştir. Proje Docker kullanılarak konteynerize edilmiştir.

## Gereksinimler

- [Docker](https://www.docker.com/products/docker-desktop) (Docker Desktop)
- [Docker Compose](https://docs.docker.com/compose/)

## Kurulum ve Çalıştırma
### Docker kullanmadan Ayaklandırma
1. Proje kök dizinine gidin:

    ```sh
    cd monad-app
    ```

2. `conteiner_ile_ayaklandır.sh` scriptini çalıştırarak Docker container'larını ayaklandırın:

    ```sh
    chmod +x dev_ortamı_ayakalndırma.sh
    ./dev_ortamı_ayakalndırma.sh
    ```

### Docker ile Ayaklandırma

1. Proje kök dizinine gidin:

    ```sh
    cd monad-app
    ```

2. `conteiner_ile_ayaklandır.sh` scriptini çalıştırarak Docker container'larını ayaklandırın:

    ```sh
    chmod +x conteiner_ile_ayaklandır.sh
    ./conteiner_ile_ayaklandır.sh
    ```

3. Uygulamaların çalıştığından emin olun:
    - Backend: [http://localhost:4000](http://localhost:4000)
    - Frontend: [http://localhost:4001](http://localhost:4001)

## Veritabanı

Proje, PostgreSQL veritabanı kullanmaktadır. Veritabanı Docker Compose ile otomatik olarak ayaklandırılır ve gerekli yapılandırmalar `docker-compose.yml` dosyasında tanımlanmıştır.

### Docker Compose Dosyası

```yaml
version: '3.8'

services:
  backend:
    image: backend-app:latest
    build:
      context: ./backend/main-app
    ports:
      - "4000:4000"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/mydatabase
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
    networks:
      - app-network
    depends_on:
      - db

  frontend:
    image: frontend-app:latest
    build:
      context: ./frontend
    ports:
      - "4001:4001"
    networks:
      - app-network

  db:
    image: postgres:latest
    environment:
      POSTGRES_DB: mydatabase
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5433:5432"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
