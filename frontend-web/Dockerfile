FROM node:20-alpine AS build
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /usr/src/app/dist/intranet-news-platform/browser /usr/share/nginx/html
COPY ./entrypoint.sh /usr/local/bin/entrypoint.sh
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
RUN chmod +x /usr/local/bin/entrypoint.sh
ENV APIURL=http://APIURLNOTSET
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
