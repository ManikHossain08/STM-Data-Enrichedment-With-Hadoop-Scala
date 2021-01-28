package ca.mcit.bigdata.scala.dataEnrichment

import ca.mcit.bigdata.scala.dataModel.{CalendarDate, Route, Trips}
import ca.mcit.bigdata.scala.fileWriting.IOFileLinks


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
