package ca.mcit.bigdata.scala.dataModel

case class Calender(serviceId: String,
                    monday: String,
                    tuesday: String,
                    wednesday: String,
                    thursday: String,
                    friday: String,
                    saturday: String,
                    sunday: String,
                    startDate: String,
                    endDate: String)

object Calender {
  def apply(line: String): Calender = {
    val fields: Array[String] = line.split(",", -1)
    Calender(fields(0), fields(1), fields(2), fields(3), fields(4), fields(5), fields(6), fields(7), fields(8), fields(9))
  }
}