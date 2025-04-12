# Stage 1: Build
FROM openjdk:17-slim AS build_stage

# work dir
WORKDIR /spring_security

# copy the maven file
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# build the application
RUN ./mvnw install -DskipTests

# Extract the layers from the built JAR for better caching
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage 2: Run
FROM openjdk:17-slim
VOLUME /tmp

ARG DEPENDENCY=/spring_security/target/dependency

# copy from build_stage
COPY --from=build_stage ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build_stage ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build_stage ${DEPENDENCY}/BOOT-INF/classes /app

#Set defaul JVM options
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70.0"

# Run by non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Run application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.ignius.spring_security.SpringSecurityApplication"]

