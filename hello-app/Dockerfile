FROM open-liberty:webProfile7
MAINTAINER IBM Java engineering at IBM Cloud
COPY /target/liberty/wlp/usr/servers/defaultServer /config/
ARG LICENSE_JAR_URL
RUN \ 
  if [ $LICENSE_JAR_URL ]; then \
    wget $LICENSE_JAR_URL -O /tmp/license.jar \
    && java -jar /tmp/license.jar -acceptLicense /opt/ibm \
    && rm /tmp/license.jar; \
  fi
RUN apt-get update -y && apt-get install -y openjdk-8-jdk
COPY /src/main/liberty/com.ibm.ws.recoverylog/com com
RUN jar uvf /opt/ol/wlp/lib/com.ibm.ws.recoverylog_1.0.20.jar com && rm -rf com
COPY /src/main/liberty/com.ibm.tx.jta/com com
RUN jar uvf /opt/ol/wlp/lib/com.ibm.tx.jta_1.0.20.jar com && rm -rf com
