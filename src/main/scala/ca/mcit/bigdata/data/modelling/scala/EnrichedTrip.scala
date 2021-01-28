package ca.mcit.bigdata.data.modelling.scala

import ca.mcit.bigdata.data.writing.scala.IOFileLinks
import ca.mcit.bigdata.schema.scala.{CalendarDate, Route, Trips}


case class TripRoute(trips: Trips, route: Option[Route])

case class EnrichedTrip(tripRoute: TripRoute, calendarDate: Option[CalendarDate])

object EnrichedTrip {
  def getEnrichedTripsList(): List[EnrichedTrip] = {
    val trips = Trips(IOFileLinks.trips).toList
    val routes = Route(IOFileLinks.routes)
    val tripRoute = RelationalJoin.LeftJoinTripRoute(trips, routes)
    val calenderDate = CalendarDate(IOFileLinks.calendarDate)
    val enrichedTrips: List[EnrichedTrip] = RelationalJoin.LeftJoinEnrichedTrip(tripRoute, calenderDate)

    enrichedTrips
  }
}
