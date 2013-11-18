name := "jsrouter"

organization := "oose.play"

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play" % "2.2.1" % "provided",
    "org.webjars" %% "webjars-play" % "2.2.0" ,
    "org.webjars" % "angularjs" % "1.2.0-rc.3" ,
    "org.webjars" % "requirejs" % "2.1.8"
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

publishArtifact in (Compile, packageDoc) := false

releaseSettings
