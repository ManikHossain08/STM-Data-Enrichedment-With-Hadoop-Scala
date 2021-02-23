package ca.mcit.scala.client

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

trait HadoopClient {

  val hdfsInputCluster = "/user/bdsf2001/manik/stm"
  val hdfsOutputCluster = "/user/bdsf2001/manik/course3"

  val conf = new Configuration()
  val hadoopConfDir: String = System.getenv("HADOOP_CONF_DIR")
  conf.addResource(new Path(s"$hadoopConfDir/core-site.xml"))
  conf.addResource(new Path(s"$hadoopConfDir/hdfs-site.xml"))

  val fileSystem: FileSystem = FileSystem.get(conf)
}
