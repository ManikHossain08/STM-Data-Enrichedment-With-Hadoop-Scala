package ca.mcit.bigdata.scala.fileReadWrite

import ca.mcit.bigdata.scala.dataModel.{Calender, Route, Trips}
import ca.mcit.bigdata.scala.dataEnrichment.{EnrichedTrip, TripRoute}
import java.io.{BufferedWriter, File, FileWriter}


class WriteDataToCSVFile() {
  val csvSchema: String = s"trip_id,route_id,service_id,trip_head_sign,wheelchairAccessible," +
    "long_route_name,route_color,monday,tuesday,wednesday,thursday,friday,saturday,sunday,start_date,end_date\n"

  def writeFile(filename: String): Unit = {
    val file = new File(filename)
    if (file.exists()) file.delete()
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(csvSchema)

    val enrichedTrips = EnrichedTrip.getEnrichedTripsList
    enrichedTrips.foreach {
      case EnrichedTrip(TripRoute(Trips(tripId, serviceId, routeId, tripHeadSign, wheelChairAccessible),
      Some(Route(_, longRouteName, routeColor))),
      Some(Calender(_, monday, tuesday, wednesday, thursday, friday, saturday, sunday, startDate, endDate))) =>
        bw.write(s"$tripId,$routeId,$serviceId,$tripHeadSign,$wheelChairAccessible,$longRouteName,$routeColor," +
          s"$monday,$tuesday,$wednesday,$thursday,$friday,$saturday,$sunday,$startDate,$endDate\n")

      case EnrichedTrip(TripRoute(Trips(tripId, serviceId, routeId, tripHeadSign, wheelChairAccessible),
      Some(Route(_, longRouteName, routeColor))), None) =>
        bw.write(s"$tripId,$routeId,$serviceId,$tripHeadSign,$wheelChairAccessible,$longRouteName,$routeColor" +
          s",,,,,,,,,\n")
    }
    println(s"Enriched trips data write successfully on location: $filename")
    bw.close()
  }
}
