package ca.mcit.bigdata.scala.dataEnrichment

import ca.mcit.bigdata.scala.dataModel.{CalendarDate, Route, Trips}
import ca.mcit.bigdata.scala.fileReadWrite.{IOFileLinks, ReadDataFromFile}


case class EnrichedTrip(tripRoute: TripRoute, calendarDate: Option[CalendarDate])

object EnrichedTrip {
  def getEnrichedTripsList(): List[EnrichedTrip] = {
    val trips = ReadDataFromFile.getTripList(IOFileLinks.trips).toList
    val routes = ReadDataFromFile.getRouteList(IOFileLinks.routes).toList
    val tripRoute = RelationalJoin.LeftJoinTripRoute(trips, routes)
    val calenderDate = ReadDataFromFile.getCalendarDateList(IOFileLinks.calendarDate).toList
    val enrichedTrips: List[EnrichedTrip] = RelationalJoin.LeftJoinEnrichedTrip(tripRoute, calenderDate)

    enrichedTrips
  }
}
