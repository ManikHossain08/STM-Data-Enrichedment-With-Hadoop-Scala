package ca.mcit.bigdata.data.modelling.scala

import ca.mcit.bigdata.schema.scala.{CalendarDate, Route, Trips}

/*
trait RelationalJoin[L, R, Q] {
  def join(a: List[L], b: List[R]): List[Q]
}

 */
object RelationalJoin {
  def LeftJoinTripRoute(a: List[Trips], b: List[Route]): List[TripRoute] = {
    val lookup: Map[Int, Route] = b.map(route => route.routeId -> route).toMap
    a.map(tripRoute =>
      if (lookup.contains(tripRoute.routeId.toInt))
        TripRoute(tripRoute, Some(lookup(tripRoute.routeId.toInt)))
      else TripRoute(tripRoute, None)
    )
  }

  def LeftJoinEnrichedTrip(a: List[TripRoute], b: List[CalendarDate]): List[EnrichedTrip] = {
    val lookup: Map[String, CalendarDate] = b.map(calendarDate => calendarDate.serviceId -> calendarDate).toMap
    a.map(enrichedTrips =>
      if (lookup.contains(enrichedTrips.trips.serviceId))
        EnrichedTrip(enrichedTrips, Some(lookup(enrichedTrips.trips.serviceId)))
      else EnrichedTrip(enrichedTrips, None)
    )
  }

}

/*

class LeftJoinTripRoute extends RelationalJoin[Trips, Route, TripRoute] {
  override def join(a: List[Trips], b: List[Route]): List[TripRoute] = {
    val t: Map[Int, Route] = b.map(route => route.routeId -> route).toMap
    a.map(trip =>
      if (t.contains(trip.routeId.toInt)) TripRoute(trip, Some(t(trip.routeId.toInt)))
      else TripRoute(trip, None)
    )
  }
}

class LeftJoinEnrichedTrip extends RelationalJoin[TripRoute, CalendarDate, EnrichedTrip] {
  override def join(a: List[TripRoute], b: List[CalendarDate]): List[EnrichedTrip] = {
    val t: Map[String, CalendarDate] = b.map(tripRoute => tripRoute.serviceId -> tripRoute).toMap
    a.map(tripRoute =>
      if (t.contains(tripRoute.trips.serviceId)) EnrichedTrip(tripRoute, Some(t(tripRoute.trips.serviceId)))
      else EnrichedTrip(tripRoute, None)
    )
  }
}*/