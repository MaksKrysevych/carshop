# Вибір базового образу
FROM nginx:alpine

# Копіювання файлів у контейнер
COPY ./src/main/resources/templates/carShop.html /usr/share/nginx/html/
COPY ./src/main/resources/templates/catalog.html /usr/share/nginx/html/

# Визначення порту
EXPOSE 80
