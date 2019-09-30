# import base image, node v12.8.0
FROM node:12.8.0-alpine

ARG API_HOST_VAR
ENV API_HOST=${API_HOST_VAR}

# set the working directory (inside the container)
WORKDIR /app

# Install app dependencies
COPY package.json .

RUN npm install

# Copy the current directory contents into the container at /app
COPY . /app

RUN ./install.env.sh

# Make port 8080 available to the world outside this container
EXPOSE 8080

# start application
CMD ["npm", "start"]