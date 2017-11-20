name := """testscala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.voltdb" % "voltdbclient" % "5.0",
  "mysql" % "mysql-connector-java" % "5.1.12",
  "com.h2database" % "h2" % "1.4.196",
  "com.zaxxer" % "HikariCP-java6" % "2.3.2",
  "com.mchange" % "c3p0" % "0.9.2.1"
)

