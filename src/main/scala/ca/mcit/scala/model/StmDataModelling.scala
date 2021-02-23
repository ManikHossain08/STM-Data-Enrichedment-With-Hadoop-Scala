package ca.mcit.scala.model


case class CalendarDate(serviceId: String, date: String, exceptionType: Int)

object CalendarDate {
  def apply(line: String): CalendarDate = {
    val fields: Array[String] = line.split(",", -1)
    CalendarDate(fields(0), fields(1), fields(2).toInt)
  }

  def toCsv(calDate: Option[CalendarDate]): String = {
    calDate match {
      case Some(calDate) => s"${calDate.date},${calDate.exceptionType}"
      case None => ",,"
    }
  }
}

case class Route(routeId: String, longRouteName: String, routeColor: String)

object Route {
  def apply(line: String): Route = {
    val fields: Array[String] = line.split(",", -1)
    Route(fields(0), fields(3), fields(6))
  }

  def toCsv(route: Option[Route]): String = {
    route match {
      case Some(route) => s"${route.longRouteName},${route.routeColor}"
      case None => ",,"
    }
  }
}

case class Trips(tripId: String, serviceId: String, routeId: String, tripHeadSign: String, wheelChairAccessible: Boolean)

object Trips {
  def apply(line: String): Trips = {
    val fields: Array[String] = line.split(",", -1)
    val isWheelChairAccessible: Boolean = if (fields(6).toInt == 1) true else false
    Trips(fields(2), fields(1), fields(0), fields(3), isWheelChairAccessible)
  }

  def toCsv(trip: Trips): String = {
    s"${trip.tripId},${trip.serviceId},${trip.routeId},${trip.tripHeadSign},${trip.wheelChairAccessible}"
  }
}

case class TripRoute(trips: Trips, route: Option[Route])

object TripRoute {
  def toCsv(tripRoute: TripRoute): String = {
    s"${Trips.toCsv(tripRoute.trips)},${Route.toCsv(tripRoute.route)}"
  }
}

case class EnrichedTrip(tripRoute: TripRoute, calendarDate: Option[CalendarDate])

object EnrichedTrip {
  val enrichTripSchema: String = s"trip_id,route_id,service_id,trip_head_sign,wheelchairAccessible," +
    "long_route_name,route_color,date,exceptionType\n"

  def toCsv(enrichedTrip: EnrichedTrip): String = {
    s"${TripRoute.toCsv(enrichedTrip.tripRoute)},${CalendarDate.toCsv(enrichedTrip.calendarDate)}\n"
  }
}