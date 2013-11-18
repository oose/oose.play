name := "actions"

organization := "oose.play"

version := "1.0-SNAPSHOT"

resolvers += "Springsource" at "http://repo.springsource.org/milestone"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.2.1" % "provided",
  "com.typesafe.play" %% "play-test" % "2.2.1" % "provided",
  "org.springframework.scala" %%"spring-scala" % "1.0.0.RC1"
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
