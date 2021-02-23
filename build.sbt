name := "course3project"

version := "0.1"

scalaVersion := "2.12.12"
val hadoopVersion = "2.7.7"

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-common" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion
)