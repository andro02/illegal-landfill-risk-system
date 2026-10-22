# ---- build: install model and kjar, then package the service ----
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /src

# modules are built in dependency order: model -> kjar -> service
COPY model model
RUN mvn -q -f model/pom.xml -DskipTests install

COPY kjar kjar
RUN mvn -q -f kjar/pom.xml -DskipTests install

COPY service service
RUN mvn -q -f service/pom.xml -DskipTests package

# ---- run: Java runtime only ----
FROM eclipse-temurin:11-jre
WORKDIR /app

# unpack the Spring Boot jar so every dependency (including the kjar with the
# Drools rules) is a regular jar on the classpath instead of a nested one
COPY --from=build /src/service/target/service-*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract --destination layers \
    && cp -r layers/*/. . \
    && rm -rf layers app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=60", "org.springframework.boot.loader.JarLauncher"]
