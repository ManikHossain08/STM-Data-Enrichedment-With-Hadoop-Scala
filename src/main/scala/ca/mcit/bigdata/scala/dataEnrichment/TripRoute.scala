package ca.mcit.bigdata.scala.dataEnrichment

import ca.mcit.bigdata.scala.dataModel.{Route, Trips}

case class TripRoute(trips: Trips, route: Option[Route])
