# Estágio 1: Build
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Estágio 2: Runtime (Nginx)
FROM nginx:alpine
COPY --from=build /app/dist/todo-web/browser /usr/share/nginx/html
EXPOSE 80
