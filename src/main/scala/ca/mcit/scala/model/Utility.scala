package ca.mcit.scala.model

import ca.mcit.scala.client.HadoopClient
import org.apache.hadoop.fs.{FSDataInputStream, Path}

object Utility extends HadoopClient {

  def filesToRecordsList(fileName: String): List[String] = {
    val filesStream = fileSystem.open(new Path(s"$hdfsInputCluster/$fileName"))
    val recordsList = Iterator.continually(filesStream.readLine()).takeWhile(_ != null).toList.tail
    filesStream.close()
    recordsList
  }

  def filesToStream(fileName: String): (Iterator[String], FSDataInputStream) = {
    val fileStream = fileSystem.open(new Path(s"$hdfsInputCluster/$fileName"))
    val iterator = Iterator.continually(fileStream.readLine()).takeWhile(_ != null)
    iterator.next()
    (iterator, fileStream)
  }

}
