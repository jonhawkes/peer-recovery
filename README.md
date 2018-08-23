## Peer Recovery Test Application
This project contains tests for Liberty peer recovery in ICP.`

### Project Contents
The project comprises an application as a  docker container deployed into a Kubernetes cluster.

Currently, the application is hello-app which is independent maven project in its own subdirectory of this project.

Once installed, the application can be accessed at:
```
http://<your kubernetes cluster>/peer-recovery-test/hello
```

### Build

The steps involved in building the project are:
1.  [Build the test application](#build-the-test-application)
2.  [Package the application as docker images](#package-the-test-application)
3.  [Push the image to a repository](#push-the-test-application)
5.  [Configure your cluster to run the application](#install-the-test-application)

### Build the Test Application

The test application resides in the hello-app directory under this project. To build the application:
1.  (cd hello-app; mvn install)

### Package the Test Application

The application should be packaged within the environment you use to access docker in your develpoment environment. The application has a Dockerfile which is used to assemble its docker image. The image should be tagged such that it can be subsequently pushed to your docker repository. Assuming you will be using dockerhub, and your docker id is `<docker id>`:
1.  (cd hello-app; docker build -t `<docker id>`/hello .)

### Push the Test Application

The service images need to be published to a repository so they will be available to run in your cluster. Assuming you will be using Docker Hub:
1.  docker login
2.  docker push 
3.  docker push `<docker id>`/hello

### Install the Test Application

The test application is installed into your cluster using the deployment yaml in the manifests directory. The yaml is applied using kubectl.
1.  kubectl apply -f manifests/hello.yaml

### Run the Sample

You now have the test application installed in your cluster. The entrypoint for the sample is:
```
http://<your kubernetes cluster>/peer-recovery-test/hello
```
The result should look something like:
```
Hello, world from hello-deployment-6ddb869c6b-7t456 at Thu Aug 23 14:48:14 UTC 2018
PATH: /opt/ol/wlp/bin:/opt/ol/docker/:/opt/ibm/java/jre/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
HELLO_SERVICE_PORT_8080_TCP_PORT: 8080
KUBERNETES_PORT: tcp://10.0.0.1:443
JAVA_HOME: /opt/ibm/java/jre
HELLO_SERVICE_PORT_8080_TCP_ADDR: 10.0.0.135
KUBERNETES_SERVICE_HOST: 10.0.0.1
WLP_SKIP_MAXPERMSIZE: true
HELLO_SERVICE_SERVICE_PORT: 8080
keystore_password: vRe7AS8HE8rDhjUgZ0Y9wiI
LIBERTY_VERSION: 18.0.0.1
HELLO_SERVICE_PORT_8080_TCP_PROTO: tcp
PWD: /opt/ol/wlp/output/defaultServer
JAVA_VERSION: 1.8.0_sr5fp17
IBM_JAVA_COMMAND_LINE: /opt/ibm/java/jre/bin/java -javaagent:/opt/ol/wlp/bin/tools/ws-javaagent.jar -Djava.awt.headless=true -jar /opt/ol/wlp/bin/tools/ws-server.jar defaultServer
KUBERNETES_PORT_443_TCP: tcp://10.0.0.1:443
HELLO_SERVICE_SERVICE_HOST: 10.0.0.135
KUBERNETES_PORT_443_TCP_ADDR: 10.0.0.1
WLP_OUTPUT_DIR: /opt/ol/wlp/output
WLP_USER_DIR: /opt/ol/wlp/usr
KUBERNETES_PORT_443_TCP_PROTO: tcp
OLDPWD: /
INVOKED: /opt/ol/wlp/bin/server
KUBERNETES_SERVICE_PORT: 443
HELLO_SERVICE_PORT: tcp://10.0.0.135:8080
LIBERTY_SHA: a059c422c6ddd53276804b8e6f2ee0b00c97e1a7
X_LOG_FILE: console.log
HOSTNAME: hello-deployment-6ddb869c6b-7t456
X_CMD: /opt/ibm/java/jre/bin/java 
X_LOG_DIR: /logs
KUBERNETES_PORT_443_TCP_PORT: 443
HELLO_SERVICE_SERVICE_PORT_HTTP: 8080
IBM_JAVA_OPTIONS: -Xshareclasses:name=liberty-%u,nonfatal,cacheDir="/opt/ol/wlp/output/.classCache",cacheDirPerm=1000 -XX:ShareClassesEnableBCI -Xscmx60m -Xscmaxaot8m -XX:+UseContainerSupport
KUBERNETES_SERVICE_PORT_HTTPS: 443
HELLO_SERVICE_PORT_8080_TCP: tcp://10.0.0.135:8080
HOME: /root
```
