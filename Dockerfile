FROM goodforgod/debian-jdk10-oracle:sid

ARG PROJECT_DIR=oracle-aggregator
ARG JAR=oracle-aggregator-*.jar
ARG GRADLE=gradle-4.10.2
ARG OJDBC=ojdbc7-12.1.0.2.jar

# Install curl & unzip
RUN apt-get -q update && \
    apt-get -qy install curl && \
    apt-get -qy install wget && \
    apt-get -qy install unzip

# Install gradle
RUN mkdir /opt/gradle && \
    mkdir /$PROJECT_DIR && \
    cd /opt/gradle && \
    curl -L "https://services.gradle.org/distributions/$GRADLE-bin.zip" -o "$GRADLE-bin.zip" && \
    unzip -q "$GRADLE-bin.zip" && \
    rm "$GRADLE-bin.zip"

# Enviroment variables for DB connection (default)
# https://hub.docker.com/_/oracle-database-enterprise-edition
ENV GRADLE_HOME=/opt/gradle/$GRADLE \
    PATH=$PATH:/opt/gradle/$GRADLE/bin

COPY . /$PROJECT_DIR

WORKDIR /$PROJECT_DIR

RUN wget -U "Chrome/68.0.3210.118" "https://github.com/lazaronixon/ojdbc7/raw/master/com/heuristica/ojdbc7/12.1.0.2/$OJDBC" && \
    mkdir -p driver && \
    mv $OJDBC driver/ && \
    apt-get -qy autoremove

EXPOSE 8080

ENTRYPOINT ["gradle", "bootRun"]
