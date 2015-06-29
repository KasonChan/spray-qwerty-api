name := "QwertyAPI"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.2"
  Seq(
    // Spray
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "io.spray" %% "spray-json" % sprayV,
    "io.spray" %% "spray-httpx" % sprayV,
    // Akka
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    // Specs2
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    // Mongodb casbah
    "org.mongodb" %% "casbah" % "2.8.1")
}
    