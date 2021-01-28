package ca.mcit.bigdata.scala.dataModel

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class StopTime(tripId: String,
                    stopId: String,
                    stopSequence: Int)

object StopTime {
  def apply(line: String): StopTime = {
    val fields: Array[String] = line.split(",", -1)
    StopTime(fields(0), fields(3), fields(4).toInt)
  }
}

