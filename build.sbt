name := "sillycat-throttle"

version := "1.0"

libraryDependencies ++= Seq(
  "org.slf4j"        % "slf4j-api" % "1.7.12",                       // MIT
  "com.typesafe.akka"   %% "akka-actor" % "2.3.2",
  "com.typesafe.akka"   %% "akka-testkit" % "2.3.2",
  "com.typesafe.akka"   %% "akka-transactor" % "2.3.2",
  "com.typesafe.akka"   %% "akka-kernel" % "2.3.2",
  "com.typesafe.akka" %% "akka-contrib" % "2.3.2",
  "joda-time"%      "joda-time"       % "2.9.2",
  "org.joda" %  "joda-convert" % "1.8.1",
  "com.typesafe"     % "config" % "1.3.0",                        // Apache v2
  "org.slf4j" % "slf4j-api" % "1.7.12",                       // MIT
  "org.slf4j" % "slf4j-log4j12" % "1.7.12",
  "org.scalatest"    %% "scalatest" % "2.2.0" % "test",          // Apache v2
  "org.mockito"      % "mockito-all" % "1.9.5" % "test"            // MIT
)
