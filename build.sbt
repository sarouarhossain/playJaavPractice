name := """playPractice"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += guice

libraryDependencies += javaJpa
libraryDependencies += javaJdbc
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.18"
// https://mvnrepository.com/artifact/org.hibernate/hibernate-core
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.4.9.Final"
libraryDependencies += "org.hibernate" % "hibernate-jpamodelgen" % "5.4.5.Final"


PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"