package ca.mcit.bigdata.scala.dataEnrichment

import ca.mcit.bigdata.scala.dataModel.Calender
import ca.mcit.bigdata.scala.fileReadWrite.{IOFileLinks, ReadDataFromFile}


case class EnrichedTrip(tripRoute: TripRoute, calendarDate: Option[Calender])

object EnrichedTrip {
  def getEnrichedTripsList: List[EnrichedTrip] = {
    val calender = ReadDataFromFile.getCalenderList(IOFileLinks.calender).toList
    val trips = ReadDataFromFile.getTripList(IOFileLinks.trips).toList
    val routes = ReadDataFromFile.getRouteList(IOFileLinks.routes).toList
    val tripRoute = RelationalJoin.LeftJoinTripRoute(trips, routes)
    val enrichedTrips: List[EnrichedTrip] = RelationalJoin.LeftJoinEnrichedTrip(tripRoute, calender)

    enrichedTrips
  }
}
