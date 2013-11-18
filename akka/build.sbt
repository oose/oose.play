name := "akka"

organization := "oose.play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "2.2.2" % "provided", 
    "com.typesafe.akka" %% "akka-testkit" % "2.2.0" % "provided" 
)     

val snapshots = Some(Resolver.file("Local github (snapshots)",  
  new File(Path.userHome.absolutePath+"/oose.github.io/m2/snapshots")))

val releases =  Some(Resolver.file("Local github (releases)", 
  new File(Path.userHome.absolutePath+"/oose.github.io/m2/releases")))

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  if (v.trim.endsWith("SNAPSHOT"))
    snapshots
  else
    releases
}

releaseSettings


