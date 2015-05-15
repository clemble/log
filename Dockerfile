FROM java:8-jre
MAINTAINER antono@clemble.com

EXPOSE 10017

ADD target/log-*-SNAPSHOT.jar /data/log.jar

CMD java -jar -Dspring.profiles.active=cloud -Dlogging.config=classpath:logback.cloud.xml -Dserver.port=10017 /data/payment-transaction.jar
