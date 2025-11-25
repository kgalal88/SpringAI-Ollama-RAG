FROM eclipse-temurin:17-jdk-jammy

# 1. Copy the certificate file into the container
#COPY http_ca.crt /usr/local/share/ca-certificates/http_ca.crt

# 2. Set the default keystore password
# The default password for the Java 'cacerts' truststore is 'changeit'
#ENV KEYSTORE_PASS="changeit"

#RUN keytool -import \
#    -alias elastic-cert \
#    -file /usr/local/share/ca-certificates/http_ca.crt \
#    -keystore $JAVA_HOME/lib/security/cacerts \
#    -storepass ${KEYSTORE_PASS} \
#    -noprompt \
#    -trustcacerts

COPY target/rag.article-0.0.1.jar /home/

ENV OLLAMA_URL="http://host.docker.internal:11434"
ENV ELASTIC_URL="http://elasticsearch:9200"

CMD ["java", "-jar", "/home/rag.article-0.0.1.jar"]
#CMD ["sleep", "1000"]
EXPOSE 8080