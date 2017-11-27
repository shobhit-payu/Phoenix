name := """payu"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.6"
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.6"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.1"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.2"