### STAGE 1: Build ###
FROM node:12.7-alpine AS build
WORKDIR /usr/src/app
COPY AngularApp/package.json AngularApp/package-lock.json ./
RUN npm install
RUN npm build
COPY AngularApp/. .
 
### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/AngularApp/. /usr/share/nginx/html/.
