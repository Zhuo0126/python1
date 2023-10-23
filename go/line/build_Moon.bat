@echo off
SET JAVA_HOME=C:/Program Files/Java/jdk-17.0.7+7\
SET MAVEN_HOME=D:/maven/apache-maven-3.8.5
SET PATH=%PATH%;%JAVA_HOME%/bin;%MAVEN_HOME%/bin
call mvn clean install -Dmaven.repo.local=D:/PCode/den  -f pom.xml
pause