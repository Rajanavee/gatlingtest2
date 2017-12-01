enablePlugins(GatlingPlugin)

import io.gatling.sbt.GatlingPlugin

name := "gatlingtest"

version := "0.0.2"

scalaVersion := "2.11.5"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

cleanKeepFiles ++= Seq("resolution-cache", "streams").map(target.value / _)

javaOptions in Gatling := overrideDefaultJavaOptions("-Xms512m", "-Xmx768m")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.2" % "test,it"

libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.2" % "test,it"

libraryDependencies += "mysql"                 % "mysql-connector-java"      % "5.1.34" % "test,it"

