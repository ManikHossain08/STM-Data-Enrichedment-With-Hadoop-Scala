package ca.mcit.bigdata.schema.scala

import scala.collection.mutable

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class StopTime(tripId: String,
                    stopId: String,
                    stopSequence: Int)

object StopTime {
  def apply(fileName: String): mutable.MutableList[StopTime] = {
    val schemaObjList = SchemaObjectCollection.getDataFromSourceUsingIterator(fileName, convertStringDataToStopTimeObject)
    schemaObjList
  }

  def convertStringDataToStopTimeObject(line: String): StopTime = {
    val fields: Array[String] = line.split(",", -1)
    StopTime(fields(0), fields(3), fields(4).toInt)
  }
}

