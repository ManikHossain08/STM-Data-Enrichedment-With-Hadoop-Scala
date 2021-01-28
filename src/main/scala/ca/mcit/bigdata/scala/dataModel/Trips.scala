package ca.mcit.bigdata.scala.dataModel

import scala.collection.mutable
import scala.util.control.Exception.allCatch
/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class Trips(tripId: String,
                 serviceId: String,
                 routeId: String,
                 tripHeadSign: String,
                 wheelChairAccessible: Boolean)

object Trips {
  def apply(fileName: String): mutable.MutableList[Trips] = {
    val schemaObjList = ObjectCollection.getDataFromSourceUsingIterator(fileName, convertStringDataToTripsObject)
    schemaObjList
  }

  def convertStringDataToTripsObject(line: String): Trips = {
    val fields: Array[String] = line.split(",", -1)
    val wheelChairAccessible: Boolean = if (fields(6).toInt == 1) true else false
    Trips(fields(2), fields(1), fields(0), fields(3), wheelChairAccessible)
  }

  def makeInt(s: String): Option[Int] = allCatch.opt(s.toInt)
}

