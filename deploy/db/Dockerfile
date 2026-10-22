# PostgreSQL with PostGIS, built on the official multi-architecture postgres
# image so the same setup runs on x86 machines and ARM servers
ARG PG_MAJOR=17
FROM postgres:${PG_MAJOR}-bookworm

ARG PG_MAJOR
RUN apt-get update \
    && apt-get install -y --no-install-recommends postgresql-${PG_MAJOR}-postgis-3 \
    && rm -rf /var/lib/apt/lists/*

# runs once, when the database volume is created for the first time
COPY restore-dump.sh /docker-entrypoint-initdb.d/
RUN sed -i 's/\r$//' /docker-entrypoint-initdb.d/restore-dump.sh \
    && chmod +x /docker-entrypoint-initdb.d/restore-dump.sh
