## ---------- Build Stage ----------
#FROM maven:3.9-eclipse-temurin-21 AS build
#WORKDIR /app
#
## فقط pom.xml رو اول کپی کن
#COPY pom.xml .
#
## دانلود dependency ها (این لایه کش میشه)
#RUN mvn dependency:go-offline -B
#
## حالا سورس رو کپی کن
#COPY src ./src
#
## build نهایی
#RUN mvn clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar

COPY ./target/reviewForAny-0.0.1-SNAPSHOT.jar /app


ENTRYPOINT ["java","-Xms64m","-Xmx128m","-XX:+UseSerialGC","-jar","app.jar"]
