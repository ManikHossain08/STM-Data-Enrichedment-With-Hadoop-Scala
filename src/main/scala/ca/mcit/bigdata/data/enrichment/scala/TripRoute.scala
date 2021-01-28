package ca.mcit.bigdata.data.enrichment.scala

import ca.mcit.bigdata.schema.scala.{Route, Trips}

case class TripRoute(trips: Trips, route: Option[Route])
