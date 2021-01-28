package ca.mcit.bigdata.schema.scala

import scala.collection.mutable
import scala.io.{BufferedSource, Source}

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

object SchemaObjectCollection {
  // Way-1: Process data after getting load into memory
  def getDataFromSource[T](fileName: String, dataTransformer: String => T): List[T] = {
    val source: BufferedSource = Source.fromFile(fileName)
    val schemaObjList = source.getLines().toList.tail.map(dataTransformer)
    source.close()
    schemaObjList
  }

  /**
   * Note: I use Mutable List to collect the JVM object using Iterator but instead of
   * using mutableList we can directly insert data to real database as a persistent object
   * but I just store it in a ADT (collections) to make everything consistent.
   *
   * I also could print each converted object onto the console using println(singleObject) but
   * I store it in mutable List as db.
   *
   * I used this approach only for processing stop_time.txt, trips.txt file as these 2  datasets are really big
   * so I don't want to store it in the memory. But we can use Iterator for processing all the files.
   *
   */
  //  Way-2(Best approach for big file): process data as it gets ingested
  def getDataFromSourceUsingIterator[T](fileName: String, dataTransformer: String => T): mutable.MutableList[T] = {
    val schemaObjList = new scala.collection.mutable.MutableList[T]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      schemaObjList += List(lineIterator.next()).map(dataTransformer).head
    }
    source.close()
    schemaObjList
  }
}
