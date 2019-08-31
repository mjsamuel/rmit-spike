# import base image, node v12.8.0
FROM node:12.8.0-alpine

# set the working directory (inside the container)
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH (inside container)
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package.json /app/package.json
RUN npm install

# start application
CMD ["npm", "start"]