FROM maven:3.8.5-openjdk-17-slim
RUN mkdir -p /root/backend && mkdir /root/.m2
COPY . /root/backend
WORKDIR /root/backend
ENV TZ=America/Fortaleza
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mvn clean package -DskipTests
RUN mv target/*.jar target/app.jar
ENTRYPOINT ["java", "-jar", "target/app.jar"]
