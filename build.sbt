
lazy val actions = project

lazy val jsrouter = project

lazy val config = project

lazy val akka = project

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