package ca.mcit.bigdata.scala.dataModel

import scala.collection.mutable
import scala.io.{BufferedSource, Source}

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

object ObjectCollection {
  // Way-1: Process data after getting load into memory
  def getDataFromSource[T](fileName: String, dataTransformer: String => T): List[T] = {
    val source: BufferedSource = Source.fromFile(fileName)
    val schemaObjList = source.getLines().toList.tail.map(dataTransformer)
    source.close()
    schemaObjList
  }


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
