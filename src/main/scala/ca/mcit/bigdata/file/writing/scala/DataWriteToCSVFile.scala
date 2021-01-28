package ca.mcit.bigdata.file.writing.scala

import ca.mcit.bigdata.data.enrichment.scala.{EnrichedTrip, TripRoute}
import ca.mcit.bigdata.schema.scala.{CalendarDate, Route, Trips}
import java.io.{BufferedWriter, File, FileWriter}

class DataWriteToCSVFile() {
  val csvSchema = s"trip_id,route_id,service_id,trip_head_sign,wheelchairAccessible,long_route_name,route_color,date,exceptionType\n"

  def writeFile(filename: String): Unit = {
    val file = new File(filename)
    if (file.exists()) file.delete()
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(csvSchema)

    val enrichedTrips = EnrichedTrip.getEnrichedTripsList()
    enrichedTrips.foreach {
      case EnrichedTrip(TripRoute(Trips(tripId, serviceId, routeId, tripHeadSign, wheelChairAccessible),
      Some(Route(_, longRouteName, routeColor))), Some(CalendarDate(_, date, exceptionType))) =>
        bw.write(s"$tripId,$routeId,$serviceId,$tripHeadSign,$wheelChairAccessible,$longRouteName,$routeColor,$date,$exceptionType\n")

      case EnrichedTrip(TripRoute(Trips(tripId, serviceId, routeId, tripHeadSign, wheelChairAccessible),
      Some(Route(_, longRouteName, routeColor))), None) =>
        bw.write(s"$tripId,$routeId,$serviceId,$tripHeadSign,$wheelChairAccessible,$longRouteName,$routeColor,,\n")
    }

    bw.close()
  }
}
