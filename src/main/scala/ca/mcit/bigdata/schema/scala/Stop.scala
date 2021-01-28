package ca.mcit.bigdata.schema.scala

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class Stop(stopId: String,
                stopName: String,
                stopLatitude: Double,
                stopLongitude: Double,
                wheelChairBoarding: Int)

object Stop {
  def apply(fileName: String): List[Stop] = {
    val schemaObjList = SchemaObjectCollection.getDataFromSource(fileName, convertStringDataToTripsObject)
    schemaObjList
  }

  def convertStringDataToTripsObject[T](line: String): Stop = {
    val fields: Array[String] = line.split(",", -1)
    Stop(fields(0), fields(2), fields(3).toDouble, fields(4).toDouble, fields(8).toInt)
  }
}

