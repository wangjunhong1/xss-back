FROM maven:3.8.1-openjdk-8
LABEL maintainer="wangjunhong <wangjunhong@email.ncu.edu.cn>"
COPY . /xss-back/
WORKDIR /xss-back
RUN ["mvn","clean","package","-DskipTests"]
CMD ["java","-jar", "target/xss_back-0.0.1-SNAPSHOT.jar"]