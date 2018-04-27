import com.typesafe.sbt.SbtGit.git._

organization := "com.timushev"
name := "scalatags-rx"
version := "0.4.0"

version := {
  (version.value, gitCurrentTags.value) match {
    case (v, w :: Nil) if s"v$v" == w => v
    case (v, Nil) => s"$v-SNAPSHOT"
    case _ => throw new IllegalStateException("Version and tag do not match")
  }
}

crossScalaVersions := Seq("2.11.12", "2.12.5")
scalaVersion := "2.12.5"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.4.0",
  "com.lihaoyi" %%% "scalatags" % "0.6.7",
  "com.lihaoyi" %%% "utest" % "0.6.4" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
