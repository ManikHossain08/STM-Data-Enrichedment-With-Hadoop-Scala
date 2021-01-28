package ca.mcit.bigdata.scala.dataEnrichment

import ca.mcit.bigdata.scala.dataModel.{Calender, Route, Trips}


object RelationalJoin {
  def LeftJoinTripRoute(a: List[Trips], b: List[Route]): List[TripRoute] = {
    val lookup: Map[Int, Route] = b.map(route => route.routeId -> route).toMap
    a.map(tripRoute =>
      if (lookup.contains(tripRoute.routeId.toInt))
        TripRoute(tripRoute, Some(lookup(tripRoute.routeId.toInt)))
      else
        TripRoute(tripRoute, None)
    )
  }

  def LeftJoinEnrichedTrip(a: List[TripRoute], b: List[Calender]): List[EnrichedTrip] = {
    val lookup: Map[String, Calender] = b.map(calendar => calendar.serviceId -> calendar).toMap
    a.map(enrichedTrips =>
      if (lookup.contains(enrichedTrips.trips.serviceId))
        EnrichedTrip(enrichedTrips, Some(lookup(enrichedTrips.trips.serviceId)))
      else
        EnrichedTrip(enrichedTrips, None)
    )
  }
}
