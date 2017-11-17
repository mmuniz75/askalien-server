FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD /target/askalien-server-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


#docker run --name askalien-server -d -p 8080:8080 -e "SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/mythidb" -e "SPRING_DATASOURCE_USERNAME=" -e "SPRING_DATASOURCE_PASSWORD=" -e "SPRING_JPA_HIBERNATE_DDL_AUTO=none" -e "SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect" -e "LUCENE_INDEX_DIR=/FILES_INDEXING" --link postgres:postgres -v /home/marcelo/work/mythidb-lucene/FILES_INDEXING:/FILES_INDEXING mmuniz/askalien:server


