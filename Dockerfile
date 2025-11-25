FROM eclipse-temurin:17-jdk-jammy

COPY target/rag.article-0.0.1.jar /home/

ENV OLLAMA_URL="http://host.docker.internal:11434"
ENV ELASTIC_URL="http://elasticsearch:9200"

CMD ["java", "-jar", "/home/rag.article-0.0.1.jar"]
#CMD ["sleep", "1000"]
EXPOSE 8080