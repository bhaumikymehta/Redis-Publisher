FROM eclipse-temurin:17 

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17

WORKDIR /app

COPY --from=0 /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]