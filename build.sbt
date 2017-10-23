name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.11.7"

libraryDependencies ++= Seq (
  jdbc,
  cache,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test,
  "com.h2database" % "h2" % "1.4.194",
   ws,
  "org.scala-lang.modules" %% "scala-async" % "0.9.2",
  "de.leanovate.play-mockws" %% "play-mockws" % "2.5.1",
  "com.typesafe.akka" %% "akka-persistence" % "2.5.6"
 // "com.typesafe.play" %% "play-slick" % "3.0.1",
  //"org.xerial" % "sqlite-jdbc" % "3.7.2"
)
