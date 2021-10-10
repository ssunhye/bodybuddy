FROM nginx:stable-alpine

COPY ./conf.d/app.conf  /etc/nginx/conf.d/app.conf
COPY ./conf.d/nginx.conf  /etc/nginx/nginx.conf

VOLUME ["/data", "/etc/nginx", "/var/log/nginx"]

WORKDIR /etc/nginx

CMD ["nginx"]

EXPOSE 80

