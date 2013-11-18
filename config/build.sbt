name := "config"

version := "1.0-SNAPSHOT"

organization := "oose.play"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
	"org.specs2" %% "specs2" % "2.2.2" % "test",
	"junit" % "junit" % "4.11" % "test"
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

