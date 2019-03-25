FROM goodforgod/debian-jdk10-oracle:sid

ARG BRANCH=master

# Install curl & unzip
RUN apt-get -q update && \
    apt-get -qy install wget && \
    apt-get -qy install curl && \
    apt-get -qy install unzip

# Download project, build it, remove installed stuff
RUN cd / && \
    curl -LOk https://github.com/GoodforGod/oracle-aggregator/archive/$BRANCH.zip && \
    unzip -q $BRANCH.zip && \
    cd oracle-aggregator* && \
    wget -U "Chrome/68.0.3210.118" https://github.com/lazaronixon/ojdbc7/raw/master/com/heuristica/ojdbc7/12.1.0.2/ojdbc7-12.1.0.2.jar && \
    mkdir driver && \
    mv ojdbc7-12.1.0.2.jar driver/ && \
    chmod u+x gradlew && \
    ./gradlew bootJar && \
    apt-get -y remove curl && \
    apt-get -y remove unzip && \
    apt-get -y remove wget && \
    apt-get -y autoremove && \
    mv build/libs/oracle-aggregator-1.0.0.jar /

# Enviroment variables for DB connection (default)
# https://hub.docker.com/_/oracle-database-enterprise-edition
ENV ORA_USER="sys as sysdba" \
    ORA_PASS="Oradoc_db1" \
    ORA_URL="jdbc:oracle:thin:@localhost:32769:ORCLCDB"

ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/oracle-aggregator-1.0.0.jar"]
