package ca.mcit.scala.client

import ca.mcit.scala.model.Utility.{filesToRecordsList, filesToStream}
import ca.mcit.scala.model.{CalendarDate, EnrichedTrip, Route, TripRoute, Trips}
import org.apache.hadoop.fs.Path

object AppClient extends App with HadoopClient {

  val routeLookUp = filesToRecordsList("routes.txt").map(Route(_))
    .map(route => route.routeId -> route).toMap
  val calendarLookUp = filesToRecordsList("calendar_dates.txt").map(CalendarDate(_))
    .map(calendar => calendar.serviceId -> calendar).toMap

  val enrichedTrips = fileSystem.create(new Path(s"$hdfsOutputCluster/enriched_trips.txt"), true)
  enrichedTrips.writeUTF(EnrichedTrip.enrichTripSchema)
  val (tripRoutes, fileStream) = filesToStream("trips.txt")
  while (tripRoutes.hasNext) {
    val trip = Trips(tripRoutes.next())
    val route = routeLookUp.get(trip.routeId)
    val calendar = calendarLookUp.get(trip.serviceId)
    val tripRoute = TripRoute(trip, route)
    enrichedTrips.writeUTF(s"${EnrichedTrip.toCsv(EnrichedTrip(tripRoute, calendar))}")
  }

  enrichedTrips.close()
  fileStream.close()
  fileSystem.close()
}
