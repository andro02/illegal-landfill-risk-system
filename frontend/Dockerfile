# Build context is the repository root (see docker-compose.yml)

# ---- build the React application ----
FROM node:20-alpine AS build
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json ./
RUN npm ci
COPY frontend/ ./
# source maps aren't needed in production and take most of the build's memory
ENV GENERATE_SOURCEMAP=false NODE_OPTIONS=--max-old-space-size=1400
RUN npm run build

# ---- serve it with Caddy, which also proxies the backend ----
FROM caddy:2-alpine
COPY deploy/Caddyfile /etc/caddy/Caddyfile
COPY --from=build /app/build /srv/app
