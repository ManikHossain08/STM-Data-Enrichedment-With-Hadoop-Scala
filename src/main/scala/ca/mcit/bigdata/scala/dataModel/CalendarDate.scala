package ca.mcit.bigdata.scala.dataModel

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class CalendarDate(serviceId: String,
                        date: String,
                        exceptionType: Int)

object CalendarDate {
  def apply(fileName: String): List[CalendarDate] = {
    val schemaObjList = ObjectCollection.getDataFromSource(fileName, convertStringDataToCalenderObject)
    schemaObjList
  }

  def convertStringDataToCalenderObject(line: String): CalendarDate = {
    val fields: Array[String] = line.split(",", -1)
    CalendarDate(fields(0), fields(1), fields(2).toInt)
  }
}

